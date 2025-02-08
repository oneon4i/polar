use 'lib.window2d'

window = Window2DProvider.provide()
sprites = {}
window.start(960, 640, 'test_title', on_start, on_update)

func on_start() {
    sprites.set(
        'test',
        window.create_sprite(
            'E:\6f4c5d3c1fbc4598b8639967ff33.jpg'
        )
    )
}

func on_update() {
    sprite = sprites.get('test')
    sprite.setX(100)
    sprite.setY(200)
    sprite.setSize(150, 100)
    window.draw(sprite)
}