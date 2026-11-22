with open('D:/veepoo/w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    py = f.read()

import re

pos = py.find('/api/meal/upload')
if pos != -1:
    print("=== /api/meal/upload in server.py ===")
    print(py[pos-50:pos+2500])

pos_rec = py.find('def get_recent_meals')
if pos_rec != -1:
    print("\n=== get_recent_meals ===")
    print(py[pos_rec-50:pos_rec+1000])

pos_pop = py.find('popular_meals')
if pos_pop != -1:
    print("\n=== popular_meals in server.py ===")
    print(py[pos_pop-100:pos_pop+1500])
