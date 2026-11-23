import re
import json

def test_decomposition():
    print("Testing compound and beverage parsing...")
    
    # Test 1: Costco Hot Dog + Saved Tea
    q1 = "hot dog foot long Costo, tea"
    print("Query 1:", q1)
    
    # Test 2: Custom spice tea
    q2 = "tea black+green bag, three cloves, cinnimon, nutmeg, 12oz water"
    print("Query 2:", q2)
    
    # Test 3: Recipe entry
    q3 = "Recipe: 2 cups almond flour, 1 cup Greek yogurt, 4 eggs, 1 cup blueberries, 1/2 cup allulose. Batch makes 8 servings"
    print("Query 3:", q3)

test_decomposition()
