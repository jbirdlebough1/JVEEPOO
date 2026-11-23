with open('D:/veepoo/w11_htm_app/cell.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('setPreset(')
print(text[idx:idx+1500])
