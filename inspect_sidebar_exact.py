with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

p1 = text.find('<nav class="lcars-sidebar">')
p2 = text.find('</nav>', p1) + len('</nav>')
print(repr(text[p1:p2]))
