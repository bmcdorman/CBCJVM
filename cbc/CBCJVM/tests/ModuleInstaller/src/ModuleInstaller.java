import java.io.*;
import java.util.zip.*;

class ModuleInstaller
{
	public ModuleInstaller() throws IOException {
		new File("/mnt/user/tmpusb").mkdir();
		Runtime.getRuntime().exec("mount -t vfat /dev/sdb1 /mnt/user/tmpusb");
	}
    public void extract(File module)
    {
        try
        {
        	new File("/mnt/user/tmpmodule").mkdir();
            String destinationName = "/mnt/user/tmpmodule/";
            byte[] buf = new byte[1024];
            ZipInputStream zipInputStream = null;
            ZipEntry zipEntry;
            zipInputStream = new ZipInputStream(new FileInputStream(module));

            zipEntry = zipInputStream.getNextEntry();
            while (zipEntry != null) 
            { 
                //for each entry to be extracted
                String entryName = zipEntry.getName();
                System.out.println("entryname "+entryName);
                int n;
                FileOutputStream fileOutputStream;
                File newFile = new File(entryName);
                String directory = newFile.getParent();
                
                if(directory == null)
                    if(newFile.isDirectory())
                        break;
                
                fileOutputStream = new FileOutputStream(
                   destinationName + entryName);             

                while ((n = zipInputStream.read(buf, 0, 1024)) > -1)
                    fileOutputStream.write(buf, 0, n);

                fileOutputStream.close(); 
                zipInputStream.closeEntry();
                zipEntry = zipInputStream.getNextEntry();

            }//while

            zipInputStream.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public File getModuleAvailable() {
    	File usb = new File("/mnt/user/tmpusb/modules");
    	if(!usb.exists()) {
    		System.out.println("Doesn't exist, yo!");
    		return null;
    	}
    	for(File i : usb.listFiles()) {
    		System.out.println(i.getName());
    		if(i.getName().endsWith(".module")) return i;
    	}
		return null;
    }
    public void dispose() throws IOException {
    	Runtime.getRuntime().exec("umount /dev/sdb1");
    	new File("/mnt/user/tmpusb").delete();
    }
}