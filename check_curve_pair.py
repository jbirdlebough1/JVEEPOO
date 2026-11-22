with open(r'D:\veepoo\apply_master_overhaul.py', 'r', encoding='utf-8') as f:
    master_code = f.read()

s_m_curve = master_code.find('new_curve_pair = """') + len('new_curve_pair = """')
e_m_curve = master_code.find('\n    """\n\ntext = text[:start_curve]')
new_curve_pair = master_code[s_m_curve:e_m_curve]

print("new_curve_pair length:", len(new_curve_pair))
print("open {:", new_curve_pair.count('{'))
print("close }:", new_curve_pair.count('}'))
