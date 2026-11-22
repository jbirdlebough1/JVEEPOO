import json

with open('all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

tabs_found = set()
for s in slides:
    t = s.get("title", "")
    for part in t.split():
        if "Tab" in part:
            tabs_found.add(t)

for item in sorted(tabs_found):
    print(item)
