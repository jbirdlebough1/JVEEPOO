with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'elif self.path.startswith("/api/meal/daily_summary"):' in l:
        for j in range(i, min(len(lines), i+80)):
            print(f"{j+1}: {lines[j]}", end='')
        break
