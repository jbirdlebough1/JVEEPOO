with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, line in enumerate(lines):
    if 'boxSynchronizedTallChart' in line:
        print(f"boxSynchronizedTallChart at line {i+1}")
        for j in range(max(0, i-5), min(len(lines), i+30)):
            print(f"  {j+1}: {lines[j]}", end='')
        break
