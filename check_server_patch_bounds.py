with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    content = f.read()

# Let's inspect get_two_day_timeline exact bounds
pos_start = content.find('def get_two_day_timeline():')
pos_end = content.find('class VeepooHandler(', pos_start)
print('get_two_day_timeline length:', pos_end - pos_start)
