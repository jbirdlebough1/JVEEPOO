with open(r"D:/veepoo/build_presentation.py", "r", encoding="utf-8") as f:
    text = f.read()

import re
fns = re.findall(r'def\s+([A-Za-z0-9_]+)', text)
print("Functions in build_presentation.py:", fns)
