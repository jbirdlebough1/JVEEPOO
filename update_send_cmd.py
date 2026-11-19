import re

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    s = f.read()

pattern = re.compile(r'async function sendCommandBytes\(bytes\)\s*\{.*?\n    \}', re.DOTALL)

replacement = """async function sendCommandBytes(bytes) {
      if (!txCharacteristic || !bluetoothDevice || !bluetoothDevice.gatt || !bluetoothDevice.gatt.connected) {
        const ok = await ensureConnected();
        if (!ok || !txCharacteristic) {
          soundError();
          logLCARS("Error: Could not connect to watch. Ensure watch screen is awake.", "err");
          return;
        }
      }
      // Mandatory: Pad every Veepoo GATT command to exactly 20 bytes
      const payload = new Uint8Array(20);
      for (let i = 0; i < bytes.length && i < 20; i++) {
        payload[i] = bytes[i];
      }
      try {
        const hex = Array.from(payload).map(b => b.toString(16).padStart(2, '0')).join(' ');
        logLCARS(`TX [${txCharacteristic.uuid.substring(0, 8)}]: ${hex}`, "tx");
        if (txCharacteristic.properties.write) {
          await txCharacteristic.writeValueWithResponse(payload);
        } else {
          await txCharacteristic.writeValueWithoutResponse(payload);
        }
      } catch (err) {
        soundError();
        logLCARS(`TX Error: ${err.message}`, "err");
      }
    }"""

new_s, count = pattern.subn(replacement, s, count=1)
print(f"Replaced sendCommandBytes count: {count}")
with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(new_s)
