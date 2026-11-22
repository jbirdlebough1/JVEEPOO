with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('function renderReportsView')
end_idx = text.find('function renderUnifiedLongitudinalTallChart', idx)
print("=== RENDER REPORTS VIEW (full code) ===")
print(text[idx:end_idx if end_idx != -1 else idx+8000])

print("\n=== RENDER UNIFIED LONGITUDINAL TALL CHART ===")
end_tall = text.find('function ', end_idx + 30)
print(text[end_idx:end_tall if end_tall != -1 else end_idx+5000])
