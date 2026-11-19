import os, re

for root, dirs, files in os.walk(r'D:\veepoo\opt2_decompiled'):
    for f in files:
        if f.endswith('.java'):
            p = os.path.join(root, f)
            try:
                with open(p, 'r', encoding='utf-8', errors='ignore') as fl:
                    content = fl.read()
                    if 'dump' in content.lower() or 'readwatchui' in content.lower() or 'readproduction' in content.lower():
                        for m in re.finditer(r'.{0,40}(dump|readwatchui|readproduction).{0,60}', content, re.IGNORECASE):
                            print(f"{f}: {m.group(0).strip()}")
            except Exception:
                pass
