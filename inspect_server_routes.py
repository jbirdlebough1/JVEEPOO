with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('class LCARSHandler')
print("LCARSHandler found at", m)
m_get = text.find('def do_GET', m)
print(text[m_get:m_get+1200])
