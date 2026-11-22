import json

with open(r'D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides:
    if s.get("id") in [24, 28, 54, 55, 56] or s.get("num") in ["24", "28", "54", "55", "56"]:
        print(f"=== SLIDE {s.get('id')}: {s.get('title')} ===")
        print("Module:", s.get("module"))
        print("Tab num:", s.get("tab_num"))
        print("Tab img:", s.get("tab_img"))
        print("Narration:", s.get("narration"))
        print("Bullets:", s.get("bullets"))
        print("Takeaway:", s.get("takeaway"))
        print()
