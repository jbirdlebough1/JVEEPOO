import json
import asyncio
import os
import edge_tts

async def main():
    with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
        slides = json.load(f)

    s25 = [s for s in slides if s['num'] == '25'][0]
    voice = "en-AU-NatashaNeural"
    tts = edge_tts.Communicate(s25['narration'], voice)
    
    for adir in [r"D:\veepoo\audio", r"D:\veepoo\presentation\audio"]:
        target = os.path.join(adir, "slide_25.mp3")
        await tts.save(target)
        print(f"Saved {target} successfully!")

if __name__ == '__main__':
    asyncio.run(main())
