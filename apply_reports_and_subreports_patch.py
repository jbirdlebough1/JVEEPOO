import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

sub1_target = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">TOTAL ENERGY (TDEE)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #c2410c; font-family: var(--lcars-mono);">2,340 <span style="font-size: 0.8rem;">kcal/day</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">+660 Active Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SUBSTRATE RATIO (RER)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">0.82</div>
                <div style="font-size: 0.7rem; color: #0f172a;">60% Lipid / 40% Carb Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">GLUT4 CLEARANCE VELOCITY</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">+280%</div>
                <div style="font-size: 0.7rem; color: #15803d;">Post-meal walk acceleration</div>
              </div>"""

sub1_replacement = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">TOTAL ENERGY (TDEE)</div>
                <div id="subValTDEE" style="font-size: 1.25rem; font-weight: 900; color: #c2410c; font-family: var(--lcars-mono);">2,340 <span style="font-size: 0.8rem;">kcal/day</span></div>
                <div id="subValActiveBurn" style="font-size: 0.7rem; color: #15803d; font-weight: bold;">+660 Active Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SUBSTRATE RATIO (RER)</div>
                <div id="subValRER" style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">0.82</div>
                <div style="font-size: 0.7rem; color: #0f172a;">60% Lipid / 40% Carb Burn</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">GLUT4 CLEARANCE VELOCITY</div>
                <div id="subValGlut4" style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">+280%</div>
                <div style="font-size: 0.7rem; color: #15803d;">Post-meal walk acceleration</div>
              </div>"""

sub2_target = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SYMPATHOVAGAL (LF/HF)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">1.22</div>
                <div style="font-size: 0.7rem; color: #0f172a;">Target: 1.0 - 1.5</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CARDIAC RESILIENCE</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">88 / 100</div>
                <div style="font-size: 0.7rem; color: #15803d;">Healthy Vagal Tone (Comet)</div>
              </div>"""

sub2_replacement = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SYMPATHOVAGAL (LF/HF)</div>
                <div id="subValLfHf" style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">1.22</div>
                <div style="font-size: 0.7rem; color: #0f172a;">Target: 1.0 - 1.5</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">CARDIAC RESILIENCE</div>
                <div id="subValCardiacResilience" style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">88 / 100</div>
                <div style="font-size: 0.7rem; color: #15803d;">Healthy Vagal Tone (Comet)</div>
              </div>"""

sub3_target = """              <span class="badge" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 800; font-size: 0.78rem;">
                34.2 MET-HRS / DAY (EXCEEDS &ge; 28 GOAL)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING / SLEEP (1.0 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">7.6 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">7.6 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SEDENTARY (1.2 - 1.4 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #b45309; font-family: var(--lcars-mono);">9.2 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">11.9 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">MODERATE WALK (3.5 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">1.8 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">6.3 MET-hrs (GLUT4)</div>
              </div>"""

sub3_replacement = """              <span id="subValMetTotal" class="badge" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 800; font-size: 0.78rem;">
                34.2 MET-HRS / DAY (EXCEEDS &ge; 28 GOAL)
              </span>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(140px, 1fr)); gap: 10px; margin-top: 12px; text-align: center;">
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING / SLEEP (1.0 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0f172a; font-family: var(--lcars-mono);">7.6 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">7.6 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">SEDENTARY (1.2 - 1.4 MET)</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #b45309; font-family: var(--lcars-mono);">9.2 <span style="font-size: 0.8rem;">hrs</span></div>
                <div style="font-size: 0.7rem; color: #64748b;">11.9 MET-hrs</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">MODERATE WALK (3.5 MET)</div>
                <div id="subValMetWalk" style="font-size: 1.25rem; font-weight: 900; color: #15803d; font-family: var(--lcars-mono);">1.8 <span style="font-size: 0.8rem;">hrs</span></div>
                <div id="subValMetWalkVal" style="font-size: 0.7rem; color: #15803d; font-weight: bold;">6.3 MET-hrs (GLUT4)</div>
              </div>"""

sub6_target = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING RESPIRATION</div>
                <div style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">14 <span style="font-size: 0.8rem;">RPM</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">Optimal (12 - 18 RPM)</div>
              </div>"""

sub6_replacement = """              <div style="background: #f8fafc; border: 1px solid #cbd5e1; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.72rem; color: #64748b; font-weight: bold;">RESTING RESPIRATION</div>
                <div id="subValBreathRpm" style="font-size: 1.25rem; font-weight: 900; color: #0284c7; font-family: var(--lcars-mono);">14 <span style="font-size: 0.8rem;">RPM</span></div>
                <div style="font-size: 0.7rem; color: #15803d; font-weight: bold;">Optimal (12 - 18 RPM)</div>
              </div>"""

assert sub1_target in text, "sub1_target not found!"
assert sub2_target in text, "sub2_target not found!"
assert sub3_target in text, "sub3_target not found!"
assert sub6_target in text, "sub6_target not found!"

text = text.replace(sub1_target, sub1_replacement, 1)
text = text.replace(sub2_target, sub2_replacement, 1)
text = text.replace(sub3_target, sub3_replacement, 1)
text = text.replace(sub6_target, sub6_replacement, 1)

print("Sub-reports HTML target tags updated successfully.")

with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)
print("Saved both files.")
