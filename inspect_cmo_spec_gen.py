with open('docs/generate_cmo_spec.py', 'r', encoding='utf-8') as f:
    text = f.read()

print('Length of generate_cmo_spec.py:', len(text))
for line in text.splitlines()[:40]:
    print(line)
