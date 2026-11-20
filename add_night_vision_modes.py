import re

print("[*] Preparing Night Vision & Pulse Label Update Script...")

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. Replace PULSE / HR with PULSE
if "PULSE / HR" in html:
    html = html.replace("PULSE / HR", "PULSE")
    print("[+] Replaced 'PULSE / HR' with 'PULSE'!")
else:
    print("[!] 'PULSE / HR' not found (may already be replaced)")

# 2. Add IDs to dial SVG elements for theme switching
# Let's inspect the SVG start
old_svg_head = """<svg id="svgHybridDial" width="240" height="280" viewBox="0 0 240 280" style="background: #07152b; font-family: 'Share Tech Mono', Consolas, sans-serif;">
                    
                    <!-- HIGH-CONTRAST BLUE OUTER BEZEL PLATE -->
                    <circle cx="120" cy="140" r="118" fill="#091b36" stroke="#1d4ed8" stroke-width="2" />
                    
                    <!-- HIGH-VISIBILITY BLUE OUTER & INNER RING BORDERS (Senior High-Contrast) -->
                    <circle cx="120" cy="140" r="114" fill="none" stroke="#00d2ff" stroke-width="2" style="filter: drop-shadow(0 0 3px #00a8ff);" />
                    <circle cx="120" cy="140" r="96" fill="none" stroke="#00d2ff" stroke-width="2" style="filter: drop-shadow(0 0 3px #00a8ff);" />"""

new_svg_head = """<svg id="svgHybridDial" width="240" height="280" viewBox="0 0 240 280" style="background: #07152b; font-family: 'Share Tech Mono', Consolas, sans-serif; transition: background 0.3s ease;">
                    
                    <!-- HIGH-CONTRAST OUTER BEZEL PLATE -->
                    <circle id="dialBezelPlate" cx="120" cy="140" r="118" fill="#091b36" stroke="#1d4ed8" stroke-width="2" />
                    
                    <!-- HIGH-VISIBILITY OUTER & INNER RING BORDERS -->
                    <circle id="dialRingOuter" cx="120" cy="140" r="114" fill="none" stroke="#00d2ff" stroke-width="2" style="filter: drop-shadow(0 0 3px #00a8ff);" />
                    <circle id="dialRingInner" cx="120" cy="140" r="96" fill="none" stroke="#00d2ff" stroke-width="2" style="filter: drop-shadow(0 0 3px #00a8ff);" />"""

if old_svg_head in html:
    html = html.replace(old_svg_head, new_svg_head)
    print("[+] Added DOM IDs to dial elements for theme switching!")
else:
    print("[!] Warning: old_svg_head did not match exactly")

# 3. Add Theme Switcher Toolbar above the dial simulator
old_sim_head = """              <div style="display: flex; gap: 8px; flex-wrap: wrap;">
                <button class="lcars-pill pill-lilac" style="font-size: 0.8rem; padding: 6px 14px; height: auto;" onclick="toggleChronoMode()">
                  <span id="lblChronoMode">MODE: REAL SENSORS</span>
                </button>
                <button class="lcars-btn btn-banana" style="font-weight: bold; padding: 6px 16px;" onclick="applyHybridChronoToWatch()">
                  ⚡ APPLY TO WATCH
                </button>
              </div>"""

new_sim_head = """              <div style="display: flex; gap: 8px; flex-wrap: wrap; align-items: center;">
                <!-- NIGHT VISION THEME SELECTOR -->
                <div style="display: flex; gap: 4px; background: rgba(0,0,0,0.4); padding: 3px 6px; border-radius: 20px; border: 1px solid #1e3a5f;">
                  <button id="btnThemeDay" class="lcars-pill pill-blue" style="font-size: 0.72rem; padding: 4px 10px; height: auto; cursor: pointer; border: 2px solid #00d2ff;" onclick="setDialVisionTheme('day')">
                    <span>☀️ DAY BLUE</span>
                  </button>
                  <button id="btnThemeRedNight" class="lcars-pill pill-red" style="font-size: 0.72rem; padding: 4px 10px; height: auto; cursor: pointer;" onclick="setDialVisionTheme('red_night')" title="Tactical Red Night Vision: Preserves retinal rod dark adaptation">
                    <span>🔴 RED NIGHT</span>
                  </button>
                  <button id="btnThemeBlueNight" class="lcars-pill pill-blue" style="font-size: 0.72rem; padding: 4px 10px; height: auto; cursor: pointer;" onclick="setDialVisionTheme('blue_night')" title="Submarine Deep Blue Night Vision">
                    <span>🔵 DEEP BLUE</span>
                  </button>
                  <button id="btnThemeAutoNight" class="lcars-pill pill-lilac" style="font-size: 0.72rem; padding: 4px 10px; height: auto; cursor: pointer;" onclick="setDialVisionTheme('auto')" title="Auto-Night: Switches to Red Night Vision from 21:00 to 07:00">
                    <span id="lblThemeAuto">🌙 AUTO-NIGHT</span>
                  </button>
                </div>

                <button class="lcars-pill pill-lilac" style="font-size: 0.8rem; padding: 6px 14px; height: auto;" onclick="toggleChronoMode()">
                  <span id="lblChronoMode">MODE: REAL SENSORS</span>
                </button>
                <button class="lcars-btn btn-banana" style="font-weight: bold; padding: 6px 16px;" onclick="applyHybridChronoToWatch()">
                  ⚡ APPLY TO WATCH
                </button>
              </div>"""

if old_sim_head in html:
    html = html.replace(old_sim_head, new_sim_head)
    print("[+] Added Night Vision Theme Selector Toolbar above dial simulator!")
else:
    print("[!] Warning: old_sim_head did not match exactly")

# 4. Add setDialVisionTheme function in script
theme_js = """
    // =========================================================================
    // DIAL VISION MODES (DAY BLUE, TACTICAL RED NIGHT, DEEP BLUE NIGHT, AUTO)
    // =========================================================================
    let currentDialTheme = localStorage.getItem('veepoo_dial_theme') || 'day';

    function setDialVisionTheme(theme, isAutoTrigger = false) {
      if (!isAutoTrigger) {
        soundButtonPress();
        currentDialTheme = theme;
        localStorage.setItem('veepoo_dial_theme', theme);
      }

      let activeMode = theme;
      if (theme === 'auto') {
        const hr = new Date().getHours();
        activeMode = (hr >= 21 || hr < 7) ? 'red_night' : 'day';
        const lbl = document.getElementById('lblThemeAuto');
        if (lbl) lbl.textContent = `🌙 AUTO (${activeMode === 'red_night' ? 'RED NIGHT' : 'DAY BLUE'})`;
      }

      // Highlight active button
      ['btnThemeDay', 'btnThemeRedNight', 'btnThemeBlueNight', 'btnThemeAutoNight'].forEach(btnId => {
        const btn = document.getElementById(btnId);
        if (btn) btn.style.border = "1px solid transparent";
      });
      if (theme === 'day') {
        const b = document.getElementById('btnThemeDay'); if (b) b.style.border = "2px solid #00d2ff";
      } else if (theme === 'red_night') {
        const b = document.getElementById('btnThemeRedNight'); if (b) b.style.border = "2px solid #ef4444";
      } else if (theme === 'blue_night') {
        const b = document.getElementById('btnThemeBlueNight'); if (b) b.style.border = "2px solid #38bdf8";
      } else if (theme === 'auto') {
        const b = document.getElementById('btnThemeAutoNight'); if (b) b.style.border = "2px solid var(--lcars-lilac)";
      }

      const svgDial = document.getElementById('svgHybridDial');
      const bezel = document.getElementById('dialBezelPlate');
      const rOuter = document.getElementById('dialRingOuter');
      const rInner = document.getElementById('dialRingInner');
      const groove = document.getElementById('trackGroove');
      const hHandPoly = document.querySelector('#handHour polygon');
      const hHandLine = document.querySelector('#handHour line');
      const mHandPoly = document.querySelector('#handMinute polygon');
      const mHandLine = document.querySelector('#handMinute line');
      const centerPin = document.querySelector('#svgHybridDial circle[r="4"]');

      if (!svgDial) return;

      if (activeMode === 'red_night') {
        // TACTICAL BRIDGE RED NIGHT VISION (Preserves Rhodopsin & Night Vision)
        svgDial.style.background = "#140003";
        if (bezel) { bezel.setAttribute('fill', '#1f0105'); bezel.setAttribute('stroke', '#7f1d1d'); }
        if (rOuter) { rOuter.setAttribute('stroke', '#ef4444'); rOuter.style.filter = "drop-shadow(0 0 4px #dc2626)"; }
        if (rInner) { rInner.setAttribute('stroke', '#ef4444'); rInner.style.filter = "drop-shadow(0 0 4px #dc2626)"; }
        if (groove) groove.setAttribute('stroke', '#2b0308');
        if (hHandPoly) { hHandPoly.setAttribute('fill', '#ff3344'); hHandPoly.setAttribute('stroke', '#000'); }
        if (hHandLine) { hHandLine.setAttribute('stroke', '#ffd1d5'); }
        if (mHandPoly) { mHandPoly.setAttribute('fill', '#fca5a5'); mHandPoly.setAttribute('stroke', '#000'); }
        if (mHandLine) { mHandLine.setAttribute('stroke', '#ef4444'); }
        if (centerPin) { centerPin.setAttribute('fill', '#ff3344'); }
        logLCARS("[*] Dial switched to Tactical Red Night Vision (Rod-photoreceptor preservation).", "rx");
      } else if (activeMode === 'blue_night') {
        // DEEP SUBMARINE BLUE NIGHT VISION
        svgDial.style.background = "#030a16";
        if (bezel) { bezel.setAttribute('fill', '#051329'); bezel.setAttribute('stroke', '#0369a1'); }
        if (rOuter) { rOuter.setAttribute('stroke', '#38bdf8'); rOuter.style.filter = "drop-shadow(0 0 4px #0284c7)"; }
        if (rInner) { rInner.setAttribute('stroke', '#38bdf8'); rInner.style.filter = "drop-shadow(0 0 4px #0284c7)"; }
        if (groove) groove.setAttribute('stroke', '#061a38');
        if (hHandPoly) { hHandPoly.setAttribute('fill', '#ffe600'); hHandPoly.setAttribute('stroke', '#000'); }
        if (hHandLine) { hHandLine.setAttribute('stroke', '#ffffff'); }
        if (mHandPoly) { mHandPoly.setAttribute('fill', '#93c5fd'); mHandPoly.setAttribute('stroke', '#000'); }
        if (mHandLine) { mHandLine.setAttribute('stroke', '#38bdf8'); }
        if (centerPin) { centerPin.setAttribute('fill', '#ffe600'); }
        logLCARS("[*] Dial switched to Submarine Deep Blue Night Vision.", "rx");
      } else {
        // DAYLIGHT SPEC (BLUE BACKGROUND + HIGH CONTRAST)
        svgDial.style.background = "#07152b";
        if (bezel) { bezel.setAttribute('fill', '#091b36'); bezel.setAttribute('stroke', '#1d4ed8'); }
        if (rOuter) { rOuter.setAttribute('stroke', '#00d2ff'); rOuter.style.filter = "drop-shadow(0 0 3px #00a8ff)"; }
        if (rInner) { rInner.setAttribute('stroke', '#00d2ff'); rInner.style.filter = "drop-shadow(0 0 3px #00a8ff)"; }
        if (groove) groove.setAttribute('stroke', '#0d2346');
        if (hHandPoly) { hHandPoly.setAttribute('fill', '#ffe600'); hHandPoly.setAttribute('stroke', '#000'); }
        if (hHandLine) { hHandLine.setAttribute('stroke', '#ffffff'); }
        if (mHandPoly) { mHandPoly.setAttribute('fill', '#ffffff'); mHandPoly.setAttribute('stroke', '#000'); }
        if (mHandLine) { mHandLine.setAttribute('stroke', '#38bdf8'); }
        if (centerPin) { centerPin.setAttribute('fill', '#ffe600'); }
        logLCARS("[*] Dial switched to Daylight High-Contrast Blue Specification.", "rx");
      }
    }
"""

if "function setDialVisionTheme" not in html:
    pos_chrono = html.find("function toggleChronoMode()")
    if pos_chrono != -1:
        html = html[:pos_chrono] + theme_js + "\n    " + html[pos_chrono:]
        print("[+] Added setDialVisionTheme function to index.html!")

# Ensure theme is initialized in initUnifiedGlucoseRing / tickChronoClock
old_init_ring = "requestAnimationFrame(tickChronoClock);"
new_init_ring = "setDialVisionTheme(currentDialTheme, true);\n      requestAnimationFrame(tickChronoClock);"

if old_init_ring in html and "setDialVisionTheme(currentDialTheme" not in html:
    html = html.replace(old_init_ring, new_init_ring, 1)
    print("[+] Hooked setDialVisionTheme into dial initialization!")

# Auto-check theme in tickChronoClock every minute if in auto mode
old_tick = "function tickChronoClock() {"
new_tick = """function tickChronoClock() {
      // Check auto theme transition if auto mode is enabled
      if (currentDialTheme === 'auto' && Math.floor(Date.now() / 1000) % 60 === 0) {
        setDialVisionTheme('auto', true);
      }"""

if old_tick in html and "currentDialTheme === 'auto'" not in html:
    html = html.replace(old_tick, new_tick, 1)
    print("[+] Hooked auto-theme evaluation into tickChronoClock!")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[✔] Night Vision & Pulse Label update completed successfully!")
