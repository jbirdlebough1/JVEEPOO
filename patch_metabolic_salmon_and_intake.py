import re

# 1. Update server.py
with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    server_text = f.read()

old_server_salmon = '''            "salmon": {
                "id": "salmon",
                "title": "Wild Alaskan Salmon & Roasted Asparagus",
                "description": "6 oz wild-caught salmon fillet grilled with dill, roasted asparagus spears, and 1/2 baked sweet potato.",
                "calories": 410,
                "net_carbs": 22.0,
                "total_carbs": 28.0,
                "fiber": 6.0,
                "protein": 38.0,
                "fat": 18.0,
                "glycemic_load": "6 (Low)",
                "tips": [
                    "Omega-3 fatty acids reduce systemic inflammation.",
                    "Low glycemic oligosaccharides."
                ],
                "meets_goal": 1
            },'''

new_server_soup = '''            "soup_grilled_cheese": {
                "id": "soup_grilled_cheese",
                "title": "Weekly Vegetable & Meat Soup with Whole Wheat Grilled Cheese, Mini Orange & Apple Slices",
                "description": "Weekly vegetable & meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices.",
                "calories": 493,
                "net_carbs": 34.0,
                "total_carbs": 43.2,
                "fiber": 9.2,
                "protein": 30.6,
                "fat": 22.2,
                "glycemic_load": "8 (Low)",
                "tips": [
                    "Vegetable fiber and beef broth proteins blunt postprandial glycemic excursions.",
                    "Sprouted whole wheat and real cheddar provide complex lipid-protein buffering.",
                    "Small fruit portioning (1.5oz orange + 1/2 apple) stays strictly within 45g net carb ceiling."
                ],
                "meets_goal": 1
            },'''

if old_server_salmon in server_text:
    server_text = server_text.replace(old_server_salmon, new_server_soup)
    with open('D:/veepoo/w11_htm_app/server.py', 'w', encoding='utf-8') as f:
        f.write(server_text)
    print("server.py updated: salmon replaced with soup_grilled_cheese!")
else:
    print("old_server_salmon not found or already replaced in server.py")

# 2. Update index.html and w11_htm_app/index.html
with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    html = f.read()

# Replace salmon in POPULAR_MEALS
old_html_salmon = '''      salmon: {
        id: "salmon",
        title: "Wild Alaskan Salmon, Roasted Asparagus & Half Sweet Potato",
        desc: "6 oz wild-caught salmon fillet grilled with olive oil and dill, roasted asparagus spears, and 1/2 medium baked sweet potato.",
        netCarbs: 24,
        totalCarbs: 30,
        calories: 420,
        protein: 38,
        fiber: 6,
        fat: 18,
        glycemicLoad: "7 (Low Glycemic Impact)",
        tips: [
          "Rich in EPA/DHA marine omega-3 fatty acids that enhance insulin sensitivity.",
          "Asparagus provides prebiotic inulin fiber supporting gut microbiome stability."
        ]
      },'''

new_html_soup = '''      soup_grilled_cheese: {
        id: "soup_grilled_cheese",
        title: "Weekly Vegetable & Meat Soup with Whole Wheat Grilled Cheese, Mini Orange & Apple Slices",
        desc: "Weekly vegetable & meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices.",
        netCarbs: 34,
        totalCarbs: 43.2,
        calories: 493,
        protein: 30.6,
        fiber: 9.2,
        fat: 22.2,
        glycemicLoad: "8 (Low Glycemic Impact)",
        tips: [
          "Vegetable fiber and beef broth proteins blunt postprandial glycemic excursions.",
          "Sprouted whole wheat and real cheddar provide complex lipid-protein buffering.",
          "Small fruit portioning (1.5oz miniature orange + 1/2 apple) stays strictly within 45g net carb ceiling."
        ]
      },'''

if old_html_salmon in html:
    html = html.replace(old_html_salmon, new_html_soup)
    print("Replaced salmon in POPULAR_MEALS object!")

# Replace option in listbox
old_opt = '<option value="salmon">🍣 Wild Pacific Salmon, Roasted Asparagus &amp; Half Sweet Potato (24g Net Carbs)</option>'
new_opt = '<option value="soup_grilled_cheese">🍲 Weekly Vegetable &amp; Meat Soup + Grilled Cheese (34g Net Carbs)</option>'
if old_opt in html:
    html = html.replace(old_opt, new_opt)
    print("Replaced salmon option in listbox!")

# Replace onSelectPopularMealFromListbox handler
old_handler = '''        } else if (mealKey === 'salmon') {
          input.value = "Wild Pacific Salmon, roasted asparagus, half sweet potato";'''
new_handler = '''        } else if (mealKey === 'soup_grilled_cheese') {
          input.value = "Weekly vegetable and meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices";'''
if old_handler in html:
    html = html.replace(old_handler, new_handler)
    print("Replaced handler in onSelectPopularMealFromListbox!")

# Replace selectMealBuilderPreset handler
old_preset = '''      } else if (text.includes("salmon") || text.includes("fish")) {
        data = POPULAR_MEALS.salmon || DEFAULT_POPULAR_MEALS.salmon;'''
new_preset = '''      } else if (text.includes("soup") || text.includes("grilled cheese")) {
        data = POPULAR_MEALS.soup_grilled_cheese || DEFAULT_POPULAR_MEALS.soup_grilled_cheese;'''
if old_preset in html:
    html = html.replace(old_preset, new_preset)
    print("Replaced handler in selectMealBuilderPreset!")

# Refine renderDietHistory food component extraction for Daily Intake Summary
old_comp_extract = '''        // Extract food components
        const comps = m.identified_items || (m.raw_json ? (typeof m.raw_json === 'string' ? JSON.parse(m.raw_json).identified_items : m.raw_json.identified_items) : null);
        if (comps && Array.isArray(comps)) {
          comps.forEach(c => occasions[occ].foodComponents.push(c.name || c.item || 'Food Item'));
        }'''

new_comp_extract = '''        // Extract food components
        let comps = null;
        try {
          if (m.identified_items && Array.isArray(m.identified_items)) {
            comps = m.identified_items;
          } else if (m.raw_json) {
            const parsed = typeof m.raw_json === 'string' ? JSON.parse(m.raw_json) : m.raw_json;
            if (parsed && Array.isArray(parsed.identified_items)) {
              comps = parsed.identified_items;
            }
          }
        } catch (e) {}

        if (comps && Array.isArray(comps) && comps.length > 0) {
          comps.forEach(c => occasions[occ].foodComponents.push(c.name || c.item || 'Food Item'));
        } else if (m.meal_name) {
          // If no raw_json items, split meal title into distinct components if comma-separated
          const cleanName = m.meal_name.replace(/\[[^\]]+\]/g, '').trim();
          if (cleanName.includes(',')) {
            cleanName.split(',').forEach(p => occasions[occ].foodComponents.push(p.trim()));
          } else {
            occasions[occ].foodComponents.push(cleanName);
          }
        }'''

if old_comp_extract in html:
    html = html.replace(old_comp_extract, new_comp_extract)
    print("Refined food component extraction in renderDietHistory!")

# Write to both files
with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(html)

print("Both index.html files updated cleanly!")
