package com.slavlend.Libraries;

import com.slavlend.Polar.PolarValue;

/*
Математический модуль.
Имеет базовый модуль математики.
 */
@SuppressWarnings("unused")
public class math {
    // конструктор
    public math() {
    }

    // степень
    public PolarValue pow(PolarValue a, PolarValue b) {
        return new PolarValue((float) Math.pow(a.asNumber(), b.asNumber()));
    }

    // квадратный корень
    public PolarValue sqrt(PolarValue a) {
        return new PolarValue((float) Math.sqrt(a.asNumber()));
    }

    // кубический корень
    public PolarValue cbrt(PolarValue a) {
        return new PolarValue((float) Math.cbrt(a.asNumber()));
    }

    // синус
    public PolarValue sin(PolarValue a) { return new PolarValue((float) Math.sin(a.asNumber()));}

    // косинус
    public PolarValue cos(PolarValue a) { return new PolarValue((float) Math.cos(a.asNumber()));}

    // тангенс
    public PolarValue tan(PolarValue a) { return new PolarValue((float) Math.tan(a.asNumber()));}

    // а-синус
    public PolarValue asin(PolarValue a) { return new PolarValue((float) Math.asin(a.asNumber()));}

    // а-косинус
    public PolarValue acos(PolarValue a) { return new PolarValue((float) Math.acos(a.asNumber()));}

    // а-тангенс
    public PolarValue atan(PolarValue a) { return new PolarValue((float) Math.atan(a.asNumber()));}

    // а-тангенс с двумя переменными
    public PolarValue atan2(PolarValue a, PolarValue b) { return new PolarValue((float) Math.atan2(a.asNumber(), b.asNumber()));}

    // логорифм
    public PolarValue log(PolarValue a) { return new PolarValue((float) Math.log(a.asNumber()));}

    // логорифм по основанию 10
    public PolarValue log10(PolarValue a) { return new PolarValue((float) Math.log10(a.asNumber()));}

    // экспонсеальное выражение
    public PolarValue exp(PolarValue a) { return new PolarValue((float) Math.exp(a.asNumber()));}

    // в радианы
    public PolarValue to_rad(PolarValue a) { return new PolarValue((float) Math.toRadians(a.asNumber()));}

    // в градусы
    public PolarValue to_deg(PolarValue a) { return new PolarValue((float) Math.toDegrees(a.asNumber()));}

    // гипотенуза
    public PolarValue hypot(PolarValue a, PolarValue b) { return new PolarValue((float) Math.hypot(a.asNumber(), b.asNumber()));}

    // синус-х
    public PolarValue sinh(PolarValue a) { return new PolarValue((float) Math.sinh(a.asNumber()));}

    // косинус-х
    public PolarValue cosh(PolarValue a) { return new PolarValue((float) Math.cosh(a.asNumber()));}

    // тангенс-х
    public PolarValue tanh(PolarValue a) { return new PolarValue((float) Math.tanh(a.asNumber()));}

    // округление
    public PolarValue round(PolarValue a) {
        return new PolarValue((float) Math.round(a.asNumber()));
    }

    // модуль числа
    public PolarValue abs(PolarValue a) {
        return new PolarValue(Math.abs(a.asNumber()));
    }
}
