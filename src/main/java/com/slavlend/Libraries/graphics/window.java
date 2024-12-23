package com.slavlend.Libraries.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.slavlend.Parser.Statements.FunctionStatement;
import com.slavlend.Polar.PolarValue;

import java.util.ArrayList;
import java.util.HashMap;

/*
Библиотека для работы с окнами
 */
public class window extends ApplicationAdapter implements InputProcessor {
    /*
    // объект
    public static class gameobject {
        private int y;
        private final String image;
        private int x;
        private int height;
        private int width;

        public gameobject(String image, int x, int y, int width, int height) {
            this.image = image;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        public void set_pos(int _x, int _y) {
            this.x = _x;
            this.y = _y;
        }

        public void set_size(int _width, int _height) {
            this.width = _width;
            this.height = _height;
        }

        public int getHeight() {
            return height;
        }

        public int getWidth() {
            return width;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String getImage() {
            return image;
        }
    }
    // изображения
    private HashMap<String, Image> images = new HashMap<>();
    private HashMap<String, gameobject> gameobjects = new HashMap<String, gameobject>();
    // функция
    private FunctionStatement loop_handler;

    // конструктор
    public window() {

    }

    // метод инициализация
    public void init(int width, int height, String title) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(width, height);
        setVisible(true);
    }

    // загрузка изображения
    public void load_image(String key, String path) {
        images.put(key, new ImageIcon(path).getImage());
    }

    // изменение кординат изображения
    public void set_pos(String name, int x, int y) {
        gameobjects.get(name).set_pos(x, y);
        repaint_window();
    }

    // изменение размера изображения
    public void set_size(String name, int w, int h) {
        gameobjects.get(name).set_size(w, h);
        repaint_window();
    }

    // получение изображения
    public PolarValue add_object(PolarObject obj, String name, String key, int x, int y, int w, int h) {
        gameobjects.put(name, new gameobject(key, x, y, w, h));
        gameobject go = gameobjects.get(name);
        ArrayList<PolarValue> args = new ArrayList<>();
        args.add(new PolarValue(x));
        args.add(new PolarValue(y));
        args.add(new PolarValue(w));
        args.add(new PolarValue(h));
        args.add(new PolarValue(name));
        args.add(new PolarValue(obj));
        PolarObject po = PolarObject.create(Classes.getInstance().getClass("GameObject"), args);
        return new PolarValue(po);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (gameobject go : gameobjects.values()) {
            g.drawImage(images.get(go.image), go.x, go.y, go.width, go.height, null);
        }
        if (loop_handler != null) {
            loop_handler.call(null, new ArrayList<>());
        }
    }

    // хэндлим
    public void handle_loop(FunctionStatement func) {
        this.loop_handler = func;
    }

    // репэинт
    public void repaint_window() {
        repaint();
    }

     */

    private HashMap<String, Texture> textures = new HashMap<>();
    private String title;
    private int height;
    private int width;
    private FunctionStatement on_initialized;
    private FunctionStatement on_updated;
    private FunctionStatement on_key_downed;

    public window() {

    }

    public void init(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    @Override
    public void create() {
        super.create();
        Gdx.input.setInputProcessor(this); // Устанавливаем обработчик ввода
        on_initialized.call(null, new ArrayList<>());
    }

    @Override
    public void render() {
        super.render();
        on_updated.call(null, new ArrayList<>());
    }

    public void on_init(FunctionStatement func) {
        this.on_initialized = func;
    }
    public void on_update(FunctionStatement func) {
        this.on_updated = func;
    }
    public void on_key_down(FunctionStatement func) {
        this.on_key_downed = func;
    }
    public void load_image(String key, String path) {
        textures.put(key, new Texture(path));
    }

    public void draw_image(String key, int x, int y) {
        SpriteBatch batch = new SpriteBatch();
        batch.begin();
        batch.draw(textures.get(key), x, y);
        batch.end();
    }

    public void clear() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public boolean keyDown(int i) {
        ArrayList<PolarValue> params = new ArrayList<>();
        params.add(new PolarValue(((Integer) i).floatValue()));
        on_key_downed.call(null, params);
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
