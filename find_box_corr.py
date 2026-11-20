with open('D:/veepoo/w11_htm_app/index.html', 'r', encoding='utf-8') as f:
    lines = f.readlines()

for i, l in enumerate(lines):
    if 'id="boxExerciseMetabolicCorrelation"' in l:
        print(f"Found on line {i+1}:")
        for j in range(max(0, i-2), min(len(lines), i+35)):
            print(f"{j+1}: {lines[j]}", end='')
        break
