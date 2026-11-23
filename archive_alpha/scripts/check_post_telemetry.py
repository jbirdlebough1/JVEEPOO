with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('elif self.path == "/api/telemetry":')
if m == -1:
    m = text.find('/api/telemetry":')
print(text[m:m+1200])
