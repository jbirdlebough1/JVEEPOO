with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('id="btnSearchLocalBOM"')
print('btnSearchLocalBOM pos:', pos)
if pos != -1:
    print(html[pos-100:pos+200])
else:
    # Look for button near Store Selector
    pos2 = html.find('selLocalStoreChain')
    print('selLocalStoreChain pos:', pos2)
    print(html[pos2:pos2+800])
