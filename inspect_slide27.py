import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

s27 = [s for s in slides if s['num'] == '27'][0]
print("TITLE:", s27['title'])
print("BULLETS:", s27['bullets'])
print("NARRATION:", s27['narration'])
print("RIGHT_HTML:\n", s27.get('right_html'))
