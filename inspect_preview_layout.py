with open('presentation/preview.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos_body = text.find('<body>')
pos_script = text.find('<script>')
print(text[pos_body:pos_script])
