import java.util.*;
/**
 * The program in a way implements an excel spreadsheet. There are two classes; 
 * SheetRow which makes a single row of the spreadsheet and Sheet which creates
 * a 9X9 spreadsheet with the help of SheetRow class. The program also adds the
 * elements in each row and column and displays the total of it. 
 * @author Ruchik Chaudhari
 * 6/10/2020
 */

public class Main{
	/**
	 * Tests the Sheet and SheetRow classes.
	 * @param args
	 */
	public static void main(String [] args) {
		Sheet sht = new Sheet();
		System.out.println("Initial State");
		System.out.println(sht+"\n");

		sht.insertRow(0);
		System.out.println("Insert Row 0");
		System.out.println(sht+"\n");

		sht.deleteRow(8);
		System.out.println("Delete Row 8");
		System.out.println(sht+"\n");

		sht.insertCol(0);
		System.out.println("Insert Col 0");
		System.out.println(sht+"\n");

		sht.deleteCol(6);
		System.out.println("Delete Col 6");
		System.out.println(sht+"\n");

		for (int row = 1; row <= 3; row ++) {
			for (int col = 1; col <= 3; col++) {
				sht.setString(row,  col, ""+((row-1)*3+col));
			}
		}
		System.out.println("Fill 3x3 array");
		System.out.println(sht+"\n");
		sht.insertRow(2);
		sht.insertRow(4);
		sht.insertCol(2);
		sht.insertCol(4);
		
		System.out.println("Insert Row 2,4, Col 2,4");
		System.out.println(sht+"\n");

		for (int i = 10; i >= 6; i--) {
			sht.deleteRow(i);
			sht.deleteCol(i);
		}
		System.out.println("Delete Row 10-6, Col 10-6");
		System.out.println(sht+"\n");

		for (int i = 0; i <= 5; i++) {
			sht.deleteRow(0);
			sht.deleteCol(0);
		}
		System.out.println("Delete Row 0x6, Col 0x6");
		System.out.println(sht+"\n");
		System.out.println();
	}
}



/**
 * Sheet with the help of SheetRow class creates a spread sheet which contains 
 * rows and columns. It initially creates a 9 X 9 spread sheet and has the ability
 * to add or remove any row and column and set value to any cell in the spread 
 * sheet.
 * @author Ruchik Chaudhari
 * 6/10/2020
 */
class Sheet {
	//Private field
		private LinkedList<SheetRow> rows;

		/**
		 * Constructors a 9 X 9 sheet of cells i.e containing 9 rows and 9 columns.
		 */
		public Sheet() {
			//creates a 9x9 sheet of cells “R<1..9>C<1..9>”
			this.rows = new LinkedList<SheetRow>();

			for(int row = 1; row < 10; row++) {
				this.rows.add(new SheetRow(row));
			}
		} 

		/**
		 * Inserts row at 0-based index
		 * @param row
		 */
		public void insertRow(int row){
			this.rows.add(row, new SheetRow(row));
		} 

		/**
		 * Deletes row at 0-based index
		 * @param row
		 */
		public void deleteRow(int row){
			this.rows.remove(row);
		} 

		/**
		 * Inserts col at 0-based index “R…C<col>” in each row
		 * @param col
		 */
		public void insertCol(int col){
			
			Iterator<SheetRow> itr = this.rows.iterator();
			int count = 0;
			while(itr.hasNext()) {
				itr.next().addCell(col, "R"+count+"C"+col);
				count++;
			}
		}

		/**
		 * Deletes col at 0-based index in each row
		 * @param col
		 */
		public void deleteCol(int col){
			for(SheetRow row : this.rows) {
				row.removeCell(col);
			}

		} 

		/**
		 * Sets String value
		 * @param row
		 * @param col
		 * @param s
		 */
		public void setString(int row, int col, String s){
			this.rows.get(row).setString(col, s);
		}

		/**
		 * Gets int value of cell at row/col
		 * @param row
		 * @param col
		 * @return
		 */
		public int getValue(int row, int col){
			return this.rows.get(row).getValue(col);
		}

		/**
		 * Print the spread sheet with sum of rows and columns in the end.
		 */
		public String toString(){
			
			System.out.println();
			String result = "";
			int colNum = 0;
			//Print each row on a new line
			for (SheetRow row : this.rows) {
				result += row + "\n";
				//get the # of columns
				colNum = row.size();
			}
			
			//Iterate through the columns
			for (int col= 0; col < colNum; col++) {
				int sum = 0;
				//Iterate through the rows
				for(SheetRow r : this.rows) {
					sum += r.getValue(col);
				}
				String s = "Sum="+sum;
				result += String.format("%-8s", s);
			}
			return result;
		}
}


/**
 * SheetRow creates a row for the spread sheet. It has one private field which 
 * is LinkedList  which represent the row of the whole spreadsheet and can
 * perform functions on each cell as well.
 * @author Ruchik Chaudhari
 * 6/10/2020
 */

class SheetRow {

	//Field
	private LinkedList<String> cells;

	/**
	 * Creates 9 column of cells “R<row>C<1..9>
	 * @param row
	 */
	public SheetRow(int row) {// 
		LinkedList<String> list = new LinkedList<String>();
		for(int index = 1; index < 10; index++) {
			list.add("R"+row+"C"+index);
		}
		this.cells = list;
	}

	/**
	 * Adds 0-based column to this row
	 * @param index
	 * @param s
	 */
	public void addCell(int index, String s){
		this.cells.add(index, s);
	}

	/**
	 * Removes 0-based column from this row
	 * @param index
	 */
	public void removeCell(int index){
		this.cells.remove(index);
	} 

	/**
	 * Sets value at column
	 * @param cell
	 * @param s
	 */
	public void setString(int cell, String s){
		this.cells.set(cell, s);
	}

	/**
	 * Returns # of columns
	 * @return
	 */
	public int size(){
		return this.cells.size();
	}

	/**
	 * Return int value of column
	 * @param col
	 * @return
	 */
	public int getValue(int col){

		String cellValue = this.cells.get(col);
		char token = cellValue.charAt(0);

		//If token is a digit get the integer value
		if (Character.isDigit(token)) 
			return Integer.parseInt(cellValue);

		return 0;

	}
	/**
	 * Prints each row of the spreadsheet with a sum at the end of it
	 */
	public String toString(){

		String result = "";
		int sum = 0;

		for(String cell : this.cells) {

			char token = cell.charAt(0);
			if (Character.isDigit(token)) 
				sum += Integer.parseInt(cell);

			result += String.format("%-8s", cell);	
		}
		return result + "Sum = "+sum;
	}
}
