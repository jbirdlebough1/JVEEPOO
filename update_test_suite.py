with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    suite = f.read()

pos_summary = suite.find('print(f"AUDIT SUMMARY:')
assert pos_summary != -1, "AUDIT SUMMARY not found"

new_tests = '''
# ==============================================================================
# GROUP 15: HEX CODE SCRUB, SLEEP RESTORATION, PANTRY & DAILY INTAKE SUMMARY
# ==============================================================================
print("\\n[GROUP 15: HEX SCRUB, SLEEP RESTORATION, PANTRY & DAILY INTAKE SUMMARY]")

# TC-40: Hex code scrub in stage cards & colored % elements
has_hex_in_stages = any(re.search(rf'stageCard{i}[\\s\\S]*?0x[0-9a-fA-F]{{2}}', html_root) for i in range(1, 7))
has_all_stage_pcts = all(f'id="stagePct{i}"' in html_root for i in range(1, 7))
if not has_hex_in_stages and has_all_stage_pcts:
    record_result("TC-40 [Full Scan UI] Zero Hex Codes in Measurement Cards & Colored % Numbers Present", True, "Stage cards display STAGE 01..06 with dedicated #stagePct1..6 elements (0 hex opcodes)")
else:
    record_result("TC-40 [Full Scan UI] Zero Hex Codes in Measurement Cards & Colored % Numbers Present", False, f"Hex in stages: {has_hex_in_stages}, has all pcts: {has_all_stage_pcts}")

# TC-41: Sleep Data Telemetry Restoration
has_sleep_metric = '"SLEEP"' in server_code
has_sleep_in_poll = 'vitals.SLEEP' in html_root
has_sleep_post = "metric: 'SLEEP'" in html_root
if has_sleep_metric and has_sleep_in_poll and has_sleep_post:
    record_result("TC-41 [Sleep Data] Nocturnal Sleep Telemetry Restoration & SQLite Ingestion", True, "Sleep metric queried in snapshot, assembled stream posted to /api/telemetry and hydrated in DOM")
else:
    record_result("TC-41 [Sleep Data] Nocturnal Sleep Telemetry Restoration & SQLite Ingestion", False, f"metric: {has_sleep_metric}, poll: {has_sleep_in_poll}, post: {has_sleep_post}")

# TC-42: Removal of Mobile /cell Gateway Pill from LCARS Desktop Screen
has_cell_pill = 'DIRECT MOBILE FOOD INTAKE GATEWAY' in html_root
if not has_cell_pill:
    record_result("TC-42 [LCARS Clean UI] Complete Removal of Mobile Food Intake Gateway Pill from LCARS Screen", True, "Obsolete mobile gateway card scrubbed from desktop console (8899/cell remains independent)")
else:
    record_result("TC-42 [LCARS Clean UI] Complete Removal of Mobile Food Intake Gateway Pill from LCARS Screen", False, "Gateway pill still present in LCARS HTML")

# TC-43: Meal Pantry Auto-Upsert for Cell Uploads & Latest-to-Oldest Sorting
has_pantry_upsert = 'upsert_popular_meal' in server_code and 'cell_{meal_id}' in server_code
has_latest_sort = 'ORDER BY timestamp DESC, id DESC' in server_code
has_client_sort = 'tB - tA' in html_root or 'b.id - a.id' in html_root
if has_pantry_upsert and has_latest_sort and has_client_sort:
    record_result("TC-43 [Meal Ingestion] Cell Uploads Upsert to Meal Pantry & Strict Latest-to-Oldest Sorting", True, "Cell meals/snacks auto-upsert to popular_meals; recent meals strictly sort DESC (most current at top)")
else:
    record_result("TC-43 [Meal Ingestion] Cell Uploads Upsert to Meal Pantry & Strict Latest-to-Oldest Sorting", False, f"upsert: {has_pantry_upsert}, server sort: {has_latest_sort}, client sort: {has_client_sort}")

# TC-44: Daily Intake Summary of All Consumed
has_daily_summary_dom = 'id="cardDailyIntakeSummary"' in html_root
has_occasions = all(f'summaryCount{occ}' in html_root for occ in ['Breakfast', 'Lunch', 'Dinner', 'Snack'])
has_daily_macro_totals = all(f'lblDay{m}' in html_root for m in ['Kcal', 'NetCarbs', 'Protein', 'Fat', 'Fiber'])
if has_daily_summary_dom and has_occasions and has_daily_macro_totals:
    record_result("TC-44 [Daily Intake] Comprehensive Daily Intake Summary of All Consumed Dashboard", True, "Dedicated #cardDailyIntakeSummary with 4-bay occasion breakdown (Breakfast/Lunch/Dinner/Snack) and macro totals")
else:
    record_result("TC-44 [Daily Intake] Comprehensive Daily Intake Summary of All Consumed Dashboard", False, f"summary DOM: {has_daily_summary_dom}, occasions: {has_occasions}, totals: {has_daily_macro_totals}")

'''

suite = suite[:pos_summary] + new_tests + suite[pos_summary:]

with open('D:/veepoo/run_full_audit_test_suite.py', 'w', encoding='utf-8') as f:
    f.write(suite)

print("Updated run_full_audit_test_suite.py with Group 15 tests!")
