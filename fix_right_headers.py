import json

slides_file = r"D:/veepoo/all_slides_data.json"
with open(slides_file, "r", encoding="utf-8") as f:
    slides = json.load(f)

for s in slides:
    sid = s.get("id") or int(s.get("num", 0))
    if sid in [14, 15]:
        s["right_header"] = s["right_header"].replace("TAB 01", "TAB 02").replace("01-VITALS", "02-VITALS")
        s["right_html"] = s["right_html"].replace("TAB 01", "TAB 02").replace("01-VITALS", "02-VITALS")
    elif sid in [16, 17, 18]:
        s["right_header"] = s["right_header"].replace("TAB 02", "TAB 03").replace("02-CARDIO", "03-CARDIO")
        s["right_html"] = s["right_html"].replace("TAB 02", "TAB 03").replace("02-CARDIO", "03-CARDIO")
    elif sid == 19:
        s["right_header"] = s["right_header"].replace("TAB 03", "TAB 04").replace("03-METABOLIC", "04-METABOLIC")
        s["right_html"] = s["right_html"].replace("TAB 03", "TAB 04").replace("03-METABOLIC", "04-METABOLIC")
    elif sid == 28:
        s["right_header"] = s["right_header"].replace("TAB 04", "TAB 05").replace("04-REPORTS", "05-EXPORT")
        s["right_html"] = s["right_html"].replace("TAB 04", "TAB 05").replace("04-REPORTS", "05-EXPORT")
    elif sid in [43, 44, 45]:
        s["right_header"] = s["right_header"].replace("TAB 11", "TAB 10").replace("11-MEDICATION", "10-MEDICATION")
        s["right_html"] = s["right_html"].replace("TAB 11", "TAB 10").replace("11-MEDICATION", "10-MEDICATION")
    elif sid in [46, 47]:
        s["right_header"] = s["right_header"].replace("TAB 12", "TAB 01").replace("12-FULL SCAN", "01-FULL SCAN")
        s["right_html"] = s["right_html"].replace("TAB 12", "TAB 01").replace("12-FULL SCAN", "01-FULL SCAN")

with open(slides_file, "w", encoding="utf-8") as f:
    json.dump(slides, f, indent=2, ensure_ascii=False)

print("Updated right_html and right_header across slides!")
