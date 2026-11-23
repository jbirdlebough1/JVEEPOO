with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos_sound = html.find('function soundSuccess')
print('function soundSuccess at pos:', pos_sound)
print('Context around soundSuccess:')
print(html[pos_sound - 200:pos_sound + 500])

# Check what script tag or block it is in
script_open = html.rfind('<script', 0, pos_sound)
script_close = html.find('</script>', pos_sound)
print(f'Script open: {script_open}, close: {script_close}')

# Check where executeFullWatchConnectSync is relative to that script tag
pos_sync = html.find('async function executeFullWatchConnectSync')
print(f'executeFullWatchConnectSync pos: {pos_sync}')
print(f'Is sync inside same script? {script_open <= pos_sync <= script_close}')
