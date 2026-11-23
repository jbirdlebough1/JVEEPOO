import os
import shutil
import hashlib

index_path = "D:/veepoo/index.html"
with open(index_path, "r", encoding="utf-8") as f:
    html = f.read()

# 1. Update Master Capability Matrix
old_matrix_row = '''                    <tr style="border-bottom: 1px solid #e2e8f0;">
                      <td style="padding: 10px; font-weight: bold; background: #f8fafc; border-right: 1.5px solid #cbd5e1;">Dietary Tracking &amp; ADA Trades</td>
                      <td style="padding: 10px; color: #64748b; border-right: 1.5px solid #cbd5e1;">No nutrition or meal tracking whatsoever.</td>
                      <td style="padding: 10px; color: #0f172a; border-right: 1.5px solid #cbd5e1;"><strong style="color: #15803d;">MACRONUTRIENT MEAL LOGGER &amp; ADA 1-TO-1 TRADES ENGINE.</strong> Logs net carbs, calories, protein, and fat. Recommends direct carbohydrate-sparing dietary substitutions that blunt postprandial glucose excursions.</td>
                      <td style="padding: 10px; text-align: center; font-weight: bold; color: #15803d;">Nutritional Self-Mastery</td>
                    </tr>'''

new_matrix_row = '''                    <tr style="border-bottom: 1px solid #e2e8f0;">
                      <td style="padding: 10px; font-weight: bold; background: #f8fafc; border-right: 1.5px solid #cbd5e1;">Dietary Tracking &amp; ADA Trades</td>
                      <td style="padding: 10px; color: #64748b; border-right: 1.5px solid #cbd5e1;">No nutrition or meal tracking whatsoever.</td>
                      <td style="padding: 10px; color: #0f172a; border-right: 1.5px solid #cbd5e1;"><strong style="color: #15803d;">MULTI-MODAL INTAKE &amp; COMPOUND SUM ENGINE (TAB 02).</strong> Accepts Photo + Verbal + Text via Mobile (<code>/cell</code>) or PC (&plusmn;5% accuracy). Accurately sums composite meals (e.g. Costco Hot Dog + saved pantry snack tea), tracks fluid volume, and computes Recipe BOM batch materials vs. recommended diabetic single-portion sizing with ADA 1-to-1 trades.</td>
                      <td style="padding: 10px; text-align: center; font-weight: bold; color: #15803d;">Nutritional Self-Mastery</td>
                    </tr>'''

if old_matrix_row in html:
    html = html.replace(old_matrix_row, new_matrix_row, 1)
    print("Updated Master Capability Matrix in index.html")
else:
    print("Warning: old_matrix_row not found in index.html")

# 2. Add Component 06 in Tab 06 Doctor Mentoring (pageLearning)
target_comp5_end = '''              </div>
            </div>
          </div>'''

new_comp6 = '''<!-- COMPONENT 6: MULTI-MODAL INTAKE & COMPOUND ESTIMATOR CLINICAL SPECIFICATION (CLE-081) -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-banana); background: #ffffff !important; color: #0f172a !important; margin-top: 14px; box-shadow: 0 4px 12px rgba(0,0,0,0.06);">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px; border-bottom: 2px solid #cbd5e1; padding-bottom: 8px;">
              <div>
                <span style="color: #0f172a; font-size: 1.15rem; font-weight: 800;">06. MULTI-MODAL INTAKE ESTIMATOR &amp; COMPOUND SUM PROTOCOL</span>
                <span style="font-size: 0.82rem; color: #475569; display: block; margin-top: 2px;">TRI-MODAL RECOGNITION (PHOTO • VOICE • TEXT) &bull; RECIPE MATERIALS BOM &bull; PANTRY REUSE (&plusmn;5% CEILING)</span>
              </div>
              <span class="lcars-pill pill-gold" style="font-size: 0.78rem; padding: 4px 10px; height: auto; font-weight: 900;">ACCURACY CEILING &le; &plusmn;5.0%</span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(300px, 1fr)); gap: 12px; margin-top: 12px;">
              <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 12px;">
                <strong style="color: #0369a1; font-size: 0.90rem; display: block; margin-bottom: 6px;">📱 MULTI-MODAL TRI-ENTRY (PC &amp; MOBILE /CELL)</strong>
                <div style="font-size: 0.82rem; color: #334155; line-height: 1.45;">
                  Patients can record intake through any preferred modality: <strong>Camera Optical Recognition (OCR/Vision)</strong>, <strong>Voice Dictation</strong>, or <strong>Direct Text Typing</strong> on smartphone (<code>/cell</code>) or desktop PC. The engine cross-references online vendor disclosures (e.g. Costco Food Court, Bubba's Tavern) to compute exact macronutrient loads within <strong>&plusmn;5% accuracy</strong>.
                </div>
              </div>

              <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 12px;">
                <strong style="color: #0369a1; font-size: 0.90rem; display: block; margin-bottom: 6px;">🌭 COMPOSITE MEAL SUM DECOMPOSITION</strong>
                <div style="font-size: 0.82rem; color: #334155; line-height: 1.45;">
                  Compound entries (e.g. <code>hot dog foot long Costo, tea</code>) evaluate the <strong>SUM OF THE ENTIRE ENTRY</strong>. The engine decomposes the vendor food item (Costco All-Beef Frank &amp; Bun: 570 kcal, 44g net carbs) and automatically matches saved pantry beverages (Ceylon Cinnamon &amp; Clove Infusion: 2 kcal, 0.5g net carbs, 16 oz fluid) for a consolidated meal total (572 kcal, 44.5g net carbs).
                </div>
              </div>

              <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 12px;">
                <strong style="color: #0369a1; font-size: 0.90rem; display: block; margin-bottom: 6px;">👩‍🍳 RECIPE MATERIALS (BOM) &amp; PORTION CONTROL</strong>
                <div style="font-size: 0.82rem; color: #334155; line-height: 1.45;">
                  For home-cooked recipes, patients enter total batch materials (e.g. 2 cups almond flour, greek yogurt, eggs, blueberries, allulose). The engine calculates the <strong>Total Recipe Materials (BOM)</strong> and provides a <strong>Diabetic Portion Recommendation</strong> (e.g. 1 of 8 slices: 174 kcal, 5.5g net carbs) with maximum safe intake limits before crossing clinical glycemic ceilings.
                </div>
              </div>

              <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 6px; padding: 12px;">
                <strong style="color: #0369a1; font-size: 0.90rem; display: block; margin-bottom: 6px;">🍵 CONTINUOUS FLUID &amp; HYDRATION LOGGING</strong>
                <div style="font-size: 0.82rem; color: #334155; line-height: 1.45;">
                  Tracks cumulative fluid ounces (<code>fl oz</code>) throughout the day across vendor refreshments (e.g. Diet Coke 24oz: 0 kcal, 24 oz hydration) and non-vendor custom infusions (black+green tea bags, cloves, cinnamon, nutmeg, water volume), ensuring hydration and insulin sensitivity without postprandial glycemic penalties.
                </div>
              </div>
            </div>
          </div>'''

# Search for the exact end of component 5 in pageLearning
marker = "ISO 15197 &amp; CLSI EP05-A3 PROTOCOL"
if marker in html and "CLE-081" not in html:
    idx_marker = html.find(marker)
    # find the closing </div> of component 5 after this marker
    # The end of component 5 is the third </div> after marker
    end_c5 = html.find("<!-- Section 3: Patient Quick 3-Step Guide -->", idx_marker)
    if end_c5 != -1:
        # insert right before Section 3
        html = html[:end_c5] + new_comp6 + "\n\n        " + html[end_c5:]
        print("Inserted Component 06 into pageLearning")

with open(index_path, "w", encoding="utf-8") as f:
    f.write(html)

shutil.copy2(index_path, "D:/veepoo/w11_htm_app/index.html")
print("Mirrored index.html to w11_htm_app/index.html")

sha1 = hashlib.sha256(open("D:/veepoo/index.html", "rb").read()).hexdigest()
sha2 = hashlib.sha256(open("D:/veepoo/w11_htm_app/index.html", "rb").read()).hexdigest()
assert sha1 == sha2, f"SHA mismatch! {sha1} != {sha2}"
print(f"Parity confirmed: {sha1}")
