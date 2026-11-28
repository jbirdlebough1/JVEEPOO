using System;
using System.Diagnostics;
using System.IO;
using System.Net.Sockets;
using System.Threading;

namespace BfmLauncher
{
    internal static class Program
    {
        private const int ServerPort = 8899;
        private const string HostedLiveUrl = "https://jbirdlebough1.github.io/JVEEPOO/";

        private static string GetBaseDir()
        {
            return AppDomain.CurrentDomain.BaseDirectory.TrimEnd('\\', '/');
        }

        private static bool IsServerListening(string host, int port, int timeoutMs)
        {
            try
            {
                using (var client = new TcpClient())
                {
                    var result = client.BeginConnect(host, port, null, null);
                    bool connected = result.AsyncWaitHandle.WaitOne(TimeSpan.FromMilliseconds(timeoutMs));
                    if (!connected) return false;
                    client.EndConnect(result);
                    return true;
                }
            }
            catch (Exception ex)
            {
                LogFault("IsServerListening", ex);
                return false;
            }
        }

        private static string FindPythonExecutable(string baseDir)
        {
            string bundled = Path.Combine(baseDir, @"tools\python\python.exe");
            if (File.Exists(bundled)) return bundled;

            string[] names = { "python.exe", "py.exe" };
            string pathEnv = Environment.GetEnvironmentVariable("PATH") ?? string.Empty;
            foreach (string dir in pathEnv.Split(new[] { ';' }, StringSplitOptions.RemoveEmptyEntries))
            {
                string cleanDir = dir.Trim();
                if (string.IsNullOrEmpty(cleanDir)) continue;
                foreach (string name in names)
                {
                    try
                    {
                        string cand = Path.Combine(cleanDir, name);
                        if (File.Exists(cand)) return cand;
                    }
                    catch (Exception ex)
                    {
                        LogFault("FindPythonExecutable", ex);
                    }
                }
            }
            return null;
        }

        private static void EnsureServerRunning(string baseDir)
        {
            if (IsServerListening("127.0.0.1", ServerPort, 150))
            {
                return;
            }

            string serverScript = Path.Combine(baseDir, @"w11_htm_app\server.py");
            if (!File.Exists(serverScript))
            {
                string installedRoot = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData), "BFM");
                string altScript = Path.Combine(installedRoot, @"w11_htm_app\server.py");
                if (File.Exists(altScript))
                {
                    serverScript = altScript;
                    baseDir = installedRoot;
                }
                else
                {
                    return;
                }
            }

            string pythonExe = FindPythonExecutable(baseDir);
            if (string.IsNullOrEmpty(pythonExe))
            {
                return;
            }

            try
            {
                var psi = new ProcessStartInfo
                {
                    FileName = pythonExe,
                    Arguments = "\"" + serverScript + "\"",
                    WorkingDirectory = baseDir,
                    UseShellExecute = false,
                    CreateNoWindow = true,
                    WindowStyle = ProcessWindowStyle.Hidden
                };
                Process.Start(psi);
                for (int i = 0; i < 16; i++)
                {
                    if (IsServerListening("127.0.0.1", ServerPort, 150)) break;
                    Thread.Sleep(150);
                }
            }
            catch (Exception ex)
            {
                LogFault("EnsureServerRunning", ex);
            }
        }

        private static string ResolveLaunchUrl(string baseDir)
        {
            string bfmPath = Path.Combine(baseDir, "BFM.HTM");
            if (File.Exists(bfmPath))
            {
                return new Uri(bfmPath).AbsoluteUri;
            }

            string installedBfm = Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData), @"BFM\BFM.HTM");
            if (File.Exists(installedBfm))
            {
                return new Uri(installedBfm).AbsoluteUri;
            }

            string idxPath = Path.Combine(baseDir, "index.html");
            if (File.Exists(idxPath))
            {
                return new Uri(idxPath).AbsoluteUri;
            }

            if (IsServerListening("127.0.0.1", ServerPort, 180))
            {
                return "http://127.0.0.1:" + ServerPort + "/";
            }

            return HostedLiveUrl;
        }

        private static string FindBrowserExecutable()
        {
            string pf = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFiles);
            string pfx86 = Environment.GetFolderPath(Environment.SpecialFolder.ProgramFilesX86);
            string localApp = Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData);

            string[] candidates =
            {
                Path.Combine(pf, @"Google\Chrome\Application\chrome.exe"),
                Path.Combine(pfx86, @"Google\Chrome\Application\chrome.exe"),
                Path.Combine(localApp, @"Google\Chrome\Application\chrome.exe"),
                Path.Combine(pfx86, @"Microsoft\Edge\Application\msedge.exe"),
                Path.Combine(pf, @"Microsoft\Edge\Application\msedge.exe"),
                Path.Combine(localApp, @"Microsoft\Edge\Application\msedge.exe")
            };

            foreach (string path in candidates)
            {
                if (!string.IsNullOrEmpty(path) && File.Exists(path))
                {
                    return path;
                }
            }
            return null;
        }

        private static void LaunchBrowserConsole(string baseDir, string targetUrl)
        {
            try
            {
                string browserExe = FindBrowserExecutable();
                if (!string.IsNullOrEmpty(browserExe))
                {
                    var psi = new ProcessStartInfo
                    {
                        FileName = browserExe,
                        Arguments = "\"" + targetUrl + "\"",
                        WorkingDirectory = baseDir,
                        UseShellExecute = false
                    };
                    Process.Start(psi);
                    return;
                }

                var fallbackPsi = new ProcessStartInfo
                {
                    FileName = targetUrl,
                    WorkingDirectory = baseDir,
                    UseShellExecute = true
                };
                Process.Start(fallbackPsi);
            }
            catch (Exception ex)
            {
                LogFault("LaunchBrowserConsole", ex);
            }
        }

        private static void LogFault(string scope, Exception ex)
        {
            try
            {
                string faultLog = Path.Combine(Path.GetTempPath(), "bfm_launcher_fault.log");
                string line = string.Format("[{0:yyyy-MM-dd HH:mm:ss}] [MIL-SPEC HANDLED FAULT] {1}: {2}{3}",
                    DateTime.Now, scope, ex, Environment.NewLine);
                File.AppendAllText(faultLog, line);
            }
            catch (Exception logEx)
            {
                Trace.WriteLine("[MIL-SPEC HANDLED FAULT] LogFault failed: " + logEx.Message);
            }
        }

        [STAThread]
        private static int Main()
        {
            try
            {
                string baseDir = GetBaseDir();
                EnsureServerRunning(baseDir);
                string launchUrl = ResolveLaunchUrl(baseDir);
                LaunchBrowserConsole(baseDir, launchUrl);
                return 0;
            }
            catch (Exception ex)
            {
                LogFault("Main", ex);
                return 1;
            }
        }
    }
}
