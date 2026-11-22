with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('"id": "salmon"')
print('=== server.py around salmon ===')
print(text[idx-50:idx+600])

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text2 = f.read()

idx2 = text2.find('salmon: {')
print('\n=== index.html around salmon: { ===')
print(text2[idx2-50:idx2+600])
