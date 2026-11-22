with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = 120474
print("=== Around 120474 (Tab 08 Learning) ===")
print(text[pos-400:pos+1500])
