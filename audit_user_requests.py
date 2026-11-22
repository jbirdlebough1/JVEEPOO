import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

print("--- SIDEBAR BUTTONS ---")
matches = re.findall(r'<button\s+id=[\'"]([^\'"]+)[\'"][^>]*onclick=[\'"]switchTab\(([^\)]+)\)[\'"][^>]*>([\s\S]*?)</button>', text[:60000])
for btn_id, arg, inner in matches:
    label_m = re.search(r'<span class=[\'"]tab-label[\'"]>([^<]+)</span>', inner)
    lbl = label_m.group(1) if label_m else inner.strip()
    print(f"btn: {btn_id:18} arg: {arg:15} label: {lbl}")

print("\n--- CLINICAL MENTORING / AIR GAPPED COMMENTS ---")
for m in re.finditer(r'(clinical\s+mentoring|air\s*gapped)', text, re.IGNORECASE):
    start = max(0, m.start() - 60)
    end = min(len(text), m.end() + 60)
    print(f"Match at {m.start()}: {repr(text[start:end])}\n")

print("\n--- 06-ADA OR MEAL PLANNER ---")
for m in re.finditer(r'06-ADA', text, re.IGNORECASE):
    start = max(0, m.start() - 60)
    end = min(len(text), m.end() + 60)
    print(f"Match at {m.start()}: {repr(text[start:end])}\n")
