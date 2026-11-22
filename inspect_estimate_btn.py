with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('ESTIMATE WITH GEMINI')
print(text[idx-200:idx+400])
