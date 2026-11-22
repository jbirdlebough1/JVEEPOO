import os
import re

for root, dirs, files in os.walk(r'D:\veepoo'):
    if any(ign in root for ign in ['.git', 'node_modules', 'tools']):
        continue
    for f in files:
        if f.endswith(('.html', '.js', '.py')):
            p = os.path.join(root, f)
            try:
                with open(p, 'r', encoding='utf-8', errors='ignore') as fp:
                    for i, l in enumerate(fp):
                        if any(k in l.lower() for k in ['load watch', 'failed to load', 'fail to load', 'loadwatch']):
                            print(f"{p}:{i+1}: {l.strip()[:100]}")
            except Exception:
                pass
