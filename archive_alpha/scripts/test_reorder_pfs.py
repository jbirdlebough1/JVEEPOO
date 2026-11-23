with open("index.html", "r", encoding="utf-8") as f:
    html = f.read()

# Fix unclosed div first
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

pfs_start_idx = fixed_html.find("<!-- =====================================================================\n           TAB 12: SEQUENTIAL COMPREHENSIVE BIOMETRIC FULL SCAN")
goals_start_idx = fixed_html.find("<!-- =====================================================================\n         TAB 13: CLINICAL DIABETIC GOALS")

print(f"pfs_start_idx: {pfs_start_idx}, goals_start_idx: {goals_start_idx}")

pfs_full_block = fixed_html[pfs_start_idx:goals_start_idx].strip()

# Remove pfs_full_block from its old location
fixed_html_without_pfs = fixed_html[:pfs_start_idx] + fixed_html[goals_start_idx:]

# Insert before pageVitals
vitals_comment = "<!-- =====================================================================\n           TAB 1: CORE VITALS"
vitals_idx = fixed_html_without_pfs.find(vitals_comment)
print("vitals_idx:", vitals_idx)

new_html = (
    fixed_html_without_pfs[:vitals_idx] +
    pfs_full_block + "\n\n      " +
    fixed_html_without_pfs[vitals_idx:]
)

# Test tag balance of new_html
import re
stack = []
lines = new_html.splitlines()
mismatches = 0
for i, line in enumerate(lines):
    line_num = i + 1
    tags = re.findall(r'(<(?:div|main)[^>]*>|<!--.*?-->|</(?:div|main)>)', line)
    for tag in tags:
        if tag.startswith('<!--'):
            continue
        if tag.startswith('</'):
            tag_name = tag[2:-1].strip()
            if stack:
                last_tag, last_line, last_src = stack.pop()
                if last_tag != tag_name:
                    print(f"Mismatch at line {line_num}: closed </{tag_name}>, but opened <{last_tag}> at line {last_line}")
                    mismatches += 1
            else:
                print(f"Extra closing </{tag_name}> at line {line_num}")
                mismatches += 1
        else:
            tag_name = 'main' if tag.startswith('<main') else 'div'
            stack.append((tag_name, line_num, tag))

print(f"Total mismatches: {mismatches}, Unclosed elements at EOF: {len(stack)}")
