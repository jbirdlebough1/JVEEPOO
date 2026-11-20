using System;
using System.Diagnostics;
using System.IO;

class Program
{
    [STAThread]
    static void Main()
    {
        string baseDir = AppDomain.CurrentDomain.BaseDirectory;
        string localFile = Path.Combine(baseDir, "preview.html");
        if (!File.Exists(localFile))
        {
            localFile = Path.Combine(baseDir, "presentation", "preview.html");
        }

        string target = File.Exists(localFile)
            ? localFile
            : "http://localhost:8899/presentation/preview.html";

        string targetUri = new Uri(Path.GetFullPath(target)).AbsoluteUri;

        // Paths to Edge and Chrome on Windows 11
        string[] browserPaths = new string[]
        {
            @"C:\Program Files (x86)\Microsoft\Edge\Application\msedge.exe",
            @"C:\Program Files\Microsoft\Edge\Application\msedge.exe",
            @"C:\Program Files\Google\Chrome\Application\chrome.exe",
            @"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"
        };

        foreach (string browserPath in browserPaths)
        {
            if (File.Exists(browserPath))
            {
                try
                {
                    // Launch with unmuted autoplay policy flag
                    ProcessStartInfo psi = new ProcessStartInfo
                    {
                        FileName = browserPath,
                        Arguments = "--autoplay-policy=no-user-gesture-required \"" + targetUri + "\"",
                        UseShellExecute = false
                    };
                    Process.Start(psi);
                    return;
                }
                catch {}
            }
        }

        // Fallback to default system shell handler
        try
        {
            Process.Start(new ProcessStartInfo(targetUri) { UseShellExecute = true });
        }
        catch
        {
            Process.Start("cmd.exe", "/c start \"\" \"" + targetUri + "\"");
        }
    }
}
