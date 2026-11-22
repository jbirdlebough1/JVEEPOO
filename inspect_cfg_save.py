with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find("age: getInt('cfgAge', 45)")
print(html[pos-300:pos+1500])
