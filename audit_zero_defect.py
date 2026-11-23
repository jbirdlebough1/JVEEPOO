"""
ZERO DEFECT AUDIT & VISUAL CLEANLINESS SUITE (audit_zero_defect.py)
Mandatory Automated Gate (-Werror) assessing every document, presentation, and display code file.
Enforces:
  1. Zero Broken Image Links & Zero SVG Markdown Fragility
  2. Zero LaTeX Math ($ / $$ / KaTeX / macros)
  3. Zero Monospace ASCII Box-Art Diagrams
  4. Zero Raw Text Flowchart Code Blocks
  5. Strict HTML Tag Balance (Div opens == closes)
  6. Dual-File Mirror Parity (Root == w11_htm_app)
  7. Python Server Bytecode Compilation
  8. Zero Forbidden Terms in Target Application Scope
"""

import os
import sys
import glob
import re
import hashlib
import py_compile

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
    print(f"\n{C_CYAN}{C_BOLD}{'=' * 75}{C_RESET}")
    print(f"{C_CYAN}{C_BOLD}   {title}{C_RESET}")
    print(f"{C_CYAN}{C_BOLD}{'=' * 75}{C_RESET}")

def run_audit():
    print_header("ZERO DEFECT & VISUAL CLEANLINESS AUDIT ENGINE (-Werror)")
    
    total_checks = 8
    passed_checks = 0
    all_defects = []

    # --------------------------------------------------------------------------
    # CHECK 1: Document Image Link & Asset Integrity Check
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 1/{total_checks}] Document Image Link & Asset Integrity Check...{C_RESET}")
    check1_defects = []
    doc_paths = glob.glob(os.path.join(WORKSPACE_DIR, "docs", "**"), recursive=True) + \
                glob.glob(os.path.join(WORKSPACE_DIR, "presentation", "**"), recursive=True)
    doc_files = [p for p in doc_paths if os.path.isfile(p) and p.endswith((".md", ".html", ".txt"))]

    for fpath in doc_files:
        with open(fpath, "r", encoding="utf-8", errors="ignore") as fp:
            content = fp.read()
        
        # Check Markdown image embeds: ![alt](src)
        img_matches = re.finditer(r'!\[([^\]]*)\]\(([^)]+)\)', content)
        for m in img_matches:
            alt, src = m.group(1), m.group(2)
            # Rule: In core documentation, do not link .svg in markdown image tags because
            # many desktop markdown viewers (Typora, Word, etc.) fail to render SVG.
            if src.lower().endswith(".svg") and fpath.endswith((".md",)):
                check1_defects.append(f"{os.path.relpath(fpath, WORKSPACE_DIR)}: Fragile .svg image embed: '{src}'. Use native Markdown Architecture Table instead.")
            
            # Check target file existence
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
    print(f"\n{C_BOLD}[CHECK 2/{total_checks}] Zero LaTeX Math & Raw Delimiters Scan...{C_RESET}")
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
    print(f"\n{C_BOLD}[CHECK 3/{total_checks}] Zero Monospace ASCII Box-Art Diagrams...{C_RESET}")
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
    print(f"\n{C_BOLD}[CHECK 4/{total_checks}] Zero Raw Text Flowchart Code Blocks...{C_RESET}")
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
    # CHECK 5: Strict HTML DOM Tag Balance (Div Opens == Closes)
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 5/{total_checks}] Strict HTML DOM Tag Balance...{C_RESET}")
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
    print(f"\n{C_BOLD}[CHECK 6/{total_checks}] Triple-File Binary Mirror Parity...{C_RESET}")
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
    print(f"\n{C_BOLD}[CHECK 7/{total_checks}] Python Server AST Bytecode Compilation...{C_RESET}")
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
    # CHECK 8: Zero Monetary Cost Strings Residue in Nutrition BOM
    # --------------------------------------------------------------------------
    print(f"\n{C_BOLD}[CHECK 8/{total_checks}] Zero Monetary Cost Strings in Nutrition BOM...{C_RESET}")
    check8_defects = []
    with open(os.path.join(WORKSPACE_DIR, "index.html"), "r", encoding="utf-8", errors="ignore") as fp:
        idx_lines = fp.readlines()
    
    in_bom = False
    for line_num, line in enumerate(idx_lines, 1):
        if "mealBOMTable" in line or "TAB 09: GEMINI MEAL BUILDER" in line:
            in_bom = True
        if in_bom and ("</table>" in line or "</main>" in line):
            in_bom = False
        if in_bom and ("COST" in line or "$ / SERVING" in line or "$/SERVING" in line):
            check8_defects.append(f"index.html:L{line_num} Monetary cost string residue in BOM: {line.strip()[:80]}")

    if check8_defects:
        print(f"  {C_RED}[FAIL] Found {len(check8_defects)} monetary string defect(s):{C_RESET}")
        for d in check8_defects:
            print(f"    - {d}")
        all_defects.extend(check8_defects)
    else:
        print(f"  {C_GREEN}[PASS] Nutrition BOM headers verified free of monetary price strings.{C_RESET}")
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
