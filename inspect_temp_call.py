with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('curveTimelineTemp')
print("curveTimelineTemp in index.html:")
if idx != -1:
    print(text[idx-50:idx+350])
else:
    print("not found, checking repChartTemp:")
    idx2 = text.find('repChartTemp')
    print(text[idx2-50:idx2+350])
