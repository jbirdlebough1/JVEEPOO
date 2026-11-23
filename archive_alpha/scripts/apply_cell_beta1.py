import re

CELL_PATH = r"D:\veepoo\w11_htm_app\cell.html"

with open(CELL_PATH, "r", encoding="utf-8") as f:
    content = f.read()

# 1. Update Title and Header
content = re.sub(
    r"<title>.*?</title>",
    "<title>BFM Shuttlecraft Foodoor • Auxiliary Intake Scout (Star Ship Gemini NCC-202X)</title>",
    content,
    flags=re.IGNORECASE
)

old_hdr = """  <!-- Mobile Top Header -->
  <div class="header">
    <div>
      <h1>📱 LCARS 5010 • MOBILE FOOD INTAKE</h1>
      <div class="sub">HOST PC:8899 &bull; DIRECT WI-FI CONNECTION</div>
    </div>
    <div id="wifiStatusPill" class="wifi-pill">
      <span>●</span> <span id="wifiStatusText">CHECKING LINK...</span>
    </div>
  </div>"""

new_hdr = """  <!-- Mobile Top Header -->
  <div class="header">
    <div>
      <h1>📱 SHUTTLECRAFT FOODOOR &bull; AUXILIARY SCOUT</h1>
      <div class="sub">STAR SHIP GEMINI NCC-202X &bull; CELL 1 &bull; <span id="lblMobileEsn">ESN-9482-A01</span></div>
    </div>
    <div id="wifiStatusPill" class="wifi-pill">
      <span>●</span> <span id="wifiStatusText">CHECKING LINK...</span>
    </div>
  </div>"""

content = content.replace(old_hdr, new_hdr)

# 2. Update syncAllStagedMeals to support /api/shuttle/dock batch sync
old_sync_fn = """    async function syncAllStagedMeals() {
      const queue = getStagedQueue();
      if (queue.length === 0) return;

      const btn = document.getElementById('btnSyncAllStaged');
      btn.disabled = true;
      btn.innerHTML = "<span>⏳ UPLOADING ALL STAGED MEALS TO PC...</span>";

      let uploadedCount = 0;
      for (const item of queue) {
        try {
          const payload = {
            meal_name: `[${item.meal_type}] ${item.meal_name}`,
            description: item.meal_name,
            source: "MOBILE_OFFLINE_SYNC",
            photo_data: item.photos.length > 0 ? item.photos[0] : "",
            photo_count: item.photo_count,
            audio_memo: item.audio_memo || "",
            commit: true,
            calories: item.calories,
            net_carbs: item.net_carbs,
            total_carbs: item.total_carbs,
            fiber: item.fiber,
            protein: item.protein,
            fat: item.fat,
            glycemic_load: item.glycemic_load,
            meets_goal: item.meets_goal,
            vendor_matched: item.vendor_matched,
            gemini_notes: item.gemini_notes,
            tips: item.tips,
            trade_alternatives: item.trade_alternatives,
            glut4_recommendation: item.glut4_recommendation
          };

          const resp = await fetch('/api/meal/upload', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
          });
          if (resp.ok) uploadedCount++;
        } catch (e) {
          console.warn("Failed uploading item", item, e);
        }
      }

      if (uploadedCount > 0) {
        saveStagedQueue([]); // Clear queue
        const succ = document.getElementById('lblSubmitSuccess');
        succ.style.display = 'block';
        succ.innerHTML = `🎉 <strong>ALL ${uploadedCount} MEALS SYNCHRONIZED TO PC WORKSTATION!</strong> Telemetry &amp; diet journal updated.`;
      }

      btn.disabled = false;
      btn.innerHTML = "<span>⚡ SYNC ALL STAGED MEALS TO PC</span>";
    }"""

new_sync_fn = """    // Device Interrogation & Registration Initialization
    function initMobileDeviceIdentity() {
      let esn = localStorage.getItem("bfm_phone_esn");
      let cellId = localStorage.getItem("bfm_cell_id") || "Cell 1";
      if (!esn) {
        esn = "ESN-9482-A01";
        localStorage.setItem("bfm_phone_esn", esn);
        localStorage.setItem("bfm_cell_id", cellId);
      }
      const esnLbl = document.getElementById('lblMobileEsn');
      if (esnLbl) esnLbl.textContent = `${cellId} • ${esn}`;
      return { esn, cellId };
    }

    async function syncAllStagedMeals() {
      const queue = getStagedQueue();
      if (queue.length === 0) return;

      const btn = document.getElementById('btnSyncAllStaged');
      if (btn) {
        btn.disabled = true;
        btn.innerHTML = "<span>🚀 DOCKING WITH STAR SHIP GEMINI NCC-202X...</span>";
      }

      const { esn, cellId } = initMobileDeviceIdentity();

      // Attempt atomic bulk docking to /api/shuttle/dock
      try {
        const batchRecords = queue.map(item => ({
          intake_timestamp: item.timestamp || new Date().toISOString(),
          meal_name: `[${item.meal_type}] ${item.meal_name}`,
          calories: item.analysis ? item.analysis.calories : item.calories,
          net_carbs: item.analysis ? item.analysis.net_carbs : item.net_carbs,
          total_carbs: item.analysis ? item.analysis.total_carbs : item.total_carbs,
          fiber: item.analysis ? item.analysis.fiber : item.fiber,
          protein: item.analysis ? item.analysis.protein : item.protein,
          fat: item.analysis ? item.analysis.fat : item.fat,
          glycemic_impact_score: item.analysis ? item.analysis.glycemic_load : item.glycemic_load,
          photo_base64: item.photos && item.photos.length > 0 ? item.photos[0] : "",
          audio_transcript: item.audio_memo || ""
        }));

        const dockPayload = {
          cell_id: cellId,
          phone_esn: esn,
          batch_records: batchRecords
        };

        const resp = await fetch('/api/shuttle/dock', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(dockPayload)
        });

        if (resp.ok) {
          const dres = await resp.json();
          saveStagedQueue([]); // Clear queue
          const succ = document.getElementById('lblSubmitSuccess');
          if (succ) {
            succ.style.display = 'block';
            succ.innerHTML = `🎉 <strong>HOMECOMING DOCK SUCCESS:</strong> ${dres.docked_records} field meals synchronized with Star Ship Gemini NCC-202X!`;
          }
          if (btn) {
            btn.disabled = false;
            btn.innerHTML = "<span>⚡ SYNC ALL STAGED MEALS TO PC</span>";
          }
          renderStagedQueue();
          return;
        }
      } catch (dockErr) {
        console.warn("Direct dock endpoint returned error, attempting fallback route...", dockErr);
      }

      // Fallback: 1-by-1 upload if dock API fails
      let uploadedCount = 0;
      for (const item of queue) {
        try {
          const payload = {
            meal_name: `[${item.meal_type}] ${item.meal_name}`,
            description: item.meal_name,
            source: "SHUTTLE_FALLBACK_SYNC",
            photo_data: item.photos && item.photos.length > 0 ? item.photos[0] : "",
            photo_count: item.photo_count || 0,
            audio_memo: item.audio_memo || "",
            commit: true,
            calories: item.calories,
            net_carbs: item.net_carbs,
            total_carbs: item.total_carbs,
            fiber: item.fiber,
            protein: item.protein,
            fat: item.fat,
            glycemic_load: item.glycemic_load,
            meets_goal: item.meets_goal
          };

          const resp = await fetch('/api/meal/upload', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
          });
          if (resp.ok) uploadedCount++;
        } catch (e) {
          console.warn("Failed uploading item", item, e);
        }
      }

      if (uploadedCount > 0) {
        saveStagedQueue([]);
        const succ = document.getElementById('lblSubmitSuccess');
        if (succ) {
          succ.style.display = 'block';
          succ.innerHTML = `🎉 <strong>ALL ${uploadedCount} MEALS SYNCHRONIZED TO PC WORKSTATION!</strong> Telemetry &amp; diet journal updated.`;
        }
      }

      if (btn) {
        btn.disabled = false;
        btn.innerHTML = "<span>⚡ SYNC ALL STAGED MEALS TO PC</span>";
      }
      renderStagedQueue();
    }"""

content = content.replace(old_sync_fn, new_sync_fn)

# Add initMobileDeviceIdentity call to Startup
content = content.replace(
    "initMealTypeByTime();",
    "initMobileDeviceIdentity();\n    initMealTypeByTime();"
)

# Check tag balance
open_divs = len(re.findall(r'<div\b', content, re.I))
close_divs = len(re.findall(r'</div\b', content, re.I))
print(f"cell.html div balance: Opens={open_divs}, Closes={close_divs}, Diff={open_divs - close_divs}")

if open_divs == close_divs:
    with open(CELL_PATH, "w", encoding="utf-8") as f:
        f.write(content)
    print("[✔] Successfully updated cell.html with perfect tag balance!")
else:
    print("[!] Div imbalance detected, refusing to write!")
