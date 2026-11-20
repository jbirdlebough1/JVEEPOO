import json
import re

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides:
    num = s['num']
    narration = s.get('narration', '')
    if re.search(r'\bslide\b', narration, re.IGNORECASE):
        # find sentences with slide
        sentences = re.split(r'(?<=[.!?])\s+', narration)
        slide_sentences = [sent for sent in sentences if re.search(r'\bslide\b', sent, re.IGNORECASE)]
        print(f"--- SLIDE {num} ---")
        for sent in slide_sentences:
            print("  ", sent)
