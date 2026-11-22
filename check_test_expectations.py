for fname in ['D:/veepoo/run_full_audit_test_suite.py', 'D:/veepoo/test_accumulated_qa.py']:
    with open(fname, 'r', encoding='utf-8') as f:
        c = f.read()
    print(f"--- {fname} ---")
    print("Has btnSkipFullScan?", "btnSkipFullScan" in c)
    print("Has RUN ANOTHER?", "RUN ANOTHER" in c)
    print("Has skipCurrentFullScanStep?", "skipCurrentFullScanStep" in c)
    print("Has pageVitals?", "pageVitals" in c)
    print("Has navTabVitals?", "navTabVitals" in c)
