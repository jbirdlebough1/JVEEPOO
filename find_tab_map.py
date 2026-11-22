with open(r"D:/veepoo/build_presentation.py", "r", encoding="utf-8") as f:
    text = f.read()

import re
matches = [m.start() for m in re.finditer(r'TAB_MAP', text)]
print("TAB_MAP occurrences in build_presentation.py:", matches)
for m in matches:
    print(text[m:m+200])
