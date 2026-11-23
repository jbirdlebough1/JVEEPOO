with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

target = 'self.send_json_response(analysis)'
replacement = 'self.send_json_response({"status": "SUCCESS", "analysis": analysis, **analysis})'

if target in text:
    text = text.replace(target, replacement, 1)
    with open(r'D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
        f.write(text)
    print("Updated send_json_response in server.py")
else:
    print("Target not found")
