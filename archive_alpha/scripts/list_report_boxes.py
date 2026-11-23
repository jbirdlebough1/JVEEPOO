with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
pos_start = text.find('id="pageReports"')
pos_end = text.find('id="pageExport"', pos_start)
if pos_end == -1:
    pos_end = text.find('class="tab-page"', pos_start + 50)

reports_html = text[pos_start:pos_end]

# Find all headings, chart-box, titles
matches = re.findall(r'(<div[^>]*class="[^"]*(?:chart-box|card-box)[^"]*"[^>]*>.*?)(?=<div[^>]*class="[^"]*(?:chart-box|card-box)|$)', reports_html, re.DOTALL)
print(f'Total boxes in Reports: {len(matches)}')
for i, b in enumerate(matches):
    m_id = re.search(r'id="([^"]+)"', b)
    box_id = m_id.group(1) if m_id else "NO_ID"
    # search for title
    titles = re.findall(r'<span[^>]*style="[^"]*(?:font-size:\s*1|font-weight:\s*900|font-weight:\s*800)[^"]*"[^>]*>(.*?)</span>', b, re.DOTALL)
    if not titles:
        titles = re.findall(r'<strong[^>]*>(.*?)</strong>', b, re.DOTALL)
    title_str = titles[0].strip() if titles else "NO_TITLE"
    title_clean = re.sub(r'<[^>]+>', '', title_str).replace('\n', ' ').strip()
    print(f'Box {i+1}: ID={box_id} | Title={title_clean[:80]}')
