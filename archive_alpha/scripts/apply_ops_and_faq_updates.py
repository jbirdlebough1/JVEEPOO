import hashlib

ops_section_05_html = """
          <!-- =========================================================================
               SECTION 05: SPECIALIZED CARDIOMETABOLIC PROCEDURES & EHR INGESTION (ECG, BLOOD COMP, MET)
               ========================================================================= -->
          <div class="setup-section" style="border-left-color: var(--lcars-peach); margin-top: 25px;">
            <div class="setup-section-title">
              <span style="color: var(--lcars-peach);">SECTION 05: SPECIALIZED CARDIOMETABOLIC PROCEDURES & CLINICAL PROTOCOLS</span>
              <span style="font-size: 0.8rem; color: var(--lcars-peach);">ACTIVE MEASUREMENT PROCEDURES, THORACIC CIRCUITS & AUTONOMOUS SENSOR DERIVATIONS</span>
            </div>
            
            <p style="color: #cbd5e1; font-size: 0.95rem; line-height: 1.6; margin-bottom: 16px;">
              Attending physicians and patients frequently inquire whether advanced cardiometabolic diagnostics require deliberate patient execution on the watch or occur autonomously in the background. The matrix below defines the exact physical execution procedures, sensor transducer mechanics, clinical significance, and LCARS workstation longitudinal reporting for all specialized diagnostics.
            </p>

            <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(360px, 1fr)); gap: 16px; margin-bottom: 20px;">
              <!-- Test A: ECG & QTc -->
              <div style="background: #0f172a; border: 1.5px solid #334155; border-radius: 8px; padding: 16px; border-left: 5px solid #ff5566;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                  <span style="color: #ff5566; font-weight: 800; font-size: 1.05rem;">A. LEAD-I ECG &amp; QTC INTERVAL</span>
                  <span style="background: rgba(255,85,102,0.15); color: #ff5566; padding: 2px 8px; border-radius: 4px; font-size: 0.75rem; font-weight: 700;">USER-INITIATED (ON WATCH)</span>
                </div>
                <div style="font-size: 0.85rem; color: #94a3b8; margin-bottom: 8px; line-height: 1.4;">
                  <strong>Transducer Circuit:</strong> Bipolar differential potential between watch rear bottom titanium backplate and right-side bezel stainless steel electrode.
                </div>
                <div style="background: #1e293b; padding: 10px; border-radius: 6px; margin-bottom: 10px; font-size: 0.85rem; color: #f1f5f9;">
                  <strong style="color: var(--lcars-banana);">HOW TO EXECUTE:</strong>
                  <ol style="margin-left: 18px; margin-top: 4px; padding: 0; line-height: 1.5;">
                    <li>Fasten watch securely on wrist. Sit relaxed with forearm supported.</li>
                    <li>On watch screen, swipe to the <strong>ECG (Electrocardiogram)</strong> menu.</li>
                    <li>Place thumb and index finger of <em>opposite hand</em> firmly on the <strong>right side bezel electrode</strong>.</li>
                    <li>Maintain steady, relaxed contact for <strong>30 uninterrupted seconds</strong> without talking or flexing.</li>
                  </ol>
                </div>
                <div style="font-size: 0.85rem; color: #cbd5e1; line-height: 1.4;">
                  <strong>Clinical Significance:</strong> Completes Einthoven Lead-I thoracic circuit across the chest cavity. Derives cardiac vector, detects dysrhythmias (PVCs, PACs, atrial fibrillation), and calculates QTc interval (<440ms normal) to screen for Diabetic Autonomic Neuropathy (DAN). Synced into LCARS <strong>Sub-Report 02</strong> with Poincaré 2D Lorenz scatter plots and weekly/monthly/quarterly/YTD trend curves.
                </div>
              </div>

              <!-- Test B: Blood Composition & Viscosity -->
              <div style="background: #0f172a; border: 1.5px solid #334155; border-radius: 8px; padding: 16px; border-left: 5px solid #ff7788;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                  <span style="color: #ff7788; font-weight: 800; font-size: 1.05rem;">B. BLOOD COMPOSITION &amp; VISCOSITY</span>
                  <span style="background: rgba(255,119,136,0.15); color: #ff7788; padding: 2px 8px; border-radius: 4px; font-size: 0.75rem; font-weight: 700;">ACTIVE OPTICAL SWEEP</span>
                </div>
                <div style="font-size: 0.85rem; color: #94a3b8; margin-bottom: 8px; line-height: 1.4;">
                  <strong>Transducer Circuit:</strong> Multi-wavelength optical plethysmography (525nm green, 660nm red, 940nm infrared) analyzing differential optical attenuation and pulsation rheology.
                </div>
                <div style="background: #1e293b; padding: 10px; border-radius: 6px; margin-bottom: 10px; font-size: 0.85rem; color: #f1f5f9;">
                  <strong style="color: var(--lcars-banana);">HOW TO EXECUTE:</strong>
                  <ol style="margin-left: 18px; margin-top: 4px; padding: 0; line-height: 1.5;">
                    <li>Best executed <strong>fasting in the morning</strong> prior to caffeine, food, or vigorous activity.</li>
                    <li>Rest wrist flat on table at heart level.</li>
                    <li>Select <strong>Blood Composition</strong> on watch screen, or click <em>Execute Full Scan</em> in LCARS Tab 12.</li>
                    <li>Remain motionless for <strong>30 seconds</strong> while multi-spectral optical diodes analyze cutaneous microcirculation.</li>
                  </ol>
                </div>
                <div style="font-size: 0.85rem; color: #cbd5e1; line-height: 1.4;">
                  <strong>Clinical Significance:</strong> Estimates microvascular blood viscosity (cP, target 3.5-4.5 cP), total lipid proxy (optimal <200 mg/dL), and serum uric acid (target <6.5 mg/dL). Enables early detection of hemorheological hyperviscosity and renal microvascular compromise. Synced into LCARS <strong>Sub-Report 04</strong> across weekly, monthly, quarterly, and YTD trend timelines.
                </div>
              </div>

              <!-- Test C: MET Energy Expenditure -->
              <div style="background: #0f172a; border: 1.5px solid #334155; border-radius: 8px; padding: 16px; border-left: 5px solid #22c55e;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                  <span style="color: #22c55e; font-weight: 800; font-size: 1.05rem;">C. MET ENERGY EXPENDITURE</span>
                  <span style="background: rgba(34,197,94,0.15); color: #22c55e; padding: 2px 8px; border-radius: 4px; font-size: 0.75rem; font-weight: 700;">100% AUTONOMOUS (24/7 BACKGROUND)</span>
                </div>
                <div style="font-size: 0.85rem; color: #94a3b8; margin-bottom: 8px; line-height: 1.4;">
                  <strong>Transducer Engine:</strong> Continuous triaxial accelerometer kinetics coupled with high-frequency PPG optical pulse rate integration.
                </div>
                <div style="background: #1e293b; padding: 10px; border-radius: 6px; margin-bottom: 10px; font-size: 0.85rem; color: #f1f5f9;">
                  <strong style="color: var(--lcars-banana);">HOW IT WORKS:</strong>
                  <ul style="margin-left: 18px; margin-top: 4px; padding: 0; line-height: 1.5; list-style-type: disc;">
                    <li><strong>Zero user intervention required:</strong> Operates continuously 24 hours a day while worn.</li>
                    <li>Firmware categorizes movement into intensity bins: Resting (1.0 MET), Sedentary (1.2-1.4 MET), Moderate (3.5 MET), Vigorous (≥6.0 MET).</li>
                    <li>Accumulates hourly and daily total <strong>MET-hours</strong> and logs them to internal non-volatile flash.</li>
                  </ul>
                </div>
                <div style="font-size: 0.85rem; color: #cbd5e1; line-height: 1.4;">
                  <strong>Clinical Significance:</strong> Directly quantifies physical activity dose for American Diabetes Association (ADA) compliance (target ≥28.0 MET-hrs/day). Non-insulin-mediated GLUT4 glucose translocation increases dramatically with sustained moderate activity. Synced into LCARS <strong>Sub-Report 03</strong> with daily, weekly, monthly, quarterly, and YTD longitudinal progress curves.
                </div>
              </div>

              <!-- Test D: Body Composition BIA -->
              <div style="background: #0f172a; border: 1.5px solid #334155; border-radius: 8px; padding: 16px; border-left: 5px solid #38bdf8;">
                <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 8px;">
                  <span style="color: #38bdf8; font-weight: 800; font-size: 1.05rem;">D. BODY COMPOSITION (BIA)</span>
                  <span style="background: rgba(56,189,248,0.15); color: #38bdf8; padding: 2px 8px; border-radius: 4px; font-size: 0.75rem; font-weight: 700;">USER-INITIATED (ON WATCH)</span>
                </div>
                <div style="font-size: 0.85rem; color: #94a3b8; margin-bottom: 8px; line-height: 1.4;">
                  <strong>Transducer Circuit:</strong> Dual-frequency bioelectrical impedance analysis (BIA) micro-current passed between watch case contacts and opposite finger pads.
                </div>
                <div style="background: #1e293b; padding: 10px; border-radius: 6px; margin-bottom: 10px; font-size: 0.85rem; color: #f1f5f9;">
                  <strong style="color: var(--lcars-banana);">HOW TO EXECUTE:</strong>
                  <ol style="margin-left: 18px; margin-top: 4px; padding: 0; line-height: 1.5;">
                    <li>Ensure watch backplate has snug skin contact on wrist.</li>
                    <li>Navigate to <strong>Body Composition</strong> on watch display.</li>
                    <li>Touch opposite hand thumb and forefinger to the top and bottom electrode contacts.</li>
                    <li>Maintain contact for <strong>15 seconds</strong> while micro-impedance is sampled.</li>
                  </ol>
                </div>
                <div style="font-size: 0.85rem; color: #cbd5e1; line-height: 1.4;">
                  <strong>Clinical Significance:</strong> Differentiates skeletal muscle mass (glucose storage sink) from visceral adiposity (insulin resistance driver). Synced into LCARS <strong>Sub-Report 05</strong> across weekly, monthly, quarterly, and yearly timelines.
                </div>
              </div>
            </div>

            <!-- Attending Physician Longitudinal Clinical Protocol -->
            <div style="background: #1e293b; border: 1px solid #475569; border-radius: 8px; padding: 16px;">
              <strong style="color: var(--lcars-peach); font-size: 1.05rem; letter-spacing: 0.05em;">PHYSICIAN LONGITUDINAL INTERPRETATION GUIDE: ASSESSING PROGRESS VS. REGRESSION</strong>
              <p style="font-size: 0.85rem; color: #cbd5e1; margin-top: 8px; line-height: 1.5;">
                Raw telemetry numbers provide immediate snapshot validation, but longitudinal multi-timeframe trend curves (Weekly, Monthly, Quarterly, YTD) are essential for clinical treatment modification:
              </p>
              <ul style="font-size: 0.85rem; color: #cbd5e1; margin-left: 20px; margin-top: 6px; line-height: 1.5;">
                <li><strong>Quarterly (QTR) &amp; Yearly (YTD) Trends:</strong> Evaluate whether medical nutritional therapy, SGLT2 inhibitors, GLP-1 receptor agonists, or exercise regimens are reversing microvascular viscosity, lowering baseline glycemic excursions, or stabilizing autonomic QTc intervals over 90 to 365 days.</li>
                <li><strong>Weekly &amp; Monthly Trends:</strong> Correlate short-term medication adjustments and lifestyle interventions directly against daily MET-hour accumulations and postprandial glucose volatility.</li>
                <li><strong>EHR Ingestion &amp; Export:</strong> Attending physicians can click <strong>[ PRINT REPORT / SAVE PDF ]</strong> in Tab 04 or <strong>[ EXPORT CLINICAL CSV ]</strong> in Tab 06 to ingest complete timestamped telemetry and vector charts directly into outpatient EHR charts (Epic, Cerner, Allscripts).</li>
              </ul>
            </div>
          </div>
"""

faq_q6_q7_html = """
              <!-- Q6: Specialized Diagnostics Execution (ECG, MET, Blood Composition) -->
              <div style="background: #090e18; padding: 14px; border-radius: 6px; border-left: 4px solid var(--lcars-peach);">
                <strong style="color: var(--lcars-peach); font-size: 1.05rem;">Q6: Do ECG, Blood Composition, and MET require manual execution on the watch, or does LCARS collect them automatically?</strong>
                <p style="margin-top: 6px; color: #ccd6e8; font-size: 0.88rem; line-height: 1.5;">
                  Different biophysical transducers require specific physical execution procedures depending on the circuit required:
                </p>
                <ul style="margin-left: 20px; color: #ccd6e8; display: flex; flex-direction: column; gap: 6px; margin-top: 6px; font-size: 0.88rem; line-height: 1.45;">
                  <li><strong>1. Lead-I ECG &amp; QTc (Manual On Watch):</strong> Requires patient action. You must select ECG on the watch face and touch the <em>right side bezel electrode</em> with the fingers of your opposite hand for 30 uninterrupted seconds. This completes the thoracic Einthoven Lead-I circuit across your chest cavity. LCARS then syncs and graphs the recorded waveform, QTc interval, and autonomic LF/HF balance.</li>
                  <li><strong>2. Blood Composition &amp; Viscosity (Active Optical Sweep):</strong> Best performed resting and fasting in the morning. Rest wrist on table at heart level and select Blood Composition on watch (or run via LCARS Tab 12 Full Scan). The watch performs a 30-second multi-wavelength optical sweep measuring microvascular viscosity, lipids, and serum uric acid.</li>
                  <li><strong>3. MET Energy Expenditure (100% Autonomous 24/7 Background):</strong> <em>Zero manual effort required.</em> The watch firmware continuously analyzes kinetic triaxial accelerometry and optical pulse rate to calculate MET work rates (Resting 1.0, Sedentary 1.2-1.4, Walk 3.5, Exercise ≥6.0 MET). Cumulative daily MET-hours are recorded continuously and synced into LCARS automatically.</li>
                  <li><strong>4. Body Composition BIA (Manual On Watch):</strong> Requires touching the dual electrode plates with opposite fingers for 15 seconds to pass safe micro-current impedance.</li>
                </ul>
              </div>

              <!-- Q7: Long-Term Cardiometabolic Trend Tracking -->
              <div style="background: #090e18; padding: 14px; border-radius: 6px; border-left: 4px solid var(--lcars-green);">
                <strong style="color: var(--lcars-green); font-size: 1.05rem;">Q7: Where can my doctor and I see long-term cardiometabolic trends (Weekly, Monthly, Quarterly, Yearly)?</strong>
                <p style="margin-top: 6px; color: #ccd6e8; font-size: 0.88rem; line-height: 1.5;">
                  In <strong>04-REPORTS</strong>, click the timeframe selector buttons (<strong>DAILY 24-HOUR</strong>, <strong>WEEKLY 7-DAY</strong>, <strong>MONTHLY 30-DAY</strong>, <strong>QUARTERLY (90-DAY)</strong>, or <strong>YEARLY (YTD)</strong>). The workstation dynamically updates all 16 clinical SVG trend charts including:
                </p>
                <ul style="margin-left: 20px; color: #ccd6e8; display: flex; flex-direction: column; gap: 4px; margin-top: 6px; font-size: 0.88rem; line-height: 1.45;">
                  <li><strong>Sub-Report 02:</strong> ECG &amp; Autonomic Balance (QTc interval duration vs 440ms ceiling + LF/HF sympathovagal ratio).</li>
                  <li><strong>Sub-Report 03:</strong> MET Energy Expenditure (Daily cumulative MET-hours vs ADA ≥28.0 MET-hrs/day target).</li>
                  <li><strong>Sub-Report 04:</strong> Blood Composition &amp; Viscosity (Microvascular blood viscosity vs 4.5 cP ceiling + Serum Uric Acid vs 6.5 mg/dL).</li>
                  <li><strong>Sub-Report 05:</strong> Body Composition BIA (Longitudinal Skeletal Muscle % vs Body Fat % trajectories).</li>
                </ul>
                <p style="margin-top: 6px; color: #cbd5e1; font-size: 0.88rem;">
                  Both patient and attending doctor can assess real physiological progress or regression over time, rather than relying on isolated single-point numbers.
                </p>
              </div>
"""

for filepath in ['index.html', 'w11_htm_app/index.html']:
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    # 1. Insert Section 05 into pageOperations
    # Target: replace the placeholder comment or insert before the closing divs of pageOperations
    target_ops = """          <!-- =========================================================================
               SECTION 05: EHR INTEROPERABILITY & INGESTION GUIDE FOR ATTENDING PHYSICIANS
               ========================================================================= -->"""
    
    if target_ops in content:
        content = content.replace(target_ops, ops_section_05_html)
    else:
        print(f"Warning: target_ops not found in {filepath}")

    # 2. Insert Q6 & Q7 into pageHelp
    # Target: right after Q5 closing div
    target_faq = """                    <a href="http://localhost:8899/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf" target="_blank" style="color: var(--lcars-blue); text-decoration: underline;">
                      http://localhost:8899/docs/VEEPOO_5010_CLINICAL_SPECIFICATION_AND_REPORTING_GUIDE.pdf
                    </a>
                  </li>
                </ol>
              </div>"""

    if target_faq in content:
        content = content.replace(target_faq, target_faq + "\n" + faq_q6_q7_html)
    else:
        print(f"Warning: target_faq not found in {filepath}")

    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

    # Verification
    data = open(filepath, 'rb').read()
    h = hashlib.sha256(data).hexdigest()
    text = data.decode('utf-8', errors='ignore')
    op = text.count('<div')
    cl = text.count('</div>')
    print(f"Updated {filepath}: size={len(data)} sha256={h} open={op} close={cl} diff={op-cl}")

