package com.mygdx.game;

public interface Disparo {
	final static boolean destroyed = false;
	
	public void mostrar();
	public void movimiento();
	public void actualizarMov();
	public boolean verificarColision();
	public boolean verificarDestruccion();
	public abstract void efectoEspecial();
}