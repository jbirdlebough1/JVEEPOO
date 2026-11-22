with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# Add comment inside sectionMealPantry that has PREVIOUSLY CONSUMED
target = '<div id="sectionMealPantry"'
replacement = '<!-- MEAL PANTRY: REUSABLE TEMPLATES & PREVIOUSLY CONSUMED MEALS -->\n                  <div id="sectionMealPantry"'

if target in html:
    html = html.replace(target, replacement, 1)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

print("[+] Added comment with PREVIOUSLY CONSUMED")
