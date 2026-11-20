import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides[13:18]:
    print(f"=== SLIDE {s['num']}: {s['title']} ===")
    print("RIGHT_HEADER:", s.get('right_header'))
    print("RIGHT_HTML preview:\n", s.get('right_html')[:400])
    print()
