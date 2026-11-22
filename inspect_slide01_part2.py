with open('presentation/slides/slide_01.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos_ul = text.find('class="bullets-list"')
print(text[pos_ul+200:pos_ul+2500])
