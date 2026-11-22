with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

target = 'import json\nimport os'
replacement = 'import json\nimport os\nimport re'
assert target in text, "target not found"
text = text.replace(target, replacement, 1)

with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(text)

print("[+] Added import re to w11_htm_app/server.py")
