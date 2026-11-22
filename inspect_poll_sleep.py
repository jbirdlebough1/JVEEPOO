with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.rfind('totH.toFixed(1)')
print(text[idx-500:idx+600])
