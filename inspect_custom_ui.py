with open(r'D:/veepoo/opt1_sdk_official/android_sdk_source/Demo/VpBluetoothSDK/app/src/main/java/com/timaimee/vpdemo/activity/UiUpdateCustomActivity.java', 'r', encoding='utf-8', errors='ignore') as f:
    text = f.read()

print("UiUpdateCustomActivity.java size:", len(text))
print(text[:3000])
