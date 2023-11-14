package  student;

import java.util.Random;

public class Queen {
	private int row;
	private int column;

	public Queen(int row, int column) {
		super();
		this.row = row;
		this.column = column;
	}

	public void move() {
		row++;
		if (row == Node.N) {
			row =0;
		}
	}

	// check whether this Queen can attack the given Queen (q)
	public boolean isConflict(Queen q) {
		if(row == q.getRow() || column == q.getColumn()) return true;
		int rowAll = Math.abs(row - q.getRow());
		int colAll = Math.abs(column  - q.getColumn());
		if (rowAll == colAll) return true;
		return false;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public void setRow(int row) {
		this.row = row;
	}

	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
}
