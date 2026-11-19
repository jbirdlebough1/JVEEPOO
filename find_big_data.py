import os, re

for root, dirs, files in os.walk(r'D:\veepoo\opt2_decompiled'):
    for f in files:
        if f.endswith('.java'):
            p = os.path.join(root, f)
            try:
                with open(p, 'r', encoding='utf-8', errors='ignore') as fl:
                    content = fl.read()
                    if 'function_big_data_tarn' in content and 'equals' in content:
                        print(f"File: {f}")
                        lines = content.splitlines()
                        for i, l in enumerate(lines):
                            if 'function_big_data_tarn' in l:
                                for j in range(max(0, i-3), min(len(lines), i+10)):
                                    print(f"  {j+1}: {lines[j].strip()}")
            except Exception:
                pass
