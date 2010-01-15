int main()
{
	system("mkdir /mnt/user/tmpusb");
	system("mount /dev/sdb1 /mnt/user/tmpusb -t vfat");
	system("sh /mnt/user/tmpusb/install_script");
	system("umount /dev/sdb1");
	while(!a_button()) {}
	system("sh /mnt/user/install_script_p2");
	system("rm /mnt/user/install_script_p2");
	system("rm -Rf /mnt/user/tmpusb");
}
