with open(r'D:\veepoo\w11_htm_app\index.html', encoding='utf-8') as f:
    text = f.read()

start = text.find('function switchTab(')
end = text.find('// =========================================================================\n    // VEEPOO GATT CONSTANTS', start)
print(text[start:end])
