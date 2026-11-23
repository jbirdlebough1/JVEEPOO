with open(r'D:/veepoo/index.html', 'r', encoding='utf-8') as f:
    text = f.read()

for pid in ['pageLearning', 'pageGoals', 'pageExport', 'pageDietTrades', 'pageHelp', 'pageFullScan']:
    pos = text.find(f'id="{pid}"')
    if pos != -1:
        end_pos = text.find('</main>', pos) # or next page
        next_page = text.find('<div id="page', pos + 10)
        chunk_len = (next_page - pos) if next_page != -1 else 1000
        print(f"=== {pid} at {pos} (length {chunk_len}) ===")
        print(text[pos:pos+500])
        print("...")
        print(text[pos+chunk_len-300:pos+chunk_len] if chunk_len > 300 else "")
    else:
        print(f"=== {pid} NOT FOUND ===")
