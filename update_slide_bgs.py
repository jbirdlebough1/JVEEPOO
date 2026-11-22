import json
import re

def update_slides():
    data_file = r"D:\veepoo\all_slides_data.json"
    with open(data_file, "r", encoding="utf-8") as f:
        slides = json.load(f)

    for s in slides:
        # Check Slide 6
        if s["id"] == 6:
            diag = s.get("diagram_html", "")
            if "#050b14" in diag:
                diag = diag.replace("background: #050b14;", "background: #ffffff; border: 1.5px solid #cbd5e1;")
                s["diagram_html"] = diag
                print("[✔] Updated Slide 6 diagram_html background to #ffffff")

        # Check Slide 52
        if s["id"] == 52:
            diag = s.get("diagram_html", "")
            if "#1e1b2e" in diag:
                diag = diag.replace("background: #1e1b2e;", "background: #fef2f2; border: 1.5px solid #ef4444;")
                diag = diag.replace("background: #052e16;", "background: #f0fdf4; border: 1.5px solid #22c55e;")
                diag = diag.replace("color: #475569;", "color: #1e293b;")
                s["diagram_html"] = diag
                print("[✔] Updated Slide 52 diagram_html to clean light theme (#ffffff / soft tints)")

        # Ensure all other diagram_html have white backgrounds
        if "diagram_html" in s:
            s["diagram_html"] = s["diagram_html"].replace("background: #fafafa;", "background: #ffffff;")
            s["diagram_html"] = s["diagram_html"].replace("background: #f8fafc;", "background: #ffffff;")

    with open(data_file, "w", encoding="utf-8") as f:
        json.dump(slides, f, indent=2, ensure_ascii=False)
    print("[✔] Successfully updated all_slides_data.json")

if __name__ == "__main__":
    update_slides()
