import sqlite3
import datetime
import urllib.parse

def test_goals_calc():
    conn = sqlite3.connect(r"D:\veepoo\w11_htm_app\telemetry.db")
    cur = conn.cursor()
    
    cur.execute("SELECT id, timestamp, meal_name, net_carbs, calories, meets_goal, gemini_notes FROM meal_entries ORDER BY id DESC")
    meals = cur.fetchall()
    print(f"Total meal entries in DB: {len(meals)}")
    for m in meals:
        print(f"  ID {m[0]}: {m[1]} | {m[2]} | {m[3]}g net carbs | meets_goal: {m[5]}")
    conn.close()

test_goals_calc()
