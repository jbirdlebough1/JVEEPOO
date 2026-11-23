with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_met = html.find('id="pageMetabolic"')
pos_comp3 = html.find('COMPONENT 3', pos_met)
print("pos_comp3:", pos_comp3)
if pos_comp3 != -1:
    print(html[pos_comp3-50:pos_comp3+3000])
