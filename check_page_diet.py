with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('id="pageDietTrades"')
print(text[m:m+2500])
