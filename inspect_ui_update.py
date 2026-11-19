with open(r'D:\veepoo\opt2_decompiled\vpprotocol\sources\com\veepoo\protocol\util\UiUpdateUtil.java', 'r', encoding='utf-8', errors='ignore') as f:
    text = f.read()

print("UiUpdateUtil.java length:", len(text))
for line in text.splitlines()[:100]:
    print(line)
