with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re

for selector in [r'\.chart-box', r'\.svg-timeline-container', r'#tallChartSvgContainer', r'body\.theme-light \.chart-box', r'body\.theme-light']:
    matches = re.findall(rf'([^{{}}]*{selector}[^{{}}]*\{{[^}}]+\}})', text)
    print(f"=== Matches for {selector} ===")
    for m in matches:
        print(m.strip()[:200])
        print("---")
