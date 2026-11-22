with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function querySleepData')
print("=== querySleepData ===")
print(text[idx:idx+1500])

idx_stream = text.find('function assembleAndParseSleepStream')
print("\n=== assembleAndParseSleepStream ===")
print(text[idx_stream:idx_stream+3000])

idx_e0 = text.find('if (header === 0xE0')
print("\n=== if (header === 0xE0) ===")
print(text[idx_e0:idx_e0+1500])
