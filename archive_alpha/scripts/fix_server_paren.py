with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()

# Check line 1934 and 1935
if lines[1933].strip() == ')' and lines[1934].strip() == ')':
    del lines[1934]
    print("Removed duplicate closing paren at line 1935")

with open('w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.writelines(lines)
