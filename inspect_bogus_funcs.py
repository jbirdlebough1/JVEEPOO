with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function initializeVitalsAndCardioBaselines')
print("=== initializeVitalsAndCardioBaselines ===")
print(text[idx:idx+2500])

print("\n=== Search for other functions setting synthetic numbers ===")
import re
for m in re.finditer(r'function\s+([A-Za-z0-9_]+)\s*\([^)]*\)\s*\{', text):
    fname = m.group(1)
    sub = text[m.start():m.start()+800]
    if any(k in sub for k in ['118/76', '98.6', '7.4', '2,384', '8420', 'is83Mode', 'gAnnual', 'glucoses =', 'stepVols =']):
        print(f"Function with hardcoded data: {fname}")
