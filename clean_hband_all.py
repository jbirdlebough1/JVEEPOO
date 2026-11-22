import json, re

def clean_slides_regex():
    path = r"D:\veepoo\all_slides_data.json"
    with open(path, "r", encoding="utf-8") as f:
        content = f.read()

    # Regex replacements
    content = re.sub(r'(?i)h-band vs lcars', 'OEM APP VS LCARS', content)
    content = re.sub(r'(?i)hband 2d poincar[ée]', '2D POINCARÉ', content)
    content = re.sub(r'(?i)h-?band\b', 'OEM App', content)
    content = re.sub(r'(?i)h-band', 'OEM App', content)

    with open(path, "w", encoding="utf-8") as f:
        f.write(content)

    print("Updated all_slides_data.json with regex")

def clean_app_comments():
    for p in [r"D:\veepoo\w11_htm_app\index.html", r"D:\veepoo\index.html"]:
        with open(p, "r", encoding="utf-8") as f:
            content = f.read()

        content = content.replace("<!-- HBand 5-Zone Cardiac Effort Distribution Bar -->", "<!-- 5-Zone Cardiac Effort Distribution Bar -->")
        content = content.replace("<!-- HBand 24-Hour 48-Slot Step & Cadence Timeline Chart -->", "<!-- 24-Hour 48-Slot Step & Cadence Timeline Chart -->")
        content = content.replace("<!-- SECTION 09: SYSTEM, ALERTS, PERIPHERALS & HBAND NOTIFICATIONS -->", "<!-- SECTION 09: SYSTEM, ALERTS, PERIPHERALS & HARDWARE NOTIFICATIONS -->")
        content = content.replace("// Veepoo HBand Protocol:", "// Veepoo Protocol:")
        content = content.replace("// 48 Half-Hour Slots for Daily Activity Bins (HBand Spec)", "// 48 Half-Hour Slots for Daily Activity Bins (Activity Spec)")

        with open(p, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"Cleaned comments in {p}")

if __name__ == "__main__":
    clean_slides_regex()
    clean_app_comments()
