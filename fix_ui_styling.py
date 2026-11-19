print("[*] Fixing ribbon contrast and slot buttons width: auto...")

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. Fix ribbon values and labels contrast
old_ribbon_val = """.ribbon-value {
      font-size: 1.15rem;
      font-weight: 700;
      color: var(--lcars-text);
      font-family: var(--lcars-mono);
      margin-top: 2px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
    }"""

new_ribbon_val = """.ribbon-label {
      font-size: 0.75rem;
      color: #94a3b8 !important;
      letter-spacing: 0.1em;
      font-weight: 700;
    }

    .ribbon-value {
      font-size: 1.15rem;
      font-weight: 700;
      color: #ffffff !important;
      font-family: var(--lcars-mono);
      margin-top: 2px;
      white-space: nowrap;
      overflow: hidden;
      text-overflow: ellipsis;
      text-shadow: 0 0 4px rgba(255,255,255,0.3);
    }"""

if old_ribbon_val in html:
    html = html.replace(old_ribbon_val, new_ribbon_val)
    print("[+] Fixed ribbon-value and ribbon-label contrast!")
else:
    print("[!] Warning: old_ribbon_val not matched")

# 2. Fix slot buttons in Tab 10 to have width: auto !important;
old_slot_toolbar = """              <div style="display: flex; gap: 6px; flex-wrap: wrap; align-items: center;">
                <span style="font-size: 0.75rem; color: var(--lcars-banana); font-weight: bold; text-transform: uppercase;">SELECT / OVERWRITE SLOT:</span>
                <button class="lcars-btn btn-banana" style="font-size: 0.78rem; padding: 5px 12px; font-weight: bold; cursor: pointer;" onclick="applyWatchFace(0, 'SLOT 0: CLEAN DEFAULT ROM')">
                  ⚡ SLOT 0 (DEFAULT)
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(1, 'SLOT 1: SPORT ANALOG')">
                  <span>SLOT 1</span>
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(2, 'SLOT 2: BIG DIGITAL')">
                  <span>SLOT 2</span>
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(3, 'SLOT 3: CHRONOMETER')">
                  <span>SLOT 3</span>
                </button>
                <button class="lcars-pill pill-blue" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(4, 'SLOT 4: HEALTH MATRIX')">
                  <span>SLOT 4</span>
                </button>
                <button class="lcars-pill pill-orange" style="font-size: 0.78rem; padding: 5px 12px; height: auto; cursor: pointer;" onclick="overwriteDynamicWatchSlot(1)" title="Activate Dynamic Custom Slot on Watch">
                  <span>⚡ OVERWRITE CUSTOM SLOT</span>
                </button>
                <button class="lcars-pill pill-lilac" style="font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="queryCurrentWatchFace()">
                  <span>🔍 AUDIT SLOTS</span>
                </button>
              </div>"""

new_slot_toolbar = """              <div style="display: flex; gap: 6px; flex-wrap: wrap; align-items: center;">
                <span style="font-size: 0.75rem; color: var(--lcars-banana); font-weight: bold; text-transform: uppercase;">SELECT / OVERWRITE SLOT:</span>
                <button class="lcars-btn btn-banana" style="width: auto !important; font-size: 0.78rem; padding: 5px 12px; font-weight: bold; cursor: pointer;" onclick="applyWatchFace(0, 'SLOT 0: CLEAN DEFAULT ROM')">
                  ⚡ SLOT 0 (DEFAULT)
                </button>
                <button class="lcars-pill pill-blue" style="width: auto !important; font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(1, 'SLOT 1: SPORT ANALOG')">
                  <span>SLOT 1</span>
                </button>
                <button class="lcars-pill pill-blue" style="width: auto !important; font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(2, 'SLOT 2: BIG DIGITAL')">
                  <span>SLOT 2</span>
                </button>
                <button class="lcars-pill pill-blue" style="width: auto !important; font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(3, 'SLOT 3: CHRONOMETER')">
                  <span>SLOT 3</span>
                </button>
                <button class="lcars-pill pill-blue" style="width: auto !important; font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="applyWatchFace(4, 'SLOT 4: HEALTH MATRIX')">
                  <span>SLOT 4</span>
                </button>
                <button class="lcars-pill pill-orange" style="width: auto !important; font-size: 0.78rem; padding: 5px 12px; height: auto; cursor: pointer;" onclick="overwriteDynamicWatchSlot(1)" title="Activate Dynamic Custom Slot on Watch">
                  <span>⚡ OVERWRITE CUSTOM SLOT</span>
                </button>
                <button class="lcars-pill pill-lilac" style="width: auto !important; font-size: 0.78rem; padding: 5px 10px; height: auto; cursor: pointer;" onclick="queryCurrentWatchFace()">
                  <span>🔍 AUDIT SLOTS</span>
                </button>
              </div>"""

if old_slot_toolbar in html:
    html = html.replace(old_slot_toolbar, new_slot_toolbar)
    print("[+] Fixed slot buttons width: auto !important!")
else:
    print("[!] Warning: old_slot_toolbar not matched")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[✔] Updates applied successfully!")
