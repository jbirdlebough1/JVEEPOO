import json, os

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

# Update Slide 28 (0-indexed 27)
s28 = slides[27]
s28['title'] = "Actionable Clinical Reports & Patient-Controlled Local Export"
s28['desc'] = "One-click local export of continuous telemetry into verified clinical PDF and CSV reports for doctor consultations."
s28['narration'] = "Displayed on your screen is the Reports Toolbar in Tab 04, highlighting our clean clinical export options. Because patient data privacy and HIPAA security are paramount, El-CARS operates strictly air-gapped on your local hardware. There is zero unauthorized transmission to third-party servers. With one click, you can generate comprehensive, publication-grade clinical reports in standard PDF and CSV formats. You maintain complete custody of your health data, ready to print, review side-by-side with your physician at Evergreen or your local clinic, or manually share through your patient portal on your own terms."
s28['card_title'] = "PATIENT-CONTROLLED LOCAL CLINICAL EXPORT & PRIVACY"
s28['bullets'] = [
    ["100% Air-Gapped Local Operation", "Telemetry remains strictly on your local PC with zero unauthorized cloud egress or unverified third-party transmissions."],
    ["One-Click Export (CSV & PDF)", "Bundles 90 days of continuous multi-lane telemetry into standardized RFC-4180 CSV and printable clinical PDF flowsheets."],
    ["Physician Consultation Ready", "Bridges the 15-minute appointment gap: hand your doctor verified time-series data covering fasting glucose, meal spikes, and night nadirs."],
    ["Patient-Directed Portal Upload", "You maintain full custody to manually attach reports to Evergreen MyChart or your health system's portal whenever you choose."]
]
s28['takeaway'] = "Your telemetry remains 100% private and air-gapped on your personal computer. One-click export generates comprehensive clinical reports ready for your next doctor consultation or patient portal upload."
s28['right_header'] = "AIR-GAPPED CLINICAL REPORT EXPORT"
s28['tab_title'] = "04-REPORTS CONSOLE • LOCAL CLINICAL EXPORT & PATIENT PRIVACY"

# Update Slide 51 (0-indexed 50)
s51 = slides[50]
s51['desc'] = "Comprehensive doctor-patient consultation covering Year-to-Date trends, 30-day monthly retrospects, and 24-hour daily continuous curves for clinical records."
s51['bullets'] = [
    ["Year-to-Date (YTD) Macro Trend", "Examines multi-quarter eA1c trajectory, seasonal glycemic stability, and cumulative medication titration progress."],
    ["Monthly (30-Day) Retrospect", "Audits 30-day glycemic variance, postprandial spike averages, dawn surge frequency, and overall Time-in-Range."],
    ["Daily (24-Hour) High-Resolution Curve", "Inspects continuous 288-point curves showing exact meal spikes, exercise GLUT4 drops, and overnight nadir safety."],
    ["Patient-Directed Clinical Consultation", "Submits verified YTD, monthly, and daily records formatted for clinical review and personal health records."]
]
s51['tab_title'] = "04-REPORTS CONSOLE • OUTPATIENT DOCTOR REVIEW & CLINICAL RECORDS"

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2)

print("[✔] Updated Slide 28 and Slide 51 in all_slides_data.json")
