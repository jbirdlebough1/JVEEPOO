with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, line in enumerate(lines[:500]):
    if any(k in line for k in ['pageVitals', 'pageCardio', 'pageMetabolic', 'pageReports', 'pageDietTrades', 'nav', 'sidebar', 'switchPage', 'showPage']):
        print(f"Line {i+1}: {line.strip()[:120]}")
