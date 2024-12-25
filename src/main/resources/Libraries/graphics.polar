class CameraSettings(x, y, z, near, far) = {}

class Color(r, g, b) = {}

class Window() = {
    func setup(width, height, title, on_init) = {
        this.provider = reflect 'com.slavlend.Libraries.graphics.window_provider'
        this.reflected = @this.provider.provide(on_init)
        @this.provider.setup(title, width, height)
    }

    func setup_3d(width, height, title, on_init, camera_settings) = {
        this.provider = reflect 'com.slavlend.Libraries.graphics.window_provider'
        this.reflected = @this.provider.provide(on_init)
        @this.provider.setup_3d(title, width, height, camera_settings)
    }

    func load_image(key, path) = {
        @this.reflected.load_image(key, path)
    }

    func add_model(key, path) = {
        @this.reflected.add_model(key, path)
    }

    func draw_model(key, x, y, z, xS, yS, zS) = {
        @this.reflected.draw_model(key, x, y, z, xS, yS, zS)
    }

    func add_light(color, x, y, z) =  {
        @this.reflected.add_light(color, x, y, z)
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

    func on_key_hold(function) = {
        @this.reflected.on_key_hold(function)
    }

    func follow(key) = {
        @this.reflected.follow(key)
    }
}