with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function renderReportsView()')
first_d = text.find('if (isDaily)', idx)
second_d = text.find('if (isDaily)', first_d + 20)
end_d = text.find('} else if (isWeekly)', second_d)
print("=== Second isDaily block (lines) ===")
print(text[second_d:end_d])
