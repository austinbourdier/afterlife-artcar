boltMin = floor(pixelCount / 15)
boltMax = ceil(pixelCount / 6)
delayFactor = 10
resetDelayFactor = 0
fade = 15

pixels = array(pixelCount)
x = 0
timer = 0
export function beforeRender(delta) {
    for (i = 0; i < pixelCount; i++)
        pixels[i] -= (pixels[i] * fade * (delta / 1000)) + (1 >> 16)

    timer -= delta

    if (timer <= 0) {
        boltSize = boltMin + random(boltMax - boltMin)
        while (boltSize-- > 0 && x < pixelCount) {
            pixels[x++] = 1
        }

        timer = random(delayFactor) + delayFactor / 5
        timer *= timer


        if (x >= pixelCount) {
            x = 0
            timer = random(resetDelayFactor) + resetDelayFactor / 3
        }
    }

}

export function render(index) {
    v = pixels[index]
    hsv(2 / 3, 0, v)

}