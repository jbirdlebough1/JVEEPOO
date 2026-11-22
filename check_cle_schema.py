import sqlite3

conn = sqlite3.connect(r'D:\CLEBUGGER\bugs.db')
cur = conn.cursor()
cur.execute("PRAGMA table_info(bugs)")
cols = cur.fetchall()
print("bugs cols:", cols)

cur.execute("SELECT * FROM bugs ORDER BY id DESC LIMIT 3")
print("recent bugs:", cur.fetchall())
conn.close()
