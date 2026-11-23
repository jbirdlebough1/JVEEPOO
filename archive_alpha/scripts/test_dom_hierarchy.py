# test_render_tabs.py
import subprocess
import time
import os

chrome_path = r"C:\Program Files\Google\Chrome\Application\chrome.exe"
if not os.path.exists(chrome_path):
    chrome_path = r"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe"

print("Chrome path:", chrome_path)

# Let's run headless Chrome to render the page with different tabs active and dump screenshots or DOM
js_script = """
(() => {
    switchTab('medication');
    return {
        activePageId: document.querySelector('.tab-page.active-page')?.id,
        allActivePages: Array.from(document.querySelectorAll('.tab-page.active-page')).map(p => p.id),
        medicationDisplay: window.getComputedStyle(document.getElementById('pageMedication')).display,
        operationsDisplay: window.getComputedStyle(document.getElementById('pageOperations')).display,
        sec05Visible: (() => {
            const h = Array.from(document.querySelectorAll('h3, h2, div')).find(e => e.textContent.includes('SECTION 05: SPECIALIZED'));
            if (!h) return false;
            let p = h;
            while (p && p !== document.body) {
                if (window.getComputedStyle(p).display === 'none') return false;
                p = p.parentElement;
            }
            return true;
        })()
    };
})()
"""

# Test via Node or python CDP or fetch
# Let's write a script that opens CDP session or tests DOM structure
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

from bs4 import BeautifulSoup
soup = BeautifulSoup(html, 'html.parser')

sec05 = None
for el in soup.find_all(text=lambda t: t and 'SECTION 05: SPECIALIZED CARDIOMETABOLIC' in t):
    sec05 = el
    break

if sec05:
    parent = sec05.parent
    path = []
    while parent and parent.name != '[document]':
        p_id = parent.get('id', '')
        p_cls = parent.get('class', [])
        path.append(f"{parent.name}#{p_id}.{'.'.join(p_cls)}")
        parent = parent.parent
    print("SECTION 05 hierarchy up to document:")
    for step in path:
        print(" ->", step)
else:
    print("SECTION 05 not found!")

# Also let's check pageMedication and pageGoals
med = soup.find('div', id='pageMedication')
print("\n#pageMedication found?", med is not None)
if med:
    print("  classes:", med.get('class'))
    print("  children count:", len(med.find_all(recursive=False)))
    print("  inner headers:", [h.text.strip() for h in med.find_all(['h1', 'h2', 'h3', 'h4'])[:5]])

goals = soup.find('div', id='pageGoals')
print("\n#pageGoals found?", goals is not None)
if goals:
    print("  classes:", goals.get('class'))
    print("  children count:", len(goals.find_all(recursive=False)))
    print("  inner headers:", [h.text.strip() for h in goals.find_all(['h1', 'h2', 'h3', 'h4'])[:5]])
