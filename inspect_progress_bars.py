with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function updateOverallProgressBar')
print(text[idx:idx+2000])
