package be.jeremygillard.pentago.gui.components;

import be.jeremygillard.pentago.model.Pentago;
import be.jeremygillard.pentago.model.State;
import be.jeremygillard.pentago.util.Observer;
import javafx.animation.RotateTransition;
import javafx.geometry.Pos;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This class represent a quadrant of the pentago game.
 *
 * @author Jeremy Gillard
 */
public class QuadrantFrame extends StackPane implements Observer {

    private final Pentago pentago;

    private final int quadrantNumber;

    private Rectangle representation;

    private RotateTransition transition;

    GridPane piecePlacement;

    /**
     * The quadrant will be instantiated with a certain number, a pentago game
     * to link with and a lightingeffect for a more friendly display.
     *
     * @param quadrantNumber the number attribuated.
     * @param pentago the pentago game.
     * @param lightingEffet the visual effect to apply.
     */
    public QuadrantFrame(int quadrantNumber, Pentago pentago, Lighting lightingEffet) {
        this.quadrantNumber = quadrantNumber;
        this.pentago = pentago;
        this.pentago.addObserver(this);
        this.piecePlacement = new GridPane();
        visualInitialization(lightingEffet);
        arrangement(quadrantNumber, lightingEffet);
        behavior();
    }

    private void visualInitialization(Lighting lightingEffet) {
        representation = new Rectangle(250, 250);
        representation.setFill(new LinearGradient(0f, 0f, 0f, 1f, true,
                CycleMethod.NO_CYCLE,
                new Stop[]{new Stop(0, Color.rgb(89, 34, 2)),
                    new Stop(1, Color.rgb(65, 28, 1))}));
        representation.setArcWidth(100);
        representation.setArcHeight(100);
        representation.setEffect(lightingEffet);
    }

    private void arrangement(int quadrantNumber, Lighting lightingEffet) {
        piecePlacement.setAlignment(Pos.CENTER);
        piecePlacement.setHgap(14);
        piecePlacement.setVgap(14);
        piecePlacement.setGridLinesVisible(true);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                switch (quadrantNumber) {
                    case 0:
                        piecePlacement.add(new PieceComponent(i, j, this.pentago, lightingEffet), i, j);
                        break;
                    case 1:
                        piecePlacement.add(new PieceComponent(i+3, j, this.pentago, lightingEffet), i, j);
                        break;
                    case 2:
                        piecePlacement.add(new PieceComponent(i, j+3, this.pentago, lightingEffet), i, j);
                        break;
                    default:
                        piecePlacement.add(new PieceComponent(i+3, j+3, this.pentago, lightingEffet), i, j);
                        break;
                }
            }
        }
        this.getChildren().addAll(representation, piecePlacement);
    }

    private void behavior() {
        transition = new RotateTransition(Duration.millis(3000), this);
        transition.setDuration(Duration.millis(800));
    }

    @Override
    public void update() {
        if (pentago.getCurrentGameState() == State.ROTATION) {
            if (pentago.getLastQuadrantRotated() == quadrantNumber) {
                if (pentago.isLastRotationClockwise()) {
                    transition.setByAngle(90);
                    piecePlacement.setRotate(piecePlacement.getRotate() - 90);
                } else {
                    transition.setByAngle(-90);
                    piecePlacement.setRotate(piecePlacement.getRotate() + 90);
                }
                transition.play();
            }
        }
    }

}
