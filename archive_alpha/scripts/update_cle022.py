import sqlite3
import datetime

conn = sqlite3.connect('D:/CLEBUGGER/bugs.db')
c = conn.cursor()
now = datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')

c.execute("""
    UPDATE bugs SET
        resolution = resolution || ' Removed redundant +Pair New pill from sidebar navigation rail since clicking the COMM LINK ribbon item directly triggers the Windows 11 Web Bluetooth scan dialog.',
        comments = comments || ' Sidebar cleaned of +Pair New clutter.',
        updated_at = ?
    WHERE bug_code = 'CLE-022'
""", (now,))

conn.commit()
conn.close()
print("CLE-022 updated with Pair New removal!")
