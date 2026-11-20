import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides:
    if s['num'] == '25':
        print("TITLE:", s['title'])
        print("BULLETS:", s['bullets'])
        print("NARRATION:", s['narration'])
        print("RIGHT_HTML:\n", s.get('right_html'))
        break
