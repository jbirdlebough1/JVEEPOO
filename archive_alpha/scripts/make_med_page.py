def get_medication_page_html():
    return '''      <!-- =====================================================================
           TAB 11: MEDICATION MANAGEMENT, INTAKE SCHEDULE & TITRATION PROGRESS TRACKER
           ===================================================================== -->
      <div id="pageMedication" class="tab-page">
        <div class="diet-container">

          <!-- CRITICAL CLINICAL SAFETY WARNING: NOT FOR TYPE 1 DIABETICS / INSULIN TITRATION -->
          <div style="background: rgba(230, 57, 70, 0.15); border: 2px solid #e63946; border-left: 8px solid #e63946; border-radius: 6px; padding: 14px 18px; margin-bottom: 8px;">
            <div style="display: flex; align-items: flex-start; gap: 12px;">
              <span style="font-size: 1.8rem; line-height: 1; flex-shrink: 0;">⚠️</span>
              <div>
                <strong style="color: #ff6b6b; font-size: 1.05rem; letter-spacing: 1px; font-family: var(--lcars-mono);">
                  CRITICAL CLINICAL NOTICE: NOT FOR TYPE 1 DIABETICS OR INDEPENDENT INSULIN TITRATION
                </strong>
                <div style="color: #ffd0d0; font-size: 0.85rem; line-height: 1.45; margin-top: 4px;">
                  All pharmacological treatments, oral antidiabetics, and subcutaneous regimens (Metformin, SGLT2 inhibitors, GLP-1 receptor agonists, or basal insulin) must be prescribed and adjusted <strong>strictly by your licensed medical doctor or endocrinologist</strong>.
                  <br><br>
                  This system tracks medication adherence and correlates biometric trend response to help you and your physician determine <strong>progress or regression</strong>. <strong>NEVER INDEPENDENTLY TITRATE OR CALCULATE INSULIN DOSAGES BASED ON OPTICAL TELEMETRY.</strong>
                </div>
              </div>
            </div>
          </div>

          <!-- COMPONENT 1: ACTIVE MEDICATION REGIMEN & DAILY INTAKE SCHEDULE -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-orange);">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: var(--lcars-orange);">01. ACTIVE PHARMACOLOGICAL REGIMEN &amp; INTAKE SCHEDULE</span>
                <span style="font-size: 0.8rem; color: var(--lcars-gray); display: block;">PRESCRIBED DOSAGE, TIMING &amp; DAILY ADHERENCE</span>
              </div>
              <div style="display: flex; gap: 8px;">
                <button class="lcars-btn btn-peach" style="padding: 4px 14px; font-size: 0.8rem;" onclick="switchTab('reports')">VIEW IN SYNCHRONIZED TALL CHART (TAB 04)</button>
              </div>
            </div>

            <!-- Active Meds Grid -->
            <div id="activeMedicationsGrid" style="display: grid; grid-template-columns: repeat(auto-fit, minmax(290px, 1fr)); gap: 12px; margin-top: 6px;">
              <!-- Populated by JavaScript -->
            </div>
          </div>

          <!-- COMPONENT 2: LOG MEDICATION CHANGE / TITRATION EVENT -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-banana);">
            <div class="setup-section-title">
              <span style="color: var(--lcars-banana);">02. LOG MEDICATION CHANGE OR TITRATION (PIN TO CLINICAL TIMELINE)</span>
              <span style="font-size: 0.85rem; color: var(--lcars-green);">AUTOMATICALLY FLAGS TIMELINE TO EVALUATE PROGRESS VS REGRESSION</span>
            </div>

            <div style="font-size: 0.86rem; color: var(--lcars-text); margin-bottom: 8px; line-height: 1.4;">
              When your physician changes a medication dosage, switches a drug class, or introduces a new agent, log the change below. The system pins a <strong>"💊 MED CHANGE"</strong> milestone marker across the daily, weekly, and yearly timeline charts so you and your doctor can visually evaluate whether the change prompted glycemic progress or regression.
            </div>

            <div class="diet-form-grid">
              <div class="setup-field">
                <label class="setup-label">CHANGE DATE &amp; TIME</label>
                <input type="datetime-local" id="medChangeTimestamp" class="setup-input">
              </div>
              <div class="setup-field">
                <label class="setup-label">MEDICATION NAME</label>
                <input type="text" id="medChangeName" class="setup-input" placeholder="e.g. Metformin ER, Empagliflozin, Ozempic">
              </div>
              <div class="setup-field">
                <label class="setup-label">CHANGE CLASSIFICATION</label>
                <select id="medChangeType" class="setup-select">
                  <option value="Dose Increase">DOSAGE INCREASE (TITRATION UP)</option>
                  <option value="Dose Decrease">DOSAGE DECREASE (TAPERING DOWN)</option>
                  <option value="New Medication">NEW MEDICATION INITIATED</option>
                  <option value="Discontinued">MEDICATION DISCONTINUED</option>
                  <option value="Schedule Change">INTAKE SCHEDULE / TIMING CHANGE</option>
                </select>
              </div>
              <div class="setup-field">
                <label class="setup-label">PREVIOUS DOSAGE</label>
                <input type="text" id="medChangeOldDose" class="setup-input" placeholder="e.g. 500 mg Daily">
              </div>
              <div class="setup-field">
                <label class="setup-label">NEW PRESCRIBED DOSAGE</label>
                <input type="text" id="medChangeNewDose" class="setup-input" placeholder="e.g. 1,000 mg BID (Breakfast &amp; Dinner)">
              </div>
              <div class="setup-field">
                <label class="setup-label">PRESCRIBING PHYSICIAN / CLINIC</label>
                <input type="text" id="medChangeDoctor" class="setup-input" placeholder="e.g. Dr. Martinez, Endocrinology Clinic">
              </div>
              <div class="setup-field" style="grid-column: span 2;">
                <label class="setup-label">CLINICAL RATIONALE &amp; EXPECTED OUTCOME</label>
                <input type="text" id="medChangeReason" class="setup-input" placeholder="e.g. Titrated up to address elevated morning fasting levels; target HbA1c &lt; 7.0%">
              </div>
            </div>
            <div style="display: flex; gap: 10px; margin-top: 10px;">
              <button class="lcars-btn btn-banana" style="flex: 2;" onclick="saveMedicationChange()">COMMIT MEDICATION CHANGE &amp; PIN TO CLINICAL TIMELINE</button>
              <button class="lcars-btn btn-orange" style="flex: 1;" onclick="clearMedChangeForm()">CLEAR FORM</button>
            </div>
          </div>

          <!-- COMPONENT 3: CHRONOLOGICAL MEDICATION CHANGE HISTORY & CLINICAL PROGRESS AUDIT -->
          <div class="diet-form-card" style="border-left-color: var(--lcars-lilac);">
            <div class="setup-section-title" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px;">
              <div>
                <span style="color: var(--lcars-lilac);">03. MEDICATION CHANGE AUDIT TRAIL &amp; CLINICAL RESPONSE EVALUATION</span>
                <span style="font-size: 0.8rem; color: var(--lcars-gray); display: block;">PRE-CHANGE VS POST-CHANGE GLYCEMIC TRAJECTORY ANALYSIS</span>
              </div>
              <div>
                <button class="lcars-btn btn-orange" style="padding: 4px 14px; font-size: 0.8rem;" onclick="clearAllMedChanges()">CLEAR CHANGE LOG</button>
              </div>
            </div>

            <!-- List of Medication Changes with Progress/Regression Badges -->
            <div class="diet-history-list" id="medicationChangeList">
              <!-- Populated by JavaScript -->
            </div>
          </div>

        </div>
      </div>
'''
