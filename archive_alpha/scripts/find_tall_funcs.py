with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
funcs = ['renderTallChart', 'renderTallTimeline', 'onTallScrubberMoved', 'setTallChartTimeframe', 'renderBivariate', 'updateBivariate', 'onSliderStepTargetChanged']
for fn in funcs:
    pos = text.find(fn)
    if pos != -1:
        line_no = text[:pos].count('\n') + 1
        print(f'{fn} found at line {line_no}')
    else:
        print(f'{fn} NOT FOUND')
