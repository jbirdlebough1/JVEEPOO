with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('/api/meal/analyze')
print(f"/api/meal/analyze found at {m}")
if m != -1:
    print(text[m:m+1500])
