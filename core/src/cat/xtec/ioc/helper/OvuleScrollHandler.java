package cat.xtec.ioc.helper;

import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;
import java.util.Random;

import cat.xtec.ioc.helper.spritemap.OvuleMap;
import cat.xtec.ioc.object.Ovule;
import cat.xtec.ioc.object.Spermatozoon;
import cat.xtec.ioc.tool.Methods;
import cat.xtec.ioc.tool.Settings;

public class OvuleScrollHandler extends Group {

    private int nOvules;
    private ArrayList<Ovule> ovules;

    private Random r;

    public OvuleScrollHandler() {
        nOvules = 3;
        ovules = new ArrayList<>();

        r = new Random();

        float ovuleSize = Methods.randomFloat(Settings.MIN_OVULE, Settings.MAX_OVULE)
                * OvuleMap.WIDTH;

        Ovule ovule = new Ovule(
                Settings.GAME_WIDTH,
                r.nextInt(Settings.GAME_HEIGHT - (int) ovuleSize),
                ovuleSize,
                ovuleSize,
                Settings.OVULE_SPEED
        );

        ovules.add(ovule);

        addActor(ovule);

        for (int i = 1; i < nOvules; i++) {
            ovuleSize = Methods.randomFloat(Settings.MIN_OVULE, Settings.MAX_OVULE)
                    * OvuleMap.WIDTH;
            ovule = new Ovule(
                    ovules.get(ovules.size() - 1).getTailX() + Settings.OVULE_GAP,
                    r.nextInt(Settings.GAME_HEIGHT - (int) ovuleSize),
                    ovuleSize,
                    ovuleSize,
                    Settings.OVULE_SPEED
            );

            ovules.add(ovule);

            addActor(ovule);
        }

    }

    @Override
    public void act(float delta) {
        super.act(delta);

        for (int i = 0; i < ovules.size(); i++) {
            Ovule ovule = ovules.get(i);

            if (ovule.isLeftOfScreen()) {
                if (i == 0) {
                    ovule.reset(
                            ovules.get(ovules.size() - 1).getTailX() + Settings.OVULE_GAP
                    );
                } else {
                    ovule.reset(
                            ovules.get(i - 1).getTailX() + Settings.OVULE_GAP
                    );
                }
            }
        }

    }

    /**
     * Checks if the spermatozoon has collided with any ovule.
     *
     * @param spermatozoon The spermatozoon object.
     * @return true if there has been a collision, false otherwise.
     */
    public boolean collides(Spermatozoon spermatozoon) {
        for (Ovule ovule : ovules) {
            if (ovule.collides(spermatozoon)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Resets all the ovule positions, to the beginning of the screen, with new Y random positions,
     * and the proper gaps between them.
     */
    public void reset() {
        ovules.get(0).reset(Settings.GAME_WIDTH); // Top right
        /* Calculate the new ovule positions */
        for (int i = 1; i < ovules.size(); i++) {
            ovules.get(i).reset(ovules.get(i - 1).getTailX() + Settings.OVULE_GAP);
        }
    }

    public ArrayList<Ovule> getOvules() {
        return ovules;
    }
}