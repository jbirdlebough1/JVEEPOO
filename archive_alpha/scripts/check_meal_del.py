with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

print("Has /api/meal/delete:", "/api/meal/delete" in text)
