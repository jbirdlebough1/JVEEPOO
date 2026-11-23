with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('def analyze_meal_with_gemini_backend')
end_pos = text.find('def insert_meal_entry')
func_code = text[pos:end_pos]

import re
matches = re.findall(r'#\s*\d+.*', func_code)
for m in matches:
    print(m)
