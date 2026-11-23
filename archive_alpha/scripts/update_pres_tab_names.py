with open('build_presentation.py', 'r', encoding='utf-8') as f:
    text = f.read()

# Replace 13-WATCH FACES in TAB_MAP
old_entry = '(13, "13-WATCH FACES", "images/lcars_tab13_watchfaces.png")'
new_entry = '(13, "13-WATCH", "images/lcars_tab13_watch.png")'

if old_entry in text:
    text = text.replace(old_entry, new_entry)
    with open('build_presentation.py', 'w', encoding='utf-8') as f:
        f.write(text)
    print("Updated build_presentation.py with 13-WATCH tab.")
else:
    print("old_entry not found in build_presentation.py")

with open('capture_all_fresh_presentation_screenshots.py', 'r', encoding='utf-8') as f:
    c_text = f.read()

old_cap = '{"tab": "watchfaces", "file": "lcars_tab13_watchfaces.png", "title": "Tab 13 Watch Faces & OTA"}'
new_cap = '{"tab": "watchfaces", "file": "lcars_tab13_watch.png", "title": "Tab 13 Watch Hardware & Analyzer"}'

if old_cap in c_text:
    c_text = c_text.replace(old_cap, new_cap)
    with open('capture_all_fresh_presentation_screenshots.py', 'w', encoding='utf-8') as f:
        f.write(c_text)
    print("Updated capture_all_fresh_presentation_screenshots.py with 13-WATCH.")
else:
    print("old_cap not found in capture_all_fresh_presentation_screenshots.py")
