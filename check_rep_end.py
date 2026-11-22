with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

start_rep = text.find('function renderReportsView() {')
idx = text.find('renderRawTelemetryTable();', start_rep)
print("start_rep:", start_rep)
print("renderRawTelemetryTable at:", idx)
if idx != -1:
    idx_close = text.find('}', idx)
    print("close brace at:", idx_close)
    print(repr(text[idx_close-30:idx_close+30]))
