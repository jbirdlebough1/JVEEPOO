with open(r'D:\veepoo\apply_master_overhaul.py', 'r', encoding='utf-8') as f:
    master_code = f.read()

s_m_rep = master_code.find('new_reports_view = """') + len('new_reports_view = """')
e_m_rep = master_code.find('\n    """\n\ntext = text[:start_rep]')
print("s_m_rep:", s_m_rep, "e_m_rep:", e_m_rep)
print("new_reports_view preview:")
print(master_code[s_m_rep:s_m_rep+500])
print("\nend of new_reports_view:")
print(master_code[e_m_rep-300:e_m_rep])
