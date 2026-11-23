# test_diff_div_counts.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

bad_chunk = text[text.find('<!-- Products Dynamic Grid -->'):text.find('<!-- =====================================================================\n           TAB 13: WATCH')]

for div_count in range(2, 7):
    replacement = "<!-- Products Dynamic Grid -->\n<div id=\"productsGrid\"></div>\n" + ("</div>\n" * div_count)
    fixed_text = text.replace(bad_chunk, replacement)
    from bs4 import BeautifulSoup
    soup = BeautifulSoup(fixed_text, 'html.parser')
    p_watch = soup.find('div', id='pageWatchFaces')
    parents = []
    curr = p_watch.parent
    while curr and curr.name != '[document]':
        cid = curr.get('id', '')
        ccls = '.'.join(curr.get('class', []))
        parents.append(f"{curr.name}#{cid}.{ccls}")
        curr = curr.parent
    print(f"div_count={div_count}: pageWatchFaces parent -> {' > '.join(parents)}")
