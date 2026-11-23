with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

# Find the second occurrence of "elif self.path.startswith(\"/api/meal/daily_summary\"):"
p1 = text.find('elif self.path.startswith("/api/meal/daily_summary"):')
p2 = text.find('elif self.path.startswith("/api/meal/daily_summary"):', p1 + 50)
if p2 != -1:
    # Find the next elif in do_POST
    next_elif = text.find('elif self.path == "/api/meal/submit":', p2)
    if next_elif == -1:
        next_elif = text.find('elif self.path == "/api/meal/upload":', p2)
    if next_elif != -1:
        # Check what was between p2 and next_elif
        print("Removing redundant block of length:", next_elif - p2)
        text = text[:p2] + text[next_elif:]
        with open(r'D:\veepoo\w11_htm_app\server.py', 'w', encoding='utf-8') as f:
            f.write(text)
        print("Removed redundant daily_summary from do_POST!")
    else:
        print("Could not find next_elif")
else:
    print("No second occurrence found")
