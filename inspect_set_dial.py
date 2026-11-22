with open(r"D:\veepoo\opt2_decompiled\vpbluetooth\sources\com\inuker\bluetooth\library\jieli\dial\JLWatchFaceManager.java", "r", encoding="utf-8", errors="ignore") as f:
    text = f.read()

idx = text.find('public void setDial(String')
print("=== setDial ===")
print(text[idx:idx+1500])

idx_serv = text.find('public void setServiceDial()')
print("\n=== setServiceDial ===")
print(text[idx_serv:idx_serv+1500])
