import re

with open("index.html", "r", encoding="utf-8") as f:
    html = f.read()

# Let's inspect style for lcars-workspace, tab-page, diet-container
css_match = re.search(r'<style>(.*?)</style>', html, re.DOTALL)
if css_match:
    css = css_match.group(1)
    for selector in ['.lcars-workspace', '.tab-page', '.diet-container', '.diet-form-card', '#pageFullScan']:
        idx = css.find(selector)
        if idx != -1:
            print(f"--- {selector} ---")
            print(css[idx:idx+300])
