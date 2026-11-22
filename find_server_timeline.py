with open('w11_htm_app/server.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('/api/telemetry/timeline')
if pos != -1:
    line_no = text[:pos].count('\n') + 1
    print('Found in server.py at line:', line_no)
    lines_arr = text.splitlines()
    for j in range(max(0, line_no - 5), min(line_no + 80, len(lines_arr))):
        print(f'{j+1}: {lines_arr[j]}')
else:
    print('NOT FOUND in server.py')
