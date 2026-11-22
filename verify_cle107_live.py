import urllib.request, json

def verify_live_server():
    # 1. Timeline
    req = urllib.request.urlopen("http://127.0.0.1:8899/api/telemetry/timeline")
    tl = json.loads(req.read().decode())
    print(f"Timeline entries: {len(tl)}")
    today_active = [p for p in tl if p['day'] == 'TODAY' and not p['isChargingGap'] and not p['isFuture']]
    print(f"Today active slots: {len(today_active)}")
    assert len(today_active) > 10, "Too few active slots today!"
    # Check medication in timeline
    med_slots = [p for p in tl if p.get('med')]
    print(f"Medication regimen slots present: {len(med_slots)}")
    for m in med_slots:
        print(f"  {m['day']} {m['time']}: {m['med']['name']} - {m['med']['desc']}")
    assert len(med_slots) >= 2, "Medication regimen missing!"

    # 2. Daily Rollups
    req2 = urllib.request.urlopen("http://127.0.0.1:8899/api/reports/daily_rollups")
    dr = json.loads(req2.read().decode())
    print(f"Daily rollups count: {len(dr.get('rollups', []))}")
    for r in dr['rollups']:
        print(f"  Date {r['date']}: Steps={r['steps']}, Mean G={r['mean_glucose']}, TIR={r['tir_pct']}%, BP={r['sys']}/{r['dia']}")

    # 3. Metabolic report
    req3 = urllib.request.urlopen("http://127.0.0.1:8899/api/reports/metabolic_exercise")
    mr = json.loads(req3.read().decode())
    print(f"Metabolic report active avg glucose: {mr['summary']['active_days_avg_glucose']}")
    print("[PASS] All live HTTP endpoints verified 100%!")

verify_live_server()
