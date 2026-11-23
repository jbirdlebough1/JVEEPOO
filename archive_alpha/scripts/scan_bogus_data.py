import re

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

print('=== SCANNING FOR HARDCODED DEMO / BOGUS DATA IN INDEX.HTML ===')
# Look for mock arrays, demo values, synthetic curve generators, hardcoded dummy readings
patterns = [
    r'mock[A-Za-z0-9_]*\s*=',
    r'demo[A-Za-z0-9_]*\s*=',
    r'dummy[A-Za-z0-9_]*\s*=',
    r'sample[A-Za-z0-9_]*\s*=',
    r'fallback[A-Za-z0-9_]*\s*=',
    r'synthetic[A-Za-z0-9_]*\s*=',
    r'fake[A-Za-z0-9_]*\s*=',
    r'generateDemo[A-Za-z0-9_]*',
    r'generateSample[A-Za-z0-9_]*',
    r'seedDemo[A-Za-z0-9_]*',
    r'defaultData\s*=',
    r'placeholder[A-Za-z0-9_]*\s*=',
    r'\[\s*(?:\{\s*time|\d+\.\d+|\d+,\s*\d+,\s*\d+).*\]'
]

for pat in patterns:
    matches = list(re.finditer(pat, text, re.I))
    if matches:
        print(f"\nPattern: {pat} ({len(matches)} matches)")
        for m in matches[:10]:
            start = max(0, m.start() - 40)
            end = min(len(text), m.end() + 80)
            print("  ...", text[start:end].replace('\n', ' '), "...")

# Also search for hardcoded DOM default numbers like 98.6, 72 bpm, 120/80, 7.4h sleep, etc. that look like live data instead of "--" or "NO DATA"
print('\n=== CHECKING DOM DEFAULT TEXT IN METRIC CARDS / TILES ===')
hardcoded_dom = re.findall(r'<div[^>]*class=["\'][^"\']*(?:value|metric|stat|reading|num)[^"\']*["\'][^>]*>([0-9][^<]*)</div>', text)
print("Hardcoded numbers in value containers (sample of 25):", hardcoded_dom[:25])
