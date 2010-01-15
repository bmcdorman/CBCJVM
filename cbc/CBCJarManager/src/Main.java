public class Main {
	public static void main(String[] args) throws Exception {
		System.setProperty("user.dir", "/");
		System.out.println("FTP started on port 21.");
		Server.main(new String[0]);
		System.out.println("Restarting in 5 seconds...");
		Thread.sleep(5000);
		Runtime.getRuntime().exec("shutdown -r");
	}
}
