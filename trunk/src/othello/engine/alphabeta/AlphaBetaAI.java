package othello.engine.alphabeta;

import java.util.Random;

import othello.configuration.LevelCfg;

public class AlphaBetaAI {

	private int currentColor = GameModel.WHITE;
	private LevelCfg level;

	// AI parameters.
	private int lookAheadDepth;
	private int forfeitWeight;
	private int frontierWeight;
	private int mobilityWeight;
	private int stabilityWeight;

	// Defines the maximum move rank value (used for ranking an end game).
	private static int maxRank = Integer.MAX_VALUE - 64;

	public AlphaBetaAI(LevelCfg level) {
		this.level = level;
	}
	
	public void setColor(int color) {
		currentColor = color;
	}
	
	// ===================================================================
	// Game AI code.
	// Note: These are executed in the worker thread.
	// ===================================================================

	//
	// This function starts the look ahead process to find the best move
	// for the current player color.
	//
	public AIMove GetBestMove(GameModel board) {
		// Initialize the alpha-beta cutoff values.
		int alpha = AlphaBetaAI.maxRank + 64;
		int beta = -alpha;
		
		// Load the AI parameters.
		this.SetAIParameters(level);

		// Kick off the look ahead.
		return this.GetBestMove(board, this.currentColor, 1, alpha, beta);
	}

	//
	// This function uses look ahead to evaluate all valid moves for a
	// given player color and returns the best move it can find.
	//
	private AIMove GetBestMove(GameModel board, int color, int depth,
			int alpha, int beta) {
		
		// Initialize the best move.
		AIMove bestMove = new AIMove(-1, -1);
		bestMove.rank = -color * AlphaBetaAI.maxRank;

		// Find out how many valid moves we have so we can initialize the
		// mobility score.
		int validMoves = board.GetValidMoveCount(color);

		// Start at a random position on the board. This way, if two or
		// more moves are equally good, we'll take one of them at random.
		Random random = new Random();
		int rowStart = random.nextInt(8);
		int colStart = random.nextInt(8);

		// Check all valid moves.
		int i, j;
		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++) {
				// Get the row and column.
				int row = (rowStart + i) % 8;
				int col = (colStart + j) % 8;

				if (board.IsValidMove(color, row, col)) {
					// Update the progress bar for each move when on the
					// first look ahead depth level.
					// TODO: code here

					// Make the move.
					AIMove testMove = new AIMove(row, col);
					GameModel testBoard = new GameModel(board);
					testBoard.MakeMove(color, testMove.row, testMove.col);
					int score = testBoard.getWhiteCount()
							- testBoard.getBlackCount();

					// Check the board.
					int nextColor = -color;
					int forfeit = 0;
					boolean isEndGame = false;
					int opponentValidMoves = testBoard
							.GetValidMoveCount(nextColor);
					if (opponentValidMoves == 0) {
						// The opponent cannot move, count the forfeit.
						forfeit = color;

						// Switch back to the original color.
						nextColor = -nextColor;

						// If that player cannot make a move either, the
						// game is over.
						if (!testBoard.HasAnyValidMove(nextColor))
							isEndGame = true;
					}

					// If we reached the end of the look ahead (end game or
					// max depth), evaluate the board and set the move
					// rank.
					if (isEndGame || depth == this.lookAheadDepth) {
						// For an end game, max the ranking and add on the
						// final score.
						if (isEndGame) {
							// Negative value for black win.
							if (score < 0)
								testMove.rank = -AlphaBetaAI.maxRank + score;

							// Positive value for white win.
							else if (score > 0)
								testMove.rank = AlphaBetaAI.maxRank + score;

							// Zero for a draw.
							else
								testMove.rank = 0;
						}

						// It's not an end game so calculate the move rank.
						else
							testMove.rank = this.forfeitWeight
									* forfeit
									+ this.frontierWeight
									* (testBoard.getBlackFrontierCount() - testBoard
											.getWhiteFrontierCount())
									+ this.mobilityWeight
									* color
									* (validMoves - opponentValidMoves)
									+ this.stabilityWeight
									* (testBoard.getWhiteSafeCount() - testBoard
											.getBlackSafeCount()) + score;
					}

					// Otherwise, perform a look ahead.
					else {
						AIMove nextMove = this.GetBestMove(testBoard,
								nextColor, depth + 1, alpha, beta);

						// Pull up the rank.
						testMove.rank = nextMove.rank;

						// Forfeits are cumulative, so if the move did not
						// result in an end game, add any current forfeit
						// value to the rank.
						if (forfeit != 0
								&& Math.abs(testMove.rank) < AlphaBetaAI.maxRank)
							testMove.rank += forfeitWeight * forfeit;

						// Adjust the alpha and beta values, if necessary.
						if (color == GameModel.WHITE && testMove.rank > beta)
							beta = testMove.rank;
						if (color == GameModel.BLACK && testMove.rank < alpha)
							alpha = testMove.rank;
					}

					// Perform a cutoff if the rank is outside tha alpha-beta
					// range.
					if (color == GameModel.WHITE && testMove.rank > alpha) {
						testMove.rank = alpha;
						return testMove;
					}
					if (color == GameModel.BLACK && testMove.rank < beta) {
						testMove.rank = beta;
						return testMove;
					}

					// If this is the first move tested, assume it is the
					// best for now.
					if (bestMove.row < 0)
						bestMove = testMove;

					// Otherwise, compare the test move to the current
					// best move and take the one that is better for this
					// color.
					else if (color * testMove.rank > color * bestMove.rank)
						bestMove = testMove;
				}
			}

		// Return the best move found.
		return bestMove;
	}

	//
	// Sets the AI parameters based on the current difficulty setting.
	//
	private void SetAIParameters(LevelCfg level) {
		// Set the AI parameter weights.
		switch (level.name) {
		case LevelCfg.EASY: // Easy
			this.forfeitWeight = 2;
			this.frontierWeight = 1;
			this.mobilityWeight = 0;
			this.stabilityWeight = 3;
			break;
		case LevelCfg.NORMAL: // Normal
			this.forfeitWeight = 3;
			this.frontierWeight = 1;
			this.mobilityWeight = 0;
			this.stabilityWeight = 5;
			break;
		case LevelCfg.HARD: // Hard
			this.forfeitWeight = 7;
			this.frontierWeight = 2;
			this.mobilityWeight = 1;
			this.stabilityWeight = 10;
			break;
		case LevelCfg.EXPERT: // Expert
			this.forfeitWeight = 35;
			this.frontierWeight = 10;
			this.mobilityWeight = 5;
			this.stabilityWeight = 50;
			break;
		default:
			this.forfeitWeight = 0;
			this.frontierWeight = 0;
			this.mobilityWeight = 0;
			this.stabilityWeight = 0;
			break;
		}

		// Set the look-ahead depth.
		this.lookAheadDepth = level.depth;
	}
}
