with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_met = html.find('id="pageMetabolic"')
if pos_met != -1:
    print("=== id=pageMetabolic ===")
    print(html[pos_met:pos_met+3000])
