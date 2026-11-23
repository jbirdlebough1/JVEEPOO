with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

target = """                    if "tips" in analysis and isinstance(analysis["tips"], list):
                        analysis["tips"].insert(0, polyol_warn)"""

replacement = """                    if "tips" not in analysis or not isinstance(analysis["tips"], list):
                        analysis["tips"] = []
                    analysis["tips"].insert(0, polyol_warn)"""

assert target in text, "target not found in server.py"
text = text.replace(target, replacement, 1)

with open(r'D:\veepoo\w11_htm_app\server.py', 'w', encoding='utf-8') as f:
    f.write(text)

print("server.py updated with safe tips array initialization.")
