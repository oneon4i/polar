package com.slavlend.Libraries.graphics;

import com.slavlend.Polar.PolarValue;
import com.slavlend.App;

import java.awt.*;

public class graphics extends Frame {
    // параметры
    private int width;
    private int height;
    private String title;

    // конструктор
    public graphics() {
    }

    // параметры окна
    public PolarValue params(PolarValue width,
                             PolarValue height,
                             PolarValue title) {
        this.width = Math.round(width.asNumber());
        this.height = Math.round(height.asNumber());
        this.title = title.asString();
        return null;
    }

    // инициализация
    public PolarValue init() {
        setSize(width, height);
        setTitle(title);
        setVisible(true);
        return null;
    }

    // рисуем
    public PolarValue draw_image(PolarValue x, PolarValue y, PolarValue path) {
        String fullPath = App.parser.getEnv() + "/" + path.asString();
        add(new image(fullPath, Math.round(x.asNumber()), Math.round(y.asNumber())));
        repaint();
        return null;
    }

    // очистка
    public void clear() {
        repaint();
    }
}
