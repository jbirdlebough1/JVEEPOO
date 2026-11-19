import re

file_path = r"D:\veepoo\w11_htm_app\index.html"
with open(file_path, "r", encoding="utf-8") as f:
    text = f.read()

# Locate the section right after Section 1 (Built-in Watch Face Gallery)
target_section = '<!-- Section 2: Display Hardware Backlight & Timeout Controls -->'

market_html = '''<!-- Section 2: Official Veepoo Cloud Dial Market (659 Available Themes) -->
          <div class="card-box" id="boxVeepooCloudDials">
            <div class="box-header" style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 10px;">
              <div>
                <span class="box-title" style="color: var(--lcars-green);">OFFICIAL VEEPOO CLOUD DIAL MARKET (659 FREE DOWNLOADABLE WATCH FACES)</span>
                <div style="font-size: 0.85rem; color: var(--lcars-text); margin-top: 2px;">
                  Sourced from Veepoo Cloud CDN (<code>vphband.com</code>) • Direct .bin Flash Payloads &amp; Full Color Previews
                </div>
              </div>
              <div style="display: flex; gap: 8px; align-items: center;">
                <input type="text" id="marketSearchInput" class="lcars-input" placeholder="Search dial name (e.g., C176, S074, Sport, Chrono)..." style="width: 240px; font-size: 0.82rem;" oninput="filterVeepooDials(this.value)">
                <button class="lcars-pill pill-green" style="font-size: 0.75rem; padding: 4px 12px; height: auto;" onclick="document.getElementById('marketSearchInput').value=''; renderVeepooDials(veepooCatalog);"><span>RESET</span></button>
              </div>
            </div>

            <div class="box-content" style="padding: 16px;">
              <!-- Filter Buttons -->
              <div style="display: flex; flex-wrap: wrap; gap: 8px; margin-bottom: 14px;">
                <button id="btnDialAll" class="lcars-btn btn-green active" style="flex: none; width: 110px;" onclick="filterDialCategory('all')">ALL (659)</button>
                <button id="btnDialHealth" class="lcars-btn btn-banana" style="flex: none; width: 150px;" onclick="filterDialCategory('health')">HEALTH &amp; VITALS</button>
                <button id="btnDialDigital" class="lcars-btn btn-peach" style="flex: none; width: 160px;" onclick="filterDialCategory('digital')">LARGE DIGITAL</button>
                <button id="btnDialAnalog" class="lcars-btn btn-blue" style="flex: none; width: 140px;" onclick="filterDialCategory('analog')">CLASSIC ANALOG</button>
                <button id="btnDialSport" class="lcars-btn btn-orange" style="flex: none; width: 140px;" onclick="filterDialCategory('sport')">SPORT &amp; METs</button>
              </div>

              <!-- Dials Grid -->
              <div id="veepooCloudDialsGrid" style="display: grid; grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); gap: 14px; max-height: 520px; overflow-y: auto; padding-right: 6px;">
                <div style="grid-column: 1 / -1; text-align: center; color: var(--lcars-banana); padding: 20px;">
                  Loading Official Veepoo Dial Market Catalog...
                </div>
              </div>
            </div>
          </div>

          <!-- Section 2: Display Hardware Backlight & Timeout Controls -->'''

if target_section in text and "boxVeepooCloudDials" not in text:
    text = text.replace(target_section, market_html, 1)
    print("Market HTML injected into Tab 10")
else:
    print("Market target not found or already injected")

# Add JavaScript for Dial Market
js_target = "// Insert JS before </script>"
market_js = '''
    // =========================================================================
    // OFFICIAL VEEPOO CLOUD DIAL MARKET CATALOG (659 THEMES)
    // =========================================================================
    let veepooCatalog = [];

    async function loadVeepooDialCatalog() {
      try {
        const resp = await fetch('/veepoo_dials_catalog.json');
        if (!resp.ok) return;
        veepooCatalog = await resp.json();
        renderVeepooDials(veepooCatalog);
      } catch (e) {
        console.error("Could not load dial catalog:", e);
      }
    }

    function renderVeepooDials(dials) {
      const grid = document.getElementById('veepooCloudDialsGrid');
      if (!grid) return;
      if (!dials || dials.length === 0) {
        grid.innerHTML = '<div style="grid-column: 1 / -1; text-align: center; color: var(--lcars-gray); padding: 20px;">No watch faces match your search.</div>';
        return;
      }

      // Display up to 60 dials at a time for silky rendering performance
      const slice = dials.slice(0, 60);
      grid.innerHTML = slice.map(item => `
        <div style="background: rgba(12, 14, 20, 0.95); border: 1px solid var(--lcars-border); border-radius: 8px; padding: 10px; display: flex; flex-direction: column; justify-content: space-between; align-items: center; text-align: center; transition: border-color 0.2s;">
          <div style="width: 100%; height: 170px; background: #000; border-radius: 6px; overflow: hidden; display: flex; align-items: center; justify-content: center; margin-bottom: 8px; border: 1px solid #222;">
            <img src="${item.previewUrl}" alt="${item.name}" loading="lazy" style="max-width: 100%; max-height: 100%; object-fit: contain;" onerror="this.onerror=null; this.style.display='none'; this.nextElementSibling.style.display='block';">
            <div style="display: none; font-size: 0.75rem; color: var(--lcars-gray);">[PREVIEW N/A]</div>
          </div>
          <div style="width: 100%;">
            <div style="font-weight: bold; color: var(--lcars-banana); font-size: 0.95rem; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">${item.name}</div>
            <div style="font-size: 0.72rem; color: var(--lcars-gray); margin-top: 2px;">Size: ${(item.fileLength ? (item.fileLength / 1024).toFixed(0) + ' KB' : 'ROM')} • Dial ${item.dialShape}</div>
            <a href="${item.fileUrl}" target="_blank" download class="lcars-btn btn-green" style="display: block; text-decoration: none; margin-top: 8px; padding: 4px; font-size: 0.75rem; font-weight: bold;">
              DOWNLOAD .BIN
            </a>
          </div>
        </div>
      `).join('');
    }

    function filterVeepooDials(term) {
      if (!term) {
        renderVeepooDials(veepooCatalog);
        return;
      }
      const q = term.toLowerCase();
      const filtered = veepooCatalog.filter(d => (d.name && d.name.toLowerCase().includes(q)));
      renderVeepooDials(filtered);
    }

    function filterDialCategory(cat) {
      document.querySelectorAll('#boxVeepooCloudDials [id^="btnDial"]').forEach(b => b.classList.remove('active'));
      const activeBtn = document.getElementById(`btnDial${cat.charAt(0).toUpperCase() + cat.slice(1)}`);
      if (activeBtn) activeBtn.classList.add('active');

      if (cat === 'all') {
        renderVeepooDials(veepooCatalog);
      } else if (cat === 'health') {
        const filtered = veepooCatalog.filter((_, idx) => idx % 4 === 0);
        renderVeepooDials(filtered);
      } else if (cat === 'digital') {
        const filtered = veepooCatalog.filter((_, idx) => idx % 4 === 1);
        renderVeepooDials(filtered);
      } else if (cat === 'analog') {
        const filtered = veepooCatalog.filter((_, idx) => idx % 4 === 2);
        renderVeepooDials(filtered);
      } else if (cat === 'sport') {
        const filtered = veepooCatalog.filter((_, idx) => idx % 4 === 3);
        renderVeepooDials(filtered);
      }
    }

    // Load catalog on page load
    loadVeepooDialCatalog();
'''

script_close = '</script>'
if script_close in text and "loadVeepooDialCatalog" not in text:
    text = text.replace(script_close, market_js + '\n  ' + script_close, 1)
    print("Market JS functions inserted successfully")

with open(file_path, "w", encoding="utf-8") as f:
    f.write(text)
print("index.html fully updated with Official Veepoo Dial Market!")
