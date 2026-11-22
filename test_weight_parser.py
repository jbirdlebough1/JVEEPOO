import re

def parse_weight_oz(text, default_oz=None):
    # Match e.g. "6 oz", "6oz", "6.5 ounces", "6 ounce"
    m = re.search(r'(\d+(?:\.\d+)?)\s*(?:oz|ounces?|ounce\b)', text, re.IGNORECASE)
    if m:
        return float(m.group(1))
    # Match grams e.g. "170g", "170 grams"
    m_g = re.search(r'(\d+(?:\.\d+)?)\s*(?:g|grams?)\b', text, re.IGNORECASE)
    if m_g:
        return float(m_g.group(1)) / 28.35
    # Match lbs
    m_lb = re.search(r'(\d+(?:\.\d+)?)\s*(?:lbs?|pounds?)\b', text, re.IGNORECASE)
    if m_lb:
        return float(m_lb.group(1)) * 16.0
    return default_oz

print("Testing weight parser:")
print("M and M's 6oz ->", parse_weight_oz("M and M's 6oz"))
print("Red Hots 6oz ->", parse_weight_oz("Red Hots 6oz"))
print("Almond Joy bar 8oz ->", parse_weight_oz("Almond Joy bar 8oz"))
print("Dairy Queen Peanut Buster Parfait ->", parse_weight_oz("Dairy Queen Peanut Buster Parfait"))
