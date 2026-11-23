import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

print("File length:", len(text))
matches = [m.start() for m in re.finditer(r'id=["\']pageFullScan["\']', text)]
print("pageFullScan occurrences:", len(matches))
for m in matches:
    start = max(0, text.rfind('\n', 0, m - 200))
    end = min(len(text), text.find('\n', m + 1000))
    print("--- MATCH ---")
    print(text[start:end])

# Check how switchTab works
m_tab = re.search(r'function switchTab\([^)]*\)\s*\{', text)
if m_tab:
    start = m_tab.start()
    print("--- switchTab ---")
    print(text[start:start+1500])

# Check DOMContentLoaded or initial tab activation
m_init = re.search(r'DOMContentLoaded', text)
if m_init:
    start = m_init.start()
    print("--- DOMContentLoaded ---")
    print(text[start:start+1500])
