with open(r'D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

target = 'metrics = ["GLUCOSE", "BLOOD_PRESSURE", "HEART_RATE", "SPO2", "TEMPERATURE", "PEDOMETER", "BATTERY"]'
replacement = 'metrics = ["GLUCOSE", "BLOOD_PRESSURE", "HEART_RATE", "SPO2", "BLOOD_OXYGEN", "TEMPERATURE", "PEDOMETER", "BATTERY", "HRV_STRESS", "HRV"]'

if target in text:
    new_text = text.replace(target, replacement, 1)
    with open(r'D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
        f.write(new_text)
    print("Successfully updated metrics in server.py")
else:
    print("Target not found in server.py")
