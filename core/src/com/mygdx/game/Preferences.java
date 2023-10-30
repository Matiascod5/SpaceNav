package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class Preferences {
    private static final String MUSICA_ACTIVADA_KEY = "musicaActivada";
    private static final String VOLUMEN_MUSICA_KEY = "volumenMusica";

    public static boolean getMusicaActivada() {
        // Recuperar el valor de música activada de las preferencias
        return Gdx.app.getPreferences("MyGamePreferences").getBoolean(MUSICA_ACTIVADA_KEY, true);
    }

    public static void setMusicaActivada(boolean activada) {
        // Guardar el valor de música activada en las preferencias
        Gdx.app.getPreferences("MyGamePreferences").putBoolean(MUSICA_ACTIVADA_KEY, activada);
        Gdx.app.getPreferences("MyGamePreferences").flush();
    }

    public static float getVolumenMusica() {
        // Recuperar el valor del volumen de música de las preferencias
        return Gdx.app.getPreferences("MyGamePreferences").getFloat(VOLUMEN_MUSICA_KEY, 1.0f);
    }

    public static void setVolumenMusica(float volumen) {
        // Guardar el valor del volumen de música en las preferencias
        Gdx.app.getPreferences("MyGamePreferences").putFloat(VOLUMEN_MUSICA_KEY, volumen);
        Gdx.app.getPreferences("MyGamePreferences").flush();
    }
}
