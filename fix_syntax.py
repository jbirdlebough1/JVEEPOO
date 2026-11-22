import re

def fix():
    path = r'D:\veepoo\w11_htm_app\index.html'
    with open(path, 'r', encoding='utf-8') as f:
        html = f.read()

    bad_snippet = '''      }       } else if (tabId === 'goals') {
        document.getElementById('navTabGoals').classList.add('active');
        document.getElementById('pageGoals').classList.add('active-page');
        if (typeof loadClinicalGoalsUI === 'function') loadClinicalGoalsUI();
else if (tabId === 'fullscan') {'''

    clean_snippet = '''      } else if (tabId === 'goals') {
        document.getElementById('navTabGoals').classList.add('active');
        document.getElementById('pageGoals').classList.add('active-page');
        if (typeof loadClinicalGoalsUI === 'function') loadClinicalGoalsUI();
      } else if (tabId === 'fullscan') {'''

    if bad_snippet in html:
        html = html.replace(bad_snippet, clean_snippet)
        print("[✔] Successfully fixed switchTab('goals') braces syntax")
    else:
        # Regex or flexible replace
        html = re.sub(r'\}\s*\}\s*else if \(tabId === \'goals\'\) \{[^}]+if \(typeof loadClinicalGoalsUI === \'function\'\) loadClinicalGoalsUI\(\);\s*else if \(tabId === \'fullscan\'\) \{',
                      clean_snippet, html)
        print("[*] Replaced via regex")

    with open(path, 'w', encoding='utf-8') as f:
        f.write(html)

    # Sync to root index.html
    import shutil
    shutil.copyfile(path, 'D:/veepoo/index.html')
    print("[✔] Synchronized to D:/veepoo/index.html")

if __name__ == '__main__':
    fix()
