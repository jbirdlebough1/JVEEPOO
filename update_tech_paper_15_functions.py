# update_tech_paper_15_functions.py
import subprocess
import os

with open('docs/generate_tech_protocol_paper.py', 'r', encoding='utf-8') as f:
    text = f.read()

matrix_section = """
  <h3>D. Master 15-Function Health Dial &amp; Remote Command Execution Matrix</h3>
  <p>
    The table below provides an exhaustive engineering and clinical mapping of all 15 health functions accessible from the physical smartwatch dial. It specifies transducer mechanics, remote query/trigger opcodes, patient interaction requirements, and dynamic sampling rate shifts (e.g. resting periodic vs. high-frequency 1-second workout telemetry).
  </p>

  <table>
    <thead>
      <tr>
        <th style="width: 4%;">#</th>
        <th style="width: 14%;">Dial Function</th>
        <th style="width: 18%;">Transducer / Sensor Hardware</th>
        <th style="width: 14%;">Execution Modality</th>
        <th style="width: 11%;">Remote BLE Opcode</th>
        <th style="width: 19%;">Sampling Rate &amp; Frequency Shift</th>
        <th style="width: 20%;">Clinical Telemetry Harvested</th>
      </tr>
    </thead>
    <tbody>
      <tr>
        <td><strong>01</strong></td>
        <td><strong>Lead-I ECG</strong></td>
        <td>Bipolar differential ECG circuit (titanium case bottom + right-side bezel electrode)</td>
        <td><span class="badge badge-amber">User-Initiated (On Watch)</span></td>
        <td><code>0x19</code> / <code>0x98</code></td>
        <td>250 Hz continuous wave for 30s contact</td>
        <td>Lead-I waveform, QTc interval (ms), RR variability, Arrhythmia screening</td>
      </tr>
      <tr>
        <td><strong>02</strong></td>
        <td><strong>Autonomic Stress</strong></td>
        <td>PPG pulse interval spectral decomposition (LF: 0.04-0.15Hz vs HF: 0.15-0.40Hz)</td>
        <td><span class="badge badge-blue">Autonomous + Spot</span></td>
        <td><code>0x71</code></td>
        <td>Periodic: Every 30 min / Spot: 60s read</td>
        <td>Stress index (1-100), Sympathovagal LF/HF ratio, vagal resilience score</td>
      </tr>
      <tr>
        <td><strong>03</strong></td>
        <td><strong>Blood Pressure (BP)</strong></td>
        <td>Multi-wavelength PPG tonometric pulse transit time &amp; wave contour analysis</td>
        <td><span class="badge badge-blue">Autonomous + Remote</span></td>
        <td><code>0x01</code> / <code>0x12</code></td>
        <td>Periodic: Every 5-10 min / Spot: 45s on-demand</td>
        <td>Systolic BP (mmHg), Diastolic BP (mmHg), MAP, Pulse Pressure</td>
      </tr>
      <tr>
        <td><strong>04</strong></td>
        <td><strong>Thermometry (Temp)</strong></td>
        <td>Dual NTC thermistors (epidermal skin contact + internal chassis ambient)</td>
        <td><span class="badge badge-green">100% Autonomous</span></td>
        <td><code>0x78</code></td>
        <td>Continuous: Harvested every 5 minutes</td>
        <td>Epidermal skin temp (°F/°C), Core temperature proxy, pyretic alerts</td>
      </tr>
      <tr>
        <td><strong>05</strong></td>
        <td><strong>Continuous Glucose</strong></td>
        <td>Dual-wavelength optical absorption spectroscopy (525nm, 660nm, 940nm)</td>
        <td><span class="badge badge-green">100% Autonomous</span></td>
        <td><code>0x75</code> / <code>0x74</code></td>
        <td>Continuous: Autonomous 5-min epoch stream</td>
        <td>Blood glucose (mg/dL / mmol/L), postprandial velocity, TIR %, eA1C %</td>
      </tr>
      <tr>
        <td><strong>06</strong></td>
        <td><strong>MET Energy</strong></td>
        <td>3-Axis MEMS accelerometer vector magnitude + continuous PPG pulse rate elevation</td>
        <td><span class="badge badge-green">100% Autonomous</span></td>
        <td><code>0x07</code> / <code>0x08</code></td>
        <td>Continuous: Integrated per-minute into intensity bins</td>
        <td>Cumulative daily MET-hours, ADA activity compliance (&ge;28 MET-hrs/day)</td>
      </tr>
      <tr>
        <td><strong>07</strong></td>
        <td><strong>HRV &amp; Poincaré</strong></td>
        <td>Inter-beat interval (RR tachogram) via high-frequency optical PPG peak detection</td>
        <td><span class="badge badge-blue">Autonomous Nocturnal</span></td>
        <td><code>0x80</code> / <code>0x81</code></td>
        <td>Nocturnal: Continuous 00:00-07:00 / Spot: 60s</td>
        <td>SDNN (ms), RMSSD (ms), 2D Poincaré / Lorenz plot geometry (comet vs torpedo)</td>
      </tr>
      <tr>
        <td><strong>08</strong></td>
        <td><strong>Body Comp (BIA)</strong></td>
        <td>Micro-current bioelectrical impedance transducer (resistance R + reactance Xc)</td>
        <td><span class="badge badge-amber">User-Initiated (On Watch)</span></td>
        <td><code>0x85</code></td>
        <td>50 kHz AC signal sweep for 15s contact</td>
        <td>Body Fat %, Skeletal Muscle %, Total Body Water %, Perfusion Index (PI)</td>
      </tr>
      <tr>
        <td><strong>09</strong></td>
        <td><strong>Guided Breath</strong></td>
        <td>Haptic LRA vibration motor pacing with concurrent optical pulse deceleration tracking</td>
        <td><span class="badge badge-amber">User-Initiated (On Watch)</span></td>
        <td><code>0x87</code></td>
        <td>0.1 Hz resonant pacing (4s inhale / 6s exhale)</td>
        <td>Respiratory sinus arrhythmia (RSA), pulse rate drop, vagal tone recovery</td>
      </tr>
      <tr>
        <td><strong>10</strong></td>
        <td><strong>Cycle Tracking</strong></td>
        <td>Longitudinal nocturnal basal skin temperature nadir &amp; biphasic thermal shift</td>
        <td><span class="badge badge-blue">Autonomous + Sync</span></td>
        <td><code>0x88</code></td>
        <td>Daily nocturnal baseline temperature check</td>
        <td>Cycle phase (Follicular, Ovulatory, Luteal), fertile window prediction</td>
      </tr>
      <tr>
        <td><strong>11</strong></td>
        <td><strong>Somnography (Sleep)</strong></td>
        <td>Triaxial actigraphy motion filtering + PPG pulse variability + SpO2 hypoxia monitoring</td>
        <td><span class="badge badge-green">100% Autonomous</span></td>
        <td><code>0xE0 0x00 / 0x01</code></td>
        <td>Continuous 20:00 to 10:00 (5-min epoch blocks)</td>
        <td>Deep, Light, REM, Awake minutes; Sleep efficiency %; Hypopnea Index (AHI)</td>
      </tr>
      <tr>
        <td><strong>12</strong></td>
        <td><strong>Pedometer (Activity)</strong></td>
        <td>3-Axis MEMS digital accelerometer with 10-step continuous stride verification</td>
        <td><span class="badge badge-green">100% Autonomous</span></td>
        <td><code>0x07</code> / <code>0x08</code></td>
        <td>Real-time continuous stride integration</td>
        <td>24-hr step volume, active distance (km/mi), caloric burn (kcal), active minutes</td>
      </tr>
      <tr>
        <td><strong>13</strong></td>
        <td><strong>Pulse Oximetry (SpO2)</strong></td>
        <td>Dual-wavelength reflectance spectrophotometry (660nm red Hb vs 940nm NIR HbO2)</td>
        <td><span class="badge badge-blue">Autonomous + Spot</span></td>
        <td><code>0x60</code> / <code>0x61</code></td>
        <td>Nocturnal: Every 5 min / Spot: 30s read</td>
        <td>SpO2 percentage (%), Oxygen Desaturation Index (ODI &lt;95% events/hr)</td>
      </tr>
      <tr>
        <td><strong>14</strong></td>
        <td><strong>Continuous Heart Rate</strong></td>
        <td>525nm green optical PPG sensor monitoring microvascular capillary bed</td>
        <td><span class="badge badge-green">100% Autonomous</span></td>
        <td><code>0x02</code> / <code>0x09</code></td>
        <td>Continuous: Harvested every 5 min</td>
        <td>Resting HR (BPM), peak diurnal HR, bradycardia (&lt;50) &amp; tachycardia (&gt;100) timestamps</td>
      </tr>
      <tr style="background: #fffbeb;">
        <td><strong>15</strong></td>
        <td><strong>Workout (Sports Mode)</strong></td>
        <td>High-rate IMU motion dynamics + continuous 1-second PPG pulse rate stream</td>
        <td><span class="badge badge-amber">Watch or Workstation</span></td>
        <td><code>0x06 0x01 / 0x02</code></td>
        <td><strong style="color: #b91c1c;">DYNAMIC SAMPLE RATE SHIFT:</strong> Switches from 5-min periodic to <strong>1-SECOND CONTINUOUS (1 Hz)</strong> high-frequency telemetry!</td>
        <td>1-second HR stream, HR training zones (Fat Burn, Aerobic, VO2 Max), cadence, HRR recovery</td>
      </tr>
    </tbody>
  </table>
"""

target = "  <h2>5. Hardware Configuration Matrix (Register 0xB8 Reference)</h2>"
assert target in text, "Target Section 5 not found"
text = text.replace(target, matrix_section + "\n" + target, 1)

with open('docs/generate_tech_protocol_paper.py', 'w', encoding='utf-8') as f:
    f.write(text)

print("[✔] Successfully injected 15-Function Matrix into generate_tech_protocol_paper.py")

# Now run the generation script
import sys
res = subprocess.run([sys.executable, 'docs/generate_tech_protocol_paper.py'], capture_output=True, text=True)
print(res.stdout)
if res.stderr:
    print("[stderr]", res.stderr)
