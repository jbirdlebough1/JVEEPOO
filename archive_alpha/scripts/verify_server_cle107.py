import sys
sys.path.insert(0, 'w11_htm_app')
import server

tl = server.get_two_day_timeline()
print('Timeline length:', len(tl))
active_today = [p for p in tl if p['day'] == 'TODAY' and not p['isChargingGap'] and not p['isFuture']]
print('Active today entries:', len(active_today))
for p in active_today[:10]:
    print(p['time'], 'G:', p['g'], 'BP:', p['sys'], 'HR:', p['hr'], 'SpO2:', p['spo2'], 'Med:', p['med'], 'Ex:', p['ex'], 'Meal:', p['meal'])
