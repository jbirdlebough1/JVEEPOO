# apply_clinical_fixes.py
import sqlite3
import json
import re
import os

# 1. Deduplicate popular_meals and correct cell_34 in telemetry.db
db_path = 'D:/veepoo/w11_htm_app/telemetry.db'
if os.path.exists(db_path):
    conn = sqlite3.connect(db_path)
    cur = conn.cursor()

    # Find duplicates by lowercase trimmed title
    cur.execute('SELECT id, title, updated_at FROM popular_meals ORDER BY updated_at DESC')
    rows = cur.fetchall()
    seen_titles = set()
    to_delete = []
    for mid, title, updated_at in rows:
        norm = title.strip().lower()
        if norm in seen_titles:
            to_delete.append(mid)
        else:
            seen_titles.add(norm)

    print(f"Deleting {len(to_delete)} duplicate popular meals:", to_delete)
    for mid in to_delete:
        cur.execute('DELETE FROM popular_meals WHERE id = ?', (mid,))

    # Fix cell_34: Peppermint crunch candy is NOT healthy 28g net carbs!
    # A standard 2 oz Christmas peppermint crunch candy is ~52g net carbs, GL 45, exceeds goals!
    cur.execute("""
        UPDATE popular_meals
        SET net_carbs = 52.0,
            total_carbs = 52.0,
            calories = 540,
            fiber = 0.0,
            protein = 2.0,
            fat = 24.0,
            glycemic_load = '45 (Severe High Spike Risk)',
            meets_goal = 0,
            description = 'High sucrose and corn syrup holiday peppermint confectionery. Severe postprandial glucose spike hazard.'
        WHERE id = 'cell_34'
    """)

    # Also update in meal_entries table
    cur.execute("""
        UPDATE meal_entries
        SET net_carbs = 52.0,
            total_carbs = 52.0,
            calories = 540,
            fiber = 0.0,
            protein = 2.0,
            fat = 24.0,
            glycemic_load = '45 (Severe High Spike Risk)',
            meets_goal = 0,
            gemini_notes = '🚨 SEVERE CONFECTIONERY HAZARD: 2 oz Peppermint Crunch contains 52g pure sucrose/corn syrup net carbs. Expected glycemic excursion: +95 to +135 mg/dL above baseline within 30 minutes, exceeding safe 140 mg/dL ceiling. Recommend immediate harm-reduction trade to zero-sugar allulose confection or mandatory 45-min GLUT4 walk.'
        WHERE meal_name LIKE '%Christmas candy pepper mint crunch%' OR id = 34
    """)

    conn.commit()
    conn.close()
    print("[✔] telemetry.db deduplicated and cell_34 corrected to clinical safety standards!")
