with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('renderSafeOptionsBundle')
print(text[idx:idx+4000])
