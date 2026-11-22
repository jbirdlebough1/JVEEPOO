with open(r"D:\veepoo\w11_htm_app\server.py", "r", encoding="utf-8") as f:
    text = f.read()

target = "import datetime\n"
if "import urllib.parse\n" not in text:
    text = text.replace(target, "import datetime\nimport urllib.parse\n")
    print("[✔] Added import urllib.parse after import datetime")

with open(r"D:\veepoo\w11_htm_app\server.py", "w", encoding="utf-8") as f:
    f.write(text)
