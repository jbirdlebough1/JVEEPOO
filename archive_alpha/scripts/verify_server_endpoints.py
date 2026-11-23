import urllib.request
import json

with urllib.request.urlopen('http://localhost:8899/api/meal/daily_summary') as resp:
    data = json.loads(resp.read().decode())
    print('Daily summary totals:', data['total_consumed_count'], 'meals,', data['total_calories'], 'kcal,', data['total_net_carbs'], 'g carbs')
    for meal_type, info in data['by_type'].items():
        print(f"{meal_type}: {info['count']} items, {info['calories']} kcal, components: {len(info.get('food_components', []))}")

with urllib.request.urlopen('http://localhost:8899/api/meal/recent?limit=5') as resp:
    recent = json.loads(resp.read().decode())
    print('Recent meals returned:', len(recent['meals']))
    for m in recent['meals']:
        print(f"ID {m['id']}: {m['meal_name']} ({m['calories']} kcal, {m['net_carbs']}g net carbs) - Items: {len(m.get('identified_items', []))}")
