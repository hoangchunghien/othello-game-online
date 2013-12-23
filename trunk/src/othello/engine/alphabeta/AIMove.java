package othello.engine.alphabeta;

import othello.common.Position;

public class AIMove {
	public int row;
	public int col;
	public int rank;

	public AIMove(int row, int col)
	{
		this.row  = row;
		this.col  = col;
		this.rank = 0;
		
	}
	
	public Position toPosition() {
		return new Position(col, row);
	}
}
