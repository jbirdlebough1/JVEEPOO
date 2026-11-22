import json

with open(r'D:\veepoo\all_slides_data.json', encoding='utf-8') as f:
    slides = json.load(f)

for i, s in enumerate(slides):
    t = (s.get('title', '') + ' ' + s.get('subtitle', '') + ' ' + json.dumps(s.get('bullets', ''))).lower()
    if 'goal' in t or 'tab 13' in t:
        print(f"Slide {i+1}: {s.get('title')}")
