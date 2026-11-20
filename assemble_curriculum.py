import os
import subprocess
import json

# Generate complete 40 slides
slides_data = []

# Import part 1 and part 2
from build_presentation_clean import curriculum as part1
from build_presentation_part2 import part2_slides as part2

slides_data.extend(part1)
slides_data.extend(part2)

# Define slides 11 to 40
remaining = [
    # MODULE 3: CORE BIOSENSOR ARRAY OPERATIONS (11-15)
    {
        "id": 11, "num": "11",
        "module": "Module 3: Core Biosensor Array Operations",
        "title": "01-VITALS Bay 1: Optical Glucose Absorption Spectroscopy",
        "desc": "How continuous optical tonometry calculates dynamic blood glucose and projected eA1c.",
        "narration": "In Module 3, we tour the individual biometric bays. Bay BIO-01 is optical glucose tonometry. By tracking multi-wavelength light absorption across superficial capillary beds, LCARS measures glucose fluctuations in real time and calculates projected A1C using the standard ADAG consensus algorithm.",
        "card_title": "BAY BIO-01: OPTICAL GLUCOSE TONOMETRY",
        "bullets": [
            ("Optical PPG Absorption", "Tracks changes in tissue light absorption correlated with circulating blood sugar."),
            ("Real-Time Glucose Readout", "Displays immediate glucose levels with clear high-contrast senior-friendly numerals."),
            ("Mathematical eA1C Projection", "Computes longitudinal average glucose and projected A1C on every reading.")
        ],
        "takeaway": "Replaces painful finger pricks with continuous non-invasive optical glucose tracking.",
        "right_header": "BAY BIO-01: GLUCOSE TONOMETRY",
        "right_badge": "LIVE SENSOR BAY",
        "right_badge_col": "#dc2626",
        "right_html": """
          <div style="display:flex; align-items:baseline; gap:8px; margin-top:8px;">
            <div style="font-size:2.8rem; font-weight:900; color:#dc2626; font-family:monospace; line-height:1;">182</div>
            <div style="font-size:1rem; font-weight:800; color:#64748b;">MG/DL</div>
          </div>
          <div style="margin-top:10px; display:grid; grid-template-columns:1fr 1fr; gap:8px;">
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Estimated A1C: 8.0%</div>
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Target: 60 - 200 mg/dL</div>
          </div>
          <div style="margin-top:10px; font-size:0.75rem; color:#64748b; font-family:monospace; border-top:1px solid #e2e8f0; padding-top:6px;">
            Status: Optimal Glycemic Stability • ADAG Formula Validated
          </div>
        """
    },
    {
        "id": 12, "num": "12",
        "module": "Module 3: Core Biosensor Array Operations",
        "title": "01-VITALS Bay 2: Dual-Matrix Thermometry",
        "desc": "How continuous dual thermistors differentiate core circadian temperature from cutaneous skin temperature.",
        "narration": "Bay THERMO-02 monitors dual-matrix body temperature. Instead of a single uncalibrated reading, the Model 5010 uses dual thermistors to separate deep core temperature from peripheral skin temperature, revealing your natural circadian nadir and daytime metabolic rhythm.",
        "card_title": "BAY THERMO-02: DUAL MATRIX THERMOMETRY",
        "bullets": [
            ("Dual Core & Skin Sensors", "Simultaneously measures internal core temperature and cutaneous skin temperature."),
            ("Circadian Nadir Identification", "Captures the overnight 4:30 AM nadir, indicating healthy autonomic function."),
            ("Metabolic Tone Tracking", "Monitors temperature rise during post-meal thermogenesis and exercise.")
        ],
        "takeaway": "Captures circadian thermal cycles without flashing bright LEDs in a dark bedroom.",
        "right_header": "BAY THERMO-02: DUAL TEMPERATURE",
        "right_badge": "DUAL THERMISTORS",
        "right_badge_col": "#0284c7",
        "right_html": """
          <div style="display:flex; align-items:baseline; gap:8px; margin-top:8px;">
            <div style="font-size:2.8rem; font-weight:900; color:#0284c7; font-family:monospace; line-height:1;">97.2°</div>
            <div style="font-size:1rem; font-weight:800; color:#64748b;">CORE °F</div>
          </div>
          <div style="margin-top:10px; display:grid; grid-template-columns:1fr 1fr; gap:8px;">
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Skin Temp: 92.4°F</div>
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Delta: 4.8°F Offset</div>
          </div>
          <div style="margin-top:10px; font-size:0.75rem; color:#64748b; font-family:monospace; border-top:1px solid #e2e8f0; padding-top:6px;">
            Circadian Assessment: Cold-Blooded Baseline Nominal
          </div>
        """
    },
    {
        "id": 13, "num": "13",
        "module": "Module 3: Core Biosensor Array Operations",
        "title": "02-CARDIO Bay 3: Pulse Wave Transit Time Blood Pressure",
        "desc": "Non-invasive ambulatory blood pressure estimation via arterial pulse wave velocity.",
        "narration": "Bay CARDIO-01 tracks blood pressure using Pulse Wave Transit Time. By measuring how fast arterial pressure waves travel through your blood vessels, the system estimates systolic and diastolic trends throughout the day and verifies healthy nocturnal blood pressure dipping.",
        "card_title": "BAY CARDIO-01: PWTT BLOOD PRESSURE",
        "bullets": [
            ("Cuffless Ambulatory Tracking", "Estimates blood pressure from arterial pulse wave velocity without uncomfortable cuff inflation."),
            ("Nocturnal Dipping Verification", "Checks that blood pressure drops by 10 to 20 percent overnight during restful sleep."),
            ("Cardiovascular Risk Reduction", "Alerts patient and physician to hypertensive spikes or non-dipping patterns.")
        ],
        "takeaway": "Continuous cuffless blood pressure monitoring that captures 24-hour cardiovascular dipping.",
        "right_header": "BAY CARDIO-01: BLOOD PRESSURE",
        "right_badge": "AMBULATORY PWTT",
        "right_badge_col": "#d97706",
        "right_html": """
          <div style="display:flex; align-items:baseline; gap:8px; margin-top:8px;">
            <div style="font-size:2.8rem; font-weight:900; color:#d97706; font-family:monospace; line-height:1;">118 / 76</div>
            <div style="font-size:1rem; font-weight:800; color:#64748b;">MMHG</div>
          </div>
          <div style="margin-top:10px; display:grid; grid-template-columns:1fr 1fr; gap:8px;">
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Mean Arterial: 90 mmHg</div>
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Status: Normative Dipping</div>
          </div>
          <div style="margin-top:10px; font-size:0.75rem; color:#64748b; font-family:monospace; border-top:1px solid #e2e8f0; padding-top:6px;">
            Method: Pulse Wave Transit Time Oscillometry
          </div>
        """
    },
    {
        "id": 14, "num": "14",
        "module": "Module 3: Core Biosensor Array Operations",
        "title": "02-CARDIO Bay 4: Dual-Wavelength SpO2 & Apnea Screening",
        "desc": "Continuous nocturnal pulse oximetry detecting desaturations and obstructive sleep apnea risk.",
        "narration": "Bay PULMO-02 provides dual-wavelength blood oxygen monitoring. Utilizing 660 nanometer red and 940 nanometer infrared sensors, it monitors arterial oxygen and sleep apnea desaturations. Because sleep apnea directly worsens insulin resistance, this safeguard is vital for diabetic health.",
        "card_title": "BAY PULMO-02: SPO2 & APNEA HYPOXIA SAFEGUARD",
        "bullets": [
            ("Overnight Apnea Detection", "Detects oxygen drops below 90% and 85% to screen for obstructive sleep apnea."),
            ("Insulin Resistance Protection", "Treating sleep apnea significantly improves morning fasting blood sugar."),
            ("Dual Red & Infrared Optical Sensing", "Maintains accuracy through the night without flashing visible green lights.")
        ],
        "takeaway": "Protects against silent overnight hypoxia that destabilizes insulin sensitivity.",
        "right_header": "BAY PULMO-02: ARTERIAL SPO2",
        "right_badge": "APNEA SAFEGUARD",
        "right_badge_col": "#15803d",
        "right_html": """
          <div style="display:flex; align-items:baseline; gap:8px; margin-top:8px;">
            <div style="font-size:2.8rem; font-weight:900; color:#15803d; font-family:monospace; line-height:1;">98%</div>
            <div style="font-size:1rem; font-weight:800; color:#64748b;">SATURATION</div>
          </div>
          <div style="margin-top:10px; display:grid; grid-template-columns:1fr 1fr; gap:8px;">
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Respiration: 16 RPM</div>
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Apnea Index: Normal</div>
          </div>
          <div style="margin-top:10px; font-size:0.75rem; color:#64748b; font-family:monospace; border-top:1px solid #e2e8f0; padding-top:6px;">
            Sensors: 660nm Red / 940nm Infrared Continuous Reflectance
          </div>
        """
    },
    {
        "id": 15, "num": "15",
        "module": "Module 3: Core Biosensor Array Operations",
        "title": "02-CARDIO Bay 5: Autonomic HRV & Sympatho-Vagal Stress",
        "desc": "Tracking Heart Rate Variability (HRV SDNN) to evaluate autonomic recovery and chronic stress load.",
        "narration": "Bay NEURO-02 assesses autonomic nervous system recovery via Heart Rate Variability. High HRV indicates robust vagal nerve tone and resilience, whereas suppressed HRV reflects systemic stress and sympathetic overdrive, which raises blood sugar by triggering cortisol release.",
        "card_title": "BAY NEURO-02: AUTONOMIC HRV SDNN RECOVERY",
        "bullets": [
            ("Vagal Tone Assessment", "Measures millisecond variations between heartbeats to assess parasympathetic recovery."),
            ("Stress-Induced Glycemia", "Chronic sympathetic stress increases hepatic glucose output via cortisol and epinephrine."),
            ("Recovery Guidance", "Guides optimal timing for exercise, restorative sleep, and relaxation.")
        ],
        "takeaway": "Provides an objective window into the nervous system balance governing metabolic stress.",
        "right_header": "BAY NEURO-02: AUTONOMIC HRV",
        "right_badge": "AUTONOMIC TONE",
        "right_badge_col": "#4f46e5",
        "right_html": """
          <div style="display:flex; align-items:baseline; gap:8px; margin-top:8px;">
            <div style="font-size:2.8rem; font-weight:900; color:#4f46e5; font-family:monospace; line-height:1;">64</div>
            <div style="font-size:1rem; font-weight:800; color:#64748b;">MS SDNN</div>
          </div>
          <div style="margin-top:10px; display:grid; grid-template-columns:1fr 1fr; gap:8px;">
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Stress Score: Low (22)</div>
            <div style="background:#f8fafc; padding:6px 10px; border-radius:4px; border:1px solid #e2e8f0; font-size:0.8rem; font-weight:bold; color:#0f172a;">Tone: Vagal Dominance</div>
          </div>
          <div style="margin-top:10px; font-size:0.75rem; color:#64748b; font-family:monospace; border-top:1px solid #e2e8f0; padding-top:6px;">
            Status: Autonomic Recovery Optimal • Rest & Repair Active
          </div>
        """
    },

    # MODULE 4: TAB 03 METABOLIC CONSOLE & BEHAVIORAL SCIENCE (16-20)
    {
        "id": 16, "num": "16",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "title": "03-METABOLIC Console: Real-Time Physiological Telemetry",
        "desc": "Synthesizing multi-sensor biometric streams into a single live behavioral console.",
        "narration": "Now we step into Tab 03: the METABOLIC Console. Here, all primary physiological telemetry streams converge. You can observe your real-time glucose, heart rate, skin and body temperature, and physical movement simultaneously, helping you immediately understand how daily actions impact your physiology.",
        "card_title": "TAB 03: 360° REAL-TIME METABOLIC CONSOLE",
        "bullets": [
            ("Unified Biometric Overview", "Simultaneously displays glucose, heart rate, dual temperatures, and step activity."),
            ("Real-Time Cause-and-Effect", "See immediately how a meal or walk shifts multiple biomarkers at once."),
            ("High-Contrast Senior Readability", "Solid white surfaces with bold colored typography ensuring instant clarity.")
        ],
        "takeaway": "A single comprehensive cockpit that reveals the immediate impact of daily habits on blood sugar.",
        "right_header": "TAB 03: METABOLIC CONSOLE",
        "right_badge": "360° LIVE SYNTHESIS",
        "right_badge_col": "#ca8a04",
        "right_html": """
          <div style="margin-top:10px; background:#fefce8; border:1.5px solid #fef08a; border-radius:6px; padding:12px;">
            <div style="font-size:1.15rem; font-weight:800; color:#854d0e; font-family:monospace;">GLUCOSE 182 • HR 68 • TEMP 97.2° • STEPS 2,384</div>
            <div style="font-size:0.84rem; color:#713f12; margin-top:6px; line-height:1.45;">Real-time unified synthesis correlating nutritional intake, exercise movement, and continuous physiological response.</div>
          </div>
          <div style="margin-top:10px; display:grid; grid-template-columns:1fr 1fr; gap:6px; font-family:monospace; font-size:0.76rem;">
            <div style="background:#f8fafc; padding:6px; border-radius:3px; border:1px solid #e2e8f0;">POST-MEAL TARGET: &lt;180</div>
            <div style="background:#f8fafc; padding:6px; border-radius:3px; border:1px solid #e2e8f0;">GLUT4 EFFECT: -20 TO -40</div>
          </div>
        """
    },
    {
        "id": 17, "num": "17",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "title": "GLUT4 Translocation Kinetics: How Exercise Lowers Glucose",
        "desc": "The biochemistry of non-insulin-mediated glucose uptake triggered by skeletal muscle contraction.",
        "narration": "Slide 17 covers one of the most powerful tools in diabetes maintenance: GLUT4 translocation. When you contract large skeletal muscles—such as your quadriceps during a brisk walk—your muscles pull glucose directly out of your bloodstream without requiring insulin. This gives you a natural, controllable drug-free mechanism to blunt postprandial spikes.",
        "card_title": "GLUT4 TRANSLOCATION MECHANISM & KINETICS",
        "bullets": [
            ("Non-Insulin Glucose Clearance", "Skeletal muscle contractions open cellular glucose channels directly without insulin."),
            ("Overcoming Insulin Resistance", "Bypasses defective insulin signaling pathways common in Type 2 Diabetes."),
            ("Post-Meal Timing", "A light 15 to 30-minute walk within 45 minutes of a meal produces the maximum glucose drop.")
        ],
        "takeaway": "Harnessing muscle contractions as a natural, drug-free tool to lower post-meal blood sugar.",
        "right_header": "GLUT4 TRANSLOCATION ENGINE",
        "right_badge": "NON-INSULIN CLEARANCE",
        "right_badge_col": "#ca8a04",
        "right_html": """
          <div style="margin-top:10px; background:#fefce8; border:1.5px solid #fef08a; border-radius:6px; padding:12px;">
            <div style="font-size:1.15rem; font-weight:800; color:#854d0e; font-family:monospace;">MUSCLE CONTRACTION -> DIRECT UPTAKE</div>
            <div style="font-size:0.84rem; color:#713f12; margin-top:6px; line-height:1.45;">Mechanical stimulation triggers GLUT4 glucose transporter translocation to the muscle cell membrane, dropping blood sugar without pancreatic insulin.</div>
          </div>
        """
    },
    {
        "id": 18, "num": "18",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "title": "The GLUT4 Exercise Journal: Calculating Direct ΔG Glucose Drops",
        "desc": "Logging exercise bouts and quantifying the exact milligram-per-deciliter drop produced by walking.",
        "narration": "In Slide 18, we introduce the GLUT4 Exercise Journal. When you finish a walk, LCARS compares your pre-exercise glucose to your post-exercise nadir, calculating your exact delta G drop. For example, a 30-minute morning walk might drop your glucose by 27 milligrams per deciliter, proving your exercise is working.",
        "card_title": "EXERCISE JOURNAL: DIRECT ΔG GLUCOSE DROPS",
        "bullets": [
            ("Quantified Glucose Drop", "Calculates the exact pre-walk to post-walk delta G reduction (e.g. -27 mg/dL)."),
            ("Personalized Exercise Dose", "Determines how many steps or minutes you need to clear specific carbohydrate meals."),
            ("Empirical Positive Feedback", "Encourages daily activity by showing you immediate, verified clinical results.")
        ],
        "takeaway": "Proves the exact clinical power of your daily walks in verified milligrams per deciliter.",
        "right_header": "GLUT4 EXERCISE JOURNAL",
        "right_badge": "MEASURED ΔG DROPS",
        "right_badge_col": "#15803d",
        "right_html": """
          <div style="margin-top:10px; background:#f0fdf4; border:1.5px solid #bbf7d0; border-radius:6px; padding:12px;">
            <div style="font-size:1.15rem; font-weight:800; color:#14532d; font-family:monospace;">MORNING WALK: -27 MG/DL DROP</div>
            <div style="font-size:0.84rem; color:#166534; margin-top:6px; line-height:1.45;">32-minute walk at 08:30 (2,384 steps) reduced glucose from 192 mg/dL to 165 mg/dL. GLUT4 clearance verified.</div>
          </div>
        """
    },
    {
        "id": 19, "num": "19",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "title": "Macronutrient Meal Logger: Net Carbs, Protein, Fat & Calories",
        "desc": "Tracking nutritional intake and correlating specific meals to postprandial glycemic excursions.",
        "narration": "Slide 19 covers the Macronutrient Meal Logger. Logging meals allows LCARS to overlay dietary carbohydrates, protein, and fat directly on your glucose timeline. You can see precisely how different foods affect your peak height and latency, teaching you which meals spike your sugar and which keep you stable.",
        "card_title": "MACRONUTRIENT MEAL & CARBOHYDRATE LOGGER",
        "bullets": [
            ("Net Carbohydrate Tracking", "Focuses on net impact carbohydrates while accounting for beneficial dietary fiber."),
            ("Meal-to-Spike Correlation", "Overlays meal times directly on the 24-hour glucose timeline to inspect peak heights."),
            ("Fat & Protein Buffering", "Demonstrates how healthy proteins and fats slow down carbohydrate absorption.")
        ],
        "takeaway": "Connects what is on your plate directly to your resulting 24-hour glucose curve.",
        "right_header": "MACRONUTRIENT MEAL LOGGER",
        "right_badge": "CARBOHYDRATE EXCURSIONS",
        "right_badge_col": "#ca8a04",
        "right_html": """
          <div style="margin-top:10px; background:#fefce8; border:1.5px solid #fef08a; border-radius:6px; padding:12px;">
            <div style="font-size:1.15rem; font-weight:800; color:#854d0e; font-family:monospace;">LUNCH: 34g NET CARBS • +44 MG/DL SPIKE</div>
            <div style="font-size:0.84rem; color:#713f12; margin-top:6px; line-height:1.45;">Lunch at 12:30 produced a 44 mg/dL excursion, peaking at 232 mg/dL at 13:30 before settling.</div>
          </div>
        """
    },
    {
        "id": 20, "num": "20",
        "module": "Module 4: Tab 03 METABOLIC Console & Behavioral Science",
        "title": "ADA 1-to-1 Carbohydrate Trades Engine",
        "desc": "Empowering patients with clinically endorsed dietary substitutions to blunt post-meal spikes.",
        "narration": "In Slide 20, we examine the ADA 1-to-1 Carbohydrate Trades Engine. Rather than imposing restrictive diets, LCARS recommends smart one-to-one swaps—like substituting rolled oats for refined white bread, or cauliflower for potatoes. These trades cut spike velocity while maintaining enjoyable meals.",
        "card_title": "ADA 1-TO-1 CARBOHYDRATE TRADES ENGINE",
        "bullets": [
            ("Smart 1-to-1 Substitutions", "Swap high-glycemic refined starches for fiber-rich complex whole food alternatives."),
            ("Preserving Food Enjoyment", "Avoids demoralizing dietary deprivation while achieving superior metabolic control."),
            ("Proven Glycemic Savings", "Simple trades routinely reduce postprandial peak spikes by 30 to 50 mg/dL.")
        ],
        "takeaway": "Smart dietary trades that slash post-meal spikes without sacrificing lifestyle enjoyment.",
        "right_header": "ADA CARBOHYDRATE TRADES",
        "right_badge": "1-TO-1 DIETARY EXCHANGES",
        "right_badge_col": "#059669",
        "right_html": """
          <div style="margin-top:10px; background:#ecfdf5; border:1.5px solid #a7f3d0; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#065f46; font-family:monospace;">WHITE BREAD -> ROLLED OATS (-15g NET)</div>
            <div style="font-size:0.84rem; color:#047857; margin-top:6px; line-height:1.45;">Replacing high-glycemic starches with high-fiber alternatives suppresses postprandial spikes by 35 mg/dL.</div>
          </div>
        """
    },

    # MODULE 5: THE DIABETES LEARNING ENGINE & EPIC EHR (21-25)
    {
        "id": 21, "num": "21",
        "module": "Module 5: The Diabetes Learning Engine",
        "title": "The Diabetes Learning Engine: Personal Glycemic Phenotyping",
        "desc": "How longitudinal telemetry builds a personalized metabolic map of carbohydrate sensitivity and clearance.",
        "narration": "Module 5 introduces the Diabetes Learning Engine. Every individual with diabetes absorbs and clears glucose differently. By analyzing weeks of continuous biofeedback, LCARS phenotypes your specific peak latency and clearance velocity, creating a personalized roadmap for you and your physician.",
        "card_title": "DIABETES LEARNING ENGINE & PHENOTYPING",
        "bullets": [
            ("Personalized Absorption Rate", "Determines your exact post-meal peak latency (e.g. 60 vs 90 minutes)."),
            ("Clearance Velocity Calculation", "Quantifies how fast your body clears circulating glucose under different activities."),
            ("Tailored Therapy Guidance", "Enables precision outpatient medication timing based on your actual metabolic phenotype.")
        ],
        "takeaway": "Replaces generic population guidelines with your individualized metabolic fingerprint.",
        "right_header": "DIABETES LEARNING ENGINE",
        "right_badge": "GLYCEMIC PHENOTYPE",
        "right_badge_col": "#0284c7",
        "right_html": """
          <div style="margin-top:10px; background:#f0f9ff; border:1.5px solid #bae6fd; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#0369a1; font-family:monospace;">PEAK LATENCY: 65 MIN • CLEARANCE: 1.1 MG/DL/MIN</div>
            <div style="font-size:0.84rem; color:#075985; margin-top:6px; line-height:1.45;">Empirical mathematical modeling of your individual carbohydrate absorption and active clearance kinetics.</div>
          </div>
        """
    },
    {
        "id": 22, "num": "22",
        "module": "Module 5: The Diabetes Learning Engine",
        "title": "Circadian Fasting Windows & Dawn Phenomenon Suppression",
        "desc": "Identifying nocturnal nadirs and counteracting morning hepatic glucose surges.",
        "narration": "Slide 22 addresses morning fasting numbers. Many diabetics wake up with high blood sugar due to the Dawn Phenomenon—a natural morning surge of cortisol that prompts the liver to release glucose. By tracking continuous overnight telemetry down to the 4 AM nadir, LCARS helps your doctor determine the right evening strategy to suppress morning spikes.",
        "card_title": "CIRCADIAN FASTING & DAWN PHENOMENON SUPPRESSION",
        "bullets": [
            ("Nocturnal Nadir Identification", "Confirms blood sugar steadily drops to its lowest healthy baseline at 4:00 AM."),
            ("Dawn Phenomenon Detection", "Differentiates hepatic morning surges from overnight Somogyi hypoglycemic rebound."),
            ("Targeted Morning Routine", "A brief morning stroll quickly clears dawn surges via immediate muscle contraction.")
        ],
        "takeaway": "Unmasks overnight blood sugar mysteries to deliver healthy, predictable morning fasting numbers.",
        "right_header": "DAWN PHENOMENON SUPPRESSION",
        "right_badge": "CIRCADIAN NADIR & SURGE",
        "right_badge_col": "#ca8a04",
        "right_html": """
          <div style="margin-top:10px; background:#fefce8; border:1.5px solid #fef08a; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#854d0e; font-family:monospace;">04:00 NADIR: 162 -> 07:00 FASTING: 174</div>
            <div style="font-size:0.84rem; color:#713f12; margin-top:6px; line-height:1.45;">Continuous 00:00 to 06:00 tracking distinguishes Somogyi rebound from hepatic Dawn Phenomenon with clinical certainty.</div>
          </div>
        """
    },
    {
        "id": 23, "num": "23",
        "module": "Module 5: The Diabetes Learning Engine",
        "title": "Sleep Architecture & Glycemic Resilience: SpO2, REM & Cortisol",
        "desc": "How deep delta sleep and stable arterial oxygen reduce nocturnal cortisol and insulin resistance.",
        "narration": "Slide 23 connects sleep quality directly to diabetes. During deep delta sleep, your body repairs tissue and suppresses cortisol. If your sleep is fragmented by hypoxia or frequent awakenings, morning cortisol spikes, worsening insulin resistance. Tracking nocturnal oxygen stability ensures your sleep actively aids your glycemic goals.",
        "card_title": "SLEEP ARCHITECTURE, DEEP DELTA & CORTISOL",
        "bullets": [
            ("Restorative Deep Delta Sleep", "Rebuilds insulin sensitivity and suppresses morning stress hormones."),
            ("Nocturnal Oxygen Stability", "Verifies uninterrupted arterial SpO2 throughout the night without hypoxic dips."),
            ("Sleep-to-Sugar Connection", "Poor sleep directly increases next-day glycemic variability and insulin resistance.")
        ],
        "takeaway": "Restorative, uninterrupted sleep is a foundational pillar of diabetic metabolic control.",
        "right_header": "SLEEP RESILIENCE & CORTISOL",
        "right_badge": "NOCTURNAL RESTORATION",
        "right_badge_col": "#7c3aed",
        "right_html": """
          <div style="margin-top:10px; background:#f5f3ff; border:1.5px solid #ddd6fe; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#5b21b6; font-family:monospace;">7.4 HOURS TOTAL • DEEP DELTA 1.9 HOURS</div>
            <div style="font-size:0.84rem; color:#4c1d95; margin-top:6px; line-height:1.45;">Restorative delta sleep lowers systemic morning cortisol, optimizing next-day peripheral insulin sensitivity.</div>
          </div>
        """
    },
    {
        "id": 24, "num": "24",
        "module": "Module 5: The Diabetes Learning Engine",
        "title": "Correlating Behavioral Habits with Direct Sensor Evidence",
        "desc": "Cross-analyzing diet, physical activity, and sleep against objective physiological biomarkers.",
        "narration": "Slide 24 highlights habit correlation. Diabetes is managed through daily habits. LCARS continuously correlates your steps, your meals, and your sleep architecture against your glucose curves. This gives you and your doctor undeniable evidence of what works best for your body.",
        "card_title": "CORRELATING HABITS WITH DIRECT SENSOR EVIDENCE",
        "bullets": [
            ("Dietary Correlation", "Correlates carbohydrate volume directly with excursion height and duration."),
            ("Step Volume Correlation", "Demonstrates the direct inverse relationship between daily steps and average glucose."),
            ("Holistic Behavioral Loop", "Provides objective positive reinforcement for healthy lifestyle decisions.")
        ],
        "takeaway": "Replaces guesswork with undeniable, objective evidence of your daily lifestyle progress.",
        "right_header": "SENSOR-HABIT CORRELATION",
        "right_badge": "MULTIVARIATE EVIDENCE",
        "right_badge_col": "#059669",
        "right_html": """
          <div style="margin-top:10px; background:#ecfdf5; border:1.5px solid #a7f3d0; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#065f46; font-family:monospace;">STEPS VS GLUCOSE: r = -0.79 CORRELATION</div>
            <div style="font-size:0.84rem; color:#047857; margin-top:6px; line-height:1.45;">Multi-variable mathematical alignment proves daily walking directly suppresses 24-hour mean glycemic load.</div>
          </div>
        """
    },
    {
        "id": 25, "num": "25",
        "module": "Module 5: The Diabetes Learning Engine",
        "title": "Actionable Clinical Reports & Epic EHR Integration",
        "desc": "How 1-click export pushes continuous telemetry directly into your doctor's Epic Electronic Health Record.",
        "narration": "Slide 25 features our flagship clinical bridge: direct Epic Electronic Health Record integration. With a single click in LCARS, your 24-hour glucose curves, Time-in-Range percentages, blood pressure dipping, and medication adherence are formatted into standard HL7 FHIR records and pushed directly into your doctor's Epic MyChart chart, becoming a permanent part of your clinical medical record.",
        "card_title": "EPIC EHR INTEGRATION & ACTIONABLE DOCTOR REPORTS",
        "bullets": [
            ("Direct Epic EHR Upload", "Push verified 24-hour telemetry directly into your doctor's Epic MyChart medical record."),
            ("HL7 FHIR Standard Format", "Packages continuous glucose, BP, SpO2, and med adherence into certified interoperable records."),
            ("Permanent Medical Record", "Telemetry becomes an official, permanent outpatient chart entry reviewed during visits.")
        ],
        "takeaway": "Seamlessly bridges your home workstation directly into your doctor's hospital Epic EHR system.",
        "right_header": "EPIC EHR MYCHART GATEWAY",
        "right_badge": "DIRECT EHR UPLOAD",
        "right_badge_col": "#0284c7",
        "right_html": """
          <div style="margin-top:10px; background:#eff6ff; border:1.5px solid #bfdbfe; border-left:6px solid #0284c7; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#1e40af; font-family:monospace;">1-CLICK PUSH TO DOCTOR'S EPIC SYSTEM</div>
            <div style="font-size:0.84rem; color:#1e3a8a; margin-top:6px; line-height:1.45;">Transmits certified HL7 FHIR continuous glucose tonometry, blood pressure dipping, and titration logs into permanent clinical records.</div>
          </div>
          <div style="margin-top:10px; display:flex; justify-content:space-between; font-family:monospace; font-size:0.75rem; background:#f8fafc; padding:6px 10px; border-radius:4px;">
            <span>EPIC STATUS:</span>
            <span style="color:#15803d; font-weight:bold;">COMMITTED TO PERMANENT CHART</span>
          </div>
        """
    },

    # MODULE 6: TAB 04 SYNCHRONIZED TALL CHART (26-30)
    {
        "id": 26, "num": "26",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "title": "Tab 04 Architecture: Single Tall Synchronized Multi-Measurement Chart",
        "desc": "How a unified vertical timeline aligns 8 physiological metrics on a shared horizontal time axis.",
        "narration": "In Module 6, we explore Tab 04: the Synchronized Tall Chart. Instead of jumping across disconnected mobile screens, LCARS stacks all 8 biometric streams on one shared 24-hour time axis with zero label overlap. You can scan vertically from midnight to midnight and instantly see how everything connects.",
        "card_title": "TAB 04: SYNCHRONIZED TALL CHART ARCHITECTURE",
        "bullets": [
            ("Unified 24-Hour Timeline", "Aligns all physiological telemetry on a shared midnight-to-midnight horizontal axis."),
            ("Multi-Line Zero-Overlap Labels", "Clean time ticks on Line 1 with staggered event badges on Lines 2 and 3."),
            ("Senior-Friendly High Contrast", "Solid white cards with 4px reference line halos ensuring total readability.")
        ],
        "takeaway": "Eliminates fragmented mobile screens in favor of one unified, beautifully readable clinical timeline.",
        "right_header": "TAB 04: 24-HR TIMELINE",
        "right_badge": "ZERO-OVERLAP X-AXIS",
        "right_badge_col": "#059669",
        "right_html": """
          <div style="margin-top:10px; background:#ecfdf5; border:1.5px solid #a7f3d0; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#065f46; font-family:monospace;">MULTI-LINE STAGGERED TIMELINE LABELS</div>
            <div style="font-size:0.84rem; color:#047857; margin-top:6px; line-height:1.45;">Line 1 time ticks with alternating event badges give descriptions 128px of clearance with zero collision.</div>
          </div>
          <div style="margin-top:10px; font-family:monospace; font-size:0.75rem; color:#475569; background:#f8fafc; padding:6px 10px; border-radius:4px;">
            X-Axis: 00:00 -> 04:00 (Nadir) -> 07:00 (Fasting) -> 08:30 (Walk) -> 13:30 (Peak) -> 24:00
          </div>
        """
    },
    {
        "id": 27, "num": "27",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "title": "The 8 Stacked Measurement Lanes: Complete Multi-Variable Audit",
        "desc": "Examining the 8 synchronized lanes: Glucose, eA1c, Temperature, Sleep, Steps, Heart Rate, BP, and SpO2.",
        "narration": "Slide 27 details the 8 stacked lanes. Lane 1 is continuous glucose. Lane 2 is projected A1C. Lane 3 is dual core and skin temperature. Lane 4 is sleep hypnograms. Lane 5 is step activity. Lane 6 is heart rate. Lane 7 is blood pressure. And Lane 8 is pulse oximetry. It's a complete 360-degree audit of your day.",
        "card_title": "8 STACKED 24-HOUR MEASUREMENT LANES",
        "bullets": [
            ("Complete 360-Degree Coverage", "Eight simultaneous physiological lanes provide an exhaustive clinical overview."),
            ("Synchronized Time Tracking", "Every lane moves in lockstep across morning, afternoon, evening, and night."),
            ("Holistic Physiological Context", "Never view glucose in isolation; see it alongside heart rate, temperature, and steps.")
        ],
        "takeaway": "An exhaustive multi-variable audit that places every glucose fluctuation in true physiological context.",
        "right_header": "8 STACKED MEASUREMENT LANES",
        "right_badge": "COMPLETE 360° AUDIT",
        "right_badge_col": "#0284c7",
        "right_html": """
          <div style="margin-top:10px; background:#f0f9ff; border:1.5px solid #bae6fd; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#0369a1; font-family:monospace;">8 SYNCHRONIZED MEASUREMENT LANES</div>
            <div style="font-size:0.84rem; color:#075985; margin-top:6px; line-height:1.45;">Glucose • eA1C • Core/Skin Temp • Sleep Hypnogram • Step Cadence • Heart Rate • Blood Pressure • SpO2</div>
          </div>
        """
    },
    {
        "id": 28, "num": "28",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "title": "The Continuous Vertical Scrubber Line: Cross-Correlating Causes",
        "desc": "How dragging the vertical scrubber aligns all 8 channels to reveal exact causes behind glucose spikes.",
        "narration": "Slide 28 introduces the vertical scrubber line. As you drag your cursor across the chart, a vertical guideline cross-sects all 8 lanes at that exact timestamp. When you stop at 1:30 PM, you see your peak glucose of 232 milligrams per deciliter aligning with 34 grams of lunch carbs, an elevated heart rate, and post-meal rest.",
        "card_title": "CONTINUOUS VERTICAL SCRUBBER LINE AT 13:30",
        "bullets": [
            ("Cross-Cutting Temporal Analysis", "A single vertical guideline intersects all 8 measurement channels simultaneously."),
            ("Pinpoint Cause-and-Effect", "Aligns peak excursions directly with meal times, medication ingestion, and activity."),
            ("Interactive Desktop Exploration", "Fluid cursor scrubbing makes investigating daily trends intuitive and informative.")
        ],
        "takeaway": "Instant cause-and-effect correlation across all 8 physiological channels at any moment in time.",
        "right_header": "VERTICAL SCRUBBER LINE",
        "right_badge": "13:30 CROSSHAIR",
        "right_badge_col": "#ca8a04",
        "right_html": """
          <div style="margin-top:10px; background:#fefce8; border:1.5px solid #fef08a; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#854d0e; font-family:monospace;">CROSSHAIR AT 13:30 (LUNCH PEAK: 232)</div>
            <div style="font-size:0.84rem; color:#713f12; margin-top:6px; line-height:1.45;">Scrubber cross-correlates 232 mg/dL excursion with 34g net carbs, 84 bpm cardiac load, and sedentary post-meal rest.</div>
          </div>
        """
    },
    {
        "id": 29, "num": "29",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "title": "Click-to-Pop Clinical Point Cards: Instant Micro-Audits",
        "desc": "Clicking any timeline node displays exact values, diet logs, and medication context.",
        "narration": "Slide 29 highlights Click-to-Pop Clinical Cards. Whenever you click any data point on the timeline, a detailed pop-up card appears. It reveals the exact numeric glucose, the delta G drop from prior readings, logged meals, exercise steps, and whether medications were taken on time.",
        "card_title": "CLICK-TO-POP CLINICAL POINT MICRO-AUDITS",
        "bullets": [
            ("Instant Point Inspection", "Click any node on the curve to inspect verified measurements and timestamps."),
            ("Comprehensive Micro-Audit", "Displays immediate delta G changes, logged dietary carbs, and medication adherence."),
            ("Consultation Efficiency", "Allows your physician to quickly audit specific spikes without searching through paper logs.")
        ],
        "takeaway": "Instant deep-dive micro-audits at the touch of any data point on your clinical curve.",
        "right_header": "CLINICAL POP-UP CARDS",
        "right_badge": "INSTANT MICRO-AUDIT",
        "right_badge_col": "#15803d",
        "right_html": """
          <div style="margin-top:10px; background:#f0fdf4; border:1.5px solid #bbf7d0; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#14532d; font-family:monospace;">POINT 10:00: 165 MG/DL (-27 MG/DL DROP)</div>
            <div style="font-size:0.84rem; color:#166534; margin-top:6px; line-height:1.45;">Pop-up micro-audit verifies 2,384 morning walk steps successfully lowered glucose from 192 to 165 mg/dL.</div>
          </div>
        """
    },
    {
        "id": 30, "num": "30",
        "module": "Module 6: Tab 04 Synchronized Tall Chart",
        "title": "Pinning '💊 MED CHANGE' Milestones: Auditing Progress vs Regression",
        "desc": "Visualizing prescription changes on clinical graphs to objectively measure therapeutic response.",
        "narration": "Slide 30 is crucial for doctor visits: Pinning Medication Changes. When your physician alters a prescription, logging the titration pins a prominent MED CHANGE milestone directly across your timeline. Both of you can then compare the 14 days before against the 14 days after, objectively proving whether the change yielded clinical progress.",
        "card_title": "PINNING '💊 MED CHANGE' MILESTONE MARKERS",
        "bullets": [
            ("Objective Titration Tracking", "Visually pins prescription adjustments directly onto longitudinal glucose graphs."),
            ("Before-and-After Analysis", "Directly compares average blood sugar and Time-in-Range before vs after dosage changes."),
            ("Definitive Therapeutic Proof", "Objectively determines whether a new medication is delivering genuine clinical progress.")
        ],
        "takeaway": "Visually proves whether a prescription change delivered genuine clinical improvement.",
        "right_header": "MEDICATION MILESTONES",
        "right_badge": "PROGRESS VS REGRESSION",
        "right_badge_col": "#7c3aed",
        "right_html": """
          <div style="margin-top:10px; background:#f5f3ff; border:1.5px solid #ddd6fe; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#5b21b6; font-family:monospace;">FLAG: 💊 MED CHANGE PINNED (NOV 14)</div>
            <div style="font-size:0.84rem; color:#4c1d95; margin-top:6px; line-height:1.45;">Metformin ER titrated to 1000mg. Longitudinal comparison confirms average glucose dropped by 12 mg/dL. Progress verified.</div>
          </div>
        """
    },

    # MODULE 7: TAB 11 MEDICATION MANAGEMENT (31-33)
    {
        "id": 31, "num": "31",
        "module": "Module 7: Tab 11 Medication Management",
        "title": "Tab 11 Architecture: Active Pharmacological Regimen Management",
        "desc": "Tracking your complete outpatient pharmacological regimen with dosage, indication, and timing.",
        "narration": "In Module 7, we examine Tab 11: Medication Management. Managing multiple outpatient medications requires diligence. LCARS maintains your complete active drug list with exact dosage, timing, and clinical indications, ensuring zero missed doses and full compliance tracking.",
        "card_title": "TAB 11: ACTIVE PHARMACOLOGICAL MANAGEMENT",
        "bullets": [
            ("Full Outpatient Regimen", "Tracks your complete active prescription suite with dosages, timing, and indications."),
            ("Adherence Monitoring", "Maintains compliance logs to verify consistent daily medication timing."),
            ("Safety & Contraindication Guard", "Organized interface prevents accidental double-dosing or forgotten intake.")
        ],
        "takeaway": "A structured, reliable pharmacological suite keeping you and your doctor fully aligned.",
        "right_header": "TAB 11: MEDICATION SUITE",
        "right_badge": "ACTIVE PHARMACOLOGY",
        "right_badge_col": "#7c3aed",
        "right_html": """
          <div style="margin-top:10px; background:#f5f3ff; border:1.5px solid #ddd6fe; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#5b21b6; font-family:monospace;">8 ACTIVE MEDICATIONS TRACKED</div>
            <div style="font-size:0.84rem; color:#4c1d95; margin-top:6px; line-height:1.45;">Comprehensive schedule with dosage, timing, clinical indications, and titration compliance records.</div>
          </div>
        """
    },
    {
        "id": 32, "num": "32",
        "module": "Module 7: Tab 11 Medication Management",
        "title": "Two Discrete Daily Intake Schedules: Morning Suite & Dinner Split",
        "desc": "Separating medications into scrollable Morning (Intake 1) and Evening (Intake 2) lists.",
        "narration": "Slide 32 highlights our dual intake layout. Medications are split cleanly into two distinct schedules: Morning Intake 1 and Evening Intake 2. The morning list holds your breakfast doses—such as Metformin, Glipizide, and cardiovascular meds—while Intake 2 holds your evening doses, preventing dosing confusion.",
        "card_title": "DUAL DAILY INTAKE SCHEDULES: MORNING & EVENING",
        "bullets": [
            ("Morning Suite (Intake 1)", "Full scrollable list for breakfast doses including Metformin, Glipizide, and Lisinopril."),
            ("Evening Suite (Intake 2)", "Dedicated evening schedule for dinner doses and lipid-lowering therapies."),
            ("Zero Visual Clutter", "Eliminates confusion by separating morning and night medications into dedicated scrollable panels.")
        ],
        "takeaway": "Clear morning and evening separation ensures every medication is taken at the right time.",
        "right_header": "DUAL INTAKE SCHEDULES",
        "right_badge": "MORNING & EVENING",
        "right_badge_col": "#7c3aed",
        "right_html": """
          <div style="margin-top:10px; background:#f5f3ff; border:1.5px solid #ddd6fe; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#5b21b6; font-family:monospace;">INTAKE 1 (MORNING) • INTAKE 2 (EVENING)</div>
            <div style="font-size:0.84rem; color:#4c1d95; margin-top:6px; line-height:1.45;">Intake 1: Metformin, Glipizide, Empagliflozin, Lisinopril, Statins. Intake 2: Evening Metformin & Omega-3. Clean scrollable lists.</div>
          </div>
        """
    },
    {
        "id": 33, "num": "33",
        "module": "Module 7: Tab 11 Medication Management",
        "title": "Granular Medication Editing, Deletion & Titration Adherence",
        "desc": "Updating dosages, logging adherence, and automatically updating clinical milestones.",
        "narration": "Slide 33 details medication editing. Whenever your doctor titrates a dose or discontinues a medication, you can edit it immediately in Tab 11. Changing a dose automatically updates your active intake list and records a titration milestone on your clinical reports.",
        "card_title": "GRANULAR MEDICATION EDITING & TITRATION ADHERENCE",
        "bullets": [
            ("Instant Dose Adjustments", "Quickly update milligrams, timing, or indications when prescriptions change."),
            ("Adherence Rate Scoring", "Calculates monthly adherence scores to share with your healthcare provider."),
            ("Automatic Timeline Synchronization", "Dose updates automatically sync with the 24-hour clinical charts.")
        ],
        "takeaway": "Keeps your digital medication profile completely synchronized with your physician's orders.",
        "right_header": "TITRATION ADHERENCE",
        "right_badge": "96.4% COMPLIANCE",
        "right_badge_col": "#059669",
        "right_html": """
          <div style="margin-top:10px; background:#ecfdf5; border:1.5px solid #a7f3d0; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#065f46; font-family:monospace;">DOSE ADJUSTMENTS SYNCHRONIZED</div>
            <div style="font-size:0.84rem; color:#047857; margin-top:6px; line-height:1.45;">Adjusting doses triggers auto-pinning of medication milestones on longitudinal charts with 96.4% recorded adherence.</div>
          </div>
        """
    },

    # MODULE 8: TAB 12 SEQUENTIAL FULL SCAN (34-35)
    {
        "id": 34, "num": "34",
        "module": "Module 8: Tab 12 Sequential Full Scan",
        "title": "Tab 12 Full Scan: Automated Sequential 6-Sensor Diagnostic Pipeline",
        "desc": "The 120-second automated diagnostic sequence testing all 6 biometric transponders.",
        "narration": "Module 8 showcases Tab 12: Sequential Full Scan. When you want a complete diagnostic check, you trigger Full Scan. Over 120 seconds, LCARS activates all six transponders sequentially: heart rate, blood pressure, temperature, oxygen saturation, ECG, and optical glucose.",
        "card_title": "TAB 12: SEQUENTIAL 6-SENSOR FULL SCAN PIPELINE",
        "bullets": [
            ("Automated 120-Second Pipeline", "Runs a complete, non-interfering diagnostic sequence across all watch sensors."),
            ("Sequential Transponder Activation", "Tests heart rate, blood pressure, temperature, SpO2, ECG, and glucose one by one."),
            ("Diagnostic Integrity Check", "Ensures all optical and thermal transponders are functioning within factory tolerances.")
        ],
        "takeaway": "A turnkey 2-minute diagnostic checkup testing every sensor on your wrist in sequence.",
        "right_header": "TAB 12: FULL SCAN PIPELINE",
        "right_badge": "120s 6-SENSOR PIPELINE",
        "right_badge_col": "#0891b2",
        "right_html": """
          <div style="margin-top:10px; background:#ecfeff; border:1.5px solid #a5f3fc; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#155e75; font-family:monospace;">6-STAGE SEQUENTIAL DIAGNOSTICS</div>
            <div style="font-size:0.84rem; color:#164e63; margin-top:6px; line-height:1.45;">Stage 1: HR -> Stage 2: BP -> Stage 3: Temp -> Stage 4: SpO2 -> Stage 5: ECG -> Stage 6: Glucose. Non-interfering flow.</div>
          </div>
        """
    },
    {
        "id": 35, "num": "35",
        "module": "Module 8: Tab 12 Sequential Full Scan",
        "title": "Live Stage Progression, Non-Interfering Scan Flow & Clinical Report",
        "desc": "Real-time visual progress through the 6 stages and generation of verified scan reports.",
        "narration": "Slide 35 displays the Full Scan results dashboard. Live progress bars track each stage in real time. When complete, LCARS delivers an immediate clinical report summarizing all six biomarkers, verifying that your watch transponders are nominal and ready for monitoring.",
        "card_title": "LIVE STAGE PROGRESSION & FULL SCAN REPORT",
        "bullets": [
            ("Real-Time Visual Progress", "Watch animated progress rings track each 15 to 25-second diagnostic stage."),
            ("Instant Diagnostic Summary", "Consolidates all six immediate measurements onto a single clean verification card."),
            ("Reproducibility Verification", "Validates sensor consistency against continuous background ambulatory telemetry.")
        ],
        "takeaway": "Immediate clinical verification confirming all wrist sensors are operating at peak fidelity.",
        "right_header": "FULL SCAN RESULTS DASHBOARD",
        "right_badge": "ALL SENSORS NOMINAL",
        "right_badge_col": "#059669",
        "right_html": """
          <div style="margin-top:10px; background:#ecfdf5; border:1.5px solid #a7f3d0; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#065f46; font-family:monospace;">HR: 68 • BP: 118/76 • TEMP: 97.2° • SPO2: 98% • GLU: 182</div>
            <div style="font-size:0.84rem; color:#047857; margin-top:6px; line-height:1.45;">Comprehensive multi-sensor audit completing in 120 seconds with immediate verification. All sensors nominal.</div>
          </div>
        """
    },

    # MODULE 9: MASTER CAPABILITY MATRIX: H-BAND VS LCARS (36-38)
    {
        "id": 36, "num": "36",
        "module": "Module 9: Master Capability Matrix: H-Band vs LCARS",
        "title": "H-Band vs LCARS Matrix Part 1: Hardware Parity & Zero-Cloud Privacy",
        "desc": "Comparing mobile app cloud telemetry against local air-gapped PC architecture.",
        "narration": "Module 9 compares the stock H-Band mobile app against LCARS. Part 1 covers architecture and privacy. While the mobile app sends private medical data to remote cloud servers, LCARS runs entirely on your local PC via port 8899 and SQLite, delivering 100 percent air-gapped data sovereignty.",
        "card_title": "H-BAND VS LCARS: ARCHITECTURE & PRIVACY",
        "bullets": [
            ("Platform Architecture", "Mobile app restricted to small phone screens vs Full Windows 11 desktop workstation."),
            ("Data Privacy & Sovereignty", "Remote third-party cloud servers vs 100% local air-gapped SQLite database."),
            ("Offline Capability", "Continuous monitoring without needing cell service, cloud logins, or accounts.")
        ],
        "takeaway": "Restores total medical data ownership: zero cloud silos, zero tracking, total privacy.",
        "right_header": "H-BAND VS LCARS: PART 1",
        "right_badge": "ARCHITECTURE & PRIVACY",
        "right_badge_col": "#ea580c",
        "right_html": """
          <div style="margin-top:10px; background:#fff7ed; border:1.5px solid #fed7aa; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#9a3412; font-family:monospace;">AIR-GAPPED SQLITE VS REMOTE CLOUD SILOS</div>
            <table style="width:100%; border-collapse:collapse; margin-top:8px; font-size:0.76rem;">
              <tr style="border-bottom:1px solid #fed7aa;">
                <td style="padding:4px; font-weight:bold; color:#7c2d12;">Standard H-Band App:</td>
                <td style="padding:4px; color:#991b1b;">Proprietary cloud servers, phone-only, fragmented tabs</td>
              </tr>
              <tr>
                <td style="padding:4px; font-weight:bold; color:#7c2d12;">LCARS 5010 Console:</td>
                <td style="padding:4px; color:#166534; font-weight:bold;">100% Local SQLite, air-gapped privacy, PC workstation</td>
              </tr>
            </table>
          </div>
        """
    },
    {
        "id": 37, "num": "37",
        "module": "Module 9: Master Capability Matrix: H-Band vs LCARS",
        "title": "H-Band vs LCARS Matrix Part 2: Calibration, Timelines & Metabolic Engine",
        "desc": "Comparing calibration capabilities, multi-lane timelines, and exercise kinetics.",
        "narration": "Part 2 contrasts clinical analytics. The mobile app uses static linear approximations and fragmented graphs. LCARS provides certified 8.3% lab anchor calibration, 8 synchronized timeline lanes with zero overlap, and empirical GLUT4 exercise drop tracking.",
        "card_title": "H-BAND VS LCARS: CALIBRATION & TIMELINES",
        "bullets": [
            ("Clinical Calibration", "Generic factory offsets vs Certified 8.3% venous blood draw calibration anchor."),
            ("Longitudinal Timelines", "Disconnected mobile screens vs Synchronized 8-lane tall chart with crosshair scrubber."),
            ("Behavioral Exercise Analytics", "Raw step counter vs Quantified GLUT4 delta G glucose drop calculations.")
        ],
        "takeaway": "Transforms raw wearable data into certified, clinically calibrated medical intelligence.",
        "right_header": "H-BAND VS LCARS: PART 2",
        "right_badge": "CALIBRATION & TIMELINES",
        "right_badge_col": "#ea580c",
        "right_html": """
          <div style="margin-top:10px; background:#fff7ed; border:1.5px solid #fed7aa; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#9a3412; font-family:monospace;">8.3% ANCHOR & GLUT4 KINETICS VS RAW TOTALS</div>
            <table style="width:100%; border-collapse:collapse; margin-top:8px; font-size:0.76rem;">
              <tr style="border-bottom:1px solid #fed7aa;">
                <td style="padding:4px; font-weight:bold; color:#7c2d12;">Standard H-Band App:</td>
                <td style="padding:4px; color:#991b1b;">Generic population offset, isolated screens, no GLUT4 math</td>
              </tr>
              <tr>
                <td style="padding:4px; font-weight:bold; color:#7c2d12;">LCARS 5010 Console:</td>
                <td style="padding:4px; color:#166534; font-weight:bold;">8.3% Lab Anchor, 8-Lane Tall Chart, Direct ΔG Drops</td>
              </tr>
            </table>
          </div>
        """
    },
    {
        "id": 38, "num": "38",
        "module": "Module 9: Master Capability Matrix: H-Band vs LCARS",
        "title": "H-Band vs LCARS Matrix Part 3: Senior-Friendly Ergonomics & 21:1 Contrast",
        "desc": "Examining senior accessibility, 21:1 solid white contrast, and high-luminance typography.",
        "narration": "Part 3 addresses visual ergonomics. The mobile app relies on tiny gray text and washed-out pastels that are difficult for seniors to read. LCARS standardizes on solid white cards with deep navy typography, delivering maximum 21:1 contrast, bold colored numerals, and night-vision themes.",
        "card_title": "H-BAND VS LCARS: SENIOR ERGONOMICS & 21:1 CONTRAST",
        "bullets": [
            ("Luminance Contrast Standard", "Replaces dim pastels with solid white #ffffff surfaces and deep navy #091428 text."),
            ("Maximum 21:1 Readability", "Meets strict accessibility criteria so seniors can read vitals clearly across the room."),
            ("High-Saturated Readouts", "Bold crimson, electric blue, forest green, and amber numerals for instant recognition.")
        ],
        "takeaway": "Engineered with senior accessibility at the forefront: clear, sharp, high-contrast readability.",
        "right_header": "H-BAND VS LCARS: PART 3",
        "right_badge": "21:1 SENIOR CONTRAST",
        "right_badge_col": "#ea580c",
        "right_html": """
          <div style="margin-top:10px; background:#fff7ed; border:1.5px solid #fed7aa; border-radius:6px; padding:12px;">
            <div style="font-size:1.1rem; font-weight:800; color:#9a3412; font-family:monospace;">SOLID WHITE 21:1 CONTRAST VS PASTEL DISPLAYS</div>
            <table style="width:100%; border-collapse:collapse; margin-top:8px; font-size:0.76rem;">
              <tr style="border-bottom:1px solid #fed7aa;">
                <td style="padding:4px; font-weight:bold; color:#7c2d12;">Standard H-Band App:</td>
                <td style="padding:4px; color:#991b1b;">Muted grays, washed-out pastels, tiny phone font sizes</td>
              </tr>
              <tr>
                <td style="padding:4px; font-weight:bold; color:#7c2d12;">LCARS 5010 Console:</td>
                <td style="padding:4px; color:#166534; font-weight:bold;">Solid white cards, 21:1 navy text, bold saturated numbers</td>
              </tr>
            </table>
          </div>
        """
    },

    # MODULE 10: CLINICAL SUMMARIES & ACHIEVING DIABETIC GOALS (39-40)
    {
        "id": 39, "num": "39",
        "module": "Module 10: Clinical Summaries & Achieving Diabetic Goals",
        "title": "Outpatient Clinical Review: Reviewing Telemetry with Your Doctor",
        "desc": "How to structure consultations, share Epic EHR records, and collaborate with your physician.",
        "narration": "In Module 10, we tie everything together. Bringing LCARS into your doctor visits changes the conversation. Instead of recounting vague memories, you review objective 24-hour curves, push verified records directly into their Epic EHR system, and collaborate on precise medication adjustments.",
        "card_title": "OUTPATIENT CONSULTATION & EPIC RECORD SUBMISSION",
        "bullets": [
            ("Structured Doctor Consultations", "Review 14-day and 30-day glycemic trajectories alongside meal and activity logs."),
            ("Direct Epic EHR Integration", "Push continuous telemetry straight into your doctor's official Epic chart with one click."),
            ("Targeted Therapy Optimization", "Make confident, evidence-based medication changes backed by real ambulatory data.")
        ],
        "takeaway": "Brings objective continuous clinical evidence directly into your outpatient medical consultations.",
        "right_header": "DOCTOR CONSULTATION DASHBOARD",
        "right_badge": "SHARED WORKBENCH",
        "right_badge_col": "#15803d",
        "right_html": """
          <div style="margin-top:10px; background:#f0fdf4; border:1.5px solid #bbf7d0; border-radius:6px; padding:12px;">
            <div style="font-size:1.15rem; font-weight:800; color:#14532d; font-family:monospace;">QUARTERLY LONGITUDINAL EVALUATION</div>
            <div style="font-size:0.84rem; color:#166534; margin-top:6px; line-height:1.45;">Empowers patient and physician to review real continuous evidence, evaluate GLUT4 efficacy, and safely titrate medications directly inside Epic EHR.</div>
          </div>
          <div style="margin-top:10px; display:flex; justify-content:space-between; font-family:monospace; font-size:0.75rem; background:#f8fafc; padding:6px 10px; border-radius:4px;">
            <span>EPIC MYCHART PUSH:</span>
            <span style="color:#0284c7; font-weight:bold;">READY FOR SUBMISSION</span>
          </div>
        """
    },
    {
        "id": 40, "num": "40",
        "module": "Module 10: Clinical Summaries & Open Source Ecosystem",
        "title": "Open Source Copyleft, Documentation & Powered by Google Gemini Pro",
        "desc": "LCARS 5010 is 100% free open-source copyleft software under GNU GPL v3, fully documented for clinical and engineering rigor, and powered by Google Gemini Pro.",
        "narration": "In closing, LCARS 5010 is completely free, open-source copyleft software released under the GNU General Public License version 3. You have complete sovereignty over your health data with zero cloud subscriptions and zero proprietary lock-in. The platform is fully documented with extensive clinical studies and engineering specifications in our docs directory, and was architected and built with Google Gemini Pro. You can purchase the FITAOS and Veepoo 5010 hardware through the links on screen and explore the full source code and documentation on GitHub. Thank you for joining our clinical masterclass!",
        "card_title": "OPEN SOURCE COPYLEFT & ARCHITECTURAL FOUNDATION",
        "bullets": [
            ("GNU GPL v3 Copyleft License", "Ensures absolute patient data sovereignty with zero recurring subscription fees and zero proprietary lock-in. You own 100% of your telemetry."),
            ("Fully Documented Engineering & Clinical Science", "Transparent, peer-audited clinical studies, ADA/ADAG A1C mathematical models, and complete protocol specifications in /docs/."),
            ("Powered by Google Gemini Pro", "Architected, engineered, and verified using Google DeepMind's flagship Gemini Pro AI, uniting advanced signal processing with senior-friendly human factors.")
        ],
        "takeaway": "An open-source, fully documented clinical workstation empowering patients and physicians together.",
        "right_header": "OPEN SOURCE ECOSYSTEM & RESOURCE HUB",
        "right_badge": "GPL v3 COPYLEFT",
        "right_badge_col": "#15803d",
        "right_html": """
          <div style="display:grid; grid-template-columns:1fr 1fr; gap:10px; margin-top:8px;">
            <a href="https://www.amazon.com/s?k=FITAOS+smart+watch" target="_blank" style="text-decoration:none; color:inherit; background:#f8fafc; border:1.5px solid #cbd5e1; border-radius:6px; padding:10px; display:block; transition:all 0.2s;">
              <div style="display:flex; align-items:center; gap:6px;">
                <span style="font-size:1.1rem;">🛒</span>
                <span style="font-weight:800; color:#0284c7; font-size:0.82rem;">WATCH SALES SITE</span>
              </div>
              <div style="font-size:0.72rem; color:#475569; margin-top:4px; line-height:1.35;">
                Purchase FITAOS 5150 / Veepoo Model 5010 hardware on Amazon or Veepoo Store.
              </div>
              <div style="font-size:0.7rem; color:#0284c7; font-weight:bold; margin-top:6px; font-family:monospace;">amazon.com / veepoo.com ↗</div>
            </a>

            <a href="../docs/clinical_glucose_study_and_a1c_analysis.md" target="_blank" style="text-decoration:none; color:inherit; background:#f8fafc; border:1.5px solid #cbd5e1; border-radius:6px; padding:10px; display:block; transition:all 0.2s;">
              <div style="display:flex; align-items:center; gap:6px;">
                <span style="font-size:1.1rem;">📚</span>
                <span style="font-weight:800; color:#15803d; font-size:0.82rem;">DOCS DIRECTORY</span>
              </div>
              <div style="font-size:0.72rem; color:#475569; margin-top:4px; line-height:1.35;">
                Clinical glucose trials, A1C validation models, bug audit logs & specifications.
              </div>
              <div style="font-size:0.7rem; color:#15803d; font-weight:bold; margin-top:6px; font-family:monospace;">D:/veepoo/docs/ ↗</div>
            </a>

            <a href="https://github.com/jbirdlebough1/JVEEPOO" target="_blank" style="text-decoration:none; color:inherit; background:#f8fafc; border:1.5px solid #cbd5e1; border-radius:6px; padding:10px; display:block; transition:all 0.2s;">
              <div style="display:flex; align-items:center; gap:6px;">
                <span style="font-size:1.1rem;">💻</span>
                <span style="font-weight:800; color:#7c3aed; font-size:0.82rem;">GITHUB REPOSITORY</span>
              </div>
              <div style="font-size:0.72rem; color:#475569; margin-top:4px; line-height:1.35;">
                Full GPL v3 copyleft source code, Python BLE daemons & HTML single-page app.
              </div>
              <div style="font-size:0.7rem; color:#7c3aed; font-weight:bold; margin-top:6px; font-family:monospace;">github.com/jbirdlebough1/JVEEPOO ↗</div>
            </a>

            <a href="https://github.com/HBandSDK/Android_Ble_SDK/issues/46" target="_blank" style="text-decoration:none; color:inherit; background:#f8fafc; border:1.5px solid #cbd5e1; border-radius:6px; padding:10px; display:block; transition:all 0.2s;">
              <div style="display:flex; align-items:center; gap:6px;">
                <span style="font-size:1.1rem;">🌐</span>
                <span style="font-weight:800; color:#d97706; font-size:0.82rem;">HBANDSDK COMMUNITY</span>
              </div>
              <div style="font-size:0.72rem; color:#475569; margin-top:4px; line-height:1.35;">
                Official open-source ecosystem showcase Issue #46 on HBandSDK organization.
              </div>
              <div style="font-size:0.7rem; color:#d97706; font-weight:bold; margin-top:6px; font-family:monospace;">github.com/HBandSDK/Issue #46 ↗</div>
            </a>
          </div>

          <div style="margin-top:12px; background:#eff6ff; border:1.5px solid #bfdbfe; border-radius:6px; padding:10px; display:flex; align-items:center; gap:12px;">
            <div style="background:#2563eb; color:#ffffff; font-weight:900; font-size:1rem; border-radius:6px; padding:6px 10px; font-family:monospace; white-space:nowrap;">
              GEMINI PRO
            </div>
            <div style="font-size:0.78rem; color:#1e3a8a; line-height:1.4;">
              <strong>Powered by Google Gemini Pro:</strong> Developed using Google DeepMind's advanced AI reasoning agent for continuous physiological telemetry, signal parsing, and clinical data synthesis.
            </div>
          </div>
        """
    }
]

slides_data.extend(remaining)
print(f"Total curriculum slides: {len(slides_data)}")

# Save full curriculum to build_presentation.py
with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides_data, f, indent=2)

print("Saved all_slides_data.json successfully!")
