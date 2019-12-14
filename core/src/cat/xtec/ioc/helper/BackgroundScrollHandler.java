package cat.xtec.ioc.helper;

import com.badlogic.gdx.scenes.scene2d.Group;

import cat.xtec.ioc.object.Background;
import cat.xtec.ioc.tool.Settings;

public class BackgroundScrollHandler extends Group {

    private Background bg, bg_back;

    public BackgroundScrollHandler() {
        bg = new Background(0, 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);
        bg_back = new Background(bg.getTailX(), 0, Settings.GAME_WIDTH * 2, Settings.GAME_HEIGHT, Settings.BG_SPEED);

        addActor(bg);
        addActor(bg_back);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        if (bg.isLeftOfScreen()) {
            bg.reset(bg_back.getTailX());

        } else if (bg_back.isLeftOfScreen()) {
            bg_back.reset(bg.getTailX());
        }
    }
}