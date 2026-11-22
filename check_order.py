import re

with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pages = re.findall(r'<div[^>]+id=["\'](page[A-Za-z0-9_]+)["\']', text)
print("Pages in DOM order:")
for i, p in enumerate(pages):
    print(f"  {i+1}: {p}")

sidebar_buttons = re.findall(r'<button[^>]+id=["\']navTab([A-Za-z0-9_]+)["\'][^>]*onclick=["\']switchTab\(["\']([a-z0-9_]+)["\']\)[^>]*>(.*?)</button>', text, re.S)
print("\nSidebar order:")
for i, (tab_id, target, content) in enumerate(sidebar_buttons):
    label = re.sub(r'<[^>]+>', '', content).strip()
    label = re.sub(r'\s+', ' ', label)
    print(f"  {i+1}: navTab{tab_id} -> target '{target}' | Label: '{label}'")
