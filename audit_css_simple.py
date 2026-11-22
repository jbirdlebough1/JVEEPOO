with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

print("Searching lines for chart styling:")
for i, line in enumerate(lines):
    if any(k in line for k in ['.chart-box', '.svg-timeline-container', '#tallChartSvgContainer', 'svgTallMaster', 'background: #05080f', 'background:#05080f']):
        print(f"Line {i+1}: {line.strip()[:140]}")
