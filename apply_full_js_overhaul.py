import re

with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

# -------------------------------------------------------------------------
# 1. REPLACE renderSvgCurveChart
# -------------------------------------------------------------------------
old_renderSvg_pattern = r'function renderSvgCurveChart\(containerId, data, options = \{\}\) \{.*?\n    \}'
# Let's find exact bounds of renderSvgCurveChart
start_svg = text.find('function renderSvgCurveChart(containerId, data, options = {}) {')
assert start_svg != -1, "start_svg not found!"

# Find where renderSvgCurveChart ends (before function setTallChartTimeframe or next top-level function)
end_svg = text.find('function setTallChartTimeframe(', start_svg)
assert end_svg != -1, "end_svg not found!"

# The portion before setTallChartTimeframe contains renderSvgCurveChart and any intermediate comment
svg_chunk = text[start_svg:end_svg]

new_svg_func = """function renderSvgCurveChart(containerId, data, options = {}) {
      const container = document.getElementById(containerId);
      if (!container) return;

      const validPoints = (data || []).filter(d => d.val !== null && d.val !== undefined && !d.isGap && d.val > 0);
      if (!data || data.length === 0 || validPoints.length === 0) {
        container.innerHTML = `
          <div style="display: flex; flex-direction: column; align-items: center; justify-content: center; height: 100%; min-height: 180px; color: #64748b; font-family: var(--lcars-mono); text-align: center; border: 1.5px dashed #cbd5e1; border-radius: 6px; background: rgba(248, 250, 252, 0.85); padding: 16px;">
            <div style="font-size: 1rem; font-weight: 800; color: #0284c7; letter-spacing: 0.05em; margin-bottom: 4px;">
              ⚡ NO SENSOR TELEMETRY RECORDED FOR THIS TIMEFRAME
            </div>
            <div style="font-size: 0.78rem; color: #64748b;">
              Connect watch or sync sensor data to generate clinical curve. Zero synthetic interpolation.
            </div>
          </div>`;
        return;
      }

      const W = 800;
      const H = 225;
      const padLeft = 60;
      const padRight = 35;
      const padTop = 26;
      const padBottom = 50;
      const plotW = W - padLeft - padRight;
      const plotH = H - padTop - padBottom;

      const minY = options.minY !== undefined ? options.minY : Math.min(...data.map(d => d.val)) * 0.9;
      const maxY = options.maxY !== undefined ? options.maxY : Math.max(...data.map(d => d.val)) * 1.1;
      const rangeY = (maxY - minY) || 1;

      const strokeColor = options.strokeColor || "var(--lcars-banana)";
      const gradId = "grad_" + containerId;
      const unit = options.unit || "";

      function getMinuteFromLabel(lbl) {
        if (!lbl) return null;
        const m = lbl.match(/^(\d{2}):(\d{2})/);
        if (m) return parseInt(m[1]) * 60 + parseInt(m[2]);
        return null;
      }

      const hasTimeLabels = data.length > 0 && getMinuteFromLabel(data[0].label) !== null;
      const n = data.length;
      const points = data.map((d, i) => {
        let x;
        if (hasTimeLabels) {
          const min = getMinuteFromLabel(d.label) || 0;
          x = padLeft + (min / 1440.0) * plotW;
        } else {
          x = padLeft + (n > 1 ? (i / (n - 1)) * plotW : plotW / 2);
        }
        const y = padTop + plotH - ((d.val - minY) / rangeY) * plotH;
        return { x, y, label: d.label, val: d.val };
      });

      // Segment builder: breaks curve across gaps
      let curvePaths = "";
      let areaPaths = "";
      let gapRectsHtml = "";
      let currentSegment = [];

      function flushCurveSegment() {
        if (currentSegment.length > 0) {
          const first = currentSegment[0];
          const last = currentSegment[currentSegment.length - 1];
          const segPoints = currentSegment.map(p => `${p.x.toFixed(1)},${p.y.toFixed(1)}`).join(" ");
          curvePaths += `<polyline points="${segPoints}" fill="none" stroke="${strokeColor}" stroke-width="2.8" stroke-linecap="round" stroke-linejoin="round" style="filter: drop-shadow(0 2px 4px rgba(0,0,0,0.15));"/>`;
          const segArea = `${first.x.toFixed(1)},${padTop + plotH} ` + segPoints + ` ${last.x.toFixed(1)},${padTop + plotH}`;
          areaPaths += `<polygon points="${segArea}" fill="url(#${gradId})"/>`;
          currentSegment = [];
        }
      }

      points.forEach((p, idx) => {
        if (p.isGap || p.val === null || (p.val === 0 && !options.allowZeroValue)) {
          flushCurveSegment();
          const prevX = idx > 0 ? points[idx - 1].x : padLeft;
          const nextX = idx < points.length - 1 ? points[idx + 1].x : padLeft + plotW;
          const gStart = (p.x + prevX) / 2;
          const gEnd = (p.x + nextX) / 2;
          const gW = Math.max(30, gEnd - gStart);
          gapRectsHtml += `
            <rect x="${gStart.toFixed(1)}" y="${padTop + 4}" width="${gW.toFixed(1)}" height="${plotH - 8}" fill="rgba(241, 245, 249, 0.75)" stroke="#cbd5e1" stroke-dasharray="3 3" rx="4"/>
            <text x="${p.x.toFixed(1)}" y="${padTop + 16}" text-anchor="middle" fill="#64748b" font-family="var(--lcars-mono)" font-size="8.5" font-weight="900">⚡ CHARGING GAP (0 DATA)</text>
          `;
        } else {
          currentSegment.push(p);
        }
      });
      flushCurveSegment();

      let refLinesHtml = "";
      if (options.referenceLines) {
        options.referenceLines.forEach((ref, rIdx) => {
          if (ref.val >= minY && ref.val <= maxY) {
            const refY = padTop + plotH - ((ref.val - minY) / rangeY) * plotH;
            const isAlternate = (rIdx % 2 === 1);
            const anchor = isAlternate ? "start" : "end";
            const textX = isAlternate ? (padLeft + 8) : (padLeft + plotW - 8);
            refLinesHtml += `
              <line x1="${padLeft}" y1="${refY.toFixed(1)}" x2="${padLeft + plotW}" y2="${refY.toFixed(1)}" 
                    stroke="${ref.color || '#4f5b66'}" stroke-dasharray="4 4" stroke-width="1.5" opacity="0.85"/>
              <text x="${textX}" y="${(refY - 5).toFixed(1)}" text-anchor="${anchor}" 
                    fill="${ref.color || '#0f172a'}" font-family="var(--lcars-mono)" font-size="10.5" font-weight="800"
                    style="paint-order: stroke fill; stroke: #ffffff; stroke-width: 4px; stroke-linejoin: round;">${ref.label}</text>
            `;
          }
        });
      }

      // X-Axis Time Scale: Clear, Non-Colliding Grid Ticks
      let xAxisHtml = "";
      if (hasTimeLabels) {
        // Draw standard 4-hour major intervals: 00:00, 04:00, 08:00, 12:00, 16:00, 20:00, 24:00
        const majorTicks = [0, 4, 8, 12, 16, 20, 24];
        majorTicks.forEach(hr => {
          const x = padLeft + (hr / 24.0) * plotW;
          const label = hr === 24 ? "24:00" : `${hr.toString().padStart(2, '0')}:00`;
          xAxisHtml += `
            <line x1="${x.toFixed(1)}" y1="${padTop + plotH}" x2="${x.toFixed(1)}" y2="${padTop + plotH + 5}" stroke="#94a3b8" stroke-width="1.5"/>
            <text x="${x.toFixed(1)}" y="${padTop + plotH + 18}" text-anchor="middle" fill="#475569" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${label}</text>
          `;
        });
      } else {
        const minSpacing = 50;
        let lastLabeledX = -999;
        points.forEach((p, idx) => {
          if (p.x - lastLabeledX >= minSpacing || idx === 0 || idx === n - 1) {
            lastLabeledX = p.x;
            xAxisHtml += `
              <line x1="${p.x.toFixed(1)}" y1="${padTop + plotH}" x2="${p.x.toFixed(1)}" y2="${padTop + plotH + 5}" stroke="#94a3b8" stroke-width="1.2"/>
              <text x="${p.x.toFixed(1)}" y="${padTop + plotH + 18}" text-anchor="middle" fill="#475569" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${p.label}</text>
            `;
          }
        });
      }

      // Points & Value Badges: Render clean dots and staggered legible values
      let pointsHtml = "";
      let lastValX = -999;
      points.forEach((p, idx) => {
        if (p.isGap || p.val === null) return;
        const showVal = (p.x - lastValX >= 36) || idx === 0 || idx === n - 1;
        if (showVal) lastValX = p.x;
        const valY = (p.y < padTop + 16) ? (p.y + 16) : (p.y - 8);

        pointsHtml += `
          <g>
            <circle class="plot-point" cx="${p.x.toFixed(1)}" cy="${p.y.toFixed(1)}" r="4" 
                    fill="${strokeColor}" stroke="#ffffff" stroke-width="2">
              <title>${p.label}: ${p.val} ${unit}</title>
            </circle>
            ${showVal ? `
              <rect x="${(p.x - 18).toFixed(1)}" y="${(valY - 10).toFixed(1)}" width="36" height="13" fill="rgba(255,255,255,0.9)" stroke="#e2e8f0" stroke-width="0.8" rx="2"/>
              <text x="${p.x.toFixed(1)}" y="${valY.toFixed(1)}" text-anchor="middle" 
                    fill="${strokeColor}" font-family="var(--lcars-mono)" font-size="9.5" font-weight="900">${p.val}${options.valSuffix || ''}</text>
            ` : ''}
          </g>
        `;
      });

      const svgHtml = `
        <svg viewBox="0 0 ${W} ${H}" preserveAspectRatio="none" style="display: block; width: 100%; height: 100%;">
          <defs>
            <linearGradient id="${gradId}" x1="0" y1="0" x2="0" y2="1">
              <stop offset="0%" stop-color="${strokeColor}" stop-opacity="0.32"/>
              <stop offset="100%" stop-color="${strokeColor}" stop-opacity="0.02"/>
            </linearGradient>
          </defs>

          <!-- Chart Area Background & Grid -->
          <rect x="${padLeft}" y="${padTop}" width="${plotW}" height="${plotH}" fill="#ffffff" stroke="#cbd5e1" stroke-width="1.5" rx="4"/>
          <line x1="${padLeft}" y1="${padTop + plotH/2}" x2="${padLeft + plotW}" y2="${padTop + plotH/2}" stroke="#f1f5f9" stroke-dasharray="2 2" stroke-width="1"/>

          <!-- Reference Target Lines -->
          ${refLinesHtml}

          <!-- Watch Charging Gaps -->
          ${gapRectsHtml}

          <!-- Area & Line Curves -->
          ${areaPaths}
          ${curvePaths}

          <!-- Data Points -->
          ${pointsHtml}

          <!-- X-Axis Time Scale -->
          ${xAxisHtml}

          <!-- Y-Axis Labels -->
          <text x="${padLeft - 8}" y="${padTop + 10}" text-anchor="end" fill="#64748b" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${Math.round(maxY)} ${unit}</text>
          <text x="${padLeft - 8}" y="${padTop + plotH}" text-anchor="end" fill="#64748b" font-family="var(--lcars-mono)" font-size="10" font-weight="800">${Math.round(minY)} ${unit}</text>
        </svg>
      `;

      container.innerHTML = svgHtml;
    }
\n    """

text = text[:start_svg] + new_svg_func + text[end_svg:]
print("renderSvgCurveChart replaced successfully.")

# -------------------------------------------------------------------------
# 2. UPDATE setTallChartTimeframe & getTallSynchronizedDataset
# -------------------------------------------------------------------------
old_tall_tf = """    function setTallChartTimeframe(tf) {
      soundButtonPress();
      currentTallChartTimeframe = tf;
      ['btnTallDaily', 'btnTallWeekly', 'btnTallYearly'].forEach(id => {
        const b = document.getElementById(id);
        if (b) {
          b.className = 'lcars-btn btn-blue';
        }
      });
      if (tf === 'daily') {
        const b = document.getElementById('btnTallDaily'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === 'weekly') {
        const b = document.getElementById('btnTallWeekly'); if (b) b.className = 'lcars-btn btn-banana';
      } else {
        const b = document.getElementById('btnTallYearly'); if (b) b.className = 'lcars-btn btn-banana';
      }
      renderTallSynchronizedChart(tf);
    }"""

new_tall_tf = """    function setTallChartTimeframe(tf) {
      soundButtonPress();
      currentTallChartTimeframe = tf;
      ['btnTallDaily', 'btnTall2Day', 'btnTallWeekly', 'btnTallYearly'].forEach(id => {
        const b = document.getElementById(id);
        if (b) {
          b.className = 'lcars-btn btn-blue';
        }
      });
      if (tf === 'daily') {
        const b = document.getElementById('btnTallDaily'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === '2day') {
        const b = document.getElementById('btnTall2Day'); if (b) b.className = 'lcars-btn btn-banana';
      } else if (tf === 'weekly') {
        const b = document.getElementById('btnTallWeekly'); if (b) b.className = 'lcars-btn btn-banana';
      } else {
        const b = document.getElementById('btnTallYearly'); if (b) b.className = 'lcars-btn btn-banana';
      }
      renderTallSynchronizedChart(tf);
    }"""

assert old_tall_tf in text, "old_tall_tf not found!"
text = text.replace(old_tall_tf, new_tall_tf, 1)

print("setTallChartTimeframe updated successfully.")

with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(text)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(text)
