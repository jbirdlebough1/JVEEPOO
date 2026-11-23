# test_full_doc_divs.py
import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Replace lines 6772-6799
bad_chunk = text[text.find('<!-- Products Dynamic Grid -->'):text.find('<!-- =====================================================================\n           TAB 13: WATCH')]
print("Original bad_chunk:\n", bad_chunk[-300:])

replacement = """<!-- Products Dynamic Grid -->
            <div id="productsGrid" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 12px;">
              <!-- Rendered via JavaScript -->
            </div>
          </div>
        </div>
      </div>

    """

fixed_text = text.replace(bad_chunk, replacement)
print("\nReplaced! Length diff:", len(fixed_text) - len(text))

from bs4 import BeautifulSoup
soup = BeautifulSoup(fixed_text, 'html.parser')

tab_pages = soup.find_all('div', class_='tab-page')
print(f"\nFound {len(tab_pages)} elements with class 'tab-page':")
for p in tab_pages:
    pid = p.get('id', 'NO_ID')
    parents = []
    curr = p.parent
    while curr and curr.name != '[document]':
        cid = curr.get('id', '')
        ccls = '.'.join(curr.get('class', []))
        parents.append(f"{curr.name}#{cid}.{ccls}")
        curr = curr.parent
    print(f"  {pid:20} -> {' > '.join(parents)}")
