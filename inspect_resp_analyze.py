with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('elif self.path == "/api/meal/analyze":')
if m == -1:
    m = text.find('/api/meal/analyze')
m2 = text.find('self.send_json_response', m)
print(text[m2:m2+500])
