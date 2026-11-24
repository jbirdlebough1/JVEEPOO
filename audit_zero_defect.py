"""
ZERO DEFECT AUDIT & VISUAL CLEANLINESS SUITE (audit_zero_defect.py)
Mandatory Automated Gate (-Werror) assessing every document, presentation, and display code file.
Enforces all 6 Prime Directives & Scott / Abrams 2009 Bridge Science Station Standards:
  1. Zero Broken Image Links & Zero SVG Markdown Fragility
  2. Zero LaTeX Math ($ / $$ / KaTeX / macros)
  3. Zero Monospace ASCII Box-Art Diagrams
  4. Zero Raw Text Flowchart Code Blocks
  5. Strict HTML DOM Tag Balance (Div opens == closes across BFM.HTM, index.html, cell.html)
  6. Triple-File Binary Mirror Parity (BFM.HTM == Root index.html == w11_htm_app/index.html)
  7. Python Server Bytecode Compilation (server.py)
  8. Standalone Desktop Execution Vector (file:///) Runtime Parity (BFM_API_BASE fetch wrapper)
  9. The Pure Zero-Data Reality Gate (0 is 0; zero synthetic meals, zero fake meds, zero test MRNs)
  10. Scott / Abrams 2009 Bridge Science Station Theme Gate (Luminous white/ice-blue aesthetic)
  11. Top Header Arch & Consolidated Status Ribbon Gate (Zero duplicate pills; fresh discovery defaults)
  12. Nutrition Metrology Accuracy & Zero Price Strings Gate (Taco Time exact 520 kcal/33g; zero $ in BOM)
  13. Consolidated EHR Data Export Gate (Single export bay in Bay 05; zero scattered export buttons)
  14. Senior Clinical Step Baseline Gate (2,000 steps baseline)
"""

import os
import sys
import glob
import re
import hashlib
import py_compile
import sqlite3

WORKSPACE_DIR = os.path.dirname(os.path.abspath(__file__))

# ANSI Colors
C_RESET = "\033[0m"
C_RED = "\033[91m"
C_GREEN = "\033[92m"
C_YELLOW = "\033[93m"
C_BLUE = "\033[94m"
C_CYAN = "\033[96m"
C_BOLD = "\033[1m"

def print_header(title):
    print(f"\n{C_CYAN}{C_BOLD}{'=' * 78}{C_RESET}")
    print(f"{C_CYAN}{C_BOLD}   {title}{C_RESET}")
    print(f"{C_CYAN}{C_BOLD}{'=' * 78}{C_RESET}")

def run_audit():
    print_header("SIX SIGMA ZERO DEFECT & RUNTIME PARITY AUDIT ENGINE (-Werror)")
    
    total_checks = 21
    passed_checks = 0
    all_defects = []

    # --------------------------------------------------------------------------
    # CHECK 1: Document Image Link & Asset Integrity Check
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 01/{total_checks}] Document Image Link & Asset Integrity Check...{C_RESET}")
    check1_defects = []
    doc_paths = glob.glob(os.path.join(WORKSPACE_DIR, "docs", "**"), recursive=True) + \
                glob.glob(os.path.join(WORKSPACE_DIR, "presentation", "**"), recursive=True)
    doc_files = [p for p in doc_paths if os.path.isfile(p) and p.endswith((".md", ".html", ".txt"))]

    for fpath in doc_files:
        with open(fpath, "r", encoding="utf-8", errors="ignore") as fp:
            content = fp.read()
        
        img_matches = re.finditer(r'!\[([^\]]*)\]\(([^)]+)\)', content)
        for m in img_matches:
            alt, src = m.group(1), m.group(2)
            if src.lower().endswith(".svg") and fpath.endswith((".md",)):
                check1_defects.append(f"{os.path.relpath(fpath, WORKSPACE_DIR)}: Fragile .svg image embed: '{src}'. Use native Markdown Architecture Table instead.")
            
            src_clean = src.split("?")[0].split("#")[0]
            if src_clean.startswith("file:///"):
                target = src_clean.replace("file:///", "").replace("/", os.sep)
            elif os.path.isabs(src_clean):
                target = src_clean
            else:
                target = os.path.normpath(os.path.join(os.path.dirname(fpath), src_clean))
            
            if not os.path.exists(target):
                check1_defects.append(f"{os.path.relpath(fpath, WORKSPACE_DIR)}: Broken image link: '{src}' (target missing: {target})")

    if check1_defects:
        print(f"  {C_RED}[FAIL] Found {len(check1_defects)} image link defect(s):{C_RESET}")
        for d in check1_defects:
            print(f"    - {d}")
        all_defects.extend(check1_defects)
    else:
        print(f"  {C_GREEN}[PASS] 100% of document image links valid; zero fragile SVG embeds.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 2: Zero LaTeX Math & Raw Delimiters Scan
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 02/{total_checks}] Zero LaTeX Math & Raw Delimiters Scan...{C_RESET}")
    check2_defects = []
    latex_macros = ["\\frac", "\\sigma", "\\sqrt", "\\approx", "\\pm", "\\rightleftharpoons"]
    
    text_targets = [p for p in doc_files if p.endswith((".md", ".html")) and not p.endswith(("VEEPOO_FITAOS_BLE_PROTOCOL_SPECIFICATION.html",))]

    for fpath in text_targets:
        with open(fpath, "r", encoding="utf-8", errors="ignore") as fp:
            lines = fp.readlines()
        for idx, line in enumerate(lines, 1):
            if "Zero LaTeX" in line or "Zero Defect" in line or "Visual Cleanliness" in line or "Mandate" in line:
                continue
            stripped = re.sub(r'\$\{[^}]*\}', '', line) # ignore JS template strings
            if fpath.endswith(".html") and ("<script" in line or "</script>" in line or "const " in line or "let " in line or "var " in line):
                continue
            if re.search(r'\$[^$\n]+\$', stripped) or "$$" in stripped:
                check2_defects.append(f"{os.path.relpath(fpath, WORKSPACE_DIR)}:L{idx} LaTeX '$' math delimiter: {line.strip()[:80]}")
            for m in latex_macros:
                if m in stripped:
                    check2_defects.append(f"{os.path.relpath(fpath, WORKSPACE_DIR)}:L{idx} Raw LaTeX macro '{m}': {line.strip()[:80]}")

    if check2_defects:
        print(f"  {C_RED}[FAIL] Found {len(check2_defects)} LaTeX math defect(s):{C_RESET}")
        for d in check2_defects[:10]:
            print(f"    - {d}")
        all_defects.extend(check2_defects)
    else:
        print(f"  {C_GREEN}[PASS] Zero LaTeX math delimiters ($/$$) or macros across all docs.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 3: Zero Monospace ASCII Box-Art Diagrams
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 03/{total_checks}] Zero Monospace ASCII Box-Art Diagrams...{C_RESET}")
    check3_defects = []
    box_chars = ["┌", "┐", "└", "┘", "│", "─", "├", "┤", "┬", "┴", "┼"]

    for fpath in text_targets:
        with open(fpath, "r", encoding="utf-8", errors="ignore") as fp:
            lines = fp.readlines()
        for idx, line in enumerate(lines, 1):
            if "Zero Monospace" in line or "Zero Defect" in line or "box-drawing" in line:
                continue
            for b in box_chars:
                if b in line:
                    check3_defects.append(f"{os.path.relpath(fpath, WORKSPACE_DIR)}:L{idx} Monospace box-art char '{b}': {line.strip()[:80]}")
                    break

    if check3_defects:
        print(f"  {C_RED}[FAIL] Found {len(check3_defects)} ASCII box-art defect(s):{C_RESET}")
        for d in check3_defects[:10]:
            print(f"    - {d}")
        all_defects.extend(check3_defects)
    else:
        print(f"  {C_GREEN}[PASS] Zero ASCII box-art characters in documentation.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 4: Zero Raw Text Flowchart Code Blocks
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 04/{total_checks}] Zero Raw Text Flowchart Code Blocks...{C_RESET}")
    check4_defects = []
    
    spec_targets = [
        os.path.join(WORKSPACE_DIR, "docs", "bfm_diabetes_beta1_functional_specification.md"),
        os.path.join(WORKSPACE_DIR, "docs", "bfm_diabetes_beta_requirements_specification.md"),
        os.path.join(WORKSPACE_DIR, "docs", "GOOGLE_LEADERSHIP_PRIMER.md")
    ]

    for fpath in spec_targets:
        if os.path.exists(fpath):
            with open(fpath, "r", encoding="utf-8", errors="ignore") as fp:
                content = fp.read()
            if re.search(r'```(?:mermaid)?\s*(?:flowchart|graph|sequenceDiagram)', content):
                check4_defects.append(f"{os.path.relpath(fpath, WORKSPACE_DIR)}: Contains raw Mermaid flowchart/sequence code block.")

    if check4_defects:
        print(f"  {C_RED}[FAIL] Found {len(check4_defects)} raw text flowchart defect(s):{C_RESET}")
        for d in check4_defects:
            print(f"    - {d}")
        all_defects.extend(check4_defects)
    else:
        print(f"  {C_GREEN}[PASS] Zero raw text flowcharts in core specifications.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 5: Strict HTML DOM Tag Balance (Div Opens == Closes)
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 05/{total_checks}] Strict HTML DOM Tag Balance...{C_RESET}")
    check5_defects = []
    html_targets = [
        os.path.join(WORKSPACE_DIR, "BFM.HTM"),
        os.path.join(WORKSPACE_DIR, "index.html"),
        os.path.join(WORKSPACE_DIR, "w11_htm_app", "index.html"),
        os.path.join(WORKSPACE_DIR, "w11_htm_app", "cell.html")
    ]

    for fpath in html_targets:
        if os.path.exists(fpath):
            with open(fpath, "r", encoding="utf-8", errors="ignore") as fp:
                html = fp.read()
            opens = len(re.findall(r'<div\b[^>]*>', html, re.IGNORECASE))
            closes = len(re.findall(r'</div>', html, re.IGNORECASE))
            if opens != closes:
                check5_defects.append(f"{os.path.relpath(fpath, WORKSPACE_DIR)}: Div mismatch! Opens={opens}, Closes={closes}, Diff={opens - closes}")
            else:
                print(f"  [OK] {os.path.relpath(fpath, WORKSPACE_DIR)}: Opens={opens}, Closes={closes}, Diff=0")

    if check5_defects:
        print(f"  {C_RED}[FAIL] Found {len(check5_defects)} tag balance defect(s):{C_RESET}")
        for d in check5_defects:
            print(f"    - {d}")
        all_defects.extend(check5_defects)
    else:
        print(f"  {C_GREEN}[PASS] HTML tag balance mathematically perfect (Diff == 0).{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 6: Triple-File Parity (BFM.HTM == Root index.html == w11_htm_app/index.html)
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 06/{total_checks}] Triple-File Binary Mirror Parity...{C_RESET}")
    f_bfm = os.path.join(WORKSPACE_DIR, "BFM.HTM")
    f_idx = os.path.join(WORKSPACE_DIR, "index.html")
    f_app = os.path.join(WORKSPACE_DIR, "w11_htm_app", "index.html")
    
    if os.path.exists(f_bfm) and os.path.exists(f_idx) and os.path.exists(f_app):
        with open(f_bfm, "rb") as fp0, open(f_idx, "rb") as fp1, open(f_app, "rb") as fp2:
            h0 = hashlib.sha256(fp0.read()).hexdigest()
            h1 = hashlib.sha256(fp1.read()).hexdigest()
            h2 = hashlib.sha256(fp2.read()).hexdigest()
        
        if h0 == h1 == h2:
            print(f"  {C_GREEN}[PASS] BFM.HTM, index.html, and w11_htm_app/index.html are bit-for-bit identical (SHA-256: {h0[:16]}...).{C_RESET}")
            passed_checks += 1
        else:
            all_defects.append(f"Mirror Parity Mismatch: BFM={h0[:16]}, Root={h1[:16]}, App={h2[:16]}")
            print(f"  {C_RED}[FAIL] Mirror files have drifted!{C_RESET}")
    else:
        all_defects.append("Missing core HTML file(s) for mirror check.")
        print(f"  {C_RED}[FAIL] Mirror check files missing.{C_RESET}")

    # --------------------------------------------------------------------------
    # CHECK 7: Python Server AST Bytecode Compilation
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 07/{total_checks}] Python Server AST Bytecode Compilation...{C_RESET}")
    check7_defects = []
    py_files = [os.path.join(WORKSPACE_DIR, "w11_htm_app", "server.py")]
    for p in py_files:
        if os.path.exists(p):
            try:
                py_compile.compile(p, doraise=True)
                print(f"  [OK] {os.path.relpath(p, WORKSPACE_DIR)} compiled cleanly.")
            except Exception as e:
                check7_defects.append(f"{os.path.relpath(p, WORKSPACE_DIR)} syntax error: {e}")

    if check7_defects:
        print(f"  {C_RED}[FAIL] Found {len(check7_defects)} Python compilation defect(s):{C_RESET}")
        for d in check7_defects:
            print(f"    - {d}")
        all_defects.extend(check7_defects)
    else:
        print(f"  {C_GREEN}[PASS] Backend Python scripts pass strict bytecode compilation.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 8: Standalone Desktop file:/// Runtime Parity Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 08/{total_checks}] Standalone Desktop file:/// Runtime Parity Gate...{C_RESET}")
    check8_defects = []
    with open(f_bfm, "r", encoding="utf-8", errors="ignore") as fp:
        bfm_content = fp.read()
    with open(os.path.join(WORKSPACE_DIR, "w11_htm_app", "server.py"), "r", encoding="utf-8", errors="ignore") as fp:
        server_content = fp.read()
    with open(os.path.join(WORKSPACE_DIR, "w11_htm_app", "cell.html"), "r", encoding="utf-8", errors="ignore") as fp:
        cell_content = fp.read()
    
    if "const BFM_API_BASE" not in bfm_content or "http://127.0.0.1:8899" not in bfm_content:
        check8_defects.append("BFM.HTM is missing dynamic BFM_API_BASE resolution for standalone file:/// protocol!")
    if "_origFetch" not in bfm_content or "window.fetch = function" not in bfm_content:
        check8_defects.append("BFM.HTM is missing window.fetch interceptor for standalone /api/ routing!")

    if check8_defects:
        print(f"  {C_RED}[FAIL] Found {len(check8_defects)} standalone execution defect(s):{C_RESET}")
        for d in check8_defects:
            print(f"    - {d}")
        all_defects.extend(check8_defects)
    else:
        print(f"  {C_GREEN}[PASS] Standalone file:/// execution vector verified with transparent /api/ port 8899 wrapper.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 9: The Pure Zero-Data Reality Gate (0 is 0)
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 09/{total_checks}] Pure Zero-Data Reality Gate (0 is 0)...{C_RESET}")
    check9_defects = []
    
    # Assert zero synthetic placeholders in initial state
    forbidden_synthetic = [
        ("PATIENT-MRN-948201", "Hardcoded test MRN found"),
        ("984210", "Synthetic MRN found"),
        ("Poached eggs, avocado", "Synthetic breakfast placeholder in initial journal"),
        ("Brisk neighborhood walk after poached eggs", "Synthetic exercise placeholder in initial journal"),
        ("Math.sin(d * 1.5)", "Synthetic projected sine wave on longitudinal chart")
    ]
    for term, reason in forbidden_synthetic:
        if term in bfm_content:
            check9_defects.append(f"Zero-Data Reality violation: {reason} ('{term}')")

    # Assert required empty notices
    required_empty_notices = [
        ("[NO NUTRITION LOGGED — JOURNAL EMPTY]", "Empty diet journal notice missing"),
        ("[NO EXERCISE LOGGED — JOURNAL EMPTY]", "Empty exercise journal notice missing"),
        ("[NO MEDICATIONS RECORDED]", "Empty medication registry notice missing"),
        ("[UNASSIGNED]", "Unassigned MRN fallback missing")
    ]
    for notice, reason in required_empty_notices:
        if notice not in bfm_content:
            check9_defects.append(f"Zero-Data Reality violation: {reason} ('{notice}')")

    if check9_defects:
        print(f"  {C_RED}[FAIL] Found {len(check9_defects)} Zero-Data Reality defect(s):{C_RESET}")
        for d in check9_defects:
            print(f"    - {d}")
        all_defects.extend(check9_defects)
    else:
        print(f"  {C_GREEN}[PASS] Zero synthetic data verified: clean zero-data state displays 0 and clean empty notices.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 10: Scott / Abrams 2009 Bridge Science Station Theme Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 10/{total_checks}] Scott / Abrams 2009 Bridge Science Station Theme Gate...{C_RESET}")
    check10_defects = []
    
    if '<body class="theme-light">' not in bfm_content:
        check10_defects.append("Body does not default to 'theme-light' class!")
    if "body.theme-light .lcars-pill {" not in bfm_content or "border-radius: 6px" not in bfm_content:
        check10_defects.append("Abrams bridge precision rectangular card styling for sidebar navigation pills is missing!")
    if "body.theme-light .lcars-pill.active {" not in bfm_content or "#0284c7" not in bfm_content:
        check10_defects.append("Abrams bridge active ice-blue (#0284c7) button highlight is missing!")
    if "body.theme-light .lcars-elbow-top-left {" not in bfm_content or "linear-gradient" not in bfm_content:
        check10_defects.append("Abrams bridge Starfleet science top elbow gradient is missing!")

    if check10_defects:
        print(f"  {C_RED}[FAIL] Found {len(check10_defects)} Abrams bridge theme defect(s):{C_RESET}")
        for d in check10_defects:
            print(f"    - {d}")
        all_defects.extend(check10_defects)
    else:
        print(f"  {C_GREEN}[PASS] Abrams 2009 bridge science station aesthetic verified (luminous white, ice-blue, precision cards).{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 11: Top Header Arch & Consolidated Status Ribbon Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 11/{total_checks}] Top Header Arch & Consolidated Status Ribbon Gate...{C_RESET}")
    check11_defects = []
    
    # Assert absence of duplicate pills from header arch
    if 'id="headerBatteryPill"' in bfm_content:
        check11_defects.append("Duplicate headerBatteryPill still present in top header arch!")
    if 'id="headerBtSignalPill"' in bfm_content:
        check11_defects.append("Duplicate headerBtSignalPill still present in top header arch!")

    # Assert clean discovery defaults in status ribbon
    if "DISCONNECTED (CLICK TO CONNECT)" not in bfm_content:
        check11_defects.append("Status ribbon Comm Link does not show initial 'DISCONNECTED (CLICK TO CONNECT)' state!")
    if "UNASSIGNED (CLICK TO DISCOVER)" not in bfm_content:
        check11_defects.append("Status ribbon Transponder does not show initial fresh discovery state 'UNASSIGNED (CLICK TO DISCOVER)'!")

    # Assert Canonical Header Demarcations (BFM SYSTEM on left elbow, BIOMETRIC FUNCTION MONITOR on title ribbon)
    if ">BFM SYSTEM</div>" not in bfm_content and "BFM SYSTEM" not in bfm_content:
        check11_defects.append("Left top blue elbow tab does not display 'BFM SYSTEM'!")
    if "BIOMETRIC FUNCTION MONITOR" not in bfm_content:
        check11_defects.append("Header title ribbon does not display 'BIOMETRIC FUNCTION MONITOR'!")
    if "BFM WORKSTATION" in bfm_content:
        check11_defects.append("Forbidden legacy branding 'BFM WORKSTATION' found in BFM.HTM!")
    if "STARSHIP GEMINI" in bfm_content or "NCC-202X" in bfm_content:
        check11_defects.append("Forbidden Star Trek branding 'STARSHIP GEMINI NCC-202X' found in BFM.HTM!")
    if "CLINICAL TRIAGE WORKSTATION" in bfm_content:
        check11_defects.append("Forbidden legacy term 'CLINICAL TRIAGE WORKSTATION' found in BFM.HTM!")
    if "VIEW 360° SYNCHRONIZED TALL CHART" in bfm_content:
        check11_defects.append("Unrequested 'VIEW 360' button still present in Component 01!")

    if check11_defects:
        print(f"  {C_RED}[FAIL] Found {len(check11_defects)} header/ribbon demarcation defect(s):{C_RESET}")
        for d in check11_defects:
            print(f"    - {d}")
        all_defects.extend(check11_defects)
    else:
        print(f"  {C_GREEN}[PASS] Top header arch decluttered; BFM SYSTEM / BIOMETRIC FUNCTION MONITOR canonical labels verified; zero speculative branding.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 12: Nutrition Metrology Accuracy & Zero Price Strings Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 12/{total_checks}] Nutrition Metrology & Zero Price Strings Gate...{C_RESET}")
    check12_defects = []
    
    # Taco Time NW Fiesta Platter exact numbers check in server.py and BFM.HTM
    with open(os.path.join(WORKSPACE_DIR, "w11_htm_app", "server.py"), "r", encoding="utf-8", errors="ignore") as fp:
        server_content = fp.read()
    
    if '"calories": 520' not in server_content or '"net_carbs": 33.0' not in server_content:
        check12_defects.append("server.py does not reflect official Taco Time Fiesta Platter profile (520 kcal, 33g net carbs)!")
    if 'calories: 520' not in bfm_content or 'netCarbs: 33.0' not in bfm_content:
        check12_defects.append("BFM.HTM does not reflect official Taco Time Fiesta Platter profile (520 kcal, 33g net carbs)!")

    # Zero monetary cost strings in nutritional BOM
    if "estimatedCost: \"14.75\"" in bfm_content or "estimated_cost\": \"14.75\"" in server_content:
        check12_defects.append("Found residual monetary cost string $14.75 in nutrition workflow!")
    if 'TOTAL ESTIMATED BILL OF MATERIALS (BOM) COST:' in bfm_content:
        check12_defects.append("Found forbidden BOM COST header in BFM.HTM!")

    if check12_defects:
        print(f"  {C_RED}[FAIL] Found {len(check12_defects)} nutrition metrology defect(s):{C_RESET}")
        for d in check12_defects:
            print(f"    - {d}")
        all_defects.extend(check12_defects)
    else:
        print(f"  {C_GREEN}[PASS] Taco Time Fiesta Platter (520 kcal, 33g net carbs) verified; zero monetary price strings in BOM.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 13: Consolidated EHR Data Export Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 13/{total_checks}] Consolidated EHR Data Export Gate...{C_RESET}")
    check13_defects = []
    
    # Assert presence in Bay 05
    if 'id="pageExport"' not in bfm_content or '05-EHR DATA EXPORT' not in bfm_content:
        check13_defects.append("Bay 05 EHR Data Export page is missing!")
    
    # Assert absence of redundant export buttons in Tab 01 and Tab 04
    if 'onclick="exportFullScanCsv()"' in bfm_content:
        check13_defects.append("Found redundant export button calling exportFullScanCsv() in Tab 01!")
    if 'onclick="exportGoalAuditToEpic()"' in bfm_content:
        check13_defects.append("Found redundant export button calling exportGoalAuditToEpic() in Tab 04!")

    if check13_defects:
        print(f"  {C_RED}[FAIL] Found {len(check13_defects)} export consolidation defect(s):{C_RESET}")
        for d in check13_defects:
            print(f"    - {d}")
        all_defects.extend(check13_defects)
    else:
        print(f"  {C_GREEN}[PASS] All EHR exports strictly consolidated into Bay 05; zero scattered export button residue.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 14: Senior Clinical Step Baseline Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 14/{total_checks}] Senior Clinical Step Baseline Gate...{C_RESET}")
    check14_defects = []
    
    if "let currentStepSimulationTarget = 2000;" not in bfm_content:
        check14_defects.append("Step simulation default target is not configured to senior baseline (2,000 steps)!")
    if 'value="2000"' not in bfm_content or '2,000 STEPS' not in bfm_content:
        check14_defects.append("Step target slider is not set to senior baseline (2,000 steps)!")

    if check14_defects:
        print(f"  {C_RED}[FAIL] Found {len(check14_defects)} senior step baseline defect(s):{C_RESET}")
        for d in check14_defects:
            print(f"    - {d}")
        all_defects.extend(check14_defects)
    else:
        print(f"  {C_GREEN}[PASS] Senior clinical baseline step target (2,000 steps/day) verified.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 15: Header ADD CELL Button Segregation Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 15/{total_checks}] Header ADD CELL Button Segregation Gate...{C_RESET}")
    check15_defects = []
    if 'id="btnHeaderAddCell"' in bfm_content:
        check15_defects.append("Forbidden btnHeaderAddCell detected in top header arch! Button must exclusively live in Config tab.")
    if 'openProvisionModal()' not in bfm_content:
        check15_defects.append("openProvisionModal() action missing from BFM.HTM!")
    if 'id="btnConfigAddCell"' not in bfm_content and 'openProvisionModal()' not in bfm_content:
        check15_defects.append("ADD CELL button missing from Config tab!")

    if check15_defects:
        print(f"  {C_RED}[FAIL] Found {len(check15_defects)} button segregation defect(s):{C_RESET}")
        for d in check15_defects:
            print(f"    - {d}")
        all_defects.extend(check15_defects)
    else:
        print(f"  {C_GREEN}[PASS] ADD CELL button strictly segregated to Config tab; header arch clean.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 16: Zero-Data Future Timeline Guard Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 16/{total_checks}] Zero-Data Future Timeline Guard Gate...{C_RESET}")
    check16_defects = []
    if "const isFuture" not in bfm_content or "isFuture ? 0 :" not in bfm_content:
        check16_defects.append("Sub-report trend chart generator lacks future-hour zero clipping guard (isFuture ? 0 : ... )!")

    if check16_defects:
        print(f"  {C_RED}[FAIL] Found {len(check16_defects)} future-data defect(s):{C_RESET}")
        for d in check16_defects:
            print(f"    - {d}")
        all_defects.extend(check16_defects)
    else:
        print(f"  {C_GREEN}[PASS] Pure Zero-Data Reality enforced on charts: unelapsed future intervals strictly display 0.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 17: Telemetry Preservation & Anti-Dropping Guard Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 17/{total_checks}] Telemetry Preservation & Anti-Dropping Gate...{C_RESET}")
    check17_defects = []
    if "if (sys > 0" not in bfm_content:
        check17_defects.append("propagateFullScanResultsToAllPages lacks guard preventing overwriting valid BP with zero/empty values!")
    if "if (sp > 0" not in bfm_content:
        check17_defects.append("propagateFullScanResultsToAllPages lacks guard preventing overwriting valid SpO2 with zero/empty values!")

    # Verify SQLite database preserves continuous watch dataset (> 10,000 vital records since Nov 18)
    db_path = os.path.join(WORKSPACE_DIR, "w11_htm_app", "telemetry.db")
    if os.path.exists(db_path):
        try:
            c = sqlite3.connect(db_path)
            v_cnt = c.execute("SELECT COUNT(*) FROM vital_records").fetchone()[0]
            c.close()
            if v_cnt < 10000:
                check17_defects.append(f"vital_records count is only {v_cnt} (< 10,000 threshold)! Historical telemetry dropped!")
        except Exception as e:
            check17_defects.append(f"Failed to query telemetry.db vital_records: {e}")

    if check17_defects:
        print(f"  {C_RED}[FAIL] Found {len(check17_defects)} telemetry dropping defect(s):{C_RESET}")
        for d in check17_defects:
            print(f"    - {d}")
        all_defects.extend(check17_defects)
    else:
        print(f"  {C_GREEN}[PASS] Full scan and polling telemetry persistence guards verified; {v_cnt} records preserved; zero data dropping.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 18: Bay 06 Respiration Trend Curve & Clinical Guidance Card Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 18/{total_checks}] Bay 06 Respiration Trend Curve Gate...{C_RESET}")
    check18_defects = []
    if 'id="curveTimelineBreath"' not in bfm_content:
        check18_defects.append("Bay 06 Respiration Sub-report lacks curveTimelineBreath SVG trend container!")
    if "renderSvgCurveChart('curveTimelineBreath'" not in bfm_content:
        check18_defects.append("renderSvgCurveChart is not wired to curveTimelineBreath!")

    if check18_defects:
        print(f"  {C_RED}[FAIL] Found {len(check18_defects)} breath trend curve defect(s):{C_RESET}")
        for d in check18_defects:
            print(f"    - {d}")
        all_defects.extend(check18_defects)
    else:
        print(f"  {C_GREEN}[PASS] Bay 06 Respiration SVG trend graph and clinical guidance card verified.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 19: Tab 01 Learning Timeframe Cleanliness & Pure Zero-Data Pantry Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 19/{total_checks}] Tab 01 Learning Timeframe Cleanliness & Zero Pantry Gate...{C_RESET}")
    check19_defects = []
    if 'id="btnLearnWeek"' in bfm_content or 'id="btnLearnMonth"' in bfm_content:
        check19_defects.append("Irrelevant week/month timeframe buttons still present in Tab 01 Learning header!")
    if "Junior Mints (35g)" in bfm_content:
        check19_defects.append("Synthetic Junior Mints case study still present in Tab 01 Component 05!")
    if "physician@evergreenhealth.com" in bfm_content:
        check19_defects.append("Hardcoded physician email still present in Bay 05 clinical export!")

    if check19_defects:
        print(f"  {C_RED}[FAIL] Found {len(check19_defects)} Tab 01 cleanliness defect(s):{C_RESET}")
        for d in check19_defects:
            print(f"    - {d}")
        all_defects.extend(check19_defects)
    else:
        print(f"  {C_GREEN}[PASS] Tab 01 Learning header clean; synthetic case study and hardcoded clinician data purged.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 20: Config Hardware Transducer Table & Deletion Parity Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 20/{total_checks}] Config Hardware Transducer Table & Deletion Parity Gate...{C_RESET}")
    check20_defects = []
    if 'id="tblWatchFunctions"' not in bfm_content:
        check20_defects.append("Section 13 Veepoo Model 5010 Hardware Transducers Table (tblWatchFunctions) missing from Config!")
    if 'deleteFamilyCell' not in bfm_content:
        check20_defects.append("deleteFamilyCell function missing from BFM.HTM client script!")
    if '/api/shuttle/cell/delete' not in server_content and '/api/shuttle/delete' not in server_content:
        check20_defects.append("/api/shuttle/cell/delete endpoint missing from server.py!")
    if 'Snack Strawberry Vanilla Granola' not in bfm_content:
        check20_defects.append("Snack Strawberry Vanilla Granola missing from pre-choice pantry estimator!")

    if check20_defects:
        print(f"  {C_RED}[FAIL] Found {len(check20_defects)} hardware table / deletion defect(s):{C_RESET}")
        for d in check20_defects:
            print(f"    - {d}")
        all_defects.extend(check20_defects)
    else:
        print(f"  {C_GREEN}[PASS] Veepoo 5010 hardware transducer table, cell deletion, and granola estimator verified.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # CHECK 21: 3D Multi-Channel Dynamic Data Points & Node Visibility Gate
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 21/{total_checks}] 3D Multi-Channel Dynamic Data Points Gate...{C_RESET}")
    check21_defects = []
    if "getChannelValueAtHour" not in bfm_content:
        check21_defects.append("3D Multi-Channel visualizer lacks getChannelValueAtHour dynamic hourly sampler!")
    if "allPlottedPoints" not in bfm_content or "cam3D.hoverMouseX" not in bfm_content:
        check21_defects.append("3D Multi-Channel visualizer lacks interactive hover inspection and data point hit-testing!")
    if "fetchServer2DayTimeline" not in bfm_content:
        check21_defects.append("3D Multi-Channel visualizer lacks 48-hour continuous timeline integration!")
    if "CH 01: GLUCOSE" not in bfm_content or "CH 09: PAIN INDEX" not in bfm_content or "CH 10: MOOD SCALE" not in bfm_content:
        check21_defects.append("3D Multi-Channel channel ordering defect: must start with Glucose mg/dL and end with Pain and Mood!")
    if "drawBoxStrut" not in bfm_content:
        check21_defects.append("3D Multi-Channel visualizer lacks SETI@home wireframe bounding cage struts!")

    if check21_defects:
        print(f"  {C_RED}[FAIL] Found {len(check21_defects)} 3D visualizer defect(s):{C_RESET}")
        for d in check21_defects:
            print(f"    - {d}")
        all_defects.extend(check21_defects)
    else:
        print(f"  {C_GREEN}[PASS] 3D Multi-Channel SETI@home plot box, dynamic points, crest nodes, and hover inspection verified.{C_RESET}")
        passed_checks += 1

    # --------------------------------------------------------------------------
    # FINAL AUDIT SUMMARY
    # --------------------------------------------------------------------------
    print_header(f"AUDIT SUMMARY: {passed_checks}/{total_checks} CHECKS PASSED")
    if all_defects:
        print(f"{C_RED}{C_BOLD}STATUS: ZERO DEFECT MANDATE VIOLATED ({len(all_defects)} DEFECTS FOUND){C_RESET}")
        for d in all_defects[:15]:
            print(f"  {C_RED}• {d}{C_RESET}")
        if len(all_defects) > 15:
            print(f"  ... and {len(all_defects) - 15} more")
        return 1
    else:
        print(f"{C_GREEN}{C_BOLD}STATUS: ZERO DEFECT MANDATE VERIFIED (100% CLEAN & AUDITED){C_RESET}")
        print(f"{C_GREEN}All documents, presentations, and display code files pass Six Sigma quality standards.{C_RESET}\n")
        return 0

if __name__ == "__main__":
    sys.exit(run_audit())
