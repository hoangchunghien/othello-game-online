package othello.engine;

import othello.common.Board;
import othello.common.Piece;
import othello.common.Position;
import othello.configuration.LevelCfg;
import othello.engine.alphabeta.AlphaBetaAI;
import othello.engine.alphabeta.GameModel;

public class AlphaBeta extends AbstractEngine {
	public static final String NAME = "alphabeta";

	private AlphaBetaAI AI;
	private LevelCfg level;

	public AlphaBeta(Board board, LevelCfg level) {
		super(board);
		this.level = level;
		AI = new AlphaBetaAI(level);
	}
	

	@Override
	public Position getMove(Piece p) {
		if (p == Piece.BLACK) {
			AI.setColor(GameModel.BLACK);
		}
		else {
			AI.setColor(GameModel.WHITE);
		}
		
		return AI.GetBestMove(new GameModel(board)).toPosition();
	}

}
