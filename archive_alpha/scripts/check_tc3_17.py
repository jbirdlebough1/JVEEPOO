with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

print("fetchTelemetryLatest:", "async function fetchTelemetryLatest()" in text)
print("fetchTelemetryTimeline:", "async function fetchTelemetryTimeline()" in text)
print("selPopularMealsListbox:", "selPopularMealsListbox" in text)
print("initPopularMeals:", "initPopularMeals" in text)

# Also let's check what was around fetchTelemetryLatest
idx = text.find('fetchTelemetryLatest')
if idx != -1:
    print(text[idx-100:idx+500])
