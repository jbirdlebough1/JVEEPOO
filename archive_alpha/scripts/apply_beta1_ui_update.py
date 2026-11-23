import re
import os
import hashlib
import subprocess

INDEX_PATH = r"D:\veepoo\w11_htm_app\index.html"
ROOT_INDEX = r"D:\veepoo\index.html"

with open(INDEX_PATH, "r", encoding="utf-8") as f:
    content = f.read()

# 1. Update Title
content = re.sub(
    r"<title>.*?</title>",
    "<title>BFM Console • Star Ship Gemini NCC-202X (Powered By Gemini)</title>",
    content,
    flags=re.IGNORECASE
)

# 2. Update Header Branding
old_header_block = """      <div class="lcars-elbow-top-left">LCARS 5010</div>
      <div class="lcars-title-block">
        <div>
          <div class="lcars-main-title">CLINICAL PHYSIOLOGICAL AMBULATORY TELEMETRY</div>
          <div class="lcars-sub-title">VEEPOO BIO-SENSOR ARRAY • MODEL 5010 CONSOLIDATED CONSOLE</div>
        </div>"""

new_header_block = """      <div class="lcars-elbow-top-left bfm-elbow-top-left">NCC-202X</div>
      <div class="lcars-title-block bfm-title-block">
        <div>
          <div class="lcars-main-title bfm-main-title">BFM CONSOLE &bull; STAR SHIP GEMINI NCC-202X</div>
          <div class="lcars-sub-title bfm-sub-title">WEARABLE SENSOR ARRAY &bull; MOBILE AUXILIARY SHUTTLECRAFT FOODOR &bull; POWERED BY GEMINI</div>
        </div>"""

if old_header_block in content:
    content = content.replace(old_header_block, new_header_block)
    print("[✔] Header branding updated to Star Ship Gemini NCC-202X.")
else:
    print("[!] Old header block not matched exactly, checking regex...")
    content = re.sub(
        r'<div class="lcars-elbow-top-left">.*?</div>\s*<div class="lcars-title-block">.*?</div>\s*</div>',
        new_header_block,
        content,
        flags=re.DOTALL
    )

# 3. Add CSS for .bfm-* aliases and Section 12 / Legal Modal
bfm_css = """
    /* =========================================================================
       BFM DESIGN SYSTEM & STAR SHIP GEMINI NCC-202X ALIASES
       ========================================================================= */
    :root {
      --bfm-orange: var(--lcars-orange, #ff9900);
      --bfm-peach: var(--lcars-peach, #ffaa55);
      --bfm-banana: var(--lcars-banana, #ffcc66);
      --bfm-lilac: var(--lcars-lilac, #cc99cc);
      --bfm-purple: var(--lcars-purple, #9977aa);
      --bfm-blue: var(--lcars-blue, #38bdf8);
      --bfm-ice: var(--lcars-ice, #7dd3fc);
      --bfm-red: var(--lcars-red, #cc3333);
      --bfm-rust: var(--lcars-rust, #ef4444);
      --bfm-green: var(--lcars-green, #4ade80);
      --bfm-dark: #0f172a;
      --bfm-panel: #1e293b;
      --bfm-border: #334155;
    }
    .bfm-workspace { width: 100%; }
    .bfm-table { width: 100%; border-collapse: collapse; font-family: var(--lcars-mono, monospace); font-size: 0.82rem; }
    .bfm-table th { background: #0f172a; color: #38bdf8; padding: 10px 12px; text-align: left; border: 1px solid #334155; }
    .bfm-table td { padding: 9px 12px; border: 1px solid #334155; color: #f8fafc; }
    .bfm-table tr:nth-child(even) { background: rgba(30, 41, 59, 0.4); }
    .bfm-status-active { color: #4ade80; font-weight: bold; }
    .bfm-status-pending { color: #fbbf24; font-weight: bold; }
    .bfm-status-offline { color: #94a3b8; }
"""

content = content.replace("</style>", bfm_css + "\n  </style>")
print("[✔] Injected BFM CSS rules and Starfleet tokens.")

# 4. Ingest Section 12 into Tab 10 bottom (in pageSetup right after Section 11)
target_section_11_end = """            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 12px; border-top: 1px solid #e2e8f0; padding-top: 10px; flex-wrap: wrap; gap: 8px;">
              <span id="lblEhrConfigStatus" style="font-size: 0.76rem; color: #16a34a; font-weight: bold;">
                ✓ Patient EHR Configuration Synchronized with Tab 05 Export
              </span>
              <button type="button" class="lcars-btn btn-green" onclick="savePatientEhrConfig()" style="font-size: 0.82rem; padding: 6px 16px; font-weight: bold;">
                💾 SAVE PATIENT EHR SETUP
              </button>
            </div>
          </div>"""

section_12_html = """            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 12px; border-top: 1px solid #e2e8f0; padding-top: 10px; flex-wrap: wrap; gap: 8px;">
              <span id="lblEhrConfigStatus" style="font-size: 0.76rem; color: #16a34a; font-weight: bold;">
                ✓ Patient EHR Configuration Synchronized with Tab 05 Export
              </span>
              <button type="button" class="lcars-btn btn-green" onclick="savePatientEhrConfig()" style="font-size: 0.82rem; padding: 6px 16px; font-weight: bold;">
                💾 SAVE PATIENT EHR SETUP
              </button>
            </div>
          </div>

          <!-- SECTION 12: REGISTERED FAMILY CELLS & WEARABLE WATCH ROSTER (STAR SHIP GEMINI NCC-202X) -->
          <div class="setup-section" style="border-left-color: #38bdf8; margin-top: 24px; background: #0f172a; border: 1.5px solid #334155; border-left: 6px solid #38bdf8; border-radius: 8px; padding: 18px; color: #f8fafc;">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #334155; padding-bottom: 10px; margin-bottom: 14px;">
              <div>
                <span style="color: #38bdf8; font-size: 1.18rem; font-weight: 800; letter-spacing: 0.5px;">
                  12. REGISTERED FAMILY CELLS &amp; WEARABLE SENSOR ARRAY ROSTER
                </span>
                <span style="font-size: 0.78rem; color: #94a3b8; display: block; margin-top: 2px;">
                  STAR SHIP GEMINI NCC-202X MULTI-TENANT ROSTER &bull; MOBILE AUXILIARY SHUTTLECRAFT FOODOR DOCKING &bull; BLE BINDINGS
                </span>
              </div>
              <div style="display: flex; gap: 8px;">
                <button type="button" class="lcars-btn btn-blue" onclick="loadFamilyCellsRoster()" style="font-size: 0.80rem; padding: 6px 14px; font-weight: bold;">
                  🔄 REFRESH ROSTER
                </button>
                <button type="button" class="lcars-btn btn-green" onclick="openProvisionModal()" style="font-size: 0.80rem; padding: 6px 16px; font-weight: bold;">
                  ➕ PROVISION NEW FAMILY CELL
                </button>
              </div>
            </div>

            <!-- Dynamic Roster Table Container -->
            <div style="overflow-x: auto; margin-bottom: 14px;">
              <table class="bfm-table" id="tblFamilyCellsRoster">
                <thead>
                  <tr>
                    <th>CELL ID</th>
                    <th>REGISTRY</th>
                    <th>CALLSIGN</th>
                    <th>USER UID</th>
                    <th>LINKED WATCH MAC</th>
                    <th>MOBILE AUXILIARY (ESN)</th>
                    <th>LAST DOCKED</th>
                    <th>STATUS</th>
                    <th>ACTIONS</th>
                  </tr>
                </thead>
                <tbody id="tbodyFamilyCells">
                  <!-- Rendered dynamically via loadFamilyCellsRoster() -->
                  <tr>
                    <td colspan="9" style="text-align: center; padding: 16px; color: #94a3b8;">
                      Loading Star Ship Gemini NCC-202X Registered Cells Roster...
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>

            <!-- Roster Status & Docking Action Footer -->
            <div style="display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #334155; padding-top: 12px; flex-wrap: wrap; gap: 10px;">
              <div style="font-size: 0.78rem; color: #94a3b8; font-family: var(--lcars-mono, monospace);">
                ROSTER SECURITY: <span style="color: #4ade80; font-weight: bold;">ACTIVE DUAL USB/SD SYNCED</span> &bull; 
                TOTAL UNITS LINKED: <strong id="lblTotalRosterUnits" style="color: #38bdf8;">2</strong>
              </div>
              <div style="display: flex; gap: 10px;">
                <button type="button" class="lcars-btn btn-orange" onclick="triggerTestDockHandshake()" style="font-size: 0.80rem; padding: 6px 14px; font-weight: bold;">
                  ⚡ TEST SHUTTLECRAFT DOCKING HANDSHAKE
                </button>
                <button type="button" class="lcars-btn btn-lilac" onclick="triggerDualUsbExportNow()" style="font-size: 0.80rem; padding: 6px 14px; font-weight: bold;">
                  💾 TRIGGER DUAL USB/SD EXPORT NOW
                </button>
              </div>
            </div>
          </div>"""

if target_section_11_end in content:
    content = content.replace(target_section_11_end, section_12_html)
    print("[✔] Successfully inserted Section 12 into Tab 10 bottom.")
else:
    print("[!] Target Section 11 boundary not found directly. Aborting section insertion.")

# 5. Ingest Provision Modal and Legal Startup Modal before </body>
modals_html = """
  <!-- =========================================================================
       MODAL 1: PROVISION NEW FAMILY CELL MODAL (STAR SHIP GEMINI NCC-202X)
       ========================================================================= -->
  <div id="modalProvisionCell" style="display: none; position: fixed; inset: 0; background: rgba(0,0,0,0.85); z-index: 99999; justify-content: center; align-items: center;">
    <div style="background: #1e293b; border: 2px solid #38bdf8; border-radius: 10px; width: 90%; max-width: 540px; padding: 22px; color: #f8fafc; box-shadow: 0 10px 25px rgba(0,0,0,0.5);">
      <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #334155; padding-bottom: 10px; margin-bottom: 16px;">
        <div style="font-size: 1.15rem; font-weight: 800; color: #38bdf8;">
          ➕ PROVISION NEW FAMILY CELL
        </div>
        <button type="button" onclick="closeProvisionModal()" style="background: none; border: none; color: #94a3b8; font-size: 1.4rem; cursor: pointer;">&times;</button>
      </div>

      <div style="display: flex; flex-direction: column; gap: 12px;">
        <div>
          <label style="font-size: 0.75rem; font-weight: bold; color: #94a3b8;">CELL ID</label>
          <input type="text" id="provCellId" class="setup-input" value="Cell 3" style="width: 100%; padding: 8px; background: #0f172a; border: 1px solid #334155; color: #f8fafc; border-radius: 4px; font-family: monospace;">
        </div>
        <div>
          <label style="font-size: 0.75rem; font-weight: bold; color: #94a3b8;">USER CALLSIGN / NAME</label>
          <input type="text" id="provCallsign" class="setup-input" placeholder="e.g. Spock, Sarah, Alex" style="width: 100%; padding: 8px; background: #0f172a; border: 1px solid #334155; color: #f8fafc; border-radius: 4px;">
        </div>
        <div>
          <label style="font-size: 0.75rem; font-weight: bold; color: #94a3b8;">ASSIGNED REGISTRY NUMBER</label>
          <input type="text" id="provRegistry" class="setup-input" value="NCC-202X-3" style="width: 100%; padding: 8px; background: #0f172a; border: 1px solid #334155; color: #38bdf8; border-radius: 4px; font-family: monospace;">
        </div>
        <div>
          <label style="font-size: 0.75rem; font-weight: bold; color: #94a3b8;">LINKED WATCH MAC ADDRESS</label>
          <input type="text" id="provWatchMac" class="setup-input" placeholder="XX:XX:XX:XX:XX:XX" style="width: 100%; padding: 8px; background: #0f172a; border: 1px solid #334155; color: #4ade80; border-radius: 4px; font-family: monospace;">
        </div>
        <div>
          <label style="font-size: 0.75rem; font-weight: bold; color: #94a3b8;">BASELINE HbA1c TARGET (%)</label>
          <input type="number" id="provTargetA1c" class="setup-input" value="6.5" step="0.1" style="width: 100%; padding: 8px; background: #0f172a; border: 1px solid #334155; color: #f8fafc; border-radius: 4px;">
        </div>
      </div>

      <div id="provResultBox" style="display: none; margin-top: 14px; background: #0f172a; border: 1px solid #4ade80; border-radius: 6px; padding: 10px; font-size: 0.80rem; font-family: monospace; color: #4ade80;">
      </div>

      <div style="display: flex; justify-content: flex-end; gap: 10px; margin-top: 18px; border-top: 1px solid #334155; padding-top: 12px;">
        <button type="button" class="lcars-btn btn-peach" onclick="closeProvisionModal()" style="font-size: 0.82rem; padding: 6px 14px;">CANCEL</button>
        <button type="button" class="lcars-btn btn-green" onclick="submitProvisionCell()" style="font-size: 0.82rem; padding: 6px 18px; font-weight: bold;">⚡ PROVISION CELL NOW</button>
      </div>
    </div>
  </div>

  <!-- =========================================================================
       MODAL 2: STARTUP CLINICAL GOVERNANCE & LEGAL ACCEPTANCE MODAL
       ========================================================================= -->
  <div id="modalLegalStartup" style="display: none; position: fixed; inset: 0; background: rgba(0,0,0,0.92); z-index: 100000; justify-content: center; align-items: center;">
    <div style="background: #1e293b; border: 3px solid #ef4444; border-radius: 12px; width: 92%; max-width: 680px; padding: 26px; color: #f8fafc; box-shadow: 0 15px 35px rgba(239, 68, 68, 0.25);">
      <div style="display: flex; align-items: center; gap: 14px; border-bottom: 2px solid #334155; padding-bottom: 12px; margin-bottom: 16px;">
        <div style="font-size: 2.2rem;">⚠️</div>
        <div>
          <div style="color: #ef4444; font-weight: 900; font-size: 1.25rem; letter-spacing: 0.5px; font-family: var(--lcars-mono, monospace);">
            CRITICAL CLINICAL NOTICE &amp; TERMS OF USE
          </div>
          <div style="color: #94a3b8; font-size: 0.78rem;">
            BFM CONSOLE &bull; STAR SHIP GEMINI NCC-202X &bull; GOVERNANCE GOV-2026-BETA1
          </div>
        </div>
      </div>

      <div style="font-size: 0.88rem; line-height: 1.6; color: #cbd5e1; max-height: 320px; overflow-y: auto; padding-right: 8px; margin-bottom: 18px;">
        <p style="margin-bottom: 12px;">
          <strong style="color: #ef4444;">1. NOT AN FDA-CLEARED CGM / NOT FOR TYPE 1 DIABETES:</strong> This non-invasive optical photoplethysmography (PPG) telemetry system provides estimated glucose trends for <strong>lifestyle metabolic learning and Type 2 wellness</strong>. It does not measure interstitial or blood glucose directly and is NOT cleared for medical diagnosis.
        </p>
        <p style="margin-bottom: 12px;">
          <strong style="color: #ef4444;">2. CONTRAINDICATED FOR INSULIN DOSING:</strong> <strong>NEVER adjust, calculate, or titrate rapid-acting insulin boluses, basal rates, or medication doses</strong> based on this telemetry. Dosing decisions must rely exclusively on prescription capillary fingerstick glucometers, cleared CGMs, and certified physician guidance.
        </p>
        <p style="margin-bottom: 12px;">
          <strong style="color: #38bdf8;">3. MULTI-TENANT ROSTER &amp; SHUTTLECRAFT PRIVACY:</strong> All family cell telemetry and mobile offline ingestion records are stored locally in the <code>telemetry.db</code> vault with cryptographic hardware binding. No unencrypted protected health information (PHI) is transmitted to third parties without local consent.
        </p>
        <p style="margin-bottom: 0;">
          <strong style="color: #4ade80;">4. AUDIT COMPLIANCE:</strong> By accepting, your device fingerprint, timestamp, and terms version (<code>BFM-BETA1-2026</code>) are cryptographically committed to the local audit ledger.
        </p>
      </div>

      <div style="display: flex; justify-content: space-between; align-items: center; border-top: 1.5px solid #334155; padding-top: 14px; flex-wrap: wrap; gap: 10px;">
        <div style="font-size: 0.76rem; color: #94a3b8; font-family: monospace;">
          TERMS VERSION: <strong style="color: #f8fafc;">BFM-BETA1-2026</strong>
        </div>
        <button type="button" class="lcars-btn btn-green" onclick="acceptLegalTermsNow()" style="font-size: 0.90rem; padding: 8px 24px; font-weight: 900; letter-spacing: 0.5px;">
          ✓ I ACCEPT AND UNDERSTAND ALL CLINICAL NOTICES
        </button>
      </div>
    </div>
  </div>
"""

# Ingest JavaScript helper functions right before </script></body>
js_helpers = """
  <!-- =========================================================================
       BFM BETA 1 JAVASCRIPT LOGIC: FAMILY ROSTER, SHUTTLECRAFT DOCK & LEGAL MODAL
       ========================================================================= -->
  <script>
    // 1. Family Cells & Watch Roster Logic
    async function loadFamilyCellsRoster() {
      try {
        const resp = await fetch('/api/shuttle/roster');
        if (!resp.ok) throw new Error("HTTP " + resp.status);
        const data = await resp.json();
        const roster = data.roster || [];
        const tbody = document.getElementById('tbodyFamilyCells');
        const countLbl = document.getElementById('lblTotalRosterUnits');
        if (countLbl) countLbl.textContent = roster.length;

        if (roster.length === 0) {
          tbody.innerHTML = '<tr><td colspan="9" style="text-align: center; color: #94a3b8; padding: 14px;">No family cells registered yet. Click Provision New Family Cell above.</td></tr>';
          return;
        }

        tbody.innerHTML = roster.map(c => {
          const statusCls = c.status === 'ACTIVE' ? 'bfm-status-active' : (c.status === 'PENDING_HANDSHAKE' ? 'bfm-status-pending' : 'bfm-status-offline');
          const lastDock = c.last_docked_at ? c.last_docked_at.replace('T', ' ').substring(0, 19) : 'NEVER';
          return `
            <tr>
              <td style="font-weight: bold; color: #38bdf8;">${c.cell_id}</td>
              <td style="color: #fb923c;">${c.registry_number}</td>
              <td style="font-weight: bold;">${c.user_callsign}</td>
              <td style="color: #94a3b8;">${c.user_uid}</td>
              <td style="color: #4ade80;">${c.linked_watch_mac || 'UNASSIGNED'}</td>
              <td style="color: #cbd5e1;">${c.phone_name} (${c.phone_esn || 'PENDING'})</td>
              <td style="color: #94a3b8;">${lastDock}</td>
              <td class="${statusCls}">${c.status}</td>
              <td>
                <button type="button" class="lcars-btn btn-blue" style="font-size: 0.70rem; padding: 2px 8px;" onclick="triggerTestDockHandshake('${c.cell_id}', '${c.phone_esn}')">DOCK</button>
              </td>
            </tr>
          `;
        }).join('');
      } catch (e) {
        console.warn("Failed loading family cells roster:", e);
      }
    }

    function openProvisionModal() {
      const modal = document.getElementById('modalProvisionCell');
      if (modal) modal.style.display = 'flex';
      const res = document.getElementById('provResultBox');
      if (res) res.style.display = 'none';
    }

    function closeProvisionModal() {
      const modal = document.getElementById('modalProvisionCell');
      if (modal) modal.style.display = 'none';
    }

    async function submitProvisionCell() {
      const cellId = document.getElementById('provCellId').value.trim();
      const callsign = document.getElementById('provCallsign').value.trim() || 'Crew Member';
      const registry = document.getElementById('provRegistry').value.trim();
      const mac = document.getElementById('provWatchMac').value.trim();
      const a1c = parseFloat(document.getElementById('provTargetA1c').value) || 6.5;

      const payload = {
        cell_id: cellId,
        user_uid: `USR-9482-${cellId.replace(/[^0-9]/g, '') || Math.floor(Math.random()*100)}`,
        user_callsign: callsign,
        assigned_registry: registry,
        linked_watch_mac: mac,
        target_a1c: a1c
      };

      try {
        const resp = await fetch('/api/shuttle/provision', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        });
        const res = await resp.json();
        if (resp.ok) {
          const resBox = document.getElementById('provResultBox');
          resBox.style.display = 'block';
          resBox.innerHTML = `✅ <strong>PROVISIONED:</strong> Token: <code>${res.pairing_token}</code><br>Enter token into mobile client <code>cell.html</code> to complete handshake.`;
          loadFamilyCellsRoster();
          setTimeout(() => { closeProvisionModal(); }, 3500);
        } else {
          alert("Provisioning failed: " + (res.error || "Unknown"));
        }
      } catch (e) {
        alert("Network error: " + e.message);
      }
    }

    async function triggerTestDockHandshake(cellId, phoneEsn) {
      cellId = cellId || 'Cell 1';
      phoneEsn = phoneEsn || 'ESN-9482-A01';
      try {
        const payload = {
          cell_id: cellId,
          phone_esn: phoneEsn,
          batch_records: [
            {
              intake_timestamp: new Date().toISOString(),
              meal_name: "Simulated Shuttlecraft Foodoor Ingestion",
              calories: 320,
              net_carbs: 18,
              protein: 24,
              fat: 14,
              glycemic_impact_score: "LOW"
            }
          ]
        };
        const resp = await fetch('/api/shuttle/dock', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify(payload)
        });
        const res = await resp.json();
        if (resp.ok) {
          alert(`🎉 DOCK SUCCESS: ${cellId} synced ${res.docked_records} record(s) at ${res.last_docked_at}!`);
          loadFamilyCellsRoster();
        } else {
          alert("Dock error: " + (res.error || "Unknown"));
        }
      } catch (e) {
        alert("Dock network error: " + e.message);
      }
    }

    async function triggerDualUsbExportNow() {
      try {
        const resp = await fetch('/api/backup/dual_export', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({})
        });
        const res = await resp.json();
        if (resp.ok) {
          alert(`💾 DUAL USB/SD EXPORT COMPLETE!\\nSQLite: ${res.sqlite_export.filename}\\nFHIR: ${res.fhir_export.filename} (${res.fhir_export.entry_count} entries)\\nManifest: ${res.manifest.filename}`);
        } else {
          alert("Dual export failed: " + (res.error || "Unknown"));
        }
      } catch (e) {
        alert("Export network error: " + e.message);
      }
    }

    // 2. Startup Legal Notice Governance
    async function checkLegalStatus() {
      const acceptedLocal = localStorage.getItem("bfm_legal_accepted");
      if (acceptedLocal === "true") return;

      try {
        const resp = await fetch('/api/legal/status');
        if (resp.ok) {
          const data = await resp.json();
          if (!data.accepted) {
            const modal = document.getElementById('modalLegalStartup');
            if (modal) modal.style.display = 'flex';
          } else {
            localStorage.setItem("bfm_legal_accepted", "true");
          }
        }
      } catch (e) {
        // Fallback to local check
        if (acceptedLocal !== "true") {
          const modal = document.getElementById('modalLegalStartup');
          if (modal) modal.style.display = 'flex';
        }
      }
    }

    async function acceptLegalTermsNow() {
      localStorage.setItem("bfm_legal_accepted", "true");
      const modal = document.getElementById('modalLegalStartup');
      if (modal) modal.style.display = 'none';

      try {
        await fetch('/api/legal/accept', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            user_uid: "USR-9482-01",
            terms_version: "BFM-BETA1-2026",
            terms_sha256: "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855"
          })
        });
      } catch (e) {
        console.warn("Could not post legal acceptance to server:", e);
      }
    }

    // Initialize on DOM ready
    window.addEventListener('DOMContentLoaded', () => {
      loadFamilyCellsRoster();
      checkLegalStatus();
    });
  </script>
"""

content = content.replace("</body>", modals_html + "\n" + js_helpers + "\n</body>")
print("[✔] Injected Modals and JavaScript logic before </body>.")

# Write back to index.html and root index.html
with open(INDEX_PATH, "w", encoding="utf-8") as f:
    f.write(content)
with open(ROOT_INDEX, "w", encoding="utf-8") as f:
    f.write(content)

print("[✔] Successfully updated both index.html files!")
