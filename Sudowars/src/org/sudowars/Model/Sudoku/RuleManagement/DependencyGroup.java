package org.sudowars.Model.Sudoku.RuleManagement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.sudowars.Model.Sudoku.Field.Cell;
import org.sudowars.Model.Sudoku.Field.Field;

/**
 * The class {@link DependencyGroup} allows grouping {@link Field} slots in relation to a 
 * given {@link Rule}.
 * @see FieldStructure
 * @see FieldStructure#getIndex(int, int)
 */
public class DependencyGroup implements Serializable {
	
	/**
	 * The serial version UID for serialization
	 */
	private static final long serialVersionUID = -5880127787614777904L;
	
	private List<Integer> indices;
	private Rule rule;

	/**
	 * Initializes a new {@link DependencyGroup} instance with the given parameters.
	 *
	 * @param rule the {@link Rule} which defines the relation
	 * @param indices the grouped slots.
	 *
	 * @throws IllegalArgumentException if whether rule or indices are <code>null</code>
	 */
	public DependencyGroup(Rule rule, List<Integer> indices) throws IllegalArgumentException{
		
		if (rule == null || indices == null) {
			// throws all defined exceptions
			throw new IllegalArgumentException();
		}
		
		// a new list to disallow modifying the indices from outside via modifying the given indices object
		this.indices = new ArrayList<Integer>(indices);
		
		// rules are not meant to hold a status so they can be used without cloning
		this.rule = rule;		
	}
	
	/**
	 * Returns the inherited {@link Rule}
	 *
	 * @return the inherited {@link Rule}
	 */
	public Rule getRule() {
		return rule;
	}
	
	/**
	 * Returns a realization of grouped {@link Cell}s relative to the given {@link Field} 
	 *
	 * @param field the {@link Field} containing the {@link Cell}s.
	 *
	 * @return a {@link List} of {@link Cell}s.
	 *
	 * @throws IllegalArgumentException if field is <code>null</code>
	 * @throws IllegalArgumentException if at least one index contained in this {@link DependencyGroup} is not covered by the {@link Field} given. 
	 */
	public List<Cell> getCells(Field<Cell> field) throws IllegalArgumentException {
		
		if (field == null) {
			// thrown when field is null
			throw new IllegalArgumentException();
		}
		
		// ArrayList implementation of List, as the size is known and no modifications are expected 
		List<Cell> cells = new ArrayList<Cell>(indices.size());
		
		for (int index : indices) {
			try {
				cells.add(field.getCell(index));
			} catch(IllegalArgumentException e) {
				// thrown when the given index is not contained by field
				// re-throw as the same exception is specified in this case
				throw e;
			}
		}
		
		// no read-only view is needed, as this list is generated dynamically
		return cells;
	}
	
	/**
	 * Returns the inherited {@link List} of indices.
	 *
	 * @return the inherited {@link List} of indices.
	 */
	List<Integer> getIndices() {
		// forbids external indices modifications after object creation
		return Collections.unmodifiableList(indices);
	}
	
}


