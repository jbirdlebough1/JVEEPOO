with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function executeScanStage6_HRV')
print(text[idx+500:idx+2500])
