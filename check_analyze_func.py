with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('function analyzeMealWithGemini')
end = text.find('function renderLongitudinalView', m)
if end == -1:
    end = text.find('function ', m + 20)
print(f"Function length: {end - m}")
print(text[m:end])
