with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, line in enumerate(lines):
    if 'id="pageDietTrades"' in line:
        print(f"pageDietTrades start at line {i+1}")
        for j in range(i, i+50):
            print(f"  {j+1}: {lines[j]}", end='')
        break
