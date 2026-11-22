with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('id="geminiMealAnalysisResult"')
print(text[pos+1500:pos+3500])
