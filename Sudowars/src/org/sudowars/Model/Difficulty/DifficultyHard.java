/*******************************************************************************
 * Copyright (c) 2011 - 2012 Adrian Vielsack, Christof Urbaczek, Florian Rosenthal, Michael Hoff, Moritz Lüdecke, Philip Flohr.
 * 
 * This file is part of Sudowars.
 * 
 * Sudowars is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Sudowars is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Sudowars.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Contributors:
 * initial API and implementation:
 * Adrian Vielsack
 * Christof Urbaczek
 * Florian Rosenthal
 * Michael Hoff
 * Moritz Lüdecke
 * Philip Flohr 
 ******************************************************************************/
package org.sudowars.Model.Difficulty;

/**
 * This class defines the value range for the {@link Difficulty} <b>hard</b>
 */
public class DifficultyHard extends Difficulty {

	private static final long serialVersionUID = 3379321694336314889L;
	
	/**
	 * Initialises a new instance of the class and defines the bounds
	 * of the {@link Difficulty}
	 */
	public DifficultyHard() {
		this.lowerBound = 8.5;
		this.upperBound = Double.MAX_VALUE;
		//TODO define upper bound : 11.6, 25.1, 17.1, 11.0 are some calculated values 
		//this.upperBound = 11.5; //possible max value which is not too hard, not verified
	}

	/**
	 * Returns a string representation of the difficulty
	 */
	public String toString() {
		return "Hard";
	}
	
}


