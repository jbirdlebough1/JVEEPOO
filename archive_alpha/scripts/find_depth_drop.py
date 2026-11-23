with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

import re

depth = 0
for i in range(1698, 7700):
    line = lines[i]
    open_divs = len(re.findall(r'<div[\s>]', line))
    close_divs = len(re.findall(r'</div>', line))
    old_depth = depth
    depth += (open_divs - close_divs)
    if (old_depth == 1 and depth == 0) or (old_depth == 0 and depth == -1):
        print(f"Depth dropped to {depth} at L{i+1}: {line}")
    elif '<div id="page' in line:
        print(f"Line {i+1}: {line.strip()[:60]} -> current depth = {depth}")
    elif '</main>' in line:
        print(f"L{i+1}: </main> tag! depth = {depth}")
