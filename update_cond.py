import re

with open('D:/veepoo/test_universal_engine.py', 'r', encoding='utf-8') as f:
    code = f.read()

# Fix the condition
old_cond = '''    is_baked_dessert = any(w in text for w in ["cake", "cheesecake", "muffin", "muffins", "cookie", "cookies", "brownie", "brownies", "pancake", "pancakes", "waffle", "waffles", "pie", "bread"])
    is_diabetic_adapted = any(w in text for w in ["zero sugar", "sugar free", "no sugar", "keto", "cottage cheese", "almond flour", "low carb", "protein cake", "protein muffin"])'''

new_cond = '''    is_savory_meal = any(w in text for w in ["chicken", "turkey", "steak", "beef", "ribeye", "sirloin", "roast", "pork", "fish", "halibut", "cod", "salmon", "shrimp", "soup", "salad"])
    is_baked_dessert = any(w in text for w in ["cake", "cheesecake", "muffin", "muffins", "cookie", "cookies", "brownie", "brownies", "pancake", "pancakes", "waffle", "waffles", "pie"]) or ("bread" in text and not any(w in text for w in ["cornbread", "corn bread", "sandwich", "toast"]))
    is_diabetic_adapted = any(w in text for w in ["zero sugar", "sugar free", "no sugar", "keto", "cottage cheese", "almond flour", "low carb", "protein cake", "protein muffin"])

    if is_baked_dessert and is_diabetic_adapted and not is_savory_meal:'''

code = code.replace(old_cond + '\n\n    if is_baked_dessert and is_diabetic_adapted:', new_cond, 1)

with open('D:/veepoo/test_universal_engine.py', 'w', encoding='utf-8') as f:
    f.write(code)

print("[+] Updated condition in test_universal_engine.py")
