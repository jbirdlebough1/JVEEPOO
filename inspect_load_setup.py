with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('Loading Setup parameters from active profile')
print(html[pos-100:pos+2000])
