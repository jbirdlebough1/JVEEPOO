with open('presentation/slides/slide_01.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos_shot = text.find('left-screenshot-card')
print(text[pos_shot:pos_shot+2500])
