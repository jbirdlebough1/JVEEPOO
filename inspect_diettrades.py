with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('id="pageDietTrades"')
if pos != -1:
    print(text[pos:pos+1500])
else:
    print("pageDietTrades not found")
