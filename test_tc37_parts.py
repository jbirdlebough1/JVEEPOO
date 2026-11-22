with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    c = f.read()

p1 = 'MEAL PANTRY' in c
p2 = 'PREVIOUSLY CONSUMED' in c
p3 = "if (typeof renderDietHistory === 'function') renderDietHistory();" in c
p4 = "if (typeof loadGoalsComplianceReport === 'function') loadGoalsComplianceReport();" in c

print('MEAL PANTRY:', p1)
print('PREVIOUSLY CONSUMED:', p2)
print('renderDietHistory:', p3)
print('loadGoalsComplianceReport:', p4)
