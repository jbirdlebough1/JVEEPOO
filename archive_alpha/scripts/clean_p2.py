with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

p1 = text.find('elif self.path.startswith("/api/meal/daily_summary"):')
p2 = text.find('elif self.path.startswith("/api/meal/daily_summary"):', p1 + 50)
if p2 != -1:
    end_p2 = text.find('self.send_json_response(daily_summary)', p2) + len('self.send_json_response(daily_summary)')
    text = text[:p2] + text[end_p2:].lstrip()
    with open(r'D:\veepoo\w11_htm_app\server.py', 'w', encoding='utf-8') as f:
        f.write(text)
    print("Cleaned redundant block from server.py!")
