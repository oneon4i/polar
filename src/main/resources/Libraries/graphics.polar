class Window() = {
    func setup(width, height, title, on_init) = {
        this.provider = reflect 'com.slavlend.Libraries.graphics.window_provider'
        this.reflected = @this.provider.provide(on_init)
        @this.provider.setup(title, width, height)
    }

    func load_image(key, path) = {
        @this.reflected.load_image(key, path)
    }

    func draw_image(key, x, y) = {
        @this.reflected.draw_image(key, x, y)
    }

    func on_update(function) = {
        @this.reflected.on_update(function)
    }

    func clear() = {
        @this.reflected.clear()
    }

    func on_key_down(function) = {
        @this.reflected.on_key_down(function)
    }
}