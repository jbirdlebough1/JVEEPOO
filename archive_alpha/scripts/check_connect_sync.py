with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_sync = html.find('async function executeFullWatchConnectSync')
pos_end = html.find('function ', pos_sync + 100)
print(html[pos_sync:pos_sync + 3000])
