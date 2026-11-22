with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

p1 = text.find('elif self.path.startswith("/api/meal/daily_summary"):')
p2 = text.find('elif self.path.startswith("/api/meal/daily_summary"):', p1 + 50)
if p2 != -1:
    end_p2 = text.find('self.send_json_response(daily_summary)', p2) + len('self.send_json_response(daily_summary)')
    print("Found end_p2:", end_p2)
    print("Text after end_p2:\n", text[end_p2:end_p2+500])
