with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx1 = text.find('function renderSvgCurveChart(containerId, data, options = {}) {')
idx2 = text.find('// CLINICAL PROTOCOL SPECIFICATION: 2D LORENZ (POINCARÉ) SCATTER ENGINE')

print("idx1:", idx1)
print("idx2:", idx2)
print("text before idx2:")
print(repr(text[idx2-120:idx2]))
