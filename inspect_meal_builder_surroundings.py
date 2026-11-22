with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('id="txtMealBuilderInput"')
print(text[pos-200:pos+1500])
