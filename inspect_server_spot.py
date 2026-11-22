with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('elif self.path == "/api/meal/recent":')
print(text[idx-100:idx+400])
