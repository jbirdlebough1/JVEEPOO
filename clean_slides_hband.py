import json, re

def clean_slides_data():
    path = r"D:\veepoo\all_slides_data.json"
    with open(path, "r", encoding="utf-8") as f:
        slides = json.load(f)

    print(f"Total slides: {len(slides)}")
    
    modified_count = 0
    for idx, slide in enumerate(slides, 1):
        raw = json.dumps(slide)
        if re.search(r'(?i)h[\s-]?band', raw):
            modified_count += 1
            print(f"Slide {idx} has matches, replacing...")
            
            # Perform text replacements
            raw = raw.replace("H-Band vs LCARS", "OEM App vs LCARS")
            raw = raw.replace("H-Band 2D Lorenz", "2D Lorenz")
            raw = raw.replace("HBAND 2D LORENZ", "2D LORENZ")
            raw = raw.replace("HBAND 2D POINCARÉ", "2D POINCARÉ")
            raw = raw.replace("HBAND 5-ZONE", "5-ZONE")
            raw = raw.replace("HBAND PARITY", "CLINICAL PARITY")
            raw = raw.replace("HBAND SPEC", "CLINICAL SPEC")
            raw = raw.replace("Standard H-Band App", "Standard OEM App")
            raw = raw.replace("Standard H-Band", "Standard OEM")
            raw = raw.replace("H-Band mobile app", "OEM mobile app")
            raw = raw.replace("H-Band app", "OEM app")
            raw = raw.replace("H-Band's", "OEM app's")
            raw = raw.replace("H-Band", "OEM App")
            raw = raw.replace("HBandSDK Issue #46", "Veepoo Protocol Issue #46")
            raw = raw.replace("HBANDSDK COMMUNITY", "VEPOO PROTOCOL COMMUNITY")
            raw = raw.replace("HBandSDK", "Veepoo Protocol")
            raw = raw.replace("HBANDSDK", "VEEPOO-SDK")
            raw = raw.replace("HBand", "OEM App")
            
            slides[idx - 1] = json.loads(raw)

    with open(path, "w", encoding="utf-8") as f:
        json.dump(slides, f, indent=2)

    print(f"[✔] Cleaned {modified_count} slides in {path}")

if __name__ == "__main__":
    clean_slides_data()
