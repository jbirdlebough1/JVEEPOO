# check_help_and_diet.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

print("Line 5450 - 5470 (Help tab opening):")
for l in range(5450, 5475):
    print(f"L{l:5}: {lines[l-1].strip()[:90]}")

print("\nLine 5900 - 5925 (DietTrades tab opening):")
for l in range(5900, 5925):
    print(f"L{l:5}: {lines[l-1].strip()[:90]}")
