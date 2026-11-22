import urllib.request, json

resp = urllib.request.urlopen('http://localhost:8899/api/telemetry/timeline')
cached2DayTimeline = json.loads(resp.read().decode('utf-8'))

for tf in ['daily', 'weekly', 'monthly', 'yearly']:
    print(f"\n================ TIMEFRAME: {tf} ================")
    isDaily = (tf == 'daily')
    isWeekly = (tf == 'weekly')
    isMonthly = (tf == 'monthly')
    isYearly = (tf == 'yearly')
    
    glucoseData = []
    bpData = []
    hrData = []
    tempData = []
    sleepData = []
    exerciseData = []
    
    staticClinicalExamplesEnabled = False
    
    if isDaily:
        if cached2DayTimeline:
            todayPoints = [d for d in cached2DayTimeline if d.get('day') == 'TODAY' and not d.get('isFuture')]
            for p in todayPoints:
                if p.get('g') is not None: glucoseData.append((p['time'], p['g']))
                if p.get('hr') is not None: hrData.append((p['time'], p['hr']))
                if p.get('sys') is not None: bpData.append((p['time'], p['sys'], p['dia']))
                if p.get('temp') is not None: tempData.append((p['time'], p['temp']))
                if p.get('steps', 0) > 0: exerciseData.append((p['time'], p['steps']))
    elif isWeekly and staticClinicalExamplesEnabled:
        pass
    elif isMonthly:
        pass
    else: # yearly / fallback
        # gAnnual = [] ...
        pass
        
    print(f"Glucose points: {len(glucoseData)}: {glucoseData[:3]}...{glucoseData[-2:] if glucoseData else []}")
    print(f"HR points: {len(hrData)}: {hrData[:3]}...{hrData[-2:] if hrData else []}")
    print(f"BP points: {len(bpData)}: {bpData[:3]}...{bpData[-2:] if bpData else []}")
    print(f"Temp points: {len(tempData)}: {len(tempData)}")
    print(f"Sleep points: {len(sleepData)}: {len(sleepData)}")
    print(f"Exercise points: {len(exerciseData)}: {len(exerciseData)}")
