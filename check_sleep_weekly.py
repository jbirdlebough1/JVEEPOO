with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('else if (isWeekly)')
if pos != -1:
    print(html[pos:pos+2500])
