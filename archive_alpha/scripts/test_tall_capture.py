import subprocess
import os

chrome = r"C:\Program Files\Google\Chrome\Application\chrome.exe"
out_img = r"D:\veepoo\images\lcars_tab_reports_tall.png"

# Tab 04 URL
url = "http://localhost:8899/index.html?theme=light#pageReports"

cmd = [
    chrome,
    "--headless=new",
    "--disable-gpu",
    "--window-size=1280,2000",
    f"--screenshot={out_img}",
    url
]

subprocess.run(cmd, check=True)
if os.path.exists(out_img):
    print("Successfully captured tall screenshot:", os.path.getsize(out_img), "bytes")
else:
    print("Failed to capture.")
