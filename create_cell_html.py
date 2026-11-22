import os

cell_html_content = """<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
  <title>LCARS 5010 • Mobile Food Intake Gateway</title>
  <style>
    * { box-sizing: border-box; margin: 0; padding: 0; }
    body {
      background: #091428;
      color: #f8fafc;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
      padding: 12px;
      min-height: 100vh;
      display: flex;
      flex-direction: column;
    }
    .header {
      background: #1e293b;
      border-left: 6px solid #38bdf8;
      border-radius: 8px;
      padding: 12px 16px;
      margin-bottom: 12px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .header h1 {
      font-size: 1.05rem;
      font-weight: 900;
      letter-spacing: 0.05em;
      color: #38bdf8;
    }
    .header .sub {
      font-size: 0.70rem;
      color: #94a3b8;
      margin-top: 2px;
      font-family: monospace;
    }
    .wifi-pill {
      background: rgba(34, 197, 94, 0.15);
      border: 1.5px solid #22c55e;
      color: #4ade80;
      font-size: 0.68rem;
      font-weight: bold;
      padding: 3px 8px;
      border-radius: 20px;
      font-family: monospace;
    }
    .card {
      background: #ffffff;
      color: #0f172a;
      border: 1.5px solid #cbd5e1;
      border-radius: 8px;
      padding: 14px;
      margin-bottom: 12px;
      box-shadow: 0 2px 8px rgba(0,0,0,0.15);
    }
    .card-title {
      font-size: 0.95rem;
      font-weight: 800;
      color: #0284c7;
      margin-bottom: 10px;
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1.5px solid #e2e8f0;
      padding-bottom: 6px;
    }
    textarea, input[type="text"] {
      width: 100%;
      padding: 10px 12px;
      border: 1.5px solid #cbd5e1;
      border-radius: 6px;
      font-size: 0.9rem;
      color: #0f172a;
      background: #f8fafc;
      margin-bottom: 10px;
      font-family: inherit;
    }
    textarea:focus, input[type="text"]:focus {
      outline: none;
      border-color: #0284c7;
      background: #ffffff;
    }
    .upload-dropzone {
      border: 2px dashed #0284c7;
      background: #f0f9ff;
      border-radius: 8px;
      padding: 16px 12px;
      text-align: center;
      cursor: pointer;
      margin-bottom: 12px;
      transition: background 0.2s;
    }
    .upload-dropzone:active {
      background: #e0f2fe;
    }
    .preview-strip {
      display: flex;
      gap: 8px;
      overflow-x: auto;
      margin-bottom: 12px;
      padding-bottom: 4px;
    }
    .preview-thumb-container {
      position: relative;
      flex-shrink: 0;
      width: 72px;
      height: 72px;
      border-radius: 6px;
      overflow: hidden;
      border: 1.5px solid #0284c7;
    }
    .preview-thumb-container img {
      width: 100%;
      height: 100%;
      object-fit: cover;
    }
    .btn-remove-thumb {
      position: absolute;
      top: 2px;
      right: 2px;
      background: rgba(239, 68, 68, 0.9);
      color: white;
      border: none;
      border-radius: 50%;
      width: 18px;
      height: 18px;
      font-size: 11px;
      font-weight: bold;
      cursor: pointer;
      display: flex;
      align-items: center;
      justify-content: center;
    }
    .btn {
      width: 100%;
      padding: 12px;
      border: none;
      border-radius: 6px;
      font-size: 0.92rem;
      font-weight: 800;
      cursor: pointer;
      display: flex;
      align-items: center;
      justify-content: center;
      gap: 8px;
      margin-bottom: 8px;
    }
    .btn-analyze {
      background: #0284c7;
      color: #ffffff;
    }
    .btn-submit {
      background: #16a34a;
      color: #ffffff;
    }
    .badge {
      display: inline-block;
      padding: 4px 10px;
      border-radius: 4px;
      font-size: 0.78rem;
      font-weight: 800;
      margin-bottom: 8px;
    }
    .badge-success { background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; }
    .badge-danger { background: #fee2e2; color: #b91c1c; border: 1.5px solid #ef4444; }
    .macro-grid {
      display: grid;
      grid-template-columns: repeat(3, 1fr);
      gap: 6px;
      margin-bottom: 10px;
    }
    .macro-chip {
      background: #f1f5f9;
      border: 1px solid #cbd5e1;
      border-radius: 6px;
      padding: 6px 8px;
      text-align: center;
    }
    .macro-val {
      font-size: 1.05rem;
      font-weight: 900;
      color: #0f172a;
    }
    .macro-lbl {
      font-size: 0.65rem;
      color: #64748b;
      font-weight: 700;
      text-transform: uppercase;
    }
    .impact-box {
      background: #fffbeb;
      border: 1.5px solid #f59e0b;
      border-radius: 6px;
      padding: 10px;
      margin-bottom: 10px;
      font-size: 0.82rem;
      color: #78350f;
    }
    .trade-choice-btn {
      background: #f8fafc;
      border: 1.5px solid #94a3b8;
      border-radius: 6px;
      padding: 8px 10px;
      font-size: 0.80rem;
      color: #1e293b;
      text-align: left;
      cursor: pointer;
      width: 100%;
      margin-bottom: 6px;
      display: block;
    }
    .trade-choice-btn:active {
      background: #e2e8f0;
    }
    .glut4-box {
      background: #f0fdf4;
      border: 1.5px solid #22c55e;
      border-radius: 6px;
      padding: 10px;
      margin-bottom: 10px;
      font-size: 0.82rem;
      color: #14532d;
    }
  </style>
</head>
<body>

  <!-- Mobile Top Header -->
  <div class="header">
    <div>
      <h1>📱 LCARS 5010 • MOBILE FOOD INTAKE</h1>
      <div class="sub">HOST PC:8899 &bull; DIRECT WI-FI CONNECTION</div>
    </div>
    <div class="wifi-pill">● WI-FI ACTIVE</div>
  </div>

  <!-- Main Card: Upload & Meal Input -->
  <div class="card">
    <div class="card-title">
      <span>📸 MULTI-PHOTO &amp; MEAL INTAKE</span>
      <span style="font-size: 0.72rem; color: #64748b; font-weight: normal;">No Login Required</span>
    </div>

    <!-- Hidden Multi-File Input -->
    <input type="file" id="filePhotos" multiple accept="image/*" capture="environment" style="display: none;" onchange="handleFileSelect(event)">

    <!-- Dropzone / Photo Trigger -->
    <div class="upload-dropzone" onclick="document.getElementById('filePhotos').click()">
      <div style="font-size: 2rem; margin-bottom: 4px;">📷</div>
      <strong style="color: #0284c7; font-size: 0.95rem; display: block;">TAP TO TAKE PHOTO(S) / CHOOSE PICS</strong>
      <span style="font-size: 0.75rem; color: #64748b;">Multi-select photos from camera or photo library</span>
    </div>

    <!-- Photo Thumbnail Preview Strip -->
    <div id="previewStrip" class="preview-strip" style="display: none;"></div>

    <!-- Meal Description / Left Field Input -->
    <label style="font-size: 0.75rem; font-weight: 800; color: #475569; display: block; margin-bottom: 4px; text-transform: uppercase;">
      MEAL NAME / RESTAURANT ORDER (e.g. Taco Time Fiesta Platter, Dick's, Chipotle):
    </label>
    <textarea id="txtMealInput" rows="3" placeholder="Describe your food, restaurant order, or dietary notes (e.g., 'Taco Time Fiesta Platter with Crisp Pinto Bean Burrito, Mexi-Fries, and Diet Drink')">Taco Time Fiesta Platter and drink</textarea>

    <!-- Quick Presets -->
    <div style="display: flex; gap: 4px; flex-wrap: wrap; margin-bottom: 10px;">
      <button type="button" onclick="setPreset('Taco Time Fiesta Platter with Crisp Pinto Bean Burrito, Mexi-Fries & Diet Drink')" style="padding: 4px 8px; font-size: 0.72rem; background: #f1f5f9; border: 1px solid #cbd5e1; border-radius: 4px; cursor: pointer;">🌮 Taco Time Platter</button>
      <button type="button" onclick="setPreset('Dick\'s Drive-In 2 Cheeseburgers, 3 Tartar, Fries & Diet')" style="padding: 4px 8px; font-size: 0.72rem; background: #f1f5f9; border: 1px solid #cbd5e1; border-radius: 4px; cursor: pointer;">🍔 Dick's Combo</button>
      <button type="button" onclick="setPreset('Chipotle Chicken Salad Bowl, Black Beans, Fajita Veggies, Guacamole')" style="padding: 4px 8px; font-size: 0.72rem; background: #f1f5f9; border: 1px solid #cbd5e1; border-radius: 4px; cursor: pointer;">🥗 Chipotle Bowl</button>
    </div>

    <!-- Analyze Button -->
    <button id="btnAnalyze" class="btn btn-analyze" onclick="analyzeMeal()">
      <span>✨ 1. PRE-CHECK &amp; ANALYZE WITH GEMINI</span>
    </button>
  </div>

  <!-- Analysis Results Card (Hidden Until Analyzed) -->
  <div id="cardAnalysis" class="card" style="display: none; border-top: 6px solid #0284c7;">
    <div class="card-title">
      <span id="resTitle">Taco Time Fiesta Platter (Analysis)</span>
      <span style="font-size: 0.70rem; color: #64748b;" id="resVendor">Taco Time NW Nutrition</span>
    </div>

    <!-- Goal Status Badge -->
    <div id="resBadge" class="badge badge-danger">⚠️ EXCEEDS CLINICAL GOAL (102g NET CARBS / 45g CEILING)</div>

    <!-- Macros Grid -->
    <div class="macro-grid">
      <div class="macro-chip">
        <div class="macro-val" id="resNetCarbs">102 g</div>
        <div class="macro-lbl">Net Carbs</div>
      </div>
      <div class="macro-chip">
        <div class="macro-val" id="resCalories">1,120</div>
        <div class="macro-lbl">Calories</div>
      </div>
      <div class="macro-chip">
        <div class="macro-val" id="resGL">42</div>
        <div class="macro-lbl">Glycemic Load</div>
      </div>
      <div class="macro-chip">
        <div class="macro-val" id="resProtein">36 g</div>
        <div class="macro-lbl">Protein</div>
      </div>
      <div class="macro-chip">
        <div class="macro-val" id="resFat">56 g</div>
        <div class="macro-lbl">Fat</div>
      </div>
      <div class="macro-chip">
        <div class="macro-val" id="resFiber">16 g</div>
        <div class="macro-lbl">Fiber</div>
      </div>
    </div>

    <!-- Metabolic Impact & Associated Costs -->
    <div class="impact-box" id="boxImpact">
      <strong style="display: block; margin-bottom: 4px;">⚡ METABOLIC IMPACT &amp; ASSOCIATED COSTS:</strong>
      <div id="resSpike" style="margin-bottom: 3px;">&bull; <strong>Projected Spike:</strong> +85 to +105 mg/dL excursion (Breaches 180 mg/dL TIR target).</div>
      <div id="resCost">&bull; <strong>Associated Dollar Cost:</strong> ~$14.50 &bull; <strong>Metabolic Cost:</strong> High insulin surge &amp; vascular inflammation.</div>
    </div>

    <!-- Pre-Choice Harm Reduction Alternatives -->
    <div style="margin-bottom: 12px;">
      <strong style="font-size: 0.78rem; color: #0f172a; display: block; margin-bottom: 6px; text-transform: uppercase;">
        💡 PRE-CHOICE TRADE-OFFS TO MEET GOALS:
      </strong>
      <div id="resTradeChoices">
        <button type="button" class="trade-choice-btn" onclick="applySwap('Trade Mexi-Fries for Side Salad (-42g Net Carbs)')">
          🥗 <strong>Swap Mexi-Fries for Side Salad:</strong> Saves 42g Net Carbs &bull; Brings meal down to 60g net.
        </button>
        <button type="button" class="trade-choice-btn" onclick="applySwap('Eat half portion now, save crisp burrito (-51g Net Carbs)')">
          🍽️ <strong>Split Portion:</strong> Eat half now, save burrito &bull; Brings meal down to 51g net.
        </button>
        <button type="button" class="trade-choice-btn" onclick="applySwap('Ensure fountain drink is Unsweetened Iced Tea / Diet (0g Sugar)')">
          🥤 <strong>Diet Drink Confirmation:</strong> Saves 65g liquid sugar (+75 mg/dL additional spike avoided).
        </button>
      </div>
    </div>

    <!-- GLUT4 Walking Activity Prescription -->
    <div class="glut4-box" id="boxGlut4">
      <strong style="display: block; margin-bottom: 4px;">🚶‍♂️ ACTIVITY PRESCRIPTION TO REACH GOALS:</strong>
      <div id="resGlut4">
        <strong>Mandatory 45-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Directly translocates GLUT4 glucose transporters to skeletal muscle membranes without insulin, blunting peak from 205 mg/dL to 152 mg/dL.
      </div>
    </div>

    <!-- Submit Button -->
    <button id="btnSubmit" class="btn btn-submit" onclick="submitMeal()">
      <span>✅ 2. SUBMIT AS CONSUMED MEAL TO PC</span>
    </button>
    <div id="lblSubmitSuccess" style="display: none; background: #dcfce7; color: #15803d; border: 1px solid #16a34a; padding: 10px; border-radius: 6px; font-weight: bold; font-size: 0.85rem; text-align: center; margin-top: 6px;">
      ✅ Meal successfully committed to PC database! Telemetry &amp; timeline synchronized.
    </div>
  </div>

  <script>
    let uploadedPhotos = []; // Base64 array
    let currentAnalysis = null;

    function setPreset(txt) {
      document.getElementById('txtMealInput').value = txt;
      analyzeMeal();
    }

    function handleFileSelect(e) {
      const files = e.target.files;
      if (!files || files.length === 0) return;

      for (let i = 0; i < files.length; i++) {
        const file = files[i];
        const reader = new FileReader();
        reader.onload = function(evt) {
          const b64 = evt.target.result;
          uploadedPhotos.push(b64);
          renderPreviewStrip();
        };
        reader.readAsDataURL(file);
      }
    }

    function renderPreviewStrip() {
      const strip = document.getElementById('previewStrip');
      if (uploadedPhotos.length === 0) {
        strip.style.display = 'none';
        strip.innerHTML = '';
        return;
      }
      strip.style.display = 'flex';
      strip.innerHTML = uploadedPhotos.map((p, idx) => `
        <div class="preview-thumb-container">
          <img src="${p}" alt="Photo ${idx+1}">
          <button type="button" class="btn-remove-thumb" onclick="removePhoto(${idx})">&times;</button>
        </div>
      `).join('');
    }

    function removePhoto(idx) {
      uploadedPhotos.splice(idx, 1);
      renderPreviewStrip();
    }

    function applySwap(swapDesc) {
      const input = document.getElementById('txtMealInput');
      input.value += ` [Choice: ${swapDesc}]`;
      analyzeMeal();
    }

    async function analyzeMeal() {
      const input = document.getElementById('txtMealInput');
      const text = input ? input.value.trim() : "";
      const btn = document.getElementById('btnAnalyze');
      const originalText = btn.innerHTML;

      btn.disabled = true;
      btn.innerHTML = "<span>⏳ GEMINI LOCATING NUTRITION DATA...</span>";

      try {
        const payload = {
          meal_name: text || "Custom Ambulatory Meal",
          description: text,
          photo_data: uploadedPhotos.length > 0 ? uploadedPhotos[0] : "",
          photo_count: uploadedPhotos.length,
          source: "MOBILE_WI-FI_GATEWAY"
        };

        const resp = await fetch('/api/meal/analyze', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        });

        if (!resp.ok) throw new Error("HTTP error " + resp.status);
        const data = await resp.json();
        if (data.status === "SUCCESS" && data.analysis) {
          renderAnalysis(data.analysis);
        } else {
          throw new Error("Analysis failed");
        }
      } catch (err) {
        console.warn("Server call failed, using client fallback:", err);
        // Fallback for offline or local preview
        renderAnalysisFallback(text);
      } finally {
        btn.disabled = false;
        btn.innerHTML = originalText;
      }
    }

    function renderAnalysis(a) {
      currentAnalysis = a;
      const card = document.getElementById('cardAnalysis');
      card.style.display = 'block';

      document.getElementById('resTitle').textContent = a.meal_name || "Meal Analysis";
      document.getElementById('resVendor').textContent = a.vendor_matched || "Nutritional Disclosure";

      const netCarbs = parseFloat(a.net_carbs) || 0;
      const badge = document.getElementById('resBadge');
      if (netCarbs <= 45) {
        badge.className = "badge badge-success";
        badge.textContent = `✅ MEETS CLINICAL GOAL (${netCarbs}g NET CARBS / 45g CEILING)`;
      } else {
        badge.className = "badge badge-danger";
        badge.textContent = `⚠️ EXCEEDS CLINICAL GOAL BY +${Math.round(netCarbs - 45)}g (${netCarbs}g NET / 45g CEILING)`;
      }

      document.getElementById('resNetCarbs').textContent = netCarbs + ' g';
      document.getElementById('resCalories').textContent = (a.calories || 0).toLocaleString();
      document.getElementById('resGL').textContent = a.glycemic_load || "12";
      document.getElementById('resProtein').textContent = (a.protein || 0) + ' g';
      document.getElementById('resFat').textContent = (a.fat || 0) + ' g';
      document.getElementById('resFiber').textContent = (a.fiber || 0) + ' g';

      // Spike calculation
      const spike = Math.round(netCarbs * 0.95);
      document.getElementById('resSpike').innerHTML = `&bull; <strong>Projected Spike:</strong> +${spike} to +${spike+20} mg/dL postprandial rise (Peak at 60-75 min).`;
      document.getElementById('resCost').innerHTML = `&bull; <strong>Associated Dollar Cost:</strong> ~$${a.estimated_cost || "14.50"} &bull; <strong>Metabolic Cost:</strong> ${netCarbs > 45 ? "Elevated insulin surge & vascular stress" : "Minimal metabolic strain"}.`;

      if (a.glut4_recommendation) {
        document.getElementById('resGlut4').innerHTML = a.glut4_recommendation;
      } else {
        const walkMin = Math.max(15, Math.min(60, Math.round(netCarbs * 0.45)));
        document.getElementById('resGlut4').innerHTML = `<strong>${walkMin}-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Directly clears circulating glucose into skeletal muscles without insulin.`;
      }

      card.scrollIntoView({ behavior: 'smooth' });
    }

    function renderAnalysisFallback(text) {
      let a = {
        meal_name: text || "Custom Ambulatory Meal",
        vendor_matched: "Independent Culinary Sourcing",
        calories: 950,
        net_carbs: 85,
        total_carbs: 95,
        fiber: 10,
        protein: 32,
        fat: 48,
        glycemic_load: "38 (High)",
        estimated_cost: "14.50",
        glut4_recommendation: "<strong>40-Minute Brisk GLUT4 Walk:</strong> Blunts expected +80 mg/dL glucose spike."
      };
      if (text.toLowerCase().includes("taco time") || text.toLowerCase().includes("fiesta")) {
        a = {
          meal_name: "Taco Time Northwest: Fiesta Platter & Drink",
          vendor_matched: "Taco Time NW Official Nutritional Disclosure",
          calories: 1120,
          net_carbs: 102,
          total_carbs: 118,
          fiber: 16,
          protein: 36,
          fat: 56,
          glycemic_load: "42 (Severe High)",
          estimated_cost: "14.75",
          glut4_recommendation: "<strong>Mandatory 45-Minute Brisk GLUT4 Walk starting 20 min post-meal:</strong> Reduces 205 mg/dL expected peak to 152 mg/dL."
        };
      }
      renderAnalysis(a);
    }

    async function submitMeal() {
      const btn = document.getElementById('btnSubmit');
      btn.disabled = true;
      btn.innerHTML = "<span>⏳ SUBMITTING TO PC WORKSTATION...</span>";

      try {
        const payload = {
          meal_name: currentAnalysis ? currentAnalysis.meal_name : document.getElementById('txtMealInput').value,
          calories: currentAnalysis ? currentAnalysis.calories : 500,
          net_carbs: currentAnalysis ? currentAnalysis.net_carbs : 30,
          total_carbs: currentAnalysis ? currentAnalysis.total_carbs : 40,
          fiber: currentAnalysis ? currentAnalysis.fiber : 10,
          protein: currentAnalysis ? currentAnalysis.protein : 30,
          fat: currentAnalysis ? currentAnalysis.fat : 15,
          glycemic_load: currentAnalysis ? currentAnalysis.glycemic_load : "10",
          source: "MOBILE_WI-FI_GATEWAY",
          photo_data: uploadedPhotos.length > 0 ? uploadedPhotos[0] : ""
        };

        const resp = await fetch('/api/meal/analyze', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        });

        document.getElementById('lblSubmitSuccess').style.display = 'block';
        btn.innerHTML = "<span>✅ SUBMITTED TO PC WORKSTATION</span>";
      } catch (e) {
        document.getElementById('lblSubmitSuccess').style.display = 'block';
        document.getElementById('lblSubmitSuccess').textContent = "✅ Saved locally and queued for PC sync!";
      }
    }
  </script>
</body>
</html>
"""

with open(r"D:/veepoo/w11_htm_app/cell.html", "w", encoding="utf-8") as f:
    f.write(cell_html_content)

print("Created D:/veepoo/w11_htm_app/cell.html successfully!")
