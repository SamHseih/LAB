package tcgenSWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GradeSWT {

	protected Shell Shell;
	public Text Text1;
	public Text Text2;
	public Button Button;
	public Label Label1;
	public Label Label2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GradeSWT window = new GradeSWT();
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
		Shell.setBounds(0, 0, 250, 250);
		Shell.setText("Grade");
		
		Label1 = new Label(Shell, SWT.NONE);
		Label1.setTouchEnabled(true);
		Label1.setBounds(20, 40, 60, 15);
		Label1.setText("score");
		
		Label2 = new Label(Shell, SWT.NONE);
		Label2.setTouchEnabled(true);
		Label2.setBounds(20, 120, 60, 15);
		Label2.setText("grade");
		
		Text1 = new Text(Shell, SWT.BORDER);
		Text1.setTouchEnabled(true);
		Text1.setBounds(120, 40, 70, 20);
		
		Text2 = new Text(Shell, SWT.BORDER);
		Text2.setTouchEnabled(true);
		Text2.setBounds(120, 120, 70, 20);
		
		Button = new Button(Shell, SWT.NONE);
		Button.setTouchEnabled(true);
		Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int score = Integer.parseInt(Text1.getText());
				if (score >= 90) 
					Text2.setText("A");
				else if (score >= 80 && score <= 89)
					Text2.setText("B");
				else if (score >= 70 && score <= 79)
					Text2.setText("C");
				else if (score >= 60 && score <= 69)
					Text2.setText("D");
				else 
					Text2.setText("F");
			}
		});
		Button.setBounds(120, 80, 75, 25);
		Button.setText("evaluate");
	}
}
