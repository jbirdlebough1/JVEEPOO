with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('searchLocalStorePricing')
print('searchLocalStorePricing pos:', pos)
if pos != -1:
    print(html[pos-50:pos+500])
