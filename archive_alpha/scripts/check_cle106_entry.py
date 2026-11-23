import sqlite3
conn = sqlite3.connect(r'D:\CLEBUGGER\bugs.db')
cur = conn.cursor()
cur.execute("SELECT bug_code, title, status FROM bugs WHERE bug_code='CLE-106'")
print(cur.fetchall())
conn.close()
