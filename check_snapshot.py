with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

pos = py.find('def get_latest_telemetry_snapshot')
if pos != -1:
    print(py[pos:pos+1500])
