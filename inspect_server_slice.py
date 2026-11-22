import re

with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('def analyze_meal_with_gemini_backend')
end_pos = text.find('def insert_meal_entry')

print(f"analyze_meal_with_gemini_backend is from {pos} to {end_pos} ({end_pos - pos} chars)")
