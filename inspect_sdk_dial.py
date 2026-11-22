import glob, re

paths = (glob.glob('D:/veepoo/opt1_sdk_official/android_sdk_source/apidoc/com/veepoo/protocol/model/*Dial*.html') + 
         glob.glob('D:/veepoo/opt1_sdk_official/android_sdk_source/apidoc/com/veepoo/protocol/util/*Dial*.html') + 
         glob.glob('D:/veepoo/opt1_sdk_official/android_sdk_source/apidoc/com/veepoo/protocol/model/TUi*.html'))

for fpath in paths:
    with open(fpath, 'r', encoding='utf-8', errors='ignore') as f:
        html = f.read()
    print('=== File:', fpath)
    methods = re.findall(r'<span class="memberNameLink"><a href="[^"]*">([^<]+)</a></span>', html)
    print('  Methods/Fields:', methods[:15])
    # Extract plain text summary
    clean = re.sub(r'<[^>]+>', ' ', html)
    clean = re.sub(r'\s+', ' ', clean)
    print('  Snippet:', clean[:300])
