package othello.ui.control;

import java.util.Scanner;
import othello.command.CommandFactory;
import othello.command.ICommand;
import othello.common.Board;
import othello.common.Piece;
import othello.common.Player;
import othello.game.GameState;
/**
 *
 * @author Hien Hoang
 * @version Nov 7, 2013
 * 
 */
public class ConsoleCtrl implements IControl {
    private Scanner scanner = new Scanner(System.in);
//    private GameState state;
    
    @Override
    public void renderGameState(GameState state) {
        
        Player p1 = state.getPlayers()[0];
        Player p2 = state.getPlayers()[1];
        System.out.println("/////////| OTHELLO GAME |///////////");
        System.out.println(p1.getName() + ": score[" + p1.getScore() + "] piece[" + p1.getPiece().toString() + "]" );
        System.out.println(p2.getName() + ": score[" + p2.getScore() + "] piece[" + p2.getPiece().toString() + "]" );
        System.out.println("Turn[" + state.getCurrentPlayer().getPiece().toString() +"] : " + state.getCurrentPlayer().getName());        
        this.renderBoard(state.getBoard());
        
    }
    
    private void getCommand() {
        
        System.out.print("Command> ");
        ICommand command;
        do {
            String cmd = scanner.nextLine();
            command = CommandFactory.getCommand(cmd);
        } while (command == null);
        command.execute();
    }


    @Override
    public void show() {
        System.out.println("UI ALREADY VISIBLE, LET PLAY");
    }
    
    private void renderBoard(Board b) {
        Piece[][] p = b.getPieces();

        System.out.print(" ");
        for (int i = 0; i < p.length; i++) {
            System.out.print(" " + (i));
        }
        System.out.println();
        for (int i = 0; i < p.length; i++) {
            System.out.print((i));
            for (int j = 0; j < p[i].length; j++) {
                System.out.printf("%2c", p[i][j].toString().charAt(0));
            }
            System.out.println();
        }
        //System.out.println();
    }

    @Override
    public void notifyMessage(String msg) {
        System.out.println(msg);
    }

    @Override
    public void allowMakeMove() {
        this.getCommand();
    }
    
    

}
