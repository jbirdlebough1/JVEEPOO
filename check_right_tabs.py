import json, re

with open(r"D:/veepoo/all_slides_data.json", "r", encoding="utf-8") as f:
    slides = json.load(f)

for s in slides:
    r_html = s.get("right_html", "")
    header = s.get("right_header", "")
    m = re.findall(r'(tab\s*\d+|0[1-9]-|1[0-5]-)', r_html + " " + header, re.I)
    if m:
        print(f"Slide {s['id']:02d}: matches in right_html/header -> {m}")
