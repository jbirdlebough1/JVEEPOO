with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('txtMealBuilderInput')
print(f"txtMealBuilderInput found at {m}")
if m != -1:
    print(text[m-800:m+1200])
