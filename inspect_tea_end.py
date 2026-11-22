with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('glut4_recommendation": "No postprandial GLUT4 walk required.')
print(text[pos:pos+500])
