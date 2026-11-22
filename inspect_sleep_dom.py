with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('function assembleAndParseSleepStream')
end = text.find('function querySleepData', m)
print(text[m:end])
