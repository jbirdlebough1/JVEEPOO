import json, re

# 1. Update all_slides_data.json
slides_file = r"D:/veepoo/all_slides_data.json"
with open(slides_file, "r", encoding="utf-8") as f:
    slides = json.load(f)

for s in slides:
    sid = s.get("id") or int(s.get("num", 0))
    
    # Slide 14 & 15: Tab 02 Vitals
    if sid == 14:
        s["title"] = "Tab 02: VITALS – Optical Glucose Absorption Spectroscopy"
        s["tab_num"] = 2
        s["tab_img"] = "images/lcars_tab_vitals_live.png"
        s["narration"] = s["narration"].replace("Tab 01: VITALS", "Tab 02: VITALS").replace("Tab 01", "Tab 02")
    elif sid == 15:
        s["title"] = "Tab 02: VITALS – Dual-Matrix Thermometry & Circadian Nadir"
        s["tab_num"] = 2
        s["tab_img"] = "images/lcars_tab_vitals_live.png"
        s["narration"] = s["narration"].replace("Tab 01: VITALS", "Tab 02: VITALS").replace("Tab 01", "Tab 02")
        
    # Slide 16, 17, 18: Tab 03 Cardio
    elif sid == 16:
        s["title"] = "Tab 03: CARDIO – Pulse Wave Transit Time Blood Pressure"
        s["tab_num"] = 3
        s["tab_img"] = "images/lcars_tab_cardio_live.png"
        s["narration"] = s["narration"].replace("Tab 02: CARDIO", "Tab 03: CARDIO").replace("Tab 02", "Tab 03")
    elif sid == 17:
        s["title"] = "Tab 03: CARDIO – Dual-Wavelength SpO2 & Apnea Screening"
        s["tab_num"] = 3
        s["tab_img"] = "images/lcars_tab_cardio_live.png"
        s["narration"] = s["narration"].replace("Tab 02: CARDIO", "Tab 03: CARDIO").replace("Tab 02", "Tab 03")
    elif sid == 18:
        s["title"] = "Tab 03: CARDIO – Autonomic HRV & Sympatho-Vagal Stress"
        s["tab_num"] = 3
        s["tab_img"] = "images/lcars_tab_cardio_live.png"
        s["narration"] = s["narration"].replace("Tab 02: CARDIO", "Tab 03: CARDIO").replace("Tab 02", "Tab 03")

    # Slide 19: Tab 04 Metabolic
    elif sid == 19:
        s["title"] = "Tab 04: METABOLIC – Real-Time Physiological Telemetry Console"
        s["tab_num"] = 4
        s["tab_img"] = "images/lcars_tab_metabolic_live.png"
        s["narration"] = s["narration"].replace("03-METABOLIC", "Tab 04 METABOLIC").replace("Tab 03", "Tab 04")
    elif sid in [20, 21, 22, 24, 25]:
        s["tab_num"] = 4
        s["tab_img"] = "images/lcars_tab_metabolic_live.png"

    # Slide 23: Tab 06 ADA Trades
    elif sid == 23:
        s["title"] = "Tab 06: ADA Carbohydrate Trades & Gemini-Curated Diabetic Recipes"
        s["tab_num"] = 6
        s["tab_img"] = "images/lcars_tab_diettrades_live.png"
        s["narration"] = s["narration"].replace("Tab 09", "Tab 06")

    # Slide 28: Tab 05 Export
    elif sid == 28:
        s["title"] = "Tab 05: EHR Direct Import Export – Standard HL7 FHIR R4 & Clinical CSV"
        s["tab_num"] = 5
        s["tab_img"] = "images/lcars_tab_export_live.png"
        s["narration"] = s["narration"].replace("Tab 05 Reports Console", "Tab 05 Export Console").replace("Tab 04", "Tab 05")

    # Slide 29: Tab 07 Reports (Single Tall Chart)
    elif sid == 29:
        s["title"] = "Tab 07: Single Tall Synchronized Multi-Measurement Chart Architecture"
        s["tab_num"] = 7
        s["tab_img"] = "images/lcars_tab_reports_live.png"
        s["narration"] = s["narration"].replace("Tab 04", "Tab 07")
    elif sid in range(30, 43):
        s["tab_num"] = 7
        if not s.get("tab_img"):
            s["tab_img"] = "images/lcars_tab_reports_live.png"

    # Slide 43, 44, 45: Tab 10 Medication
    elif sid == 43:
        s["title"] = "Tab 10 Architecture: Active Pharmacological Regimen Management"
        s["tab_num"] = 10
        s["tab_img"] = "images/lcars_tab_medication_live.png"
        s["narration"] = s["narration"].replace("Tab 11", "Tab 10")
    elif sid in [44, 45]:
        s["tab_num"] = 10
        s["tab_img"] = "images/lcars_tab_medication_live.png"
        s["narration"] = s["narration"].replace("Tab 11", "Tab 10")

    # Slide 46, 47: Tab 01 Full Scan
    elif sid == 46:
        s["title"] = "Tab 01 Full Scan: Automated Sequential 6-Sensor Diagnostic Pipeline"
        s["tab_num"] = 1
        s["tab_img"] = "images/lcars_tab_fullscan_live.png"
        s["narration"] = s["narration"].replace("Tab 12 Full Scan", "Tab 01 Full Scan").replace("Tab 12", "Tab 01")
    elif sid == 47:
        s["title"] = "Tab 01 Full Scan: Live Stage Progression & Consolidated Clinical Report"
        s["tab_num"] = 1
        s["tab_img"] = "images/lcars_tab_fullscan_live.png"
        s["narration"] = s["narration"].replace("Tab 12", "Tab 01")

    # Slide 54: Tab 08 Learning Engine
    elif sid == 54:
        s["tab_num"] = 8
        s["tab_img"] = "images/lcars_tab_learning_live.png"

    # Slide 55: Tab 11 Clinical Goals
    elif sid == 55:
        s["tab_num"] = 11
        s["tab_img"] = "images/lcars_tab_goals_live.png"

    # Slide 56: Tab 07 Reports (48h timeline)
    elif sid == 56:
        s["title"] = "Tab 07 Reports: 48-Hour Continuous Metrology & Charging Dock Transparency"
        s["tab_num"] = 7
        s["tab_img"] = "images/lcars_tab_reports_live.png"

with open(slides_file, "w", encoding="utf-8") as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print(f"Successfully updated all {len(slides)} slides in all_slides_data.json")

# 2. Update build_presentation.py
bp_file = r"D:/veepoo/build_presentation.py"
with open(bp_file, "r", encoding="utf-8") as f:
    bp_code = f.read()

# Replace TAB_MAP
tab_map_pattern = r"TAB_MAP = \[.*?\]"
new_tab_map = """TAB_MAP = [
    (1, "01-FULL SCAN", "images/lcars_tab_fullscan_live.png"),
    (2, "02-VITALS", "images/lcars_tab_vitals_live.png"),
    (3, "03-CARDIO", "images/lcars_tab_cardio_live.png"),
    (4, "04-METABOLIC", "images/lcars_tab_metabolic_live.png"),
    (5, "05-EXPORT", "images/lcars_tab_export_live.png"),
    (6, "06-ADA TRADES", "images/lcars_tab_diettrades_live.png"),
    (7, "07-REPORTS", "images/lcars_tab_reports_live.png"),
    (8, "08-LEARNING", "images/lcars_tab_learning_live.png"),
    (9, "09-HELP & FAQ", "images/lcars_tab_help.png"),
    (10, "10-MEDICATION", "images/lcars_tab_medication_live.png"),
    (11, "11-CLINICAL GOALS", "images/lcars_tab_goals_live.png"),
    (12, "12-CONFIG", "images/lcars_tab_setup_live.png"),
    (13, "13-OPERATIONS", "images/lcars_tab_operations.png"),
    (14, "14-DATA LOG", "images/lcars_tab_datalog_live.png"),
    (15, "15-WATCH FACES", "images/lcars_tab_watchfaces.png")
]"""

bp_code = re.sub(tab_map_pattern, new_tab_map, bp_code, flags=re.S)

# Update get_active_tab_num function
get_active_pattern = r"def get_active_tab_num\(s, tab_img\):.*?return 1"
new_get_active = """def get_active_tab_num(s, tab_img):
    if s.get("tab_num"):
        return s["tab_num"]
    base = os.path.basename(tab_img or "").lower()
    if "fullscan" in base:
        return 1
    if "vital" in base:
        return 2
    if "cardio" in base:
        return 3
    if "metabolic" in base:
        return 4
    if "export" in base:
        return 5
    if "diet" in base or "meal" in base:
        return 6
    if "chart_" in base or "report" in base or "tall" in base:
        return 7
    if "learning" in base:
        return 8
    if "help" in base:
        return 9
    if "medication" in base or "med_" in base:
        return 10
    if "goal" in base:
        return 11
    if "setup" in base or "config" in base:
        return 12
    if "operation" in base:
        return 13
    if "log" in base:
        return 14
    if "watchface" in base or "dial" in base:
        return 15
    for t_num, t_name, t_path in TAB_MAP:
        if os.path.basename(t_path).lower() in base:
            return t_num
    return 1"""

bp_code = re.sub(get_active_pattern, new_get_active, bp_code, flags=re.S)

with open(bp_file, "w", encoding="utf-8") as f:
    f.write(bp_code)

print("Successfully updated build_presentation.py with all 15 LCARS tabs!")
