with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

pos = py.find("self.path == '/api/telemetry'")
if pos == -1:
    pos = py.find("'/api/telemetry'")
if pos != -1:
    print(py[pos-100:pos+1500])
