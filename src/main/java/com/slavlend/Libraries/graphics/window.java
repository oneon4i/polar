package com.slavlend.Libraries.graphics;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.slavlend.Logger.PolarLogger;
import com.slavlend.Parser.Statements.FunctionStatement;
import com.slavlend.Polar.PolarObject;
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

    // список изображений
    private HashMap<String, Texture> textures = new HashMap<>();
    // список моделей
    private HashMap<String, ModelInstance> models = new HashMap<>();
    // заголовок
    private String title;
    // высота
    private int height;
    // ширина
    private int width;
    // хэндлеры
    private FunctionStatement on_initialized;
    private FunctionStatement on_updated;
    private FunctionStatement on_key_downed;
    private FunctionStatement on_key_holded;
    private FunctionStatement on_key_up;
    // зажатые клавиши
    private ArrayList<Integer> holdings_keys = new ArrayList<Integer>();
    // 3д камера
    private PerspectiveCamera camera;
    // 3д окружение
    private Environment environment;
    // 3д настройки
    public PolarObject settings_3d;
    // 3д модельный батч для 3д отрисовки
    public ModelBatch modelBatch;
    // 2д модельный батч для 2д отрисовки
    public SpriteBatch spriteBatch;
    // слежка
    private String following;

    // конструктор
    public window() {

    }

    // инициализация окна
    public void init(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
    }

    // при создании окна
    @Override
    public void create() {
        super.create();
        // создаем батчи
        modelBatch = new ModelBatch();
        spriteBatch = new SpriteBatch();
        // если есть 3д настройки -> иницализируем
        if (settings_3d != null) {
            setup_3d(settings_3d);
        }

        // Настройка управления камерой
        // Gdx.input.setInputProcessor(new CameraInputController(camera));
        // устанавливаем инпут процессор на текущий
        Gdx.input.setInputProcessor(this);
        // вызываем функцию
        if (on_initialized != null) {
            on_initialized.call(null, new ArrayList<>());
        } else {
            PolarLogger.Warning("Initialization Method Is Not Set", -1);
        }
    }

    public void set_camera_input() {
        Gdx.input.setInputProcessor(new CameraInputController(camera));
    }

    public void set_default_input() {
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void render() {
        if (following != null && !following.equals("")) {
            camera.position.set(models.get(following).transform.getTranslation(new Vector3()).add(new Vector3(50, 50, 50)));
            camera.update();
        }
        super.render();
        on_updated.call(null, new ArrayList<>());

        for (Integer i : holdings_keys) {
            ArrayList<PolarValue> list = new ArrayList<>();
            list.add(new PolarValue(i.floatValue()));
            if (on_key_holded != null) {
                on_key_holded.call(null, list);
            }
        }
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

    public void on_key_hold(FunctionStatement func) {
        this.on_key_holded = func;
    }

    public void load_image(String key, String path) {
        textures.put(key, new Texture(path));
    }

    public void draw_image(String key, int x, int y, int w, int h) {
        spriteBatch.begin();
        spriteBatch.draw(textures.get(key), x, y, w, h);
        spriteBatch.end();
    }

    public void clear() {
        Gdx.gl.glClearColor(1, 1, 1, 1); // Устанавливает цвет фона в белый
        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    @Override
    public boolean keyDown(int i) {
        ArrayList<PolarValue> params = new ArrayList<>();
        params.add(new PolarValue(((Integer) i).floatValue()));
        if (on_key_downed != null) {
            on_key_downed.call(null, params);
        }
        holdings_keys.add(i);
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        if (holdings_keys.contains((Integer) i)) {
            holdings_keys.remove((Integer) i);
        }
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

    // установка 3д среды
    public void setup_3d(PolarObject settings) {
        // камера
        camera = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(
                settings.classValues.get("x").asNumber(),
                settings.classValues.get("y").asNumber(),
                settings.classValues.get("z").asNumber()
        );
        camera.lookAt(0, 0, 0);
        camera.near = settings.classValues.get("near").asNumber();
        camera.far = settings.classValues.get("far").asNumber();
        camera.update();

        // куб
        ModelBuilder modelBuilder = new ModelBuilder();
        Model box = modelBuilder.createBox(5f, 5f, 5f,
                new Material(ColorAttribute.createDiffuse(Color.BLUE)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        ModelInstance boxInstance = new ModelInstance(box);
        models.put("box", boxInstance);

        // окружение
        environment = new Environment();
    }

    // установка света
    public void add_light(PolarObject color, float x, float y, float z)
    {
        float r = color.classValues.get("r").asNumber();
        float g = color.classValues.get("g").asNumber();
        float b = color.classValues.get("b").asNumber();

        environment.add(new DirectionalLight().set(r, g, b, x, y, z));
    }

    // добавление модели
    public void add_model(String key, String path) {
        // объект
        ObjLoader loader = new ObjLoader();
        // модель
        Model model = loader.loadModel(Gdx.files.internal(path));
        ModelInstance modelInstance = new ModelInstance(model);
        // помещаем модель
        models.put(key, modelInstance);
    }

    // отрисовка модели
    public void draw_model(String key, int x, int y, int z, int xS, int yS, int zS) {
        modelBatch.begin(camera);
        models.get(key).transform.setToTranslationAndScaling(new Vector3(x, y, z), new Vector3(xS, yS, zS));
        modelBatch.render(models.get(key), environment);
        modelBatch.end();
    }

    // слежка камеры
    public void follow(String key) {
        following = key;
    }
}
