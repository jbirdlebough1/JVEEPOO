with open(r'D:\veepoo\apply_master_overhaul.py', 'r', encoding='utf-8') as f:
    text = f.read()

# Locate new_curve_pair
p1 = text.find('new_curve_pair = """') + len('new_curve_pair = """')
p2 = text.find('"""\n\ntext = text[:start_curve]', p1)
print(f"new_curve_pair: [{p1}:{p2}] len={p2-p1}")
print("starts with:", repr(text[p1:p1+80]))
print("ends with:", repr(text[p2-80:p2]))

# Locate new_reports_view
p3 = text.find('new_reports_view = """') + len('new_reports_view = """')
p4 = text.find('"""\n\ntext = text[:start_rep]', p3)
print(f"\nnew_reports_view: [{p3}:{p4}] len={p4-p3}")
if p4 != -1:
    print("starts with:", repr(text[p3:p3+80]))
    print("ends with:", repr(text[p4-80:p4]))
else:
    # find where new_reports_view ends in apply_master_overhaul.py
    print("searching for end of new_reports_view...")
    p4_alt = text.find('text = text[:start_rep]', p3)
    print("p4_alt:", p4_alt)
    print(repr(text[p4_alt-100:p4_alt+50]))
