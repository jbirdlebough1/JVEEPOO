import sqlite3
conn = sqlite3.connect(r'D:\CLEBUGGER\bugs.db')
cur = conn.cursor()
cur.execute("SELECT id, summary, status FROM bugs ORDER BY id DESC LIMIT 5")
for r in cur.fetchall():
    print(r)
conn.close()
