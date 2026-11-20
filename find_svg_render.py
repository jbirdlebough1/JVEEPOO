with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
print("renderTallChartSvg:", re.findall(r'.{0,40}renderTallChartSvg.{0,50}', text))
print("svg height:", re.findall(r'.{0,30}height=[\'"][^\'"]*[\'"].{0,30}', text[:1500]))
