/**
 * Copyright (c) 2012 Nokia Corporation. 
 */

package othello.engine.alphabeta;

import othello.common.Board;
import othello.game.GameState;

/**
 * The game model responsible for dealing the game logic.
 */
public class GameModel {	
	// These constants represent the contents of a GameModel square.
	public static final int BLACK = -1;
	public static final int EMPTY = 0;
	public static final int WHITE = 1;
	
	// Constants
	public static final int DEFAULT_GRID_SIZE_SQUARED = 8;
//	private static final int MAX_LISTENERS = 3;
	
	// members
	private int _gridSizeSquared = DEFAULT_GRID_SIZE_SQUARED;

	// Internal counts.
	private int blackCount;
	private int whiteCount;
	private int emptyCount;
	private int blackFrontierCount;
	private int whiteFrontierCount;
	private int blackSafeCount;
	private int whiteSafeCount;

	// This two-dimensional array represents the squares on the GameModel.
	private int[][] squares;

	// This two-dimensional array tracks which discs are safe (i.e.,
	// discs that cannot be outflanked in any direction).
	private boolean[][] safeDiscs;

	//
	public int getBlackCount() {
		return blackCount;
	}

	public void setBlackCount(int blackCount) {
		this.blackCount = blackCount;
	}

	public int getWhiteCount() {
		return whiteCount;
	}

	public void setWhiteCount(int whiteCount) {
		this.whiteCount = whiteCount;
	}

	public int getEmptyCount() {
		return emptyCount;
	}

	public void setEmptyCount(int emptyCount) {
		this.emptyCount = emptyCount;
	}

	public int getBlackFrontierCount() {
		return blackFrontierCount;
	}

	public void setBlackFrontierCount(int blackFrontierCount) {
		this.blackFrontierCount = blackFrontierCount;
	}

	public int getWhiteFrontierCount() {
		return whiteFrontierCount;
	}

	public void setWhiteFrontierCount(int whiteFrontierCount) {
		this.whiteFrontierCount = whiteFrontierCount;
	}

	public int getBlackSafeCount() {
		return blackSafeCount;
	}

	public void setBlackSafeCount(int blackSafeCount) {
		this.blackSafeCount = blackSafeCount;
	}

	public int getWhiteSafeCount() {
		return whiteSafeCount;
	}

	public void setWhiteSafeCount(int whiteSafeCount) {
		this.whiteSafeCount = whiteSafeCount;
	}
	
	public int[][] getSquares() {
		return squares;
	} 
	
	public int gridSizeSquared() {
		return _gridSizeSquared;
	}
	
	//
	// Creates a new, empty GameModel object.
	//
//	public GameModel() {
//		
//		// Create the squares and safe disc map.
//		this.squares = new int[8][8];
//		this.safeDiscs = new boolean[8][8];
//
//		// Clear the GameModel and map.
//		for (int i = 0; i < 8; i++)
//			for (int j = 0; j < 8; j++) {
//				this.squares[i][j] = GameModel.EMPTY;
//				this.safeDiscs[i][j] = false;
//			}
//
//		// Update the counts.
//		this.UpdateCounts();
		
//		// Create and initialize the game grid
//		_listeners = new IGameModelListener[MAX_LISTENERS];
//		
//		// Create and initialize the game grid
//		_listeners = new IGameModelListener[MAX_LISTENERS];
//		for (int i = 0; i < MAX_LISTENERS; ++i) {
//			_listeners[i] = null;
//		}
//
//		_listeners[0] = listener;
//	}

	//
	// Creates a new, empty Board object.
	//
	public GameModel()
	{
		//
		// TODO: Add constructor logic here
		//

		// Create the squares and safe disc map.
		this.squares = new int[8][8];
		this.safeDiscs = new boolean[8][8];

		// Clear the board and map.
		int i, j;
		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
			{
				this.squares[i][j] = GameModel.EMPTY;
				this.safeDiscs[i][j] = false;
			}

		// Update the counts.
		this.UpdateCounts();
	}

	//
	// Creates a new Board object by copying an existing one.
	//
	public GameModel(GameModel board)
	{
		// Create the squares and map.
		this.squares = new int[8][8];
		this.safeDiscs = new boolean[8][8];

		// Copy the given board.
		int i, j;
		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
			{
				this.squares[i][j] = board.squares[i][j];
				this.safeDiscs[i][j] = board.safeDiscs[i][j];
			}

		// Copy the counts.
		this.blackCount = board.blackCount;
		this.whiteCount = board.whiteCount;
		this.emptyCount = board.emptyCount;
		this.blackSafeCount = board.blackSafeCount;
		this.whiteSafeCount = board.whiteSafeCount;
	}
	
	public GameModel(Board bo) {
		
		this.squares = new int[bo.getHeight()][bo.getWidth()];
		this.safeDiscs = new boolean[bo.getHeight()][bo.getWidth()];
		
		for (int i = 0; i < bo.getHeight(); i++) {
			for (int j = 0; j < bo.getWidth(); j++) {
				switch (bo.getPiece(j, i)) {
				case BLACK:
					squares[i][j] = GameModel.BLACK;
					break;
				case WHITE:
					squares[i][j] = GameModel.WHITE;
					break;
				default:
					squares[i][j] = GameModel.EMPTY;
					break;
				}
				safeDiscs[i][j] = false;
			}
		}
		this.UpdateCounts();
	}
	
	/**
	 * Adds the given listener.
	 * @param listener The listener to add.
	 * @return True if successful, false otherwise.
//	 */
//	public boolean addListener(final IGameModelListener listener) {
//		for (int i = 0; i < MAX_LISTENERS; ++i) {
//			if (_listeners[i] == null) {
//				_listeners[i] = listener;
//				return true;
//			}
//		}
//		
//		return false;
//	}
	
	/**
	 * Removes the given listener.
	 * @param listener The listener to remove.
	 * @return True if successful, false otherwise.
	 */
//	public boolean removeListener(final IGameModelListener listener) {
//		for (int i = 0; i < MAX_LISTENERS; ++i) {
//			if (_listeners[i] == listener) {
//				_listeners[i] = null;
//				return true;
//			}
//		}
//		
//		return false;
//	}

	//
	// Sets a board with the initial game set-up.
	//
	public void SetForNewGame()
	{
		// Clear the board.
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
			{
				this.squares[i][j] = GameModel.EMPTY;
				this.safeDiscs[i][j] = false;
			}

		// Set two black and two white discs in the center.
		this.squares[3][3] = WHITE;
		this.squares[3][4] = BLACK;
		this.squares[4][3] = BLACK;
		this.squares[4][4] = WHITE;

		// Update the counts.
		this.UpdateCounts();
		
		// Notify the listeners
//		for (int i = 0; i < MAX_LISTENERS; ++i) {
//			if (_listeners[i] != null) {
//				_listeners[i].onGameModelChanged();
//			}
//		}
	}

	//
	// Returns the contents of a given board square.
	//
	public int GetSquareContents(int row, int col)
	{
		return this.squares[row][col];
	}

	//
	// Places a disc for the player on the board and flips any outflanked
	// opponents.
	// Note: For performance reasons, it does NOT check that the move is
	// valid.
	//
	public void MakeMove(int color, int row, int col)
	{
		// Set the disc on the square.
		this.squares[row][col] = color;

		// Flip any flanked opponents.
		int dr, dc;
		int r, c;
		for (dr = -1; dr <= 1; dr++)
			for (dc = -1; dc <= 1; dc++)
				// Are there any outflanked opponents?
				if (!(dr == 0 && dc == 0) && IsOutflanking(color, row, col, dr, dc))
				{
					r = row + dr;
					c = col + dc;
					// Flip 'em.
					while(this.squares[r][c] == -color)
					{
						this.squares[r][c] = color;
						r += dr;
						c += dc;
					}
				}

		// Update the counts.
		this.UpdateCounts();
		
		// Notify the listeners
//		if (_listeners != null)
//			for (int i = 0; i < MAX_LISTENERS; ++i) {
//				if (_listeners[i] != null) {
//					_listeners[i].onGameModelChanged();
//				}
//			}
		
		/*System.out.println("blackCount " + blackCount);
		System.out.println("whiteCount " + whiteCount);
		System.out.println("emptyCount " + emptyCount);
		System.out.println("blackFrontierCount " + blackFrontierCount);
		System.out.println("whiteFrontierCount " + whiteFrontierCount);
		System.out.println("blackSafeCount " + blackSafeCount);
		System.out.println("whiteSafeCount" + whiteSafeCount);
		System.out.println("///////////////////////////////////////////////////////////////////////////");*/
	}

	//
	// Determines if the player can make any valid move on the board.
	//
	public boolean HasAnyValidMove(int color)
	{
		// Check all board positions for a valid move.
		int r, c;
		for (r = 0; r < 8; r++)
			for (c = 0; c < 8; c++)
				if (this.IsValidMove(color, r, c))
					return true;

		// None found.
		return false;
	}

	//
	// Determines if a specific move is valid for the player.
	//
	public boolean IsValidMove(int color, int row, int col)
	{
		// The square must be empty.
		if (this.squares[row][col] != GameModel.EMPTY)
			return false;

		// Must be able to flip at least one opponent disc.
		int dr, dc;
		for (dr = -1; dr <= 1; dr++)
			for (dc = -1; dc <= 1; dc++)
				if (!(dr == 0 && dc == 0) && this.IsOutflanking(color, row, col, dr, dc))
					return true;

		// No opponents could be flipped.
		return false;
	}

	//
	// Returns the number of valid moves a player can make on the board.
	//
	public int GetValidMoveCount(int color)
	{
		int n = 0;

		// Check all board positions.
		int i, j;
		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
				// If the move is valid for the color, bump the count.
				if (this.IsValidMove(color, i, j))
					n++;
		return n;
	}

	//
	// Given a player move and a specific direction, determines if any
	// opponent discs will be outflanked.
	// Note: For performance reasons the direction values are NOT checked
	// for validity (dr and dc may be one of -1, 0 or 1 but both should
	// not be zero).
	//
	private boolean IsOutflanking(int color, int row, int col, int dr, int dc)
	{
		// Move in the given direction as long as we stay on the board and
		// land on a disc of the opposite color.
		int r = row + dr;
		int c = col + dc;
		while (r >= 0 && r < 8 && c >= 0 && c < 8 && this.squares[r][c] == -color)
		{
			r += dr;
			c += dc;
		}

		// If we ran off the board, only moved one space or didn't land on
		// a disc of the same color, return false.
		if (r < 0 || r > 7 || c < 0 || c > 7 || (r - dr == row && c - dc == col) || this.squares[r][c] != color)
			return false;

		// Otherwise, return true;
		return true;
	}

	//
	// Updates the board counts and safe disc map.
	// Note: MUST be called after any changes to the board contents.
	//
	private void UpdateCounts()
	{
		// Reset all counts.
		this.blackCount         = 0;
		this.whiteCount         = 0;
		this.emptyCount         = 0;
		this.blackFrontierCount = 0;
		this.whiteFrontierCount = 0;
		this.whiteSafeCount     = 0;
		this.blackSafeCount     = 0;

		int i, j;

		// Update the safe disc map.
		//
		// All currently unsafe discs are checked to see if they are still
		// outflankable. Those that are not are marked as safe.
		// If any new safe discs were found, the process is repeated
		// because this change may have made other discs safe as well. The
		// loop exits when no new safe discs are found.
		boolean statusChanged = true;
		while (statusChanged)
		{
			statusChanged = false;
			for (i = 0; i < 8; i++)
				for (j = 0; j < 8; j++)
					if (this.squares[i][j] != GameModel.EMPTY && !this.safeDiscs[i][j] && !this.IsOutflankable(i, j))
					{
						this.safeDiscs[i][j] = true;
						statusChanged = true;
					}
		}

		// Tally the counts.
		int dr, dc;
		for (i = 0; i < 8; i++)
			for (j = 0; j < 8; j++)
			{
				// If there is a disc at this position, determine if it is
				// on the frontier (i.e., adjacent to an empty square).
				boolean isFrontier = false;
				if (this.squares[i][j] != GameModel.EMPTY)
				{
					for (dr = -1; dr <= 1; dr++)
						for (dc = -1; dc <= 1; dc++)
							if (!(dr == 0 && dc == 0) && i + dr >= 0 && i + dr < 8 && j + dc >= 0 && j + dc < 8 && this.squares[i + dr][j + dc] == GameModel.EMPTY)
								isFrontier = true;
				}

				// Update the counts.
				if (this.squares[i][j] == GameModel.BLACK)
				{
					this.blackCount++;
					if (isFrontier)
						this.blackFrontierCount++;
					if (this.safeDiscs[i][j])
						this.blackSafeCount++;
				}
				else if (this.squares[i][j] == GameModel.WHITE)
				{
					this.whiteCount++;
					if (isFrontier)
						this.whiteFrontierCount++;
					if (this.safeDiscs[i][j])
						this.whiteSafeCount++;
				}
				else
					this.emptyCount++;
			}
	}

	//
	// Returns true if the disc at the given position can be outflanked in
	// any direction.
	// Note: For performance reasons we do NOT check that the square has a
	// disc.
	//
	private boolean IsOutflankable(int row, int col)
	{
		// Get the disc color.
		int color = this.squares[row][col];

		// Check each line through the disc.
		// NOTE: A disc is outflankable if there is an empty square on
		// both sides OR if there is an empty square on one side and an
		// opponent or unsafe (outflankable) disc of the same color on the
		// other side.
		int i, j;
		boolean hasSpaceSide1,  hasSpaceSide2;
		boolean hasUnsafeSide1, hasUnsafeSide2;

		// Check the horizontal line through the disc.
		hasSpaceSide1  = false;
		hasUnsafeSide1 = false;
		hasSpaceSide2  = false;
		hasUnsafeSide2 = false;
		// West side.
		for (j = 0; j < col && !hasSpaceSide1; j++)
			if (this.squares[row][j] == GameModel.EMPTY)
				hasSpaceSide1 = true;
			else if (this.squares[row][j] != color || !this.safeDiscs[row][j])
				hasUnsafeSide1 = true;
		// East side.
		for (j = col + 1; j < 8 && !hasSpaceSide2; j++)
			if (this.squares[row][j] == GameModel.EMPTY)
				hasSpaceSide2 = true;
			else if (this.squares[row][j] != color || !this.safeDiscs[row][j])
				hasUnsafeSide2 = true;
		if ((hasSpaceSide1  && hasSpaceSide2 ) ||
			(hasSpaceSide1  && hasUnsafeSide2) ||
			(hasUnsafeSide1 && hasSpaceSide2 ))
			return true;

		// Check the vertical line through the disc.
		hasSpaceSide1  = false;
		hasSpaceSide2  = false;
		hasUnsafeSide1 = false;
		hasUnsafeSide2 = false;
		// North side.
		for (i = 0; i < row && !hasSpaceSide1; i++)
			if (this.squares[i][col] == GameModel.EMPTY)
				hasSpaceSide1 = true;
			else if (this.squares[i][col] != color || !this.safeDiscs[i][col])
				hasUnsafeSide1 = true;
		// South side.
		for (i = row + 1; i < 8 && !hasSpaceSide2; i++)
			if (this.squares[i][col] == GameModel.EMPTY)
				hasSpaceSide2 = true;
			else if (this.squares[i][col] != color || !this.safeDiscs[i][col])
				hasUnsafeSide2 = true;
		if ((hasSpaceSide1  && hasSpaceSide2 ) ||
			(hasSpaceSide1  && hasUnsafeSide2) ||
			(hasUnsafeSide1 && hasSpaceSide2 ))
			return true;

		// Check the Northwest-Southeast diagonal line through the disc.
		hasSpaceSide1  = false;
		hasSpaceSide2  = false;
		hasUnsafeSide1 = false;
		hasUnsafeSide2 = false;
		// Northwest side.
		i = row - 1;
		j = col - 1;
		while (i >= 0 && j >= 0 && !hasSpaceSide1)
		{
			if (this.squares[i][j] == GameModel.EMPTY)
				hasSpaceSide1 = true;
			else if (this.squares[i][j] != color || !this.safeDiscs[i][j])
				hasUnsafeSide1 = true;
			i--;
			j--;
		}
		// Southeast side.
		i = row + 1;
		j = col + 1;
		while (i < 8 && j < 8 && !hasSpaceSide2)
		{
			if (this.squares[i][j] == GameModel.EMPTY)
				hasSpaceSide2 = true;
			else if (this.squares[i][j] != color || !this.safeDiscs[i][j])
				hasUnsafeSide2 = true;
			i++;
			j++;
		}
		if ((hasSpaceSide1  && hasSpaceSide2 ) ||
			(hasSpaceSide1  && hasUnsafeSide2) ||
			(hasUnsafeSide1 && hasSpaceSide2 ))
			return true;

		// Check the Northeast-Southwest diagonal line through the disc.
		hasSpaceSide1  = false;
		hasSpaceSide2  = false;
		hasUnsafeSide1 = false;
		hasUnsafeSide2 = false;
		// Northeast side.
		i = row - 1;
		j = col + 1;
		while (i >= 0 && j < 8 && !hasSpaceSide1)
		{
			if (this.squares[i][j] == GameModel.EMPTY)
				hasSpaceSide1 = true;
			else if (this.squares[i][j] != color || !this.safeDiscs[i][j])
				hasUnsafeSide1 = true;
			i--;
			j++;
		}
		// Southwest side.
		i = row + 1;
		j = col - 1;
		while (i < 8 && j >= 0 && !hasSpaceSide2)
		{
			if (this.squares[i][j] == GameModel.EMPTY)
				hasSpaceSide2 = true;
			else if (this.squares[i][j] != color || !this.safeDiscs[i][j])
				hasUnsafeSide2 = true;
			i++;
			j--;
		}
		if ((hasSpaceSide1  && hasSpaceSide2 ) ||
			(hasSpaceSide1  && hasUnsafeSide2) ||
			(hasUnsafeSide1 && hasSpaceSide2 ))
			return true;

		// All lines are safe so the disc cannot be outflanked.
		return false;
	}

	public int winner() {
		// TODO Auto-generated method stub
		return 0;
	}
}
