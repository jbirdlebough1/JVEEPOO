print("[*] Fixing sendCommandBytes write queue and writeWithoutResponse...")

with open(r"D:\veepoo\w11_htm_app\index.html", "r", encoding="utf-8") as f:
    html = f.read()

old_send = """async function sendCommandBytes(bytes) {
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

new_send = """let bleWriteQueue = Promise.resolve();

    async function sendCommandBytes(bytes) {
      // Serialize all GATT writes through a single FIFO queue to eliminate "GATT operation already in progress"
      bleWriteQueue = bleWriteQueue.then(async () => {
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
          
          // Veepoo Protocol: f0080003 strictly requires writeWithoutResponse (response=False)
          if (txCharacteristic.properties.writeWithoutResponse) {
            await txCharacteristic.writeValueWithoutResponse(payload);
          } else if (txCharacteristic.properties.write) {
            await txCharacteristic.writeValueWithResponse(payload);
          } else {
            await txCharacteristic.writeValue(payload);
          }
          // Small inter-packet pacing gap
          await new Promise(r => setTimeout(r, 65));
        } catch (err) {
          soundError();
          logLCARS(`TX Error: ${err.message}`, "err");
        }
      }).catch(err => {
        logLCARS(`Queue Error: ${err.message}`, "err");
      });

      return bleWriteQueue;
    }"""

if old_send in html:
    html = html.replace(old_send, new_send)
    print("[+] Successfully replaced sendCommandBytes with serialized FIFO queue & writeWithoutResponse!")
else:
    print("[!] Warning: old_send did not match")

with open(r"D:\veepoo\w11_htm_app\index.html", "w", encoding="utf-8") as f:
    f.write(html)

print("[✔] Write complete!")
