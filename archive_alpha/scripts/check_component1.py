with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('COMPONENT 01: INTERACTIVE DIABETIC MEAL BUILDER')
print(text[m+1000:m+4000])
