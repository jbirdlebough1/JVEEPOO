import sqlite3

conn = sqlite3.connect(r"D:\CLEBUGGER\bugs.db")
cur = conn.cursor()
cur.execute("SELECT bug_code, title, status, component FROM bugs ORDER BY id DESC LIMIT 10")
for r in cur.fetchall():
    print("Bug:", r)
conn.close()
