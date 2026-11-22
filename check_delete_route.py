with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('self.path.startswith("/api/meal/delete")')
print(text[idx-50:idx+800])
