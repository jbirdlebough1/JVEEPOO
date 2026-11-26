using System;
using System.Diagnostics;
using System.IO;
using System.Net.Sockets;
using System.Threading;

namespace BfmLauncher
{
    internal static class Program
    {
        private const string BaseDir = @"D:\veepoo";
        private const string PythonExe = @"D:\veepoo\tools\python\python.exe";
        private const string ServerScript = @"w11_htm_app\server.py";
        private const string ConsoleHtml = @"D:\veepoo\BFM.HTM";
        private const string ConsoleFileUrl = "file:///D:/veepoo/BFM.HTM";
        private const string ChromeExe = @"C:\Program Files\Google\Chrome\Application\chrome.exe";
        private const string FaultLog = @"D:\veepoo\w11_htm_app\launcher_fault.log";

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

        private static void EnsureServerRunning()
        {
            if (IsServerListening("127.0.0.1", 8899, 150))
            {
                return;
            }
            try
            {
                var psi = new ProcessStartInfo
                {
                    FileName = PythonExe,
                    Arguments = ServerScript,
                    WorkingDirectory = BaseDir,
                    UseShellExecute = false,
                    CreateNoWindow = true,
                    WindowStyle = ProcessWindowStyle.Hidden
                };
                Process.Start(psi);
                for (int i = 0; i < 15; i++)
                {
                    if (IsServerListening("127.0.0.1", 8899, 120)) break;
                    Thread.Sleep(150);
                }
            }
            catch (Exception ex)
            {
                LogFault("EnsureServerRunning", ex);
            }
        }

        private static void LaunchBrowserConsole()
        {
            try
            {
                if (File.Exists(ChromeExe))
                {
                    var chromePsi = new ProcessStartInfo
                    {
                        FileName = ChromeExe,
                        Arguments = "\"" + ConsoleFileUrl + "\"",
                        WorkingDirectory = BaseDir,
                        UseShellExecute = false
                    };
                    Process.Start(chromePsi);
                    return;
                }

                var fallbackPsi = new ProcessStartInfo
                {
                    FileName = ConsoleHtml,
                    WorkingDirectory = BaseDir,
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
                string line = string.Format("[{0:yyyy-MM-dd HH:mm:ss}] [MIL-SPEC HANDLED FAULT] {1}: {2}{3}",
                    DateTime.Now, scope, ex, Environment.NewLine);
                File.AppendAllText(FaultLog, line);
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
                EnsureServerRunning();
                LaunchBrowserConsole();
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
