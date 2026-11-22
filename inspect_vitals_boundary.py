with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'id="pageVitals"' in l:
        p_vitals_start = i
    if 'id="pageCardio"' in l:
        p_vitals_end = i
        break

print(f"pageVitals from line {p_vitals_start+1} to {p_vitals_end+1}")
print("First 10 lines:")
for j in range(p_vitals_start, p_vitals_start+10):
    print(f"{j+1}: {lines[j]}", end='')

print("Last 10 lines before pageCardio:")
for j in range(p_vitals_end-10, p_vitals_end+2):
    print(f"{j+1}: {lines[j]}", end='')
