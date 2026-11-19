with open(r'D:\veepoo\opt2_decompiled\vpprotocol\sources\com\veepoo\protocol\operate\vp_dc.java', 'r', encoding='utf-8', errors='ignore') as f:
    text = f.read()

pos = text.find('vp_a(EUIFromType')
if pos == -1:
    pos = text.find('byte[] vp_a(')
print(text[pos:pos+1500])
