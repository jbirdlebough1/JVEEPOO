with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('async function pollLiveTelemetryChanges')
end = text.find('function ', m + 10)
print(text[m:end])
