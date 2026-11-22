with open(r"D:/veepoo/build_presentation.py", "r", encoding="utf-8") as f:
    text = f.read()

m = text.find('def generate_slide_html')
print(text[m:m+2000])
