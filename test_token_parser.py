import re

def parse_safe_option_query(text):
    t = text.lower()
    # Check for trigger tokens
    tokens = [
        r'find\s+me\s+a\s+(?:sugar\s*free|diabetic|keto|low\s*carb|safe)?\s*(?:dessert|treat|snack|meal|option)?\s*like\s*[\'"]?([^\'"]+)[\'"]?',
        r'sugar\s*free\s*(?:dessert|treat|snack|meal)?\s*like\s*[\'"]?([^\'"]+)[\'"]?',
        r'healthy\s*alternative\s*to\s*[\'"]?([^\'"]+)[\'"]?',
        r'safe\s*(?:option|dessert|snack|treat)\s*for\s*[\'"]?([^\'"]+)[\'"]?'
    ]

    query_target = None
    for pattern in tokens:
        m = re.search(pattern, t)
        if m:
            query_target = m.group(1).strip()
            break

    return query_target

queries = [
    "find me a sugar free dessert like 'blueberry cake'",
    "find me a sugar free dessert like cottage cheese cake",
    "find me a diabetic treat like 'ice cream parfait'",
    "find me a safe snack like 'chocolate candy'",
    "sugar free dessert like 'apple pie'",
    "regular 4 oz chicken breast and salad"
]

print("Testing token parser:")
for q in queries:
    target = parse_safe_option_query(q)
    print(f"'{q}' -> TARGET: {repr(target)}")
