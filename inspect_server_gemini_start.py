with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('def analyze_meal_with_gemini_backend')
print(text[pos:pos+1500])
