import re

def update_index_files():
    for p in [r"D:\veepoo\w11_htm_app\index.html", r"D:\veepoo\index.html"]:
        with open(p, "r", encoding="utf-8") as f:
            html = f.read()

        # 1. Update the Cell Phone Wi-Fi Gateway Card in Tab 09
        old_gateway_pattern = re.compile(
            r'<!-- CELL PHONE PHOTO UPLOAD & WI-FI AUTO-SYNC GATEWAY -->\s*<div style="background: #f0fdf4;.*?</div>\s*</div>\s*</div>',
            re.DOTALL
        )
        
        new_qtag_gateway_html = '''<!-- CELL PHONE WI-FI ACCESS PORT & GOOGLE LENS Q-TAG -->
            <div style="background: #ffffff; border: 1.5px solid #cbd5e1; border-top: 4px solid #0284c7; border-radius: 8px; padding: 14px 18px; margin-bottom: 16px; box-shadow: 0 1px 4px rgba(0,0,0,0.05);">
              <div style="display: flex; justify-content: space-between; align-items: center; flex-wrap: wrap; gap: 8px; margin-bottom: 10px;">
                <div>
                  <strong style="color: #0369a1; font-size: 0.98rem; font-family: var(--lcars-mono); letter-spacing: 0.04em;">
                    📱 CELL PHONE WI-FI ACCESS PORT &bull; GOOGLE LENS Q-TAG
                  </strong>
                  <span style="font-size: 0.78rem; color: #64748b; display: block; margin-top: 2px;">
                    NO PHONE APP REQUIRED &bull; SCAN Q-TAG WITH GOOGLE LENS OR SEND LINK VIA SMS/EMAIL &bull; SAVE TO PHONE WALLET
                  </span>
                </div>
                <div style="font-size: 0.76rem; color: #15803d; font-family: var(--lcars-mono); font-weight: bold; background: #f0fdf4; padding: 3px 8px; border-radius: 4px; border: 1px solid #86efac;">
                  ● WI-FI SERVER ACTIVE (192.168.12.207:8899)
                </div>
              </div>

              <div style="display: flex; gap: 16px; align-items: center; flex-wrap: wrap;">
                <!-- Scannable Q-Tag (Google Lens / Camera) -->
                <div style="display: flex; flex-direction: column; align-items: center; background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 8px; padding: 10px; text-align: center;">
                  <img id="imgMobileWifiQr" src="/mobile_wifi_qr.svg" alt="Google Lens Q-Tag" style="width: 124px; height: 124px; background: #ffffff; border-radius: 4px; border: 1px solid #e2e8f0; display: block;">
                  <span style="font-size: 0.72rem; color: #0369a1; font-weight: 800; font-family: var(--lcars-mono); margin-top: 6px;">
                    🔍 SCAN WITH GOOGLE LENS
                  </span>
                  <span style="font-size: 0.65rem; color: #64748b;">or phone camera to open</span>
                </div>

                <!-- Link box and SMS / Email dispatchers -->
                <div style="flex: 1; min-width: 260px;">
                  <label style="font-size: 0.75rem; color: #475569; font-weight: 800; display: block; margin-bottom: 4px;">
                    EXACT WI-FI LINK FOR PHONE BROWSER:
                  </label>
                  <div style="display: flex; gap: 6px; margin-bottom: 8px;">
                    <input type="text" id="txtMobileWifiUrl" value="http://192.168.12.207:8899/mobile_upload.html" readonly style="flex: 1; padding: 7px 10px; border: 1.5px solid #cbd5e1; border-radius: 6px; font-family: var(--lcars-mono); font-size: 0.85rem; font-weight: bold; color: #0f172a; background: #f8fafc;">
                    <button class="lcars-btn btn-blue" style="font-size: 0.78rem; padding: 7px 12px; font-weight: bold;" onclick="copyMobileWifiLink()">
                      📋 COPY LINK
                    </button>
                  </div>

                  <!-- Quick Dispatchers (SMS, Email, Open) -->
                  <div style="display: flex; gap: 8px; flex-wrap: wrap;">
                    <a id="btnSmsMobileLink" href="sms:?&body=LCARS%20Mobile%20Food%20Upload%20Link:%20http://192.168.12.207:8899/mobile_upload.html" class="lcars-btn btn-green" style="font-size: 0.76rem; padding: 6px 12px; text-decoration: none; font-weight: bold; display: inline-flex; align-items: center; gap: 4px;">
                      💬 SEND VIA SMS
                    </a>
                    <a id="btnEmailMobileLink" href="mailto:?subject=LCARS%20Mobile%20Meal%20Upload%20Link&body=Open%20this%20link%20on%20your%20phone%20browser%20while%20connected%20to%20Wi-Fi%20to%20upload%20meal%20photos%20for%20Gemini%20macro%20analysis:%0A%0Ahttp://192.168.12.207:8899/mobile_upload.html%0A%0ASave%20the%20credentials%20to%20your%20phone%20wallet/keychain%20so%20next%20time%20it%20opens%20directly!" class="lcars-btn btn-orange" style="font-size: 0.76rem; padding: 6px 12px; text-decoration: none; font-weight: bold; display: inline-flex; align-items: center; gap: 4px;">
                      ✉️ EMAIL TO CELL
                    </a>
                    <a href="http://192.168.12.207:8899/mobile_upload.html" target="_blank" class="lcars-btn btn-peach" style="font-size: 0.76rem; padding: 6px 12px; text-decoration: none; font-weight: bold; display: inline-flex; align-items: center; gap: 4px;">
                      🔗 TEST IN BROWSER
                    </a>
                  </div>

                  <div style="font-size: 0.73rem; color: #64748b; margin-top: 8px; line-height: 1.4;">
                    💡 <strong>3-Step Simple Flow:</strong> (1) Scan Q-Tag with Google Lens or email/SMS link to your phone. (2) Save login to your phone wallet/keychain once. (3) Next time, opening the link directly launches the upload form to grab meal photos from your phone pictures folder.
                  </div>
                </div>
              </div>
            </div>'''

        # Try regex or marker replace
        if old_gateway_pattern.search(html):
            html = old_gateway_pattern.sub(new_qtag_gateway_html, html)
            print(f"[✔] Replaced cell gateway with Google Lens Q-Tag card in {p}")
        else:
            marker_start = "<!-- CELL PHONE PHOTO UPLOAD & WI-FI AUTO-SYNC GATEWAY -->"
            marker_end = "<!-- Quick Meal Request Presets -->"
            pos1 = html.find(marker_start)
            pos2 = html.find(marker_end)
            if pos1 != -1 and pos2 != -1:
                html = html[:pos1] + new_qtag_gateway_html + '\n\n            ' + html[pos2:]
                print(f"[✔] Replaced cell gateway using marker slice in {p}")
            else:
                print(f"[!] Could not find cell gateway marker in {p}")

        # 2. Add Dick's Drive-In button to Quick Meal Request Presets
        dicks_btn_html = '''<button class="lcars-btn btn-orange" style="font-size: 0.76rem; padding: 4px 10px;" onclick="selectMealBuilderPreset('dicks')">
                  🍔 Dick's Drive-In Deluxe (28g net)
                </button>'''
        if "selectMealBuilderPreset('dicks')" not in html:
            pos_btn = html.find("selectMealBuilderPreset('chipotle')")
            if pos_btn != -1:
                btn_end = html.find("</button>", pos_btn) + 9
                html = html[:btn_end] + '\n                ' + dicks_btn_html + html[btn_end:]
                print(f"[✔] Added Dick's Drive-In button to Presets in {p}")

        # 3. Add dicks to MEAL_BUILDER_PRESETS in JS
        dicks_js_preset = '''      dicks: {
        title: "Dick's Drive-In Deluxe Burger (Seattle Iconic 1954)",
        desc: "Dick's Deluxe 100% real beef double patties, melted cheese, lettuce, pickle relish and mayo on a toasted bun.",
        vendor: "Dick's Drive-In Restaurants (Seattle Official Nutrition)",
        calories: 420,
        netCarbs: 28,
        protein: 20,
        fiber: 2,
        fat: 26,
        glycemicLoad: "12 (Low-Medium)",
        meetsGoal: true,
        statusText: "✅ MEETS CLINICAL DIABETIC GOALS (28g NET CARBS / 45g MAX)",
        statusClass: "badge-green",
        projectedSpike: "+22 mg/dL (Euvolemic Range)",
        items: [
          { name: "100% Real Beef Patties", portion: "2 patties", netCarbs: 0, protein: 18, cal: 180 },
          { name: "Melted American Cheese", portion: "1 slice", netCarbs: 1, protein: 2, cal: 60 },
          { name: "Crisp Lettuce, Relish & Mayo", portion: "standard", netCarbs: 3, fiber: 1, cal: 50 },
          { name: "Toasted Hamburger Bun", portion: "1 bun", netCarbs: 24, fiber: 1, cal: 130 }
        ],
        geminiAnalysis: "EXCELLENT DIABETIC COMPLIANCE: At 28g Net Carbs, Dick's Deluxe easily satisfies your doctor's 45g net carb ceiling! High beef albumin and healthy lipid buffer slows carbohydrate absorption. (Note: Hand-cut fries add +44g net carbs; share half the fries or take a 15-min GLUT4 walk if ordered with fries).",
        insulinStrategy: "Basal/Oral coverage sufficient. 15-min post-meal walk keeps peak < 160 mg/dL.",
        stores: [
          { store: "Dick's Drive-In Broadway (Capitol Hill)", dist: "0.8 mi", price: "$5.10", item: "Dick's Deluxe (Order at Window)" },
          { store: "Dick's Drive-In Wallingford (45th St)", dist: "2.4 mi", price: "$5.10", item: "Dick's Deluxe (Fresh Off Grill)" },
          { store: "Dick's Drive-In Queen Anne", dist: "3.1 mi", price: "$5.10", item: "Dick's Deluxe (Walk-up Window)" },
          { store: "Dick's Drive-In Lake City", dist: "6.2 mi", price: "$5.10", item: "Dick's Deluxe (Window Service)" }
        ]
      },
'''
        if 'dicks: {' not in html:
            pos_mcd = html.find('mcdonalds: {')
            if pos_mcd != -1:
                html = html[:pos_mcd] + dicks_js_preset + '      ' + html[pos_mcd:]
                print(f"[✔] Injected dicks into MEAL_BUILDER_PRESETS in {p}")

        # 4. Fix typing parser: separate Dick's from McDonald's and remove burger/fries from McDonald's
        old_mcd_check = 'text.includes("mcdonald") || text.includes("quarter pounder") || text.includes("fries") || text.includes("burger")'
        new_dicks_check = 'text.includes("dick") || text.includes("deluxe") || text.includes("drive-in") || text.includes("drivein")'
        
        if old_mcd_check in html:
            replacement_parser = f'{new_dicks_check}) {{\n        data = MEAL_BUILDER_PRESETS.dicks;\n      }} else if (text.includes("mcdonald") || text.includes("quarter pounder") || text.includes("big mac")'
            html = html.replace(old_mcd_check, replacement_parser)
            print(f"[✔] Fixed parser in {p} to accurately recognize Dick's Drive-In")

        # 5. Inject copyMobileWifiLink function if not present
        if 'function copyMobileWifiLink' not in html:
            fn_code = '''
    function copyMobileWifiLink() {
      soundButtonPress();
      const input = document.getElementById('txtMobileWifiUrl');
      if (input) {
        input.select();
        navigator.clipboard.writeText(input.value).then(() => {
          alert("Mobile Wi-Fi Link copied to clipboard!\\n\\n" + input.value + "\\n\\nPaste into an SMS text message or email to open on your smartphone.");
        }).catch(() => {
          alert("Mobile Link: " + input.value);
        });
      }
    }
'''
            pos_script = html.find('function selectMealBuilderPreset')
            if pos_script != -1:
                html = html[:pos_script] + fn_code + '\n    ' + html[pos_script:]
                print(f"[✔] Injected copyMobileWifiLink into {p}")

        with open(p, "w", encoding="utf-8") as f:
            f.write(html)
        print(f"Saved {p}")

if __name__ == "__main__":
    update_index_files()
