package pkgGame;

import java.security.SecureRandom;
import java.util.Random;

import pkgHelper.LatinSquare;

/**
 * Sudoku - This class extends LatinSquare, adding methods, constructor to
 * handle Sudoku logic
 * 
 * @version 1.2
 * @since Lab #2
 * @author Bert.Gibbons
 *
 */
public class Sudoku extends LatinSquare {
	private HashMap<Integer,Sudoku.Cells> cells = new HashMap<Integer>(Integer,Sudoku.Cell);
	
	private int iSize;


	private int iSqrtSize;

	
	public Sudoku(int iSize) throws Exception {

		this.iSize = iSize;

		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}

		int[][] puzzle = new int[iSize][iSize];
		super.setLatinSquare(puzzle);
		FillDiagonalRegions();
	}


	public Sudoku(int[][] puzzle) throws Exception {
		super(puzzle);
		this.iSize = puzzle.length;
		double SQRT = Math.sqrt(iSize);
		if ((SQRT == Math.floor(SQRT)) && !Double.isInfinite(SQRT)) {
			this.iSqrtSize = (int) SQRT;
		} else {
			throw new Exception("Invalid size");
		}

	}

	public int[][] getPuzzle() {
		return super.getLatinSquare();
	}


	public int getRegionNbr(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return i;
	}


	public int[] getRegion(int iCol, int iRow) {

		int i = (iCol / iSqrtSize) + ((iRow / iSqrtSize) * iSqrtSize);

		return getRegion(i);
	}

	

	public int[] getRegion(int r) {

		int[] reg = new int[super.getLatinSquare().length];

		int i = (r % iSqrtSize) * iSqrtSize;
		int j = (r / iSqrtSize) * iSqrtSize;
		int iMax = i + iSqrtSize;
		int jMax = j + iSqrtSize;
		int iCnt = 0;

		for (; j < jMax; j++) {
			for (i = (r % iSqrtSize) * iSqrtSize; i < iMax; i++) {
				reg[iCnt++] = super.getLatinSquare()[j][i];
			}
		}

		return reg;
	}

	
	public boolean isPartialSudoku() {

		if (!super.isLatinSquare()) {
			return false;
		}

		for (int k = 0; k < this.getPuzzle().length; k++) {

			if (super.hasDuplicates(getRegion(k))) {
				return false;
			}

			if (!hasAllValues(getRow(0), getRegion(k))) {
				return false;
			}
		}

		if (ContainsZero()) {
			return false;
		}

		return true;

	}

	
	public boolean isSudoku() {

		if (!isPartialSudoku()) {
			return false;
		}

		if (ContainsZero()) {
			return false;
		}

		return true;
	}

	
	public boolean isValidValue(int iRow,int iCol,  int iValue) {
		
		public boolean isValidValue(int iCol, int iRow, int iValue) {
			return iValue>= 1 && iValue<=iSize &&
					isValidColumnValue(iCol,iValue)&& isValidRowValue(iRow,iValue) &&
					isValidRegionValue(iRow,iCol,iValue);
		}
	}


	public void PrintPuzzle() {
		for (int i = 0; i < this.getPuzzle().length; i++) {
			System.out.println("");
			for (int j = 0; j < this.getPuzzle().length; j++) {
				System.out.print(this.getPuzzle()[i][j]);
				if ((j + 1) % iSqrtSize == 0)
					System.out.print(" ");
			}
			if ((i + 1) % iSqrtSize == 0)
				System.out.println(" ");

		}
		System.out.println("");
	}

	
	private void FillDiagonalRegions() {

		for (int i = 0; i < iSize; i = i + iSqrtSize) {
			System.out.println("Filling region: " + getRegionNbr(i, i));
			SetRegion(getRegionNbr(i, i));
			ShuffleRegion(getRegionNbr(i, i));
		}
	}


	private void SetRegion(int r) {
		int iValue = 0;

		iValue = 1;
		for (int i = (r / iSqrtSize) * iSqrtSize; i < ((r / iSqrtSize) * iSqrtSize) + iSqrtSize; i++) {
			for (int j = (r % iSqrtSize) * iSqrtSize; j < ((r % iSqrtSize) * iSqrtSize) + iSqrtSize; j++) {
				this.getPuzzle()[i][j] = iValue++;
			}
		}
	}

	
	private void ShuffleRegion(int r) {
		int[] region = getRegion(r);
		shuffleArray(region);
		int iCnt = 0;
		for (int i = (r / iSqrtSize) * iSqrtSize; i < ((r / iSqrtSize) * iSqrtSize) + iSqrtSize; i++) {
			for (int j = (r % iSqrtSize) * iSqrtSize; j < ((r % iSqrtSize) * iSqrtSize) + iSqrtSize; j++) {
				this.getPuzzle()[i][j] = region[iCnt++];
			}
		}
	}

	private void shuffleArray(int[] ar) {

		Random rand = new SecureRandom();
		for (int i = ar.length - 1; i > 0; i--) {
			int index = rand.nextInt(i + 1);
			// Simple swap
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}
	boolean isValidColumnValue(int iCol, int iValue) {
		if (doesElementExist(super.getColumn(iCol),iValue))
		{
			return false;
		}
		return true;
	}
	boolean isValidRowValue(int iRow,int iValue) {
		if (doesElementExist(super.getRow(iRow),iValue))
		{
			return false;
		}
		return true;
	}
	boolean isValidRegionValue(int iRow,int iCol,int iValue) {
		if (doesElementExist(this.getRegion(iCol, iRow),iValue))
		{
			return false;
		}
		
		return true;
	}
	
	
	
	
	
	
	
	
	private boolean fillRemaining(Sudoku.Cell c) {
		return true;
	}
	}
	private void SetCells() {
		
	}
	private void ShowAvailableValues() {
	}
	private HashSet<Integer> getAllValidCellValues(int iCol,int iRow){
	}

	
	
	
	
	
	
	
	private class Sudoku.Cell extends java.lang.Object{
		private int iCol;
		private int iRow;
		private ArrayList<Integer> lstValidValues;
		public Cell(int iRow, int iCol) {
			this.iCol = iCol;
			this.iRow = iRow;
		}
		public int getiCol() {
			return iCol;
		}
		public int getiRow() {
			return iRow;
		}
		@Override
		public boolean equals(Object e) {
			if(e instanceof Cell) {
				return this.iCol == (Cell)e.getiCol() && this.iRow == (Cell)e.getiRow();
			}
			return false;
		}
		public ArrayList<Integer> getLstValidValues(){
			return lstValidValues;
		}
		public Sudoku.Cell getNextCell(Sudoku.Cell c) {
			if(c.getiRow() == iSize-1 && c.getiCol()==iSize-1) {
				return null;
			}
			
		}
		@Overrides
		public int hashCode() {
			
		}
		public void setlstValidValues(HashSet<Integer> hsValidValues) {
			
		}
		public void ShuffleValidValues() {
			
		}

			
	}
}
