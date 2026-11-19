import re

print("[*] Updating index.html with live Apply Progress and Hardware Verification...")

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

# 1. Insert 0xC7 Telemetry Parser right after 0xA1 in onTelemetryReceived
c7_parser = """      // 0xC7: Watch Face Hardware Style ACK & Verification
      if (header === 0xC7 && bytes.length >= 3) {
        const isSuccess = bytes[1] === 0x01;
        const activeSlot = bytes.length > 2 ? bytes[2] : 0;
        const uiType = bytes.length > 3 ? bytes[3] : 0;
        const sourceName = uiType === 0x02 ? "SPI FLASH CUSTOM (0x02)" : "FIRMWARE ROM (0x00)";
        
        logLCARS(`[✔] HARDWARE WATCH FACE ACK: Slot ${activeSlot} Engaged (${sourceName})`, "rx");

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
          if (pStep) pStep.textContent = `✔ WATCH HARDWARE CONFIRMED: SLOT ${activeSlot} ACTIVE!`;
          if (pStat) pStat.textContent = `GATT ACK 0xC7: Screen controller refreshed. Hardware slot ${activeSlot} verified.`;
          soundSuccess();
          setTimeout(() => { if (pCont) pCont.style.display = 'none'; }, 3500);
        }
      }
"""

if "header === 0xC7" not in html:
    pos_a1 = html.find('// 0x87: Dual-Sensor Temperature Telemetry')
    if pos_a1 != -1:
        html = html[:pos_a1] + c7_parser + "\n      " + html[pos_a1:]
        print("[+] Added 0xC7 hardware ACK parser to onTelemetryReceived!")

# 2. Update applyWatchFace to animate progress
old_apply_face = """    async function applyWatchFace(styleIndex, styleName) {
      soundButtonPress();
      logLCARS(`[*] Transmitting Watch Face Change -> ${styleName} (Style ${styleIndex})...`, "tx");
      // Opcode 0xC7, sub-cmd 0x01 = Set Screen Style, styleIndex, uiFromType=0 (Default Firmware)
      const pkt = [0xC7, 0x01, styleIndex & 0xFF, 0x00];
      await sendGattCommandBytes(pkt, `Set Watch Face Style ${styleIndex} (0xC7)`);

      // Update UI state
      currentActiveFaceIndex = styleIndex;
      document.getElementById('lblCurrentActiveFace').textContent = styleName.toUpperCase();
      document.getElementById('lblActiveFaceSlot').textContent = `Slot ${styleIndex}`;
      
      // Highlight active card
      document.querySelectorAll('.watchface-card').forEach((card, idx) => {
        if (idx === styleIndex) {
          card.style.border = "2px solid var(--lcars-banana)";
        } else {
          card.style.border = "1px solid var(--lcars-border)";
        }
      });
      logLCARS(`[+] Watch Face Switched to Style ${styleIndex}!`, "rx");
    }"""

new_apply_face = """    async function applyWatchFace(styleIndex, styleName) {
      soundButtonPress();
      const pCont = document.getElementById('applyProgressContainer');
      const pBar = document.getElementById('applyProgressBar');
      const pPct = document.getElementById('lblApplyPct');
      const pStep = document.getElementById('lblApplyStep');
      const pStat = document.getElementById('lblApplyStatus');

      if (pCont) pCont.style.display = 'block';
      if (pBar) pBar.style.width = '20%';
      if (pPct) pPct.textContent = '20%';
      if (pStep) pStep.textContent = `CONNECTING TO FITAOS HARDWARE...`;
      if (pStat) pStat.textContent = `Targeting Slot ${styleIndex} (${styleName})...`;

      const ok = await ensureConnected();
      if (!ok) {
        if (pStep) pStep.textContent = 'BLUETOOTH LINK FAILED';
        if (pStat) pStat.textContent = 'Ensure watch screen is turned on and in range.';
        soundError();
        return;
      }

      if (pBar) pBar.style.width = '50%';
      if (pPct) pPct.textContent = '50%';
      if (pStep) pStep.textContent = `TRANSMITTING OPCODE 0xC7 (SET SLOT ${styleIndex})...`;
      if (pStat) pStat.textContent = `Sending 20-byte command packet to BLE RX...`;

      // Opcode 0xC7, sub-cmd 0x01 = Set Screen Style, styleIndex, uiFromType=0 (Default Firmware)
      const pkt = [0xC7, 0x01, styleIndex & 0xFF, 0x00];
      await sendGattCommandBytes(pkt, `Set Watch Face Style ${styleIndex} (0xC7)`);

      // Update UI state
      currentActiveFaceIndex = styleIndex;
      document.getElementById('lblCurrentActiveFace').textContent = styleName.toUpperCase();
      document.getElementById('lblActiveFaceSlot').textContent = `Slot ${styleIndex}`;
      
      // Highlight active card
      document.querySelectorAll('.watchface-card').forEach((card, idx) => {
        if (idx === styleIndex) {
          card.style.border = "2px solid var(--lcars-banana)";
        } else {
          card.style.border = "1px solid var(--lcars-border)";
        }
      });

      if (pBar) pBar.style.width = '80%';
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

if old_apply_face in html:
    html = html.replace(old_apply_face, new_apply_face)
    print("[+] Updated applyWatchFace with live progress and hardware verification!")
else:
    print("[!] Warning: old_apply_face did not match")

# 3. Update applyHybridChronoToWatch with live progress
pattern_chrono = re.compile(r'function applyHybridChronoToWatch\(\)\s*\{.*?\n    \}', re.DOTALL)
new_apply_chrono = """async function applyHybridChronoToWatch() {
      soundButtonPress();
      const pCont = document.getElementById('applyProgressContainer');
      const pBar = document.getElementById('applyProgressBar');
      const pPct = document.getElementById('lblApplyPct');
      const pStep = document.getElementById('lblApplyStep');
      const pStat = document.getElementById('lblApplyStatus');

      if (pCont) pCont.style.display = 'block';
      if (pBar) pBar.style.width = '20%';
      if (pPct) pPct.textContent = '20%';
      if (pStep) pStep.textContent = 'AWAKENING WATCH GATT DISPLAY PIPELINE...';
      if (pStat) pStat.textContent = 'Checking Bluetooth link to FITAOS...';

      const ok = await ensureConnected();
      if (!ok) {
        if (pStep) pStep.textContent = 'BLUETOOTH LINK FAILED';
        if (pStat) pStat.textContent = 'Ensure watch screen is turned on and in range.';
        soundError();
        return;
      }

      if (pBar) pBar.style.width = '50%';
      if (pPct) pPct.textContent = '50%';
      if (pStep) pStep.textContent = 'TRANSMITTING BIO-METABOLIC DIAL COMMAND (0xC7 0x01 0x00 0x00)...';
      if (pStat) pStat.textContent = 'Restoring clean factory ROM Style 0...';

      // Opcode 0xC7, sub-cmd 0x01 = Set Screen Style, styleIndex 0x00, uiFromType 0x00 (Clean Default ROM Face)
      const pkt = [0xC7, 0x01, 0x00, 0x00];
      await sendCommandBytes(pkt);

      document.getElementById('lblCurrentActiveFace').textContent = "STYLE 0: BIO-METABOLIC LCARS CONSOLE";
      document.getElementById('lblActiveFaceSource').textContent = "FIRMWARE ROM DEFAULT (0x00)";
      logLCARS("[+] Clean Factory Style 0 command sent to watch!", "rx");

      if (pBar) pBar.style.width = '80%';
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

new_html, cnt = pattern_chrono.subn(new_apply_chrono, html, count=1)
if cnt > 0:
    html = new_html
    print("[+] Updated applyHybridChronoToWatch with live progress!")
else:
    print("[!] Warning: pattern_chrono did not match")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[✔] Successfully committed progress and verification updates to index.html!")
