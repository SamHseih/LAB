package tcgenSWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;

public class DateSWT {

	protected Shell Shell;
	public Text Text;
	public DateTime Datetime;
	public Button Button;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DateSWT window = new DateSWT();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		Shell.open();
		Shell.layout();
		while (!Shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		Shell = new Shell();
		Shell.setTouchEnabled(true);
		Shell.setBounds(0, 0, 250, 200);
		Shell.setText("DateTime");
		
		Datetime = new DateTime(Shell, SWT.BORDER);
		Datetime.setTouchEnabled(true);
		Datetime.setBounds(70, 20, 90, 30);
		
		Text = new Text(Shell, SWT.BORDER);
		Text.setTouchEnabled(true);
		Text.setBounds(70, 120, 75, 25);
		
		Button = new Button(Shell, SWT.NONE);
		Button.setTouchEnabled(true);
		Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int y = Datetime.getYear();
				int m = Datetime.getMonth()+1;
				int d = Datetime.getDay();
				if(m == 12) {
					if(d == 31) {
						y = y + 1;
						m = 1;
						d = 1;
					}
					else {
						d = d + 1;
					}
				}
				else if(m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 ) {
					if(d == 31) {
						m = m + 1;
						d = 1;
					}
					else {
						d = d + 1;
					}
				}
				else if(m == 4 || m == 6 || m == 9 || m == 11) {
					if(d == 30) {
						m = m + 1;
						d = 1;
					}
					else {
						d = d + 1;
					}
				}
				else if((y % 400 == 0) || (y % 4 == 0 && y % 100 != 0)) {
					if(d == 29) {
						m = m + 1;
						d = 1;
					}
					else {
						d = d + 1;
					}
				}
				else if(d == 28) {
					m = m + 1;
					d = 1;
				}
				else if(m  == 2 && d < 28) {
					d = d + 1;
				}
				Text.setText(y + "/" + m + "/" + d);
			}
		});
		Button.setBounds(70, 70, 75, 25);
		Button.setText("next");
	}
}
