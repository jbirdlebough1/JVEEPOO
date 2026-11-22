with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx1 = text.find('function renderReportsView() {')
print("idx1:", idx1)

# Find function closing brace
# Notice: inside renderReportsView, functions like renderRawTelemetryTable() are called
idx_call = text.find('renderRawTelemetryTable();', idx1)
print("renderRawTelemetryTable(); at:", idx_call)

idx_end = text.find('\n    }\n', idx_call)
print("idx_end:", idx_end)
print("Code around idx_end:")
print(repr(text[idx_end-40:idx_end+80]))
