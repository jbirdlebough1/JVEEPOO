with open(r"D:/veepoo/presentation/slides/slide_01.html", "r", encoding="utf-8") as f:
    text = f.read()

m = text.find('class="lcars-sidebar')
if m == -1:
    m = text.find('sidebar')
print("Sidebar around pos:", m)
print(text[m:m+1500])
