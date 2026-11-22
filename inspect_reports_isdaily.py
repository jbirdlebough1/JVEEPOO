with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('if (isDaily) {')
idx2 = text.find('if (isDaily) {', idx + 10)
print("=== isDaily data block ===")
print(text[idx2:idx2+2500])
