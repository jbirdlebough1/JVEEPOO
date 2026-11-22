import sqlite3

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
cur = conn.cursor()
cur.execute("PRAGMA table_info(bugs)")
cols = cur.fetchall()
print("Columns in bugs:", [c[1] for c in cols])
cur.execute("SELECT * FROM bugs ORDER BY id DESC LIMIT 2")
print("Recent rows:", cur.fetchall())
conn.close()
