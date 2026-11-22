import os, re

def audit_charts(html_path):
    with open(html_path, 'r', encoding='utf-8') as f:
        html = f.read()

    print(f"=== Auditing {html_path} ===")
    # Look for chart containers and SVG styling
    chart_ids = [
        'tallChartSvgContainer', 'svgTallMaster', 'boxChartHRV', 'svgLorenzScatter',
        'boxExerciseMetabolicCorrelation', 'svgStepGlucoseCorrelator', 'svgHBand48Slots',
        'curveTimelineGlucose', 'curveTimelineA1C', 'curveTimelineTemp',
        'curveTimelineHeartRate', 'curveTimelineBP', 'curveTimelineSpO2',
        'curveTimelineHRV', 'curveTimelineSleep', 'curveTimelineExercise',
        'tallHudRibbon'
    ]
    
    for cid in chart_ids:
        pos = html.find(cid)
        if pos != -1:
            snippet = html[max(0, pos-60):min(len(html), pos+140)]
            print(f"--- ID: {cid} ---")
            print(snippet.replace('\n', ' '))
        else:
            print(f"--- ID: {cid} NOT FOUND ---")

if __name__ == '__main__':
    audit_charts('D:/veepoo/w11_htm_app/index.html')
