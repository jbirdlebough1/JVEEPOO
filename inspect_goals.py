with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function loadClinicalGoalsUI')
print(text[idx:idx+2000])
