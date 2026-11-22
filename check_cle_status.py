import sqlite3

con = sqlite3.connect(r'D:\CLEBUGGER\bugs.db')
cur = con.cursor()
for r in cur.execute("SELECT bug_code, status, title FROM bugs WHERE bug_code LIKE 'CLE-08%' ORDER BY bug_code DESC").fetchall():
    print(r)
con.close()
