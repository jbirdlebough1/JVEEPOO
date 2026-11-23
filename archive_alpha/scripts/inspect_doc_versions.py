import re

for doc in [r"D:\veepoo\docs\bfm_diabetes_beta_requirements_specification.md", r"D:\veepoo\docs\bfm_diabetes_beta1_functional_specification.md"]:
    lines = open(doc, encoding="utf-8").readlines()
    print(f"\n--- {doc} ---")
    for i, l in enumerate(lines[:50]):
        if any(w in l.lower() for w in ['version', 'build', 'beta', 'alpha', 'naming']):
            print(f"L{i+1}: {l.strip()}")
