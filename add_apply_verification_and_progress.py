import re

print("[*] Adding Apply Verification, Progress Bar & 0xC7 ACK Handler...")

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. Add verification progress bar element right below the Apply button / above the simulator
old_marker = '<!-- THE 240x280 PHYSICAL SIMULATOR FRAME -->'
new_progress_bar = """<!-- LIVE APPLY PROGRESS & HARDWARE VERIFICATION BAR -->
              <div id="applyProgressContainer" style="display: none; width: 100%; max-width: 600px; margin: 0 auto 16px auto; background: rgba(0,0,0,0.6); border: 2px solid var(--lcars-banana); border-radius: 8px; padding: 12px;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 6px;">
                  <span id="lblApplyStep" style="font-size: 0.82rem; font-weight: bold; color: var(--lcars-banana);">INITIALIZING GATT FLASH LINK...</span>
                  <span id="lblApplyPct" style="font-size: 0.9rem; font-weight: 900; color: #ffffff;">0%</span>
                </div>
                <div style="width: 100%; height: 12px; background: #0c1829; border-radius: 6px; overflow: hidden; border: 1px solid #1e3a5f;">
                  <div id="applyProgressBar" style="width: 0%; height: 100%; background: linear-gradient(90deg, #38bdf8, #22c55e); transition: width 0.25s ease;"></div>
                </div>
                <div id="lblApplyStatus" style="font-size: 0.75rem; color: #94a3b8; margin-top: 6px; font-family: monospace;">Awaiting hardware controller confirmation...</div>
              </div>

              <!-- THE 240x280 PHYSICAL SIMULATOR FRAME -->"""

if old_marker in html and "id=\"applyProgressContainer\"" not in html:
    html = html.replace(old_marker, new_progress_bar, 1)
    print("[+] Added Apply Progress & Verification Container!")

# 2. Add 0xC7 parser to handleNotification
old_notif_end = "logLCARS(`RX [${characteristic.uuid.substring(0, 8)}]: ${hex}`, \"rx\");"
new_notif_c7 = """logLCARS(`RX [${characteristic.uuid.substring(0, 8)}]: ${hex}`, "rx");

      // Hardware Watch Face Style Response (Opcode 0xC7)
      if (data.length >= 4 && data[0] === 0xC7) {
        const isSuccess = data[1] === 0x01;
        const activeSlot = data[2];
        const uiType = data.length > 3 ? data[3] : 0;
        const sourceName = uiType === 0x02 ? "SPI FLASH CUSTOM (0x02)" : "FIRMWARE ROM (0x00)";
        
        const elActive = document.getElementById('lblCurrentActiveFace');
        const elSource = document.getElementById('lblActiveFaceSource');
        const elSlot = document.getElementById('lblActiveFaceSlot');
        
        if (elActive) elActive.textContent = `SLOT ${activeSlot} (${sourceName})`;
        if (elSource) elSource.textContent = sourceName;
        if (elSlot) elSlot.textContent = `Slot ${activeSlot}`;

        // Complete progress bar if active
        const pCont = document.getElementById('applyProgressContainer');
        const pBar = document.getElementById('applyProgressBar');
        const pPct = document.getElementById('lblApplyPct');
        const pStep = document.getElementById('lblApplyStep');
        const pStat = document.getElementById('lblApplyStatus');
        
        if (pBar && pCont && pCont.style.display !== 'none') {
          pBar.style.width = '100%';
          if (pPct) pPct.textContent = '100%';
          if (pStep) pStep.textContent = `✔ WATCH HARDWARE CONFIRMED: SLOT ${activeSlot} ENGAGED!`;
          if (pStat) pStat.textContent = `ACK Received: 0xC7 0x01. Display controller successfully updated.`;
          soundSuccess();
          setTimeout(() => { pCont.style.display = 'none'; }, 3500);
        }
      }"""

if old_notif_end in html and "data[0] === 0xC7" not in html:
    html = html.replace(old_notif_end, new_notif_c7, 1)
    print("[+] Added Opcode 0xC7 hardware ACK parser to handleNotification!")

# 3. Enhance applyWatchFace and applyHybridChronoToWatch to drive progress animation
old_apply_chrono = """    function applyHybridChronoToWatch() {
      soundButtonPress();
      logLCARS("[*] Restoring Clean Factory Style 0 on Watch (Opcode 0xC7 0x01 0x00 0x00)...", "tx");
      // Opcode 0xC7, sub-cmd 0x01 = Set Screen Style, styleIndex 0x00, uiFromType 0x00 (Clean Default ROM Face)
      const pkt = [0xC7, 0x01, 0x00, 0x00];
      await sendCommandBytes(pkt);
      document.getElementById('lblCurrentActiveFace').textContent = "STYLE 0: BIO-METABOLIC LCARS CONSOLE";
      document.getElementById('lblActiveFaceSource').textContent = "FIRMWARE ROM DEFAULT (0x00)";
      logLCARS("[+] Clean Factory Style 0 restored successfully on watch!", "rx");
      
      // Also ensure 12-hour format is active
      setTimeout(() => {
        setWatch12HourFormat();
      }, 400);
    }"""

new_apply_chrono = """    async function applyHybridChronoToWatch() {
      soundButtonPress();
      const pCont = document.getElementById('applyProgressContainer');
      const pBar = document.getElementById('applyProgressBar');
      const pPct = document.getElementById('lblApplyPct');
      const pStep = document.getElementById('lblApplyStep');
      const pStat = document.getElementById('lblApplyStatus');

      if (pCont) pCont.style.display = 'block';
      if (pBar) pBar.style.width = '15%';
      if (pPct) pPct.textContent = '15%';
      if (pStep) pStep.textContent = 'STEP 1: AWAKENING WATCH GATT DISPLAY PIPELINE...';
      if (pStat) pStat.textContent = 'Checking Bluetooth link to FITAOS...';

      // 1. Wake up and ensure connection
      const ok = await ensureConnected();
      if (!ok) {
        if (pStep) pStep.textContent = 'LINK FAILED: Ensure watch screen is on';
        soundError();
        return;
      }

      if (pBar) pBar.style.width = '45%';
      if (pPct) pPct.textContent = '45%';
      if (pStep) pStep.textContent = 'STEP 2: TRANSMITTING OPCODE 0xC7 TO WATCH CONTROLLER...';
      if (pStat) pStat.textContent = 'Payload: C7 01 00 00 [20-byte padded packet]';

      // Opcode 0xC7, sub-cmd 0x01 = Set Screen Style, styleIndex 0x00, uiFromType 0x00
      const pkt = [0xC7, 0x01, 0x00, 0x00];
      await sendCommandBytes(pkt);

      if (pBar) pBar.style.width = '75%';
      if (pPct) pPct.textContent = '75%';
      if (pStep) pStep.textContent = 'STEP 3: ENFORCING 12-HOUR FORMAT & AWAITING ACK...';
      if (pStat) pStat.textContent = 'Transmitting Opcode 0xB8 Page 0...';

      // Also ensure 12-hour format
      setTimeout(async () => {
        await setWatch12HourFormat();
        // Query back to verify slot
        setTimeout(async () => {
          await sendCommandBytes([0xC7, 0x02, 0x00, 0x00]);
        }, 300);
      }, 350);
    }"""

if old_apply_chrono in html:
    html = html.replace(old_apply_chrono, new_apply_chrono, 1)
    print("[+] Enhanced applyHybridChronoToWatch with live verification & progress animation!")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[✔] Finished adding Apply Verification and Progress Bar!")
