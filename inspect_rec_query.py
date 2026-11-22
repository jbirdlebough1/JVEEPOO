with open(r'D:\veepoo\w11_htm_app\server.py', 'r', encoding='utf-8') as f:
    text = f.read()

idx = text.find('if is_recommendation_query:')
print(text[idx:idx+4500])
