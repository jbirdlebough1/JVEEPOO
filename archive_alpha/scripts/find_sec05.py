with open('index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('SECTION 05: SPECIALIZED CARDIOMETABOLIC PROCEDURES')
print("Position:", pos)
if pos != -1:
    # Find the enclosing <div id=...
    prev_div = text.rfind('<div', 0, pos)
    prev_page = text.rfind('id="page', 0, pos)
    print("Enclosing page:", text[prev_page:prev_page+50])
    print(text[pos-200:pos+300])
