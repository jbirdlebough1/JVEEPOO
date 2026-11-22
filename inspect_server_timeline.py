with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

for i, line in enumerate(lines):
    if 'def get_two_day_timeline' in line:
        print(f"get_two_day_timeline at line {i+1}:")
        for j in range(i, min(i+100, len(lines))):
            print(f"  {j+1}: {lines[j]}")
        break
