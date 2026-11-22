with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('body.theme-light .log-tx')
print(text[idx-50:idx+350])
