with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_sync = html.find('// Step 8: Trigger Server Sync')
print(html[pos_sync:pos_sync + 1500])
