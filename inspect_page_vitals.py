with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

m_vitals = text.find('id="pageVitals"')
m_next = text.find('id="pageCardio"')
print("pageVitals text length:", m_next - m_vitals)
print(text[m_vitals:m_vitals+3500])
