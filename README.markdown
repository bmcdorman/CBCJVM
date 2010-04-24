Current Stable Release: 10.3; Current Development Version: 10.4
-----------

First off, remember that by following these instructions you may brick your CBC and void your warranty.  You have been warned.
CBCv2 ONLY

Ubuntu Guide (should be similar for other *nix operating systems):

To build (assuming you already have a JDK installed):
        sudo apt-get install git-core ant maven2
        git clone git://github.com/catron/CBCJVM.git
        cd CBCJVM
        sh BuildEverything.sh

Congrats! You should now have everything you need in `CBCJVM/installer/install`!!1!
See corresponding Readme.txt file.

To update, just run:
        cd CBCJVM
        git pull
        sh BuildEverything.sh
        ... and reinstall on cbc

Documentation (generated upon build) is located at `CBCJVM/cbc/CBCJVM/docs/api` see the [wiki](http://wiki.github.com/catron/CBCJVM/) for more information

When compiling or running custom code, be sure to set `CBCJVM/cbc/CBCJVM/bin` as your classpath!

You can use maven to build the tests and to build your own programs!
Just CD into the test directory, and provided it has a good pom.xml file (still cleaning them up and adding them to old tests) and run `mvn clean install`

### This file is part of CBCJVM. ###
CBCJVM is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

CBCJVM is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with CBCJVM.  If not, see <http://www.gnu.org/licenses/>.
