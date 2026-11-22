with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="watchfaces"')
if idx == -1:
    idx = text.find('tab-watchfaces')
if idx == -1:
    idx = text.find('boxHybridChronoStudio')

print("Tab 15 section location:", idx)
if idx != -1:
    print(text[idx-200:idx+2500])
