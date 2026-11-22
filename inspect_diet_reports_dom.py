with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="dietHistoryList"')
print("=== HTML around dietHistoryList ===")
print(text[idx-400:idx+600])

idx_rep = text.find('switchReportSubTab')
if idx_rep == -1:
    idx_rep = text.find('currentReportTimeframe')
print("\n=== Reports Tab navigation/timeframe ===")
print(text[idx_rep-200:idx_rep+800])
