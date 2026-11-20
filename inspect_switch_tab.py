with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

start = text.find('function switchTab')
print(text[start:start+600])
