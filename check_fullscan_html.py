with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_scan = html.find('id="pageFullScan"')
print('pos_scan:', pos_scan)

# Find the next 2000 characters
print(html[pos_scan - 100:pos_scan + 2000])
