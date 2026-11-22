import json

with open(r'D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

print(f"Total slides: {len(slides)}")
for i, s in enumerate(slides):
    title = s.get('title', '')
    narrative = s.get('narrative', '')
    bullet_points = s.get('bullet_points', [])
    print(f"=== SLIDE {i+1}: {title} ===")
    for bp in bullet_points:
        print(f"  * {bp[:120]}")
