with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# 1. Update Sidebar Nav
p1 = text.find('<nav class="lcars-sidebar">')
assert p1 != -1, "nav not found"
p2 = text.find('</nav>', p1) + len('</nav>')

new_sidebar = '''<nav class="lcars-sidebar">
      <button id="navTabFullScan" class="lcars-pill pill-blue active" onclick="switchTab('fullscan')">
        <span class="tab-label">01-FULL SCAN</span>
      </button>
      <button id="navTabCardio" class="lcars-pill pill-orange" onclick="switchTab('cardio')">
        <span class="tab-label">02-CARDIO</span>
      </button>
      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span class="tab-label">03-METABOLIC</span>
      </button>
      <button id="navTabExport" class="lcars-pill pill-lilac" onclick="switchTab('export')" style="border-left: 5px solid #38bdf8;">
        <span class="tab-label">04-EXPORT</span>
      </button>
      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">05-ADA TRADES</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">06-REPORTS</span>
      </button>
      <button id="navTabLearning" class="lcars-pill pill-ice" onclick="switchTab('learning')" style="border-left: 5px solid #0284c7;">
        <span class="tab-label">07-LEARNING</span>
      </button>
      <button id="navTabHelp" class="lcars-pill pill-banana" onclick="switchTab('help')">
        <span class="tab-label">08-HELP &amp; FAQ</span>
      </button>
      <button id="navTabMedication" class="lcars-pill pill-orange" onclick="switchTab('medication')">
        <span class="tab-label">09-MEDICATION</span>
      </button>
      <button id="navTabGoals" class="lcars-pill pill-green" onclick="switchTab('goals')">
        <span class="tab-label">10-CLINICAL GOALS</span>
      </button>
      <button id="navTabSetup" class="lcars-pill pill-lilac" onclick="switchTab('setup')">
        <span class="tab-label">11-CONFIG</span>
      </button>
      <button id="navTabOperations" class="lcars-pill pill-peach" onclick="switchTab('operations')">
        <span class="tab-label">12-OPERATIONS</span>
      </button>
      <button id="navTabLog" class="lcars-pill pill-blue" onclick="switchTab('log')">
        <span class="tab-label">13-DATA LOG</span>
      </button>
      <button id="navTabWatchFaces" class="lcars-pill pill-banana" onclick="switchTab('watchfaces')">
        <span class="tab-label">14-WATCH FACES</span>
      </button>
    </nav>'''

text = text[:p1] + new_sidebar + text[p2:]
print("Replaced sidebar!")

# 2. Remove btnSkipFullScan from Full Scan Action Bar
old_action_bar = '''            <!-- Sequence Action Bar -->
            <div style="display: flex; gap: 10px; margin-top: 6px; flex-wrap: wrap;">
              <button id="btnStartFullScan" class="lcars-btn btn-green" style="flex: 2; min-height: 44px; font-size: 1.05rem; font-weight: bold; letter-spacing: 0.05em;" onclick="startFullScanSequence()">
                ▶ START FULL SCAN SEQUENCE (ALL 6 SENSORS)
              </button>
              <button id="btnCancelFullScan" class="lcars-btn btn-orange" style="flex: 1; min-height: 44px; font-size: 0.95rem;" onclick="cancelFullScanSequence()" disabled>
                ✕ CANCEL SEQUENCE
              </button>
              <button id="btnSkipFullScan" class="lcars-btn btn-blue" style="flex: 1; min-height: 44px; font-size: 0.95rem;" onclick="skipCurrentFullScanStep()" disabled>
                ⏭ SKIP TO NEXT
              </button>
            </div>'''

new_action_bar = '''            <!-- Sequence Action Bar -->
            <div style="display: flex; gap: 10px; margin-top: 6px; flex-wrap: wrap;">
              <button id="btnStartFullScan" class="lcars-btn btn-green" style="flex: 2; min-height: 44px; font-size: 1.05rem; font-weight: bold; letter-spacing: 0.05em;" onclick="startFullScanSequence()">
                ▶ START FULL SCAN SEQUENCE (ALL 6 SENSORS)
              </button>
              <button id="btnCancelFullScan" class="lcars-btn btn-orange" style="flex: 1; min-height: 44px; font-size: 0.95rem;" onclick="cancelFullScanSequence()" disabled>
                ✕ CANCEL SEQUENCE
              </button>
            </div>'''

assert old_action_bar in text, "old_action_bar not found"
text = text.replace(old_action_bar, new_action_bar, 1)
print("Removed btnSkipFullScan!")

# 3. Remove "↻ RUN ANOTHER FULL SCAN" button from #cardFullScanReport
old_run_another = '''            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 12px; flex-wrap: wrap; gap: 8px;">
              <div style="font-size: 0.8rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">
                All 6 sensors disengaged successfully. Watch hardware returned to idle display.
              </div>
              <button class="lcars-btn btn-banana" style="flex: none; padding: 6px 16px; font-weight: bold;" onclick="startFullScanSequence()">
                ↻ RUN ANOTHER FULL SCAN
              </button>
            </div>'''

new_run_another = '''            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 12px; flex-wrap: wrap; gap: 8px;">
              <div style="font-size: 0.8rem; color: var(--lcars-gray); font-family: var(--lcars-mono);">
                All 6 sensors disengaged successfully. Watch hardware returned to idle display.
              </div>
            </div>'''

assert old_run_another in text, "old_run_another not found"
text = text.replace(old_run_another, new_run_another, 1)
print("Removed RUN ANOTHER FULL SCAN button!")

# 4. Remove pageVitals screen and replace with Telemetry DOM Bridge
start_pv = text.find('id="pageVitals"')
assert start_pv != -1, "pageVitals not found"
start_div_pv = text.rfind('<div', 0, start_pv)

end_pv = text.find('id="pageCardio"')
assert end_pv != -1, "pageCardio not found"
end_div_pv = text.rfind('</div>', 0, end_pv) + len('</div>')

vitals_bridge = '''<!-- Telemetry DOM Bridge (Maintains backward compatibility for sensor IDs without duplicate UI screens) -->
      <div id="vitalsDomBridge" style="display: none;">
        <span id="valGlucose">--</span>
        <span id="badgeGlucose">IDLE</span>
        <span id="unitGlucose">mg/dL</span>
        <span id="valBP">-- / --</span>
        <span id="badgeBP">IDLE</span>
        <span id="unitBP">mmHg</span>
        <span id="valTemp">--.-</span>
        <span id="badgeTemp">IDLE</span>
        <span id="unitTemp">°F</span>
        <span id="valSpO2">--</span>
        <span id="badgeSpO2">IDLE</span>
        <span id="unitSpO2">%</span>
        <span id="txtTempCore">Core: --.-°F</span>
        <span id="txtTempSkin">Skin: --.-°F</span>
        <span id="barGlucose"></span>
        <span id="barTemp"></span>
        <span id="barBP"></span>
        <span id="barSpO2"></span>
        <span id="txtGlucoseProgress">0%</span>
        <span id="txtGlucoseRisk">NORMAL HOMEOSTASIS (60–200 MG/DL)</span>
        <span id="txtBPStatus">STANDBY</span>
        <span id="lblGlucoseCalStatus">PRIVATE MODE ACTIVE</span>
        <span id="lblRawGlucoseVal">--</span>
      </div>\n\n'''

text = text[:start_div_pv] + vitals_bridge + text[end_div_pv:]
print("Replaced pageVitals with vitalsDomBridge!")

# 5. In switchTab, route 'vitals' directly to 'fullscan'
old_switch_vitals = '''      if (tabId === 'vitals') {
        document.getElementById('navTabVitals').classList.add('active');
        document.getElementById('pageVitals').classList.add('active-page');
      } else if (tabId === 'cardio') {'''

new_switch_vitals = '''      if (tabId === 'vitals') {
        switchTab('fullscan');
        return;
      } else if (tabId === 'cardio') {'''

assert old_switch_vitals in text, "old_switch_vitals not found"
text = text.replace(old_switch_vitals, new_switch_vitals, 1)
print("Updated switchTab vitals routing!")

# 6. Guard any remaining btnSkipFullScan references in JS
text = text.replace("document.getElementById('btnSkipFullScan').disabled = false;", "const bSkip = document.getElementById('btnSkipFullScan'); if (bSkip) bSkip.disabled = false;")
text = text.replace("document.getElementById('btnSkipFullScan').disabled = true;", "const bSkip = document.getElementById('btnSkipFullScan'); if (bSkip) bSkip.disabled = true;")

# Save both index.html files
with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

print("Duplicates removed and files synchronized successfully!")
