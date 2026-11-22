with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('def analyze_meal_with_gemini_backend(')
print(f"analyze_meal_with_gemini_backend at {idx}")
if idx != -1:
    print(text[idx:idx+4000])
