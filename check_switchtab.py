with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

pos = html.find('function switchTab')
end_pos = html.find('function ', pos + 30)
print(html[pos:end_pos])
