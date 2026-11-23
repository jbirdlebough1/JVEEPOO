import os

plan_path = r"D:\veepoo\docs\bfm_beta1_master_test_plan.md"
content = open(plan_path, "r", encoding="utf-8").read()

hdyf_test_step = """
#### Step 6B: Subjective Experiential Telemetry ("How Do You Feel?" HDYF)
1. On `cell.html` (or workstation console), locate the **"💭 HOW DO YOU FEEL?"** inquiry button.
2. Enter subjective response via voice or text (e.g., *"Energetic, satisfied after lunch, zero brain fog"*).
3. Select feeling category tags: `Energy: High`, `Clarity: Clear`, `Mood: Positive`.
4. Click **"Submit Assessment"**: verify timestamped entry is staged in mobile memory.
5. Perform Homecoming Dock: verify the subjective comment is synchronized to `server.py` and attached to the contemporaneous glucose reading in `telemetry.db`.
6. Inspect Tab 03 / Tab 04: verify subjective feeling marker appears on the 24-hour timeline."""

content = content.replace("#### Step 7: Dual External USB/SD Storage Backup Testing",
                          hdyf_test_step + "\n\n#### Step 7: Dual External USB/SD Storage Backup Testing")

# Add TEST-11 to RTM mapping table
rtm_old = """| **TEST-10** | Canonical Version String | Check 10 in gate suite | Verify `beta.00.01.0001` displayed in UI, API, and FHIR. |"""
rtm_new = """| **TEST-10** | Canonical Version String | Check 10 in gate suite | Verify `beta.00.01.0001` displayed in UI, API, and FHIR. |
| **TEST-11** | Subjective Experiential Telemetry (HDYF) | `test_beta1_e2e.py` | Verify mobile HDYF prompt, Wi-Fi sync, and timeline correlation. |"""

content = content.replace(rtm_old, rtm_new)

with open(plan_path, "w", encoding="utf-8") as f:
    f.write(content)

print("[✔] Updated Master Test Plan with HDYF Step 6B and TEST-11.")
