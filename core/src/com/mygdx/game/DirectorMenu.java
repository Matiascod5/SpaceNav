import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class DirectorMenu {
    public void construirMenuPausa(MenuBuilder builder) {
        builder.construirOpciones(new String[]{"Continuar", "Reiniciar", "Salir"});
        builder.construirFondoSprite(new Texture(Gdx.files.internal("CuadroMenu.png")));
        builder.construirMenu();
    }

    public void construirMenuNaves(MenuBuilder builder) {
        builder.construirOpciones(new String[]{"Continuar", "Reiniciar", "Salir"});
        builder.construirFondoSprite(null);
        builder.construirMenu();
    }

    public void construirMenuSonido(MenuBuilder builder) {
        builder.construirOpciones(new String[]{"Continuar", "Reiniciar", "Salir"});
        builder.construirFondoSprite(null);
        builder.construirMenu();
    }
}
