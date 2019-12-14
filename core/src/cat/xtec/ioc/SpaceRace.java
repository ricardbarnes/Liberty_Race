package cat.xtec.ioc;

import com.badlogic.gdx.Game;

import cat.xtec.ioc.helper.AssetManager;
import cat.xtec.ioc.screen.SplashScreen;

public class SpaceRace extends Game {

    @Override
    public void create() {
        AssetManager.load(); // Load resources (assets)

        setScreen(new SplashScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        AssetManager.dispose();
    }
}