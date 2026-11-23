import re
with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Search for any string formatting time or adding tick marks with small steps
matches = [m.start() for m in re.finditer(r'<text[^>]+>\$\{.*time.*\}</text>|<text[^>]+>\$\{.*label.*\}</text>', text)]
print("Found text label interpolations:", len(matches))
for m in matches:
    print(text[m-40:m+120].replace('\n', ' '))
