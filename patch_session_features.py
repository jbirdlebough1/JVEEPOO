import re

print("Starting patch application...")

# 1. Update cell.html
cell_path = r"D:\veepoo\w11_htm_app\cell.html"
with open(cell_path, "r", encoding="utf-8") as f:
    cell_html = f.read()

target_ui = """    <!-- Photo Thumbnail Preview Strip -->
    <div id="previewStrip" class="preview-strip" style="display: none;"></div>"""

replace_ui = """    <!-- Photo Thumbnail Preview Strip -->
    <div id="previewStrip" class="preview-strip" style="display: none;"></div>

    <!-- Voice Recording & Spoken Order Interface -->
    <div style="background: #f8fafc; border: 1.5px solid #0284c7; border-radius: 6px; padding: 10px; margin-bottom: 12px;">
      <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
        <span style="font-size: 0.75rem; font-weight: 800; color: #0284c7; text-transform: uppercase;">
          🎙️ VOICE MEAL RECORDER / DICTATION:
        </span>
        <span id="lblVoiceStatus" style="font-size: 0.72rem; color: #64748b; font-family: monospace;">TAP TO RECORD</span>
      </div>
      <button type="button" id="btnRecordVoice" class="btn" style="background: #0284c7; color: #ffffff; padding: 10px; margin-bottom: 6px; font-size: 0.88rem;" onclick="toggleVoiceRecording()">
        <span id="iconRecordVoice">🎙️</span> <span id="txtRecordVoice">START VOICE RECORDING (SPOKEN ORDER)</span>
      </button>
      <div id="voiceFeedbackBox" style="display: none; background: #e0f2fe; border: 1px dashed #0284c7; border-radius: 4px; padding: 6px 10px; font-size: 0.80rem; color: #0369a1;">
        <span style="font-weight: bold;">Listening:</span> Speak your meal (e.g. <em>"Dick's drivein 2 cheeze burgers, tarter, fry and diet 24oz"</em>)...
      </div>
      <div id="audioMemoPlayerBox" style="display: none; margin-top: 6px;">
        <audio id="audioMemoPlayer" controls style="width: 100%; height: 32px;"></audio>
      </div>
    </div>"""

if target_ui in cell_html and "VOICE MEAL RECORDER" not in cell_html:
    cell_html = cell_html.replace(target_ui, replace_ui, 1)
    print("Added voice recording UI to cell.html")

target_js = """    let uploadedPhotos = []; // Base64 array
    let currentAnalysis = null;
    let selectedMealType = 'Snack';
    let isConnectedToHost = false;"""

replace_js = """    let uploadedPhotos = []; // Base64 array
    let currentAnalysis = null;
    let selectedMealType = 'Snack';
    let isConnectedToHost = false;

    // Voice Recording Engine (SpeechRecognition & MediaRecorder)
    let isRecordingVoice = false;
    let speechRecognizer = null;
    let mediaRecorderInstance = null;
    let audioChunks = [];
    let recordedAudioBase64 = "";

    function initSpeechEngine() {
      const SpeechRecognition = window.SpeechRecognition || window.webkitSpeechRecognition;
      if (SpeechRecognition) {
        speechRecognizer = new SpeechRecognition();
        speechRecognizer.continuous = true;
        speechRecognizer.interimResults = true;
        speechRecognizer.lang = 'en-US';

        speechRecognizer.onresult = function(event) {
          let finalTranscript = '';
          for (let i = event.resultIndex; i < event.results.length; ++i) {
            if (event.results[i].isFinal) {
              finalTranscript += event.results[i][0].transcript;
            }
          }
          if (finalTranscript) {
            const input = document.getElementById('txtMealInput');
            if (input) {
              const prev = input.value.trim();
              if (prev && prev !== 'Clove and cinnamon tea') {
                input.value = prev + ' ' + finalTranscript.trim();
              } else {
                input.value = finalTranscript.trim();
              }
            }
          }
        };

        speechRecognizer.onerror = function(event) {
          console.warn('Speech recognition notice:', event.error);
        };

        speechRecognizer.onend = function() {
          if (isRecordingVoice) {
            stopVoiceRecording();
          }
        };
      }
    }

    async function toggleVoiceRecording() {
      if (isRecordingVoice) {
        stopVoiceRecording();
      } else {
        await startVoiceRecording();
      }
    }

    async function startVoiceRecording() {
      isRecordingVoice = true;
      audioChunks = [];
      const btn = document.getElementById('btnRecordVoice');
      const icon = document.getElementById('iconRecordVoice');
      const txt = document.getElementById('txtRecordVoice');
      const status = document.getElementById('lblVoiceStatus');
      const feedback = document.getElementById('voiceFeedbackBox');

      if (btn) btn.style.background = '#ef4444';
      if (icon) icon.textContent = '⏹️';
      if (txt) txt.textContent = 'STOP RECORDING & TRANSCRIBE';
      if (status) { status.textContent = '● RECORDING...'; status.style.color = '#ef4444'; }
      if (feedback) feedback.style.display = 'block';

      if (!speechRecognizer) initSpeechEngine();
      if (speechRecognizer) {
        try { speechRecognizer.start(); } catch (e) {}
      }

      if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
        try {
          const stream = await navigator.mediaDevices.getUserMedia({ audio: true });
          mediaRecorderInstance = new MediaRecorder(stream);
          mediaRecorderInstance.ondataavailable = e => {
            if (e.data.size > 0) audioChunks.push(e.data);
          };
          mediaRecorderInstance.onstop = () => {
            const audioBlob = new Blob(audioChunks, { type: 'audio/webm' });
            const reader = new FileReader();
            reader.onloadend = () => {
              recordedAudioBase64 = reader.result;
              const player = document.getElementById('audioMemoPlayer');
              const box = document.getElementById('audioMemoPlayerBox');
              if (player && box) {
                player.src = recordedAudioBase64;
                box.style.display = 'block';
              }
            };
            reader.readAsDataURL(audioBlob);
            stream.getTracks().forEach(track => track.stop());
          };
          mediaRecorderInstance.start();
        } catch (err) {
          console.warn('Audio capture optional:', err);
        }
      }
    }

    function stopVoiceRecording() {
      isRecordingVoice = false;
      const btn = document.getElementById('btnRecordVoice');
      const icon = document.getElementById('iconRecordVoice');
      const txt = document.getElementById('txtRecordVoice');
      const status = document.getElementById('lblVoiceStatus');
      const feedback = document.getElementById('voiceFeedbackBox');

      if (btn) btn.style.background = '#0284c7';
      if (icon) icon.textContent = '🎙️';
      if (txt) txt.textContent = 'START VOICE RECORDING (SPOKEN ORDER)';
      if (status) { status.textContent = 'RECORDED'; status.style.color = '#16a34a'; }
      if (feedback) feedback.style.display = 'none';

      if (speechRecognizer) {
        try { speechRecognizer.stop(); } catch (e) {}
      }
      if (mediaRecorderInstance && mediaRecorderInstance.state !== 'inactive') {
        try { mediaRecorderInstance.stop(); } catch (e) {}
      }
    }"""

if target_js in cell_html and "toggleVoiceRecording" not in cell_html:
    cell_html = cell_html.replace(target_js, replace_js, 1)
    print("Added voice recording JS to cell.html")

target_stage = """      const stagedItem = {
        id: "meal_" + Date.now(),
        meal_type: selectedMealType,
        meal_name: text || currentAnalysis.meal_name,
        timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
        photo_count: uploadedPhotos.length,
        photos: [...uploadedPhotos],
        analysis: currentAnalysis
      };"""

replace_stage = """      const stagedItem = {
        id: "meal_" + Date.now(),
        meal_type: selectedMealType,
        meal_name: text || currentAnalysis.meal_name,
        timestamp: new Date().toLocaleTimeString([], { hour: '2-digit', minute: '2-digit' }),
        photo_count: uploadedPhotos.length,
        photos: [...uploadedPhotos],
        audio_memo: recordedAudioBase64,
        analysis: currentAnalysis
      };"""

if target_stage in cell_html:
    cell_html = cell_html.replace(target_stage, replace_stage, 1)
    print("Updated stageMealForSync in cell.html")

with open(cell_path, "w", encoding="utf-8") as f:
    f.write(cell_html)
print("Updated cell.html successfully!")


# 2. Update index.html
index_path = r"D:\veepoo\index.html"
with open(index_path, "r", encoding="utf-8") as f:
    index_html = f.read()

# 2a. Label Meal Pantry
target_pantry_lbl = """                <label for="listboxPopularMeals" style="font-size: 0.82rem; font-weight: 800; color: #0f172a; text-transform: uppercase;">
                  📋 PREVIOUSLY CONSUMED &amp; POPULAR MEALS (SCROLL &amp; SELECT):
                </label>
                <span style="font-size: 0.72rem; color: #64748b; font-family: var(--lcars-mono);">
                  1-CLICK CHOICE &bull; AUTO-EVALUATES &bull; 3-CLICK PARADIGM
                </span>"""

replace_pantry_lbl = """                <label for="listboxPopularMeals" style="font-size: 0.82rem; font-weight: 800; color: #0f172a; text-transform: uppercase;">
                  📋 MEAL PANTRY (PREVIOUSLY CONSUMED &amp; SAVED MEALS — SCROLL &amp; SELECT):
                </label>
                <span style="font-size: 0.72rem; color: #64748b; font-family: var(--lcars-mono);">
                  MEAL PANTRY &bull; 1-CLICK SELECT &bull; GEMINI EVALUATION &bull; SUBMIT TO DB
                </span>"""

if target_pantry_lbl in index_html:
    index_html = index_html.replace(target_pantry_lbl, replace_pantry_lbl, 1)
    print("Updated Meal Pantry label in index.html")

# 2b. Add calls to renderDietHistory() and loadGoalsComplianceReport() in submitActiveAnalyzedMealChoice
target_submit_choice = """      logLCARS(`[🍽️ MEAL COMMITTED] "${mealName}" logged (${data.netCarbs || 0}g Net Carbs). LCARS watching 2-hour response curve.`, "rx");
      if (typeof renderLongitudinalView === 'function') renderLongitudinalView();"""

replace_submit_choice = """      logLCARS(`[🍽️ MEAL COMMITTED] "${mealName}" logged (${data.netCarbs || 0}g Net Carbs). LCARS watching 2-hour response curve.`, "rx");
      if (typeof renderLongitudinalView === 'function') renderLongitudinalView();
      if (typeof renderDietHistory === 'function') renderDietHistory();
      if (typeof loadGoalsComplianceReport === 'function') loadGoalsComplianceReport();"""

if target_submit_choice in index_html:
    index_html = index_html.replace(target_submit_choice, replace_submit_choice, 1)
    print("Updated submitActiveAnalyzedMealChoice in index.html")

# 2c. Add calls in submitActualMealAndMonitorMetrics
target_submit_actual = """      renderLongitudinalView();

      const scrollTarget = document.getElementById('cardDoctorPatientLongitudinal');"""

replace_submit_actual = """      renderLongitudinalView();
      if (typeof renderDietHistory === 'function') renderDietHistory();
      if (typeof loadGoalsComplianceReport === 'function') loadGoalsComplianceReport();

      const scrollTarget = document.getElementById('cardDoctorPatientLongitudinal');"""

if target_submit_actual in index_html:
    index_html = index_html.replace(target_submit_actual, replace_submit_actual, 1)
    print("Updated submitActualMealAndMonitorMetrics in index.html")

# 2d. In pollLiveTelemetryChanges, update dietHistory and goals compliance when db_version changes
target_poll_db = """        // Check if new records arrived
        if (dbVer !== lastKnownDbVersion) {
          lastKnownDbVersion = dbVer;"""

replace_poll_db = """        // Check if new records arrived
        if (dbVer !== lastKnownDbVersion) {
          lastKnownDbVersion = dbVer;
          if (typeof renderDietHistory === 'function') renderDietHistory();
          if (typeof loadGoalsComplianceReport === 'function') loadGoalsComplianceReport();"""

if target_poll_db in index_html:
    index_html = index_html.replace(target_poll_db, replace_poll_db, 1)
    print("Updated pollLiveTelemetryChanges in index.html")

# 2e. In analyzeMealIntake client fallback, add tea/coffee/zero drink fallback
target_fallback = """        } else if (text.includes("oat") || text.includes("meal01")) {
          data = POPULAR_MEALS.meal01 || DEFAULT_POPULAR_MEALS.meal01;
        } else {"""

replace_fallback = """        } else if (text.includes("oat") || text.includes("meal01")) {
          data = POPULAR_MEALS.meal01 || DEFAULT_POPULAR_MEALS.meal01;
        } else if (text.includes("clove") || text.includes("cinnamon") || text.includes("tea") || text.includes("infusion") || text.includes("tisane") || text.includes("coffee") || text.includes("seltzer") || text.includes("diet")) {
          const isCoffee = text.includes("coffee") || text.includes("espresso") || text.includes("americano");
          const isCloveCinn = text.includes("clove") || text.includes("cinnamon");
          const cals = isCoffee ? 4 : (isCloveCinn ? 2 : 0);
          const carbs = isCloveCinn ? 0.5 : 0;
          const bevName = (text.includes("clove") && text.includes("cinnamon")) ? "Clove & Cinnamon Herbal Infusion Tea" : (
            text.includes("clove") ? "Clove Herbal Tea" : (
              text.includes("cinnamon") ? "Cinnamon Herbal Tea" : (
                text.includes("tea") ? "Herbal / Unsweetened Tea" : (isCoffee ? "Black Coffee / Americano" : "Zero-Calorie Beverage / Water")
              )
            )
          );
          data = {
            title: rawText || bevName,
            desc: rawText,
            calories: cals,
            netCarbs: carbs,
            totalCarbs: carbs,
            fiber: 0,
            protein: 0,
            fat: 0,
            gl: "0 (Zero Glycemic Impact)",
            estimatedCost: "0.45",
            meetsGoal: true,
            tips: [
              "✨ CLINICAL POLYPHENOL PROFILE: Clove (eugenol) and cinnamon (cinnamaldehyde) provide bioactive antioxidant polyphenols that enhance cellular insulin sensitivity with zero caloric or glycemic load.",
              "Zero protein, zero fat, and zero glycemic excursion."
            ],
            glut4: "No postprandial GLUT4 walk required. Glycemic trajectory will remain completely flat in target safe zone.",
            glut4Peak: "PREDICTED PEAK: FLATLINE (< 2 MG/DL EXCURSION)"
          };
        } else {"""

if target_fallback in index_html:
    index_html = index_html.replace(target_fallback, replace_fallback, 1)
    print("Updated analyzeMealIntake client fallback in index.html")

# 2f. Restore Full Scan Startup Hydration in initFullScanView()
target_scan_vitals = """      const nowMs = Date.now();
      function isRecentAndPositive(v) {
        if (!v || !(v.value1 > 0)) return false;
        if (!v.timestamp) return true;
        try {
          const cleanTs = v.timestamp.replace(' ', 'T');
          const tMs = new Date(cleanTs).getTime();
          if (isNaN(tMs)) return true;
          return (nowMs - tMs) < 86400000; // strictly within 24 hours
        } catch(e) { return true; }
      }

      const hasPositiveVitals = (
        isRecentAndPositive(vitals.GLUCOSE) ||
        isRecentAndPositive(vitals.BLOOD_PRESSURE) ||
        isRecentAndPositive(vitals.HEART_RATE) ||
        isRecentAndPositive(spo2Obj) ||
        isRecentAndPositive(vitals.TEMPERATURE) ||
        isRecentAndPositive(hrvObj)
      );"""

replace_scan_vitals = """      function isPositiveVital(v) {
        return !!(v && (v.value1 > 0 || (v.systolic > 0)));
      }

      const hasPositiveVitals = (
        isPositiveVital(vitals.GLUCOSE) ||
        isPositiveVital(vitals.BLOOD_PRESSURE) ||
        isPositiveVital(vitals.HEART_RATE) ||
        isPositiveVital(spo2Obj) ||
        isPositiveVital(vitals.TEMPERATURE) ||
        isPositiveVital(hrvObj)
      );

      let latestScanTimestamp = "";
      [vitals.GLUCOSE, vitals.BLOOD_PRESSURE, vitals.HEART_RATE, spo2Obj, vitals.TEMPERATURE, hrvObj].forEach(v => {
        if (v && v.timestamp && v.timestamp > latestScanTimestamp) latestScanTimestamp = v.timestamp;
      });"""

if target_scan_vitals in index_html:
    index_html = index_html.replace(target_scan_vitals, replace_scan_vitals, 1)
    print("Updated initFullScanView vitals check in index.html")

target_scan_meta = """      if (hud) hud.textContent = "★ RECENT COMPREHENSIVE SCAN AUDIT ONLINE • READY FOR RE-SCAN";
      if (hudDesc) hudDesc.textContent = "Biometric audit loaded from SQLite telemetry database. Press [START FULL SCAN SEQUENCE] for live re-scan.";
      if (repMeta) repMeta.textContent = "★ RECENT COMPREHENSIVE SCAN AUDIT ONLINE • READY FOR RE-SCAN";"""

replace_scan_meta = """      if (hud) hud.textContent = "★ RECENT COMPREHENSIVE SCAN AUDIT ONLINE • READY FOR RE-SCAN";
      if (hudDesc) hudDesc.textContent = "Biometric audit loaded from SQLite telemetry database. Press [START FULL SCAN SEQUENCE] for live re-scan.";
      if (repMeta) {
        repMeta.textContent = latestScanTimestamp ? `★ RECORDED BIO-SCAN AUDIT: ${latestScanTimestamp} • READY FOR RE-SCAN` : "★ RECENT COMPREHENSIVE SCAN AUDIT ONLINE • READY FOR RE-SCAN";
      }"""

if target_scan_meta in index_html:
    index_html = index_html.replace(target_scan_meta, replace_scan_meta, 1)
    print("Updated initFullScanView meta title in index.html")

# 2g. Remove redundant sync buttons across index.html
# 1) btnReadSleep
target_btn_sleep = """                <button id="btnReadSleep" class="lcars-btn btn-blue" onclick="querySleepData(0)">SYNC SLEEP (0xE0)</button>"""
if target_btn_sleep in index_html:
    index_html = index_html.replace(target_btn_sleep, "", 1)
    print("Removed redundant btnReadSleep")

# 2) btnSyncWorkoutData
target_btn_workout = """                  <button id="btnSyncWorkoutData" class="lcars-pill pill-ice" style="font-size: 0.8rem; padding: 6px 12px; height: auto;" onclick="syncStepData()">
                    <span>SYNC WATCH PEDOMETER</span>
                  </button>"""
if target_btn_workout in index_html:
    index_html = index_html.replace(target_btn_workout, "", 1)
    print("Removed redundant btnSyncWorkoutData")

# 3) Tab 03 "SYNC WATCH STEPS"
target_btn_steps = """                <button class="lcars-btn btn-banana" style="flex: none; padding: 4px 14px; font-size: 0.8rem;" onclick="syncStepData()">SYNC WATCH STEPS</button>"""
if target_btn_steps in index_html:
    index_html = index_html.replace(target_btn_steps, "", 1)
    print("Removed redundant SYNC WATCH STEPS button")

# 4) Tab 05 / 14 "SYNC WATCH"
target_btn_watch = """              <button class="lcars-btn btn-blue" style="padding: 4px 12px; font-size: 0.8rem;" onclick="syncWatchTime()">SYNC WATCH</button>"""
if target_btn_watch in index_html:
    index_html = index_html.replace(target_btn_watch, "", 1)
    print("Removed redundant SYNC WATCH button in log bay")

# 5) "PING & TIME SYNC (0xA1)"
target_btn_ping = """              <button class="lcars-btn btn-blue" onclick="syncWatchTime()">PING & TIME SYNC (0xA1)</button>"""
if target_btn_ping in index_html:
    index_html = index_html.replace(target_btn_ping, "", 1)
    print("Removed redundant PING & TIME SYNC button")

# 6) Top header btnHeaderSyncNow
target_btn_header_sync = """          <button id="btnHeaderSyncNow" class="lcars-pill pill-orange" style="padding: 4px 10px; font-size: 0.72rem; height: auto; cursor: pointer;" onclick="triggerWatchSyncNow()" title="Trigger Immediate On-Demand Watch Sync">
            <span>⚡ SYNC NOW</span>
          </button>"""
if target_btn_header_sync in index_html:
    index_html = index_html.replace(target_btn_header_sync, "", 1)
    print("Removed redundant btnHeaderSyncNow from top header")

# Save root index.html
with open(index_path, "w", encoding="utf-8") as f:
    f.write(index_html)
print("Saved root index.html")

# 3. Mirror root index.html byte-for-byte to w11_htm_app/index.html
app_index_path = r"D:\veepoo\w11_htm_app\index.html"
with open(app_index_path, "w", encoding="utf-8") as f:
    f.write(index_html)
print("Mirrored index.html to w11_htm_app/index.html with 100% parity!")
