with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# 1. Update renderSvgCurveChart and renderSvgDualCurveChart with robust container ID resolution
target_curve_res = """function renderSvgCurveChart(containerId, data, options = {}) {
      const container = document.getElementById(containerId);
      if (!container) return;"""

rep_curve_res = """function renderSvgCurveChart(containerId, data, options = {}) {
      let container = document.getElementById(containerId);
      if (!container) {
        const altMap = {
          'repChartGlucose': 'curveTimelineGlucose',
          'repChartA1C': 'curveTimelineA1C',
          'repChartTemp': 'curveTimelineTemp',
          'repChartHR': 'curveTimelineHeartRate',
          'repChartHeartRate': 'curveTimelineHeartRate',
          'repChartBloodPressure': 'curveTimelineBP',
          'repChartBP': 'curveTimelineBP',
          'repChartSpO2': 'curveTimelineSpO2',
          'repChartPulseOximetry': 'curveTimelineSpO2',
          'repChartHRV': 'curveTimelineHRV',
          'repChartAutonomicHrv': 'curveTimelineHRV',
          'repChartSleep': 'curveTimelineSleep',
          'repChartSleepArchitecture': 'curveTimelineSleep',
          'repChartExercise': 'curveTimelineExercise',
          'repChartExerciseCadence': 'curveTimelineExercise'
        };
        if (altMap[containerId]) container = document.getElementById(altMap[containerId]);
      }
      if (!container) return;"""

if target_curve_res in text:
    text = text.replace(target_curve_res, rep_curve_res, 1)

target_dual_res = """function renderSvgDualCurveChart(containerId, data, options = {}) {
      const container = document.getElementById(containerId);
      if (!container) return;"""

rep_dual_res = """function renderSvgDualCurveChart(containerId, data, options = {}) {
      let container = document.getElementById(containerId);
      if (!container) {
        const altMap = {
          'repChartBP': 'curveTimelineBP',
          'repChartBloodPressure': 'curveTimelineBP'
        };
        if (altMap[containerId]) container = document.getElementById(altMap[containerId]);
      }
      if (!container) return;"""

if target_dual_res in text:
    text = text.replace(target_dual_res, rep_dual_res, 1)

# 2. Update renderReportsView to directly target curveTimeline IDs
replacements = [
    ('renderSvgCurveChart("repChartGlucose"', 'renderSvgCurveChart("curveTimelineGlucose"'),
    ('renderSvgCurveChart("repChartA1C"', 'renderSvgCurveChart("curveTimelineA1C"'),
    ('renderSvgCurveChart("repChartTemp"', 'renderSvgCurveChart("curveTimelineTemp"'),
    ('renderSvgCurveChart("repChartSleep"', 'renderSvgCurveChart("curveTimelineSleep"'),
    ('renderSvgCurveChart("repChartExercise"', 'renderSvgCurveChart("curveTimelineExercise"'),
    ('renderSvgCurveChart("repChartHR"', 'renderSvgCurveChart("curveTimelineHeartRate"'),
    ('renderSvgDualCurveChart("repChartBP"', 'renderSvgDualCurveChart("curveTimelineBP"'),
    ('renderSvgCurveChart("repChartSpO2"', 'renderSvgCurveChart("curveTimelineSpO2"'),
    ('renderSvgCurveChart("repChartHRV"', 'renderSvgCurveChart("curveTimelineHRV"')
]

for old_s, new_s in replacements:
    if old_s in text:
        text = text.replace(old_s, new_s)
        print(f"✓ Replaced {old_s} with {new_s}")

with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

print("Files saved with exact parity.")
