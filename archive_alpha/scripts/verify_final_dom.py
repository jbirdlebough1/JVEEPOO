# verify_final_dom.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

from bs4 import BeautifulSoup
soup = BeautifulSoup(text, 'html.parser')

tab_pages = soup.find_all('div', class_='tab-page')
print(f"Found {len(tab_pages)} tab pages:")
all_correct = True
for p in tab_pages:
    pid = p.get('id', 'NO_ID')
    parents = []
    curr = p.parent
    while curr and curr.name != '[document]':
        cid = curr.get('id', '')
        ccls = '.'.join(curr.get('class', []))
        parents.append(f"{curr.name}#{cid}.{ccls}")
        curr = curr.parent
    parent_str = " | ".join(parents)
    print(f"  {pid:20} -> {parent_str}")
    if not (parents[0].startswith('main#') and parents[1].startswith('div#') and parents[2].startswith('body#')):
        all_correct = False

print(f"\nAll 14 tab pages strictly direct children of main.lcars-workspace? {all_correct}")
assert all_correct, "DOM hierarchy error!"
