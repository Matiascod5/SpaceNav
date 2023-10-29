/*public abstract class Marciano {
	private int x;
	private int y;
	private int xVel = 5;
	private int yVel = 5;
	private Sprite sprite;
	private int cantEnemigos;
	//private  ArrayList<Enemigo> balls1 = new ArrayList<>();
	//private  ArrayList<Enemigo> balls2 = new ArrayList<>();
	
	public Marciano() {
        super(new Sprite(new Texture(Gdx.files.internal("aGreyMedium4.png")));,3,3)
	}
	
	public void añadirEnemigo(Enemigo enemigo, ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2) {
	    for (int i = 0; i < cantEnemigos; i++) {
	        //Ball2 bb = new Ball2(r.nextInt((int)Gdx.graphics.getWidth()),	50+r.nextInt((int)Gdx.graphics.getHeight()-50),	20+r.nextInt(10), xVel+r.nextInt(4), yVel+r.nextInt(4), 
	  	            //new Texture(Gdx.files.internal("aGreyMedium4.png")));	   
	  	    balls1.add(enemigo);
	  	    balls2.add(enemigo);
	  	}
	}

	public void mostrarEnemigo(SpriteBatch batch){
		Sprite sprite = getSprite();
		sprite.draw(batch);
	}

/*
	public void movimiento() {
        x += getxVel();
        y += getyVel();

        if (x+getxVel() < 0 || x+getxVel()+sprite.getWidth() > Gdx.graphics.getWidth())
        	setxVel(getxVel() * -1);
        if (y+getyVel() < 0 || y+getyVel()+sprite.getHeight() > Gdx.graphics.getHeight())
        	setyVel(getyVel() * -1);
        sprite.setPosition(x, y);
    }*/
	
	/*
	public void mostrarEnemigo(SpriteBatch batch, ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2) {
    	for (int i = 0; i < balls1.size(); i++) {
    	  	  //mostrarAsteroide();
    	  	    Enemigo enemigo = balls1.get(i);
    	  	    Sprite b = enemigo.getSprite();
    	  	    b.draw(batch);
    	}
    }*/
	/*
	public void draw(SpriteBatch batch) {
    	sprite.draw(batch);
    }
	//public abstract Rectangle getArea();
	//public abstract int getxVel();
	//public abstract void setxVel(int xVel);
	//public abstract int getyVel();
	//public abstract void setyVel(int yVel);
	public abstract void movimiento();

	/*
	
	public void movimiento() {
        x += getxVel();
        y += getyVel();

        if (x+getxVel() < 0 || x+getxVel()+sprite.getWidth() > Gdx.graphics.getWidth())
        	setxVel(getxVel() * -1);
        if (y+getyVel() < 0 || y+getyVel()+sprite.getHeight() > Gdx.graphics.getHeight())
        	setyVel(getyVel() * -1);
        sprite.setPosition(x, y);
    }
	*/
	
	
/*
	public void verificarColisionNave(Nave nave, ArrayList<Enemigo> balls1, ArrayList<Enemigo> balls2) {
    	for (int i = 0; i < balls1.size(); i++) {
    		Enemigo b=balls1.get(i);
    		if (nave.verificarColisionNave(b)) {
    			balls1.remove(i);
             	balls2.remove(i);
             	i--;
    		}
    	}
    }
	
	public void checkCollision(Enemigo b2) {
        if(sprite.getBoundingRectangle().overlaps(b2.sprite.getBoundingRectangle())){
        	// rebote
            if (getxVel() ==0) setxVel(getxVel() + b2.getxVel()/2);
            if (b2.getxVel() ==0) b2.setxVel(b2.getxVel() + getxVel()/2);
        	setxVel(- getxVel());
            b2.setxVel(-b2.getxVel());
            
            if (getyVel() ==0) setyVel(getyVel() + b2.getyVel()/2);
            if (b2.getyVel() ==0) b2.setyVel(b2.getyVel() + getyVel()/2);
            setyVel(- getyVel());
            b2.setyVel(- b2.getyVel()); 
        }
    }
	
	public Rectangle getArea() {
    	return sprite.getBoundingRectangle();
    }
	public void setPosition() {
		this.sprite.setPosition(x, y);
	}
    
    public void setX(int x) {
    	this.x = x;
    }
    
    public void setY(int y) {
    	this.y = y;
    }
    
    public void setxVel(int xVel) {
		this.xVel = xVel;
	}
    
    public void setyVel(int yVel) {
		this.yVel = yVel;
	}
    
    public void setSprite(Sprite sprite) {
    	this.sprite = sprite;
    }
    
    public int getX() {
    	return this.x;
    }
    
    public int getY() {
    	return this.y;
    }
    
    public int getxVel() {
		return this.xVel;
	}
    
	public int getyVel() {
		return this.yVel;
	}
	
    public Sprite getSprite() {
    	return this.sprite;
    }
}*/
