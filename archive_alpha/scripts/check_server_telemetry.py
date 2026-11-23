with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

m = text.find('api/telemetry')
print("api/telemetry in server.py:")
while m != -1:
    print(f"  found at {m}:", text[m:m+100].replace('\n', ' '))
    m = text.find('api/telemetry', m + 1)
