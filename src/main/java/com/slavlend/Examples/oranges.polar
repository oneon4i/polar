use 'lib.graphics'
use 'lib.tasks'
use 'lib.array'
use 'lib.polar'

window = new Window()
x = 400
y = 400
@window.setup(800, 480, 'title', initialized)

func initialized() = {
    @window.load_image('test', 'E:\Turtle Game Engine\Contents\CustomEmojis\Dino\dino_run_0.png')
    @window.on_key_hold(key_handler)
    @window.on_update(update)
}

func key_handler(code) = {
    if (code == 29) {
        x -= 10
    } elif (code == 32) {
        x += 10
    } elif (code == 51) {
        y += 10
    } elif (code == 47) {
        y -= 10
    }
}

func update() = {
    @window.clear()
    @window.draw_image('test', x, y)
}