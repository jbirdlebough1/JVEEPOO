import re
import hashlib

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# 1. Update sidebar navigation order: swap 02-METABOLIC and 03-MEAL PLANNER
old_sidebar = '''    <nav class="lcars-sidebar">
      <button id="navTabFullScan" class="lcars-pill pill-blue active" onclick="switchTab('fullscan')">
        <span class="tab-label">01-FULL SCAN</span>
      </button>
      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span class="tab-label">02-METABOLIC</span>
      </button>
      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">03-MEAL PLANNER</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">04-REPORTS</span>
      </button>'''

new_sidebar = '''    <nav class="lcars-sidebar">
      <button id="navTabFullScan" class="lcars-pill pill-blue active" onclick="switchTab('fullscan')">
        <span class="tab-label">01-FULL SCAN</span>
      </button>
      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">02-MEAL PLANNER</span>
      </button>
      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span class="tab-label">03-METABOLIC</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">04-REPORTS</span>
      </button>'''

assert old_sidebar in html, "old_sidebar not found"
html = html.replace(old_sidebar, new_sidebar, 1)
print("[+] Swapped Meal Planner to Tab 02 and Metabolic to Tab 03 in sidebar navigation")

# 2. Clarify Meal Pantry label in Tab 02 (Meal Planner) to prevent duplication confusion
html = html.replace('📋 PATHWAY A: REUSE MEAL PANTRY (PREVIOUSLY CONSUMED &amp; SAVED MEALS)',
                    '📋 PATHWAY A: REUSE MEAL PANTRY (SAVED MEAL &amp; SNACK TEMPLATES)')
html = html.replace('MEAL PANTRY (PREVIOUSLY CONSUMED &amp; SAVED MEALS &bull; SCROLL &amp; SELECT)',
                    'MEAL PANTRY (SAVED MEAL &amp; SNACK TEMPLATES &bull; SCROLL &amp; SELECT)')
html = html.replace('MEAL PANTRY (PREVIOUSLY CONSUMED &amp; SAVED MEALS — SCROLL &amp; SELECT)',
                    'MEAL PANTRY (SAVED MEAL &amp; SNACK TEMPLATES — SCROLL &amp; SELECT)')

# 3. Clarify Metabolic Section 03 title: ACTUAL CONSUMED MEALS & GLUCOSE SCRUBBER (DATE & TIME TIMELINE)
html = html.replace('<span style="color: var(--lcars-lilac);">03. DIETARY INTAKE &amp; MEAL LOGGING</span>',
                    '<span style="color: var(--lcars-lilac);">03. ACTUAL CONSUMED MEALS &amp; GLUCOSE SCRUBBER (DATE &amp; TIME TIMELINE)</span>')

# 4. Update cross-references: Full scan link to Meal Planner (Tab 02)
html = html.replace("switchTab('diettrades')\">OPEN MEAL PLANNER &amp; COACH (TAB 03)</button>",
                    "switchTab('diettrades')\">OPEN MEAL PLANNER &amp; COACH (TAB 02)</button>")

# In Meal Planner action ribbon/buttons:
html = html.replace("switchTab('reports')\" style=\"background: #ea580c; color: white;\">📊 VIEW IN 8-LANE CHART (TAB 04)</button>",
                    "switchTab('metabolic')\" style=\"background: #d97706; color: white;\">👉 VIEW METABOLIC RESPONSE (TAB 03)</button>\n                  <button type=\"button\" class=\"lcars-btn\" onclick=\"switchTab('reports')\" style=\"background: #ea580c; color: white;\">📊 VIEW IN 8-LANE CHART (TAB 04)</button>")

# Check div tag balance
o = len(re.findall(r'<div\b', html))
c = len(re.findall(r'</div\b', html))
print(f"Div balance: open={o}, close={c}, delta={o-c}")
assert o == c, f"Div tag mismatch: open={o}, close={c}"

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
print(f"[+] Exact dual-file parity: {h1 == h2} ({h1})")
