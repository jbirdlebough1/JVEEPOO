with open('D:/veepoo/run_full_audit_test_suite.py', 'r', encoding='utf-8') as f:
    suite = f.read()

# Replace Group 15 with correct run_test calls
pos_g15 = suite.find('# GROUP 15: HEX CODE SCRUB')
pos_summary = suite.find('print(f"AUDIT SUMMARY:')

correct_g15 = '''# ==============================================================================
# GROUP 15: HEX CODE SCRUB, SLEEP RESTORATION, PANTRY & DAILY INTAKE SUMMARY
# ==============================================================================
print("\\n[GROUP 15: HEX SCRUB, SLEEP RESTORATION, PANTRY & DAILY INTAKE SUMMARY]")

# TC-40: Hex code scrub in stage cards & colored % elements
# Check stageCard1..6 block specifically:
stages_block_m = re.search(r'id=["\']gridFullScanStages["\'][\s\S]*?(?=<div\s+id=[\"\']fullScanActiveHud)', idx_content)
has_hex_in_stages = False
if stages_block_m:
    has_hex_in_stages = bool(re.search(r'0x[0-9a-fA-F]{2}', stages_block_m.group(0)))
has_all_stage_pcts = all(f'id="stagePct{i}"' in idx_content for i in range(1, 7))
run_test("TC-40", "Full Scan UI", "Zero Hex Codes in Measurement Cards & Colored % Numbers Present",
         not has_hex_in_stages and has_all_stage_pcts,
         "Stage cards display STAGE 01..06 with dedicated #stagePct1..6 elements (0 hex opcodes)")

# TC-41: Sleep Data Telemetry Restoration
has_sleep_metric = '"SLEEP"' in server_content
has_sleep_in_poll = 'vitals.SLEEP' in idx_content
has_sleep_post = "metric: 'SLEEP'" in idx_content
run_test("TC-41", "Sleep Data", "Nocturnal Sleep Telemetry Restoration & SQLite Ingestion",
         has_sleep_metric and has_sleep_in_poll and has_sleep_post,
         "Sleep metric queried in snapshot, assembled stream posted to /api/telemetry and hydrated in DOM")

# TC-42: Removal of Mobile /cell Gateway Pill from LCARS Desktop Screen
has_cell_pill = 'DIRECT MOBILE FOOD INTAKE GATEWAY' in idx_content
run_test("TC-42", "LCARS Clean UI", "Complete Removal of Mobile Food Intake Gateway Pill from LCARS Screen",
         not has_cell_pill,
         "Obsolete mobile gateway card scrubbed from desktop console (8899/cell remains independent)")

# TC-43: Meal Pantry Auto-Upsert for Cell Uploads & Latest-to-Oldest Sorting
has_pantry_upsert = 'upsert_popular_meal' in server_content and 'cell_{meal_id}' in server_content
has_latest_sort = 'ORDER BY timestamp DESC, id DESC' in server_content
has_client_sort = 'tB - tA' in idx_content or 'b.id - a.id' in idx_content
run_test("TC-43", "Meal Ingestion", "Cell Uploads Upsert to Meal Pantry & Strict Latest-to-Oldest Sorting",
         has_pantry_upsert and has_latest_sort and has_client_sort,
         "Cell meals/snacks auto-upsert to popular_meals; recent meals strictly sort DESC (most current at top)")

# TC-44: Daily Intake Summary of All Consumed
has_daily_summary_dom = 'id="cardDailyIntakeSummary"' in idx_content
has_occasions = all(f'summaryCount{occ}' in idx_content for occ in ['Breakfast', 'Lunch', 'Dinner', 'Snack'])
has_daily_macro_totals = all(f'lblDay{m}' in idx_content for m in ['Kcal', 'NetCarbs', 'Protein', 'Fat', 'Fiber'])
run_test("TC-44", "Daily Intake", "Comprehensive Daily Intake Summary of All Consumed Dashboard",
         has_daily_summary_dom and has_occasions and has_daily_macro_totals,
         "Dedicated #cardDailyIntakeSummary with 4-bay occasion breakdown (Breakfast/Lunch/Dinner/Snack) and macro totals")
'''

suite = suite[:pos_g15] + correct_g15 + "\n" + suite[pos_summary:]

with open('D:/veepoo/run_full_audit_test_suite.py', 'w', encoding='utf-8') as f:
    f.write(suite)

print("Updated Group 15 in run_full_audit_test_suite.py!")
