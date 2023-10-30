package com.mygdx.game;

public class Configuracion {
    private static boolean musicaActivada = true;

    public static boolean isMusicaActivada() {
        return musicaActivada;
    }

    public static void setMusicaActivada(boolean activada) {
        musicaActivada = activada;
    }
}
