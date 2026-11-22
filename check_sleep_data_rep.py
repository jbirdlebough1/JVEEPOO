with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('// 8. Sleep Duration & Hypnogram Curve')
if pos != -1:
    print(html[pos-1500:pos])
