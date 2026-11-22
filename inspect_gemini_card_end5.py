with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('id="geminiMealAnalysisResult"')
print(text[pos+4500:pos+6200])
