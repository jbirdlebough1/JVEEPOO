import re, hashlib

def check_parity_and_tags():
    with open("index.html", "r", encoding="utf-8") as f:
        c1 = f.read()
    with open("w11_htm_app/index.html", "r", encoding="utf-8") as f:
        c2 = f.read()

    h1 = hashlib.sha256(c1.encode("utf-8")).hexdigest()
    h2 = hashlib.sha256(c2.encode("utf-8")).hexdigest()
    parity = (h1 == h2)

    open_tags = len(re.findall(r'<div\b', c1))
    close_tags = len(re.findall(r'</div>', c1))
    diff = open_tags - close_tags

    print(f"SHA256 Parity: {parity} (Hash: {h1[:16]}...)")
    print(f"Div Tags: open={open_tags}, close={close_tags}, diff={diff}")
    assert parity, "Parity check failed!"
    assert diff == 0, f"Div tag imbalance: {diff}"
    print("[PASS] Parity and Tag Balance 100% verified!")

check_parity_and_tags()
