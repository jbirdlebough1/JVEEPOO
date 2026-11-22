import sqlite3
conn = sqlite3.connect(r'D:\CLEBUGGER\bugs.db')
cur = conn.cursor()
cur.execute("PRAGMA table_info(bugs)")
for col in cur.fetchall():
    print(col)
cur.execute("SELECT * FROM bugs ORDER BY rowid DESC LIMIT 5")
for r in cur.fetchall():
    print(r)
conn.close()
