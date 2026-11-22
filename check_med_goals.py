with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    text = f.read()

lines = text.splitlines()

print("--- Searching for page-medication and page-goals in HTML ---")
for i, line in enumerate(lines):
    if 'id="page-medication"' in line or 'id="page-goals"' in line or 'id="pageMedication"' in line or 'id="pageGoals"' in line:
        print(f"L{i+1}: {line}")
        # print next 30 lines
        for j in range(i, min(i+40, len(lines))):
            print(f"  {j+1}: {lines[j]}")

print("\n--- Searching for switchTab function ---")
for i, line in enumerate(lines):
    if 'function switchTab' in line:
        print(f"switchTab at L{i+1}:")
        for j in range(i, min(i+60, len(lines))):
            print(f"  {j+1}: {lines[j]}")
        break

print("\n--- Searching for medication and goals in switchTab ---")
for i, line in enumerate(lines):
    if 'switchTab' in line and ('medication' in line or 'goals' in line):
        print(f"L{i+1}: {line}")
