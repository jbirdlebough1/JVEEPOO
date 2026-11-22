with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function analyzeMealWithGemini(')
if idx != -1:
    print(text[idx:idx+3500])
else:
    idx2 = text.find('analyzeMealWithGemini')
    print('found at:', idx2)
    print(text[idx2:idx2+1000])
