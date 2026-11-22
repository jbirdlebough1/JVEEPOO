import re
with open(r'D:\veepoo\w11_htm_app\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

for m in re.finditer(r'trackGroove', text):
    start = max(0, m.start() - 50)
    end = min(len(text), m.end() + 200)
    print(text[start:end])
    print("="*40)
