with open(r'D:\veepoo\w11_htm_app\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Find the nav bar
nav_start = text.find('<nav class="lcars-sidebar"')
nav_end = text.find('</nav>', nav_start)
print("CURRENT SIDEBAR BUTTONS:")
print(text[nav_start:nav_end+6])
