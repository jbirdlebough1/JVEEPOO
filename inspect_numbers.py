with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    text = f.read()

# Check scale numbers in SVG
pos = text.find('LARGE, HIGH-CONTRAST THRESHOLD NUMBERS')
if pos != -1:
    pos_end = text.find('</g>', pos)
    print("Found numbers block:")
    print(text[pos:pos_end+4])
