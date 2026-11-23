import json
import re

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

print(f"Total slides: {len(slides)}")

found_masterclass = []
found_workbench = []

for idx, s in enumerate(slides):
    num = s.get('num', str(idx+1))
    title = s.get('title', '')
    narration = s.get('narration', '')
    desc = s.get('desc', '')
    bullets = str(s.get('bullets', []))
    right_header = s.get('right_header', '')
    right_html = s.get('right_html', '')
    
    full_text = f"{title} | {narration} | {desc} | {bullets} | {right_header} | {right_html}"
    
    if re.search(r'masterclass', full_text, re.IGNORECASE):
        found_masterclass.append((num, title, [m.group(0) for m in re.finditer(r'.{0,30}masterclass.{0,30}', full_text, re.IGNORECASE)]))
        
    if re.search(r'workbench', full_text, re.IGNORECASE):
        found_workbench.append((num, title, [m.group(0) for m in re.finditer(r'.{0,30}workbench.{0,30}', full_text, re.IGNORECASE)]))

print("=== FOUND MASTERCLASS ===")
for num, title, matches in found_masterclass:
    print(f"Slide {num} ({title}): {matches}")

print("\n=== FOUND WORKBENCH ===")
for num, title, matches in found_workbench:
    print(f"Slide {num} ({title}): {matches}")
