with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('id="pageWatchFaces"')
print("pageWatchFaces idx:", idx)
if idx != -1:
    print(repr(text[idx-350:idx+50]))
