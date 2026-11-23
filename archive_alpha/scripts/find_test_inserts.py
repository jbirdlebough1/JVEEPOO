import glob, re

for fpath in glob.glob('D:/veepoo/*.py'):
    with open(fpath, 'r', encoding='utf-8', errors='ignore') as f:
        text = f.read()
    if 'INSERT INTO meal_entries' in text or '/api/meal/upload' in text or '/api/meal/submit' in text:
        print(f"File {fpath} touches meals!")
