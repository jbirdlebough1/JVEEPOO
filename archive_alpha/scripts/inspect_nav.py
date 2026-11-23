lines = open(r"D:\veepoo\w11_htm_app\index.html", encoding="utf-8").readlines()
print("Tab switching search:")
for i, l in enumerate(lines):
    if 'pageSetup' in l and ('onclick' in l or '<button' in l or '<a ' in l or 'nav' in l or 'tab' in l):
        print(f"Line {i+1}: {l.strip()[:100]}")
