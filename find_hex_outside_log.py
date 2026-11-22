with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

# Find pageLog boundaries
pos_log_start = html.find('id="pageLog"')
pos_log_end = html.find('<!--', pos_log_start + 100)

print(f"pageLog is from {pos_log_start} to {pos_log_end}")

# In HTML tags (outside script tags and outside pageLog):
# Find any visible text containing 0x[0-9a-fA-F]
# Let's inspect script tags vs HTML text
in_script = False
lines = html.split('\n')
for idx, line in enumerate(lines):
    line_stripped = line.strip()
    if '<script' in line:
        in_script = True
    if '</script>' in line:
        in_script = False
        continue
    if not in_script:
        # Check if line contains 0x..
        m = re.search(r'0x[0-9a-fA-F]{2}', line)
        if m:
            # Check if this line is within pageLog
            pos = html.find(line)
            is_in_log = (pos_log_start <= pos <= pos_log_end)
            if not is_in_log:
                print(f"Line {idx+1}: {line_stripped}")
