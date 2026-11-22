with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('03. CLINICAL COLLABORATION PROTOCOL: WORKING WITH YOUR DOCTOR')
idx_help = text.find('id="pageHelp"', idx)
print(text[idx-50:idx_help])
