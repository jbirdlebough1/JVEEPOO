import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

s27 = [s for s in slides if s['num'] == '27'][0]
print("Slide 27 screenshot:", s27.get('tab_screenshot'))
print("Slide 27 step_focus:", s27.get('step_focus'))
