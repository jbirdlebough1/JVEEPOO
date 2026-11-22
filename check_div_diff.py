with open('D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    orig = f.read()

flowchart_start_marker = '''            <!-- =========================================================================
                 VISUAL THINKING STYLE VERTICAL FLOWCHART PIPELINE
                 ========================================================================= -->
            <div style="background: #f8fafc; border: 1.5px solid #cbd5e1; border-radius: 8px; padding: 14px; margin-bottom: 18px;">'''

flowchart_end_marker = '''            <!-- =========================================================================
                 🩺 DOCTOR-PATIENT LONGITUDINAL MEAL METABOLIC EXPLORER'''

p1 = orig.find(flowchart_start_marker)
p2 = orig.find(flowchart_end_marker)

replaced_block = orig[p1:p2]
o1 = replaced_block.count('<div')
c1 = replaced_block.count('</div')
print(f'replaced block: open={o1}, close={c1}, delta={o1-c1}')

with open('D:/veepoo/apply_tacotime_flow_and_bom.py', 'r', encoding='utf-8') as f:
    text = f.read()

s_marker = 'new_pipeline_html = \'\'\''
e_marker = '\'\'\''
i1 = text.find(s_marker) + len(s_marker)
i2 = text.find(e_marker, i1)
pipe_str = text[i1:i2]
o2 = pipe_str.count('<div')
c2 = pipe_str.count('</div')
print(f'current new_pipeline_html: open={o2}, close={c2}, delta={o2-c2}')

pipe_str_fixed = pipe_str + '          </div>\n\n'
o3 = pipe_str_fixed.count('<div')
c3 = pipe_str_fixed.count('</div')
print(f'fixed new_pipeline_html: open={o3}, close={c3}, delta={o3-c3}')
