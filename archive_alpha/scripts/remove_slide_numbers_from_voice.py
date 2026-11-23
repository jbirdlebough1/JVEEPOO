import json
import re

with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
    slides = json.load(f)

replacements = {
    "05": [
        ("On this slide, we cite six landmark publications", "Here, we cite six landmark publications")
    ],
    "10": [
        ("Slide 7 establishes our clinical safe harbor.", "Our clinical safe harbor standardizes on 60 to 200 milligrams per deciliter.")
    ],
    "11": [
        ("In Slide 8, we examine clinical calibration.", "Next, we examine clinical calibration and baseline anchoring.")
    ],
    "12": [
        ("In Slide 9, we enforce Private Mode.", "Here, we enforce strict Private Mode telemetry.")
    ],
    "13": [
        ("Slide 10 details background acquisition.", "Continuous background telemetry operates around the clock.")
    ],
    "20": [
        ("Slide 17 covers one of the most powerful tools in diabetes maintenance: GLUT4 translocation.", "Here, we focus on one of the most powerful tools in diabetes maintenance: GLUT4 translocation.")
    ],
    "21": [
        ("In Slide 18, we introduce the GLUT4 Exercise Journal.", "Now we examine the GLUT4 Exercise Journal.")
    ],
    "22": [
        ("Slide 19 covers the Macronutrient Meal Logger.", "Next, we review the Macronutrient Meal Logger.")
    ],
    "23": [
        ("In Slide 20, we explore the ADA 1-to-1 Carbohydrate Trades and Clinical Nutrition Engine.", "Here, we explore the ADA 1-to-1 Carbohydrate Trades and Clinical Nutrition Engine.")
    ],
    "25": [
        ("Slide 22 addresses morning fasting numbers.", "Here, we address morning fasting numbers and the dawn surge.")
    ],
    "26": [
        ("Slide 23 connects sleep quality directly to diabetes.", "Next, we connect sleep architecture directly to metabolic regulation.")
    ],
    "27": [
        ("Slide 24 highlights habit correlation.", "Here, we examine multi-parameter habit correlation.")
    ],
    "28": [
        ("Slide 25 features our flagship clinical bridge: direct Epic Electronic Health Record integration.", "Displayed on screen is our flagship clinical bridge: direct Epic Electronic Health Record integration.")
    ],
    "30": [
        ("Slide 27 details the 8 stacked lanes.", "Displayed on screen are the eight stacked clinical telemetry lanes.")
    ],
    "31": [
        ("Slide 28 introduces the vertical scrubber line.", "Here, we introduce the synchronized vertical scrubber HUD.")
    ],
    "32": [
        ("Slide 29 highlights Click-to-Pop Clinical Cards.", "Next, we examine Click-to-Pop Clinical Audit Cards.")
    ],
    "33": [
        ("Slide 30 is crucial for doctor visits: Pinning Medication Changes.", "Pinning medication change milestones is crucial for clinical consultations.")
    ],
    "34": [
        ("Slide thirty-one focuses on the individual Daily Twenty-Four-Hour Continuous Glucose Curve.", "Displayed on screen is the individual Daily Twenty-Four-Hour Continuous Glucose Curve.")
    ],
    "35": [
        ("Slide thirty-two examines the Daily Twenty-Four-Hour Estimated A1C Projection Curve.", "Here, we examine the Daily Twenty-Four-Hour Estimated A1C Projection Curve.")
    ],
    "36": [
        ("Slide thirty-three presents the Twenty-Four-Hour Body Temperature Matrix, contrasting calculated core body temperature against wrist epidermal contact temperature.", "Displayed on screen is the Twenty-Four-Hour Body Temperature Matrix, contrasting calculated core body temperature against wrist epidermal contact temperature.")
    ],
    "37": [
        ("Slide thirty-four covers the Twenty-Four-Hour Ambulatory Blood Pressure Dual-Band Chart.", "Next, we examine the Twenty-Four-Hour Ambulatory Blood Pressure Dual-Band Chart.")
    ],
    "38": [
        ("Slide thirty-five inspects the Nocturnal SpO2 and Obstructive Sleep Apnea Hypoxia Dips Matrix.", "Here, we inspect the Nocturnal SpO2 and Obstructive Sleep Apnea Hypoxia Dips Matrix.")
    ],
    "39": [
        ("Slide thirty-six details the Twenty-Four-Hour Heart Rate and Five-Zone Cardiac Load Chart.", "Displayed on screen is the Twenty-Four-Hour Heart Rate and Five-Zone Cardiac Load Chart.")
    ],
    "40": [
        ("Slide thirty-seven examines Autonomic Heart Rate Variability and Lorenz Cardiac Scatter Dynamics.", "Here, we examine Autonomic Heart Rate Variability and Lorenz Cardiac Scatter Dynamics.")
    ],
    "41": [
        ("Slide thirty-eight presents the Nocturnal Sleep Duration and Circadian Hypnogram.", "Next, we examine the Nocturnal Sleep Duration and Circadian Hypnogram.")
    ],
    "42": [
        ("Slide thirty-nine completes the individual chart suite with Ambulatory Physical Activity and Step Density.", "Completing our individual chart suite is Ambulatory Physical Activity and Step Density.")
    ],
    "44": [
        ("Slide 32 highlights our dual intake layout.", "Displayed on screen is our dual medication intake layout.")
    ],
    "45": [
        ("Slide 33 details medication editing.", "Here, we detail medication editing and dosage adjustment.")
    ],
    "47": [
        ("Slide 35 displays the Full Scan results dashboard.", "Displayed on screen is the Full Scan diagnostic telemetry dashboard.")
    ]
}

modified_slides = []

for s in slides:
    num = s['num']
    if num in replacements:
        for old, new in replacements[num]:
            if old in s['narration']:
                s['narration'] = s['narration'].replace(old, new)
                modified_slides.append(num)
                print(f"Slide {num}: Replaced '{old}' with '{new}'")
            else:
                print(f"WARNING: Target not found in Slide {num}: '{old}'")

with open('D:/veepoo/all_slides_data.json', 'w', encoding='utf-8') as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print(f"Updated {len(set(modified_slides))} slides in all_slides_data.json!")
