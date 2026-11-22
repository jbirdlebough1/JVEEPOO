import sqlite3
import os

print("=== CHECKING CLEBUGGER DB ===")
db_path = r"D:\CLEBUGGER\bugs.db"
if os.path.exists(db_path):
    conn = sqlite3.connect(db_path)
    cur = conn.cursor()
    cur.execute("SELECT name FROM sqlite_master WHERE type='table'")
    print("Tables:", cur.fetchall())
    
    cur.execute("PRAGMA table_info(bugs)")
    print("Bugs columns:", cur.fetchall())
    
    cur.execute("SELECT bug_id, title, status, severity FROM bugs ORDER BY bug_id DESC LIMIT 10")
    for r in cur.fetchall():
        print("  Bug:", r)
    conn.close()
else:
    print("bugs.db not found at", db_path)

print("\n=== CHECKING log_cle077.py ===")
if os.path.exists(r"D:\veepoo\log_cle077.py"):
    with open(r"D:\veepoo\log_cle077.py", "r", encoding="utf-8") as f:
        print(f.read()[:1500])
