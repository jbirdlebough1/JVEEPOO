import json
import re
import subprocess

def main():
    p = r"D:\veepoo\all_slides_data.json"
    with open(p, "r", encoding="utf-8") as f:
        text = f.read()

    print("Before clean, occurrences:")
    print("  #050b14:", text.count("#050b14"))
    print("  #1e1b2e:", text.count("#1e1b2e"))
    print("  #052e16:", text.count("#052e16"))

    # Replace #050b14 with #ffffff
    text = text.replace("#050b14", "#ffffff")
    # Replace #1e1b2e with #fef2f2 (soft red)
    text = text.replace("#1e1b2e", "#fef2f2")
    # Replace #052e16 with #f0fdf4 (soft green)
    text = text.replace("#052e16", "#f0fdf4")

    # In Slide 52, update text colors for the light cards
    text = text.replace("color: #f87171;", "color: #b91c1c;")
    text = text.replace("color: #4ade80;", "color: #15803d;")

    with open(p, "w", encoding="utf-8") as f:
        f.write(text)

    print("After clean, occurrences:")
    print("  #050b14:", text.count("#050b14"))
    print("  #1e1b2e:", text.count("#1e1b2e"))
    print("  #052e16:", text.count("#052e16"))

    # Now rebuild presentation
    print("\n[*] Rebuilding presentation with build_presentation.py...")
    subprocess.run(["D:\\veepoo\\tools\\python\\python.exe", "D:\\veepoo\\build_presentation.py"], check=True)
    print("[✔] Presentation rebuild complete!")

if __name__ == "__main__":
    main()
