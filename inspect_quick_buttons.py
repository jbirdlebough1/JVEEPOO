with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('Collection of Quick Sub-Report Jump Buttons')
if idx != -1:
    print(text[idx-200:idx+600])
