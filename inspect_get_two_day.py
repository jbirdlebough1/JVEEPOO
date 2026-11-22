with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('def get_two_day_timeline')
print(text[idx:idx+2500])
