with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_poll = html.find('async function pollLiveTelemetryChanges')
end_poll = html.find('function ', pos_poll + 50)
print(html[pos_poll:end_poll])
