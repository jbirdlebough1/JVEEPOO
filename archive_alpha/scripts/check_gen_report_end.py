with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('function generateFinalFullScanReport')
end = text.find('function ', m + 10)
print(text[end-800:end])
