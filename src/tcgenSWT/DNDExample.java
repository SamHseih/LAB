package tcgenSWT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.widgets.*;

public class DNDExample {
	  public static void main(String[] args) {
		    Display display = new Display();
		    Shell shell = new Shell(display);
		    
		    Text text = new Text(shell, SWT.BORDER|SWT.SINGLE);

		    Transfer[] types = new Transfer[] { TextTransfer.getInstance() };
		    DragSource source = new DragSource(text, DND.DROP_MOVE | DND.DROP_COPY);
		    source.setTransfer(types);
		    
		    source.addDragListener(new DragSourceAdapter() {
		      public void dragSetData(DragSourceEvent event) {
		        // Get the selected items in the drag source
		        DragSource ds = (DragSource) event.widget;
		        Text text = (Text) ds.getControl();

		        event.data = text.getSelectionText();
		      }
		    });

		    Label label = new Label(shell, SWT.BORDER);
		    // Create the drop target
		    DropTarget target = new DropTarget(label, DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
		    target.setTransfer(types);
		    target.addDropListener(new DropTargetAdapter() {
		      public void dragEnter(DropTargetEvent event) {
		        if (event.detail == DND.DROP_DEFAULT) {
		          event.detail = (event.operations & DND.DROP_COPY) != 0 ? DND.DROP_COPY : DND.DROP_NONE;
		        }

		        // Allow dropping text only
		        for (int i = 0, n = event.dataTypes.length; i < n; i++) {
		          if (TextTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
		            event.currentDataType = event.dataTypes[i];
		          }
		        }
		      }

		      public void dragOver(DropTargetEvent event) {
		         event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
		      }
		      public void drop(DropTargetEvent event) {
		        if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
		          // Get the dropped data
		          DropTarget target = (DropTarget) event.widget;
		          Label label = (Label) target.getControl();
		          String data = (String) event.data;

		          label.setText(data);
		          label.redraw();
		        }
		      }
		    });

		    text.setBounds(10,10,100,25);
		    label.setBounds(10,55,100,25);
		    shell.open();

		    while (!shell.isDisposed()) {
		      if (!display.readAndDispatch()) {
		        display.sleep();
		      }
		    }
		    display.dispose();
		  }
}
