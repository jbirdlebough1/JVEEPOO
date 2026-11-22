with open(r"D:\veepoo\index.html", "r", encoding="utf-8") as f:
    text = f.read()

for fn in ['testSwitchSlot2', 'testOverwriteSlot3WithSlot2', 'testDirectSlot3']:
    idx = text.find(f'function {fn}')
    if idx != -1:
        print(f"=== {fn} ===")
        print(text[idx:idx+800])
