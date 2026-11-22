with open(r'D:\veepoo\w11_htm_app\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('PHYSICAL SIMULATOR')
if pos != -1:
    print(text[pos-100:pos+2500])
else:
    print("Not found")
