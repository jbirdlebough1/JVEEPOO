with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('POPULAR MEAL REQUEST PRESETS')
if m == -1:
    m = text.find('selectMealBuilderPreset')
print("Popular meals presets at", m)
print(text[m-200:m+1500])
