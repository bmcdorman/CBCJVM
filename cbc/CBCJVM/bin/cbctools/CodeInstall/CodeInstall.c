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
