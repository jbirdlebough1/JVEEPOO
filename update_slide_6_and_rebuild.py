# update_slide_6_and_rebuild.py
import json
import subprocess
import os

with open('all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

for s in slides:
    if s['id'] == 6:
        s['title'] = "Smartwatch Hardware: 15 Health Dial Functions & Sample Rate Dynamics"
        s['card_title'] = "15 HEALTH DIAL FUNCTIONS • REMOTE VS. USER-INITIATED MATRIX"
        s['desc'] = "Comprehensive breakdown of all 15 health functions on the watch dial, detailing autonomous background streaming, remote command opcodes, physical electrode contact requirements, and the critical 1-second high-frequency sample rate shift during workouts."
        s['narration'] = "On the right is the physical FITAOS 5150 smartwatch transponder. The watch dial provides 15 distinct health functions. Eight of these stream completely autonomously in the background without user intervention: optical blood glucose, ambulatory blood pressure, dual-matrix temperature, continuous heart rate, oxygen saturation, metabolic MET energy, 24-hour physical activity, and nocturnal sleep somnography. In contrast, specialized diagnostics require deliberate patient execution on the watch: Lead-I ECG requires closing the bipolar chest circuit on the right bezel electrode for 30 seconds; Body Composition BIA requires finger contact on the bioimpedance pad; and Guided Breath paces respiration via haptic motor feedback. Crucially, when the patient engages Workout mode on the watch dial, the firmware switches its sampling rate from five-minute periodic intervals into a continuous one-second dynamic telemetry stream at one Hertz, capturing high-resolution cardiovascular exertion and recovery curves."
        s['bullets'] = [
            ["1. Autonomous Background Streaming (8 Channels)", "Glucose (0x75), BP (0x01), Temp (0x78), HR (0x02), SpO2 (0x60), MET (0x07), Activity (0x08), and Sleep (0xE0) stream automatically every 5 minutes."],
            ["2. User-Initiated Contact Tests (ECG & BIA)", "Lead-I ECG (0x19) and Body Composition BIA (0x85) require the patient to place their opposite fingers on the right bezel electrode to complete the physical circuit."],
            ["3. Dynamic Workout Sample Rate Shift (1 Hz)", "Engaging Workout mode on the dial triggers a critical firmware shift: telemetry sample rate increases from 5-min periodic to 1-second continuous dynamic streaming!"],
            ["4. Autonomous Nocturnal & Autonomic Analytics", "Nocturnal Somnography (0xE0), Apnea Hypoxia Index (0x61), Basal Thermal Cycle Tracking (0x88), and HRV Poincaré Lorenz scatter (0x80) process automatically."]
        ]
        s['takeaway'] = "15 health dial functions audited: 8 autonomous background streams, 3 user-initiated physical electrode tests, and a 1-second dynamic sample rate shift in Workout mode."
        break

with open('all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2)

print("[✔] Updated Slide 6 in all_slides_data.json")

# Now rebuild presentation
import sys
res = subprocess.run([sys.executable, 'build_presentation.py'], capture_output=True, text=True)
print(res.stdout)
if res.stderr:
    print("[stderr]", res.stderr)
