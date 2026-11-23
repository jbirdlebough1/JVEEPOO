import re

def test_queries():
    # 1. Test compound query
    q1 = "hot dog foot long Costo, tea"
    print("Testing q1:", q1)
    # Check regex
    has_tea_or_drink_ref = any(w in q1.lower() for w in ["tea", "snack_clove_tea", "clove tea", "cinnamon tea", "herbal tea", "diet coke", "drink", "water"])
    has_hotdog_or_food = any(w in q1.lower() for w in ["hot dog", "hotdog", "frank", "costco", "costo", "burger", "pizza", "sandwich"])
    print("  has_tea:", has_tea_or_drink_ref, "has_food:", has_hotdog_or_food)
    
    # 2. Test tea with spices
    q2 = "tea black+green bag, three cloves, cinnimon, nutmeg, 12oz water"
    print("\nTesting q2:", q2)
    has_black = "black" in q2.lower()
    has_green = "green" in q2.lower()
    has_nutmeg = "nutmeg" in q2.lower()
    has_clove = "clove" in q2.lower() or "cloves" in q2.lower()
    has_cinn = "cinnimon" in q2.lower() or "cinnamon" in q2.lower()
    m_fl = re.search(r'(\d+(?:\.\d+)?)\s*(?:fl\s*oz|oz\b|ounces?)', q2.lower())
    fl = float(m_fl.group(1)) if m_fl else 12.0
    print("  black:", has_black, "green:", has_green, "nutmeg:", has_nutmeg, "clove:", has_clove, "cinn:", has_cinn, "fluid:", fl)

    # 3. Test recipe
    q3 = "Recipe: 2 cups almond flour, 1 cup Greek yogurt, 4 eggs, 1 cup blueberries, 1/2 cup allulose. Batch makes 8 servings"
    print("\nTesting q3:", q3)
    is_recipe = any(w in q3.lower() for w in ["recipe", "batch", "servings", "makes", "ingredients", "materials total", "whole pan", "yield"])
    m_srv = re.search(r'(?:makes?|servings?|yields?|serves?|portions?)\s*[:=]?\s*(\d+)', q3.lower())
    srv = int(m_srv.group(1)) if m_srv else 8
    print("  is_recipe:", is_recipe, "servings:", srv)

test_queries()
