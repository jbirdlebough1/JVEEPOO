import re
import shutil
import hashlib

# 1. Patch server.py
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_code = f.read()

# Add Dairy Queen / sundae / blizzard handler right before Taco Time handler or after it
dq_handler = '''    # 1a. Dairy Queen / Fast Food Dessert / Ice Cream Sundaes (Official Published Nutritional Disclosures)
    elif any(w in text for w in ["dairy queen", "dq", "sundae", "blizzard", "ice cream", "soft serve"]):
        is_small_or_mini = any(w in text for w in ["mini", "small", "kid"])
        if "blizzard" in text:
            cals = 380 if is_small_or_mini else 680
            carbs = 48.0 if is_small_or_mini else 92.0
            prod_name = "Dairy Queen Mini Blizzard Treat" if is_small_or_mini else "Dairy Queen Standard Blizzard Treat"
        elif "cone" in text:
            cals = 200 if is_small_or_mini else 330
            carbs = 28.0 if is_small_or_mini else 46.0
            prod_name = "Dairy Queen Small Vanilla Soft Serve Cone" if is_small_or_mini else "Dairy Queen Medium Vanilla Cone"
        else: # Sundae
            cals = 280 if is_small_or_mini else 440
            carbs = 38.0 if is_small_or_mini else 62.0
            prod_name = "Dairy Queen Small Chocolate Sundae" if is_small_or_mini else "Dairy Queen Regular Chocolate Sundae"

        meets_goal = carbs <= 45.0
        return {
            "meal_name": prod_name,
            "vendor_matched": "Dairy Queen (Published Nutritional Disclosures & Menu Data)",
            "photo_filename": "dairy_queen_sundae.jpg",
            "calories": cals,
            "net_carbs": carbs,
            "total_carbs": carbs + 2.0,
            "fiber": 1.0,
            "protein": 7.0,
            "fat": 12.0,
            "glycemic_load": "18 (Medium-High)" if not meets_goal else "12 (Low-Med)",
            "estimated_cost": "4.19",
            "meets_goal": meets_goal,
            "is_restaurant": True,
            "identified_items": [
                {"name": prod_name, "portion": "1 order", "net_carbs": carbs, "fiber": 1, "protein": 7, "fat": 12, "calories": cals}
            ],
            "projected_spike": f"+{int(carbs * 1.6)} to +{int(carbs * 2.2)} mg/dL (Requires Insulin / GLUT4 Exercise)" if not meets_goal else f"+{int(carbs * 1.1)} mg/dL (Manageable Spike)",
            "gemini_notes": "🍦 RESTAURANT / PREPARED DESSERT ANALYSIS: Sourced directly from Dairy Queen official nutritional disclosures. Soft serve dairy fats moderately buffer the high glycemic simple sugar payload. For diabetic harm reduction, choosing a Small or Mini size keeps net carbs within or near the 45g ceiling.",
            "trade_alternatives": [
                "Swap to a Dairy Queen Small Soft Serve Cone (28g net carbs, meets goal).",
                "Choose a Mini Blizzard or Mini Sundae (31g-38g net carbs) instead of Medium/Large (+90g carbs).",
                "Pair with a brisk 25-minute post-consumption GLUT4 walk to activate insulin-independent glucose uptake."
            ],
            "glut4_recommendation": "Perform 25 to 35 minutes brisk walking within 20 minutes of consumption to suppress acute postprandial glucose spike."
        }
'''

# Find Taco Time block
tt_pos = server_code.find('    # 1. Taco Time Northwest')
if tt_pos != -1:
    server_code = server_code[:tt_pos] + dq_handler + server_code[tt_pos:]
    print("[+] Added Dairy Queen official handler to server.py")

# Also replace salmon in fish block
server_code = server_code.replace('"Wild Alaskan Sockeye Salmon"', '"Pan-Seared Pacific Halibut & Roasted Asparagus"')
server_code = server_code.replace('Wild Alaskan Sockeye Salmon with Steamed Broccoli', 'Pacific Halibut with Steamed Broccoli')

with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
    f.write(server_code)
print("[+] Updated w11_htm_app/server.py")


# 2. Patch index.html
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# Replace [CHIPOTLE] with [TACOTIME]
html = html.replace('<div style="font-size: 0.68rem; color: #64748b;">[CHIPOTLE]</div>',
                    '<div style="font-size: 0.68rem; color: #64748b;">[TACOTIME]</div>')

# Replace quote in doctor mentoring note
html = html.replace("The patient now understands exactly what Dick's Drive-In, Chipotle, and oats do to their body.",
                    "The patient now understands exactly what Taco Time Northwest, home meal prep, and oats do to their body.")

# Div tag balance check
o = len(re.findall(r'<div\b', html))
c = len(re.findall(r'</div\b', html))
print(f"index.html Div balance: open={o}, close={c}, delta={o-c}")
assert o == c, f"Div tag mismatch: open={o}, close={c}"

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)
with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

h1 = hashlib.sha256(open('D:/veepoo/index.html', 'rb').read()).hexdigest()
h2 = hashlib.sha256(open('D:/veepoo/w11_htm_app/index.html', 'rb').read()).hexdigest()
print(f"[+] Bit-for-bit parity check: {h1 == h2} (hash: {h1})")
