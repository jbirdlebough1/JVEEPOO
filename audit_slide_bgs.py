import os, glob, re

slides = sorted(glob.glob('D:/veepoo/slides/slide_*.html'))
print(f"Total slides found: {len(slides)}")

dark_bg_pattern = re.compile(r'background(?:-color)?\s*:\s*(#[0-9a-fA-F]{3,6}|rgba?\([^)]+\))', re.IGNORECASE)

issues = []
for s in slides:
    with open(s, 'r', encoding='utf-8') as f:
        content = f.read()
    bgs = dark_bg_pattern.findall(content)
    # Check for dark colors like #0..., #1..., #2..., rgb(0..40, ...)
    for bg in bgs:
        bg_clean = bg.strip().lower()
        if bg_clean.startswith('#') and len(bg_clean) in [4, 7]:
            hex_val = bg_clean[1:]
            if len(hex_val) == 3:
                r, g, b = int(hex_val[0]*2, 16), int(hex_val[1]*2, 16), int(hex_val[2]*2, 16)
            else:
                r, g, b = int(hex_val[:2], 16), int(hex_val[2:4], 16), int(hex_val[4:], 16)
            if r < 100 and g < 100 and b < 100:
                issues.append((os.path.basename(s), bg))

print(f"Total dark background occurrences in slides: {len(issues)}")
for s, bg in issues[:30]:
    print(f"  {s}: {bg}")
