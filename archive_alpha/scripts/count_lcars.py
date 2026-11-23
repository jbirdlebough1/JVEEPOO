html = open(r"D:\veepoo\w11_htm_app\index.html", encoding="utf-8").read()
import re
lcars_matches = re.findall(r'\blcars-[a-zA-Z0-9_-]+', html)
print(f"Total lcars- class / token occurrences: {len(lcars_matches)}")
unique_classes = set(lcars_matches)
print(f"Unique lcars-* classes: {len(unique_classes)}")
print(sorted(list(unique_classes))[:25])
