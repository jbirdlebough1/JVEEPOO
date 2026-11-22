with open("index.html", "r", encoding="utf-8") as f:
    html = f.read()

main_start = html.find("lcars-workspace")
main_end = html.find("</main>")
pfs_pos = html.find('id="pageFullScan"')

print("main_start:", main_start, "main_end:", main_end, "pfs_pos:", pfs_pos)
print("Is inside main:", main_start < pfs_pos < main_end)
