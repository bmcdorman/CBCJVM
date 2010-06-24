/*
 * This file is part of CBCJVM.
 * CBCJVM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * CBCJVM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with CBCJVM.  If not, see <http://www.gnu.org/licenses/>.
*/

package cbccore.movement.plugins.create;

import cbccore.create.Create;
import cbccore.movement.plugins.MovementPlugin;
import cbccore.InvalidValueException;
import java.io.IOException;

/**
 * A DriveTrain class for the iRobot Create
 * 
 * @author Benjamin Woodruff
 */

public class CreateMovementPlugin extends MovementPlugin {
	
	private static final double DEFAULT_TRAIN_WIDTH = 25.5;
	//private static final double wheelCircumference = 10.;
	private double efficiency;
	//private double leftCmps = 0.;
	//private double rightCmps = 0.;
	private Create create = null;
	
	
	/**
	 * Basic constructor
	 */
	public CreateMovementPlugin(Create create, double efficiency, boolean fullMode) throws IOException {
		super(DEFAULT_TRAIN_WIDTH);
		this.create = create;
		create.connect();
		this.efficiency = efficiency;
		// FIXME: This should be moved to it's own method
		if(fullMode) {
			create.setMode(Create.Mode.Full); 
		} else {
			create.setMode(Create.Mode.Safe); 
		}
	}
	
	/** {@inheritDoc} */
	public void directDrive(double leftCmps, double rightCmps) {
		create.driveDirect((int)(rightCmps*10./efficiency), (int)(leftCmps*10./efficiency));
	}
	
	/**
	 * The create has no freeze api. So this essentially calls <code>kill()</code>
	 * 
	 * @see      #kill
	 */
	public void freeze() {
		kill(); //no such api
	}
	
	/** {@inheritDoc} */
	public double getLeftMaxCmps() {
		return 50.*efficiency;
	}
	
	/** {@inheritDoc} */
	public double getRightMaxCmps() {
		return 50.*efficiency;
	}
}
