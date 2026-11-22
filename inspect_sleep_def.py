with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function assembleAndParseSleepStream')
next_fn = text.find('\n    function ', idx + 30)
print(text[idx:next_fn])
