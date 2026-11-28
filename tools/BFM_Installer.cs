using System;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Net;
using System.Reflection;
using System.Threading;
using System.Windows.Forms;

namespace BfmInstaller
{
    internal static class Program
    {
        private const string RawBaseUrl = "https://raw.githubusercontent.com/jbirdlebough1/JVEEPOO/main/";

        private static readonly string[][] InstallManifest = new[]
        {
            new[] { "BFM.HTM", "BFM.HTM", "500000" },
            new[] { "bfm.ico", "bfm.ico", "500" },
            new[] { "BFM_Launcher.exe", "BFM_Launcher.exe", "4000" },
            new[] { "w11_htm_app/server.py", @"w11_htm_app\server.py", "50000" },
            new[] { "w11_htm_app/cell.html", @"w11_htm_app\cell.html", "10000" }
        };

        private static string GetArgValue(string[] args, string prefix)
        {
            foreach (string a in args)
            {
                if (a.StartsWith(prefix, StringComparison.OrdinalIgnoreCase))
                {
                    return a.Substring(prefix.Length).Trim().Trim('"');
                }
            }
            return null;
        }

        private static bool HasFlag(string[] args, string flag)
        {
            foreach (string a in args)
            {
                if (string.Equals(a.Trim(), flag, StringComparison.OrdinalIgnoreCase))
                {
                    return true;
                }
            }
            return false;
        }

        private static void CreateShortcut(string shortcutPath, string targetPath, string workingDir, string iconLocation, string description)
        {
            string dir = Path.GetDirectoryName(shortcutPath);
            if (!string.IsNullOrEmpty(dir) && !Directory.Exists(dir))
            {
                Directory.CreateDirectory(dir);
            }

            Type shellType = Type.GetTypeFromProgID("WScript.Shell");
            if (shellType == null)
            {
                throw new InvalidOperationException("WScript.Shell COM object unavailable on this system.");
            }

            object shell = Activator.CreateInstance(shellType);
            try
            {
                object shortcut = shellType.InvokeMember(
                    "CreateShortcut",
                    BindingFlags.InvokeMethod,
                    null,
                    shell,
                    new object[] { shortcutPath }
                );
                Type scType = shortcut.GetType();
                scType.InvokeMember("TargetPath", BindingFlags.SetProperty, null, shortcut, new object[] { targetPath });
                scType.InvokeMember("WorkingDirectory", BindingFlags.SetProperty, null, shortcut, new object[] { workingDir });
                scType.InvokeMember("IconLocation", BindingFlags.SetProperty, null, shortcut, new object[] { iconLocation });
                scType.InvokeMember("Description", BindingFlags.SetProperty, null, shortcut, new object[] { description });
                scType.InvokeMember("Save", BindingFlags.InvokeMethod, null, shortcut, null);
            }
            finally
            {
                if (System.Runtime.InteropServices.Marshal.IsComObject(shell))
                {
                    System.Runtime.InteropServices.Marshal.ReleaseComObject(shell);
                }
            }
        }

        private static void LogFault(string scope, Exception ex)
        {
            try
            {
                string logPath = Path.Combine(Path.GetTempPath(), "bfm_installer_fault.log");
                File.AppendAllText(logPath, string.Format("[{0:yyyy-MM-dd HH:mm:ss}] [MIL-SPEC HANDLED FAULT] {1}: {2}{3}",
                    DateTime.Now, scope, ex, Environment.NewLine));
            }
            catch (Exception logEx)
            {
                Trace.WriteLine("[MIL-SPEC HANDLED FAULT] LogFault: " + logEx.Message);
            }
        }

        private static bool DownloadOrCopyAsset(string remoteRel, string localPath, int minBytes, string sourceDir, Action<string> logLine)
        {
            string targetDir = Path.GetDirectoryName(localPath);
            if (!string.IsNullOrEmpty(targetDir) && !Directory.Exists(targetDir))
            {
                Directory.CreateDirectory(targetDir);
            }

            string tmpPath = localPath + ".tmp";
            string url = RawBaseUrl + remoteRel;
            try
            {
                using (var wc = new WebClient())
                {
                    wc.Headers.Add("User-Agent", "BFM-Installer/1.0");
                    wc.DownloadFile(url, tmpPath);
                }
                var fi = new FileInfo(tmpPath);
                if (fi.Exists && fi.Length >= minBytes)
                {
                    if (File.Exists(localPath)) File.Delete(localPath);
                    File.Move(tmpPath, localPath);
                    logLine(string.Format("[OK] Downloaded {0} ({1:N0} bytes)", remoteRel, fi.Length));
                    return true;
                }
                if (File.Exists(tmpPath)) File.Delete(tmpPath);
            }
            catch (Exception ex)
            {
                LogFault("Download_" + remoteRel, ex);
                logLine("[!] Web download failed for " + remoteRel + ": " + ex.Message);
                if (File.Exists(tmpPath))
                {
                    try { File.Delete(tmpPath); } catch (Exception delEx) { LogFault("CleanupTmp", delEx); }
                }
            }

            // Offline fallback: copy sibling asset if run inside an extracted repo or USB kit
            string localFallback = Path.Combine(sourceDir, remoteRel.Replace('/', Path.DirectorySeparatorChar));
            if (File.Exists(localFallback))
            {
                File.Copy(localFallback, localPath, true);
                var fi = new FileInfo(localPath);
                if (fi.Length >= minBytes)
                {
                    logLine(string.Format("[OK] Copied bundled {0} ({1:N0} bytes)", remoteRel, fi.Length));
                    return true;
                }
            }

            return false;
        }

        private static bool RunInstall(
            string installDir,
            string desktopDir,
            string startDir,
            string sourceDir,
            bool noLaunch,
            Action<int, string> reportProgress,
            Action<string> logLine)
        {
            ServicePointManager.SecurityProtocol = (SecurityProtocolType)3072 | SecurityProtocolType.Tls;

            if (!Directory.Exists(installDir))
            {
                Directory.CreateDirectory(installDir);
            }

            int total = InstallManifest.Length;
            for (int i = 0; i < total; i++)
            {
                string remoteRel = InstallManifest[i][0];
                string relDest = InstallManifest[i][1];
                int minBytes = int.Parse(InstallManifest[i][2]);
                string destPath = Path.Combine(installDir, relDest);
                reportProgress(10 + (i * 14), "Installing " + remoteRel + "...");

                if (!DownloadOrCopyAsset(remoteRel, destPath, minBytes, sourceDir, logLine))
                {
                    reportProgress(100, "Failed to install " + remoteRel);
                    return false;
                }
            }

            reportProgress(85, "Creating Desktop and Start Menu shortcuts...");
            string launcherExe = Path.Combine(installDir, "BFM_Launcher.exe");
            string iconPath = Path.Combine(installDir, "bfm.ico") + ",0";

            string desktopLink = Path.Combine(desktopDir, "BFM Biometric Monitor.lnk");
            CreateShortcut(desktopLink, launcherExe, installDir, iconPath, "Launch BFM Biometric Function Monitor");
            logLine("[OK] Desktop shortcut: " + desktopLink);

            string startAppFolder = Path.Combine(startDir, "BFM Biometric Monitor");
            string startLink = Path.Combine(startAppFolder, "BFM Biometric Monitor.lnk");
            CreateShortcut(startLink, launcherExe, installDir, iconPath, "Launch BFM Biometric Function Monitor");
            logLine("[OK] Start Menu shortcut: " + startLink);

            reportProgress(100, "BFM Installation Complete!");
            logLine("[COMPLETE] Installed cleanly to " + installDir);

            if (!noLaunch && File.Exists(launcherExe))
            {
                Process.Start(new ProcessStartInfo
                {
                    FileName = launcherExe,
                    WorkingDirectory = installDir,
                    UseShellExecute = true
                });
            }
            return true;
        }

        [STAThread]
        private static int Main(string[] args)
        {
            string localApp = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);
            string installDir = GetArgValue(args, "/installdir=") ?? Path.Combine(localApp, "BFM");
            string desktopDir = GetArgValue(args, "/desktopdir=") ?? Environment.GetFolderPath(Environment.SpecialFolder.DesktopDirectory);
            string startDir = GetArgValue(args, "/startdir=") ?? Environment.GetFolderPath(Environment.SpecialFolder.Programs);
            bool noLaunch = HasFlag(args, "/nolaunch");
            bool silent = HasFlag(args, "/silent");
            string sourceDir = AppDomain.CurrentDomain.BaseDirectory.TrimEnd('\\', '/');

            if (silent)
            {
                try
                {
                    bool ok = RunInstall(
                        installDir, desktopDir, startDir, sourceDir, noLaunch,
                        (pct, msg) => { },
                        line => { }
                    );
                    return ok ? 0 : 1;
                }
                catch (Exception ex)
                {
                    LogFault("SilentMain", ex);
                    return 1;
                }
            }

            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);

            var form = new Form
            {
                Text = "BFM Biometric Function Monitor — Setup & Installer",
                Width = 620,
                Height = 400,
                StartPosition = FormStartPosition.CenterScreen,
                FormBorderStyle = FormBorderStyle.FixedDialog,
                MaximizeBox = false,
                BackColor = Color.White
            };

            try
            {
                form.Icon = Icon.ExtractAssociatedIcon(Assembly.GetExecutingAssembly().Location);
            }
            catch (Exception icoEx)
            {
                LogFault("FormIcon", icoEx);
            }

            var lblHeader = new Label
            {
                Text = "Installing BFM Biometric Function Monitor...",
                Font = new Font("Segoe UI", 12, FontStyle.Bold),
                ForeColor = Color.FromArgb(2, 132, 199),
                Location = new Point(20, 16),
                AutoSize = true
            };

            var lblStatus = new Label
            {
                Text = "Target folder: " + installDir,
                Font = new Font("Segoe UI", 9.5f),
                ForeColor = Color.FromArgb(51, 65, 85),
                Location = new Point(22, 46),
                Width = 560
            };

            var bar = new ProgressBar
            {
                Minimum = 0,
                Maximum = 100,
                Value = 5,
                Location = new Point(22, 74),
                Width = 560,
                Height = 22
            };

            var txtLog = new TextBox
            {
                Multiline = true,
                ReadOnly = true,
                ScrollBars = ScrollBars.Vertical,
                Font = new Font("Consolas", 9f),
                BackColor = Color.FromArgb(248, 250, 252),
                Location = new Point(22, 110),
                Width = 560,
                Height = 195
            };

            var btnClose = new Button
            {
                Text = "Installing...",
                Enabled = false,
                Location = new Point(462, 318),
                Width = 120,
                Height = 32
            };
            btnClose.Click += (s, e) => form.Close();

            form.Controls.AddRange(new Control[] { lblHeader, lblStatus, bar, txtLog, btnClose });

            bool installSuccess = false;
            form.Shown += (s, e) =>
            {
                var worker = new Thread(() =>
                {
                    try
                    {
                        installSuccess = RunInstall(
                            installDir,
                            desktopDir,
                            startDir,
                            sourceDir,
                            noLaunch,
                            (pct, msg) =>
                            {
                                form.BeginInvoke((Action)(() =>
                                {
                                    bar.Value = Math.Max(0, Math.Min(100, pct));
                                    lblStatus.Text = msg;
                                }));
                            },
                            line =>
                            {
                                form.BeginInvoke((Action)(() =>
                                {
                                    txtLog.AppendText(line + Environment.NewLine);
                                }));
                            }
                        );
                    }
                    catch (Exception ex)
                    {
                        LogFault("WorkerThread", ex);
                        form.BeginInvoke((Action)(() =>
                        {
                            txtLog.AppendText("[ERROR] " + ex.Message + Environment.NewLine);
                        }));
                    }
                    finally
                    {
                        form.BeginInvoke((Action)(() =>
                        {
                            btnClose.Enabled = true;
                            btnClose.Text = installSuccess ? "Done" : "Close";
                            if (installSuccess)
                            {
                                lblHeader.Text = "BFM Installed & Ready!";
                                lblHeader.ForeColor = Color.FromArgb(21, 128, 61);
                            }
                            else
                            {
                                lblHeader.Text = "BFM Installation Failed";
                                lblHeader.ForeColor = Color.FromArgb(185, 28, 28);
                            }
                        }));
                    }
                });
                worker.IsBackground = true;
                worker.SetApartmentState(ApartmentState.STA);
                worker.Start();
            };

            Application.Run(form);
            return installSuccess ? 0 : 1;
        }
    }
}
