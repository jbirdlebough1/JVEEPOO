import json
import asyncio
import os
import edge_tts

async def render_slide(sem, s, audio_dirs):
    num = s['num']
    narration = s['narration']
    voice = "en-AU-NatashaNeural"
    
    async with sem:
        try:
            tts = edge_tts.Communicate(narration, voice)
            temp_file = f"D:/veepoo/audio/temp_slide_{num}.mp3"
            await tts.save(temp_file)
            
            for adir in audio_dirs:
                target_file = os.path.join(adir, f"slide_{num}.mp3")
                with open(temp_file, "rb") as src, open(target_file, "wb") as dst:
                    dst.write(src.read())
                    
            if os.path.exists(temp_file):
                os.remove(temp_file)
                
            print(f"Slide {num}: audio re-rendered ({len(narration)} chars)")
        except Exception as e:
            print(f"Slide {num} ERROR: {e}")

async def main():
    with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
        slides = json.load(f)

    target_nums = [
        "05", "10", "11", "12", "13", "20", "21", "22", "23", "25",
        "26", "27", "28", "30", "31", "32", "33", "34", "35", "36",
        "37", "38", "39", "40", "41", "42", "44", "45", "47"
    ]
    
    slides_to_render = [s for s in slides if s['num'] in target_nums]
    print(f"Rendering {len(slides_to_render)} updated audio tracks...")

    audio_dirs = [r"D:\veepoo\audio", r"D:\veepoo\presentation\audio"]
    sem = asyncio.Semaphore(5)
    tasks = [render_slide(sem, s, audio_dirs) for s in slides_to_render]
    await asyncio.gather(*tasks)
    print("Re-rendering complete!")

if __name__ == '__main__':
    asyncio.run(main())
