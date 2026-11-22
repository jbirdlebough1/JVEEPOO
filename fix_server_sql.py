def fix_server_sql():
    path = r"D:\veepoo\w11_htm_app\server.py"
    with open(path, "r", encoding="utf-8") as f:
        code = f.read()

    bad = '''def init_database():
    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("""
            
        cur.execute("""
            CREATE TABLE IF NOT EXISTS meal_entries ('''

    good = '''def init_database():
    with db_lock:
        conn = get_db()
        cur = conn.cursor()
        cur.execute("""
            CREATE TABLE IF NOT EXISTS meal_entries ('''

    if bad in code:
        code = code.replace(bad, good)
        print("[✔] Fixed double cur.execute in server.py")
    else:
        # Regex replace
        import re
        code = re.sub(r'cur\.execute\("""\s+cur\.execute\("""', 'cur.execute("""', code)
        print("[*] Replaced via regex")

    with open(path, "w", encoding="utf-8") as f:
        f.write(code)

if __name__ == '__main__':
    fix_server_sql()
