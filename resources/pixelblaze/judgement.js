color = 'transparent'

timeSinceStart = 0

export function beforeRender(delta) {
    timeSinceStart += delta;
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
    }
    else if (timeSinceStart < 39000) {
        if (Math.floor(timeSinceStart / 150) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    }
    else if (timeSinceStart < 40000) {
        if (Math.floor(timeSinceStart / 100) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    }
    else if (timeSinceStart < 41000) {
        if (Math.floor(timeSinceStart / 50) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    }
    else if (timeSinceStart < 42000) {
        if (Math.floor(timeSinceStart / 25) % 2 === 0) {
            rgb(255, 255, 255)
        } else {
            rgb(255, 0, 0)
        }
    }
    else if (timeSinceStart < 50000) {
        if (Math.floor(timeSinceStart / 10) % 2 === 0) {
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