package g49803.atl.pentago.controller;

import g49803.atl.pentago.model.Pentago;
import g49803.atl.pentago.model.Player;
import g49803.atl.pentago.view.View;

/**
 *
 * @author g49803
 */
public class Controller {

    private final Pentago pentago;

    private final View view;

    public Controller(Pentago pentago, View view) {
        this.pentago = pentago;
        this.view = view;
    }

    public void startGame() {

        while (pentago.isThereEnoughtPlayer()) {
            try {
                pentago.addPlayer(view.askPlayerName());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        do {
            pentago.start();
            while (!pentago.isEnded()) {

                for (Player player : pentago.getPlayers()) {
                    String[] marblePositionCmd = view.giveCoordinates();
                    pentago.placeMarble(marblePositionCmd[0], marblePositionCmd[1], player);
                    view.displayBoard();

                    String[] rotationQuadrantDirectionCmd = view.giveRotationDirection();
                    pentago.turnQuadrant(rotationQuadrantDirectionCmd[0], rotationQuadrantDirectionCmd[1]);
                    view.displayBoard();
                }

            }
        } while (view.takeRevenge);

    }

}
