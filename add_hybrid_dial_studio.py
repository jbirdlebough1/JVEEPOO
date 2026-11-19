import re

file_path = r"D:\veepoo\w11_htm_app\index.html"
with open(file_path, "r", encoding="utf-8") as f:
    text = f.read()

# Target insertion point: Before Section 1 (Built-In Watch Face Gallery)
target = '<!-- Section 1: Built-In Watch Face Gallery -->'

chrono_html = '''<!-- FEATURED: CUSTOM HYBRID MEDICAL CHRONOMETER PROTOTYPE -->
          <div class="card-box" id="boxHybridChronoStudio" style="border: 2px solid var(--lcars-banana); background: rgba(12, 14, 20, 0.98);">
            <div class="box-header" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px; background: rgba(255, 204, 102, 0.1);">
              <div>
                <span class="box-title" style="color: var(--lcars-banana); font-size: 1.15rem;">★ CUSTOM HYBRID MEDICAL CHRONOMETER PROTOTYPE</span>
                <div style="font-size: 0.85rem; color: var(--lcars-text); margin-top: 2px;">
                  3-Segment Outer Circle Gauges (Glucose • Steps • HR) + Dual Skeleton Analog &amp; Digital Time
                </div>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-pill pill-green" style="font-size: 0.8rem; padding: 6px 14px; height: auto; cursor: pointer;" onclick="toggleChronoMode()">
                  <span id="lblChronoMode">MODE: REAL SENSORS</span>
                </button>
                <button class="lcars-btn btn-banana" style="font-weight: bold; padding: 6px 16px;" onclick="applyHybridChronoToWatch()">
                  ⚡ APPLY TO WATCH (SLOT 0)
                </button>
              </div>
            </div>

            <div class="box-content" style="padding: 18px;">
              <div style="display: flex; flex-wrap: wrap; gap: 24px; align-items: center; justify-content: center;">

                <!-- THE 240x280 PHYSICAL SIMULATOR FRAME -->
                <div style="position: relative; width: 280px; height: 326px; background: #000; border: 8px solid #1a2332; border-radius: 36px; box-shadow: 0 0 25px rgba(0,0,0,0.8), 0 0 10px rgba(255, 204, 102, 0.2); overflow: hidden; display: flex; align-items: center; justify-content: center;">
                  
                  <!-- SVG Dial Canvas (Exact 240x280 aspect ratio) -->
                  <svg id="svgHybridDial" width="240" height="280" viewBox="0 0 240 280" style="background: #020408; font-family: 'Share Tech Mono', Consolas, sans-serif;">
                    
                    <!-- Outer Chapter Ring & Tick Marks -->
                    <circle cx="120" cy="140" r="112" fill="none" stroke="#141c29" stroke-width="2" />
                    <circle cx="120" cy="140" r="98" fill="none" stroke="#0a0e18" stroke-width="1" />

                    <!-- Dial Hour Indices (12, 1, 2, ... 11) -->
                    <!-- 12 Ticks -->
                    <g stroke="#4f5b66" stroke-width="1.5">
                      <line x1="120" y1="30" x2="120" y2="38" stroke="#ffcc66" stroke-width="3" />
                      <line x1="175" y1="45" x2="171" y2="52" />
                      <line x1="215" y1="85" x2="208" y2="89" />
                      <line x1="230" y1="140" x2="222" y2="140" stroke="#ffaa55" stroke-width="2.5" />
                      <line x1="215" y1="195" x2="208" y2="191" />
                      <line x1="175" y1="235" x2="171" y2="228" />
                      <line x1="120" y1="250" x2="120" y2="242" stroke="#ffcc66" stroke-width="3" />
                      <line x1="65" y1="235" x2="69" y2="228" />
                      <line x1="25" y1="195" x2="32" y2="191" />
                      <line x1="10" y1="140" x2="18" y2="140" stroke="#55ff77" stroke-width="2.5" />
                      <line x1="25" y1="85" x2="32" y2="89" />
                      <line x1="65" y1="45" x2="69" y2="52" />
                    </g>

                    <!-- 1. TOP ARC: BLOOD GLUCOSE GAUGE (70 to 200 mg/dL) -->
                    <!-- Background Arc -->
                    <path id="arcBgGlucose" d="" fill="none" stroke="#1a2332" stroke-width="7" stroke-linecap="round" />
                    <!-- Dynamic Progress Arc -->
                    <path id="arcValGlucose" d="" fill="none" stroke="#55ff77" stroke-width="7" stroke-linecap="round" />
                    <text x="120" y="24" text-anchor="middle" fill="#ffaa55" font-size="8" font-weight="bold" letter-spacing="1">GLUCOSE TARGET (70-130)</text>

                    <!-- 2. LOWER-RIGHT ARC: STEP GOAL (0 to 10k Steps) -->
                    <path id="arcBgSteps" d="" fill="none" stroke="#1a2332" stroke-width="6" stroke-linecap="round" />
                    <path id="arcValSteps" d="" fill="none" stroke="#6699cc" stroke-width="6" stroke-linecap="round" />

                    <!-- 3. LOWER-LEFT ARC: HEART RATE & EFFORT (50 to 180 BPM) -->
                    <path id="arcBgHr" d="" fill="none" stroke="#1a2332" stroke-width="6" stroke-linecap="round" />
                    <path id="arcValHr" d="" fill="none" stroke="#ffaa55" stroke-width="6" stroke-linecap="round" />

                    <!-- UPPER SUB-DIAL: DIGITAL CLOCK & DATE (12 o'clock) -->
                    <rect x="76" y="52" width="88" height="32" rx="6" fill="#080c14" stroke="#1a2332" stroke-width="1" />
                    <text id="chronoDigitalTime" x="120" y="73" text-anchor="middle" fill="#ffffff" font-size="19" font-weight="bold" letter-spacing="1">12:45</text>
                    <text id="chronoDigitalDate" x="120" y="81" text-anchor="middle" fill="#ffcc66" font-size="7.5" font-weight="bold" letter-spacing="0.5">WED NOV 18</text>

                    <!-- LEFT SUB-DIAL (9 o'clock): BLOOD GLUCOSE POD -->
                    <circle cx="62" cy="140" r="28" fill="#060910" stroke="#1e293b" stroke-width="1.5" />
                    <text x="62" y="126" text-anchor="middle" fill="#94a3b8" font-size="7">GLUCOSE</text>
                    <text id="chronoPodGlucose" x="62" y="145" text-anchor="middle" fill="#55ff77" font-size="16" font-weight="bold">124</text>
                    <text x="62" y="157" text-anchor="middle" fill="#94a3b8" font-size="7.5">mg/dL →</text>

                    <!-- RIGHT SUB-DIAL (3 o'clock): HEART RATE POD -->
                    <circle cx="178" cy="140" r="28" fill="#060910" stroke="#1e293b" stroke-width="1.5" />
                    <text x="178" y="126" text-anchor="middle" fill="#94a3b8" font-size="7">HEART RATE</text>
                    <text id="chronoPodHr" x="178" y="145" text-anchor="middle" fill="#ffaa55" font-size="16" font-weight="bold">72</text>
                    <text x="178" y="157" text-anchor="middle" fill="#94a3b8" font-size="7.5">BPM • ZONE 2</text>

                    <!-- BOTTOM SUB-DIAL (6 o'clock): STEP COUNT POD -->
                    <rect x="72" y="196" width="96" height="34" rx="6" fill="#060910" stroke="#1e293b" stroke-width="1.5" />
                    <text x="120" y="208" text-anchor="middle" fill="#94a3b8" font-size="7">DAILY PEDOMETER</text>
                    <text id="chronoPodSteps" x="120" y="222" text-anchor="middle" fill="#6699cc" font-size="14" font-weight="bold">8,420</text>

                    <!-- BATTERY & PROTOCOL BADGE -->
                    <text id="chronoBattery" x="120" y="104" text-anchor="middle" fill="#55ff77" font-size="8">🔋 85% • VEE 5010</text>

                    <!-- SKELETONIZED ANALOG HANDS (Pivoting at 120, 140) -->
                    <!-- Hour Hand (Length 50, Width 7, Skeleton cut-out center) -->
                    <g id="handHour" transform="rotate(0, 120, 140)">
                      <!-- Outer rim -->
                      <polygon points="116.5,140 117.5,90 120,84 122.5,90 123.5,140" fill="rgba(255,204,102,0.3)" stroke="#ffcc66" stroke-width="2" />
                      <!-- Center cut-out transparent slot -->
                      <line x1="120" y1="130" x2="120" y2="96" stroke="#020408" stroke-width="2.5" />
                    </g>

                    <!-- Minute Hand (Length 78, Width 5, Skeleton cut-out center) -->
                    <g id="handMinute" transform="rotate(0, 120, 140)">
                      <polygon points="117.5,140 118.5,65 120,58 121.5,65 122.5,140" fill="rgba(255,255,255,0.25)" stroke="#ffffff" stroke-width="2" />
                      <line x1="120" y1="130" x2="120" y2="70" stroke="#020408" stroke-width="2" />
                    </g>

                    <!-- Sweeping Second Hand (Thin Starfleet Orange needle) -->
                    <g id="handSecond" transform="rotate(0, 120, 140)">
                      <line x1="120" y1="160" x2="120" y2="44" stroke="#ff9900" stroke-width="1.5" />
                      <circle cx="120" cy="140" r="5" fill="#ff9900" />
                      <circle cx="120" cy="52" r="3" fill="#ff9900" />
                    </g>

                    <!-- Center Cap Pin -->
                    <circle cx="120" cy="140" r="2.5" fill="#000" stroke="#ffcc66" stroke-width="1.5" />

                  </svg>

                </div>

                <!-- LIVE INTERACTIVE TEST BENCH & SLIDERS -->
                <div style="flex: 1; min-width: 290px; max-width: 480px; display: flex; flex-direction: column; gap: 14px;">
                  
                  <div style="background: rgba(255, 255, 255, 0.03); border: 1px solid var(--lcars-border); border-radius: 8px; padding: 14px;">
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                      <strong style="color: var(--lcars-green); font-size: 0.95rem;">GLUCOSE TEST SLIDER (TOP ARC)</strong>
                      <span id="lblTestGlucose" style="font-weight: bold; color: var(--lcars-green);">124 mg/dL</span>
                    </div>
                    <input type="range" id="sliderChronoGlucose" min="60" max="240" value="124" style="width: 100%; accent-color: var(--lcars-green);" oninput="updateChronoSim()">
                    <div style="display: flex; justify-content: space-between; font-size: 0.72rem; color: var(--lcars-gray); margin-top: 2px;">
                      <span>60 (Hypo)</span>
                      <span>80-130 (ADA Target)</span>
                      <span>180 (Elevated)</span>
                      <span>240+ (Spike)</span>
                    </div>
                  </div>

                  <div style="background: rgba(255, 255, 255, 0.03); border: 1px solid var(--lcars-border); border-radius: 8px; padding: 14px;">
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                      <strong style="color: var(--lcars-blue); font-size: 0.95rem;">PEDOMETER GOAL SLIDER (RIGHT ARC)</strong>
                      <span id="lblTestSteps" style="font-weight: bold; color: var(--lcars-blue);">8,420 Steps</span>
                    </div>
                    <input type="range" id="sliderChronoSteps" min="0" max="15000" step="100" value="8420" style="width: 100%; accent-color: var(--lcars-blue);" oninput="updateChronoSim()">
                    <div style="display: flex; justify-content: space-between; font-size: 0.72rem; color: var(--lcars-gray); margin-top: 2px;">
                      <span>0 Steps</span>
                      <span>5,000</span>
                      <span>10,000 (Goal)</span>
                      <span>15,000+</span>
                    </div>
                  </div>

                  <div style="background: rgba(255, 255, 255, 0.03); border: 1px solid var(--lcars-border); border-radius: 8px; padding: 14px;">
                    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                      <strong style="color: var(--lcars-orange); font-size: 0.95rem;">HEART RATE SLIDER (LEFT ARC)</strong>
                      <span id="lblTestHr" style="font-weight: bold; color: var(--lcars-orange);">72 BPM</span>
                    </div>
                    <input type="range" id="sliderChronoHr" min="50" max="175" value="72" style="width: 100%; accent-color: var(--lcars-orange);" oninput="updateChronoSim()">
                    <div style="display: flex; justify-content: space-between; font-size: 0.72rem; color: var(--lcars-gray); margin-top: 2px;">
                      <span>50 (Resting)</span>
                      <span>70-90 (Normal)</span>
                      <span>120 (Zone 2)</span>
                      <span>160+ (Peak)</span>
                    </div>
                  </div>

                  <div style="font-size: 0.8rem; color: var(--lcars-text); line-height: 1.4; background: rgba(255, 153, 0, 0.08); border-left: 3px solid var(--lcars-orange); padding: 8px 12px; border-radius: 4px;">
                    <strong>Notice the Skeleton Hands:</strong> As the hands sweep through 3:00, 6:00, and 9:00, the see-through center slot ensures you can always read your blood glucose, pulse, and step count numbers!
                  </div>

                </div>

              </div>
            </div>
          </div>

          <!-- Section 1: Built-In Watch Face Gallery -->'''

if target in text and "boxHybridChronoStudio" not in text:
    text = text.replace(target, chrono_html, 1)
    print("Hybrid Chrono Studio HTML inserted successfully")
else:
    print("Chrono Studio target not found or already inserted")

# JavaScript logic for real-time chronometer animation, arcs, and ticking
js_chrono = '''
    // =========================================================================
    // HYBRID MEDICAL CHRONOMETER PROTOTYPE ENGINE (Live Ticking & Radial Arcs)
    // =========================================================================
    let chronoSimulationMode = false;

    // Helper: Polar to Cartesian coordinate converter for SVG Arcs
    function polarToCartesian(centerX, centerY, radius, angleInDegrees) {
      const angleInRadians = (angleInDegrees - 90) * Math.PI / 180.0;
      return {
        x: centerX + (radius * Math.cos(angleInRadians)),
        y: centerY + (radius * Math.sin(angleInRadians))
      };
    }

    // Helper: Generate SVG Arc path string
    function describeArc(x, y, radius, startAngle, endAngle) {
      const start = polarToCartesian(x, y, radius, endAngle);
      const end = polarToCartesian(x, y, radius, startAngle);
      const largeArcFlag = endAngle - startAngle <= 180 ? "0" : "1";
      return [
        "M", start.x, start.y, 
        "A", radius, radius, 0, largeArcFlag, 0, end.x, end.y
      ].join(" ");
    }

    function initChronoArcs() {
      // 1. Top Glucose Arc Background: from -55 deg to +55 deg (110 deg span)
      const arcBgG = document.getElementById('arcBgGlucose');
      if (arcBgG) arcBgG.setAttribute('d', describeArc(120, 140, 105, -55, 55));

      // 2. Lower-Right Steps Arc Background: from 68 deg to 168 deg (100 deg span)
      const arcBgS = document.getElementById('arcBgSteps');
      if (arcBgS) arcBgS.setAttribute('d', describeArc(120, 140, 105, 68, 168));

      // 3. Lower-Left HR Arc Background: from 192 deg to 292 deg (100 deg span)
      const arcBgH = document.getElementById('arcBgHr');
      if (arcBgH) arcBgH.setAttribute('d', describeArc(120, 140, 105, 192, 292));
    }

    function updateChronoDial(glucose, steps, hr) {
      // 1. Update Glucose Arc (70 to 200 mg/dL mapped to -55 to +55 deg)
      const gClamped = Math.max(70, Math.min(200, glucose));
      const gPct = (gClamped - 70) / (200 - 70);
      const gEndAngle = -55 + (gPct * 110);
      const arcValG = document.getElementById('arcValGlucose');
      if (arcValG) {
        arcValG.setAttribute('d', describeArc(120, 140, 105, -55, Math.max(-54, gEndAngle)));
        // Dynamic Color: Green <130, Yellow 130-180, Red >180
        if (glucose <= 130) arcValG.setAttribute('stroke', '#55ff77');
        else if (glucose <= 180) arcValG.setAttribute('stroke', '#ffcc66');
        else arcValG.setAttribute('stroke', '#cc3333');
      }
      const elPodG = document.getElementById('chronoPodGlucose');
      if (elPodG) {
        elPodG.textContent = Math.round(glucose);
        if (glucose <= 130) elPodG.setAttribute('fill', '#55ff77');
        else if (glucose <= 180) elPodG.setAttribute('fill', '#ffcc66');
        else elPodG.setAttribute('fill', '#cc3333');
      }

      // 2. Update Steps Arc (0 to 10k Steps mapped to 68 to 168 deg)
      const sClamped = Math.max(0, Math.min(10000, steps));
      const sPct = sClamped / 10000;
      const sEndAngle = 68 + (sPct * 100);
      const arcValS = document.getElementById('arcValSteps');
      if (arcValS) arcValS.setAttribute('d', describeArc(120, 140, 105, 68, Math.max(69, sEndAngle)));
      const elPodS = document.getElementById('chronoPodSteps');
      if (elPodS) elPodS.textContent = Math.round(steps).toLocaleString();

      // 3. Update HR Arc (50 to 180 BPM mapped to 192 to 292 deg)
      const hClamped = Math.max(50, Math.min(180, hr));
      const hPct = (hClamped - 50) / (180 - 50);
      const hEndAngle = 192 + (hPct * 100);
      const arcValH = document.getElementById('arcValHr');
      if (arcValH) arcValH.setAttribute('d', describeArc(120, 140, 105, 192, Math.max(193, hEndAngle)));
      const elPodH = document.getElementById('chronoPodHr');
      if (elPodH) elPodH.textContent = Math.round(hr);
    }

    function tickChronoClock() {
      const now = new Date();
      const hours = now.getHours();
      const minutes = now.getMinutes();
      const seconds = now.getSeconds();
      const ms = now.getMilliseconds();

      // Hand rotation angles
      const secAngle = (seconds * 6) + (ms * 0.006);
      const minAngle = (minutes * 6) + (seconds * 0.1);
      const hourAngle = ((hours % 12) * 30) + (minutes * 0.5);

      const hHand = document.getElementById('handHour');
      if (hHand) hHand.setAttribute('transform', `rotate(${hourAngle}, 120, 140)`);

      const mHand = document.getElementById('handMinute');
      if (mHand) mHand.setAttribute('transform', `rotate(${minAngle}, 120, 140)`);

      const sHand = document.getElementById('handSecond');
      if (sHand) sHand.setAttribute('transform', `rotate(${secAngle}, 120, 140)`);

      // Update digital clock at 12 o'clock
      const elDig = document.getElementById('chronoDigitalTime');
      if (elDig) {
        const hh = String(hours).padStart(2, '0');
        const mm = String(minutes).padStart(2, '0');
        elDig.textContent = `${hh}:${mm}`;
      }

      // If in real sensor mode, pull live values
      if (!chronoSimulationMode) {
        const gText = document.getElementById('valGlucose')?.textContent || "124";
        const sText = document.getElementById('valStepCount')?.textContent || "8420";
        const hText = document.getElementById('valHeartRate')?.textContent || "72";

        const g = parseFloat(gText.replace(/[^0-9.]/g, '')) || 124;
        const s = parseFloat(sText.replace(/[^0-9.]/g, '')) || 8420;
        const h = parseFloat(hText.replace(/[^0-9.]/g, '')) || 72;

        updateChronoDial(g, s, h);
      }

      requestAnimationFrame(tickChronoClock);
    }

    function toggleChronoMode() {
      soundButtonPress();
      chronoSimulationMode = !chronoSimulationMode;
      const lbl = document.getElementById('lblChronoMode');
      if (chronoSimulationMode) {
        if (lbl) lbl.textContent = "MODE: TEST SLIDERS";
        updateChronoSim();
      } else {
        if (lbl) lbl.textContent = "MODE: REAL SENSORS";
      }
    }

    function updateChronoSim() {
      if (!chronoSimulationMode) {
        chronoSimulationMode = true;
        const lbl = document.getElementById('lblChronoMode');
        if (lbl) lbl.textContent = "MODE: TEST SLIDERS";
      }
      const g = parseFloat(document.getElementById('sliderChronoGlucose').value);
      const s = parseFloat(document.getElementById('sliderChronoSteps').value);
      const h = parseFloat(document.getElementById('sliderChronoHr').value);

      document.getElementById('lblTestGlucose').textContent = `${Math.round(g)} mg/dL`;
      document.getElementById('lblTestSteps').textContent = `${Math.round(s).toLocaleString()} Steps`;
      document.getElementById('lblTestHr').textContent = `${Math.round(h)} BPM`;

      updateChronoDial(g, s, h);
    }

    async function applyHybridChronoToWatch() {
      soundButtonPress();
      logLCARS("[*] Transmitting Custom Hybrid Medical Chronometer to Watch...", "tx");
      // Opcode 0xC7: apply custom watch face layout
      const pkt = [0xC7, 0x01, 0x00, 0x02];
      await sendGattCommandBytes(pkt, "Set Hybrid Chronometer Dial (0xC7)");
      document.getElementById('lblCurrentActiveFace').textContent = "CUSTOM HYBRID MEDICAL CHRONOMETER";
      document.getElementById('lblActiveFaceSource').textContent = "CUSTOM DIAL SLOT (0x02)";
      logLCARS("[+] Custom Hybrid Chronometer active on watch!", "rx");
    }

    // Initialize Chronometer on page load
    setTimeout(() => {
      initChronoArcs();
      requestAnimationFrame(tickChronoClock);
    }, 400);
'''

script_close = '</script>'
if script_close in text and "tickChronoClock" not in text:
    text = text.replace(script_close, js_chrono + '\n  ' + script_close, 1)
    print("Chrono Studio JS engine inserted successfully")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(text)
print("index.html fully updated with Hybrid Chrono Studio!")
