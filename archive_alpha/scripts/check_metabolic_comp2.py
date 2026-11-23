with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_met = html.find('id="pageMetabolic"')
pos_comp2 = html.find('COMPONENT 2', pos_met)
print("pos_comp2:", pos_comp2)
if pos_comp2 != -1:
    print(html[pos_comp2-50:pos_comp2+3000])
