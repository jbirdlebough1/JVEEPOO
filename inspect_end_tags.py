with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(7415, len(lines)):
    if '</div>' in lines[i] or '<div' in lines[i] or '</body>' in lines[i] or '</main>' in lines[i]:
        if i < 7430 or i > len(lines) - 40:
            print(f"{i+1}: {lines[i]}", end='')
