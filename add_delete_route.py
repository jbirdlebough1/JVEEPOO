with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

target = 'elif self.path.startswith("/api/meal/daily_summary"):'
replacement = '''elif self.path.startswith("/api/meal/delete"):
            from urllib.parse import parse_qs, urlparse
            params = parse_qs(urlparse(self.path).query)
            meal_id = params.get("id", [None])[0]
            if meal_id:
                with db_lock:
                    conn = get_db()
                    cur = conn.cursor()
                    cur.execute("DELETE FROM meal_entries WHERE id = ?", (meal_id,))
                    conn.commit()
                    conn.close()
                self.send_json_response({"status": "DELETED", "id": meal_id})
            else:
                self.send_json_response({"status": "ERROR", "error": "Missing meal id"}, 400)

        elif self.path.startswith("/api/meal/daily_summary"):'''

assert target in text, "target not found"
text = text.replace(target, replacement, 1)

with open(r'D:\veepoo\w11_htm_app\server.py', 'w', encoding='utf-8') as f:
    f.write(text)

print("Added /api/meal/delete to server.py!")
