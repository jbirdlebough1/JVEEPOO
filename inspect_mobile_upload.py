with open(r'D:/veepoo/w11_htm_app/mobile_upload.html', 'r', encoding='utf-8') as f:
    text = f.read()

print("Length of mobile_upload.html:", len(text))
print(text[:1500])
