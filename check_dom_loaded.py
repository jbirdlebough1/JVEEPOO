with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find("window.addEventListener('DOMContentLoaded'")
print(text[m-200:m+500])
