import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

s29 = [s for s in slides if s['num'] == '29'][0]
print("NUM:", s29['num'])
print("TITLE:", s29['title'])
print("BULLETS:", s29['bullets'])
print("NARRATION:", s29['narration'])
print("TAB_SCREENSHOT:", s29.get('tab_screenshot'))
print("STEP_FOCUS:", s29.get('step_focus'))
