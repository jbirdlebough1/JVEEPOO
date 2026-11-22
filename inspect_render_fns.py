with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

def show_fn(name):
    idx = text.find(f'function {name}')
    if idx != -1:
        print(f"\n==================== {name} ====================")
        # find matching closing brace or next function
        next_fn = text.find('function ', idx + 20)
        print(text[idx:next_fn if next_fn != -1 else idx+4000])

show_fn('render360HealthView')
show_fn('filterDailyUpToCurrentTime')
show_fn('renderSvgCurveChart')
show_fn('renderSvgDualCurveChart')
show_fn('renderLorenzScatterPlot')
show_fn('renderStepGlucoseCorrelator')
