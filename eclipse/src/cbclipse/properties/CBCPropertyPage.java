package cbclipse.properties;

import java.util.ArrayList;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.QualifiedName;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.PropertyPage;

import cbcdownloader.DownloadConfiguration;
import cbcdownloader.Downloader;
import cbcdownloader.DummyDownloader;
import cbcdownloader.NetworkDownloader;
import cbcdownloader.USBDownloader;
import cbclipse.Connection;
import cbclipse.ConnectionManager;

public class CBCPropertyPage extends PropertyPage {

	private static final int TEXT_FIELD_WIDTH = 50;

	private Text textField = null;
	private Label property = null;
	private Combo downloader = null;
	private Connection current = null;

	

	/**
	 * Constructor for SamplePropertyPage.
	 */
	public CBCPropertyPage() {
		super();
	}

	private void addFirstSection(Composite parent) {
		Composite composite = createDefaultComposite(parent);

		// Label for path field
		Label pathLabel = new Label(composite, SWT.NONE);
		pathLabel.setText("Downloader: ");

		downloader = new Combo(composite, SWT.READ_ONLY);

		downloader.setItems(new String[] { 
				ConnectionInfo.downloaders[0].toString(),
				ConnectionInfo.downloaders[1].toString(), 
				ConnectionInfo.downloaders[2].toString() 
				});
		
		try {
			current = ConnectionInfo.getConnection((IResource)getElement());
		} catch (CoreException e2) {
			e2.printStackTrace();
		}
		
		int i = 0;
		for(String item : downloader.getItems()) {
			if(item.equals(current.getDownloader().toString())) {
				downloader.select(i);
			}
			++i;
		}
		
		downloader.setSize(300, 50);

		downloader.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Select called");
				if (current == null) {
					try {
						current = ConnectionInfo.getConnection((IResource)getElement());
					} catch (CoreException e1) {
						e1.printStackTrace();
					}
				}
				Downloader down = null;
				for (Downloader d : ConnectionInfo.downloaders) {
					if (downloader.getText().equals(d.toString())) {
						down = d;
						break;
					}
				}
				if(down == null) return;
				current.setDownloader(down, down.getConfigurationObject());
				try {
					ConnectionInfo.save((IResource)getElement(), current);
				} catch (CoreException e1) {
					e1.printStackTrace();
				}
				
				property.setText(current.getConfig().getRequirements().toArray()[0].toString());
				property.update();
				property.redraw();
			}
		});
	}

	private void addSeparator(Composite parent) {
		Label separator = new Label(parent, SWT.SEPARATOR | SWT.HORIZONTAL);
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		separator.setLayoutData(gridData);
	}

	private void addSecondSection(Composite parent) {
		Composite composite = createDefaultComposite(parent);

		property = new Label(composite, SWT.NONE);
		property.setText("");

		try {
			current = ConnectionInfo.getConnection((IResource)getElement());

			String text = current.getConfig().getRequirements().toArray()[0].toString();
			for (int i = text.length(); i < 10; ++i) {
				text += " ";
			}
			property.setText(text);

			textField = new Text(composite, SWT.SINGLE | SWT.BORDER);
			String value = current.getConfig().getValueFor(text.trim());
			if (value == null)
				value = "";
			textField.setText(value);
		} catch (CoreException e1) {
			downloader.select(2);
		}

		GridData gd = new GridData();
		gd.widthHint = convertWidthInCharsToPixels(TEXT_FIELD_WIDTH);
		textField.setLayoutData(gd);
	}

	

	/**
	 * @see PreferencePage#createContents(Composite)
	 */
	protected Control createContents(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout();
		composite.setLayout(layout);
		GridData data = new GridData(GridData.FILL);
		data.grabExcessHorizontalSpace = true;
		composite.setLayoutData(data);

		addFirstSection(composite);
		addSeparator(composite);
		addSecondSection(composite);
		return composite;
	}

	private Composite createDefaultComposite(Composite parent) {
		Composite composite = new Composite(parent, SWT.NULL);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		composite.setLayout(layout);

		GridData data = new GridData();
		data.verticalAlignment = GridData.FILL;
		data.horizontalAlignment = GridData.FILL;
		composite.setLayoutData(data);

		return composite;
	}

	protected void performDefaults() {
		downloader.select(2);
	}

	public boolean performOk() {
		if (current == null)
			return false;
		try {
			current.getConfig().setValueFor(property.getText().trim(), textField.getText().trim());
			ConnectionInfo.save((IResource)getElement(), current);
		} catch (CoreException e) {
			return false;
		}
		return true;
	}

}