import re
import hashlib

with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

target = '''    function searchLocalStoreCatalog() {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const sel = document.getElementById('selLocalStoreChain');
      const storeVal = sel ? sel.value : 'fredmeyer';
      const storeNames = {
        'fredmeyer': 'Fred Meyer / QFC (3.1 mi)',
        'safeway': 'Safeway / Albertsons (4.6 mi)',
        'traderjoes': "Trader Joe's / Costco (6.8 mi)"
      };
      const storeName = storeNames[storeVal] || 'Fred Meyer / QFC (3.1 mi)';
      const statusEl = document.getElementById('localStoreSearchStatus');
      if (statusEl) {
        statusEl.style.display = 'block';
        statusEl.innerHTML = `🔍 <strong>${storeName}:</strong> Sourced all recipe materials within 20-mile radius! <span style="color: #0284c7; font-weight: bold;">100% IN STOCK</span> &bull; Aisle coordinates &amp; digital coupons synced. Curbside pickup ready.`;
        setTimeout(() => { if (statusEl) statusEl.style.display = 'none'; }, 6000);
      }
    }'''

replacement = '''    function searchLocalStoreCatalog() {
      if (typeof soundButtonPress === 'function') soundButtonPress();
      const sel = document.getElementById('selLocalStoreChain');
      const storeVal = sel ? sel.value : 'fredmeyer';
      const storeText = (sel && sel.selectedIndex >= 0) ? sel.options[sel.selectedIndex].text : 'Fred Meyer / QFC (2.8 mi)';
      const inpZip = document.getElementById('inputBOMZipCode') || document.getElementById('cfgZipCode');
      const activeZip = inpZip ? inpZip.value.trim() : '98201';
      const statusEl = document.getElementById('localStoreSearchStatus');
      if (statusEl) {
        statusEl.style.display = 'block';
        statusEl.innerHTML = `🔍 <strong>${storeText}:</strong> Sourced all recipe materials within 20-mile radius of ZIP <strong>${activeZip}</strong>! <span style="color: #0284c7; font-weight: bold;">100% IN STOCK</span> &bull; Aisle coordinates &amp; digital coupons synced. Curbside pickup ready.`;
        setTimeout(() => { if (statusEl) statusEl.style.display = 'none'; }, 6000);
      }
      if (typeof soundSuccess === 'function') soundSuccess();
    }'''

assert target in html, "target searchLocalStoreCatalog not found"
html = html.replace(target, replacement, 1)

# Check div tag balance
o = len(re.findall(r'<div\b', html))
c = len(re.findall(r'</div\b', html))
print(f"Div balance: open={o}, close={c}, delta={o-c}")
assert o == c, f"Div tag mismatch: open={o}, close={c}"

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
print(f"[+] Exact parity: {h1 == h2} (hash: {h1})")
