import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

print(f"Total slides currently: {len(slides)}")
for s in slides[:10]:
    print(f"{s['num']}: [{s['module']}] {s['title']}")
