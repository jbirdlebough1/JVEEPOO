import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# Check key tab containers
tabs = ['pageFullScan', 'pageVitals', 'pageCardio', 'pageMetabolic', 'pageExport', 'pageDietTrades', 'pageReports', 'pageLearning', 'pageHelp', 'pageMedication', 'pageGoals', 'pageSetup', 'pageOperations', 'pageLog', 'pageWatchFaces']

for t in tabs:
    m = re.search(rf'<div\s+id=[\"\']{t}[\"\'][\s\S]*?(?=<div\s+id=[\"\']page|\Z)', html)
    if not m:
        print(f"FAILED to find tab {t}")
    else:
        # count <div> and </div>
        content = m.group(0)
        opens = len(re.findall(r'<div\b', content))
        closes = len(re.findall(r'</div>', content))
        print(f"Tab {t}: <div opens: {opens}, closes: {closes}")

print("DOM structure check complete!")
