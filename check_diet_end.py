with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('async function renderDietHistory')
end_pos = html.find('function deleteMealEntry', pos)
print("renderDietHistory full text:")
print(html[pos:end_pos])
