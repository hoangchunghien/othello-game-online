package othello.command;

import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 * @version Dec 8, 2013
 * Description
 * . This command executor interface is in client only
 * . The object that implements this interface will have
 * . the get move (Position) permission
 */
public interface IGetMoveCmdExec {
    
    public void getMoveFor(AbstractPlayer player);
}
