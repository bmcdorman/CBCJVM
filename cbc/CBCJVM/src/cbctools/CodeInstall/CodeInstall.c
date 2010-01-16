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

/**
 * 
 * @author Braden McDorman / Benjamin Woodruff
 *
 */

int main() {
	//designed & based on after the CBCJava installer
	//chdir("/mnt/user/code/CodeInstall");
	system("mkdir /mnt/user/tmpusb");
	system("mount /dev/sdb1 /mnt/user/tmpusb -t vfat");
	
	printf("[INSTALL] ----------------------------- [INSTALL]");
	printf("[INSTALL] Destroying previous installation... ");
	system("rm -R /mnt/user/code");
	//system("mkdir /mnt/user/code");
	system("cp -R /mnt/user/tmpusb/install_data/code/ /mnt/user");
	
	system("umount /dev/sdb1");
	printf("[INSTALL] Remove Drive. UR DONE!");
}
