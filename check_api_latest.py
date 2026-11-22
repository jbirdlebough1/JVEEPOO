with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

pos = py.find('/api/telemetry/latest')
if pos != -1:
    print(py[pos-200:pos+1500])
