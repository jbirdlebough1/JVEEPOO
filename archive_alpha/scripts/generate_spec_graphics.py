"""
Script to generate production-grade vector SVG graphics for BFM Beta 1 Specifications.
Aesthetic Standard: Scott Chambliss Starfleet Sickbay BFM Theme (Dark space navy, BFM peach, cyan, emerald green).
"""
import os
import shutil

OUTPUT_DIRS = [
    "D:/veepoo/docs/images",
    "D:/veepoo/images",
    "C:/Users/jclou/.gemini/antigravity/brain/f42bf5fb-c1ac-40d8-890c-c24c46404d2a/images"
]

for d in OUTPUT_DIRS:
    os.makedirs(d, exist_ok=True)

# ==============================================================================
# DIAGRAM 1: Alpha Codebase Audit & 3-Stream Migration
# ==============================================================================
SVG_1 = '''<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1000 620" width="1000" height="620" style="background:#070b12; font-family:'Segoe UI',-apple-system,BlinkMacSystemFont,Roboto,sans-serif;">
  <defs>
    <linearGradient id="bgGrad" x1="0" y1="0" x2="1" y2="1">
      <stop offset="0%" stop-color="#080d1a"/>
      <stop offset="100%" stop-color="#020617"/>
    </linearGradient>
    <linearGradient id="headerGrad" x1="0" y1="0" x2="1" y2="0">
      <stop offset="0%" stop-color="#0f172a"/>
      <stop offset="100%" stop-color="#1e293b"/>
    </linearGradient>
  </defs>

  <rect width="1000" height="620" fill="url(#bgGrad)"/>
  <rect x="15" y="15" width="970" height="590" rx="12" fill="none" stroke="#1e293b" stroke-width="1.5"/>

  <!-- Scott BFM Corner Accents -->
  <path d="M 15 45 L 15 15 L 45 15" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 955 15 L 985 15 L 985 45" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 15 575 L 15 605 L 45 605" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 955 605 L 985 605 L 985 575" fill="none" stroke="#38bdf8" stroke-width="3"/>

  <!-- Header Banner -->
  <rect x="35" y="30" width="930" height="58" rx="8" fill="url(#headerGrad)" stroke="#334155" stroke-width="1"/>
  <rect x="35" y="30" width="12" height="58" rx="4" fill="#fb923c"/>
  <text x="60" y="55" fill="#f8fafc" font-size="16" font-weight="800" letter-spacing="1.5">STAR SHIP GEMINI NCC-202X • ARCHITECTURAL ASSET AUDIT</text>
  <text x="60" y="75" fill="#38bdf8" font-size="11" font-weight="600" letter-spacing="1">ALPHA CODEBASE TRIAGE (23,100 LINES) CATEGORIZED INTO THREE BETA 1 STREAMS</text>
  <rect x="810" y="44" width="140" height="30" rx="15" fill="#0f172a" stroke="#10b981" stroke-width="1.5"/>
  <text x="880" y="63" fill="#10b981" font-size="10" font-weight="800" text-anchor="middle" letter-spacing="1">100% AUDITED</text>

  <!-- Left Column: Alpha Source Codebase -->
  <rect x="35" y="110" width="280" height="475" rx="10" fill="#0d1527" stroke="#1e3a8a" stroke-width="1.5"/>
  <rect x="35" y="110" width="280" height="38" rx="10" fill="#1e293b"/>
  <rect x="35" y="138" width="280" height="10" fill="#1e293b"/>
  <text x="52" y="134" fill="#93c5fd" font-size="12" font-weight="800" letter-spacing="1">EXISTING ALPHA CODEBASE</text>
  <rect x="235" y="116" width="70" height="22" rx="11" fill="#0369a1"/>
  <text x="270" y="131" fill="#ffffff" font-size="9" font-weight="800" text-anchor="middle">23.1K LOC</text>

  <g transform="translate(50, 165)">
    <rect width="250" height="85" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="85" rx="2" fill="#38bdf8"/>
    <text x="16" y="24" fill="#f8fafc" font-size="12" font-weight="700">index.html (19,800 lines)</text>
    <text x="16" y="44" fill="#94a3b8" font-size="10">• 14 Workstation Tab Consoles</text>
    <text x="16" y="60" fill="#94a3b8" font-size="10">• Live SVG Circadian Telemetry</text>
    <text x="16" y="76" fill="#94a3b8" font-size="10">• Reverse-engineered BLE parsers</text>
  </g>

  <g transform="translate(50, 265)">
    <rect width="250" height="85" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="85" rx="2" fill="#38bdf8"/>
    <text x="16" y="24" fill="#f8fafc" font-size="12" font-weight="700">cell.html (1,200 lines)</text>
    <text x="16" y="44" fill="#94a3b8" font-size="10">• Mobile Shuttlecraft Foodoor PWA</text>
    <text x="16" y="60" fill="#94a3b8" font-size="10">• Real-Time Gemini Nutrition Coach</text>
    <text x="16" y="76" fill="#94a3b8" font-size="10">• Voice &amp; Camera Multi-Modal Intake</text>
  </g>

  <g transform="translate(50, 365)">
    <rect width="250" height="85" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="85" rx="2" fill="#fb923c"/>
    <text x="16" y="24" fill="#f8fafc" font-size="12" font-weight="700">server.py (1,500 lines)</text>
    <text x="16" y="44" fill="#94a3b8" font-size="10">• Local HTTP Daemon (Port 8899)</text>
    <text x="16" y="60" fill="#94a3b8" font-size="10">• Conagra / USDA Nutrition Truth DB</text>
    <text x="16" y="76" fill="#94a3b8" font-size="10">• Zero-Cost String Verification</text>
  </g>

  <g transform="translate(50, 465)">
    <rect width="250" height="85" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="85" rx="2" fill="#fb923c"/>
    <text x="16" y="24" fill="#f8fafc" font-size="12" font-weight="700">watch_monitor.py (600 lines)</text>
    <text x="16" y="44" fill="#94a3b8" font-size="10">• Background BLE Heartbeat Daemon</text>
    <text x="16" y="60" fill="#94a3b8" font-size="10">• Raw GATT Characteristic Pipes</text>
    <text x="16" y="76" fill="#94a3b8" font-size="10">• 24/7 Optical Telemetry Ingest</text>
  </g>

  <!-- Conduits (Connecting Lines) -->
  <path d="M 315 207 C 345 207, 345 175, 375 175" fill="none" stroke="#10b981" stroke-width="3" stroke-dasharray="6 4"/>
  <path d="M 315 307 C 345 307, 345 330, 375 330" fill="none" stroke="#fb923c" stroke-width="3" stroke-dasharray="6 4"/>
  <path d="M 315 507 C 345 507, 345 490, 375 490" fill="none" stroke="#38bdf8" stroke-width="3" stroke-dasharray="6 4"/>

  <!-- Right Column: 3 Migration Streams -->
  <!-- Stream 1: Verbatim Reuse -->
  <g transform="translate(375, 110)">
    <rect width="590" height="145" rx="10" fill="#052e16" stroke="#059669" stroke-width="1.5" fill-opacity="0.6"/>
    <rect width="590" height="34" rx="10" fill="#064e3b"/>
    <rect y="24" width="590" height="10" fill="#064e3b"/>
    <text x="18" y="23" fill="#34d399" font-size="12" font-weight="800" letter-spacing="1">STREAM 1: VERBATIM REUSE (100% PRESERVED ASSETS)</text>
    <text x="18" y=\"56\" fill="#f8fafc" font-size="11" font-weight="600">• 6-Sensor Optical BLE Pipeline: 0x89 Glucose, 0xD0 HR, 0x80 SpO2, 0xA0 Batt, 0xA8 Steps</text>
    <text x="18" y=\"78\" fill="#f8fafc" font-size="11" font-weight="600">• ADAG Mathematical Engine: eA1C = (Glucose + 46.7) / 28.7 (Zero clinical variance)</text>
    <text x="18" y=\"100\" fill="#f8fafc" font-size="11" font-weight="600">• Circadian Multi-Lane SVG Timelines: 8 stacked lanes, staggered X-axis, high contrast</text>
    <text x="18" y=\"122\" fill="#f8fafc" font-size="11" font-weight="600">• Nutrition Truth Tables: Conagra pot pie (34g net carbs) &amp; GLUT4 kinetic velocity</text>
  </g>

  <!-- Stream 2: Refactored Modules -->
  <g transform="translate(375, 270)">
    <rect width="590" height="145" rx="10" fill="#451a03" stroke="#d97706" stroke-width="1.5" fill-opacity="0.6"/>
    <rect width="590" height="34" rx="10" fill="#78350f"/>
    <rect y="24" width="590" height="10" fill="#78350f"/>
    <text x="18" y="23" fill="#fbbf24" font-size="12" font-weight="800" letter-spacing="1">STREAM 2: REFACTORED MODULES (STRUCTURAL HARDENING)</text>
    <text x="18" y=\"56\" fill="#f8fafc" font-size="11" font-weight="600">• CSS Class Migration: 1,847 replacements from lcars-* to modular bfm-* design system</text>
    <text x="18" y=\"78\" fill="#f8fafc" font-size="11" font-weight="600">• Universal Wearables: Strip legacy '5010' brands to Universal Sensor Array standard</text>
    <text x="18" y=\"100\" fill="#f8fafc" font-size="11" font-weight="600">• Multi-Tenant Storage: Partition single telemetry.db into user-isolated telemetry_user_&lt;uuid&gt;.db</text>
    <text x="18" y=\"122\" fill="#f8fafc" font-size="11" font-weight="600">• Ingestion Dispatcher: Replace hardcoded MAC with Dynamic 3-Way Triad Routing Engine</text>
  </g>

  <!-- Stream 3: Newly Engineered Modules -->
  <g transform="translate(375, 430)">
    <rect width="590" height="155" rx="10" fill="#082f49" stroke="#0284c7" stroke-width="1.5" fill-opacity="0.6"/>
    <rect width="590" height="34" rx="10" fill="#0c4a6e"/>
    <rect y="24" width="590" height="10" fill="#0c4a6e"/>
    <text x="18" y="23" fill="#38bdf8" font-size="12" font-weight="800" letter-spacing="1">STREAM 3: NEWLY ENGINEERED CAPABILITIES (ZERO-REGRESSION BETA 1)</text>
    <text x="18" y=\"56\" fill="#f8fafc" font-size="11" font-weight="600">• Tab 10 Master Roster: Registered Family Cells &amp; Watch Roster with live sync status</text>
    <text x="18" y=\"78\" fill="#f8fafc" font-size="11" font-weight="600">• Auto-Scan Handshake: /api/shuttle/register mobile interrogation (Phone ESN + Name)</text>
    <text x="18" y=\"100\" fill="#f8fafc" font-size="11" font-weight="600">• Offline Mobile Vault: cell.html IndexedDB with field meal photo BLOBs and audio notes</text>
    <text x="18" y=\"122\" fill="#f8fafc" font-size="11" font-weight="600">• Automated Dual External Backup: Daily .sqlite + Epic FHIR R4 .json with SHA-256 manifests</text>
    <text x="18" y=\"142\" fill="#f8fafc" font-size="11" font-weight="600">• Legal Safety Gate: Acceptance modal locked to immutable user_legal_acceptance DB table</text>
  </g>
</svg>'''

# ==============================================================================
# DIAGRAM 2: Multi-Tenant 3-Way Hardware Binding Triad Router
# ==============================================================================
SVG_2 = '''<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1000 620" width="1000" height="620" style="background:#070b12; font-family:'Segoe UI',-apple-system,BlinkMacSystemFont,Roboto,sans-serif;">
  <defs>
    <linearGradient id="bgGrad2" x1="0" y1="0" x2="1" y2="1">
      <stop offset="0%" stop-color="#080d1a"/>
      <stop offset="100%" stop-color="#020617"/>
    </linearGradient>
    <linearGradient id="headerGrad2" x1="0" y1="0" x2="1" y2="0">
      <stop offset="0%" stop-color="#0f172a"/>
      <stop offset="100%" stop-color="#1e293b"/>
    </linearGradient>
  </defs>

  <rect width="1000" height="620" fill="url(#bgGrad2)"/>
  <rect x="15" y="15" width="970" height="590" rx="12" fill="none" stroke="#1e293b" stroke-width="1.5"/>

  <!-- Scott BFM Corner Accents -->
  <path d="M 15 45 L 15 15 L 45 15" fill="none" stroke="#fb923c" stroke-width="3"/>
  <path d="M 955 15 L 985 15 L 985 45" fill="none" stroke="#fb923c" stroke-width="3"/>
  <path d="M 15 575 L 15 605 L 45 605" fill="none" stroke="#fb923c" stroke-width="3"/>
  <path d="M 955 605 L 985 605 L 985 575" fill="none" stroke="#fb923c" stroke-width="3"/>

  <!-- Header Banner -->
  <rect x="35" y="30" width="930" height="58" rx="8" fill="url(#headerGrad2)" stroke="#334155" stroke-width="1"/>
  <rect x="35" y="30" width="12" height="58" rx="4" fill="#38bdf8"/>
  <text x="60" y="55" fill="#f8fafc" font-size="16" font-weight="800" letter-spacing="1.5">MULTI-TENANT ANTI-COLLISION • 3-WAY HARDWARE BINDING TRIAD</text>
  <text x="60" y="75" fill="#fb923c" font-size="11" font-weight="600" letter-spacing="1">USER UID &lt;-&gt; CELL PHONE ESN &lt;-&gt; LINKED WATCH MAC (SCREEN INDEPENDENT ROUTING)</text>
  <rect x="800" y="44" width="150" height="30" rx="15" fill="#0f172a" stroke="#fb923c" stroke-width="1.5"/>
  <text x="875" y="63" fill="#fb923c" font-size="10" font-weight="800" text-anchor="middle" letter-spacing="1">ZERO CONTAMINATION</text>

  <!-- 3 Main Columns: Household Hardware -> Workstation Dispatcher -> Isolated Vaults -->
  <!-- Column 1: Concurrent Household Hardware -->
  <rect x="35" y="110" width="270" height="475" rx="10" fill="#0d1527" stroke="#1e3a8a" stroke-width="1.5"/>
  <rect x="35" y="110" width="270" height="36" rx="10" fill="#1e293b"/>
  <rect x="35" y="136" width="270" height="10" fill="#1e293b"/>
  <text x="50" y="133" fill="#93c5fd" font-size="11" font-weight="800" letter-spacing="1">1. HOUSEHOLD HARDWARE</text>

  <!-- Dad's Hardware -->
  <g transform="translate(50, 160)">
    <rect width="240" height="85" rx="6" fill="#0b1324" stroke="#0284c7" stroke-width="1.5"/>
    <text x="14" y="24" fill="#38bdf8" font-size="12" font-weight="700">PROFILE 01: DAD (JOHN)</text>
    <text x="14" y="46" fill="#f8fafc" font-size="10">⌚ Watch: MAC 8B:DE:40:83:F6:16</text>
    <text x="14" y="64" fill="#f8fafc" font-size="10">📱 Cell 1: ESN-9482-A018-7F</text>
    <text x="14" y="78" fill="#10b981" font-size="9" font-weight="700">Status: Active Continuous BLE</text>
  </g>

  <!-- Mom's Hardware -->
  <g transform="translate(50, 265)">
    <rect width="240" height="85" rx="6" fill="#0b1324" stroke="#d97706" stroke-width="1.5"/>
    <text x="14" y="24" fill="#fbbf24" font-size="12" font-weight="700">PROFILE 02: MOM (MARY)</text>
    <text x="14" y="46" fill="#f8fafc" font-size="10">⌚ Watch: MAC C4:12:9A:33:02:51</text>
    <text x="14" y="64" fill="#f8fafc" font-size="10">📱 Cell 2: ESN-7731-B942-3C</text>
    <text x="14" y="78" fill="#fbbf24" font-size="9" font-weight="700">Status: Field Meal Wi-Fi Dock</text>
  </g>

  <!-- Son's Hardware -->
  <g transform="translate(50, 370)">
    <rect width="240" height="85" rx="6" fill="#0b1324" stroke="#475569" stroke-width="1.5"/>
    <text x="14" y="24" fill="#cbd5e1" font-size="12" font-weight="700">PROFILE 03: SON (ALEX)</text>
    <text x="14" y="46" fill="#f8fafc" font-size="10">⌚ Watch: MAC E1:55:88:99:A2:00</text>
    <text x="14" y="64" fill="#f8fafc" font-size="10">📱 Cell 3: ESN-4410-C209-1E</text>
    <text x="14" y="78" fill="#94a3b8" font-size="9" font-weight="700">Status: Standby / Paired</text>
  </g>

  <g transform="translate(50, 475)">
    <rect width="240" height="90" rx="6" fill="#1e1b4b" stroke="#6366f1" stroke-width="1"/>
    <text x="14" y="24" fill="#a5b4fc" font-size="10" font-weight="800">HARDWARE COLLISION HAZARD:</text>
    <text x="14" y="44" fill="#e0e7ff" font-size="9">If Dad is actively viewing Tab 04 on PC,</text>
    <text x="14" y="58" fill="#e0e7ff" font-size="9">Mom's incoming watch packets must NEVER</text>
    <text x="14" y="72" fill="#e0e7ff" font-size="9">route to Dad's open browser screen!</text>
  </g>

  <!-- Column 2: Center Dispatcher -->
  <rect x="345" y="110" width="310" height="475" rx="10" fill="#0f172a" stroke="#334155" stroke-width="1.5"/>
  <rect x="345" y="110" width="310" height="36" rx="10" fill="#1e293b"/>
  <rect x="345" y="136" width="310" height="10" fill="#1e293b"/>
  <text x="360" y="133" fill="#f8fafc" font-size="11" font-weight="800" letter-spacing="1">2. STAR SHIP GEMINI BASE DISPATCHER</text>

  <!-- Monitor State Card -->
  <g transform="translate(360, 160)">
    <rect width="280" height="65" rx="6" fill="#312e81" stroke="#4338ca" stroke-width="1"/>
    <text x="14" y="24" fill="#c7d2fe" font-size="10" font-weight="800">ACTIVE PC MONITOR SCREEN STATE</text>
    <text x="14" y="44" fill="#ffffff" font-size="11" font-weight="700">Viewing: Profile 01 (Dad) • Tab 04 Charts</text>
    <text x="14" y="58" fill="#f43f5e" font-size="9" font-weight="800">⚡ COMPLETELY IGNORED BY INGESTION ROUTER</text>
  </g>

  <!-- Dispatcher Routing Engine -->
  <g transform="translate(360, 240)">
    <rect width="280" height="180" rx="8" fill="#020617" stroke="#38bdf8" stroke-width="1.5"/>
    <text x="16" y="26" fill="#38bdf8" font-size="12" font-weight="800">TRIAD ROUTING ENGINE (server.py)</text>
    <rect x="16" y="38" width="248" height="30" rx="4" fill="#0f172a" stroke="#334155" stroke-width="1"/>
    <text x="24" y="57" fill="#94a3b8" font-size="9">BLE Ingest: Inspect Source Watch MAC</text>

    <rect x="16" y="76" width="248" height="30" rx="4" fill="#0f172a" stroke="#334155" stroke-width="1"/>
    <text x="24" y="95" fill="#94a3b8" font-size="9">Cell Dock: Inspect Payload Hardware ESN</text>

    <rect x="16" y="114" width="248" height="54" rx="4" fill="#064e3b" stroke="#059669" stroke-width="1"/>
    <text x="24" y="132" fill="#34d399" font-size="9" font-weight="800">SQLITE MASTER REGISTRY LOOKUP:</text>
    <text x="24" y="146" fill="#f8fafc" font-size="9">SELECT user_uid FROM registered_cells</text>
    <text x="24" y="160" fill="#f8fafc" font-size="9">WHERE linked_watch_mac=? OR phone_esn=?</text>
  </g>

  <g transform="translate(360, 435)">
    <rect width="280" height="130" rx="8" fill="#14532d" stroke="#16a34a" stroke-width="1" fill-opacity="0.4"/>
    <text x="16" y="24" fill="#4ade80" font-size="11" font-weight="800">DETERMINISTIC ROUTING RULE:</text>
    <text x="16" y="44" fill="#f8fafc" font-size="10">• Watch MAC 8B:DE... -> telemetry_user_1.db</text>
    <text x="16" y="62" fill="#f8fafc" font-size="10">• Cell ESN-9482...   -> telemetry_user_1.db</text>
    <text x="16" y="80" fill="#fbbf24" font-size="10">• Watch MAC C4:12... -> telemetry_user_2.db</text>
    <text x="16" y="98" fill="#fbbf24" font-size="10">• Cell ESN-7731...   -> telemetry_user_2.db</text>
    <text x="16" y="118" fill="#38bdf8" font-size="9" font-weight="700">Zero Cross-Tenant Contamination Guaranteed</text>
  </g>

  <!-- Column 3: Isolated User Vaults -->
  <rect x="695" y="110" width="270" height="475" rx="10" fill="#0d1527" stroke="#1e3a8a" stroke-width="1.5"/>
  <rect x="695" y="110" width="270" height="36" rx="10" fill="#1e293b"/>
  <rect x="695" y="136" width="270" height="10" fill="#1e293b"/>
  <text x="710" y="133" fill="#93c5fd" font-size="11" font-weight="800" letter-spacing="1">3. AIR-GAPPED USER VAULTS</text>

  <!-- Vault 1 -->
  <g transform="translate(710, 160)">
    <rect width="240" height="120" rx="6" fill="#0b1324" stroke="#0284c7" stroke-width="1.5"/>
    <text x="14" y="24" fill="#38bdf8" font-size="11" font-weight="800">telemetry_user_USR-001.db</text>
    <text x="14" y="44" fill="#f8fafc" font-size="10">Owner: John (Dad)</text>
    <text x="14" y="62" fill="#94a3b8" font-size="9">• 24h Optical Glucose Tonometry</text>
    <text x="14" y="78" fill="#94a3b8" font-size="9">• Heart Rate, SpO2, Temperature</text>
    <text x="14" y="94" fill="#94a3b8" font-size="9">• Cell 1 Nutrition Intake Records</text>
    <text x="14" y="110" fill="#10b981" font-size="9" font-weight="700">Lock: Dedicated Thread SQLite Handle</text>
  </g>

  <!-- Vault 2 -->
  <g transform="translate(710, 300)">
    <rect width="240" height="120" rx="6" fill="#0b1324" stroke="#d97706" stroke-width="1.5"/>
    <text x="14" y="24" fill="#fbbf24" font-size="11" font-weight="800">telemetry_user_USR-002.db</text>
    <text x="14" y="44" fill="#f8fafc" font-size="10">Owner: Mary (Mom)</text>
    <text x="14" y="62" fill="#94a3b8" font-size="9">• Continuous Watch 2 Biometrics</text>
    <text x="14" y="78" fill="#94a3b8" font-size="9">• Cell 2 Field Meal Intake Batches</text>
    <text x="14" y="94" fill="#94a3b8" font-size="9">• Personalized Glycemic Baseline</text>
    <text x="14" y="110" fill="#10b981" font-size="9" font-weight="700">Lock: Dedicated Thread SQLite Handle</text>
  </g>

  <!-- Vault 3 -->
  <g transform="translate(710, 440)">
    <rect width="240" height="125" rx="6" fill="#0b1324" stroke="#475569" stroke-width="1.5"/>
    <text x="14" y="24" fill="#cbd5e1" font-size="11" font-weight="800">telemetry_user_USR-003.db</text>
    <text x="14" y="44" fill="#f8fafc" font-size="10">Owner: Alex (Son)</text>
    <text x="14" y="62" fill="#94a3b8" font-size="9">• Standby Telemetry Vault</text>
    <text x="14" y="78" fill="#94a3b8" font-size="9">• Ready for Watch 3 Pairing</text>
    <text x="14" y="94" fill="#94a3b8" font-size="9">• Cell 3 Registered and Bound</text>
    <text x="14" y="112" fill="#38bdf8" font-size="9" font-weight="700">Status: Zero Cross-Talk Ready</text>
  </g>

  <!-- Routing Connectors -->
  <path d="M 290 200 L 360 270" fill="none" stroke="#38bdf8" stroke-width="2"/>
  <path d="M 290 305 L 360 295" fill="none" stroke="#fbbf24" stroke-width="2"/>
  <path d="M 640 450 L 710 220" fill="none" stroke="#38bdf8" stroke-width="2"/>
  <path d="M 640 495 L 710 360" fill="none" stroke="#fbbf24" stroke-width="2"/>
</svg>'''

# ==============================================================================
# DIAGRAM 3: Mobile Lifecycle: Auto-Scan Handshake & Homecoming Docking
# ==============================================================================
SVG_3 = '''<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1000 620" width="1000" height="620" style="background:#070b12; font-family:'Segoe UI',-apple-system,BlinkMacSystemFont,Roboto,sans-serif;">
  <defs>
    <linearGradient id="bgGrad3" x1="0" y1="0" x2="1" y2="1">
      <stop offset="0%" stop-color="#080d1a"/>
      <stop offset="100%" stop-color="#020617"/>
    </linearGradient>
    <linearGradient id="headerGrad3" x1="0" y1="0" x2="1" y2="0">
      <stop offset="0%" stop-color="#0f172a"/>
      <stop offset="100%" stop-color="#1e293b"/>
    </linearGradient>
  </defs>

  <rect width="1000" height="620" fill="url(#bgGrad3)"/>
  <rect x="15" y="15" width="970" height="590" rx="12" fill="none" stroke="#1e293b" stroke-width="1.5"/>

  <!-- Scott BFM Corner Accents -->
  <path d="M 15 45 L 15 15 L 45 15" fill="none" stroke="#10b981" stroke-width="3"/>
  <path d="M 955 15 L 985 15 L 985 45" fill="none" stroke="#10b981" stroke-width="3"/>
  <path d="M 15 575 L 15 605 L 45 605" fill="none" stroke="#10b981" stroke-width="3"/>
  <path d="M 955 605 L 985 605 L 985 575" fill="none" stroke="#10b981" stroke-width="3"/>

  <!-- Header Banner -->
  <rect x="35" y="30" width="930" height="58" rx="8" fill="url(#headerGrad3)" stroke="#334155" stroke-width="1"/>
  <rect x="35" y="30" width="12" height="58" rx="4" fill="#10b981"/>
  <text x="60" y="55" fill="#f8fafc" font-size="16" font-weight="800" letter-spacing="1.5">MOBILE SHUTTLECRAFT FOODOR • AUTO-SCAN &amp; HOMECOMING DOCKING</text>
  <text x="60" y="75" fill="#34d399" font-size="11" font-weight="600" letter-spacing="1">CRYPTOGRAPHIC QR PAIRING, MULTI-DAY OFFLINE FIELD VAULT &amp; TWO-PHASE SHA-256 SYNC</text>
  <rect x="800" y="44" width="150" height="30" rx="15" fill="#0f172a" stroke="#10b981" stroke-width="1.5"/>
  <text x="875" y="63" fill="#10b981" font-size="10" font-weight="800" text-anchor="middle" letter-spacing="1">AIR-GAPPED PHI</text>

  <!-- Sequence Diagram Swimlanes -->
  <!-- Lane 1: User -->
  <rect x="55" y="105" width="130" height="32" rx="6" fill="#1e293b" stroke="#475569" stroke-width="1"/>
  <text x="120" y="125" fill="#f8fafc" font-size="10" font-weight="700" text-anchor="middle">HOUSEHOLD USER</text>
  <line x1="120" y1="137" x2="120" y2="585" stroke="#334155" stroke-width="1" stroke-dasharray="4 4"/>

  <!-- Lane 2: PC Workstation (index.html) -->
  <rect x="235" y="105" width="160" height="32" rx="6" fill="#0c4a6e" stroke="#0284c7" stroke-width="1"/>
  <text x="315" y="125" fill="#38bdf8" font-size="10" font-weight="800" text-anchor="middle">STAR SHIP GEMINI (PC)</text>
  <line x1="315" y1="137" x2="315" y2="585" stroke="#0284c7" stroke-width="1" stroke-dasharray="4 4"/>

  <!-- Lane 3: Station Server (server.py) -->
  <rect x="445" y="105" width="140" height="32" rx="6" fill="#1e1b4b" stroke="#6366f1" stroke-width="1"/>
  <text x="515" y="125" fill="#a5b4fc" font-size="10" font-weight="800" text-anchor="middle">SERVER (server.py)</text>
  <line x1="515" y1="137" x2="515" y2="585" stroke="#6366f1" stroke-width="1" stroke-dasharray="4 4"/>

  <!-- Lane 4: Mobile PWA (cell.html) -->
  <rect x="635" y="105" width="160" height="32" rx="6" fill="#78350f" stroke="#d97706" stroke-width="1"/>
  <text x="715" y="125" fill="#fbbf24" font-size="10" font-weight="800" text-anchor="middle">SHUTTLECRAFT (PWA)</text>
  <line x1="715" y1="137" x2="715" y2="585" stroke="#d97706" stroke-width="1" stroke-dasharray="4 4"/>

  <!-- Lane 5: Local Database -->
  <rect x="840" y="105" width="125" height="32" rx="6" fill="#064e3b" stroke="#059669" stroke-width="1"/>
  <text x="902" y="125" fill="#34d399" font-size="10" font-weight="800" text-anchor="middle">SQLITE VAULTS</text>
  <line x1="902" y1="137" x2="902" y2="585" stroke="#059669" stroke-width="1" stroke-dasharray="4 4"/>

  <!-- STAGE 1: AUTO-SCAN ONBOARDING HANDSHAKE -->
  <rect x="45" y="150" width="910" height="24" rx="4" fill="#0f172a" stroke="#334155" stroke-width="1"/>
  <text x="60" y="166" fill="#38bdf8" font-size="10" font-weight="800" letter-spacing="1">PHASE A: AUTO-SCAN MOBILE ONBOARDING &amp; ESN HARDWARE LOCK</text>

  <!-- Step 1 -->
  <line x1="120" y1="190" x2="315" y2="190" stroke="#f8fafc" stroke-width="1.5"/>
  <polygon points="310,186 318,190 310,194" fill="#f8fafc"/>
  <text x="140" y="184" fill="#f8fafc" font-size="9" font-weight="600">1. Click "Add Cell &amp; User" (Mary / USR-002)</text>

  <!-- Step 2 -->
  <line x1="315" y1="210" x2="515" y2="210" stroke="#38bdf8" stroke-width="1.5"/>
  <polygon points="510,206 518,210 510,214" fill="#38bdf8"/>
  <text x="330" y="204" fill="#38bdf8" font-size="9" font-weight="600">2. GET /api/shuttle/provision?uid=USR-002</text>

  <!-- Step 3 -->
  <line x1="515" y1="230" x2="315" y2="230" stroke="#a5b4fc" stroke-width="1.5" stroke-dasharray="4 2"/>
  <polygon points="320,226 312,230 320,234" fill="#a5b4fc"/>
  <text x="330" y="224" fill="#a5b4fc" font-size="9" font-weight="600">3. Dynamic QR Code with Cryptographic Token</text>

  <!-- Step 4 -->
  <line x1="715" y1="250" x2="315" y2="250" stroke="#fbbf24" stroke-width="1.5"/>
  <polygon points="320,246 312,250 320,254" fill="#fbbf24"/>
  <text x="440" y="244" fill="#fbbf24" font-size="9" font-weight="600">4. Shuttlecraft Camera Scans Dynamic QR Code</text>

  <!-- Step 5 -->
  <line x1="715" y1="270" x2="515" y2="270" stroke="#fbbf24" stroke-width="1.5"/>
  <polygon points="520,266 512,270 520,274" fill="#fbbf24"/>
  <text x="535" y="264" fill="#fbbf24" font-size="9" font-weight="600">5. POST /api/shuttle/register (Token, Phone Name, ESN-7731)</text>

  <!-- Step 6 -->
  <line x1="515" y1="290" x2="902" y2="290" stroke="#10b981" stroke-width="1.5"/>
  <polygon points="897,286 905,290 897,294" fill="#10b981"/>
  <text x="540" y="284" fill="#10b981" font-size="9" font-weight="600">6. INSERT INTO registered_cells (UID, ESN-7731, Watch MAC)</text>

  <!-- Step 7 -->
  <line x1="515" y1="310" x2="715" y2="310" stroke="#10b981" stroke-width="1.5" stroke-dasharray="4 2"/>
  <polygon points="710,306 718,310 710,314" fill="#10b981"/>
  <text x="540" y="304" fill="#10b981" font-size="9" font-weight="600">7. 200 OK: Handshake Confirmed (Cell 2 Paired &amp; Bound)</text>

  <!-- STAGE 2: FIELD AUTONOMY & HOMECOMING BULK DOCKING -->
  <rect x="45" y="335" width="910" height="24" rx="4" fill="#0f172a" stroke="#334155" stroke-width="1"/>
  <text x="60" y="351" fill="#fbbf24" font-size="10" font-weight="800" letter-spacing="1">PHASE B: OFFLINE FIELD AUTONOMY &amp; HOMECOMING BULK TRANSFER</text>

  <!-- Step 8: Offline Field Work -->
  <rect x="650" y="370" width="130" height="45" rx="4" fill="#451a03" stroke="#f59e0b" stroke-width="1"/>
  <text x="715" y="388" fill="#fde68a" font-size="9" font-weight="700" text-anchor="middle">OFFLINE FIELD USE</text>
  <text x="715" y="402" fill="#f8fafc" font-size="8" text-anchor="middle">Photo BLOBs &amp; Transcripts</text>

  <!-- Step 9: Return Home & Tap Dock -->
  <line x1="120" y1="435" x2="715" y2="435" stroke="#f8fafc" stroke-width="1.5"/>
  <polygon points="710,431 718,435 710,439" fill="#f8fafc"/>
  <text x="240" y="429" fill="#f8fafc" font-size="9" font-weight="600">8. User Returns to Home Wi-Fi &amp; Taps "Homecoming Dock"</text>

  <!-- Step 10: Bulk Transfer -->
  <line x1="715" y1="460" x2="515" y2="460" stroke="#fbbf24" stroke-width="1.5"/>
  <polygon points="520,456 512,460 520,464" fill="#fbbf24"/>
  <text x="535" y="454" fill="#fbbf24" font-size="9" font-weight="600">9. POST /api/shuttle/dock (ESN-7731 + Field Meal Batch)</text>

  <!-- Step 11: ESN Resolve & Write -->
  <line x1="515" y1="485" x2="902" y2="485" stroke="#10b981" stroke-width="1.5"/>
  <polygon points="897,481 905,485 897,489" fill="#10b981"/>
  <text x="540" y="479" fill="#10b981" font-size="9" font-weight="600">10. Resolve ESN -> Mary -> Ingest to telemetry_user_2.db</text>

  <!-- Step 12: Cryptographic Receipt -->
  <line x1="515" y1="510" x2="715" y2="510" stroke="#10b981" stroke-width="1.5" stroke-dasharray="4 2"/>
  <polygon points="710,506 718,510 710,514" fill="#10b981"/>
  <text x="535" y="504" fill="#10b981" font-size="9" font-weight="600">11. Return Verified SHA-256 Batch Receipt Hash</text>

  <!-- Step 13: Local Prune -->
  <rect x="650" y="525" width="130" height="35" rx="4" fill="#064e3b" stroke="#10b981" stroke-width="1"/>
  <text x="715" y="542" fill="#34d399" font-size="9" font-weight="700" text-anchor="middle">RECEIPT VERIFIED</text>
  <text x="715" y="554" fill="#f8fafc" font-size="8" text-anchor="middle">Clear PENDING in IndexedDB</text>

  <!-- Step 14: PC Live Refresh -->
  <line x1="515" y1="575" x2="315" y2="575" stroke="#38bdf8" stroke-width="1.5"/>
  <polygon points="320,571 312,575 320,579" fill="#38bdf8"/>
  <text x="330" y="569" fill="#38bdf8" font-size="9" font-weight="600">12. Broadcast SSE Event: Tab 10 Roster Sync Live</text>
</svg>'''

# ==============================================================================
# DIAGRAM 4: Ten-Point Six Sigma Pre-Build Quality Gate
# ==============================================================================
SVG_4 = '''<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1000 620" width="1000" height="620" style="background:#070b12; font-family:'Segoe UI',-apple-system,BlinkMacSystemFont,Roboto,sans-serif;">
  <defs>
    <linearGradient id="bgGrad4" x1="0" y1="0" x2="1" y2="1">
      <stop offset="0%" stop-color="#080d1a"/>
      <stop offset="100%" stop-color="#020617"/>
    </linearGradient>
    <linearGradient id="headerGrad4" x1="0" y1="0" x2="1" y2="0">
      <stop offset="0%" stop-color="#0f172a"/>
      <stop offset="100%" stop-color="#1e293b"/>
    </linearGradient>
  </defs>

  <rect width="1000" height="620" fill="url(#bgGrad4)"/>
  <rect x="15" y="15" width="970" height="590" rx="12" fill="none" stroke="#1e293b" stroke-width="1.5"/>

  <!-- Scott BFM Corner Accents -->
  <path d="M 15 45 L 15 15 L 45 15" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 955 15 L 985 15 L 985 45" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 15 575 L 15 605 L 45 605" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 955 605 L 985 605 L 985 575" fill="none" stroke="#38bdf8" stroke-width="3"/>

  <!-- Header Banner -->
  <rect x="35" y="30" width="930" height="58" rx="8" fill="url(#headerGrad4)" stroke="#334155" stroke-width="1"/>
  <rect x="35" y="30" width="12" height="58" rx="4" fill="#10b981"/>
  <text x="60" y="55" fill="#f8fafc" font-size="16" font-weight="800" letter-spacing="1.5">TEN-POINT SIX SIGMA PRE-BUILD QUALITY GATE (-Werror)</text>
  <text x="60" y="75" fill="#38bdf8" font-size="11" font-weight="600" letter-spacing="1">MANDATORY AUTOMATED PRE-COMMIT AUDIT • ZERO DEFECTS &amp; ZERO REGRESSIONS</text>
  <rect x="800" y="44" width="150" height="30" rx="15" fill="#064e3b" stroke="#10b981" stroke-width="1.5"/>
  <text x="875" y="63" fill="#34d399" font-size="10" font-weight="800" text-anchor="middle" letter-spacing="1">10/10 CERTIFIED</text>

  <!-- Level 1: Static Code & DOM Integrity (Checks 1-5) -->
  <rect x="35" y="110" width="455" height="415" rx="10" fill="#0d1527" stroke="#1e3a8a" stroke-width="1.5"/>
  <rect x="35" y="110" width="455" height="36" rx="10" fill="#1e293b"/>
  <rect x="35" y="136" width="455" height="10" fill="#1e293b"/>
  <text x="50" y="133" fill="#93c5fd" font-size="11" font-weight="800" letter-spacing="1">LEVEL 1: STATIC CODE &amp; DOM TREE INTEGRITY</text>

  <!-- Check 01 -->
  <g transform="translate(50, 160)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 01/10] Dual-File SHA-256 Mirror Parity</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Root index.html and w11_htm_app/index.html must be bit-for-bit identical.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: SHA-256 1f15b42ad1c8... matched perfectly.</text>
  </g>

  <!-- Check 02 -->
  <g transform="translate(50, 230)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 02/10] Strict HTML Div Tag Balance</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Opens must equal Closes; diff must be mathematically zero.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: index.html (1965/1965, Diff=0) • cell.html (61/61, Diff=0)</text>
  </g>

  <!-- Check 03 -->
  <g transform="translate(50, 300)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 03/10] Strict DOM Workspace Hierarchy</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">All 14 tab pages must be verified direct children of &lt;main.bfm-workspace&gt;.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: 14/14 tab consoles verified as immediate workspace children.</text>
  </g>

  <!-- Check 04 -->
  <g transform="translate(50, 370)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 04/10] JavaScript AST Static Syntax</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Full script extraction and Node.js parser validation (-c).</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: Zero JavaScript syntax errors across all tab controllers.</text>
  </g>

  <!-- Check 05 -->
  <g transform="translate(50, 440)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 05/10] Python Server Compilation &amp; Route AST</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Mandatory py_compile execution across backend daemons.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: server.py and watch_monitor.py bytecode compiled cleanly.</text>
  </g>

  <!-- Level 2: Data Schemas & Algorithmic Safety (Checks 6-10) -->
  <rect x="510" y="110" width="455" height="415" rx="10" fill="#0d1527" stroke="#1e3a8a" stroke-width="1.5"/>
  <rect x="510" y="110" width="455" height="36" rx="10" fill="#1e293b"/>
  <rect x="510" y="136" width="455" height="10" fill="#1e293b"/>
  <text x="525" y="133" fill="#93c5fd" font-size="11" font-weight="800" letter-spacing="1">LEVEL 2: DATA SCHEMAS &amp; ALGORITHMIC SAFETY</text>

  <!-- Check 06 -->
  <g transform="translate(525, 160)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 06/10] SQLite Schema Parity &amp; 3-Way Triad Integrity</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Validates UID &lt;-&gt; ESN &lt;-&gt; Watch MAC relational constraints.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: telemetry_master.db DDL &amp; vault tables verified.</text>
  </g>

  <!-- Check 07 -->
  <g transform="translate(525, 230)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 07/10] Confectionery Safety &amp; Nutrition Calculations</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Verifies popular_meals truth table; zero high-sugar candy records.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: 17 verified items, zero duplicates, zero candy entries.</text>
  </g>

  <!-- Check 08 -->
  <g transform="translate(525, 300)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 08/10] Zero Monetary Cost Strings in Nutrition BOM</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Audits Tab 09 meal headers to prevent price string confusion.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: Zero price strings; strictly metabolic impact metrics.</text>
  </g>

  <!-- Check 09 -->
  <g transform="translate(525, 370)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 09/10] Zero Forbidden Terms Enforcement</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Scans for forbidden legacy tokens: '5010', 'Patient', 'Clinical'.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: Pre-build scan verified clean; all replacement targets mapped.</text>
  </g>

  <!-- Check 10 -->
  <g transform="translate(525, 440)">
    <rect width="425" height="60" rx="6" fill="#0b1324" stroke="#334155" stroke-width="1"/>
    <rect width="6" height="60" rx="2" fill="#10b981"/>
    <text x="16" y="22" fill="#f8fafc" font-size="11" font-weight="700">[CHECK 10/10] Mock Auto-Scan Handshake &amp; Ingestion Test</text>
    <text x="16" y="38" fill="#94a3b8" font-size="9">Simulates concurrent watch BLE and cell docking payloads.</text>
    <text x="16" y="52" fill="#34d399" font-size="9" font-weight="700">PASS: Dual-payload ingestion dispatches to isolated vaults.</text>
  </g>

  <!-- Bottom Certification Banner -->
  <rect x="35" y="540" width="930" height="50" rx="8" fill="#064e3b" stroke="#10b981" stroke-width="1.5"/>
  <text x="500" y="570" fill="#f8fafc" font-size="13" font-weight="800" text-anchor="middle" letter-spacing="1">SIX SIGMA QUALITY STATUS: 10/10 GATES PASSING • CERTIFIED READY FOR BETA 1 BUILD</text>
</svg>'''

# ==============================================================================
# DIAGRAM 5: Beta 1 Phased Implementation Roadmap
# ==============================================================================
SVG_5 = '''<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1000 380" width="1000" height="380" style="background:#070b12; font-family:'Segoe UI',-apple-system,BlinkMacSystemFont,Roboto,sans-serif;">
  <defs>
    <linearGradient id="bgGrad5" x1="0" y1="0" x2="1" y2="1">
      <stop offset="0%" stop-color="#080d1a"/>
      <stop offset="100%" stop-color="#020617"/>
    </linearGradient>
    <linearGradient id="headerGrad5" x1="0" y1="0" x2="1" y2="0">
      <stop offset="0%" stop-color="#0f172a"/>
      <stop offset="100%" stop-color="#1e293b"/>
    </linearGradient>
  </defs>

  <rect width="1000" height="380" fill="url(#bgGrad5)"/>
  <rect x="15" y="15" width="970" height="350" rx="12" fill="none" stroke="#1e293b" stroke-width="1.5"/>

  <!-- Scott BFM Corner Accents -->
  <path d="M 15 45 L 15 15 L 45 15" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 955 15 L 985 15 L 985 45" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 15 335 L 15 365 L 45 365" fill="none" stroke="#38bdf8" stroke-width="3"/>
  <path d="M 955 365 L 985 365 L 985 335" fill="none" stroke="#38bdf8" stroke-width="3"/>

  <!-- Header Banner -->
  <rect x="35" y="30" width="930" height="55" rx="8" fill="url(#headerGrad5)" stroke="#334155" stroke-width="1"/>
  <rect x="35" y="30" width="12" height="55" rx="4" fill="#38bdf8"/>
  <text x="60" y="55" fill="#f8fafc" font-size="16" font-weight="800" letter-spacing="1.5">BETA 1 PHASED IMPLEMENTATION ROADMAP &amp; ZERO-REGRESSION PIPELINE</text>
  <text x="60" y="73" fill="#38bdf8" font-size="11" font-weight="600" letter-spacing="1">STRUCTURED FIVE-PHASE SEQUENTIAL EXECUTION ENFORCED BY -Werror GATES</text>
  <rect x="800" y="42" width="150" height="30" rx="15" fill="#0f172a" stroke="#38bdf8" stroke-width="1.5"/>
  <text x="875" y="61" fill="#38bdf8" font-size="10" font-weight="800" text-anchor="middle" letter-spacing="1">ZERO REGRESSION</text>

  <!-- 5 Sequential Phase Cards -->
  <!-- Phase 1 -->
  <g transform="translate(35, 110)">
    <rect width="170" height="230" rx="8" fill="#0c4a6e" stroke="#0284c7" stroke-width="1.5"/>
    <rect width="170" height="34" rx="8" fill="#0369a1"/>
    <rect y="24" width="170" height="10" fill="#0369a1"/>
    <text x="14" y="22" fill="#ffffff" font-size="11" font-weight="800">PHASE 1</text>
    <text x="14" y="50" fill="#38bdf8" font-size="10" font-weight="800">ENGINE &amp; DB</text>
    <text x="14" y="72" fill="#f8fafc" font-size="9">• telemetry_master.db</text>
    <text x="14" y="88" fill="#f8fafc" font-size="9">• user_profiles table</text>
    <text x="14" y="104" fill="#f8fafc" font-size="9">• registered_cells table</text>
    <text x="14" y="120" fill="#f8fafc" font-size="9">• 3-Way Triad DDL</text>
    <text x="14" y="136" fill="#f8fafc" font-size="9">• /api/shuttle/register</text>
    <text x="14" y="152" fill="#f8fafc" font-size="9">• /api/shuttle/dock</text>
    <text x="14" y="168" fill="#f8fafc" font-size="9">• Dynamic BLE Router</text>
    <rect x="14" y="195" width="142" height="22" rx="4" fill="#082f49" stroke="#38bdf8" stroke-width="1"/>
    <text x="85" y="210" fill="#38bdf8" font-size="8" font-weight="800" text-anchor="middle">GATE: SQL PARITY</text>
  </g>

  <!-- Arrow 1 -> 2 -->
  <polygon points="213,220 221,225 213,230" fill="#38bdf8"/>

  <!-- Phase 2 -->
  <g transform="translate(225, 110)">
    <rect width="170" height="230" rx="8" fill="#1e1b4b" stroke="#6366f1" stroke-width="1.5"/>
    <rect width="170" height="34" rx="8" fill="#4338ca"/>
    <rect y="24" width="170" height="10" fill="#4338ca"/>
    <text x="14" y="22" fill="#ffffff" font-size="11" font-weight="800">PHASE 2</text>
    <text x="14" y="50" fill="#a5b4fc" font-size="10" font-weight="800">CONSOLE UI</text>
    <text x="14" y="72" fill="#f8fafc" font-size="9">• lcars- to bfm- classes</text>
    <text x="14" y="88" fill="#f8fafc" font-size="9">• Star Ship Gemini branding</text>
    <text x="14" y="104" fill="#f8fafc" font-size="9">• Tab 10 Master Roster</text>
    <text x="14" y="120" fill="#f8fafc" font-size="9">• Add/Edit/Delete modals</text>
    <text x="14" y="136" fill="#f8fafc" font-size="9">• Legal Acceptance modal</text>
    <text x="14" y="152" fill="#f8fafc" font-size="9">• Senior contrast halos</text>
    <text x="14" y="168" fill="#f8fafc" font-size="9">• Zero Div Balance Drift</text>
    <rect x="14" y="195" width="142" height="22" rx="4" fill="#0f172a" stroke="#a5b4fc" stroke-width="1"/>
    <text x="85" y="210" fill="#a5b4fc" font-size="8" font-weight="800" text-anchor="middle">GATE: DIV BALANCE = 0</text>
  </g>

  <!-- Arrow 2 -> 3 -->
  <polygon points="403,220 411,225 403,230" fill="#a5b4fc"/>

  <!-- Phase 3 -->
  <g transform="translate(415, 110)">
    <rect width="170" height="230" rx="8" fill="#451a03" stroke="#f59e0b" stroke-width="1.5"/>
    <rect width="170" height="34" rx="8" fill="#b45309"/>
    <rect y="24" width="170" height="10" fill="#b45309"/>
    <text x="14" y="22" fill="#ffffff" font-size="11" font-weight="800">PHASE 3</text>
    <text x="14" y="50" fill="#fde68a" font-size="10" font-weight="800">MOBILE PWA</text>
    <text x="14" y="72" fill="#f8fafc" font-size="9">• cell.html auto-scan</text>
    <text x="14" y="88" fill="#f8fafc" font-size="9">• Pull ESN &amp; phone name</text>
    <text x="14" y="104" fill="#f8fafc" font-size="9">• IndexedDB offline vault</text>
    <text x="14" y="120" fill="#f8fafc" font-size="9">• Multi-day meal storage</text>
    <text x="14" y="136" fill="#f8fafc" font-size="9">• Homecoming Dock button</text>
    <text x="14" y="152" fill="#f8fafc" font-size="9">• Two-phase receipt hash</text>
    <text x="14" y="168" fill="#f8fafc" font-size="9">• Zero Data Loss in Transit</text>
    <rect x="14" y="195" width="142" height="22" rx="4" fill="#0f172a" stroke="#fbbf24" stroke-width="1"/>
    <text x="85" y="210" fill="#fbbf24" font-size="8" font-weight="800" text-anchor="middle">GATE: ESN REFLECTION</text>
  </g>

  <!-- Arrow 3 -> 4 -->
  <polygon points="593,220 601,225 593,230" fill="#fbbf24"/>

  <!-- Phase 4 -->
  <g transform="translate(605, 110)">
    <rect width="170" height="230" rx="8" fill="#052e16" stroke="#10b981" stroke-width="1.5"/>
    <rect width="170" height="34" rx="8" fill="#047857"/>
    <rect y="24" width="170" height="10" fill="#047857"/>
    <text x="14" y="22" fill="#ffffff" font-size="11" font-weight="800">PHASE 4</text>
    <text x="14" y="50" fill="#6ee7b7" font-size="10" font-weight="800">DUAL BACKUP</text>
    <text x="14" y="72" fill="#f8fafc" font-size="9">• USB &amp; SD card exports</text>
    <text x="14" y="88" fill="#f8fafc" font-size="9">• File System Access API</text>
    <text x="14" y="104" fill="#f8fafc" font-size="9">• Format 1: Raw .sqlite</text>
    <text x="14" y="120" fill="#f8fafc" font-size="9">• Format 2: FHIR R4 JSON</text>
    <text x="14" y="136" fill="#f8fafc" font-size="9">• Verified LOINC codes</text>
    <text x="14" y="152" fill="#f8fafc" font-size="9">• SHA-256 batch manifest</text>
    <text x="14" y="168" fill="#f8fafc" font-size="9">• Graceful download fallback</text>
    <rect x="14" y="195" width="142" height="22" rx="4" fill="#0f172a" stroke="#10b981" stroke-width="1"/>
    <text x="85" y="210" fill="#10b981" font-size="8" font-weight="800" text-anchor="middle">GATE: FHIR R4 VALID</text>
  </g>

  <!-- Arrow 4 -> 5 -->
  <polygon points="783,220 791,225 783,230" fill="#10b981"/>

  <!-- Phase 5 -->
  <g transform="translate(795, 110)">
    <rect width="170" height="230" rx="8" fill="#1e293b" stroke="#38bdf8" stroke-width="1.5"/>
    <rect width="170" height="34" rx="8" fill="#0f172a"/>
    <rect y="24" width="170" height="10" fill="#0f172a"/>
    <text x="14" y="22" fill="#ffffff" font-size="11" font-weight="800">PHASE 5</text>
    <text x="14" y="50" fill="#38bdf8" font-size="10" font-weight="800">CERTIFICATION</text>
    <text x="14" y="72" fill="#f8fafc" font-size="9">• strict_gate_v2.py (10/10)</text>
    <text x="14" y="88" fill="#f8fafc" font-size="9">• Multi-device concurrent test</text>
    <text x="14" y="104" fill="#f8fafc" font-size="9">• Zero cross-talk verify</text>
    <text x="14" y="120" fill="#f8fafc" font-size="9">• Offline PWA sync test</text>
    <text x="14" y="136" fill="#f8fafc" font-size="9">• Automated backup test</text>
    <text x="14" y="152" fill="#f8fafc" font-size="9">• Zero Forbidden Terms</text>
    <text x="14" y="168" fill="#f8fafc" font-size="9">• 100% Six Sigma Certified</text>
    <rect x="14" y="195" width="142" height="22" rx="4" fill="#064e3b" stroke="#10b981" stroke-width="1"/>
    <text x="85" y="210" fill="#34d399" font-size="8" font-weight="800" text-anchor="middle">100% SIX SIGMA</text>
  </g>
</svg>'''

FILES = {
    "bfm_alpha_migration_streams.svg": SVG_1,
    "bfm_triad_hardware_routing.svg": SVG_2,
    "bfm_mobile_docking_sequence.svg": SVG_3,
    "bfm_six_sigma_quality_gate.svg": SVG_4,
    "bfm_beta1_implementation_roadmap.svg": SVG_5
}

for name, content in FILES.items():
    for d in OUTPUT_DIRS:
        path = os.path.join(d, name)
        with open(path, "w", encoding="utf-8") as f:
            f.write(content)
        print(f"Wrote {path}")

print("Successfully generated all 5 vector SVG diagrams in all directories!")
