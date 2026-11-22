with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

for fn in ['querySleepData', 'assembleAndParseSleepStream', 'renderSleepChart', 'renderHypnogram']:
    pos = text.find(fn)
    if pos != -1:
        print(f"=== {fn} at {pos} ===")
        print(text[pos:pos+1200])
    else:
        print(f"=== {fn} NOT FOUND ===")
