with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('cached2DayTimeline = data')
print(text[idx-200:idx+400])
