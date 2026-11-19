with open(r'D:\veepoo\opt2_decompiled\vpprotocol\sources\com\veepoo\protocol\util\UiUpdateUtil.java', 'r', encoding='utf-8', errors='ignore') as f:
    text = f.read()

pos = text.find('setCustomWacthUi')
if pos != -1:
    print(text[pos:pos+2500])
