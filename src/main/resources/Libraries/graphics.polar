# abstract class frame #
class Frame(width, height, title) = {
    func init() = {
        this.window = reflect 'com.slavlend.Libraries.graphics.graphics'
        @this.window.params(
            this.width,
            this.height,
            this.title
        )
        @this.window.init()
    }

    # функция для того, чтобы показать окно #
    func show() = {
        @this.window.show()
    }

    # функция для того, чтобы скрыть окно #
    func hide() = {
        @this.window.hide()
    }

    # функция отрисовки #
    func draw(x, y, path) = {
        @this.window.draw_image(
            x,
            y,
            path
        )
    }

    # очистка экрана #
    func clear() = { @this.window.clear() }
}