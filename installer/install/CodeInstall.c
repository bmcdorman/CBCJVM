int main() {
	//designed & based on after the CBCJava installer
	//chdir("/mnt/user/code/CodeInstall");
	system("mkdir -p /mnt/user/tmpusb");
	system("mount /dev/sdb1 /mnt/user/tmpusb -t vfat");
	
	printf("\n\n[INSTALL] ----------------------------- [INSTALL]\n");
	printf("[INSTALL] Destroying previous installation...\n");
	//system("rm -R /mnt/user/code");
	system("mkdir -p /mnt/user/code");
	printf("[INSTALL] Copying code...\n");
	system("cp -R /mnt/user/tmpusb/install_data/code/ /mnt/user");
	
	system("umount /dev/sdb1");
	printf("[INSTALL] Remove Drive. UR DONE!\n\n");
}
