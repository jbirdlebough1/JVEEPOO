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
                
            print(f"Slide {num}: sensor audio rendered ({len(narration)} chars)")
        except Exception as e:
            print(f"Slide {num} ERROR: {e}")

async def main():
    with open('D:/veepoo/all_slides_data.json', 'r', encoding='utf-8') as f:
        slides = json.load(f)

    target_nums = ["14", "15", "16", "17", "18"]
    slides_to_render = [s for s in slides if s['num'] in target_nums]
    print(f"Rendering {len(slides_to_render)} sensor audio tracks...")

    audio_dirs = [r"D:\veepoo\audio", r"D:\veepoo\presentation\audio"]
    sem = asyncio.Semaphore(5)
    tasks = [render_slide(sem, s, audio_dirs) for s in slides_to_render]
    await asyncio.gather(*tasks)
    print("Sensor audio rendering complete!")

if __name__ == '__main__':
    asyncio.run(main())
