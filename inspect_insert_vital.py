with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('def insert_vital_record')
print("insert_vital_record at", m)
print(text[m:m+1200])
