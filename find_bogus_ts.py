import re

for fn in ['index.html', 'w11_htm_app/server.py']:
    with open(fn, 'r', encoding='utf-8') as f:
        text = f.read()
    matches = [m.start() for m in re.finditer(r'00:00:01|00:01:00|00:00:15', text)]
    print(f"File {fn}: {len(matches)} matches")
    for m in matches:
        print(text[max(0, m-50):min(len(text), m+150)].replace('\n', ' '))
