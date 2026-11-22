with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    ht = f.read()

old_html_block = '''      salmon: {
        id: "MEAL-06",
        title: "Wild Alaskan Salmon, Roasted Asparagus & Half Sweet Potato",
        desc: "6 oz wild-caught salmon fillet grilled with olive oil and dill, roasted asparagus spears, and 1/2 baked sweet potato.",
        calories: 410,
        netCarbs: 22,
        totalCarbs: 28,
        fiber: 6,
        protein: 38,
        fat: 18,
        gl: "6 (LOW)",
        meetsGoal: true,
        tips: [
          "Omega-3 fatty acids reduce systemic inflammation.",
          "Complex oligosaccharides in asparagus support healthy gut microbiome."
        ],
        glut4: "15-minute relaxed walk promotes digestion.",
        glut4Peak: "PREDICTED PEAK: 114 MG/DL (EXCELLENT CONTROL)"
      },'''

new_html_block = '''      soup_grilled_cheese: {
        id: "MEAL-06",
        title: "Weekly Vegetable & Meat Soup with Whole Wheat Grilled Cheese, Mini Orange & Apple Slices",
        desc: "Weekly vegetable & meat soup, whole wheat grilled cheese sandwich, 1 miniature orange (1.5oz), 1/2 apple slices.",
        calories: 493,
        netCarbs: 34,
        totalCarbs: 43.2,
        fiber: 9.2,
        protein: 30.6,
        fat: 22.2,
        gl: "8 (LOW)",
        meetsGoal: true,
        tips: [
          "Vegetable fiber and beef broth proteins blunt postprandial glycemic excursions.",
          "Sprouted whole wheat and cheddar cheese lipid buffer prevents sharp glucose spike.",
          "Small fruit portioning (1.5oz orange + 1/2 apple) stays strictly within 45g net carb ceiling."
        ],
        glut4: "15-minute post-meal GLUT4 walk promotes peripheral glucose uptake.",
        glut4Peak: "PREDICTED PEAK: 126 MG/DL (OPTIMAL GLYCEMIC REGULATION)"
      },'''

assert old_html_block in ht, "old_html_block not found in index.html!"
ht = ht.replace(old_html_block, new_html_block)

# Replace option in select
old_opt = '<option value="salmon">🍣 Wild Pacific Salmon, Roasted Asparagus &amp; Half Sweet Potato (24g Net Carbs)</option>'
new_opt = '<option value="soup_grilled_cheese">🍲 Weekly Vegetable &amp; Meat Soup + Grilled Cheese (34g Net Carbs)</option>'
if old_opt in ht:
    ht = ht.replace(old_opt, new_opt)

# Replace selectMealBuilderPreset
old_preset = '''      } else if (text.includes("salmon") || text.includes("fish")) {
        data = POPULAR_MEALS.salmon || DEFAULT_POPULAR_MEALS.salmon;'''
new_preset = '''      } else if (text.includes("soup") || text.includes("grilled cheese")) {
        data = POPULAR_MEALS.soup_grilled_cheese || DEFAULT_POPULAR_MEALS.soup_grilled_cheese;'''
if old_preset in ht:
    ht = ht.replace(old_preset, new_preset)

with open('D:/veepoo/index.html', 'w', encoding='utf-8') as f:
    f.write(ht)

with open('D:/veepoo/w11_htm_app/index.html', 'w', encoding='utf-8') as f:
    f.write(ht)

print("index.html and w11_htm_app/index.html updated cleanly!")
