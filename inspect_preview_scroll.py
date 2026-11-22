with open(r'D:\veepoo\presentation\preview.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('left-screenshot-viewport')
print("idx:", idx)
if idx != -1:
    print(text[idx-200:idx+1500])
