package cat.xtec.ioc.object;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateByAction;

import java.util.Random;

import cat.xtec.ioc.helper.AssetManager;
import cat.xtec.ioc.object.baseclass.Scrollable;
import cat.xtec.ioc.tool.Settings;

public class YellowPill extends Scrollable {

    private Circle collisionCircle;

    public YellowPill(float x, float y, float width, float height, float speed) {
        super(x, y, width, height, speed);

        collisionCircle = new Circle();

        setOrigin();

        RotateByAction rotateAction = new RotateByAction();
        rotateAction.setAmount(-90f);
        rotateAction.setDuration(0.2f);

        RepeatAction repeat = new RepeatAction();
        repeat.setAction(rotateAction);
        repeat.setCount(RepeatAction.FOREVER);

        this.addAction(repeat);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        collisionCircle.set(position.x + width / 2.0f, position.y + width / 2.0f, width / 2.0f);
    }

    @Override
    public void reset(float newX) {
        super.reset(newX);
        position.y = new Random().nextInt(Settings.GAME_HEIGHT - (int) height);
        setOrigin();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(AssetManager.yellowCoin,
                position.x, position.y,
                this.getOriginX(),
                this.getOriginY(),
                width,
                height,
                this.getScaleX(),
                this.getScaleY(),
                this.getRotation());
    }

    /**
     * Checks if there has been a collision.
     *
     * @param spermatozoon
     * @return true if there has been a collision and false if not.
     */
    public boolean collides(Spermatozoon spermatozoon) {
        if (position.x <= spermatozoon.getX() + spermatozoon.getWidth()) {
            // Checks if the objects have collided as long as the yellow coin shares screen pixels with the spermatozoon
            return (Intersector.overlaps(collisionCircle, spermatozoon.getCollisionRect()));
        }
        return false;
    }

    public void setOrigin() {
        this.setOrigin(width / 2 + 1, height / 2);
    }
}
