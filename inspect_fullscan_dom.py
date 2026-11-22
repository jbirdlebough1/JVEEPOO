with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

# Check lines around pageFullScan (6890-6920)
for i in range(6890, 6920):
    print(f"{i+1}: {lines[i]}", end="")
