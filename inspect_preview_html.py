with open(r'D:\veepoo\presentation\preview.html', 'r', encoding='utf-8') as f:
    text = f.read()

print("Length of preview.html:", len(text))
pos_iframe = text.find('iframe')
print("iframe found at:", pos_iframe)
print(text[pos_iframe-100:pos_iframe+300] if pos_iframe != -1 else "No iframe")
