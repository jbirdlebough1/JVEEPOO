with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_met = html.find('id="pageMetabolic"')
pos_comp3 = html.find('COMPONENT 3', pos_met)
pos_list = html.find('dietHistoryList', pos_comp3)
print("pos_list:", pos_list)
if pos_list != -1:
    print(html[pos_list-200:pos_list+1500])
