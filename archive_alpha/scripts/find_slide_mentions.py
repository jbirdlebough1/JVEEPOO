import json
import re

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

print(f"Total slides: {len(slides)}")

found = []
for s in slides:
    num = s['num']
    narration = s.get('narration', '')
    matches = re.findall(r'.{0,30}\bslide\b.{0,30}', narration, re.IGNORECASE)
    if matches:
        found.append((num, matches))

print(f"Slides mentioning 'slide': {len(found)}")
for num, m in found:
    print(f"Slide {num}: {m}")
