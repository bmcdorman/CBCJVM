package cbc.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import cbc.helpers.Helper;

public class CBCUSBAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window = null;
	final String m_CBCPlugin = "CBCJava USB Downlading Plug-in";
	Helper helper = null;
	public void run(IAction action) {
		helper.error("CBCJava does not yet support USB downloading!");
	}
	public void selectionChanged(IAction action, ISelection selection) {
	}
	public void dispose() {
	}
	public void init(IWorkbenchWindow window) {
		this.window = window;
		helper = new Helper(this.window, m_CBCPlugin);
	}
}