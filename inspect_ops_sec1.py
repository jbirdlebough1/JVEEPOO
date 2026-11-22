with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageOperations"')
idx_sec2 = text.find('02. PRECISION TRANSDUCER CALIBRATION PROTOCOLS', idx)
print(text[idx:idx_sec2])
