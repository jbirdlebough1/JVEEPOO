with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('/api/meal/analyze')
print(text[pos+1000:pos+2500])
