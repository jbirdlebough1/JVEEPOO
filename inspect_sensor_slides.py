import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides[12:22]:
    print(f"Slide {s['num']}: [{s['module']}] {s['title']}")
    print(f"   Tab Screenshot: {s.get('tab_screenshot')}")
    print(f"   Right Header: {s.get('right_header')}")
    print()
