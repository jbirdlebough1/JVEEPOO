import hashlib

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    content = f.read()

# Sidebar replacement
old_nav = '''      <button id="navTabExport" class="lcars-pill pill-lilac" onclick="switchTab('export')" style="border-left: 5px solid #38bdf8;">
        <span class="tab-label">04-EXPORT</span>
      </button>
      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">05-MEAL PLANNER</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">06-REPORTS</span>
      </button>'''

new_nav = '''      <button id="navTabDietTrades" class="lcars-pill pill-green" onclick="switchTab('diettrades')">
        <span class="tab-label">04-MEAL PLANNER</span>
      </button>
      <button id="navTabReports" class="lcars-pill pill-blue" onclick="switchTab('reports')">
        <span class="tab-label">05-REPORTS</span>
      </button>
      <button id="navTabExport" class="lcars-pill pill-lilac" onclick="switchTab('export')" style="border-left: 5px solid #38bdf8;">
        <span class="tab-label">06-EXPORT</span>
      </button>'''

assert old_nav in content, 'old_nav not found'
content = content.replace(old_nav, new_nav)

# Cross-reference replacements
replacements = [
    ('VIEW 360° SYNCHRONIZED TALL CHART (TAB 04)', 'VIEW 360° SYNCHRONIZED TALL CHART (TAB 05)'),
    ('OPEN 360° SYNCHRONIZED TALL CHART (TAB 04)', 'OPEN 360° SYNCHRONIZED TALL CHART (TAB 05)'),
    ('🥗 OPEN MEAL BUILDER &amp; COACH (TAB 06)', '🥗 OPEN MEAL PLANNER &amp; COACH (TAB 04)'),
    ('SINGLE TALL SYNCHRONIZED MULTI-MEASUREMENT CHART (TAB 04).', 'SINGLE TALL SYNCHRONIZED MULTI-MEASUREMENT CHART (TAB 05).'),
    ('Click <strong><code>[ EXPORT CLINICAL CSV ]</code></strong> in Tab 04 to download', 'Click <strong><code>[ EXPORT CLINICAL CSV ]</code></strong> in Tab 06 to download'),
    ('📊 VIEW IN 8-LANE CHART (TAB 04)', '📊 VIEW IN 8-LANE CHART (TAB 05)'),
    ('timeline on Tab 04, you can click on any glucose spike', 'timeline on Tab 05, you can click on any glucose spike'),
    ('VIEW IN TALL CHART (TAB 04)', 'VIEW IN TALL CHART (TAB 05)'),
    ('📊 AUDIT % GOALS MET IN REPORTS (TAB 04)', '📊 AUDIT % GOALS MET IN REPORTS (TAB 05)'),
    ('📊 AUDIT % GOALS MET (TAB 04)', '📊 AUDIT % GOALS MET (TAB 05)'),
    ('Synchronized with Tab 05 Export', 'Synchronized with Tab 06 Export'),
    ('// Update Tab 05 Export displays', '// Update Tab 06 Export displays'),
    ('// 4. TAB 06: ADA TRADES (#pageDietTrades)', '// 4. TAB 04: MEAL PLANNER (#pageDietTrades)'),
    ('// 5. TAB 07: REPORTS (#pageReports)', '// 5. TAB 05: REPORTS (#pageReports)')
]

for old, new in replacements:
    assert old in content, f'Replacement target not found: {old}'
    content = content.replace(old, new)

# Div check
o = content.count('<div')
c = content.count('</div')
print(f'Div check: open={o}, close={c}, delta={o-c}')
assert o == c, f'Div mismatch: {o} != {c}'

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(content)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(content)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
assert h1 == h2, 'Hashes do not match!'
print(f'SUCCESS! Dual file updated with SHA256: {h1}')
