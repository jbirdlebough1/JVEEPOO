with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = re.findall(r'def\s+([a-zA-Z0-9_]*rollup[a-zA-Z0-9_]*|[a-zA-Z0-9_]*daily[a-zA-Z0-9_]*|[a-zA-Z0-9_]*history[a-zA-Z0-9_]*|[a-zA-Z0-9_]*report[a-zA-Z0-9_]*)', text, re.IGNORECASE)
print('Server functions matching rollup/daily/history/report:', set(matches))

matches_routes = re.findall(r'self\.path\.startswith\([\'"]([^\'"]+)[\'"]\)', text)
print('All GET/POST routes in server.py:')
for r in sorted(set(matches_routes)):
    print('  ', r)
