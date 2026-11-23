import json, re

with open(r'D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

print(f"Total slides: {len(slides)}")

outdated_references = []
for idx, s in enumerate(slides):
    slide_id = s.get("id", idx + 1)
    title = s.get("title", "")
    narration = s.get("narration", "")
    card_title = s.get("card_title", "")
    tab_num = s.get("tab_num")
    tab_img = s.get("tab_img")
    
    # Check for tab references in title or narration
    text = f"{title} | {card_title} | {narration}"
    matches = re.findall(r'tab\s+(\d+)', text, re.I)
    
    # Print slide summary
    print(f"Slide {slide_id:02d}: tab_num={tab_num} | tab_img={tab_img} | Title: {title[:60]}")
    if matches:
        print(f"    -> mentions tabs: {matches}")
