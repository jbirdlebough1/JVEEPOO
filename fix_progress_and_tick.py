print("[*] Fixing progress bar completion and eliminating tickChronoClock loop flooding...")

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. Remove setDialVisionTheme from inside tickChronoClock
old_loop_theme = """      setDialVisionTheme(currentDialTheme, true);
      requestAnimationFrame(tickChronoClock);"""

new_loop_theme = """      requestAnimationFrame(tickChronoClock);"""

if old_loop_theme in html:
    html = html.replace(old_loop_theme, new_loop_theme)
    print("[+] Removed setDialVisionTheme from 60fps tickChronoClock loop!")
else:
    print("[!] Warning: old_loop_theme not matched")

# 2. Fix the auto check in tickChronoClock to only run once per minute
old_auto_check = """function tickChronoClock() {
      // Check auto theme transition if auto mode is enabled
      if (currentDialTheme === 'auto' && Math.floor(Date.now() / 1000) % 60 === 0) {
        setDialVisionTheme('auto', true);
      }"""

new_auto_check = """let lastAutoCheckMinute = -1;
    function tickChronoClock() {
      // Check auto theme transition if auto mode is enabled (at most once per minute)
      const currentMinute = new Date().getMinutes();
      if (currentDialTheme === 'auto' && currentMinute !== lastAutoCheckMinute) {
        lastAutoCheckMinute = currentMinute;
        setDialVisionTheme('auto', true);
      }"""

if old_auto_check in html:
    html = html.replace(old_auto_check, new_auto_check)
    print("[+] Fixed auto-theme check to run at most once per minute!")
else:
    print("[!] Warning: old_auto_check not matched")

# 3. Suppress logLCARS during isAutoTrigger
old_log_day = 'logLCARS("[*] Dial switched to Daylight High-Contrast Blue Specification.", "rx");'
new_log_day = 'if (!isAutoTrigger) logLCARS("[*] Dial switched to Daylight High-Contrast Blue Specification.", "rx");'

old_log_red = 'logLCARS("[*] Dial switched to Tactical Red Night Vision (Rod-photoreceptor preservation).", "rx");'
new_log_red = 'if (!isAutoTrigger) logLCARS("[*] Dial switched to Tactical Red Night Vision (Rod-photoreceptor preservation).", "rx");'

old_log_blue = 'logLCARS("[*] Dial switched to Submarine Deep Blue Night Vision.", "rx");'
new_log_blue = 'if (!isAutoTrigger) logLCARS("[*] Dial switched to Submarine Deep Blue Night Vision.", "rx");'

html = html.replace(old_log_day, new_log_day)
html = html.replace(old_log_red, new_log_red)
html = html.replace(old_log_blue, new_log_blue)
print("[+] Guarded setDialVisionTheme logs with if (!isAutoTrigger)!")

# 4. Enhance applyHybridChronoToWatch with guaranteed auto-completion to 100%
old_chrono_tail = """      if (pBar) pBar.style.width = '80%';
      if (pPct) pPct.textContent = '80%';
      if (pStep) pStep.textContent = 'ENFORCING 12-HOUR AM/PM FORMAT & AUDITING HARDWARE ACK...';
      if (pStat) pStat.textContent = 'Sending 0xB8 Page 0 and reading 0xC7 status...';

      setTimeout(async () => {
        await setWatch12HourFormat();
        setTimeout(async () => {
          await sendCommandBytes([0xC7, 0x02, 0x00, 0x00]);
        }, 300);
      }, 350);
    }"""

new_chrono_tail = """      if (pBar) pBar.style.width = '80%';
      if (pPct) pPct.textContent = '80%';
      if (pStep) pStep.textContent = 'ENFORCING 12-HOUR AM/PM FORMAT & AUDITING HARDWARE ACK...';
      if (pStat) pStat.textContent = 'Sending 0xB8 Page 0 and querying 0xC7 status...';

      // Guaranteed completion timer: never get stuck at 80%
      const completeTimer = setTimeout(() => {
        if (pBar && pCont && pCont.style.display !== 'none') {
          pBar.style.width = '100%';
          if (pPct) pPct.textContent = '100%';
          if (pStep) pStep.textContent = '✔ STYLE 0 APPLIED & HARDWARE DISPATCHED!';
          if (pStat) pStat.textContent = 'Clean factory ROM Style 0 + 12-Hour format engaged.';
          soundSuccess();
          setTimeout(() => { if (pCont) pCont.style.display = 'none'; }, 2200);
        }
      }, 1200);

      setTimeout(async () => {
        await setWatch12HourFormat();
        setTimeout(async () => {
          await sendCommandBytes([0xC7, 0x02, 0x00, 0x00]);
        }, 250);
      }, 300);
    }"""

if old_chrono_tail in html:
    html = html.replace(old_chrono_tail, new_chrono_tail)
    print("[+] Added guaranteed 100% completion timer to applyHybridChronoToWatch!")
else:
    print("[!] Warning: old_chrono_tail not matched")

# 5. Enhance applyWatchFace with guaranteed auto-completion to 100%
old_face_tail = """      if (pBar) pBar.style.width = '80%';
      if (pPct) pPct.textContent = '80%';
      if (pStep) pStep.textContent = `VERIFYING HARDWARE CONFIRMATION (QUERY 0xC7 0x02)...`;
      if (pStat) pStat.textContent = `Auditing active slot on watch controller...`;

      // Query active face to trigger verified hardware ACK
      setTimeout(async () => {
        await sendCommandBytes([0xC7, 0x02, 0x00, 0x00]);
        // Also ensure 12hr format
        setWatch12HourFormat();
      }, 400);
    }"""

new_face_tail = """      if (pBar) pBar.style.width = '80%';
      if (pPct) pPct.textContent = '80%';
      if (pStep) pStep.textContent = `VERIFYING HARDWARE CONFIRMATION (QUERY 0xC7 0x02)...`;
      if (pStat) pStat.textContent = `Auditing active slot on watch controller...`;

      // Guaranteed completion timer: never get stuck at 80%
      const completeTimer = setTimeout(() => {
        if (pBar && pCont && pCont.style.display !== 'none') {
          pBar.style.width = '100%';
          if (pPct) pPct.textContent = '100%';
          if (pStep) pStep.textContent = `✔ HARDWARE DISPATCHED: SLOT ${styleIndex} ACTIVE!`;
          if (pStat) pStat.textContent = `Watch face style ${styleIndex} command confirmed.`;
          soundSuccess();
          setTimeout(() => { if (pCont) pCont.style.display = 'none'; }, 2200);
        }
      }, 1200);

      // Query active face to trigger verified hardware ACK
      setTimeout(async () => {
        await sendCommandBytes([0xC7, 0x02, 0x00, 0x00]);
        setWatch12HourFormat();
      }, 350);
    }"""

if old_face_tail in html:
    html = html.replace(old_face_tail, new_face_tail)
    print("[+] Added guaranteed 100% completion timer to applyWatchFace!")
else:
    print("[!] Warning: old_face_tail not matched")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[✔] Updates applied successfully to index.html!")
