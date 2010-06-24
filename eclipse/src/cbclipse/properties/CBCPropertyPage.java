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
import cbclipse.ConnectionManager;

public class CBCPropertyPage extends PropertyPage {

	private static final int TEXT_FIELD_WIDTH = 50;

	private Text textField = null;
	private Label property = null;
	private Combo downloader = null;
	private Downloader[] downloaders = new Downloader[] {
			new NetworkDownloader(), new USBDownloader(), new DummyDownloader() };
	private Downloader current = null;

	public static QualifiedName qualifiedDownloadName = new QualifiedName("",
			"DOWNLOADER");
	public static QualifiedName qualifiedPropertyName = new QualifiedName("",
			"PROPERTY");
	public static QualifiedName qualifiedPropertyValue = new QualifiedName("",
			"PROPERTY_VALUE");

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

		downloader.setItems(new String[] { downloaders[0].toString(),
				downloaders[1].toString(), downloaders[2].toString() });
		downloader.select(0);
		downloader.setSize(300, 50);

		downloader.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				System.out.println("Select called");
				if (property == null)
					return;
				for (Downloader d : downloaders) {
					if (downloader.getText().equals(d.toString())) {
						current = d;
						break;
					}
				}
				property.setText(current.getConfigurationObject()
						.getRequirements().toArray()[0].toString());
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
			String selection = ((IResource) getElement())
					.getPersistentProperty(qualifiedDownloadName);
			int i = 0;

			if (selection == null)
				selection = downloaders[2].toString();
			for (Downloader d : downloaders) {
				if (selection.equals(d.toString())) {
					current = d;
					downloader.select(i);
					break;
				}
				++i;
			}

			String text = new ArrayList<String>(current
					.getConfigurationObject().getRequirements()).get(0);
			for (i = text.length(); i < 10; ++i) {
				text += " ";
			}
			property.setText(text);

			textField = new Text(composite, SWT.SINGLE | SWT.BORDER);
			String value = ((IResource) getElement())
					.getPersistentProperty(qualifiedPropertyValue);
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

	private DownloadConfiguration getConfig() {
		if (current == null)
			return null;
		DownloadConfiguration config = current.getConfigurationObject();
		config.setValueFor(property.getText().trim(), textField.getText());
		return config;
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
		// store the value in the owner text field
		if (current == null)
			return false;
		try {
			((IResource) getElement()).setPersistentProperty(
					qualifiedDownloadName, current.toString());
			((IResource) getElement()).setPersistentProperty(
					qualifiedPropertyName, property.getText());
			((IResource) getElement()).setPersistentProperty(
					qualifiedPropertyValue, textField.getText());
			ConnectionManager.setDownloader(current, getConfig());
		} catch (CoreException e) {
			return false;
		}
		return true;
	}

}