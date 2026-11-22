with open(r"D:\veepoo\index.html", "r", encoding="utf-8") as f:
    text = f.read()

idx = text.find('btnStepSwitchSlot2')
print("=== HTML around stock slot buttons ===")
print(text[idx-200:idx+1500])

idx_js = text.find('function applySlotWatchFace')
if idx_js == -1:
    idx_js = text.find('function switchSlot')
if idx_js == -1:
    idx_js = text.find('0xC7, 0x01')
print("\n=== JS for slot switching ===")
print(text[idx_js-100:idx_js+1200])
