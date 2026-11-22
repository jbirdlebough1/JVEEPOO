import json, re, os, subprocess

# 1. Verify build_presentation output
with open(r"D:/veepoo/presentation/slides/slide_01.html", "r", encoding="utf-8") as f:
    s1_html = f.read()

tabs_in_s1 = re.findall(r'<button[^>]+class=["\'][^"\']*lcars-tab-btn[^"\']*["\'][^>]*>(.*?)</button>', s1_html, re.S)
print(f"Tabs rendered in slide 1 sidebar: {len(tabs_in_s1)}")
for idx, t in enumerate(tabs_in_s1):
    clean = re.sub(r'<[^>]+>', '', t).strip()
    print(f"  Tab {idx+1}: {clean}")

# 2. Verify all_slides_data.json has 56 slides with valid tab_num
with open(r"D:/veepoo/all_slides_data.json", "r", encoding="utf-8") as f:
    slides = json.load(f)

print(f"\nTotal slides in json: {len(slides)}")
for s in [slides[13], slides[15], slides[18], slides[22], slides[27], slides[28], slides[42], slides[45], slides[53], slides[54], slides[55]]:
    print(f"  Slide {s['id']}: Tab {s.get('tab_num')} | {s['title']}")

# 3. Verify Preview_Presentation.exe exists and is updated
exe_stat = os.stat(r"D:/veepoo/Preview_Presentation.exe")
print(f"\nPreview_Presentation.exe: size={exe_stat.st_size} bytes, modified={exe_stat.st_mtime}")
