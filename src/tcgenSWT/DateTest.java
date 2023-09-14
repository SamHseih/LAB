package tcgenSWT;

import static org.junit.Assert.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.junit.Test;

public class DateTest {
	
	private Boolean listenerCalled;
	private String teststr; 
	public String bs_event_handler = "widgetselected";
	
	@Test
	public void Button_alignment_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object button = Date.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_alignment = Date.Button.getAlignment();
			assertNotNull(Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_alignment = Date.Button.getAlignment();
			assertNotEquals(131072, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_alignment = Date.Button.getAlignment();
			assertNotEquals(16384, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_alignment = Date.Button.getAlignment();
			assertNotEquals(0, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_alignment = Date.Button.getAlignment();
			assertEquals(16777216, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object button = Date.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Rectangle bounds = Date.Button.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Button.getBounds().x;
			int y = Date.Button.getBounds().y;
			int width = Date.Button.getBounds().width;
			int height = Date.Button.getBounds().height;
			assertNotEquals(0, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Button.getBounds().x;
			int y = Date.Button.getBounds().y;
			int width = Date.Button.getBounds().width;
			int height = Date.Button.getBounds().height;
			assertEquals(70, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Button.getBounds().x;
			int y = Date.Button.getBounds().y;
			int width = Date.Button.getBounds().width;
			int height = Date.Button.getBounds().height;
			assertEquals(70, x);
			assertEquals(70, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Button.getBounds().x;
			int y = Date.Button.getBounds().y;
			int width = Date.Button.getBounds().width;
			int height = Date.Button.getBounds().height;
			assertEquals(70, x);
			assertEquals(70, y);
			assertEquals(75, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_7() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Button.getBounds().x;
			int y = Date.Button.getBounds().y;
			int width = Date.Button.getBounds().width;
			int height = Date.Button.getBounds().height;
			assertEquals(70, x);
			assertEquals(70, y);
			assertEquals(75, width);
			assertEquals(25, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object button = Date.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_enabled = Date.Button.getEnabled();
			assertNotNull(Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_enabled = Date.Button.getEnabled();
			assertEquals(true, Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_enabled = Date.Button.getEnabled();
			assertNotEquals(false, Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object button = Date.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_grayed = Date.Button.getGrayed();
			assertNotNull(Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_grayed = Date.Button.getGrayed();
			assertNotEquals(true, Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_grayed = Date.Button.getGrayed();
			assertEquals(false, Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object button = Date.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_selection = Date.Button.getSelection();
			assertNotNull(Button_selection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_selection = Date.Button.getSelection();
			assertEquals(false, Button_selection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			assertNotNull(listener);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			Date.Button.addSelectionListener(listener);
			Date.Button.notifyListeners(SWT.Selection, new Event());
			assertEquals("widgetselected", bs_event_handler);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			Date.Button.addSelectionListener(listener);
			Date.Button.notifyListeners(SWT.Selection, new Event());
			assertNotEquals("", bs_event_handler);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object button = Date.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Button_text = Date.Button.getText();
			assertNotNull(Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Button_text = Date.Button.getText();
			assertEquals("next", Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Button_text = Date.Button.getText();
			assertNotEquals("", Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object button = Date.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_textdirection = Date.Button.getTextDirection();
			assertNotNull(Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_textdirection = Date.Button.getTextDirection();
			assertNotEquals(10663296, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_textdirection = Date.Button.getTextDirection();
			assertNotEquals(67108864, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_textdirection = Date.Button.getTextDirection();
			assertEquals(33554432, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Button_textdirection = Date.Button.getTextDirection();
			assertNotEquals(0, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object button = Date.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_touchenabled = Date.Button.getTouchEnabled();
			assertNotNull(Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_touchenabled = Date.Button.getTouchEnabled();
			assertEquals(true, Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Button_touchenabled = Date.Button.getTouchEnabled();
			assertNotEquals(false, Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void DateTime_bounds_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object datetime = Date.Datetime;
			assertNotNull(datetime);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_bounds_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Rectangle bounds = Date.Datetime.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_bounds_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Datetime.getBounds().x;
			int y = Date.Datetime.getBounds().y;
			int width = Date.Datetime.getBounds().width;
			int height = Date.Datetime.getBounds().height;
			assertNotEquals(0, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_bounds_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Datetime.getBounds().x;
			int y = Date.Datetime.getBounds().y;
			int width = Date.Datetime.getBounds().width;
			int height = Date.Datetime.getBounds().height;
			assertEquals(70, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_bounds_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Datetime.getBounds().x;
			int y = Date.Datetime.getBounds().y;
			int width = Date.Datetime.getBounds().width;
			int height = Date.Datetime.getBounds().height;
			assertEquals(70, x);
			assertEquals(20, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_bounds_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Datetime.getBounds().x;
			int y = Date.Datetime.getBounds().y;
			int width = Date.Datetime.getBounds().width;
			int height = Date.Datetime.getBounds().height;
			assertEquals(70, x);
			assertEquals(20, y);
			assertEquals(90, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_bounds_test_7() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Datetime.getBounds().x;
			int y = Date.Datetime.getBounds().y;
			int width = Date.Datetime.getBounds().width;
			int height = Date.Datetime.getBounds().height;
			assertEquals(70, x);
			assertEquals(20, y);
			assertEquals(90, width);
			assertEquals(30, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_enabled_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object datetime = Date.Datetime;
			assertNotNull(datetime);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_enabled_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Datetime_enabled = Date.Datetime.getEnabled();
			assertNotNull(Datetime_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_enabled_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Datetime_enabled = Date.Datetime.getEnabled();
			assertEquals(true, Datetime_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_enabled_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Label_enabled = Date.Datetime.getEnabled();
			assertNotEquals(false, Label_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_textdirection_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object datetime = Date.Datetime;
			assertNotNull(datetime);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_textdirection_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Datetime_textdirection = Date.Datetime.getTextDirection();
			assertNotNull(Datetime_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_textdirection_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Datetime_textdirection = Date.Datetime.getTextDirection();
			assertNotEquals(10663296, Datetime_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_textdirection_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Datetime_textdirection = Date.Datetime.getTextDirection();
			assertNotEquals(67108864, Datetime_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_textdirection_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Datetime_textdirection = Date.Datetime.getTextDirection();
			assertNotEquals(0, Datetime_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_textdirection_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Datetime_textdirection = Date.Datetime.getTextDirection();
			assertEquals(33554432, Datetime_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_touchenabled_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object datetime = Date.Datetime;
			assertNotNull(datetime);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_touchenabled_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Datetime_touchenabled = Date.Datetime.getTouchEnabled();
			assertNotNull(Datetime_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_touchenabled_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Datetime_touchenabled = Date.Datetime.getTouchEnabled();
			assertEquals(true, Datetime_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Datetime_touchenabled_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Datetime_touchenabled = Date.Datetime.getTouchEnabled();
			assertNotEquals(false, Datetime_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object shell = Date.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Rectangle bounds = Date.Shell.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Shell.getBounds().x;
			int y = Date.Shell.getBounds().y;
			int width = Date.Shell.getBounds().width;
			int height = Date.Shell.getBounds().height;
			assertNotEquals(1, x);
			assertEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Shell.getBounds().x;
			int y = Date.Shell.getBounds().y;
			int width = Date.Shell.getBounds().width;
			int height = Date.Shell.getBounds().height;
			assertEquals(0, x);
			assertNotEquals(1, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Shell.getBounds().x;
			int y = Date.Shell.getBounds().y;
			int width = Date.Shell.getBounds().width;
			int height = Date.Shell.getBounds().height;
			assertEquals(0, x);
			assertEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Shell.getBounds().x;
			int y = Date.Shell.getBounds().y;
			int width = Date.Shell.getBounds().width;
			int height = Date.Shell.getBounds().height;
			assertEquals(0, x);
			assertEquals(0, y);
			assertEquals(250, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_7() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Shell.getBounds().x;
			int y = Date.Shell.getBounds().y;
			int width = Date.Shell.getBounds().width;
			int height = Date.Shell.getBounds().height;
			assertEquals(0, x);
			assertEquals(0, y);
			assertEquals(250, width);
			assertEquals(200, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object shell = Date.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_enabled = Date.Shell.getEnabled();
			assertNotNull(Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_enabled = Date.Shell.getEnabled();
			assertNotEquals(false, Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_enabled = Date.Shell.getEnabled();
			assertEquals(true, Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object shell = Date.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Date.Shell.getLayout();
			if (Shell_layout instanceof Layout) {
				Shell_layoutinfo = "Layout";
			}
			assertNotEquals("Layout", Shell_layoutinfo);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Date.Shell.getLayout();
			if (Shell_layout instanceof StackLayout) {
				Shell_layoutinfo = "StackLayout";
			}
			assertNotEquals("StackLayout", Shell_layoutinfo);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Date.Shell.getLayout();
			if (Shell_layout instanceof FormLayout) {
				Shell_layoutinfo = "FormLayout";
			}
			assertNotEquals("FormLayout", Shell_layoutinfo);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Date.Shell.getLayout();
			if (Shell_layout instanceof RowLayout) {
				Shell_layoutinfo = "RowLayout";
			}
			assertNotEquals("RowLayout", Shell_layoutinfo);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Date.Shell.getLayout();
			if (Shell_layout instanceof FillLayout) {
				Shell_layoutinfo = "FillLayout";
			}
			assertNotEquals("FillLayout", Shell_layoutinfo);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_7() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Date.Shell.getLayout();
			if (Shell_layout instanceof GridLayout) {
				Shell_layoutinfo = "GridLayout";
			}
			assertNotEquals("GridLayout", Shell_layoutinfo);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_8() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Date.Shell.getLayout();
			if (Shell_layout == null) {
				Shell_layoutinfo = "abosolute Layout";
			}
			assertEquals("abosolute Layout", Shell_layoutinfo);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_9() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Date.Shell.getLayout();
			if (Shell_layout != null) {
				Shell_layoutinfo = "";
			}
			assertEquals("", Shell_layoutinfo);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object shell = Date.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Point shell_minimumsize = Date.Shell.getMinimumSize();
			assertNotNull(shell_minimumsize);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int shell_minimumsize_x = Date.Shell.getMinimumSize().x;
			int shell_minimumsize_y = Date.Shell.getMinimumSize().y;
			assertNotEquals(0, shell_minimumsize_x);
			assertNotEquals(0, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int shell_minimumsize_x = Date.Shell.getMinimumSize().x;
			int shell_minimumsize_y = Date.Shell.getMinimumSize().y;
			assertEquals(136, shell_minimumsize_x);
			assertEquals(39, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int shell_minimumsize_x = Date.Shell.getMinimumSize().x;
			int shell_minimumsize_y = Date.Shell.getMinimumSize().y;
			assertEquals(136, shell_minimumsize_x);
			assertNotEquals(0, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object shell = Date.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_modified = Date.Shell.getModified();
			assertNotNull(Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_modified = Date.Shell.getModified();
			assertNotEquals(true, Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_modified = Date.Shell.getModified();
			assertEquals(false, Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object shell = Date.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_text = Date.Shell.getText();
			assertNotNull(Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_text = Date.Shell.getText();
			assertEquals("DateTime", Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Shell_text = Date.Shell.getText();
			assertNotEquals("", Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object shell = Date.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Shell_textdirection = Date.Shell.getTextDirection();
			assertNotNull(Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Shell_textdirection = Date.Shell.getTextDirection();
			assertNotEquals(10663296, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Shell_textdirection = Date.Shell.getTextDirection();
			assertNotEquals(67108864, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Shell_textdirection = Date.Shell.getTextDirection();
			assertEquals(33554432, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Shell_textdirection = Date.Shell.getTextDirection();
			assertNotEquals(0, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object shell = Date.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_touchenabled = Date.Shell.getTouchEnabled();
			assertNotNull(Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_touchenabled = Date.Shell.getTouchEnabled();
			assertNotEquals(false, Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Shell_touchenabled = Date.Shell.getTouchEnabled();
			assertEquals(true, Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object text = Date.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Rectangle bounds = Date.Text.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Text.getBounds().x;
			int y = Date.Text.getBounds().y;
			int width = Date.Text.getBounds().width;
			int height = Date.Text.getBounds().height;
			assertNotEquals(0, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Text.getBounds().x;
			int y = Date.Text.getBounds().y;
			int width = Date.Text.getBounds().width;
			int height = Date.Text.getBounds().height;
			assertEquals(70, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Text.getBounds().x;
			int y = Date.Text.getBounds().y;
			int width = Date.Text.getBounds().width;
			int height = Date.Text.getBounds().height;
			assertEquals(70, x);
			assertEquals(120, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Text.getBounds().x;
			int y = Date.Text.getBounds().y;
			int width = Date.Text.getBounds().width;
			int height = Date.Text.getBounds().height;
			assertEquals(70, x);
			assertEquals(120, y);
			assertEquals(75, width);
			assertEquals(25, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_7() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int x = Date.Text.getBounds().x;
			int y = Date.Text.getBounds().y;
			int width = Date.Text.getBounds().width;
			int height = Date.Text.getBounds().height;
			assertEquals(70, x);
			assertEquals(120, y);
			assertEquals(75, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_editable_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object text = Date.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_editable_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_editable = Date.Text.getEditable();
			assertNotNull(Text_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_editable_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_editable = Date.Text.getEditable();
			assertEquals(true, Text_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_editable_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_editable = Date.Text.getEditable();
			assertNotEquals(false, Text_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_enabled_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object text = Date.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_enabled_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_enabled = Date.Text.getEnabled();
			assertNotNull(Text_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_enabled_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_enabled = Date.Text.getEnabled();
			assertEquals(true, Text_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_enabled_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_enabled = Date.Text.getEnabled();
			assertNotEquals(false, Text_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_text_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object text = Date.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_text_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Text_text = Date.Text.getText();
			assertNotNull(Text_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_text_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Text_text = Date.Text.getText();
			assertEquals("", Text_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_text_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			String Text_text = Date.Text.getText();
			assertNotEquals("''", Text_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object text = Date.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Text_textdirection = Date.Text.getTextDirection();
			assertNotNull(Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Text_textdirection = Date.Text.getTextDirection();
			assertNotEquals(10663296, Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Text_textdirection = Date.Text.getTextDirection();
			assertNotEquals(67108864, Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_5() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Text_textdirection = Date.Text.getTextDirection();
			assertNotEquals(0, Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_6() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			int Text_textdirection = Date.Text.getTextDirection();
			assertEquals(33554432, Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_touchenabled_test_1() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			Object text = Date.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_touchenabled_test_2() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_touchenabled = Date.Text.getTouchEnabled();
			assertNotNull(Text_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_touchenabled_test_3() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_touchenabled = Date.Text.getTouchEnabled();
			assertEquals(true, Text_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_touchenabled_test_4() {
		DateSWT Date = new DateSWT();
		try {
			Date.createContents();
			boolean Text_touchenabled = Date.Text.getTouchEnabled();
			assertNotEquals(false, Text_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
