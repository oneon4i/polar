package com.slavlend.Compiler.Libs;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.slavlend.Compiler.Compiler;
import com.slavlend.Vm.VmFunction;
import lombok.Getter;

/*
Библиотека для работы с 2Д окном
 */

@Getter
public class Window2D implements ApplicationListener {
    // при старте
    private VmFunction onStart;
    // при обновлении
    private VmFunction onUpdate;
    // для отрисовки спрайтов
    private SpriteBatch spriteBatch;

    // старт окна
    public void start(float width, float height, String title,
                      VmFunction on_start, VmFunction on_update) {
        this.onStart = on_start;
        this.onUpdate = on_update;
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(title);
        config.setWindowedMode(((Float)width).intValue(), ((Float)height).intValue());
        new Lwjgl3Application(this, config);
    }

    public Object create_sprite(String path) {
        return new Sprite(new Texture(Gdx.files.internal(path)));
    }

    public void draw(Sprite sprite) {
        sprite.draw(spriteBatch);
    }

    @Override
    public void create() {
        spriteBatch = new SpriteBatch();
        onStart.exec(Compiler.iceVm, false);
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        spriteBatch.begin();
        onUpdate.exec(Compiler.iceVm, false);
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
