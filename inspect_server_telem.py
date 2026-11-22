with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('api/telemetry')
print(text[idx-50:idx+2000])
