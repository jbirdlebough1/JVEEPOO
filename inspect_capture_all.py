with open('run_feature_tests_and_screenshots.py', 'r', encoding='utf-8') as f:
    text = f.read()

pos = text.find('async def capture')
if pos == -1:
    pos = text.find('Capture')
if pos == -1:
    pos = 1000
print(text[pos:pos+3000])
