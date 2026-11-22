with open(r'D:\veepoo\presentation\preview.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('// If steady slide, lock scroll compl')
print(text[idx:idx+2000])
