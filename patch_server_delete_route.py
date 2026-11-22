with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    code = f.read()

# Check if /api/meal/delete is in do_POST
idx_dopost = code.find('def do_POST(self):')
assert idx_dopost != -1, "do_POST not found in server.py"

idx_post_delete = code.find('self.path.startswith("/api/meal/delete")', idx_dopost)
if idx_post_delete == -1:
    print("Adding /api/meal/delete to do_POST...")
    # Find a good anchor in do_POST, e.g. /api/meal/analyze or /api/meal/record
    target_anchor = 'elif self.path == "/api/meal/record" or self.path == "/api/meal/upload":'
    assert target_anchor in code, "target_anchor not found in do_POST"
    
    delete_post_block = """        elif self.path.startswith("/api/meal/delete"):
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
                self.send_json_response({"status": "ERROR", "error": "Missing meal id"}, 400)

        """
    code = code.replace(target_anchor, delete_post_block + target_anchor, 1)

    with open('w11_htm_app/server.py', 'w', encoding='utf-8') as f:
        f.write(code)
    print("w11_htm_app/server.py updated with POST /api/meal/delete!")
else:
    print("/api/meal/delete already in do_POST.")
