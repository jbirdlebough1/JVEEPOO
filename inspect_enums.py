import re

for fn in ['EWatchUIElementType.html', 'EWatchUIElementPosition.html', 'EWatchUIType.html']:
    p = f'D:/veepoo/opt1_sdk_official/android_sdk_source/apidoc/com/veepoo/protocol/model/enums/{fn}'
    with open(p, 'r', encoding='utf-8', errors='ignore') as f:
        html = f.read()
    clean = re.sub(r'<[^>]+>', ' ', html)
    clean = re.sub(r'\s+', ' ', clean)
    print(f"=== {fn} ===")
    print(clean[:1000])
