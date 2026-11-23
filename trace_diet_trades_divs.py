# trace_diet_trades_divs.py
import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

stack = []
for ln in range(5909, 6806):
    line = lines[ln - 1]
    tokens = re.finditer(r'(<div[^>]*>|</div>)', line, re.I)
    for m in tokens:
        tag = m.group(1)
        if tag.startswith('</'):
            if stack:
                otag, oln, otxt = stack.pop()
            else:
                print(f"[EXTRA CLOSE at L{ln}]: {line.strip()[:60]}")
        else:
            stack.append(('div', ln, tag[:50]))

print(f"\nAt line 6805, remaining open divs in pageDietTrades: {len(stack)}")
for t, l, txt in stack:
    print(f"  L{l:5}: {txt}")
