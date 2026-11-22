with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

# Check where analysis is returned for /api/meal/analyze
idx = text.find('elif self.path == "/api/meal/upload" or self.path == "/api/meal/analyze":')
print("Found endpoint at:", idx)
print(text[idx:idx+1500])
