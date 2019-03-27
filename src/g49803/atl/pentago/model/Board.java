package g49803.atl.pentago.model;

/**
 *
 * @author g49803
 */
public class Board {
    
    private final Marble[][] board;
    
    public Board() {
        board = new Marble[6][6];
    }
    
    public boolean isEmptyCell(int col, int row) {
        return board[col][row] == null;
    }
    
    public void fillCell(int col, int row, Marble marbleColor){
        board[col][row] = marbleColor;
    }
    
    public void turnQuadrant(int quadrantPosition, boolean direction) {
        
    }
    
    public Marble[][] getArrangement() {
        return board;
    }
    
    @Override
    public String toString() {
        String description = "";
        for (Marble[] marbles : board) {
            for (Marble marble : marbles) {
                if(marble == null) {
                    description += " ";
                } else {
                    description += "O";
                }
            }
            description += "\n";
        }
        return description;
    }
    
}
