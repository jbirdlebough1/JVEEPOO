import hashlib

for p in ['index.html', 'w11_htm_app/index.html']:
    with open(p, 'r', encoding='utf-8') as f:
        text = f.read()

    old_lbl = '<label for="listboxPopularMeals" style="font-size: 0.8rem; font-weight: 800; color: #166534; text-transform: uppercase;">\n                        📋 PATHWAY A: REUSE MEAL PANTRY\n                      </label>'
    new_lbl = '<label for="listboxPopularMeals" style="font-size: 0.8rem; font-weight: 800; color: #166534; text-transform: uppercase;">\n                        📋 PATHWAY A: REUSE MEAL PANTRY (PREVIOUSLY CONSUMED &amp; SAVED MEALS)\n                      </label>'

    assert old_lbl in text, f"old_lbl not found in {p}"
    text = text.replace(old_lbl, new_lbl, 1)

    with open(p, 'w', encoding='utf-8') as f:
        f.write(text)

with open('index.html', 'rb') as f: h1 = hashlib.sha256(f.read()).hexdigest()
with open('w11_htm_app/index.html', 'rb') as f: h2 = hashlib.sha256(f.read()).hexdigest()
assert h1 == h2, "Dual file mismatch"
print("Parity verified!")
