with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('def analyze_meal_with_gemini_backend')
print(f"analyze_meal_with_gemini_backend found at {m}")
if m != -1:
    print(text[m:m+1500])
