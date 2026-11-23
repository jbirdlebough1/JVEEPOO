import sys
sys.path.append(r"D:\veepoo\w11_htm_app")
import server

r1 = server.analyze_meal_with_gemini_backend("Dick's Drive-In", "Dick's Deluxe")
print("Dick's Deluxe Result:")
print("  Meal:", r1.get("meal_name"))
print("  Net Carbs:", r1.get("net_carbs"))
print("  Meets Goal:", r1.get("meets_goal"))
print("  Vendor:", r1.get("vendor_matched"))

r2 = server.analyze_meal_with_gemini_backend("Dick's Drive-In Deluxe & Fries")
print("\nDick's Deluxe & Fries Result:")
print("  Meal:", r2.get("meal_name"))
print("  Net Carbs:", r2.get("net_carbs"))
print("  Meets Goal:", r2.get("meets_goal"))

r3 = server.analyze_meal_with_gemini_backend("McDonald's Quarter Pounder")
print("\nMcDonald's Result:")
print("  Meal:", r3.get("meal_name"))
