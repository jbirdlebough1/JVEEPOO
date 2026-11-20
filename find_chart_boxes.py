with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'id="boxChartGlucose"' in l:
        print(f"boxChartGlucose on line {i+1}")
    if 'id="boxChartHRV"' in l or 'id="boxChartSpO2"' in l:
        print(f"{l.strip()} on line {i+1}")
