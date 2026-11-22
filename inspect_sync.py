import re

with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

matches = re.findall(r"switchTab\('([^']+)'\)", text)
print('Total switchTab calls:', len(matches))
print('Unique switchTab calls:', sorted(list(set(matches))))

# Let's find the sidebar navigation HTML
nav_start = text.find('id="sidebarNav"')
if nav_start == -1:
    nav_start = text.find('class="lcars-sidebar')
if nav_start == -1:
    nav_start = text.find('sidebar')

print("Sidebar search around pos:", nav_start)
print(text[nav_start:nav_start+3000])
