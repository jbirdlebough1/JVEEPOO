with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('navTabFullScan')
print(html[pos-50:pos+800])
