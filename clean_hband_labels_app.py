import re

def clean_hband_in_app():
    path_app = r"D:\veepoo\w11_htm_app\index.html"
    with open(path_app, "r", encoding="utf-8") as f:
        html = f.read()

    print(f"Original app size: {len(html)} bytes")

    # Specific replacements for all user-facing labels and text
    replacements = [
        # Instructions and contention messages
        ("Ensure HBand is closed on your phone/tablet.", "Ensure the mobile app is closed on your phone/tablet."),
        ("Ensure HBand is closed", "Ensure the mobile app is closed"),
        ("⚠️ PHONE CONTENTION (CLOSE HBAND)", "⚠️ PHONE CONTENTION (CLOSE MOBILE APP)"),
        ('logLCARS("Ambulatory Workout Session COMMENCED (HBand Protocol)", "tx");', 'logLCARS("Ambulatory Workout Session COMMENCED (Telemetry Protocol)", "tx");'),
        
        # Buttons
        ("📊 VIEW 2D LORENZ SCATTER (HBAND)", "📊 VIEW 2D LORENZ SCATTER (POINCARÉ)"),
        ("📊 2D LORENZ SCATTER (HBAND)", "📊 2D LORENZ SCATTER (POINCARÉ)"),

        # Bay and section titles
        ("AMBULATORY WORKOUT & EXERCISE DYNAMICS (HBAND SPEC)", "AMBULATORY WORKOUT & EXERCISE DYNAMICS (CLINICAL SPEC)"),
        ("HBAND 5-ZONE EXERCISE CARDIAC LOAD DISTRIBUTION", "5-ZONE EXERCISE CARDIAC LOAD DISTRIBUTION"),
        ("HBAND 24-HOUR HALF-HOUR ACTIVITY BINS (48 SLOTS)", "24-HOUR HALF-HOUR ACTIVITY BINS (48 SLOTS)"),
        ("24-HOUR HEART RATE & 5-ZONE CARDIAC LOAD (HBAND SPEC)", "24-HOUR HEART RATE & 5-ZONE CARDIAC LOAD (CLINICAL SPEC)"),
        ("HBAND PROTOCOL: 2D LORENZ (POINCARÉ) SCATTER PLOT", "CLINICAL PROTOCOL: 2D LORENZ (POINCARÉ) SCATTER PLOT"),
        ("HBAND MORPHOLOGY MODES:", "CARDIAC MORPHOLOGY MODES:"),
        ("HBAND CLINICAL IMPRESSION:", "CARDIAC CLINICAL IMPRESSION:"),
        ("09. SYSTEM, ALERTS, PERIPHERALS & HBAND NOTIFICATIONS", "09. SYSTEM, ALERTS, PERIPHERALS & HARDWARE NOTIFICATIONS"),
        ("CMD WomenSetting / H-BAND", "CMD WomenSetting / HARDWARE"),

        # Capability matrix
        ("MASTER FEATURE COMPARISON: H-BAND MOBILE APP VS. LCARS CLINICAL WORKSTATION", "MASTER FEATURE COMPARISON: OEM MOBILE APP VS. LCARS CLINICAL WORKSTATION"),
        ("MASTER CAPABILITY MATRIX: H-BAND MOBILE APP VS. LCARS CLINICAL WORKSTATION", "MASTER CAPABILITY MATRIX: OEM MOBILE APP VS. LCARS CLINICAL WORKSTATION"),
        ("manufacturer's mobile smartphone app (<strong>H-Band</strong>)", "manufacturer's stock mobile smartphone app"),
        ("native hardware command and sensor capability of H-Band while", "native hardware command and sensor capability of the stock firmware while"),
        ("H-BAND (MANUFACTURER MOBILE APP)", "OEM MOBILE APP (STOCK MANUFACTURER)"),

        # FAQ item
        ("FAQ ITEM: H-BAND PARITY & MIGRATION", "FAQ ITEM: OEM APP PARITY & MIGRATION"),
        ("Q5: How does LCARS compare to the official H-Band mobile app? Do I lose any features?", "Q5: How does LCARS compare to the official manufacturer mobile app? Do I lose any features?"),
        ("present in H-Band, including blood glucose", "present in the manufacturer firmware, including blood glucose"),
        ("However, unlike H-Band, LCARS keeps all your clinical data", "However, unlike stock mobile apps, LCARS keeps all your clinical data"),

        # Dial CDN text
        ("Sourced from Veepoo Cloud CDN (<code>vphband.com</code>)", "Sourced from Veepoo Cloud Dial CDN (<code>cloud-dial</code>)"),
        ("(HBand Protocol Specification)", "(Clinical Protocol Specification)"),
        ("(HBand 5-Zone Cardiac Effort Distribution Bar)", "(5-Zone Cardiac Effort Distribution Bar)"),
        ("(HBand 24-Hour 48-Slot Step & Cadence Timeline Chart)", "(24-Hour 48-Slot Step & Cadence Timeline Chart)"),
        ("(HBAND PARITY)", "(CLINICAL SPEC)"),
        ("HBAND PROTOCOL SPECIFICATION:", "CLINICAL PROTOCOL SPECIFICATION:"),
        ("H-Band Standard Morphologies Control Bar", "Standard Morphologies Control Bar"),
        ("// HBAND EXERCISE & WORKOUT ENGINE", "// EXERCISE & WORKOUT ENGINE"),
        ("// HBAND Parity 2D Lorenz Poincaré Scatter Plot", "// 2D Lorenz Poincaré Scatter Plot"),
        ("// RENDER ALL 10 CLINICAL SVG CHARTS (HBAND PARITY ENGINE)", "// RENDER ALL 10 CLINICAL SVG CHARTS (TELEMETRY ENGINE)"),
    ]

    for old_s, new_s in replacements:
        if old_s in html:
            count = html.count(old_s)
            html = html.replace(old_s, new_s)
            print(f"[✔] Replaced '{old_s[:40]}' ({count}x) -> '{new_s[:40]}'")
        else:
            print(f"[-] Not found: '{old_s[:40]}'")

    with open(path_app, "w", encoding="utf-8") as f:
        f.write(html)
    print(f"Saved w11_htm_app/index.html ({len(html)} bytes)")

    # Also synchronize to D:\veepoo\index.html
    path_root = r"D:\veepoo\index.html"
    with open(path_root, "w", encoding="utf-8") as f:
        f.write(html)
    print(f"Synchronized to {path_root}")

if __name__ == "__main__":
    clean_hband_in_app()
