import sys
sys.path.insert(0, 'w11_htm_app')
from server import get_two_day_timeline

tl = get_two_day_timeline()
for e in tl:
    if e['day'] == 'TODAY' and (e['meal'] or e['g'] or e['steps'] > 0 or not e['isChargingGap']):
        print(e['time'], 'g:', e['g'], 'sys:', e['sys'], 'hr:', e['hr'], 'steps:', e['steps'], 'meal:', e['meal'], 'gap:', e['isChargingGap'], 'future:', e['isFuture'])
