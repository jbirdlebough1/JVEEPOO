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

        // 1. Prefer local presentation file if available
        // 2. Otherwise open live hosted presentation on GitHub Pages
        string target = File.Exists(localFile)
            ? localFile
            : "https://jbirdlebough1.github.io/JVEEPOO/presentation/preview.html";

        try
        {
            Process.Start(new ProcessStartInfo(target) { UseShellExecute = true });
        }
        catch
        {
            Process.Start("cmd.exe", "/c start \"\" \"" + target + "\"");
        }
    }
}
