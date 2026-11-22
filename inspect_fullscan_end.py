with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(5505, len(lines)):
    if '</main>' in lines[i] or '<!-- =====================================================================' in lines[i] and i > 5520:
        print(f"Line {i+1}: {lines[i]}")
        for j in range(max(0, i-5), min(len(lines), i+10)):
            print(f"  {j+1}: {lines[j]}", end='')
        break
