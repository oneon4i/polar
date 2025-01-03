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
    // список изображений
    private final HashMap<String, Texture> textures = new HashMap<>();
    // список моделей
    private final HashMap<String, ModelInstance> models = new HashMap<>();
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

    // установка размеров
    public void resize(int width, int height) {
        Gdx.graphics.setWindowedMode(width, height);
    }

    // установка тайтла
    public void set_title(String title) {
        Gdx.graphics.setTitle(title);
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
            PolarLogger.warning("Initialization Method Is Not Set", -1);
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
        if (following != null && !following.isEmpty()) {
            camera.position.set(models.get(following).transform.getTranslation(new Vector3()).add(new Vector3(50, 50, 50)));
            camera.update();
        }
        super.render();
        if (on_updated != null) {
            on_updated.call(null, new ArrayList<>());
        }

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
                settings.getClassValues().get("x").asNumber(),
                settings.getClassValues().get("y").asNumber(),
                settings.getClassValues().get("z").asNumber()
        );
        camera.lookAt(0, 0, 0);
        camera.near = settings.getClassValues().get("near").asNumber();
        camera.far = settings.getClassValues().get("far").asNumber();
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
        float r = color.getClassValues().get("r").asNumber();
        float g = color.getClassValues().get("g").asNumber();
        float b = color.getClassValues().get("b").asNumber();

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
