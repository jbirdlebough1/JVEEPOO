import os
import json

path = r"D:\veepoo\stock_dials\shape4_240x280\inventory.json"
if os.path.exists(path):
    with open(path, "r", encoding="utf-8") as f:
        inv = json.load(f)
    print(f"inventory.json has {len(inv)} items")
    for item in inv[:5]:
        print("  ", item)
else:
    print("inventory.json not found")

path_rep = r"D:\veepoo\stock_dials\shape4_240x280\analysis_report.json"
if os.path.exists(path_rep):
    with open(path_rep, "r", encoding="utf-8") as f:
        rep = json.load(f)
    print("\nanalysis_report.json:", rep.get("total_analyzed"), "magics:", rep.get("magics"))
