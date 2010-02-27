/*
Copyright (c) 2001, 2010, Robert Penner (original version) and Benjamin Woodruff
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
    * Redistributions of source code must retain the above copyright
      notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright
      notice, this list of conditions and the following disclaimer in the
      documentation and/or other materials provided with the distribution.
    * Neither the name of the <organization> nor the
      names of its contributors may be used to endorse or promote products
      derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL BENJAMIN WOODRUFF OR ROBERT PENNER BE LIABLE FOR
ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
(INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package cbccore.easing;

import cbccore.InvalidValueException;

/**
 * t is time, b is beginning position, c is the total change in position,
 * and d is the duration of the tween.
 * 
 * @author Benjamin Woodruff
 * @author Robert Penner
 */
public abstract class EasingEquation {
	
	public static final int EASE_IN = 1;
	public static final int EASE_OUT = 2;
	public static final int EASE_IN_OUT = 3;
	
	
	
	
	
	
	
	
	
	public double ease(int type, double t, double b, double c, double d) throws InvalidValueException {
		switch(type) {
			case EASE_IN:
				return easeIn(t, b, c, d);
			case EASE_OUT:
				return easeOut(t, b, c, d);
			case EASE_IN_OUT:
				return easeInOut(t, b, c, d);
		}
		throw new InvalidValueException();
	}
	
	public double easeIn(double t, double b, double c, double d) {
		return easeIn(t, c, d) + b;
	}
	
	public double easeOut(double t, double b, double c, double d) {
		return easeOut(t, c, d) + b;
	}
	
	public double easeInOut(double t, double b, double c, double d) {
		return easeInOut(t, c, d) + b;
	}
	
	
	
	
	
	
	
	public double ease(int type, double t, double c, double d) throws InvalidValueException {
		switch(type) {
			case EASE_IN:
				return easeIn(t, c, d);
			case EASE_OUT:
				return easeOut(t, c, d);
			case EASE_IN_OUT:
				return easeInOut(t, c, d);
		}
		throw new InvalidValueException();
	}
	
	public double easeIn(double t, double c, double d) {
		double tOverD = d==0.?0.:t/d;
		return easeIn(tOverD)*tOverD*c;
	}
	
	public double easeOut(double t, double c, double d) {
		double tOverD = d==0.?0.:t/d;
		return easeOut(tOverD)*tOverD*c;
	}
	
	public double easeInOut(double t, double c, double d) {
		double tOverD = d==0.?0.:t/d;
		return easeInOut(tOverD)*tOverD*c;
	}
	
	
	
	
	
	
	public double ease(int type, double percent) throws InvalidValueException {
		switch(type) {
			case EASE_IN:
				return easeIn(percent);
			case EASE_OUT:
				return easeOut(percent);
			case EASE_IN_OUT:
				return easeInOut(percent);
		}
		throw new InvalidValueException();
	}
	
	public abstract double easeIn(double percent);
	
	public double easeOut(double percent) {
		return 1.-easeIn(1.-percent);
	}
	
	//should work in most cases
	public double easeInOut(double percent) {
		if (percent < 0.5) return .5*easeIn(percent*2.);
		return .5+.5*easeOut((percent-.5)*2.);
	}
	
	
	
	
	
	
	
	
	
	
	//****************************
	//******* area under the graph
	//****************************
	
	public double easeArea(int type, double t, double b, double c, double d) throws InvalidValueException {
		switch(type) {
			case EASE_IN:
				return easeInArea(t, b, c, d);
			case EASE_OUT:
				return easeOutArea(t, b, c, d);
			case EASE_IN_OUT:
				return easeInOutArea(t, b, c, d);
		}
		throw new InvalidValueException();
	}
	
	public double easeInArea(double t, double b, double c, double d) {
		return easeInArea(t, c, d) + b * d;
	}
	
	public double easeOutArea(double t, double b, double c, double d) {
		return easeOutArea(t, c, d) + b * d;
	}
	
	public double easeInOutArea(double t, double b, double c, double d) {
		return easeInOutArea(t, c, d) + b * d;
	}
	
	
	
	public double easeArea(int type, double t, double c, double d) throws InvalidValueException {
		switch(type) {
			case EASE_IN:
				return easeInArea(t, c, d);
			case EASE_OUT:
				return easeOutArea(t, c, d);
			case EASE_IN_OUT:
				return easeInOutArea(t, c, d);
		}
		throw new InvalidValueException();
	}
	
	public double easeInArea(double t, double c, double d) {
		return easeInArea(t/d)*t*c;
	}
	
	public double easeOutArea(double t, double c, double d) {
		return easeOutArea(t/d)*t*c;
	}
	
	public double easeInOutArea(double t, double c, double d) {
		return easeInOutArea(t/d)*t*c;
	}
	
	
	
	
	
	
	
	
	public double easeArea(int type, double percent) throws InvalidValueException {
		switch(type) {
			case EASE_IN:
				return easeInArea(percent);
			case EASE_OUT:
				return easeOutArea(percent);
			case EASE_IN_OUT:
				return easeInOutArea(percent);
		}
		throw new InvalidValueException();
	}
	
	//find the indefinate integral
	public abstract double easeInArea(double percent);
	public abstract double easeOutArea(double percent);
	
	//should work in most cases
	public double easeInOutArea(double percent) {
		if (percent < 0.5) return .5*easeInArea(percent*2.);
		return .5*easeInArea(1.)+.5*easeOutArea((percent-.5)*2.);
	}
}
