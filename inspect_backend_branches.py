with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('def analyze_meal_with_gemini_backend')
end_pos = text.find('def insert_meal_entry')
func_code = text[pos:end_pos]

import re
matches = re.finditer(r'^\s*(?:elif|if)\s+.*?:', func_code, re.MULTILINE)
for m in matches:
    print(m.group(0).strip())
