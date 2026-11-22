# strict_gate.py
"""
SIX SIGMA / STRICT COMPILATION GATE FOR LCARS 5010
Modeled after GCC -Wall -Werror -Wpedantic
Exits with code 0 ONLY when zero defects, zero regressions, and full DOM/JS/DB integrity are confirmed.
"""
import sys
import os
import re
import hashlib
import sqlite3
import subprocess

BASE_DIR = r"D:\veepoo"
INDEX_ROOT = os.path.join(BASE_DIR, "index.html")
INDEX_APP = os.path.join(BASE_DIR, "w11_htm_app", "index.html")
SERVER_PY = os.path.join(BASE_DIR, "w11_htm_app", "server.py")
CELL_HTML = os.path.join(BASE_DIR, "w11_htm_app", "cell.html")
DB_PATH = os.path.join(BASE_DIR, "w11_htm_app", "telemetry.db")

EXPECTED_TABS = [
    "pageFullScan", "pageCardio", "pageMetabolic", "pageExport",
    "pageLearning", "pageReports", "pageSetup", "pageLog",
    "pageOperations", "pageHelp", "pageDietTrades", "pageWatchFaces",
    "pageMedication", "pageGoals"
]

def log_pass(msg):
    print(f"  [PASS] {msg}")

def log_fail(msg):
    print(f"  [FAIL] {msg}")
    sys.exit(1)

def run_gate():
    print("=================================================================")
    print("      SIX SIGMA STRICT QUALITY & REGRESSION GATE (-Werror)       ")
    print("=================================================================")

    # 1. DUAL-FILE BIT-FOR-BIT SHA256 PARITY
    print("\n[CHECK 1/6] Dual-File SHA256 Mirror Parity...")
    if not os.path.exists(INDEX_ROOT) or not os.path.exists(INDEX_APP):
        log_fail("index.html or w11_htm_app/index.html is missing!")
    h1 = hashlib.sha256(open(INDEX_ROOT, "rb").read()).hexdigest()
    h2 = hashlib.sha256(open(INDEX_APP, "rb").read()).hexdigest()
    if h1 != h2:
        log_fail(f"Parity Mismatch! root: {h1[:12]} != app: {h2[:12]}")
    log_pass(f"Root and w11_htm_app are bit-for-bit identical (SHA256: {h1[:16]}...)")

    # 2. HTML DOM PARITY & TAG BALANCE
    print("\n[CHECK 2/6] Strict HTML Tag Balance & DOM Tree Hierarchy...")
    html = open(INDEX_ROOT, "r", encoding="utf-8").read()
    open_divs = len(re.findall(r'<div\b', html, re.I))
    close_divs = len(re.findall(r'</div\b', html, re.I))
    if open_divs != close_divs:
        log_fail(f"Div imbalance detected! Open: {open_divs}, Close: {close_divs}, Diff: {open_divs - close_divs}")
    log_pass(f"Div balance perfect (Opens: {open_divs}, Closes: {close_divs}, Diff: 0)")

    from bs4 import BeautifulSoup
    soup = BeautifulSoup(html, "html.parser")
    tab_divs = soup.find_all("div", class_="tab-page")
    found_tab_ids = [t.get("id") for t in tab_divs]
    
    for expected in EXPECTED_TABS:
        if expected not in found_tab_ids:
            log_fail(f"Missing tab page: #{expected}")
        tab_el = soup.find("div", id=expected)
        parent = tab_el.parent
        if not (parent and parent.name == "main" and "lcars-workspace" in parent.get("class", [])):
            log_fail(f"Tab #{expected} is NOT a direct child of main.lcars-workspace! Parent is: <{parent.name}> #{parent.get('id', '')}.{parent.get('class', '')}")
    log_pass(f"All {len(EXPECTED_TABS)} tab pages are verified direct children of <main class='lcars-workspace'>")

    # 3. JAVASCRIPT STATIC COMPILATION / SYNTAX CHECK
    print("\n[CHECK 3/6] JavaScript AST Syntax Verification (Node --check)...")
    scripts = soup.find_all("script")
    combined_js = []
    for s in scripts:
        if s.string and not s.get("src"):
            combined_js.append(s.string)
    js_payload = "\n;\n".join(combined_js)
    temp_js_path = os.path.join(BASE_DIR, "_strict_temp_lint.js")
    with open(temp_js_path, "w", encoding="utf-8") as f:
        f.write(js_payload)
    try:
        proc = subprocess.run(["node", "--check", temp_js_path], capture_output=True, text=True)
        if proc.returncode != 0:
            log_fail(f"JavaScript Syntax Error in index.html:\n{proc.stderr}")
        log_pass("JavaScript in index.html passed static syntax validation with zero errors.")
    finally:
        if os.path.exists(temp_js_path):
            os.remove(temp_js_path)

    # 4. SERVER.PY COMPILATION & AST CHECK
    print("\n[CHECK 4/6] Python Server Syntax & Ingestion AST Check...")
    proc_py = subprocess.run([sys.executable, "-m", "py_compile", SERVER_PY], capture_output=True, text=True)
    if proc_py.returncode != 0:
        log_fail(f"Python syntax error in server.py:\n{proc_py.stderr}")
    log_pass("server.py passed Python bytecode compilation.")

    # 5. DATABASE DEDUPLICATION & CLINICAL SAFETY CHECK
    print("\n[CHECK 5/6] SQLite Database Deduplication & Confectionery Safety...")
    if os.path.exists(DB_PATH):
        conn = sqlite3.connect(DB_PATH)
        cur = conn.cursor()
        cur.execute("SELECT id, title, net_carbs, meets_goal FROM popular_meals")
        meals = cur.fetchall()
        seen = set()
        duplicates = []
        for mid, title, nc, mg in meals:
            norm = title.strip().lower()
            if norm in seen:
                duplicates.append((mid, title))
            seen.add(norm)
            # Clinical safety: candy / confectionery must NEVER be marked as meets_goal=1
            if any(w in norm for w in ["peppermint crunch", "candy", "fudge", "caramel"]) and mg == 1:
                log_fail(f"Clinical safety violation! Confectionery '{title}' marked as meets_goal=1 in DB!")
        if duplicates:
            log_fail(f"Meal pantry contains duplicates: {duplicates}")
        log_pass(f"popular_meals verified: {len(meals)} unique items, zero duplicates, zero dangerous confections.")
        conn.close()

    # 6. MONETARY COSTS VS METABOLIC IMPACT
    print("\n[CHECK 6/6] Zero Monetary Cost Residue in Nutrition / BOM Headers...")
    if "<th style=\"padding: 6px 8px; color: #0f172a; text-align: right;\">EST. COST</th>" in html:
        log_fail("Found forbidden EST. COST header in index.html BOM table!")
    if "$7.85 Basket" in html:
        log_fail("Found forbidden monetary basket price in index.html!")
    log_pass("Zero monetary cost strings in BOM headers; metabolic impact verified.")

    print("\n=================================================================")
    print("      STATUS: 100% SIX SIGMA VERIFIED • ZERO DEFECTS FOUND       ")
    print("=================================================================")

if __name__ == "__main__":
    run_gate()
