package cat.xtec.ioc.object.baseclass;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Scrollable base class for the scrollable actors.
 */
public class Scrollable extends Actor {

    protected Vector2 position;
    protected float velocity;
    protected float width;
    protected float height;
    protected boolean leftOfScreen;

    public Scrollable(float x, float y, float width, float height, float speed) {
        position = new Vector2(x, y);
        this.velocity = speed;
        this.width = width;
        this.height = height;
        leftOfScreen = false;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        position.x += velocity * delta;

        if (position.x + width < 0) {
            leftOfScreen = true;
        }
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

    /**
     * Resets the position to the scrollable object.
     *
     * @param newX X coordinate to place the object.
     */
    public void reset(float newX) {
        position.x = newX;
        leftOfScreen = false;
    }

    public boolean isLeftOfScreen() {
        return leftOfScreen;
    }

    public float getTailX() {
        return position.x + width;
    }
}
