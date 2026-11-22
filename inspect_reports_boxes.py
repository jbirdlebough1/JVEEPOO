with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('id="pageReports"')
print("pageReports found at character:", pos)
# Find the end of pageReports or major subsections
end_pos = text.find('id="pageSetup"')
print("pageSetup found at character:", end_pos)
print("pageReports length:", end_pos - pos)

# Find major headings inside pageReports
sub = text[pos:end_pos]
import re
chart_boxes = re.findall(r'<div[^>]*class="[^"]*chart-box[^"]*"[^>]*id="([^"]+)"', sub)
print("Chart boxes inside pageReports:", chart_boxes)
