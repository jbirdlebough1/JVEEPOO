with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('async function analyzeMealWithGemini')
end = text.find('function setLongitudinalTimeframe', m)
print(text[end-1200:end])
