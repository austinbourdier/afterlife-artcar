color = 'transparent'

timeSinceStart = 0
boltMin = floor(pixelCount / 15)
boltMax = ceil(pixelCount / 6)
delayFactor = 10
resetDelayFactor = 0
fade = 15

pixels = array(pixelCount)
x = 0
timer = 0
numSparks = 5
accel = .1
speed = .02
cooling1 = .02 // subtractive cooling
cooling2 = .97 // coefficient cooling
sparks = array(numSparks)
sparkX = array(numSparks)

export function beforeRender(delta) {
    timeSinceStart += delta;
    if (timeSinceStart > 49000) {
        if (judgementWinner() === "heaven") {
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
        } else if (judgementWinner() === "hell") {
            delta *= speed
            for (i = 0; i < pixelCount; i++) {
                cooldown = cooling1 * delta

                if (cooldown > pixels[i]) {
                    pixels[i] = .9
                } else {
                    pixels[i] = pixels[i] * cooling2 - cooldown
                }
            }

            for (k = pixelCount - 1; k >= 4; k--) {
                h1 = pixels[k - 1]
                h2 = pixels[k - 2]
                h3 = pixels[k - 3]
                h4 = pixels[k - 4]
                pixels[k] = (h1 + h2 + h3 * 2 + h4 * 3) / 7
            }

            for (i = 0; i < numSparks; i++) {
                if (sparks[i] <= 0) {
                    sparks[i] = random(1)
                    sparkX[i] = 0
                }
                sparks[i] += accel * delta

                ox = sparkX[i]
                sparkX[i] += sparks[i] * sparks[i] * delta
                if (sparkX[i] > pixelCount) {
                    sparkX[i] = 0
                    sparks[i] = 0
                    continue
                }

                for (j = ox; j < sparkX[i]; j++)
                    pixels[j] += clamp(1 - sparks[i] * .4, 0, 1) * .5
            }
        }

    }
}

export function render(index) {
    if (timeSinceStart < 33000) {
        if (Math.floor(timeSinceStart / 5000) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 35000) {
        if (Math.floor(timeSinceStart / 500) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 36000) {
        if (Math.floor(timeSinceStart / 400) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 37000) {
        if (Math.floor(timeSinceStart / 300) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 38000) {
        if (Math.floor(timeSinceStart / 200) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 39000) {
        if (Math.floor(timeSinceStart / 150) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 40000) {
        if (Math.floor(timeSinceStart / 100) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 41000) {
        if (Math.floor(timeSinceStart / 50) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 42000) {
        if (Math.floor(timeSinceStart / 25) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else if (timeSinceStart < 50000) {
        if (Math.floor(timeSinceStart / 10) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    } else {
        // Show winning color
        if (judgementWinner() === "heaven") {
            v = pixels[index]
            hsv(2 / 3, 0, v)
        } else if (judgementWinner() === "hell") {
            v = pixels[index]
            hsv(.1 * clamp(v * v, 0, 1), 1 - (v - 1) * 2, v * 2)
        } else {
            // tie
            if (index % 2 === 0) {
                rgb(255, 255, 255)
            } else {
                rgb(255, 0, 0)
            }
        }
    }
}