with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('executeFullWatchConnectSync')
end_idx = text.find('function setupGattChannels', idx)
print(text[idx+1800:end_idx])
