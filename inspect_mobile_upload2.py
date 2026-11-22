with open(r'D:/veepoo/w11_htm_app/mobile_upload.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('<form')
if m == -1:
    m = text.find('upload')
print(text[m:m+2000])
