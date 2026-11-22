import os, re

def implement_export_tab_and_config():
    files = [r"D:\veepoo\w11_htm_app\index.html", r"D:\veepoo\index.html"]

    for fpath in files:
        with open(fpath, "r", encoding="utf-8") as f:
            html = f.read()

        # -----------------------------------------------------------------
        # 1. SIDEBAR REORDER WITH DEDICATED 05-EXPORT TAB
        # -----------------------------------------------------------------
        new_sidebar = """    <nav class="lcars-sidebar">
      <button id="navTabFullScan" class="lcars-pill pill-blue active" onclick="switchTab('fullscan')">
        <span class="tab-label">01-FULL SCAN</span>
      </button>
      <button id="navTabVitals" class="lcars-pill pill-banana" onclick="switchTab('vitals')">
        <span class="tab-label">02-VITALS</span>
      </button>
      <button id="navTabCardio" class="lcars-pill pill-orange" onclick="switchTab('cardio')">
        <span class="tab-label">03-CARDIO</span>
      </button>
      <button id="navTabMetabolic" class="lcars-pill pill-peach" onclick="switchTab('metabolic')">
        <span class="tab-label">04-METABOLIC</span>
      </button>
      <button id="navTabExport" class="lcars-pill pill-lilac" onclick="switchTab('export')" style="border-left: 5px solid #38bdf8;">
        <span class="tab-label">05-EXPORT</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">06-REPORTS</span>
      </button>
      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">07-ADA TRADES</span>
      </button>
      <button id="navTabHelp" class="lcars-pill pill-banana" onclick="switchTab('help')">
        <span class="tab-label">08-HELP &amp; FAQ</span>
      </button>
      <button id="navTabMedication" class="lcars-pill pill-orange" onclick="switchTab('medication')">
        <span class="tab-label">09-MEDICATION</span>
      </button>
      <button id="navTabGoals" class="lcars-pill pill-green" onclick="switchTab('goals')">
        <span class="tab-label">10-CLINICAL GOALS</span>
      </button>
      <button id="navTabSetup" class="lcars-pill pill-lilac" onclick="switchTab('setup')">
        <span class="tab-label">11-CONFIG</span>
      </button>
      <button id="navTabOperations" class="lcars-pill pill-peach" onclick="switchTab('operations')">
        <span class="tab-label">12-OPERATIONS</span>
      </button>
      <button id="navTabLog" class="lcars-pill pill-ice" onclick="switchTab('log')">
        <span class="tab-label">13-DATA LOG</span>
      </button>
      <button id="navTabWatchFaces" class="lcars-pill pill-lilac" onclick="switchTab('watchfaces')">
        <span class="tab-label">14-WATCH FACES</span>
      </button>
      <div class="lcars-side-cap">MODEL 5010 &bull; CLINICAL TELEMETRY</div>
    </nav>"""

        pos_nav_start = html.find('<nav class="lcars-sidebar">')
        pos_nav_end = html.find('</nav>', pos_nav_start) + len('</nav>')
        if pos_nav_start != -1 and pos_nav_end != -1:
            html = html[:pos_nav_start] + new_sidebar + html[pos_nav_end:]
            print(f"[✔] Updated sidebar with 05-EXPORT tab in {fpath}")

        # -----------------------------------------------------------------
        # 2. CREATE PAGE FOR 05-EXPORT (#pageExport)
        # Place it right before #pageReports
        # -----------------------------------------------------------------
        export_page_html = """
      <!-- ================================================================= -->
      <!-- TAB 05: EHR DATA EXPORT (EPIC • CERNER • MYCHART DIRECT IMPORT)   -->
      <!-- ================================================================= -->
      <div id="pageExport" class="tab-page">
        <div class="card-box" style="margin-bottom: 25px; border-left: 6px solid var(--lcars-blue);">
          
          <div class="box-header" style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #cbd5e1; padding-bottom: 12px; margin-bottom: 18px; flex-wrap: wrap; gap: 10px;">
            <div>
              <span class="box-title" style="color: #0284c7; font-size: 1.25rem; font-weight: 800; letter-spacing: 1px;">
                05-EHR DATA EXPORT (EPIC &bull; CERNER &bull; MYCHART)
              </span>
              <div style="font-size: 0.82rem; color: #475569; margin-top: 3px;">
                STANDARDIZED TELEMETRY FOR DIRECT INGESTION INTO ELECTRONIC HEALTH RECORDS &bull; PATIENT-DIRECTED DELIVERY
              </div>
            </div>
            <div style="display: flex; gap: 8px; align-items: center;">
              <span class="badge" style="background: #e0f2fe; color: #0369a1; border: 1.5px solid #0284c7; font-weight: 800; font-size: 0.78rem;">
                ✓ HL7 FHIR R4
              </span>
              <span class="badge" style="background: #dcfce7; color: #15803d; border: 1.5px solid #16a34a; font-weight: 800; font-size: 0.78rem;">
                ✓ ZERO CONVERSION NEEDED
              </span>
            </div>
          </div>

          <!-- Top Section: Patient EHR Information Card -->
          <div style="background: #ffffff; border: 1.5px solid #cbd5e1; border-left: 6px solid #0284c7; border-radius: 8px; padding: 16px; margin-bottom: 20px;">
            <div style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 12px; flex-wrap: wrap; gap: 8px;">
              <div>
                <strong style="color: #0f172a; font-size: 0.98rem; font-family: var(--lcars-mono);">
                  PATIENT EHR PROFILE &amp; PROVIDER TARGET
                </strong>
                <span style="font-size: 0.76rem; color: #64748b; margin-left: 8px;">(Configured in Tab 11 Config)</span>
              </div>
              <button class="lcars-btn btn-peach" style="font-size: 0.76rem; padding: 4px 12px;" onclick="switchTab('setup')">
                ⚙️ EDIT EHR SETUP IN CONFIG
              </button>
            </div>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(220px, 1fr)); gap: 12px; font-family: var(--lcars-mono);">
              <div style="background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.70rem; color: #64748b; font-weight: bold;">HEALTH SYSTEM / EHR PLATFORM</div>
                <div style="font-size: 0.90rem; font-weight: 800; color: #0284c7; margin-top: 2px;" id="dispExportHealthSys">EvergreenHealth (Epic Systems)</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.70rem; color: #64748b; font-weight: bold;">PATIENT MRN / CHART #</div>
                <div style="font-size: 0.90rem; font-weight: 800; color: #0f172a; margin-top: 2px;" id="dispExportMrn">PATIENT-MRN-948201</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.70rem; color: #64748b; font-weight: bold;">PRIMARY CARE PHYSICIAN / CLINIC</div>
                <div style="font-size: 0.90rem; font-weight: 800; color: #15803d; margin-top: 2px;" id="dispExportDoctorEmail">physician@evergreenhealth.com</div>
              </div>
              <div style="background: #f8fafc; border: 1px solid #e2e8f0; border-radius: 6px; padding: 10px;">
                <div style="font-size: 0.70rem; color: #64748b; font-weight: bold;">TELEMETRY TIME WINDOW</div>
                <div style="font-size: 0.90rem; font-weight: 800; color: #7c3aed; margin-top: 2px;">48 HOURS CONTINUOUS</div>
              </div>
            </div>
          </div>

          <!-- Section 2: Standard Format Export Grid (4 Actions) -->
          <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(320px, 1fr)); gap: 16px; margin-bottom: 22px;">
            
            <!-- Option 1: HL7 FHIR R4 JSON Bundle -->
            <div style="background: #ffffff; border: 2px solid #0284c7; border-radius: 8px; padding: 16px; display: flex; flex-direction: column; justify-content: space-between; box-shadow: 0 2px 5px rgba(0,0,0,0.05);">
              <div>
                <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 8px;">
                  <span style="font-size: 1.6rem;">📦</span>
                  <div>
                    <strong style="color: #0284c7; font-size: 1.05rem;">1. Epic FHIR R4 Collection (.JSON)</strong>
                    <div style="font-size: 0.74rem; color: #16a34a; font-weight: bold;">OFFICIAL USCDI v3 STANDARD &bull; ZERO CONVERSION</div>
                  </div>
                </div>
                <p style="font-size: 0.80rem; color: #475569; line-height: 1.45; margin-bottom: 12px;">
                  Complete 48-hour continuous collection bundle with pre-mapped LOINC observation codes (Glucose <code>2339-0</code>, eA1C <code>4548-4</code>, Blood Pressure <code>8480-6</code>, Heart Rate <code>8867-4</code>, SpO2 <code>59408-5</code>, Steps <code>41950-7</code>). Ingests directly into Epic Hyperspace, Cerner, or AthenaHealth flowsheets.
                </p>
              </div>
              <button onclick="downloadEpicFHIR()" class="lcars-btn" style="background: #0284c7; color: #ffffff; font-weight: 800; font-size: 0.88rem; padding: 10px 16px; border-radius: 6px; cursor: pointer; border: none; width: 100%;">
                📥 DOWNLOAD EPIC FHIR R4 (.JSON)
              </button>
            </div>

            <!-- Option 2: RFC-4180 Clinical CSV -->
            <div style="background: #ffffff; border: 2px solid #16a34a; border-radius: 8px; padding: 16px; display: flex; flex-direction: column; justify-content: space-between; box-shadow: 0 2px 5px rgba(0,0,0,0.05);">
              <div>
                <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 8px;">
                  <span style="font-size: 1.6rem;">📊</span>
                  <div>
                    <strong style="color: #15803d; font-size: 1.05rem;">2. Clinical Spreadsheet (.CSV)</strong>
                    <div style="font-size: 0.74rem; color: #15803d; font-weight: bold;">STANDARD LOINC HEADERS &bull; UNIVERSAL IMPORT</div>
                  </div>
                </div>
                <p style="font-size: 0.80rem; color: #475569; line-height: 1.45; margin-bottom: 12px;">
                  RFC-4180 standard spreadsheet containing timestamped glucose, hemodynamics, thermal metrics, and step activity with official LOINC headers. Opens cleanly in Microsoft Excel or EHR batch flowsheet import wizards without reformatting.
                </p>
              </div>
              <button onclick="downloadClinicalCSV()" class="lcars-btn" style="background: #16a34a; color: #ffffff; font-weight: 800; font-size: 0.88rem; padding: 10px 16px; border-radius: 6px; cursor: pointer; border: none; width: 100%;">
                📥 DOWNLOAD CLINICAL CSV (.CSV)
              </button>
            </div>

            <!-- Option 3: Draft Email to Doctor -->
            <div style="background: #ffffff; border: 2px solid #f59e0b; border-radius: 8px; padding: 16px; display: flex; flex-direction: column; justify-content: space-between; box-shadow: 0 2px 5px rgba(0,0,0,0.05);">
              <div>
                <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 8px;">
                  <span style="font-size: 1.6rem;">📧</span>
                  <div>
                    <strong style="color: #b45309; font-size: 1.05rem;">3. Draft Email to Doctor</strong>
                    <div style="font-size: 0.74rem; color: #b45309; font-weight: bold;">PRE-FILLED WITH CLINIC IMPORT INSTRUCTIONS</div>
                  </div>
                </div>
                <p style="font-size: 0.80rem; color: #475569; line-height: 1.45; margin-bottom: 12px;">
                  Launches your default email program with a pre-written clinical subject line, your MRN, and clear step-by-step instructions for clinic medical records staff explaining how to import your attached FHIR bundle directly into Epic.
                </p>
              </div>
              <button onclick="draftEmailToDoctor()" class="lcars-btn" style="background: #f59e0b; color: #ffffff; font-weight: 800; font-size: 0.88rem; padding: 10px 16px; border-radius: 6px; cursor: pointer; border: none; width: 100%;">
                📧 DRAFT EMAIL TO DOCTOR
              </button>
            </div>

            <!-- Option 4: Printable Clinical PDF Handbook -->
            <div style="background: #ffffff; border: 2px solid #7c3aed; border-radius: 8px; padding: 16px; display: flex; flex-direction: column; justify-content: space-between; box-shadow: 0 2px 5px rgba(0,0,0,0.05);">
              <div>
                <div style="display: flex; align-items: center; gap: 8px; margin-bottom: 8px;">
                  <span style="font-size: 1.6rem;">📄</span>
                  <div>
                    <strong style="color: #7c3aed; font-size: 1.05rem;">4. Doctor's Clinical Handbook (PDF)</strong>
                    <div style="font-size: 0.74rem; color: #7c3aed; font-weight: bold;">FULL CLINICAL &amp; METROLOGICAL SPECIFICATION</div>
                  </div>
                </div>
                <p style="font-size: 0.80rem; color: #475569; line-height: 1.45; margin-bottom: 12px;">
                  Complete 10-page clinical handbook detailing biophysical transducers, optical tonometry calibration, LOINC mapping, charging gap metrology, and Epic Hyperspace flowsheet integration for your attending physician.
                </p>
              </div>
              <button onclick="window.open('/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf', '_blank')" class="lcars-btn" style="background: #7c3aed; color: #ffffff; font-weight: 800; font-size: 0.88rem; padding: 10px 16px; border-radius: 6px; cursor: pointer; border: none; width: 100%;">
                📄 OPEN CLINICAL HANDBOOK (PDF)
              </button>
            </div>

          </div>

          <!-- Section 3: Patient Quick 3-Step Guide -->
          <div style="background: #f1f5f9; border: 1.5px dashed #94a3b8; border-radius: 8px; padding: 14px 18px;">
            <strong style="color: #0f172a; font-size: 0.92rem; display: block; margin-bottom: 6px;">
              💡 3 SIMPLE STEPS TO DELIVER DATA TO YOUR DOCTOR:
            </strong>
            <ol style="margin: 0 0 0 18px; padding: 0; font-size: 0.82rem; color: #334155; line-height: 1.5;">
              <li><strong>Download:</strong> Click <em>"Download Epic FHIR R4 (.JSON)"</em> or <em>"Download Clinical CSV"</em> above to save your telemetry file to your computer.</li>
              <li><strong>Deliver:</strong> Log in to your healthcare provider portal (e.g. <strong>Evergreen MyChart</strong>) and attach the file to a message for your doctor, or click <em>"Draft Email to Doctor"</em>.</li>
              <li><strong>Doctor Ingestion:</strong> Your physician or clinic staff opens your chart in Epic Hyperspace, selects <em>"External Document / Flowsheet Ingestion"</em>, and imports your telemetry directly into your chart. <strong>Zero translation or conversion required!</strong></li>
            </ol>
          </div>

        </div>
      </div>
"""

        if 'id="pageExport"' not in html:
            pos_rep = html.find('id="pageReports"')
            if pos_rep != -1:
                # Find container start of pageReports
                pos_rep_div = html.rfind('<div id="pageReports"', 0, pos_rep + 25)
                if pos_rep_div != -1:
                    html = html[:pos_rep_div] + export_page_html + "\n\n      " + html[pos_rep_div:]
                    print(f"[✔] Injected #pageExport right before #pageReports in {fpath}")

        # -----------------------------------------------------------------
        # 3. ADD PATIENT EHR SETUP IN TAB 11 CONFIG (#pageSetup)
        # -----------------------------------------------------------------
        config_ehr_section = """
          <!-- SECTION 07: PATIENT EHR SETUP (EPIC / CERNER / MYCHART) -->
          <div class="setup-section" style="border-left-color: #0284c7; margin-top: 18px; background: #ffffff; border: 1.5px solid #cbd5e1; border-left: 6px solid #0284c7; border-radius: 8px; padding: 16px;">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; border-bottom: 1.5px solid #e2e8f0; padding-bottom: 8px; margin-bottom: 12px;">
              <div>
                <span style="color: #0284c7; font-size: 1.15rem; font-weight: 800;">
                  07. PATIENT EHR SETUP (EPIC &bull; CERNER &bull; MYCHART)
                </span>
                <span style="font-size: 0.78rem; color: #64748b; display: block; margin-top: 2px;">
                  CONFIGURE YOUR HEALTHCARE PROVIDER &amp; MEDICAL RECORD NUMBER FOR 1-CLICK EHR EXPORT
                </span>
              </div>
              <span class="badge" style="background: #e0f2fe; color: #0369a1; border: 1px solid #0284c7; font-size: 0.75rem; font-weight: bold;">
                EHR AUTO-SYNC
              </span>
            </div>

            <div class="setup-grid" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(260px, 1fr)); gap: 12px;">
              <div class="setup-field">
                <label class="setup-label" style="font-size: 0.74rem; font-weight: bold; color: #475569;">HEALTH SYSTEM / EHR PLATFORM</label>
                <select id="cfgEhrSystem" class="setup-select" onchange="savePatientEhrConfig()" style="width: 100%; padding: 7px 10px; border: 1.5px solid #94a3b8; border-radius: 4px; font-weight: 600; font-size: 0.85rem; color: #0f172a; background: #ffffff;">
                  <option value="EvergreenHealth • Epic Systems (Evergreen MyChart)" selected>EvergreenHealth • Epic Systems (Evergreen MyChart)</option>
                  <option value="Providence Health • Epic MyChart">Providence Health • Epic MyChart</option>
                  <option value="UW Medicine • Epic Hyperspace">UW Medicine • Epic Hyperspace</option>
                  <option value="Overlake Medical • Epic Systems">Overlake Medical • Epic Systems</option>
                  <option value="Kaiser Permanente • Epic HealthConnect">Kaiser Permanente • Epic HealthConnect</option>
                  <option value="Universal EHR / Cerner / Other">Universal EHR / Cerner / Other</option>
                </select>
              </div>

              <div class="setup-field">
                <label class="setup-label" style="font-size: 0.74rem; font-weight: bold; color: #475569;">PATIENT MRN (CHART NUMBER)</label>
                <input type="text" id="cfgEhrMrn" class="setup-input" value="PATIENT-MRN-948201" onchange="savePatientEhrConfig()" style="width: 100%; padding: 7px 10px; border: 1.5px solid #94a3b8; border-radius: 4px; font-family: monospace; font-weight: bold; font-size: 0.85rem; color: #0284c7; background: #ffffff;">
              </div>

              <div class="setup-field">
                <label class="setup-label" style="font-size: 0.74rem; font-weight: bold; color: #475569;">ATTENDING PHYSICIAN / CLINIC EMAIL</label>
                <input type="email" id="cfgEhrDoctorEmail" class="setup-input" value="physician@evergreenhealth.com" onchange="savePatientEhrConfig()" style="width: 100%; padding: 7px 10px; border: 1.5px solid #94a3b8; border-radius: 4px; font-size: 0.85rem; color: #0f172a; background: #ffffff;">
              </div>

              <div class="setup-field">
                <label class="setup-label" style="font-size: 0.74rem; font-weight: bold; color: #475569;">PREFERRED EXPORT STANDARD</label>
                <select id="cfgEhrFormat" class="setup-select" onchange="savePatientEhrConfig()" style="width: 100%; padding: 7px 10px; border: 1.5px solid #94a3b8; border-radius: 4px; font-weight: 600; font-size: 0.85rem; color: #0f172a; background: #ffffff;">
                  <option value="fhir" selected>HL7 FHIR Release 4 JSON (USCDI v3 Compliant)</option>
                  <option value="csv">Standard RFC-4180 CSV with LOINC Headers</option>
                </select>
              </div>
            </div>

            <div style="display: flex; justify-content: space-between; align-items: center; margin-top: 12px; border-top: 1px solid #e2e8f0; padding-top: 10px; flex-wrap: wrap; gap: 8px;">
              <span id="lblEhrConfigStatus" style="font-size: 0.76rem; color: #16a34a; font-weight: bold;">
                ✓ Patient EHR Configuration Synchronized with Tab 05 Export
              </span>
              <button type="button" class="lcars-btn btn-green" onclick="savePatientEhrConfig()" style="font-size: 0.82rem; padding: 6px 16px; font-weight: bold;">
                💾 SAVE PATIENT EHR SETUP
              </button>
            </div>
          </div>
"""

        if 'id="cfgEhrSystem"' not in html:
            pos_setup_end = html.find('id="pageOperations"')
            if pos_setup_end != -1:
                # Find container close of pageSetup
                pos_close_div = html.rfind('</div>\n      </div>', 0, pos_setup_end)
                if pos_close_div != -1:
                    html = html[:pos_close_div] + config_ehr_section + "\n        " + html[pos_close_div:]
                    print(f"[✔] Injected Patient EHR Setup into Tab 11 Config in {fpath}")

        # -----------------------------------------------------------------
        # 4. REMOVE REPETITIVE SECTION 05 FROM TAB 12 OPERATIONS
        # -----------------------------------------------------------------
        pos_sec05_ops = html.find('05. EHR INTEROPERABILITY &amp; CLINIC INGESTION PROTOCOL')
        if pos_sec05_ops != -1:
            pos_sec05_start = html.rfind('<div class="setup-section"', 0, pos_sec05_ops)
            pos_sec05_end = html.find('<!-- =====================================================================\n           TAB 8:', pos_sec05_ops)
            if pos_sec05_start != -1 and pos_sec05_end != -1:
                html = html[:pos_sec05_start] + html[pos_sec05_end:]
                print(f"[✔] Removed repetitive EHR clutter from Tab 12 Operations in {fpath}")

        # -----------------------------------------------------------------
        # 5. UPDATE SWITCHTAB TO HANDLE 'export'
        # -----------------------------------------------------------------
        switch_tab_export_code = """      } else if (tabId === 'export') {
        const btnExp = document.getElementById('navTabExport');
        const pageExp = document.getElementById('pageExport');
        if (btnExp) btnExp.classList.add('active');
        if (pageExp) pageExp.classList.add('active-page');
        syncExportPageWithConfig();"""

        if "else if (tabId === 'export')" not in html:
            pos_sw_metabolic = html.find("} else if (tabId === 'reports') {")
            if pos_sw_metabolic != -1:
                html = html[:pos_sw_metabolic] + switch_tab_export_code + "\n" + html[pos_sw_metabolic:]
                print(f"[✔] Injected 'export' tab handler into switchTab() in {fpath}")

        # -----------------------------------------------------------------
        # 6. ADD PATIENT EHR CONFIG JS HELPERS
        # -----------------------------------------------------------------
        js_ehr_config_helpers = """
    // =========================================================================
    // PATIENT EHR CONFIGURATION & EXPORT TAB SYNCHRONIZATION
    // =========================================================================
    function getPatientEhrConfig() {
      try {
        const saved = localStorage.getItem('veepoo_patient_ehr_config');
        if (saved) return JSON.parse(saved);
      } catch(e) {}
      return {
        healthSys: "EvergreenHealth • Epic Systems (Evergreen MyChart)",
        mrn: "PATIENT-MRN-948201",
        doctorEmail: "physician@evergreenhealth.com",
        prefFormat: "fhir"
      };
    }

    function savePatientEhrConfig() {
      const cfg = {
        healthSys: document.getElementById('cfgEhrSystem')?.value || "EvergreenHealth • Epic Systems (Evergreen MyChart)",
        mrn: document.getElementById('cfgEhrMrn')?.value || "PATIENT-MRN-948201",
        doctorEmail: document.getElementById('cfgEhrDoctorEmail')?.value || "physician@evergreenhealth.com",
        prefFormat: document.getElementById('cfgEhrFormat')?.value || "fhir"
      };
      try {
        localStorage.setItem('veepoo_patient_ehr_config', JSON.stringify(cfg));
      } catch(e) {}
      syncExportPageWithConfig();
      const statusEl = document.getElementById('lblEhrConfigStatus');
      if (statusEl) {
        statusEl.textContent = `✓ Saved & Synced with 05-EXPORT at ${new Date().toLocaleTimeString()}`;
        setTimeout(() => {
          if (statusEl) statusEl.textContent = "✓ Patient EHR Configuration Synchronized with Tab 05 Export";
        }, 3000);
      }
      if (typeof soundNotification === 'function') soundNotification();
    }

    function syncExportPageWithConfig() {
      const cfg = getPatientEhrConfig();
      
      // Update Tab 05 Export displays
      const dSys = document.getElementById('dispExportHealthSys');
      const dMrn = document.getElementById('dispExportMrn');
      const dEmail = document.getElementById('dispExportDoctorEmail');
      if (dSys) dSys.textContent = cfg.healthSys;
      if (dMrn) dMrn.textContent = cfg.mrn;
      if (dEmail) dEmail.textContent = cfg.doctorEmail;

      // Update Config inputs
      const cSys = document.getElementById('cfgEhrSystem');
      const cMrn = document.getElementById('cfgEhrMrn');
      const cEmail = document.getElementById('cfgEhrDoctorEmail');
      const cFmt = document.getElementById('cfgEhrFormat');
      if (cSys && !cSys.dataset.userEdited) cSys.value = cfg.healthSys;
      if (cMrn && !cMrn.dataset.userEdited) cMrn.value = cfg.mrn;
      if (cEmail && !cEmail.dataset.userEdited) cEmail.value = cfg.doctorEmail;
      if (cFmt && !cFmt.dataset.userEdited) cFmt.value = cfg.prefFormat;

      // Also sync modal inputs if opened
      const mSys = document.getElementById('epicHealthSystem');
      const mMrn = document.getElementById('epicMRN');
      const mEmail = document.getElementById('txtDoctorEmail');
      if (mSys) mSys.value = cfg.healthSys.toLowerCase().includes('evergreen') ? 'evergreen' : 'universal';
      if (mMrn) mMrn.value = cfg.mrn;
      if (mEmail) mEmail.value = cfg.doctorEmail;
    }
"""

        if "function getPatientEhrConfig()" not in html:
            pos_js_insert = html.find("function downloadEpicFHIR()")
            if pos_js_insert != -1:
                html = html[:pos_js_insert] + js_ehr_config_helpers + "\n\n    " + html[pos_js_insert:]
                print(f"[✔] Injected EHR config JS helpers into {fpath}")

        # Ensure initApp initializes syncExportPageWithConfig
        if "syncExportPageWithConfig();" not in html and "function initApp()" in html:
            html = html.replace("function initApp() {", "function initApp() {\n      syncExportPageWithConfig();")
            print(f"[✔] Added syncExportPageWithConfig() to initApp() in {fpath}")

        with open(fpath, "w", encoding="utf-8") as f:
            f.write(html)
        print(f"[✔] Saved {fpath}")

if __name__ == "__main__":
    implement_export_tab_and_config()
