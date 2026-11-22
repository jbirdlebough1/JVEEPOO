with open(r"D:\veepoo\w11_htm_app\server.py", "r", encoding="utf-8") as f:
    text = f.read()

bad_line = '            self.send_json_response(report)\n            self.send_json_response({"status": "OK", "meals": meals})'
good_line = '            self.send_json_response(report)'

if bad_line in text:
    text = text.replace(bad_line, good_line)
    print("[✔] Removed duplicate send_json_response line")
else:
    # Try CRLF
    bad_line_crlf = '            self.send_json_response(report)\r\n            self.send_json_response({"status": "OK", "meals": meals})'
    if bad_line_crlf in text:
        text = text.replace(bad_line_crlf, good_line)
        print("[✔] Removed duplicate send_json_response line (CRLF)")

with open(r"D:\veepoo\w11_htm_app\server.py", "w", encoding="utf-8") as f:
    f.write(text)
