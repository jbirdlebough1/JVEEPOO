with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

import re
matches = list(re.finditer('geminiMealAnalysisResult', text))
print(f"geminiMealAnalysisResult found {len(matches)} times:")
for m in matches:
    start = max(0, m.start() - 50)
    end = min(len(text), m.end() + 100)
    print(text[start:end].replace('\n', ' '))
