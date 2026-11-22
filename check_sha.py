import hashlib

with open('D:/veepoo/index.html', 'rb') as f1:
    h1 = hashlib.sha256(f1.read()).hexdigest()

with open('D:/veepoo/w11_htm_app/index.html', 'rb') as f2:
    h2 = hashlib.sha256(f2.read()).hexdigest()

print('index.html sha256:            ', h1)
print('w11_htm_app/index.html sha256:', h2)
print('Identical:', h1 == h2)
