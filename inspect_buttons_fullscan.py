with open(r'D:\veepoo\index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'id="btnSkipFullScan"' in l:
        print(f"btnSkipFullScan on line {i+1}:")
        for j in range(max(0, i-5), min(len(lines), i+10)):
            print(f"{j+1}: {lines[j]}", end='')
        break

for i, l in enumerate(lines):
    if 'RUN ANOTHER FULL SCAN' in l:
        print(f"\nRUN ANOTHER FULL SCAN on line {i+1}:")
        for j in range(max(0, i-5), min(len(lines), i+10)):
            print(f"{j+1}: {lines[j]}", end='')
        break
