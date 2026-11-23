# check_tab_tags.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

import re

def trace_region(start_line, end_line):
    stack = []
    for ln in range(start_line, end_line + 1):
        line = lines[ln - 1]
        tokens = re.findall(r'(<(?:div|main)[^>]*>|</(?:div|main)>)', line, re.I)
        for t in tokens:
            if t.startswith('</'):
                tag = t[2:-1].lower()
                if stack:
                    otag, oln, otxt = stack.pop()
                    if otag != tag:
                        print(f"L{ln}: Mismatch closed </{tag}>, opened <{otag}> at L{oln}: {otxt}")
                else:
                    print(f"L{ln}: EXTRA CLOSE </{tag}>")
            else:
                tag = re.match(r'<([a-zA-Z0-9]+)', t).group(1).lower()
                stack.append((tag, ln, t[:35]))
    return stack

print("Tracing pageHelp (5455 - 5908):")
s = trace_region(5455, 5908)
print(f"Left open: {len(s)}")
for x in s: print(" ", x)

print("\nTracing pageDietTrades (5906 - 6804):")
s = trace_region(5906, 6804)
print(f"Left open: {len(s)}")
for x in s: print(" ", x)

print("\nTracing pageWatchFaces (6802 - 7818):")
s = trace_region(6802, 7818)
print(f"Left open: {len(s)}")
for x in s: print(" ", x)

print("\nTracing pageMedication (7816 - 7972):")
s = trace_region(7816, 7972)
print(f"Left open: {len(s)}")
for x in s: print(" ", x)

print("\nTracing pageGoals (7970 - 8205):")
s = trace_region(7970, 8205)
print(f"Left open: {len(s)}")
for x in s: print(" ", x)
