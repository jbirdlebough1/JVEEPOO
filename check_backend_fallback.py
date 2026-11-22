with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('def analyze_meal_with_gemini_backend')
end = text.find('def ', m + 10)
print(text[end-1200:end])
