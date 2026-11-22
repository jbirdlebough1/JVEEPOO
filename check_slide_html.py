with open(r"D:/veepoo/presentation/slides/slide_01.html", "r", encoding="utf-8") as f:
    text = f.read()

import re
print("Length of slide_01.html:", len(text))
matches = re.findall(r'<div[^>]+class=["\'][^"\']*lcars-tab-btn[^"\']*["\'][^>]*>(.*?)</div>', text, re.S)
print("div lcars-tab-btn found:", len(matches))
for i, m in enumerate(matches):
    clean = re.sub(r'<[^>]+>', '', m).strip()
    print(f"  {i+1}: {clean}")
