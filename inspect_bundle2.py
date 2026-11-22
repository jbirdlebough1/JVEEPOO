with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('currentSafeOptionsBundle')
print("idx:", idx)
if idx != -1:
    print(text[idx-500:idx+2500])
