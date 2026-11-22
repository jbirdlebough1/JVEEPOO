import urllib.request
import json

try:
    resp = urllib.request.urlopen("http://127.0.0.1:8899/api/meal/daily_summary")
    data = json.loads(resp.read().decode("utf-8"))
    print("Daily summary status: OK")
    print("Date:", data.get("date"))
    print("Total consumed count:", data.get("total_consumed_count"))
    print("Total calories:", data.get("total_calories"))
    print("Total net carbs:", data.get("total_net_carbs"))
    print("By type keys:", list(data.get("by_type", {}).keys()))
except Exception as e:
    print("Daily summary error:", e)
