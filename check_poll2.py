with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('4. Update Autonomic HRV & Stress')
if pos != -1:
    print(html[pos:pos+3000])
