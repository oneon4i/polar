use 'lib.graphics'
use 'lib.tasks'
use 'lib.array'
use 'lib.polar'

window = new Window()
x = 0
y = 0
z = 0
xS = 1
yS = 1
zS = 1
@window.setup_3d(800, 480, 'title', initialized, new CameraSettings(10, 10, 10, 1, 1000))

func initialized() = {
    @window.load_image('test', 'E:\Turtle Game Engine\Contents\CustomEmojis\Dino\dino_run_0.png')
    @window.add_model('test', 'C:\Users\MuraDana\Documents\t_34_obj.obj')
    @window.add_model('cat', 'C:\Users\MuraDana\Documents\model.obj')
    @window.add_light(new Color(1, 1, 1), -1, -0.8, -0.2)
    @window.on_key_hold(key_handler)
    @window.on_update(update)
    @window.follow('test')
}

func update() = {
    @put('upd')
    @window.clear()
    @window.draw_model('test', 0-y, z, 0-x, xS, yS, zS)
    @window.draw_model('cat', 0, 0, 0, 5, 5, 5)
    @window.draw_image('test', x, y)
}
func key_handler(code) = {
    if (code == 29) {
        x -= 1
    } elif (code == 32) {
        x += 1
    } elif (code == 51) {
        y += 1
    } elif (code == 47) {
        y -= 1
    }
}