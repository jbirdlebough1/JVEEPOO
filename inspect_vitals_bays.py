with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m_vitals = text.find('id="pageVitals"')
m_next = text.find('id="pageCardio"')
vitals_chunk = text[m_vitals:m_next]

import re
matches = re.findall(r'<span class="lcars-bay-title"[^>]*>(.*?)</span>', vitals_chunk)
print("Bays in pageVitals:")
for idx, b in enumerate(matches):
    print(f"{idx+1}: {b}")
