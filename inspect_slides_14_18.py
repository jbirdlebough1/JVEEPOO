import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides[13:18]:
    print(f"==================================================")
    print(f"SLIDE {s['num']}: {s['title']}")
    print(f"Module: {s['module']}")
    print(f"Desc: {s['desc']}")
    print(f"Narration: {s['narration']}")
    print(f"Bullets: {s['bullets']}")
    print(f"Takeaway: {s['takeaway']}")
