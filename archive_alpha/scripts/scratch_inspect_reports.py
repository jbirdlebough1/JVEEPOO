with open('index.html', encoding='utf-8') as f:
    for i, line in enumerate(f):
        l = line.lower()
        if 'repchartglucose' in l or 'tallchartsvgcontainer' in l or 'subvalmet' in l or 'met (metabolic' in l or 'blood composition' in l:
            print(f'Line {i+1}: {line.strip()[:110]}')
