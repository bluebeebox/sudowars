package org.sudowars.Model.Solver;

import java.util.LinkedList;

import org.sudowars.DebugHelper;
import org.sudowars.Model.Sudoku.Field.Cell;
import org.sudowars.Model.Sudoku.Field.DataCell;
import org.sudowars.Model.Sudoku.Field.DataCellBuilder;
import org.sudowars.Model.Sudoku.Field.Field;
import org.sudowars.Model.Sudoku.Field.FieldBuilder;

/**
 * This class defines the functionality to solve the next cell of a {@link Field}. It uses logical
 * combinations so it try to solve the next cell like a human would do.
 */
public class HumanSolver extends StrategyExecutor implements ConsecutiveSolver {
	
	private static final long serialVersionUID = -5626584974040288389L;

	/**
	 * Initialises the used strategies and adds them to the list by there priority.
	 */
	@Override
	protected void createStrategies() {
		
		this.solveStrategies = new LinkedList<SolverStrategy>();

		this.solveStrategies.add(new NakedSingleStrategy());
		this.solveStrategies.add(new HiddenSingleStrategy());
		this.solveStrategies.add(new LockedCandidateStrategy());
		this.solveStrategies.add(new NakedNCliqueStrategy());
		this.solveStrategies.add(new HiddenNCliqueStrategy());
		
	}
	
	/**
	 * Saves the solution in the cell
	 * @param currentState the current solution state
	 * @param solvedCell the solved cell
	 * @param solution the solution of the cell
	 * @return <code>true</code> if the cell was saved, <code>false</code> otherwise
	 */
	@Override
	protected boolean saveCell(SolverState currentState, int solvedCellIndex, int solution){
		//the field does not allow setValue, the function return true, so the executor
		//can go on and break after this hit
		return true;
	}
	
	/**
	 * Returns the {@link SolveStep} to solve the next {@link Cell}.
	 * @param currentState The current field and notes of the solver
	 * @return {@link SolveStep} to solve the next {@link Cell}
	 */
	public HumanSolveStep getCellToSolveNext(SolverState currentState) throws IllegalArgumentException {
		
		if (currentState == null) {
			throw new IllegalArgumentException("given SolverState cannot be null.");
		}
		
		//clear used strategies to save all strategies necessary to solve the next cells 
		//from the current state
		this.usedStrategies.clear();
		
		//solve the next cell
		StrategyExecutor.ExecuteResult result = this.executeStrategies(currentState, true);
		
		//checks if calling of the strategies found a cell
		HumanSolveStep humanSolveStep = null;
		SolveStep solveStep = currentState.getLastSolveStep();
		if (result == StrategyExecutor.ExecuteResult.UNIQUESOLUTION && solveStep.hasSolvedCell()) {
				
			//check if the solver return a cell without a specific solution. That happens
			//if the solver needs to use backtracking to solve the cell but can not write
			//on the current field to save the calculated values.
			if (solveStep.getSolution() == 0) {
				//use backtracker to identify the solution of the cell
				BacktrackingSolver solver = new BacktrackingSolver();
				//build field of data cells out of the given field
				FieldBuilder<DataCell> fb = new FieldBuilder<DataCell>();
				Field<DataCell> solverField = fb.build(currentState.getField().getStructure(), new DataCellBuilder());
				for (Cell cell : currentState.getField().getCells()) {
					solverField.getCell(cell.getIndex()).setInitial(cell.isInitial());
					solverField.getCell(cell.getIndex()).setValue(cell.getValue());
				}
				//iterate through the candidates and try to solve the field after setting the value of the cell
				for (Integer candidate : currentState.getNoteManager().getNotes(solveStep.getSolvedCell())) {
					//set candidate as cell value
					solverField.getCell(solveStep.getSolvedCell().getIndex()).setValue(candidate);
					//try to solve the field, as it is unique solvable the candidate is the
					//searched solution if the field can be solved
					if (solver.solve(solverField, currentState.getDependencyManager()) != null) {
						solveStep = new SolveStep(solveStep.getSolvedCell(), candidate, false);
						this.usedStrategies.clear();
						break;
					}
				}
			}
			
			//generate HumanSolveStep			
			humanSolveStep = new HumanSolveStep(
										solveStep.getSolvedCell(), 
										solveStep.getSolution(), 
										solveStep.hasChangedNotes(), 
										this.getUsedStrategies());
			
		} else {
			DebugHelper.log(DebugHelper.PackageName.Solver, "executeStrategies() results no unique solution or no solved cell: " + result);
		}
		
		return humanSolveStep;
		
	}
	
}


