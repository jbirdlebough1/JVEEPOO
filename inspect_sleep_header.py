with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('window._sleepPackets.push')
print(text[m-1800:m-600])
