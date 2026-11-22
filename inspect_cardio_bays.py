with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m_cardio = text.find('id="pageCardio"')
m_next = text.find('id="pageMetabolic"')
cardio_chunk = text[m_cardio:m_next]

import re
matches = re.findall(r'<span class="lcars-bay-title"[^>]*>(.*?)</span>', cardio_chunk)
print("Bays in pageCardio:")
for idx, b in enumerate(matches):
    print(f"{idx+1}: {b}")
