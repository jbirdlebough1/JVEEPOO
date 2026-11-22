with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('} else if (isWeekly) {', 400000)
if pos == -1:
    pos = html.find('else if (isWeekly)')
    pos = html.find('else if (isWeekly)', pos + 20)
if pos != -1:
    print(html[pos:pos+2500])
