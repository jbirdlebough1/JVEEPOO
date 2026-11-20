import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides[19:30]:
    print(f"Slide {s['num']}: {s['title']}")
    print(f"   Screenshot: {s.get('tab_screenshot')}")
    print(f"   Tab Title:  {s.get('tab_title')}")
