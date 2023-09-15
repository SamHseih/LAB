package tcgenSWT;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import java.util.function.Consumer;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.MouseWheelListener;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;

public class CounterSWT {

	public Shell Shell;
	public Button Button;
	public Label Label;
	public Text Text;
	public int value;
	public String bs_event_handler;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CounterSWT window = new CounterSWT();
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
		Shell.setBounds(0, 0, 250, 100);
		Shell.setText("Application");
		
		Button = new Button(Shell, SWT.NONE);
		Button.setSelection(true);
		Button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				++value;
				Text.setText(value + "");
			}
		});
		Button.setBounds(150, 10, 75, 25);
		Button.setText("count");
		
		Label = new Label(Shell, SWT.NONE);
		Label.setBounds(10, 10, 60, 15);
		Label.setText("counter");
		
		Text = new Text(Shell, SWT.NONE);
		Text.setTouchEnabled(true);
		Text.setBounds(70, 10, 70, 20);
		Text.setText("0");
	}
	
}
