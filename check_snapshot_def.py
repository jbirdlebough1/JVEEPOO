with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find("def get_latest_telemetry_snapshot")
print(text[m:m+1200])
