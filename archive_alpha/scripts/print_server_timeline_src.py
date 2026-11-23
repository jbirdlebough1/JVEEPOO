with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos_start = text.find('def get_two_day_timeline():')
pos_end = text.find('class VeepooHandler(', pos_start)
print(text[pos_start:pos_end])
