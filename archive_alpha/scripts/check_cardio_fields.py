with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_c = html.find('id="pageCardio"')
print("=== pageCardio ===")
print(html[pos_c:pos_c+2500])
