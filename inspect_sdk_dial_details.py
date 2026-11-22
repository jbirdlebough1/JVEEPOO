import os

paths = [
    r"D:\veepoo\opt1_sdk_official\android_sdk_source\apidoc\com\veepoo\protocol\util\DialPictureUtil.html",
    r"D:\veepoo\opt2_decompiled\vpprotocol\sources\com\veepoo\protocol\util\ai\AiDialJLPreviewSenderImpl.java",
    r"D:\veepoo\opt2_decompiled\vpprotocol\sources\com\veepoo\protocol\AiDialPreviewManager.java"
]

for p in paths:
    if os.path.exists(p):
        print(f"\n================ {os.path.basename(p)} ================")
        with open(p, "r", encoding="utf-8", errors="ignore") as f:
            print(f.read()[:2000])
    else:
        print(f"Not found: {p}")
