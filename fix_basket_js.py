with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

text = text.replace("basket: '$7.85 Basket'", "basket: 'ADA Clinically Approved'")
text = text.replace("basket: '$8.40 Basket'", "basket: 'ADA Clinically Approved'")

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(text)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(text)

print("[OK] Cleaned basket prices from JS storeData in both files!")
