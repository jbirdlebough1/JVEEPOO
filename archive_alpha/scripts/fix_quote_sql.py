def fix_sql():
    path = r"D:\veepoo\w11_htm_app\server.py"
    with open(path, "r", encoding="utf-8") as f:
        code = f.read()

    bad = '''        """)
        CREATE TABLE IF NOT EXISTS vital_records ('''

    good = '''        """)
        cur.execute("""
            CREATE TABLE IF NOT EXISTS vital_records ('''

    if bad in code:
        code = code.replace(bad, good)
        print("[✔] Fixed missing cur.execute on vital_records")
    else:
        print("[!] Bad string not found exactly")

    with open(path, "w", encoding="utf-8") as f:
        f.write(code)

if __name__ == '__main__':
    fix_sql()
