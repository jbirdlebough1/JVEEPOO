with open(r'D:\veepoo\w11_htm_app\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'svgHybridDial' in l and 'getElementById' in l:
        print(f"Line {i+1}: {l.strip()}")
        # print 20 lines before
        for k in range(max(0, i-25), i+1):
            if 'function ' in lines[k]:
                print(f"  Inside: {lines[k].strip()}")
