with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i in range(2022, 2320):
    l = lines[i]
    if '<div' in l or '</div' in l or 'class=' in l or 'id=' in l:
        if any(w in l for w in ['stat-card', 'setup-section', 'setup-section-title', 'id=', 'cardio', 'sleep', 'workout']):
            print(f'{i+1}: {l.strip()[:100]}')
