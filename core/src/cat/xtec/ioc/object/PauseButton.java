package cat.xtec.ioc.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import cat.xtec.ioc.helper.AssetManager;

public class PauseButton extends Actor {

    public static final String NAME = "pause-button";

    public static final int BUTTON_WIDTH = 30;
    public static final int BUTTON_HEIGHT = 30;

    private Vector2 position;
    private int width, height;

    public PauseButton(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public float getY() {
        return position.y;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.pauseButton, position.x, position.y, width, height);
    }

}
