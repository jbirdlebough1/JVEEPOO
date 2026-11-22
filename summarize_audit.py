import json
import os
import re

# 1. Load CLEBUGGER dump
with open(r"D:\veepoo\clebugger_dump.json", "r", encoding="utf-8") as f:
    bugs = json.load(f)

print(f"Total bugs in dump: {len(bugs)}")
# Filter for Veepoo / LCARS related bugs (CLE-001 through CLE-082)
veepoo_bugs = [b for b in bugs if b['bug_code'].startswith("CLE-")]
print(f"Total CLE- bugs: {len(veepoo_bugs)}")

# 2. Inspect transcript.jsonl
transcript_path = r"C:\Users\jclou\.gemini\antigravity\brain\f42bf5fb-c1ac-40d8-890c-c24c46404d2a\.system_generated\logs\transcript.jsonl"
user_prompts = []
if os.path.exists(transcript_path):
    with open(transcript_path, "r", encoding="utf-8") as f:
        for line in f:
            try:
                step = json.loads(line)
                if step.get("type") == "USER_INPUT":
                    content = step.get("content", "")
                    if content and not content.startswith("<SYSTEM_MESSAGE>"):
                        user_prompts.append({
                            "step_index": step.get("step_index"),
                            "created_at": step.get("created_at"),
                            "content": content[:120].strip()
                        })
            except:
                pass

print(f"Total user requests in this session: {len(user_prompts)}")
for p in user_prompts[-10:]:
    print(f"Step {p['step_index']}: {p['content']}")

# 3. Categorize changes from CLE-060 through CLE-082 (recent batches) and all core functional areas
recent_bugs = [b for b in veepoo_bugs if int(b['bug_code'].replace("CLE-", "")) >= 65]
print("\n--- Recent Bugs (CLE-065 through CLE-082) ---")
for b in recent_bugs:
    print(f"{b['bug_code']}: {b['title']} [{b['status']}]")
