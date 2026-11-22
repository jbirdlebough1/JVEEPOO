with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

def show_code(name, max_len=2000):
    idx = text.find(f'function {name}')
    if idx != -1:
        print(f"\n================ {name} ================")
        print(text[idx:idx+max_len])

show_code('renderSvgCurveChart')
show_code('renderSvgDualCurveChart')
show_code('renderLorenzScatterPlot')
show_code('renderStepGlucoseCorrelator')
show_code('renderTallSynchronizedChart')
