with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

bad_block = """                elif self.path.startswith("/api/meal/delete"):
            from urllib.parse import parse_qs, urlparse
            params = parse_qs(urlparse(self.path).query)
            meal_id = params.get("id", [None])[0]
            if not meal_id and self.headers.get("Content-Length"):
                try:
                    length = int(self.headers.get("Content-Length", 0))
                    body = self.rfile.read(length).decode("utf-8")
                    data = json.loads(body)
                    meal_id = data.get("id")
                except Exception:
                    pass

            if meal_id:
                with db_lock:
                    conn = get_db()
                    cur = conn.cursor()
                    cur.execute("DELETE FROM meal_entries WHERE id = ?", (meal_id,))
                    deleted = cur.rowcount > 0
                    conn.commit()
                    conn.close()
                print(f"[+] Successfully deleted meal_entries record id={meal_id}")
                self.send_json_response({"status": "DELETED", "id": meal_id, "deleted": deleted})
            else:
                self.send_json_response({"status": "ERROR", "error": "Missing meal id"}, 400)"""

good_block = """        elif self.path.startswith("/api/meal/delete"):
            from urllib.parse import parse_qs, urlparse
            params = parse_qs(urlparse(self.path).query)
            meal_id = params.get("id", [None])[0]
            if not meal_id and self.headers.get("Content-Length"):
                try:
                    length = int(self.headers.get("Content-Length", 0))
                    body = self.rfile.read(length).decode("utf-8")
                    data = json.loads(body)
                    meal_id = data.get("id")
                except Exception:
                    pass

            if meal_id:
                with db_lock:
                    conn = get_db()
                    cur = conn.cursor()
                    cur.execute("DELETE FROM meal_entries WHERE id = ?", (meal_id,))
                    deleted = cur.rowcount > 0
                    conn.commit()
                    conn.close()
                print(f"[+] Successfully deleted meal_entries record id={meal_id}")
                self.send_json_response({"status": "DELETED", "id": meal_id, "deleted": deleted})
            else:
                self.send_json_response({"status": "ERROR", "error": "Missing meal id"}, 400)"""

assert bad_block in text, "bad_block not found"
text = text.replace(bad_block, good_block, 1)

with open('w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(text)

print("Indentation fixed successfully!")
