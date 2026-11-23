import os
import shutil

aliases = {
    "lcars_tab01_fullscan.png": ["lcars_tab_vitals.png", "lcars_tab_fullscan.png"],
    "lcars_tab02_meal_planner.png": ["lcars_tab_diet.png", "lcars_tab_diettrades.png", "lcars_tab_meal_planner.png"],
    "lcars_tab03_cardio.png": ["lcars_tab_metabolic.png", "lcars_tab_cardio.png"],
    "lcars_tab04_reports.png": ["lcars_tab_reports.png"],
    "lcars_tab05_export.png": ["lcars_tab_export.png"],
    "lcars_tab06_learning.png": ["lcars_tab_learning.png"],
    "lcars_tab07_help.png": ["lcars_tab_help.png"],
    "lcars_tab08_medication.png": ["lcars_tab_medication.png"],
    "lcars_tab09_goals.png": ["lcars_tab_goals.png"],
    "lcars_tab10_config.png": ["lcars_tab_setup.png", "lcars_tab_config.png"],
    "lcars_tab11_operations.png": ["lcars_tab_operations.png"],
    "lcars_tab12_datalog.png": ["lcars_tab_log.png", "lcars_tab_datalog.png"],
    "lcars_tab13_watchfaces.png": ["lcars_tab_watchfaces.png"]
}

dirs = [
    r"D:\veepoo\presentation\images",
    r"D:\veepoo\slides\images",
    r"D:\veepoo\images"
]

for d in dirs:
    if not os.path.exists(d): continue
    for src_file, dest_list in aliases.items():
        src_path = os.path.join(d, src_file)
        if os.path.exists(src_path):
            for dest_file in dest_list:
                dest_path = os.path.join(d, dest_file)
                shutil.copyfile(src_path, dest_path)
                print(f"Copied {src_file} -> {dest_file} in {d}")

print("All slide image aliases populated successfully.")
