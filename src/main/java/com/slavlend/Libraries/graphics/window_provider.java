package com.slavlend.Libraries.graphics;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.slavlend.Parser.Address;
import com.slavlend.Parser.Statements.FunctionStatement;
import com.slavlend.Polar.PolarObject;
import com.slavlend.Polar.PolarValue;
import com.slavlend.Polar.Reflected;

/*
Провайдер окна
 */
public class window_provider {
    private window wnd;
    public PolarValue provide(FunctionStatement on_init) {
        wnd = new window();
        wnd.on_init(on_init);
        return new PolarValue(new Reflected(new Address(-1), window.class, wnd));
    }

    public void setup(String title, int width, int height) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(title);
        config.setWindowedMode(width, height);
        config.setResizable(true);
        new Lwjgl3Application(wnd, config);
    }

    public void setup_3d(String title, int width, int height, PolarObject settings) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle(title);
        config.setWindowedMode(width, height);
        config.setResizable(true);
        wnd.settings_3d = settings;
        new Lwjgl3Application(wnd, config);
    }
}
