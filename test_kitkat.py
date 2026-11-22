import sys
sys.path.insert(0, r'D:\veepoo\w11_htm_app')
from server import analyze_meal_with_gemini_backend

try:
    res = analyze_meal_with_gemini_backend("kit kat bar 6 oz", "", "")
    print("SUCCESS:")
    print("meal_name:", res.get("meal_name"))
    print("calories:", res.get("calories"))
    print("net_carbs:", res.get("net_carbs"))
    print("status:", res.get("status"))
except Exception as e:
    import traceback
    print("EXCEPTION:")
    traceback.print_exc()
