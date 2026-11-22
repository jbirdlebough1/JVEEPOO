with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Check bivariate section
p1 = text.find('// =========================================================================\n    // STEP VOLUME VS. 24-HOUR MEAN GLUCOSE BIVARIATE CORRELATOR ENGINE')
p2 = text.find('// =========================================================================\n    // CLINICAL GOALS ENGINE', p1)
print('Bivariate code range:', p1, p2)

# Check tall chart section
t1 = text.find('function renderTallSynchronizedChart(tf) {')
t2 = text.find('function onTallScrubberMoved(percent) {', t1)
print('Tall chart code range:', t1, t2)
