with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find("'/api/meal/analyze'")
if idx == -1:
    idx = text.find('"/api/meal/analyze"')
if idx == -1:
    idx = text.find('meal/analyze')
print(f"meal/analyze at {idx}")
if idx != -1:
    print(text[idx-200:idx+3500])
