with open('presentation/slides/slide_01.html', 'r', encoding='utf-8') as f:
    text = f.read()

print('Length:', len(text))
print(text[:1200])
