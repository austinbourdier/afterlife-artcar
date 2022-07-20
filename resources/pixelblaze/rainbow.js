 var speedRange = 1 / 500
    var speed = speedRange
    export function sliderSpeed(s) {
        speed = s * s * speedRange
    }

    var t1 = 0;

    export function beforeRender(delta) {
        t1 = (t1 + delta * speed) % 1
    }
    export function render(index) {
        h = t1 + index / pixelCount;
         s = 1;
          v = 1;
           hsv(h, s, v);
    }