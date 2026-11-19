import urllib.request, urllib.parse, ssl, json, os

ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

catalog = []
seen_names = set()

# dialShapes matching rectangular 240x280 and related
shapes = [4, 9, 2, 7, 8, 14, 16, 17, 18, 20, 21]

for s in shapes:
    params = {
        'dialShape': str(s),
        'binProtocol': '1',
        'maxLength': '500000',
        'deviceNumber': '5010',
        'deviceVersion': '0.16.16.0',
        'appType': '1',
        'appVersion': '3.2.0'
    }
    query = urllib.parse.urlencode(params)
    url = f"https://www.vphband.com:9001/api/system/getthemes?{query}"
    req = urllib.request.Request(url, headers={'User-Agent': 'Mozilla/4.0', 'Accept': '*/*'})
    try:
        with urllib.request.urlopen(req, context=ctx, timeout=4) as r:
            res = r.read().decode('utf-8')
            if res and res != 'null':
                data = json.loads(res)
                for item in data:
                    name = item.get('name')
                    if name and name not in seen_names:
                        seen_names.add(name)
                        catalog.append({
                            "name": name,
                            "dialShape": s,
                            "previewUrl": item.get("previewUrl"),
                            "fileUrl": item.get("fileUrl"),
                            "crc": item.get("crc"),
                            "fileLength": item.get("fileLenght")
                        })
    except Exception:
        pass

out_path = r"D:\veepoo\w11_htm_app\veepoo_dials_catalog.json"
with open(out_path, "w", encoding="utf-8") as f:
    json.dump(catalog, f, indent=2)

print(f"SAVED {len(catalog)} OFFICIAL VEEPOO WATCH FACES TO {out_path}")
