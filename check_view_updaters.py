with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

import re

# Find render360HealthView
pos_360 = html.find('function render360HealthView')
if pos_360 != -1:
    print("=== render360HealthView ===")
    print(html[pos_360:pos_360+1500])

# Find populateSetupFromDevice
pos_setup = html.find('function populateSetupFromDevice')
if pos_setup != -1:
    print("=== populateSetupFromDevice ===")
    print(html[pos_setup:pos_setup+1000])
