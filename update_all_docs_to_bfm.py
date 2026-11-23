import re
import os
import shutil
import hashlib

print("=== STARTING BFM (BIOFUNCTION MONITOR) & DOCS MIGRATION ===")

# =========================================================================
# 1. UPDATE PRESENTATION/TRANSCRIPT.MD
# =========================================================================
transcript_path = "D:/veepoo/presentation/TRANSCRIPT.md"
with open(transcript_path, "r", encoding="utf-8") as f:
    text = f.read()

# Update Title
text = text.replace(
    "# LCARS 5010 Clinical Ambulatory Telemetry Onboarding - Complete Voice Transcript",
    "# BFM 5010 Clinical Biofunction Monitor Onboarding - Complete Voice Transcript"
)

# Update Slide 1 Voice Intro
old_slide1_intro = "G'day mate! Welcome to the El-CARS 5010 Clinical Ambulatory Telemetry Onboarding presentation."
new_slide1_intro = "G'day mate! Welcome to the BFM 5010 Clinical Biofunction Monitor onboarding presentation, inspired by the clean clinical vision of Gene Roddenberry and Scott Chambliss, and the life-saving telemetry heritage of Physio-Control."

text = text.replace(old_slide1_intro, new_slide1_intro)

# Systematic replacements in TRANSCRIPT.md
# Replace "El-CARS" with "the BFM", "LCARS" with "BFM"
text = re.sub(r'\bEl-CARS\b', 'BFM', text)
text = re.sub(r'\bLCARS\b', 'BFM', text)

# Add Dedication to Slide 1
dedication_points = """- **Heritage & Dedication:** Inspired by Gene Roddenberry's humanistic vision and Scott Chambliss's pristine modern Starfleet Sickbay aesthetic, uniting the USS Gemini mothership with Shuttlecraft Foodoor.
- **Physio-Control Lifepak Pedigree:** Rooted in real-world emergency triage telemetry, closing the critical diagnostic gaps to save diabetic lives.
- **An Author's Personal Struggle:**"""

text = text.replace("- **An Author's Personal Struggle:**", dedication_points, 1)

with open(transcript_path, "w", encoding="utf-8") as f:
    f.write(text)
print("[presentation/TRANSCRIPT.md] Successfully updated to BFM and added Roddenberry/Chambliss/Physio-Control heritage")


# =========================================================================
# 2. UPDATE INDEX.HTML (AND MIRROR TO W11_HTM_APP/INDEX.HTML)
# =========================================================================
index_path = "D:/veepoo/index.html"
with open(index_path, "r", encoding="utf-8") as f:
    html = f.read()

# A. Header Brand Title
html = re.sub(r'VEEPOO LCARS 5010', 'BFM 5010 BIOFUNCTION MONITOR', html)
html = re.sub(r'<title>.*?LCARS.*?</title>', '<title>BFM 5010 Biofunction Monitor • USS Gemini</title>', html)

# B. Add Dedication Banner into Tab 01 (pageFullScan) right at the top
target_fullscan_top = '''      <div id="pageFullScan" class="tab-page active-page">
        <div class="diet-container">'''

dedication_banner = '''      <div id="pageFullScan" class="tab-page active-page">
        <div class="diet-container">

          <!-- BFM HERITAGE & DEDICATION BANNER -->
          <div style="background: linear-gradient(135deg, #f8fafc 0%, #eff6ff 100%); border: 1.5px solid #bfdbfe; border-left: 6px solid #0284c7; border-radius: 8px; padding: 12px 16px; margin-bottom: 12px; box-shadow: 0 2px 8px rgba(0,0,0,0.04);">
            <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div style="display: flex; align-items: center; gap: 10px;">
                <span style="font-size: 1.4rem;">🚀</span>
                <div>
                  <div style="font-size: 0.92rem; font-weight: 800; color: #0c4a6e; letter-spacing: 0.04em; font-family: var(--lcars-mono);">
                    BFM 5010 BIOFUNCTION MONITOR • USS GEMINI (NCC-5010-G)
                  </div>
                  <div style="font-size: 0.78rem; color: #0369a1; margin-top: 1px;">
                    Dedicated to <strong>Gene Roddenberry</strong> &amp; <strong>Scott Chambliss</strong> • Informed by <strong>Physio-Control LIFEPAK</strong> Triage Telemetry
                  </div>
                </div>
              </div>
              <div style="display: flex; gap: 6px; align-items: center;">
                <span class="lcars-pill pill-blue" style="font-size: 0.72rem; padding: 3px 8px; height: auto; font-weight: bold;">MOTHERSHIP: ONLINE</span>
                <span class="lcars-pill pill-green" style="font-size: 0.72rem; padding: 3px 8px; height: auto; font-weight: bold;">SHUTTLE: FOODOOR READY</span>
              </div>
            </div>
          </div>'''

if target_fullscan_top in html and "BFM HERITAGE & DEDICATION BANNER" not in html:
    html = html.replace(target_fullscan_top, dedication_banner, 1)
    print("[index.html] Inserted BFM Heritage & Dedication Banner in Tab 01")

# C. Update user-facing strings in Operations & Doctor Mentoring
html = html.replace("LCARS 5010 Clinical Ambulatory Workstation", "BFM 5010 Clinical Biofunction Monitor Workstation")
html = html.replace("LCARS 5010 CLINICAL WORKSTATION", "BFM 5010 BIOFUNCTION MONITOR")
html = html.replace("LCARS 5010 workstation", "BFM 5010 Biofunction Monitor")

with open(index_path, "w", encoding="utf-8") as f:
    f.write(html)
print("[index.html] Saved index.html")

shutil.copy2(index_path, "D:/veepoo/w11_htm_app/index.html")
print("Mirrored index.html to w11_htm_app/index.html")


# =========================================================================
# 3. UPDATE CELL.HTML
# =========================================================================
cell_path = "D:/veepoo/w11_htm_app/cell.html"
with open(cell_path, "r", encoding="utf-8") as f:
    cell_html = f.read()

cell_html = re.sub(r'<title>.*?</title>', '<title>BFM Shuttlecraft 01: Foodoor • Mobile Intake Scout</title>', cell_html)
old_cell_title = 'LCARS MOBILE INTAKE GATEWAY'
new_cell_title = '🛸 BFM SHUTTLECRAFT 01: FOODOOR'

if old_cell_title in cell_html:
    cell_html = cell_html.replace(old_cell_title, new_cell_title)

# Update subtitle
old_cell_sub = 'Direct Ambulatory Intake Bridge &bull; Port 8899'
new_cell_sub = 'Away-Mission Food Recon &bull; Mothership: USS Gemini'

if old_cell_sub in cell_html:
    cell_html = cell_html.replace(old_cell_sub, new_cell_sub)

with open(cell_path, "w", encoding="utf-8") as f:
    f.write(cell_html)
print("[cell.html] Saved cell.html with Shuttlecraft 01: Foodoor branding")

# Parity check
sha1 = hashlib.sha256(open("D:/veepoo/index.html", "rb").read()).hexdigest()
sha2 = hashlib.sha256(open("D:/veepoo/w11_htm_app/index.html", "rb").read()).hexdigest()
assert sha1 == sha2, f"SHA mismatch! {sha1} != {sha2}"
print(f"Dual-file mirror parity confirmed: {sha1}")
