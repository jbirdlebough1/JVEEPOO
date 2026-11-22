with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="tab15"')
if idx == -1:
    idx = text.find('WATCH FACES')
    idx = text.find('<section', idx - 200) if idx != -1 else -1

print("Tab 15 HTML:", text[idx:idx+2500] if idx != -1 else "not found")

idx_flash = text.find('function applyHybridChronoToWatch')
print("\n=== applyHybridChronoToWatch ===")
print(text[idx_flash:idx_flash+2000] if idx_flash != -1 else "not found")
