with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('if (p.steps > 0) exerciseData.push({ label: p.time, val: p.steps });')
if pos != -1:
    print(html[pos:pos+2000])
