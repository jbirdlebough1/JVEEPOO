import re, os

def remove_copilot_and_replace_with_coach():
    for p in [r"D:\veepoo\w11_htm_app\index.html", r"D:\veepoo\index.html"]:
        with open(p, "r", encoding="utf-8") as f:
            html = f.read()

        # 1. Remove the broken "00. SMART GLUCOSE-DRIVEN SNACK & MEAL NOTIFIER" component
        start_marker = "<!-- Component 0: Closed-Loop Glucose Smart Snack Notifier & Safe Operating Area -->"
        end_marker = "<!-- =========================================================================\n               COMPONENT 01: INTERACTIVE DIABETIC MEAL BUILDER"
        if start_marker in html:
            pos1 = html.find(start_marker)
            # Find next COMPONENT 01 or INTERACTIVE DIABETIC MEAL BUILDER
            pos2 = html.find("<!-- =========================================================================", pos1)
            if pos1 != -1 and pos2 != -1:
                html = html[:pos1] + html[pos2:]
                print(f"[✔] Removed broken 00. Smart Notifier card from {p}")
            else:
                print(f"[!] Could not find slicing markers in {p}")
        else:
            print(f"[-] start_marker not found in {p}")

        # 2. Replace all occurrences of Copilot with Coach
        html = html.replace("GEMINI NUTRITION COPILOT", "GEMINI NUTRITION COACH")
        html = html.replace("Gemini Nutrition Copilot", "Gemini Nutrition Coach")
        html = html.replace("GEMINI COPILOT ACTIVE", "GEMINI COACH ACTIVE")
        html = html.replace("Gemini Copilot", "Gemini Coach")
        html = html.replace("GEMINI NUTRITION ASSISTANT", "GEMINI NUTRITION COACH")
        html = html.replace("Gemini Nutrition Assistant", "Gemini Nutrition Coach")
        html = html.replace("GEMINI AI ASSISTANT ACTIVE", "GEMINI COACH ACTIVE")
        html = html.replace("Gemini Assistant", "Gemini Coach")
        html = html.replace("COPILOT", "COACH")
        html = html.replace("Copilot", "Coach")
        html = html.replace("copilot", "coach")

        with open(p, "w", encoding="utf-8") as f:
            f.write(html)
        print(f"[✔] Saved {p} with Coach replacement")

def clean_all_other_files():
    base = r"D:\veepoo"
    for root, dirs, files in os.walk(base):
        for f in files:
            if f.endswith(('.html', '.json', '.py', '.md', '.txt')):
                p = os.path.join(root, f)
                try:
                    with open(p, "r", encoding="utf-8", errors="ignore") as fp:
                        c = fp.read()
                    if re.search(r'(?i)copilot', c) and not f.startswith('audit_all_narrations'):
                        c = c.replace("GEMINI NUTRITION COPILOT", "GEMINI NUTRITION COACH")
                        c = c.replace("Gemini Nutrition Copilot", "Gemini Nutrition Coach")
                        c = c.replace("GEMINI COPILOT ACTIVE", "GEMINI COACH ACTIVE")
                        c = c.replace("Gemini Copilot", "Gemini Coach")
                        c = re.sub(r'\bCOPILOT\b', 'COACH', c)
                        c = re.sub(r'\bCopilot\b', 'Coach', c)
                        c = re.sub(r'\bcopilot\b', 'coach', c)
                        with open(p, "w", encoding="utf-8") as fp:
                            fp.write(c)
                        print(f"[✔] Replaced Copilot with Coach in {os.path.relpath(p, base)}")
                except Exception as ex:
                    pass

if __name__ == "__main__":
    remove_copilot_and_replace_with_coach()
    clean_all_other_files()
