import json

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides:
    num = s['num']
    title = s['title']
    screenshot = s.get('tab_screenshot', '')
    narration = s.get('narration', '')
    
    # Check if 'masterclass' is in narration
    if 'masterclass' in narration.lower():
        print(f"SLIDE {num} has masterclass in narration!")
    if 'workbench' in narration.lower():
        print(f"SLIDE {num} has workbench in narration!")
    if 'copilot' in narration.lower():
        print(f"SLIDE {num} has copilot in narration!")

print("Check finished.")
