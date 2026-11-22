with open(r"D:\veepoo\index.html", "r", encoding="utf-8") as f:
    text = f.read()

idx = text.find('function applyWatchFace(')
print("=== applyWatchFace ===")
print(text[idx:idx+2500])
