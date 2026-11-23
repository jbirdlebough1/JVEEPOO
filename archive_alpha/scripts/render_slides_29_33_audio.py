import json
import asyncio
import os
import shutil
import edge_tts

async def render_slide(sem, num, narration, voice, audio_dirs):
    async with sem:
        try:
            tts = edge_tts.Communicate(narration, voice)
            temp_file = f"D:/veepoo/audio/temp_slide_{num}.mp3"
            await tts.save(temp_file)
            
            for adir in audio_dirs:
                os.makedirs(adir, exist_ok=True)
                target_file = os.path.join(adir, f"slide_{num}.mp3")
                shutil.copy2(temp_file, target_file)
                
            if os.path.exists(temp_file):
                os.remove(temp_file)
                
            print(f"Slide {num}: audio rendered successfully ({len(narration)} chars)")
        except Exception as e:
            print(f"Slide {num} ERROR: {e}")

async def main():
    with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
        slides = json.load(f)

    target_nums = ["29", "30", "31", "32", "33"]
    slides_to_render = [s for s in slides if s['num'] in target_nums]
    print(f"Rendering {len(slides_to_render)} audio tracks for Slides 29-33...")

    audio_dirs = [r"D:\veepoo\audio", r"D:\veepoo\presentation\audio"]
    sem = asyncio.Semaphore(3)
    voice = "en-AU-NatashaNeural"
    tasks = [render_slide(sem, s['num'], s['narration'], voice, audio_dirs) for s in slides_to_render]
    await asyncio.gather(*tasks)
    print("All audio renders for Slides 29-33 complete!")

if __name__ == '__main__':
    asyncio.run(main())
