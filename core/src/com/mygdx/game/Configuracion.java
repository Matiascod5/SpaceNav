package com.mygdx.game;

public class Configuracion {
    private static boolean musicaActivada = true;
    private static float volumenMusica = 1.0f;

    public static boolean isMusicaActivada() {
        return musicaActivada;
    }

    public static void setMusicaActivada(boolean activada) {
        musicaActivada = activada;
    }

    public static float getVolumenMusica() {
        return volumenMusica;
    }

    public static void setVolumenMusica(float volumen) {
        volumenMusica = volumen;
    }
}

