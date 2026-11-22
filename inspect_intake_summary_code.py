with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('renderDailyIntakeSummary')
if idx == -1:
    idx = text.find('cardDailyIntakeSummary')
print(text[idx-50:idx+2500])
