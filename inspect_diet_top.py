with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageDietTrades"')
lines = text[idx:idx+15000].split('\n')
for i, l in enumerate(lines[:120]):
    print(f"{i+1:3}: {l}")
