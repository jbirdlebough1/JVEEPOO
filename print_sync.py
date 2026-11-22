with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

pos_sync = html.find('async function executeFullWatchConnectSync')
end_sync = html.find('function ', pos_sync + 40)
sync_fn = html[pos_sync:end_sync]
print("executeFullWatchConnectSync code:")
print(sync_fn)
