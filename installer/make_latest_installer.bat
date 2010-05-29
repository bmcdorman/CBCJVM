echo "Working..."
del  install/install_data # I don't think this should follow symbolic links, dangerous
mkdir install/install_data
mkdir install/install_data/lib
cp lib/* install/install_data/lib
mkdir install/install_data/jvm
cp jvm/* install/install_data/jvm
mkdir install/install_data/jvm/cbc
cp ../cbc/CBCJVM/bin/* install/install_data/jvm/cbc
cp ../cbc/CBCJVM/jni install/install_data/jvm/cbc
cp cjm install/install_data/jvm
mkdir install/install_data/code
cp ../cbc/CBCJVM/tests/* install/install_data/code
echo "Done!"
