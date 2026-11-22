with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('SECTION 01: BIOMETRIC PROFILE')
print(html[pos:pos+3000])
