with open(r"D:\veepoo\w11_htm_app\server.py", "r", encoding="utf-8") as f:
    text = f.read()

# Add import urllib.parse if missing
if "import urllib.parse" not in text:
    text = "import urllib.parse\n" + text
    print("[✔] Added import urllib.parse")

with open(r"D:\veepoo\w11_htm_app\server.py", "w", encoding="utf-8") as f:
    f.write(text)
