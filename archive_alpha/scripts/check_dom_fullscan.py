with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# Let's check opening and closing tags from <main class="lcars-workspace"> to pageFullScan
pos_main = html.find('<main class="lcars-workspace">')
pos_fullscan = html.find('id="pageFullScan"')
pos_vitals = html.find('id="pageVitals"')
pos_cardio = html.find('id="pageCardio"')

print(f"pos_main: {pos_main}")
print(f"pos_fullscan: {pos_fullscan}")
print(f"pos_vitals: {pos_vitals}")
print(f"pos_cardio: {pos_cardio}")

# Let's inspect all tags between pos_main and pos_fullscan
print("\n--- Between pos_main and pos_fullscan ---")
print(html[pos_main:pos_fullscan + 100])

# Now let's inspect the entire content of #pageFullScan from its opening tag to its closing tag
end_fullscan = html.find('<!-- =====================================================================\n           TAB 02: VITALS', pos_fullscan)
if end_fullscan == -1:
    end_fullscan = html.find('id="pageVitals"', pos_fullscan)
print(f"end_fullscan: {end_fullscan}")

fullscan_html = html[pos_fullscan:end_fullscan]
print(f"Length of pageFullScan HTML: {len(fullscan_html)}")
print("First 300 chars of pageFullScan:")
print(fullscan_html[:300])
print("Last 300 chars of pageFullScan:")
print(fullscan_html[-300:])
