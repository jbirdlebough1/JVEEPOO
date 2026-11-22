with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('title_display = meal_name.strip()')
print(text[pos-200:pos+1500])
