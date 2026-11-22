with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function renderReportsView()')
idx_sub = text.find('if (isDaily) {', idx + 10)
next_block = text.find('} else if (isWeekly) {', idx_sub)
print("=== Current isDaily data assignment block ===")
print(text[idx_sub:next_block])
