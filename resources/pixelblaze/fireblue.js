// init variables
// these were tested with a 72-pixel APA102 strip, adjust to flavor
cooling = 0.025  // fire cooling rate
sparking = 1  // fire sparking rate
delay = 20  // delay between new frames
timer = 0  // accumulates delay between render passes
dirMode = 0  // direction (and symmetry) of fire
// one-sided:  0 - from head, 1 - from tail
// symmetric:  2 - from ends, 3 - from middle
if (dirMode < 2) {   // pixelCount with respect to dirMode
  pixelsUsed = pixelCount
} else {
  pixelsUsed = floor(pixelCount / 2)
  cooling += 0.04  // use a little more cooling to match
}
heat = array(pixelsUsed)  // array to store 'temperature' of pixels

// create HeatColors 8-bit palette:  0x000000,
//  0x000033, 0x000066, 0x000099, 0x0000CC, 0x0000FF,
//  0x0033FF, 0x0066FF, 0x0099FF, 0x00CCFF, 0x00FFFF,
//  0x33FFFF, 0x66FFFF, 0x99FFFF, 0xCCFFFF, 0xFFFFFF
heatColors_r = array(256)
heatColors_g = array(256)
heatColors_b = array(256)

for (i = 0; i < 256; i++) {
  if (i < 85) { heatColors_b[i] = (i / 85) } else { heatColors_b[i] = 1 }
  if (i >= 85) { if (i < 170) { heatColors_g[i] = ((i - 85) / 85) } else { heatColors_g[i] = 1 } }
  if (i >= 170) { heatColors_r[i] = ((i - 170) / 85) }
}

export function beforeRender(delta) {
  timer += delta
  if (timer >= delay) {  // if enough time has passed, render next frame
    timer -= delay
    // step 1:  cool down the entire strip
    for (i = 0; i < pixelsUsed; i++) {
      heat[i] -= random(cooling)
      heat[i] = clamp(heat[i],0,1)
    }
    // step 2:  carry heat 'up' the strip
    for (i = pixelsUsed - 1; i >= 2; i--) {
      if (i % 50 >= 2) {
        heat[i] = (heat[i - 1] + heat[i - 2] + heat[i - 2]) / 3
      }
    }
    // step 3:  add sparks to the base of the flame
    if (sparking > random(1)) {
        r = random(pixelsUsed / 50)
      i = floor(r) * 50

      heat[i] = heat[i] + random(0.37) + 0.63  // add a minimum amount of heat to the pixel

      heat[i] = clamp(heat[i],0,1)
    }
  }  // end if (timer >= delay)
}  // end export function beforeRender(delta)
export function render(index) {

  heat_index = floor(clamp(heat[index] * 256, 0, 255))
  r = heatColors_r[heat_index]
  g = heatColors_g[heat_index]
  b = heatColors_b[heat_index]

  rgb(r, g, b)

}  // end export function render(index)
