with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

p1 = text.find('elif self.path.startswith("/api/meal/daily_summary"):')
p2 = text.find('elif self.path.startswith("/api/meal/daily_summary"):', p1 + 50)
if p2 != -1:
    print(text[p2+2500:p2+3500])
