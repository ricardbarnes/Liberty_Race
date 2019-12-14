package cat.xtec.ioc.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import cat.xtec.ioc.helper.AssetManager;
import cat.xtec.ioc.tool.Settings;

public class Spermatozoon extends Actor {

    public static final String NAME = "spermatozoon";

    public static final int SPERMATOZOON_STRAIGHT = 0;
    public static final int SPERMATOZOON_UP = 1;
    public static final int SPERMATOZOON_DOWN = 2;

    private Vector2 position;
    private int width, height;
    private int direction;

    private Rectangle collisionRect;

    public Spermatozoon(float x, float y, int width, int height) {
        this.width = width;
        this.height = height;
        position = new Vector2(x, y);

        direction = SPERMATOZOON_STRAIGHT;

        collisionRect = new Rectangle();

        setBounds(position.x, position.y, width, height);
        setTouchable(Touchable.enabled);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        switch (direction) {
            case SPERMATOZOON_UP:
                if (this.position.y - Settings.SPERMATOZOON_SPEED * delta >= 0) {
                    this.position.y -= Settings.SPERMATOZOON_SPEED * delta;
                }
                break;
            case SPERMATOZOON_DOWN:
                if (this.position.y + height + Settings.SPERMATOZOON_SPEED * delta <= Settings.GAME_HEIGHT) {
                    this.position.y += Settings.SPERMATOZOON_SPEED * delta;
                }
                break;
            case SPERMATOZOON_STRAIGHT:
                break;
        }

        collisionRect.set(position.x, position.y + 3, width, 10);
        setBounds(position.x, position.y, width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(getSpermatozoonTexture(), position.x, position.y, width, height);
    }

    public void reset() {
        position.x = Settings.SPERMATOZOON_START_X;
        position.y = Settings.SPERMATOZOON_START_Y;
        direction = SPERMATOZOON_STRAIGHT;
        collisionRect = new Rectangle();
    }

    public void goUp() {
        direction = SPERMATOZOON_UP;
    }

    public void goDown() {
        direction = SPERMATOZOON_DOWN;
    }

    public void goStraight() {
        direction = SPERMATOZOON_STRAIGHT;
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

    public Rectangle getCollisionRect() {
        return collisionRect;
    }

    public TextureRegion getSpermatozoonTexture() {
        switch (direction) {
            case SPERMATOZOON_UP:
                return AssetManager.spermatozoonUp;
            case SPERMATOZOON_DOWN:
                return AssetManager.spermatozoonDown;
            default:
                return AssetManager.spermatozoonStraight;
        }
    }
}
