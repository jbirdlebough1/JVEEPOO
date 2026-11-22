with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('function assembleAndParseSleepStream')
print(f"assembleAndParseSleepStream at {m}:")
if m != -1:
    print(text[m:m+2500])
