# test_tag_fix.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Let's inspect exact lines 6768 to 6806
lines = text.splitlines()
print("Lines 6770 - 6806:")
for i in range(6769, min(6806, len(lines))):
    print(f"L{i+1:5}: {lines[i]}")
