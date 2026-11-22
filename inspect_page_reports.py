with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageReports"')
if idx == -1:
    idx = text.find('id="reports"')
print("=== Reports page section ===")
print(text[idx:idx+2500])
