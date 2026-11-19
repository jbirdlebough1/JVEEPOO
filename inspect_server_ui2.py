with open(r'D:\veepoo\opt2_decompiled\vpprotocol\sources\com\veepoo\protocol\util\UiUpdateUtil.java', 'r', encoding='utf-8', errors='ignore') as f:
    text = f.read()

pos = text.find('public void setCustomWacthUi')
print(text[pos+200:pos+2500])
