with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('def get_recent_meals')
print(text[idx:idx+800])
