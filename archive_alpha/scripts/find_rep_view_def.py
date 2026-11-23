with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find("currentReportTimeframe = 'daily'")
print(text[idx-100:idx+2500])
