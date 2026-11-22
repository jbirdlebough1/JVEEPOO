with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    lines = f.readlines()

print("Around line 1596:")
for j in range(1590, 1605):
    print(f"{j+1}: {lines[j]}", end='')

print("\nAround line 1773:")
for j in range(1765, 1785):
    print(f"{j+1}: {lines[j]}", end='')
