with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    s = f.read()

target = '<button class="lcars-pill pill-green" style="font-size: 0.8rem; padding: 6px 14px; height: auto; cursor: pointer;" onclick="toggleChronoMode()">'

replacement = """<!-- NIGHT VISION THEME SELECTOR -->
                <div style="display: flex; gap: 4px; background: rgba(0,0,0,0.4); padding: 3px 6px; border-radius: 20px; border: 1px solid #1e3a5f; align-items: center;">
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

                <button class="lcars-pill pill-green" style="font-size: 0.8rem; padding: 6px 14px; height: auto; cursor: pointer;" onclick="toggleChronoMode()">"""

if target in s:
    s = s.replace(target, replacement)
    print("SUCCESS: Inserted Night Vision Theme Buttons!")
    with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
        f.write(s)
else:
    print("WARNING: target not matched!")
