with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

# Check where do_GET is
m_get = text.find('def do_GET(self):')
print("do_GET at", m_get)
print(text[m_get:m_get+400])

# Check where analyze_meal_with_gemini_backend is
m_backend = text.find('def analyze_meal_with_gemini_backend(')
print("analyze_meal_with_gemini_backend at", m_backend)
