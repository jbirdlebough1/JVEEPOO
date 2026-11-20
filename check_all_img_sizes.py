import os
import struct

def get_image_size(path):
    try:
        with open(path, 'rb') as f:
            data = f.read(30)
            if data[:8] == b'\x89PNG\r\n\x1a\n':
                w, h = struct.unpack('>II', data[16:24])
                return w, h
    except:
        pass
    return None

img_dir = r"D:\veepoo\images"
for f in os.listdir(img_dir):
    if f.endswith('.png'):
        p = os.path.join(img_dir, f)
        print(f"{f}: {get_image_size(p)}")
