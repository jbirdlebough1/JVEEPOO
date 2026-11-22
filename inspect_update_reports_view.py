with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('function updateReportsView(')
pos_end = text.find('function ', pos + 30)
print('updateReportsView range:', pos, pos_end)
print(text[pos:pos+1500])
