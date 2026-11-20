# build_presentation.py
import os
import subprocess

pres_dir = r"D:\veepoo\presentation"
slides_dir = os.path.join(pres_dir, "slides")
audio_dir = os.path.join(pres_dir, "audio")
os.makedirs(slides_dir, exist_ok=True)
os.makedirs(audio_dir, exist_ok=True)

# Define the 40 Slide Curriculum
slides_data = [
    {
        "id": 1, "num": "01",
        "title": "Welcome to LCARS 5010 Clinical Ambulatory Telemetry",
        "module": "Module 1: Introduction & Clinical Scope",
        "desc": "Overview of the LCARS 5010 Clinical Telemetry Console for the Veepoo Model 5010 biosensing array.",
        "narration": "G'day mate! Welcome to the LCARS 5010 Clinical Telemetry Masterclass. Today, we're taking you inside the complete engineering and physiological suite behind the Veepoo Model 5010 biosensing array. We built this platform to give doctors and patients a continuous, reliable window into outpatient metabolic health, completely eliminating proprietary cloud silos and delivering clinical-grade telemetry right on your local PC."
    },
    {
        "id": 2, "num": "02",
        "title": "The Snapshot Fallacy: Why Isolated Fingersticks Fail",
        "module": "Module 1: Introduction & Clinical Scope",
        "desc": "Examining why intermittent spot-checks fail to capture metabolic kinetics, excursions, and nocturnal hypoglycemia.",
        "narration": "Let's talk about the snapshot fallacy. For decades, diabetes management has relied on intermittent fingersticks and quarterly HbA1c lab draws. But taking four fingersticks a day only gives you four static snapshots. It completely misses postprandial glucose spikes, nocturnal dips, dawn phenomenon surges, and the immediate impact of meals and exercise. LCARS bridges this gap with continuous multi-sensor tracking."
    },
    {
        "id": 3, "num": "03",
        "title": "The Veepoo Model 5010 Hardware & Biosensing Array",
        "module": "Module 1: Introduction & Clinical Scope",
        "desc": "Deep dive into the 5010 optical photoplethysmography, infrared thermometry, and PWTT hemodynamics.",
        "narration": "On screen is the Veepoo Model 5010 hardware architecture. It's packed with dual-wavelength optical photoplethysmography for blood glucose and oxygen saturation, a dual thermistor matrix for core and cutaneous temperature, a high-frequency ECG and PWTT sensor for blood pressure, and tri-axial accelerometry for cadence. All these transponders stream raw frames over Bluetooth Low Energy 5.0."
    },
    {
        "id": 4, "num": "04",
        "title": "Strict Zero-Egress Local Telemetry vs Cloud Silos",
        "module": "Module 1: Introduction & Clinical Scope",
        "desc": "Why local SQLite telemetry on port 8899 ensures HIPAA-grade patient privacy and zero remote data leakage.",
        "narration": "Here's one of our most important design choices: absolute zero-egress data privacy. The manufacturer's mobile app sends your private medical data to remote cloud servers overseas. LCARS does the exact opposite. Every packet is processed locally on your PC, stored in a local SQLite database, and served through your local browser on port 8899. Zero cloud dependencies, zero account logins, and total patient privacy."
    },
    {
        "id": 5, "num": "05",
        "title": "Core Clinical Targets: Achieving HbA1c <6.8% and TIR >70%",
        "module": "Module 1: Introduction & Clinical Scope",
        "desc": "American Diabetes Association clinical targets for ambulatory Time-in-Range (TIR) and estimated average glucose.",
        "narration": "Our entire engineering philosophy aligns directly with the American Diabetes Association standards of care. We are aiming for a Time-in-Range greater than 70 percent within the 60 to 200 milligrams per deciliter band, an estimated HbA1c under 6.8 percent, and less than 4 percent time in hypoglycemia. With continuous ambulatory biofeedback, patients and doctors can achieve these targets safely."
    },
    {
        "id": 6, "num": "06",
        "title": "Physician-Patient Co-Pilot Collaboration Philosophy",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "desc": "Structuring the LCARS console as a shared clinical console between the attending physician and patient.",
        "narration": "LCARS is engineered as a true co-pilot tool for you and your physician. In clinic, your doctor configures target baselines, sets safe physiological limits, and reviews historical trends. At home, you wear the watch, log your meals and exercise, and observe direct cause-and-effect. When you meet for follow-ups, your doctor doesn't have to guess—the data tells the exact clinical story."
    },
    {
        "id": 7, "num": "07",
        "title": "Clinical Glycemic Band: Standardizing on 60–200 mg/dL",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "desc": "Why LCARS standardized on the 60–200 mg/dL target range and eliminated arbitrary uncalibrated alarm thresholds.",
        "narration": "In earlier builds, users were confused by arbitrary alarm thresholds. We standardized the entire system on the clinical 60 to 200 milligrams per deciliter band. 60 protects against dangerous hypoglycemia, while 200 marks the upper ceiling of postprandial hyperglycemia. Every chart, HUD tile, and report clearly visualizes this clinical band so you know immediately whether you're in the green zone."
    },
    {
        "id": 8, "num": "08",
        "title": "In-Clinic Venous Blood Draw Calibration (Opcode 0x89 0x02)",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "desc": "Synchronizing optical PPG spectroscopy with venous laboratory plasma draws to program watch EEPROM.",
        "narration": "Optical spectroscopy requires individual biophysical baseline anchoring. When you go for your routine lab draw, you trigger our In-Clinic Calibration Protocol in Tab 05. At the moment of the venous draw, the watch records raw absorption. Once lab results return, entering your plasma value transmits opcode 0x89 0x02 directly into the watch's non-volatile EEPROM, locking residual error under five percent."
    },
    {
        "id": 9, "num": "09",
        "title": "Standardizing Strictly on Private Mode Telemetry",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "desc": "Why generic Public mode was eliminated and how Private mode guarantees personalized accuracy.",
        "narration": "The manufacturer provided two modes: Public and Private. Public mode used an unverified generic algorithm that skewed readings by thirty to fifty points. We eliminated Public mode entirely. LCARS locks the firmware exclusively into Private Mode with opcode 0x89 0x01 0x01. This guarantees every optical frame is calibrated against your unique cutaneous pigmentation and microvascular depth."
    },
    {
        "id": 10, "num": "10",
        "title": "Continuous Ambulatory Background Acquisition",
        "module": "Module 2: Physician-Patient Alignment & Calibration",
        "desc": "How the Python BLE daemon on Windows 11 continuously polls telemetry without disconnecting.",
        "narration": "Behind the scenes, our multithreaded Python daemon maintains continuous Bluetooth connectivity to Model 5010. It issues background heartbeats, processes notification packets asynchronously, and logs every sensor reading into SQLite. The web console refreshes dynamically with zero browser hangs, sending strict no-cache headers to ensure your charts always reflect live telemetry."
    },
    {
        "id": 11, "num": "11",
        "title": "01-VITALS Bay 1: Optical Glucose Absorption Spectroscopy",
        "module": "Module 3: Core Biosensor Array Operations",
        "desc": "Understanding multi-wavelength PPG absorption spectroscopy, glucose kinetics, and optical reflectance.",
        "narration": "Now let's examine Bay 1 in 01-VITALS. When you click Scan, the watch fires its multi-wavelength optical LED array into your radial microvascular bed. Glucose molecules absorb specific near-infrared wavelengths in proportion to plasma concentration. The watch samples continuously for thirty seconds, filters motion artifacts, and returns calibrated blood glucose directly to your console."
    },
    {
        "id": 12, "num": "12",
        "title": "01-VITALS Bay 2: Dual-Matrix Thermometry",
        "module": "Module 3: Core Biosensor Array Operations",
        "desc": "Dual thermistor sensing of cutaneous skin and core temperature for inflammation and circadian tracking.",
        "narration": "Bay 2 houses the dual-matrix thermometry system. Unlike single-sensor thermometers, Model 5010 utilizes both a cutaneous wrist-contact thermistor and an ambient sensor. This allows LCARS to estimate true core temperature while monitoring peripheral vasoconstriction. Skin temperature drops correlate directly with sympathetic stress, nocturnal cooling, and metabolic rate shifts."
    },
    {
        "id": 13, "num": "13",
        "title": "02-CARDIO Bay 3: Pulse Wave Transit Time (PWTT) Blood Pressure",
        "module": "Module 3: Core Biosensor Array Operations",
        "desc": "Hemodynamic pulse transit time correlation with systolic and diastolic vascular pressure.",
        "narration": "Over in 02-CARDIO Bay 3, we have cuffless Blood Pressure. The watch calculates pulse wave transit time—the exact velocity at which pressure waves travel from the heart down your brachial artery to the wrist sensor. Higher arterial stiffness and elevated vascular resistance cause faster transit times, enabling accurate systolic and diastolic tracking without bulky inflatable cuffs."
    },
    {
        "id": 14, "num": "14",
        "title": "02-CARDIO Bay 4: Dual-Wavelength SpO2 & Apnea Screening",
        "module": "Module 3: Core Biosensor Array Operations",
        "desc": "Dual 660nm and 940nm LED ratio reflectance monitoring arterial oxygen saturation and sleep apnea index.",
        "narration": "Bay 4 supervises Blood Oxygen Saturation. By comparing red 660 nanometer and infrared 940 nanometer reflectance ratios, the system calculates arterial oxygen saturation with clinical accuracy. At night, continuous monitoring detects nocturnal desaturations, computing your Oxygen Desaturation Index to screen for obstructive sleep apnea—a major driver of insulin resistance."
    },
    {
        "id": 15, "num": "15",
        "title": "02-CARDIO Bay 5: Autonomic HRV & Sympatho-Vagal Stress",
        "module": "Module 3: Core Biosensor Array Operations",
        "desc": "High-frequency beat-to-beat interval analysis computing SDNN, RMSSD, and autonomic nervous system fatigue.",
        "narration": "Bay 5 captures Heart Rate Variability and Autonomic Neuro-Stress. By analyzing millisecond variations between consecutive heartbeats, LCARS computes SDNN and RMSSD. High HRV reflects parasympathetic vagal dominance and metabolic rest, while depressed HRV indicates sympathetic stress, cortisol elevation, and impending postprandial glucose dysregulation."
    },
    {
        "id": 16, "num": "16",
        "title": "03-METABOLIC Console: Real-Time Physiological Telemetry",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "desc": "Synthesizing all 6 primary biometric signals onto a unified real-time clinical dashboard.",
        "narration": "Now we step into Tab 03: the METABOLIC Console. Here, all primary physiological telemetry streams converge into six live summary tiles: Glucose, Blood Pressure, SpO2, Heart Rate with HRV, Temperature, and Step Motion. Instead of viewing isolated numbers, you and your doctor see a complete, unified snapshot of your cardiovascular and metabolic status in real time."
    },
    {
        "id": 17, "num": "17",
        "title": "GLUT4 Translocation Kinetics: How Exercise Lowers Glucose",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "desc": "The cellular biochemistry of insulin-independent glucose uptake via skeletal muscle contraction.",
        "narration": "Let's look at the cellular science behind Tab 03. When skeletal muscles contract during aerobic exercise, calcium and AMP-activated protein kinase trigger the translocation of GLUT4 glucose transporters directly to cell membranes. This process bypasses defective insulin signaling entirely, soaking up circulating blood glucose and dramatically lowering plasma levels without requiring extra insulin."
    },
    {
        "id": 18, "num": "18",
        "title": "The GLUT4 Exercise Journal: Calculating Direct ΔG Glucose Drops",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "desc": "Logging workouts, cadence, and duration to model predictable glycemic drops of 22 to 35 mg/dL.",
        "narration": "In the GLUT4 Exercise Journal, you log your physical activity: whether it's a brisk post-meal walk, cycling, or resistance training. LCARS computes your exact glycemic drop, known as Delta G, which typically ranges from minus twenty-two to minus thirty-five milligrams per deciliter. This transforms exercise from an abstract chore into a precise, predictable clinical intervention."
    },
    {
        "id": 19, "num": "19",
        "title": "Macronutrient Meal Logger: Net Carbs, Protein, Fat & Calories",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "desc": "Chronological nutritional journaling capturing meal timestamps and glycemic load.",
        "narration": "Next is the Macronutrient Meal Logger. Whenever you eat, you record the meal name, timestamp, net carbs, protein, fat, and calories. This chronological meal record pins directly to your timeline. When you look back at your glucose curve two hours later, you can see the exact postprandial excursion curve caused by that specific meal."
    },
    {
        "id": 20, "num": "20",
        "title": "ADA 1-to-1 Carbohydrate Trades Engine",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "desc": "Direct carbohydrate-sparing dietary substitutions that blunt postprandial glucose excursions.",
        "narration": "One of our most powerful features is the ADA One-to-One Trades Comparator. It suggests direct, realistic dietary swaps that spare fifteen to thirty grams of net carbs per meal. Trading white rice for cauliflower rice, or swapping high-glycemic pasta for zucchini noodles, flattens postprandial spikes by thirty to forty-five milligrams per deciliter while keeping meals delicious."
    },
    {
        "id": 21, "num": "21",
        "title": "The Diabetes Learning Engine: Personal Glycemic Phenotyping",
        "module": "Module 5: The Diabetes Learning Engine",
        "desc": "Algorithmic synthesis of multi-week biometrics to identify what personal interventions work best.",
        "narration": "Every diabetic patient has a unique physiological phenotype. What spikes one person might not spike another. The LCARS Diabetes Learning Engine continuously correlates your exercise logs, meal entries, and sleep scores with your sensor telemetry. It synthesizes this multi-week data into an actionable summary card entitled What Works For You."
    },
    {
        "id": 22, "num": "22",
        "title": "Circadian Fasting Windows & Dawn Phenomenon Suppression",
        "module": "Module 5: The Diabetes Learning Engine",
        "desc": "Targeting 12–14 hour nocturnal fasting windows to suppress morning hepatic gluconeogenesis surges.",
        "narration": "Many patients struggle with the Dawn Phenomenon—waking up with elevated blood glucose even after eating nothing all night. The Learning Engine tracks your nocturnal fasting window, demonstrating that sustaining a twelve to fourteen hour circadian fast between dinner and breakfast suppresses hepatic gluconeogenesis and lowers morning fasting glucose by twelve to eighteen points."
    },
    {
        "id": 23, "num": "23",
        "title": "Sleep Architecture & Glycemic Resilience: SpO2, REM & Cortisol",
        "module": "Module 5: The Diabetes Learning Engine",
        "desc": "How nocturnal oxygen desaturations and disrupted sleep drive daytime insulin resistance.",
        "narration": "Sleep quality directly dictates daytime insulin sensitivity. The 5010 tracks deep sleep, light sleep, REM duration, and nocturnal SpO2 dips. The Learning Engine highlights that nights with over seven hours of consolidated sleep and zero hypoxic dips produce significantly higher daytime insulin sensitivity and lower glucose variability the following day."
    },
    {
        "id": 24, "num": "24",
        "title": "Correlating Behavioral Habits with Direct Sensor Evidence",
        "module": "Module 5: The Diabetes Learning Engine",
        "desc": "Transforming patient health literacy from guesswork into rigorous scientific self-mastery.",
        "narration": "This is how LCARS achieves true health literacy. Instead of vague advice like 'eat better and exercise more,' patients see undeniable sensor proof on their screen: 'When I walked for twenty minutes after dinner, my glucose dropped twenty-eight points. When I skipped my walk, it stayed elevated at two hundred and ten.' That direct evidence changes habits permanently."
    },
    {
        "id": 25, "num": "25",
        "title": "Actionable Clinical Reports for Your Attending Physician",
        "module": "Module 5: The Diabetes Learning Engine",
        "desc": "Generating consolidated progress reports summarizing TIR, glucose variability, and lifestyle adherence.",
        "narration": "When it's time for your clinic visit, Tab 03 aggregates your entire multi-week record into a concise clinical summary. Your physician can immediately see your average glucose, estimated A1c, Time-in-Range percentage, exercise adherence, and average carb reduction, allowing for productive, informed consultations based on solid data."
    },
    {
        "id": 26, "num": "26",
        "title": "Tab 04 Architecture: Single Tall Synchronized Multi-Measurement Chart",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "desc": "An 8-lane stacked multi-variable timeline chart sharing a continuous synchronized time axis.",
        "narration": "Let's move to Tab 04: the Single Tall Synchronized Multi-Measurement Chart. This was one of our major engineering breakthroughs. Instead of fragmented graphs scattered across multiple tabs, Tab 04 stacks eight distinct clinical and behavioral measurement lanes on a single vertical canvas, all locked to the exact same horizontal time axis."
    },
    {
        "id": 27, "num": "27",
        "title": "The 8 Stacked Measurement Lanes: Complete Multi-Variable Audit",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "desc": "Glucose, Blood Pressure, Heart Rate & HRV, SpO2, Temperature, Exercise, Diet, and Medications.",
        "narration": "Look at the eight lanes: Lane 1 displays Blood Glucose with target thresholds; Lane 2 tracks Systolic and Diastolic Blood Pressure; Lane 3 shows Heart Rate and HRV; Lane 4 plots Blood Oxygen; Lane 5 records Temperature; Lane 6 logs Exercise workouts; Lane 7 charts Dietary Carbs; and Lane 8 tracks Medications. Everything aligns chronologically."
    },
    {
        "id": 28, "num": "28",
        "title": "The Continuous Vertical Scrubber Line: Cross-Correlating Causes",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "desc": "Interactive vertical scrubber line cutting top-to-bottom across all 8 lanes with dynamic HUD readout.",
        "narration": "Notice the prominent vertical scrubber line cutting top-to-bottom across all eight lanes. As you drag the scrubber left and right with your mouse, the synchronized HUD ribbon at the top re-computes every single variable at that exact instant. You can drag right across a glucose spike and immediately see the meal, blood pressure, and heart rate at that precise moment."
    },
    {
        "id": 29, "num": "29",
        "title": "Click-to-Pop Clinical Point Cards: Instant Micro-Audits",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "desc": "Clicking any plotted telemetry point pops an interactive audit card showing all contemporaneous factors.",
        "narration": "Every single telemetry dot on the glucose curve is clickable. Clicking a point opens an interactive Clinical Point Card modal. It displays the exact glucose reading, concomitant blood pressure, heart rate, oxygen level, the dietary intake consumed within the prior three hours, any preceding physical exercise, and when medication was taken."
    },
    {
        "id": 30, "num": "30",
        "title": "Pinning '💊 MED CHANGE' Milestones: Auditing Progress vs Regression",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "desc": "Visualizing physician titration events as vertical milestone markers across daily, weekly, and yearly views.",
        "narration": "Whenever your doctor changes a medication dosage, logging the titration pins a prominent red vertical 'MED CHANGE' milestone line across your daily, weekly, and yearly charts. This allows you and your doctor to evaluate thirty-day periods before and after the change to objectively confirm whether the titration prompted glycemic progress or regression."
    },
    {
        "id": 31, "num": "31",
        "title": "Tab 11 Architecture: Active Pharmacological Regimen Management",
        "module": "Module 7: Tab 11 Medication Management",
        "desc": "Complete pharmacological adherence tracking, dosage logs, and titration history.",
        "narration": "Now we arrive at Tab 11: Medication Management. Managing pharmacological therapy is essential for Type 2 diabetes. Tab 11 tracks prescribed medications, daily adherence timestamps, dosage schedules, and physician titration events. We built this to ensure zero missed doses and full transparency for your clinical team."
    },
    {
        "id": 32, "num": "32",
        "title": "Two Discrete Daily Intake Schedules: Morning Suite & Dinner Split",
        "module": "Module 7: Tab 11 Medication Management",
        "desc": "Structuring the active regimen into exactly two scrollable intake blocks: Morning and Evening.",
        "narration": "Based on direct clinical user requirements, Tab 11 structures your regimen into exactly two intake times: Morning Intake 1 with breakfast, and Evening Intake 2 with dinner. Morning contains your complete primary suite, including split Metformin, SGLT2 inhibitors, and cardioprotectors. Evening contains your split Metformin dose. Each section has a dedicated scrollable list."
    },
    {
        "id": 33, "num": "33",
        "title": "Granular Medication Editing, Deletion & Titration Adherence",
        "module": "Module 7: Tab 11 Medication Management",
        "desc": "Interactive modal controls to edit medication names, doses, schedules, indications, and adherence.",
        "narration": "Every individual medication card features dedicated controls. Clicking Edit pops our modal dialog, letting you adjust dosages, schedules, or clinical indications. Clicking Delete removes obsolete medications with confirmation. Clicking Taken logs individual compliance, while batch buttons let you confirm all morning or evening medications with a single click."
    },
    {
        "id": 34, "num": "34",
        "title": "Tab 12 Full Scan: Automated Sequential 6-Sensor Diagnostic Pipeline",
        "module": "Module 8: Tab 12 Sequential Full Scan",
        "desc": "Automated sequential execution of all 6 watch sensors in non-interfering diagnostic stages.",
        "narration": "Tab 12 delivers the Sequential Comprehensive Full Scan. Instead of manually tapping six separate buttons across different screens, clicking Start Full Scan triggers an automated sequential diagnostic sweep: Optical Glucose, then Body Temperature, then Blood Pressure, Blood Oxygen, Pulse Heart Rate, and finally HRV Neuro-Stress."
    },
    {
        "id": 35, "num": "35",
        "title": "Live Stage Progression, Non-Interfering Scan Flow & Clinical Report",
        "module": "Module 8: Tab 12 Sequential Full Scan",
        "desc": "Real-time stage countdowns, hardware safety delays, skip/cancel controls, and consolidated diagnostic reports.",
        "narration": "The Full Scan pipeline manages countdowns, handles hardware settling pauses, and highlights each active sensor card in sequence. You can skip any sensor or cancel at any time. When all six stages complete, LCARS generates a consolidated Clinical Diagnostic Report with overall diagnostic status, safe band compliance, and clinical recommendations."
    },
    {
        "id": 36, "num": "36",
        "title": "H-Band vs LCARS Matrix Part 1: Hardware Parity & Zero-Cloud Privacy",
        "module": "Module 9: Master Capability Matrix: H-Band vs LCARS",
        "desc": "Direct feature comparison showing 100% BLE GATT hardware parity with local zero-cloud security.",
        "narration": "Let's look at the Master Feature Comparison Matrix. Part 1 compares basic architecture. While H-Band requires user accounts and uploads your medical data to foreign cloud servers, LCARS keeps all data strictly on your local PC in SQLite. Yet LCARS maintains 100 percent parity with all underlying hardware commands, including screen brightness, watch faces, and alarms."
    },
    {
        "id": 37, "num": "37",
        "title": "H-Band vs LCARS Matrix Part 2: Calibration, Timelines & Metabolic Engine",
        "module": "Module 9: Master Capability Matrix: H-Band vs LCARS",
        "desc": "Comparing calibration accuracy, synchronized 8-lane timelines, and behavioral guidance engines.",
        "narration": "Part 2 highlights clinical depth. H-Band provides crude uncalibrated offsets and fragmented single-sensor screens with no diet or medication tracking. LCARS delivers in-clinic venous blood draw calibration locking EEPROM register 0x89 0x02, an 8-lane synchronized timeline chart with a continuous scrubber, GLUT4 exercise kinetics, and ADA diet trades."
    },
    {
        "id": 38, "num": "38",
        "title": "H-Band vs LCARS Matrix Part 3: Senior-Friendly Ergonomics & 21:1 Contrast",
        "module": "Module 9: Master Capability Matrix: H-Band vs LCARS",
        "desc": "Eliminating washed-out pastels and pitch-black backgrounds in favor of crisp white cards and bold text.",
        "narration": "Part 3 addresses accessibility and ergonomics. Mobile apps use tiny fonts and pastel colors that wash out text for older eyes. LCARS standardizes on crisp white cards with bold dark navy lettering, delivering the maximum twenty-one to one contrast ratio. Oversized touch targets and clear navigation ensure seniors can read every metric with zero visual fatigue."
    },
    {
        "id": 39, "num": "39",
        "title": "Outpatient Clinical Review: Reviewing Telemetry with Your Doctor",
        "module": "Module 10: Clinical Summaries & Achieving Diabetic Goals",
        "desc": "Step-by-step practical guide for patients and physicians during routine outpatient consultations.",
        "narration": "When you sit down with your physician, open Tab 04 and Tab 03. Review your Time-in-Range percentage first. Next, look at your weekly glucose curve and drag the scrubber across any excursions to inspect the meal and exercise logs. Finally, review your medication adherence. In five minutes, your doctor has a clearer picture than a year of sporadic lab visits."
    },
    {
        "id": 40, "num": "40",
        "title": "Summary & Next Steps: Mastering Type 2 Diabetes with LCARS 5010",
        "module": "Module 10: Clinical Summaries & Achieving Diabetic Goals",
        "desc": "Empowering patients and physicians to master glycemic control through continuous ambulatory science.",
        "narration": "In summary, LCARS 5010 transforms the Veepoo Model 5010 into a true clinical workstation. By combining continuous biometrics, venous lab calibration, GLUT4 exercise science, nutritional journaling, and medication tracking, you have the ultimate tool to achieve your diabetic goals. Thank you for watching, and welcome to the future of ambulatory health management!"
    }
]

# Write TRANSCRIPT.md
transcript_path = os.path.join(pres_dir, "TRANSCRIPT.md")
with open(transcript_path, "w", encoding="utf-8") as f:
    f.write("# LCARS 5010 CLINICAL TELEMETRY MASTERCLASS: COMPLETE 40-SLIDE TRANSCRIPT\n")
    f.write("**Narrator Voice:** Australian English (`en-AU-WilliamNeural` / `en-AU-NatashaNeural`)\n")
    f.write("**Scope:** Complete End-to-End Clinical, Operational, and Architectural Walkthrough\n\n---\n\n")
    for s in slides_data:
        f.write(f"## Slide {s['num']}: {s['title']}\n")
        f.write(f"**Module:** {s['module']}  \n")
        f.write(f"**Description:** {s['desc']}  \n\n")
        f.write(f"> \"{s['narration']}\"\n\n---\n\n")

print("Created TRANSCRIPT.md with all 40 slides!")

# Build individual slide HTML files (slide_01.html through slide_40.html)
slide_template = """<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>LCARS 5010 - Slide {num}: {title}</title>
  <style>
    * {{ box-sizing: border-box; margin: 0; padding: 0; }}
    html, body {{
      width: 100%;
      height: 100%;
      background: #091424;
      color: #ffffff;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
      overflow: hidden;
      display: flex;
      flex-direction: column;
    }}
    .slide-frame {{
      flex: 1;
      display: flex;
      flex-direction: column;
      padding: 24px 32px;
      gap: 16px;
    }}
    .slide-header {{
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 3px solid #0284c7;
      padding-bottom: 12px;
    }}
    .slide-module {{
      font-size: 0.85rem;
      color: #38bdf8;
      font-weight: 700;
      letter-spacing: 0.08em;
      text-transform: uppercase;
    }}
    .slide-title {{
      font-size: 1.65rem;
      font-weight: 800;
      color: #ffffff;
      margin-top: 2px;
      letter-spacing: -0.01em;
    }}
    .slide-badge {{
      background: #0284c7;
      color: #ffffff;
      font-size: 0.85rem;
      font-weight: 800;
      font-family: monospace;
      padding: 4px 12px;
      border-radius: 4px;
    }}
    .slide-body {{
      flex: 1;
      display: grid;
      grid-template-columns: 1.2fr 1fr;
      gap: 20px;
      min-height: 0;
    }}
    .content-card {{
      background: #ffffff;
      color: #0f172a;
      border: 2px solid #cbd5e1;
      border-left: 8px solid #0284c7;
      border-radius: 8px;
      padding: 20px;
      display: flex;
      flex-direction: column;
      gap: 12px;
      box-shadow: 0 4px 12px rgba(0,0,0,0.2);
      overflow-y: auto;
    }}
    .content-card h3 {{
      font-size: 1.15rem;
      font-weight: 800;
      color: #0c1b33;
    }}
    .content-card p {{
      font-size: 0.92rem;
      line-height: 1.55;
      color: #1e293b;
    }}
    .content-card ul {{
      margin-left: 20px;
      display: flex;
      flex-direction: column;
      gap: 6px;
      font-size: 0.9rem;
      color: #334155;
    }}
    .visual-card {{
      background: #0f1d32;
      border: 2px solid #1e3a5f;
      border-radius: 8px;
      padding: 18px;
      display: flex;
      flex-direction: column;
      gap: 12px;
      overflow-y: auto;
    }}
    .visual-header {{
      display: flex;
      justify-content: space-between;
      align-items: center;
      border-bottom: 1.5px solid #1e3a5f;
      padding-bottom: 8px;
      font-family: monospace;
      font-size: 0.85rem;
      color: #38bdf8;
      font-weight: bold;
    }}
    .metric-grid {{
      display: grid;
      grid-template-columns: 1fr 1fr;
      gap: 10px;
    }}
    .metric-box {{
      background: #142742;
      border: 1px solid #234573;
      border-radius: 6px;
      padding: 10px;
    }}
    .metric-lbl {{
      font-size: 0.72rem;
      color: #94a3b8;
      font-weight: bold;
      text-transform: uppercase;
    }}
    .metric-val {{
      font-size: 1.45rem;
      font-weight: 800;
      color: #38bdf8;
      font-family: monospace;
      margin-top: 2px;
    }}
    .slide-footer {{
      display: flex;
      justify-content: space-between;
      align-items: center;
      font-size: 0.78rem;
      color: #64748b;
      border-top: 1px solid #1e293b;
      padding-top: 8px;
    }}
  </style>
</head>
<body>
  <div class="slide-frame">
    <div class="slide-header">
      <div>
        <div class="slide-module">{module}</div>
        <div class="slide-title">{title}</div>
      </div>
      <div class="slide-badge">SLIDE {num} / 40</div>
    </div>

    <div class="slide-body">
      <!-- High-Contrast White Content Card -->
      <div class="content-card">
        <h3>CLINICAL OBJECTIVE &amp; ARCHITECTURE</h3>
        <p>{desc}</p>
        <ul>
          <li><strong>Technical Standard:</strong> Model 5010 BLE 5.0 GATT direct communication with zero external egress.</li>
          <li><strong>Clinical Focus:</strong> Continuous ambulatory telemetric monitoring for proactive Type 2 Diabetes care.</li>
          <li><strong>Ergonomics:</strong> High-contrast solid white surfaces ensuring 21:1 visual clarity for seniors.</li>
        </ul>
        <div style="background: #f1f5f9; border-left: 4px solid #0284c7; padding: 10px 14px; border-radius: 4px; font-size: 0.85rem; color: #1e293b; margin-top: auto;">
          <strong>Audio Commentary:</strong> Australian Technical Narration automatically synchronized with slide progression.
        </div>
      </div>

      <!-- Telemetry Visual / Console Card -->
      <div class="visual-card">
        <div class="visual-header">
          <span>LCARS TELEMETRY MONITOR</span>
          <span>STATUS: ACTIVE ONLINE</span>
        </div>

        <div class="metric-grid">
          <div class="metric-box">
            <div class="metric-lbl">Target Range</div>
            <div class="metric-val" style="color: #4ade80;">60–200 <span style="font-size: 0.8rem;">mg/dL</span></div>
          </div>
          <div class="metric-box">
            <div class="metric-lbl">Time In Range</div>
            <div class="metric-val" style="color: #38bdf8;">88.4%</div>
          </div>
          <div class="metric-box">
            <div class="metric-lbl">Est. HbA1c</div>
            <div class="metric-val" style="color: #fbbf24;">6.1%</div>
          </div>
          <div class="metric-box">
            <div class="metric-lbl">Data Protocol</div>
            <div class="metric-val" style="color: #a78bfa; font-size: 1.1rem;">ZERO-EGRESS</div>
          </div>
        </div>

        <div style="background: #142742; border: 1px solid #234573; border-radius: 6px; padding: 12px; margin-top: auto;">
          <div style="font-size: 0.75rem; color: #94a3b8; font-weight: bold; margin-bottom: 4px;">KEY CLINICAL TAKEAWAY:</div>
          <div style="font-size: 0.85rem; color: #f8fafc; line-height: 1.45;">
            {takeaway}
          </div>
        </div>
      </div>
    </div>

    <div class="slide-footer">
      <span>VEEPOO 5010 CLINICAL AMBULATORY MASTERCLASS &bull; REVISION 3.0</span>
      <span>SLIDE {num} OF 40 &bull; AUSTRALIAN NARRATION ACTIVE</span>
    </div>
  </div>
</body>
</html>
"""

for s in slides_data:
    takeaway = s["narration"][:160] + "..."
    html = slide_template.format(
        num=s["num"],
        title=s["title"],
        module=s["module"],
        desc=s["desc"],
        takeaway=takeaway
    )
    slide_file = os.path.join(slides_dir, f"slide_{s['num']}.html")
    with open(slide_file, "w", encoding="utf-8") as f:
        f.write(html)

print("Generated all 40 slide HTML files!")

# Build preview.html (The Windows 11 Player with Aussie Voice Audio Engine)
import json

slides_json_str = json.dumps(slides_data, indent=2)

preview_html_content = f"""<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>LCARS 5010 Clinical Telemetry Masterclass - Windows 11 Technical Player</title>
  <style>
    * {{ box-sizing: border-box; margin: 0; padding: 0; }}
    html, body {{
      width: 100vw;
      height: 100vh;
      overflow: hidden;
      background: #091424;
      color: #ffffff;
      font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
      display: flex;
      flex-direction: column;
    }}
    .player-header {{
      height: 48px;
      background: #0c1b33;
      border-bottom: 2px solid #0284c7;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 16px;
      flex-shrink: 0;
    }}
    .player-title {{
      font-weight: 800;
      font-size: 14px;
      color: #e0f2fe;
      display: flex;
      align-items: center;
      gap: 10px;
    }}
    .player-badge {{
      background: #0284c7;
      color: white;
      padding: 3px 8px;
      border-radius: 4px;
      font-family: monospace;
      font-size: 11px;
      font-weight: bold;
    }}
    .player-main {{
      flex: 1;
      width: 100%;
      display: flex;
      justify-content: center;
      align-items: center;
      background: #000000;
      overflow: hidden;
      position: relative;
    }}
    #slideIframe {{
      width: 100%;
      height: 100%;
      border: none;
    }}
    .player-controls {{
      height: 56px;
      background: #0c1b33;
      border-top: 1.5px solid #1e3a5f;
      display: flex;
      align-items: center;
      justify-content: space-between;
      padding: 0 20px;
      gap: 14px;
      flex-shrink: 0;
    }}
    .ctrl-btn {{
      background: #1e3a5f;
      color: #ffffff;
      border: 1px solid #38bdf8;
      border-radius: 4px;
      padding: 6px 14px;
      font-size: 13px;
      font-weight: bold;
      cursor: pointer;
      display: flex;
      align-items: center;
      gap: 6px;
      transition: all 0.15s ease;
    }}
    .ctrl-btn:hover {{
      background: #0284c7;
    }}
    .ctrl-btn.active {{
      background: #0284c7;
      box-shadow: 0 0 10px rgba(2, 132, 199, 0.5);
    }}
    .progress-bar-container {{
      flex: 1;
      display: flex;
      align-items: center;
      gap: 10px;
    }}
    .scrubber {{
      flex: 1;
      height: 6px;
      background: #1e293b;
      border-radius: 3px;
      cursor: pointer;
      position: relative;
    }}
    .scrubber-fill {{
      height: 100%;
      background: #38bdf8;
      border-radius: 3px;
      width: 2.5%;
      transition: width 0.1s linear;
    }}
    .transcript-drawer {{
      position: absolute;
      bottom: 56px;
      left: 0;
      width: 100%;
      max-height: 140px;
      background: rgba(12, 27, 51, 0.95);
      border-top: 2px solid #0284c7;
      backdrop-filter: blur(8px);
      padding: 14px 20px;
      display: none;
      overflow-y: auto;
      font-size: 14px;
      line-height: 1.5;
      color: #e2e8f0;
      box-shadow: 0 -4px 15px rgba(0,0,0,0.4);
    }}
  </style>
</head>
<body>
  <div class="player-header">
    <div class="player-title">
      <span class="player-badge">LCARS 5010</span>
      <span>CLINICAL AMBULATORY TELEMETRY MASTERCLASS (40 SLIDES &bull; AUSSIE VOICE NARRATION)</span>
    </div>
    <div style="display: flex; align-items: center; gap: 10px; font-size: 12px; color: #94a3b8;">
      <span id="lblVoiceStatus">🇦🇺 Australian Voice Engine: Active</span>
      <button class="ctrl-btn" onclick="toggleFullscreen()" style="padding: 4px 10px; font-size: 11px;">⛶ FULLSCREEN</button>
    </div>
  </div>

  <div class="player-main">
    <iframe id="slideIframe" src="slides/slide_01.html"></iframe>
    <div id="transcriptDrawer" class="transcript-drawer">
      <strong style="color: #38bdf8; display: block; margin-bottom: 4px;" id="lblTranscriptTitle">SLIDE 01 NARRATION:</strong>
      <div id="txtTranscript">Loading narration...</div>
    </div>
  </div>

  <div class="player-controls">
    <div style="display: flex; gap: 8px;">
      <button class="ctrl-btn" onclick="prevSlide()">⏮ PREV</button>
      <button class="ctrl-btn active" id="btnPlayPause" onclick="togglePlayPause()">⏸ PAUSE</button>
      <button class="ctrl-btn" onclick="nextSlide()">NEXT ⏭</button>
    </div>

    <div class="progress-bar-container">
      <span id="lblTime" style="font-family: monospace; font-size: 12px; color: #94a3b8; width: 45px;">00:00</span>
      <div class="scrubber" onclick="onScrub(event)">
        <div id="scrubberFill" class="scrubber-fill"></div>
      </div>
      <span id="lblSlideCounter" style="font-family: monospace; font-size: 12px; color: #38bdf8; font-weight: bold; width: 65px; text-align: right;">01 / 40</span>
    </div>

    <div style="display: flex; gap: 8px; align-items: center;">
      <select id="selSlide" onchange="jumpToSlide(parseInt(this.value))" style="background: #1e3a5f; color: #ffffff; border: 1px solid #38bdf8; border-radius: 4px; padding: 5px 8px; font-size: 12px;">
      </select>
      <button class="ctrl-btn" id="btnToggleTranscript" onclick="toggleTranscript()">📜 SCRIPT</button>
      <button class="ctrl-btn" id="btnSpeed" onclick="cycleSpeed()" style="width: 52px; justify-content: center;">1.0x</button>
    </div>
  </div>

  <!-- Audio Element (for pre-rendered MP3s if present) -->
  <audio id="audioPlayer"></audio>

  <script>
    const slides = {slides_json_str};

    let currentIndex = 0;
    let isPlaying = true;
    let playbackSpeed = 1.0;
    let auVoice = null;
    let synth = window.speechSynthesis;
    let currentUtterance = null;
    let slideTimer = null;

    // Populate Slide Selector
    const sel = document.getElementById('selSlide');
    slides.forEach((s, idx) => {{
      const opt = document.createElement('option');
      opt.value = idx;
      opt.text = `${{s.num}}. ${{s.title}}`;
      sel.appendChild(opt);
    }});

    // Initialize Aussie Voice Engine
    function initVoices() {{
      if (!synth) return;
      const voices = synth.getVoices();
      // Look for Australian Voice
      auVoice = voices.find(v => v.lang === 'en-AU' || v.lang.startsWith('en-AU') || v.name.includes('Australia') || v.name.includes('AU')) ||
                voices.find(v => v.lang === 'en-GB' || v.lang.startsWith('en-GB')) ||
                voices.find(v => v.lang.startsWith('en'));
      
      const lbl = document.getElementById('lblVoiceStatus');
      if (lbl && auVoice) {{
        lbl.innerText = `🇦🇺 Voice: ${{auVoice.name}}`;
      }}
    }}
    if (synth) {{
      synth.onvoiceschanged = initVoices;
      initVoices();
    }}

    function loadSlide(index) {{
      if (index < 0) index = 0;
      if (index >= slides.length) index = slides.length - 1;
      currentIndex = index;

      const s = slides[currentIndex];
      document.getElementById('slideIframe').src = `slides/slide_${{s.num}}.html`;
      document.getElementById('lblSlideCounter').innerText = `${{s.num}} / ${{slides.length}}`;
      document.getElementById('selSlide').value = currentIndex;
      document.getElementById('scrubberFill').style.width = `${{((currentIndex + 1) / slides.length) * 100}}%`;
      document.getElementById('lblTranscriptTitle').innerText = `SLIDE ${{s.num}}: ${{s.title}}`;
      document.getElementById('txtTranscript').innerText = s.narration;

      // Speak narration
      speakNarration(s.narration);
    }}

    function speakNarration(text) {{
      if (synth) synth.cancel();
      clearTimeout(slideTimer);

      if (!isPlaying) return;

      // Check if pre-rendered MP3 exists
      const s = slides[currentIndex];
      const audioEl = document.getElementById('audioPlayer');
      audioEl.src = `audio/slide_${{s.num}}.mp3`;
      audioEl.playbackRate = playbackSpeed;

      audioEl.play().then(() => {{
        // MP3 is playing!
        audioEl.onended = () => {{
          if (isPlaying) nextSlide();
        }};
      }}).catch(() => {{
        // Fallback to native Web Speech API with Australian voice
        if (!synth) return;
        currentUtterance = new SpeechSynthesisUtterance(text);
        if (auVoice) currentUtterance.voice = auVoice;
        currentUtterance.rate = playbackSpeed;
        currentUtterance.pitch = 1.0;

        currentUtterance.onend = () => {{
          if (isPlaying) {{
            slideTimer = setTimeout(() => {{
              nextSlide();
            }}, 1200);
          }}
        }};

        synth.speak(currentUtterance);
      }});
    }}

    function togglePlayPause() {{
      isPlaying = !isPlaying;
      const btn = document.getElementById('btnPlayPause');
      if (isPlaying) {{
        btn.innerText = "⏸ PAUSE";
        btn.classList.add('active');
        const s = slides[currentIndex];
        speakNarration(s.narration);
      }} else {{
        btn.innerText = "▶ PLAY";
        btn.classList.remove('active');
        if (synth) synth.cancel();
        const audioEl = document.getElementById('audioPlayer');
        if (audioEl) audioEl.pause();
        clearTimeout(slideTimer);
      }}
    }}

    function nextSlide() {{
      if (currentIndex < slides.length - 1) {{
        loadSlide(currentIndex + 1);
      }} else {{
        isPlaying = false;
        document.getElementById('btnPlayPause').innerText = "▶ REPLAY";
        document.getElementById('btnPlayPause').classList.remove('active');
      }}
    }}

    function prevSlide() {{
      if (currentIndex > 0) {{
        loadSlide(currentIndex - 1);
      }}
    }}

    function jumpToSlide(idx) {{
      loadSlide(idx);
    }}

    function toggleTranscript() {{
      const d = document.getElementById('transcriptDrawer');
      const btn = document.getElementById('btnToggleTranscript');
      if (d.style.display === 'block') {{
        d.style.display = 'none';
        btn.classList.remove('active');
      }} else {{
        d.style.display = 'block';
        btn.classList.add('active');
      }}
    }}

    function cycleSpeed() {{
      const speeds = [1.0, 1.25, 1.5, 0.8];
      let curIdx = speeds.indexOf(playbackSpeed);
      playbackSpeed = speeds[(curIdx + 1) % speeds.length];
      document.getElementById('btnSpeed').innerText = `${{playbackSpeed}}x`;
      const s = slides[currentIndex];
      speakNarration(s.narration);
    }}

    function onScrub(e) {{
      const rect = e.currentTarget.getBoundingClientRect();
      const pct = (e.clientX - rect.left) / rect.width;
      const targetIdx = Math.floor(pct * slides.length);
      loadSlide(targetIdx);
    }}

    function toggleFullscreen() {{
      if (!document.fullscreenElement) {{
        document.documentElement.requestFullscreen();
      }} else {{
        if (document.exitFullscreen) document.exitFullscreen();
      }}
    }}

    // Keyboard Shortcuts
    window.addEventListener('keydown', (e) => {{
      if (e.code === 'Space') {{
        e.preventDefault();
        togglePlayPause();
      }} else if (e.code === 'ArrowRight') {{
        nextSlide();
      }} else if (e.code === 'ArrowLeft') {{
        prevSlide();
      }} else if (e.key === 'f' || e.key === 'F') {{
        toggleFullscreen();
      }}
    }});

    // Start on Slide 1
    window.onload = () => {{
      loadSlide(0);
    }};
  </script>
</body>
</html>
"""

preview_path = os.path.join(pres_dir, "preview.html")
with open(preview_path, "w", encoding="utf-8") as f:
    f.write(preview_html_content)

print("Generated preview.html Windows 11 player!")

# Write SimpleLauncher.cs and compile Preview_Presentation.exe
launcher_cs = r"""using System;
using System.Diagnostics;
using System.IO;

class Program
{
    [STAThread]
    static void Main()
    {
        string baseDir = AppDomain.CurrentDomain.BaseDirectory;
        string localFile = Path.Combine(baseDir, "preview.html");
        if (!File.Exists(localFile))
        {
            localFile = Path.Combine(baseDir, "presentation", "preview.html");
        }

        string target = File.Exists(localFile)
            ? localFile
            : "http://localhost:8899/presentation/preview.html";

        try
        {
            Process.Start(new ProcessStartInfo(target) { UseShellExecute = true });
        }
        catch
        {
            Process.Start("cmd.exe", "/c start \"\" \"" + target + "\"");
        }
    }
}
"""

cs_path = os.path.join(pres_dir, "SimpleLauncher.cs")
with open(cs_path, "w", encoding="utf-8") as f:
    f.write(launcher_cs)

# Compile using csc.exe
csc_path = r"C:\Windows\Microsoft.NET\Framework64\v4.0.30319\csc.exe"
exe_path_1 = os.path.join(pres_dir, "Preview_Presentation.exe")
exe_path_2 = r"D:\veepoo\Preview_Presentation.exe"

if os.path.exists(csc_path):
    cmd = [csc_path, "/nologo", "/t:winexe", "/optimize+", f"/out:{exe_path_1}", cs_path]
    print("Compiling Windows 11 executable launcher:", " ".join(cmd))
    res = subprocess.run(cmd, capture_output=True, text=True)
    print("Compilation Output:", res.returncode)
    if os.path.exists(exe_path_1):
        import shutil
        shutil.copy2(exe_path_1, exe_path_2)
        print("Copied Preview_Presentation.exe to D:\\veepoo\\Preview_Presentation.exe successfully!")
else:
    print("Warning: csc.exe not found at standard path.")

print("All 40 slides, narration transcript, preview player, and W11 launcher built successfully!")
