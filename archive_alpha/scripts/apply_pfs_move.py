with open("index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. Fix unclosed div in pageDietTrades
target = """            <!-- FAQ ITEM: HOW LCARS HELPS DOCTORS & PATIENTS -->
            <div style="background: #ffffff; padding: 16px; border-radius: 8px; border: 2px solid #cbd5e1; border-left: 6px solid #15803d; margin-top: 14px;">
              <strong style="color: #0f172a; font-size: 1.05rem;">Q6: How does LCARS help my physician and me manage my diabetes better than isolated fingersticks?</strong>
              <div style="font-size: 0.88rem; color: #1e293b; line-height: 1.5; margin-top: 6px;">
                Isolated fingersticks only show a static number at a single moment in time without context. LCARS provides a <strong>360-Degree Personal Health Console</strong> that correlates 8 simultaneous telemetry streams (Glucose, BP, HR/HRV, SpO2, Skin Temp, Motion, Diet, and Medications).
                <br><br>
                When you or your doctor scrub through your daily or weekly timeline on Tab 04, you can click on any glucose spike or drop to immediately reveal the exact meal carbs consumed, the exercise completed (and its resulting &Delta;G drop), and your medication adherence. Furthermore, when your doctor adjusts a prescription, logging the titration pins a <strong>"💊 MED CHANGE"</strong> marker across your charts, allowing both of you to objectively determine whether the change prompted glycemic <strong>progress or regression</strong>.
              </div>
            </div>

</div>"""

replacement = """            <!-- FAQ ITEM: HOW LCARS HELPS DOCTORS & PATIENTS -->
            <div style="background: #ffffff; padding: 16px; border-radius: 8px; border: 2px solid #cbd5e1; border-left: 6px solid #15803d; margin-top: 14px;">
              <strong style="color: #0f172a; font-size: 1.05rem;">Q6: How does LCARS help my physician and me manage my diabetes better than isolated fingersticks?</strong>
              <div style="font-size: 0.88rem; color: #1e293b; line-height: 1.5; margin-top: 6px;">
                Isolated fingersticks only show a static number at a single moment in time without context. LCARS provides a <strong>360-Degree Personal Health Console</strong> that correlates 8 simultaneous telemetry streams (Glucose, BP, HR/HRV, SpO2, Skin Temp, Motion, Diet, and Medications).
                <br><br>
                When you or your doctor scrub through your daily or weekly timeline on Tab 04, you can click on any glucose spike or drop to immediately reveal the exact meal carbs consumed, the exercise completed (and its resulting &Delta;G drop), and your medication adherence. Furthermore, when your doctor adjusts a prescription, logging the titration pins a <strong>"💊 MED CHANGE"</strong> marker across your charts, allowing both of you to objectively determine whether the change prompted glycemic <strong>progress or regression</strong>.
              </div>
            </div>

        </div>
      </div>"""

fixed_html = html.replace(target, replacement, 1)

# 2. Extract pageFullScan block
pfs_start_idx = fixed_html.find("<!-- =====================================================================\n           TAB 12: SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCAN")
goals_start_idx = fixed_html.find("<!-- =====================================================================\n         TAB 13: CLINICAL DIABETIC GOALS")

pfs_full_block = fixed_html[pfs_start_idx:goals_start_idx].strip()
# Update comment and header to TAB 01
pfs_full_block = pfs_full_block.replace("TAB 12: SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCAN", "TAB 01: SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCAN")
pfs_full_block = pfs_full_block.replace("12. SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCAN", "01. SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCAN")

# Remove from old position
fixed_html_without_pfs = fixed_html[:pfs_start_idx] + fixed_html[goals_start_idx:]

# Insert before pageVitals
vitals_comment = "<!-- =====================================================================\n           TAB 1: CORE VITALS"
vitals_idx = fixed_html_without_pfs.find(vitals_comment)

new_html = (
    fixed_html_without_pfs[:vitals_idx] +
    pfs_full_block + "\n\n      " +
    fixed_html_without_pfs[vitals_idx:]
)

# Save to index.html
with open("index.html", "w", encoding="utf-8") as f:
    f.write(new_html)
print("Saved updated index.html")

# Save mirror to w11_htm_app/index.html
with open("w11_htm_app/index.html", "w", encoding="utf-8") as f:
    f.write(new_html)
print("Saved updated w11_htm_app/index.html")
