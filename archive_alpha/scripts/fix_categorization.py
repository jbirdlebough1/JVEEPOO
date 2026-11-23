# Fix server.py
with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

old_server_categorize = '''                    # Infer meal type from name or notes if not explicit
                    name_lower = (it["meal_name"] + " " + it["source"] + " " + it["gemini_notes"]).lower()
                    if "breakfast" in name_lower or "morning" in name_lower or "omelet" in name_lower or "egg" in name_lower or "coffee" in name_lower:
                        m_type = "Breakfast"
                    elif "lunch" in name_lower or "sandwich" in name_lower or "burger" in name_lower or "wrap" in name_lower or "salad" in name_lower:
                        m_type = "Lunch"
                    elif "dinner" in name_lower or "supper" in name_lower or "steak" in name_lower or "salmon" in name_lower or "taco" in name_lower:
                        m_type = "Dinner"
                    else:
                        m_type = "Snack"'''

new_server_categorize = '''                    # Infer meal type from name and hour
                    name_lower = it["meal_name"].lower()
                    hour = 12
                    if " " in it["timestamp"]:
                        try:
                            hour = int(it["timestamp"].split(" ")[1].split(":")[0])
                        except Exception:
                            pass

                    if "[snack]" in name_lower or "snack" in name_lower or "infusion" in name_lower or " tea" in name_lower or "tea " in name_lower:
                        m_type = "Snack"
                    elif "breakfast" in name_lower or "[breakfast]" in name_lower or hour < 11:
                        m_type = "Breakfast"
                    elif "lunch" in name_lower or "[lunch]" in name_lower or (11 <= hour < 16):
                        m_type = "Lunch"
                    elif "dinner" in name_lower or "[dinner]" in name_lower or hour >= 16:
                        m_type = "Dinner"
                    else:
                        m_type = "Snack"'''

assert old_server_categorize in text, "old_server_categorize not found"
text = text.replace(old_server_categorize, new_server_categorize, 1)

with open(r'D:\veepoo\w11_htm_app\server.py', 'w', encoding='utf-8') as f:
    f.write(text)

print("Updated server.py categorization!")

# Fix index.html
with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    html = f.read()

old_html_categorize = '''        if (nameStr.includes('[snack]') || nameStr.includes('tea') || nameStr.includes('infusion') || nameStr.includes('snack') || nameStr.includes('shake') || nameStr.includes('cookie')) {
          occ = 'Snack';
        } else if (nameStr.includes('[breakfast]') || nameStr.includes('breakfast') || hour < 11) {
          occ = 'Breakfast';
        } else if (nameStr.includes('[lunch]') || nameStr.includes('lunch') || (hour >= 11 && hour < 16)) {
          occ = 'Lunch';
        } else if (nameStr.includes('[dinner]') || nameStr.includes('dinner') || nameStr.includes('supper') || hour >= 16) {
          occ = 'Dinner';
        }'''

new_html_categorize = '''        const mealTitleLower = (m.meal_name || '').toLowerCase();
        if (mealTitleLower.includes('[snack]') || mealTitleLower.includes('snack') || mealTitleLower.includes('infusion') || /\\btea\\b/i.test(mealTitleLower) || mealTitleLower.includes('cookie')) {
          occ = 'Snack';
        } else if (mealTitleLower.includes('[breakfast]') || mealTitleLower.includes('breakfast') || hour < 11) {
          occ = 'Breakfast';
        } else if (mealTitleLower.includes('[lunch]') || mealTitleLower.includes('lunch') || (hour >= 11 && hour < 16)) {
          occ = 'Lunch';
        } else if (mealTitleLower.includes('[dinner]') || mealTitleLower.includes('dinner') || hour >= 16) {
          occ = 'Dinner';
        } else {
          occ = 'Snack';
        }'''

assert old_html_categorize in html, "old_html_categorize not found"
html = html.replace(old_html_categorize, new_html_categorize, 1)

with open(r'D:\veepoo\index.html', 'w', encoding='utf-8') as f:
    f.write(html)

with open(r'D:\veepoo\w11_htm_app\index.html', 'w', encoding='utf-8') as f:
    f.write(html)

print("Updated index.html files categorization!")
