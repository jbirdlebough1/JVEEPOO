import os
import re
import json

preview_path = r'D:\veepoo\presentation\preview.html'
with open(preview_path, 'r', encoding='utf-8') as f:
    text = f.read()

# Extract slides array from preview.html
idx_start = text.find('const slides = [')
if idx_start == -1:
    idx_start = text.find('var slides = [')
if idx_start == -1:
    idx_start = text.find('let slides = [')

print("slides array found at:", idx_start)

# Find closing bracket
depth = 0
idx_end = None
for i in range(idx_start, len(text)):
    if text[i] == '[': depth += 1
    elif text[i] == ']':
        depth -= 1
        if depth == 0:
            idx_end = i + 1
            break

slides_js = text[idx_start + len('const slides = '):idx_end]
print("slides JS length:", len(slides_js))

# Check audio files in D:\veepoo\presentation\audio
audio_dir = r'D:\veepoo\presentation\audio'
existing_audio = os.listdir(audio_dir) if os.path.exists(audio_dir) else []
print(f"Total audio files in {audio_dir}: {len(existing_audio)}")

# Check slide HTML files in D:\veepoo\presentation\
slide_files = [f for f in os.listdir(r'D:\veepoo\presentation') if f.startswith('slide_') and f.endswith('.html')]
print(f"Total slide HTML files in presentation: {len(slide_files)}")

# Check images referenced in slide HTML files
missing_images = []
for sf in slide_files:
    fp = os.path.join(r'D:\veepoo\presentation', sf)
    with open(fp, 'r', encoding='utf-8') as f:
        s_content = f.read()
    imgs = re.findall(r'<img[^>]*src=[\'"]([^\'"]+)[\'"]', s_content)
    for img in imgs:
        # Resolve relative to presentation
        clean_img = img.split('?')[0]
        if clean_img.startswith('images/'):
            full_img = os.path.join(r'D:\veepoo\presentation', clean_img)
            if not os.path.exists(full_img):
                missing_images.append((sf, img))

print(f"Missing images across all slides: {len(missing_images)}")
if missing_images:
    for m in missing_images[:10]:
        print("  Missing:", m)
else:
    print("✓ All slide images exist on disk!")
