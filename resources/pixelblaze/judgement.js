color = 'transparent'

timeSinceStart = 0

export function beforeRender(delta) {
    timeSinceStart += delta;
}

export function render(index) {
  if (timeSinceStart < 10000) {
    if (Math.floor(timeSinceStart / 2000) % 2 === 0) {
        rgb(255, 255, 255)
    } else {
        rgb(255, 0, 0)
    }
  } else if (timeSinceStart < 18000) {
    if (Math.floor(timeSinceStart / 1000) % 2 === 0) {
        rgb(255, 255, 255)
    } else {
        rgb(255, 0, 0)
    }
  } else if (timeSinceStart < 24000) {
    if (Math.floor(timeSinceStart / 500) % 2 === 0) {
        rgb(255, 255, 255)
    } else {
        rgb(255, 0, 0)
    }
  } else if (timeSinceStart < 28000) {
    if (Math.floor(timeSinceStart / 250) % 2 === 0) {
        rgb(255, 255, 255)
    } else {
        rgb(255, 0, 0)
    }
  } else if (timeSinceStart < 30000) {
    if (Math.floor(timeSinceStart / 100) % 2 === 0) {
        rgb(255, 255, 255)
    } else {
        rgb(255, 0, 0)
    }
  } else {
    // Show winning color
    if (judgementWinner() === "heaven") {
        rgb(255, 255, 255)
    } else if (judgementWinner() === "hell") {
        rgb(255, 0, 0)
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