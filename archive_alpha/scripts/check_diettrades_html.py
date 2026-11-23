with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_dt = html.find('id="pageDietTrades"')
print("pos_dt:", pos_dt)
if pos_dt != -1:
    print(html[pos_dt:pos_dt+2000])
