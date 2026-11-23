def fix_newlines():
    path = r'D:\veepoo\w11_htm_app\index.html'
    with open(path, 'r', encoding='utf-8') as f:
        html = f.read()

    bad1 = '''      navigator.clipboard.writeText(listText).then(() => {
        alert("📋 GROCERY SHOPPING LIST COPIED TO CLIPBOARD!

Paste into your Notes, Reminders, or Grocery app.");
      }).catch(() => {
        alert("BOM Shopping List:

" + listText);
      });'''

    good1 = '''      navigator.clipboard.writeText(listText).then(() => {
        alert("📋 GROCERY SHOPPING LIST COPIED TO CLIPBOARD!\\n\\nPaste into your Notes, Reminders, or Grocery app.");
      }).catch(() => {
        alert("BOM Shopping List:\\n\\n" + listText);
      });'''

    if bad1 in html:
        html = html.replace(bad1, good1)
        print("[✔] Fixed multiline alert string")
    else:
        # Replace alert line by line
        html = html.replace('alert("📋 GROCERY SHOPPING LIST COPIED TO CLIPBOARD!\n\nPaste into your Notes, Reminders, or Grocery app.");',
                            'alert("📋 GROCERY SHOPPING LIST COPIED TO CLIPBOARD!\\n\\nPaste into your Notes, Reminders, or Grocery app.");')
        html = html.replace('alert("BOM Shopping List:\n\n" + listText);',
                            'alert("BOM Shopping List:\\n\\n" + listText);')
        print("[*] Replaced via fallback")

    with open(path, 'w', encoding='utf-8') as f:
        f.write(html)

    import shutil
    shutil.copyfile(path, 'D:/veepoo/index.html')
    print("[✔] Synchronized to D:/veepoo/index.html")

if __name__ == '__main__':
    fix_newlines()
