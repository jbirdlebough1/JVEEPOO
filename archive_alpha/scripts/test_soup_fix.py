# test_soup_fix.py
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# Replace the stray section
bad_block = """        </div>
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

good_block = """        </div>
      </div>"""

fixed_text = text.replace(bad_block, good_block)
print("Bad block found and replaced?", len(fixed_text) != len(text))

from bs4 import BeautifulSoup
soup = BeautifulSoup(fixed_text, 'html.parser')

tab_pages = soup.find_all('div', class_='tab-page')
print(f"Found {len(tab_pages)} elements with class 'tab-page':")
for p in tab_pages:
    pid = p.get('id', 'NO_ID')
    parents = []
    curr = p.parent
    while curr and curr.name != '[document]':
        cid = curr.get('id', '')
        ccls = '.'.join(curr.get('class', []))
        parents.append(f"{curr.name}#{cid}.{ccls}")
        curr = curr.parent
    print(f"  {pid:20} -> {' > '.join(parents)}")
