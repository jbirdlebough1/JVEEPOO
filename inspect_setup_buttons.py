with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('id="pageSetup"')
end_pos = html.find('id="page', pos+10)
setup_html = html[pos:end_pos] if end_pos != -1 else html[pos:pos+10000]

import re
buttons = re.findall(r'<button[^>]*>.*?</button>', setup_html, re.DOTALL)
for b in buttons:
    print('Button in setup:', re.sub(r'\s+', ' ', b))
