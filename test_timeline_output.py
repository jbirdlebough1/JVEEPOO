import sys
sys.path.insert(0, 'w11_htm_app')
from server import get_two_day_timeline

tl = get_two_day_timeline()
print('Total timeline entries:', len(tl))
non_gaps = [e for e in tl if not e['isChargingGap'] and not e['isFuture']]
print('Non-gap entries:', len(non_gaps))
for e in non_gaps[:15]:
    print(e['day'], e['time'], 'g:', e['g'], 'sys:', e['sys'], 'dia:', e['dia'], 'hr:', e['hr'], 'steps:', e['steps'], 'med:', e['med'], 'meal:', e['meal'], 'notes:', e['notes'])
