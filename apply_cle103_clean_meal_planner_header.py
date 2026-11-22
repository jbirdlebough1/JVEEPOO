import re
import hashlib

print("=== APPLYING CLE-103: CLEAN MEAL PLANNER HEADER & REMOVE QUICK NAV ===")

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# 1. Target Header Block
target_header = '''          <div class="box-header" style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #1a2c1e; padding-bottom: 12px; margin-bottom: 18px;">
            <div>
              <span class="box-title" style="color: var(--lcars-green); font-size: 1.15rem; letter-spacing: 2px;">
                06-ADA DIET TRADES, CLINICAL RECIPES & PATIENT MENTORING ENGINE
              </span>
              <div style="font-size: 0.8rem; color: #80c890; margin-top: 3px;">
                AMERICAN DIABETES ASSOCIATION (ADA) STANDARDS OF CARE • CLOSED-LOOP PHYSICIAN & PATIENT COACHING
              </div>
            </div>
            <div style="display: flex; gap: 8px;">
              <span class="badge" style="background: rgba(85,255,119,0.15); color: var(--lcars-green); border: 1px solid var(--lcars-green); font-size: 0.75rem;">
                CLINICAL MENTORING
              </span>
              <span class="badge" style="background: rgba(56,182,255,0.15); color: var(--lcars-blue); border: 1px solid var(--lcars-blue); font-size: 0.75rem;">
                AIR-GAPPED PRIVACY
              </span>
            </div>
          </div>'''

replacement_header = '''          <div class="box-header" style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #1a2c1e; padding-bottom: 12px; margin-bottom: 18px;">
            <div>
              <span class="box-title" style="color: var(--lcars-green); font-size: 1.15rem; letter-spacing: 2px;">
                02-MEAL PLANNER: DIET TRADES, CLINICAL RECIPES &amp; PRE-CHOICE ESTIMATION
              </span>
              <div style="font-size: 0.8rem; color: #80c890; margin-top: 3px;">
                AMERICAN DIABETES ASSOCIATION (ADA) STANDARDS OF CARE &bull; PRE-CHOICE ESTIMATION &bull; RECIPE SOURCING
              </div>
            </div>
          </div>'''

assert target_header in html, "target_header not found in index.html"
html = html.replace(target_header, replacement_header, 1)
print("[+] Replaced 06-ADA header with 02-MEAL PLANNER and removed clinical mentoring & air gapped badges")

# 2. Target Quick Nav Block
target_quick_nav = '''            <!-- TOP LCARS WORKFLOW RIBBON (MINIMUM KEY & MOUSE STROKES) -->
            <div style="display: flex; gap: 8px; margin-bottom: 16px; flex-wrap: wrap; background: #0f172a; padding: 8px 12px; border-radius: 6px; border: 1.5px solid #334155; align-items: center;">
              <span style="font-size: 0.74rem; font-weight: 900; color: #38bdf8; font-family: var(--lcars-mono); margin-right: 4px;">QUICK NAV:</span>
              <button type="button" class="lcars-btn btn-blue" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionMealPantry')">
                📋 1. MEAL PANTRY (REUSE)
              </button>
              <button type="button" class="lcars-btn btn-green" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionNewMeal')">
                ➕ 2. NEW MEAL / SNACK
              </button>
              <button type="button" class="lcars-btn btn-banana" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('geminiMealAnalysisResult')">
                ✨ 3. GEMINI ESTIMATION
              </button>
              <button type="button" class="lcars-btn btn-peach" style="font-size: 0.78rem; padding: 5px 12px; font-weight: 800;" onclick="scrollToWorkflowSection('sectionAdaTrades')">
                ⚖️ 4. ADA TRADES
              </button>
              <button type="button" class="lcars-btn" style="background: #10b981; color: #ffffff; border: 1.5px solid #059669; font-size: 0.78rem; padding: 5px 14px; font-weight: 900;" onclick="submitActualMealAndMonitorMetrics()">
                🚀 5. CONSUME &amp; LOG
              </button>
            </div>'''

assert target_quick_nav in html, "target_quick_nav not found in index.html"
html = html.replace(target_quick_nav, '', 1)
print("[+] Removed redundant Quick Nav ribbon")

# Div tag balance check
open_divs = len(re.findall(r'<div\b', html))
close_divs = len(re.findall(r'</div\b', html))
print(f"Div balance: open={open_divs}, close={close_divs}, delta={open_divs - close_divs}")
assert open_divs == close_divs, f"Div balance mismatch: open={open_divs}, close={close_divs}"

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
print(f"[+] Exact dual-file parity: {h1 == h2} ({h1})")
print("=== CLE-103 PATCH APPLIED SUCCESSFULLY ===")
