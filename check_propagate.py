with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('function propagateFullScanResultsToAllPages')
print(f"propagateFullScanResultsToAllPages found at {m}")
if m != -1:
    end = text.find('function ', m + 10)
    print(text[m:end])
