with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx_dopost = text.find('def do_POST')
sub = text[idx_dopost:]
import re
for m in re.finditer(r'elif self\.path[^\n:]*', sub):
    print(m.group(0))
