import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Replace the end of pageDietTrades with cleanly closing 4 divs
target = """            <!-- Products Dynamic Grid -->
            <div id="productsGrid" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 12px;">
              <!-- Rendered via JavaScript -->
            </div>
          </div>


        </div>
      </div>

        </div>
      
            <!-- FAQ ITEM: OEM APP PARITY & MIGRATION -->
            <div style="background: #ffffff; padding: 16px; border-radius: 8px; border: 2px solid #cbd5e1; border-left: 6px solid #2563eb; margin-top: 14px;">
              <strong style="color: #0f172a; font-size: 1.05rem;">Q5: How does LCARS compare to the official manufacturer mobile app? Do I lose any features?</strong>
              <div style="font-size: 0.88rem; color: #1e293b; line-height: 1.5; margin-top: 6px;">
                You lose <strong>zero features</strong>. LCARS implements <strong>100% of the underlying Bluetooth GATT opcodes</strong> present in the manufacturer firmware, including blood glucose, blood pressure, dual temperature, SpO2 apnea detection, heart rate variability, sleep stages, watch face customization, and screen settings.
                <br><br>
                However, unlike stock mobile apps, LCARS keeps all your clinical data strictly on your local PC with <strong>zero cloud egress</strong>, provides an <strong>in-clinic blood draw calibration protocol</strong>, features an <strong>8-lane synchronized timeline chart with a continuous vertical scrubber</strong>, and provides active metabolic guidance (exercise GLUT4 kinetics, diet trades, and medication tracking).
              </div>
            </div>

            <!-- FAQ ITEM: HOW LCARS HELPS DOCTORS & PATIENTS -->
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

replacement = """            <!-- Products Dynamic Grid -->
            <div id="productsGrid" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(290px, 1fr)); gap: 12px;">
              <!-- Rendered via JavaScript -->
            </div>
          </div>

        </div>
      </div>

    </div>
  </div>"""

sim_text = text.replace(target, replacement, 1)

# Now check nesting depth of all tab pages
lines = sim_text.splitlines()

tab_pages = []
for i, line in enumerate(lines):
    m = re.search(r'<div\s+id=[\'"]([^\'"]+)[\'"]\s+class=[\'"][^\'"]*tab-page[^\'"]*[\'"]', line)
    if not m:
        m = re.search(r'<div\s+class=[\'"][^\'"]*tab-page[^\'"]*[\'"]\s+id=[\'"]([^\'"]+)[\'"]', line)
    if m:
        tab_pages.append((i+1, m.group(1)))

print("--- NESTING DEPTH AT EACH TAB PAGE WITH FIX ---")
depth = 0
for i, line in enumerate(lines):
    if '<main' in line:
        print(f"Main starts at L{i+1}")
    if '</main>' in line:
        print(f"Main ends at L{i+1} (depth: {depth})")
    
    for t_line, t_id in tab_pages:
        if i + 1 == t_line:
            print(f"  Tab {t_id:16} at line {t_line} (depth: {depth})")
            
    open_divs = len(re.findall(r'<div[\s>]', line))
    close_divs = len(re.findall(r'</div>', line))
    depth += (open_divs - close_divs)

print(f"Final depth at end of file: {depth}")
