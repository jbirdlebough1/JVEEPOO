with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

pos = py.find('INSERT OR REPLACE INTO popular_meals')
pos2 = py.find('INSERT OR REPLACE INTO popular_meals', pos + 30)
if pos2 != -1:
    print(py[pos2-200:pos2+500])
