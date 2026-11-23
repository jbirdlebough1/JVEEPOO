import sqlite3

# Update DB to meets_goal = 0
conn = sqlite3.connect('D:/veepoo/w11_htm_app/telemetry.db')
cur = conn.cursor()
cur.execute("UPDATE popular_meals SET meets_goal = 0 WHERE id = 'cell_34'")
cur.execute("UPDATE meal_entries SET meets_goal = 0 WHERE id = 34 OR meal_name LIKE '%pepper%'")
conn.commit()
conn.close()

# Update server.py meets_goal to False
for p in ['D:/veepoo/w11_htm_app/server.py', 'D:/veepoo/w11_htm_app/cell.html', 'D:/veepoo/index.html', 'D:/veepoo/w11_htm_app/index.html']:
    with open(p, 'r', encoding='utf-8') as f:
        content = f.read()
    content = content.replace('"meets_goal": True,\n            "projected_spike": "+45 to +60', '"meets_goal": False,\n            "projected_spike": "+45 to +60')
    content = content.replace('meets_goal: true,\n          projected_spike: "+45 to +60', 'meets_goal: false,\n          projected_spike: "+45 to +60')
    content = content.replace('meetsGoal: true,\n            tips: [\n              "🍬 OFFICIAL PACKAGE NUTRITION FACTS', 'meetsGoal: false,\n            tips: [\n              "🍬 OFFICIAL PACKAGE NUTRITION FACTS')
    with open(p, 'w', encoding='utf-8') as f:
        f.write(content)

print("[✔] Updated meets_goal to False across DB, server.py, cell.html, index.html!")
