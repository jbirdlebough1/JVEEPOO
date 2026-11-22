with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'id="pageCardio"' in l or 'id="pageMetabolic"' in l or 'id="pageDiet"' in l:
        print(f'{i+1}: {l.strip()[:80]}')
