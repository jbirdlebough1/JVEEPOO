with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if i > 1750 and '/api/meal/daily_summary' in l:
        for j in range(i-5, min(len(lines), i+40)):
            print(f"{j+1}: {lines[j]}", end='')
        break
