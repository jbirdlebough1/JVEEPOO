with open('presentation/slides/slide_01.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos_main = text.find('class="slide-main"')
if pos_main == -1:
    pos_main = text.find('slide-body')
if pos_main == -1:
    pos_main = text.find('<body>')
print(text[pos_main:pos_main+2000])
