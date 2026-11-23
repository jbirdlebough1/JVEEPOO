import re

with open(r'D:\veepoo\w11_htm_app\index.html', encoding='utf-8') as f:
    text = f.read()

script_match = re.search(r'<script>(.*?)</script>', text, re.DOTALL)
js = script_match.group(1) if script_match else ''
referenced_ids = set(re.findall(r"getElementById\(['\"]([a-zA-Z0-9_-]+)['\"]\)", js))
defined_ids = set(re.findall(r"id=['\"]([a-zA-Z0-9_-]+)['\"]", text))
missing = referenced_ids - defined_ids
print('Missing DOM IDs:', missing)
