package org.sudowars.Model.Sudoku.Field;

/**
 * The class {@link DataCell} represents the smallest structure inside a {@link Sudoku}.
 */
public final class DataCell implements Cell {
	
	/**
	 * Serial version UID for serialization.
	 */
	private static final long serialVersionUID = 7952639650027214265L;

	/**
	 * Define, if this cell is a given number in the sudoku game.
	 * Is <code>0</code>, if the cell is empty and <code>1</code>, if the number is given.
	 */
	public static final int NOT_SET = 0;
	
	private int value;
	private int index;
	private boolean initial;
	
	/**
	 * Default constructor to initialize a new instance of type {@link DataCell} with a not-set value.
	 *
	 * @param index the {@link DataCell}s future index.
	 * @throws IllegalArgumentException when index is less than 0
	 */
	public DataCell(int index, boolean initial) {
		if (index < 0) {
			throw new IllegalArgumentException();
		}
		
		this.value = NOT_SET;
		this.index = index;
		this.initial = initial;
	}

	/**
	 * Returns the {@link DataCell}s value
	 *
	 * @return an not-negative integer
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Returns the {@link DataCell}s index.
	 *
	 * @return the {@link DataCell}s index.
	 * @see FieldStructure#getIndex(int, int)
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Returns whether this is an inital {@link DataCell} inside
	 *
	 * @return whether this is an inital {@link DataCell} inside
	 */
	public boolean isInitial() {
		return initial;
	}
	
	/**
	 * Sets the {@link DataCell}s value.
	 *
	 * @param value the {@link DataCell}s new value.
	 *
	 * @throws IllegalArgumentException if the value is lower than <code>0</code>. 
	 */
	public void setValue(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("Illegal cell value");
		}
		this.value = value;
	}
	
	/**
	 * Sets the {@link DataCell}s initial-state.
	 * 
	 * @param state the {@link DataCell}s new state.
	 */
	public void setInitial(boolean state) {
		this.initial = state;
	}

	/**
	 * Determines whether the {@link Cell} contains a value or not.
	 * @return <code>true</code> id cell contains a value, otherwise <code>false</code>
	 */
	@Override
	public boolean isSet() {
		return this.value != NOT_SET;
	}
	
	/**
	 * Returns a copy of the calling {@link DataCell}
	 * @return a deep copy of the calling {@link DataCell}
	 */
	public DataCell clone() {
		DataCell newCell = new DataCell(this.index, this.initial);
		if (this.value != DataCell.NOT_SET) {
			newCell.setValue(this.value);
		}
		return newCell;
	}

	
}
