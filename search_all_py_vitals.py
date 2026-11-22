import glob, re

for f in glob.glob('D:/veepoo/*.py'):
    with open(f, 'r', encoding='utf-8', errors='ignore') as fp:
        c = fp.read()
    if 'pageVitals' in c or 'navTabVitals' in c:
        print(f"Found in {f}")
