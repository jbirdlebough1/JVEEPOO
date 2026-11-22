with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_poll = html.find('async function pollLiveTelemetryChanges')
print(html[pos_poll:pos_poll + 1000])
