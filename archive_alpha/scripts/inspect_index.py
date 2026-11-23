lines = open(r"D:\veepoo\w11_htm_app\index.html", encoding="utf-8").readlines()
print("Tab pages in index.html:")
for i, l in enumerate(lines):
    if 'id="page' in l or 'class="tab-page' in l:
        print(f"Line {i+1}: {l.strip()[:100]}")
