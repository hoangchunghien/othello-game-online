package othello.command;

import othello.common.AbstractPlayer;

/**
 *
 * @author Hien Hoang
 * @version Dec 4, 2013
 * Description
 * . TODO
 */
public interface IUndoCmdExec {
    
    public void makeUndo(AbstractPlayer caller);
}
