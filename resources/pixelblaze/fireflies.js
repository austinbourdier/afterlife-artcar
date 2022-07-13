leader = 0
direction = 1
pixels = array(pixelCount)
speed = pixelCount  / 700
fade = .00015
export function beforeRender(delta) {
  leader += direction * delta * speed
  if (leader >= pixelCount) {
    direction = -direction
    leader = pixelCount -1
  }

  if (leader < 0) {
    direction = -direction
    leader = 0
  }
  pixels[floor(leader)] = 1
  for (i = 0; i < pixelCount; i++) {
    pixels[i] -= delta * fade
    pixels[i] = max(0, pixels[i])
  }
}
export function render(index) {
  v = pixels[index]
  v = v*v*v
  hsv(0, 1, v)
}
