with open("index.html", "r", encoding="utf-8") as f:
    lines = f.readlines()

for i in range(6030, 6060):
    print(f"{i+1}: {lines[i]}", end="")
