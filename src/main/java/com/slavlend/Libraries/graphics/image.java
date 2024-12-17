package com.slavlend.Libraries.graphics;

import com.slavlend.Env.PolarEnv;
import com.slavlend.Parser.Address;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;

public class image  extends JPanel {
    private Image image;
    private int x = 50, y = 50; // Начальные координаты изображения

    public image(String path, int x, int y) {
        // Загрузка изображения
        try {
            image = ImageIO.read(new File(path));
            this.x = x;
            this.y = y;
        } catch (IOException e) {
            PolarEnv.Crash("Image not found: " + path, new Address(-1));
        }

        // Настройка слушателей
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Применение трансформации (масштабирование и перевод по координатам)
        AffineTransform transform = new AffineTransform();
        transform.translate(x, y);
        g2d.drawImage(image, transform, this);
    }
}
