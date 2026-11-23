with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

for i in range(1, 7):
    fn_name = f'executeScanStage{i}'
    pos = html.find(fn_name)
    if pos != -1:
        print(f"=== {fn_name} ===")
        print(html[pos:pos+500])
