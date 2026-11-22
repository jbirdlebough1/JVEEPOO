with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

p1 = text.find('// =========================================================================\n    // STEP VOLUME VS. 24-HOUR MEAN GLUCOSE BIVARIATE CORRELATOR ENGINE')
p2 = text.find('// =========================================================================\n    // CLINICAL GOALS ENGINE', p1)

print('--- START OF BIVARIATE ---')
print(text[p1:p1+800])
print('--- END OF BIVARIATE ---')
print(text[p2-800:p2])
