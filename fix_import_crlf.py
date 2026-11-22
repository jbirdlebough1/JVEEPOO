with open(r"D:\veepoo\w11_htm_app\server.py", "r", encoding="utf-8") as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if l.startswith("import datetime"):
        lines.insert(i + 1, "import urllib.parse\n")
        break

with open(r"D:\veepoo\w11_htm_app\server.py", "w", encoding="utf-8") as f:
    f.writelines(lines)
print("[✔] Inserted import urllib.parse properly!")
