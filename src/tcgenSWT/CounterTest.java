package tcgenSWT;

import static org.junit.Assert.*;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.junit.Test;

public class CounterTest {
	
	private Boolean listenerCalled;
	private String teststr; 
	public String bs_event_handler = "increment";
	
	@Test
	public void Button_alignment_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object button = counter.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_alignment = counter.Button.getAlignment();
			assertNotNull(Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_alignment = counter.Button.getAlignment();
			assertNotEquals(131072, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_alignment = counter.Button.getAlignment();
			assertNotEquals(16384, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_alignment = counter.Button.getAlignment();
			assertEquals(16777216, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_alignment = counter.Button.getAlignment();
			assertNotEquals(0, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object button = counter.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Rectangle bounds = counter.Button.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Button.getBounds().x;
			int y = counter.Button.getBounds().y;
			int width = counter.Button.getBounds().width;
			int height = counter.Button.getBounds().height;
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Button.getBounds().x;
			int y = counter.Button.getBounds().y;
			int width = counter.Button.getBounds().width;
			int height = counter.Button.getBounds().height;
			assertEquals(150, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Button.getBounds().x;
			int y = counter.Button.getBounds().y;
			int width = counter.Button.getBounds().width;
			int height = counter.Button.getBounds().height;
			assertEquals(150, x);
			assertEquals(10, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Button.getBounds().x;
			int y = counter.Button.getBounds().y;
			int width = counter.Button.getBounds().width;
			int height = counter.Button.getBounds().height;
			assertEquals(150, x);
			assertEquals(10, y);
			assertEquals(75, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_7() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Button.getBounds().x;
			int y = counter.Button.getBounds().y;
			int width = counter.Button.getBounds().width;
			int height = counter.Button.getBounds().height;
			assertEquals(150, x);
			assertEquals(10, y);
			assertEquals(75, width);
			assertEquals(25, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object button = counter.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_enabled = counter.Button.getEnabled();
			assertNotNull(Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_enabled = counter.Button.getEnabled();
			assertNotEquals(false, Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_enabled = counter.Button.getEnabled();
			assertEquals(true, Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object button = counter.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_grayed = counter.Button.getGrayed();
			assertNotNull(Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_grayed = counter.Button.getGrayed();
			assertEquals(false, Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_grayed = counter.Button.getGrayed();
			assertNotEquals(true, Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object button = counter.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_selection = counter.Button.getSelection();
			assertNotNull(Button_selection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_selection = counter.Button.getSelection();
			assertEquals(false, Button_selection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			assertNotNull(listener);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			counter.Button.addSelectionListener(listener);
			counter.Button.notifyListeners(SWT.Selection, new Event());
			assertNotEquals("", bs_event_handler);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			counter.Button.addSelectionListener(listener);
			counter.Button.notifyListeners(SWT.Selection, new Event());
			assertEquals("increment", bs_event_handler);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object button = counter.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Button_text = counter.Button.getText();
			assertNotNull(Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Button_text = counter.Button.getText();
			assertEquals("count", Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Button_text = counter.Button.getText();
			assertNotEquals("", Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object button = counter.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_textdirection = counter.Button.getTextDirection();
			assertNotNull(Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_textdirection = counter.Button.getTextDirection();
			assertNotEquals(10663296, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_textdirection = counter.Button.getTextDirection();
			assertNotEquals(67108864, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_textdirection = counter.Button.getTextDirection();
			assertEquals(33554432, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Button_textdirection = counter.Button.getTextDirection();
			assertNotEquals(0, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object button = counter.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_touchenabled = counter.Button.getTouchEnabled();
			assertNotNull(Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_touchenabled = counter.Button.getTouchEnabled();
			assertEquals(false, Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Button_touchenabled = counter.Button.getTouchEnabled();
			assertNotEquals(true, Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_alignment_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object label = counter.Label;
			assertNotNull(label);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_alignment_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getAlignment();
			assertNotNull(Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_alignment_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getAlignment();
			assertNotEquals(16777216, Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_alignment_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getAlignment();
			assertNotEquals(131072, Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_alignment_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getAlignment();
			assertNotEquals(0, Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_alignment_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getAlignment();
			assertEquals(16384, Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_bounds_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object label = counter.Label;
			assertNotNull(label);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_bounds_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Rectangle bounds = counter.Label.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_bounds_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Label.getBounds().x;
			int y = counter.Label.getBounds().y;
			int width = counter.Label.getBounds().width;
			int height = counter.Label.getBounds().height;
			assertNotEquals(0, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_bounds_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Label.getBounds().x;
			int y = counter.Label.getBounds().y;
			int width = counter.Label.getBounds().width;
			int height = counter.Label.getBounds().height;
			assertEquals(10, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_bounds_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Label.getBounds().x;
			int y = counter.Label.getBounds().y;
			int width = counter.Label.getBounds().width;
			int height = counter.Label.getBounds().height;
			assertEquals(10, x);
			assertEquals(10, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_bounds_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Label.getBounds().x;
			int y = counter.Label.getBounds().y;
			int width = counter.Label.getBounds().width;
			int height = counter.Label.getBounds().height;
			assertEquals(10, x);
			assertEquals(10, y);
			assertEquals(60, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_bounds_test_7() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Label.getBounds().x;
			int y = counter.Label.getBounds().y;
			int width = counter.Label.getBounds().width;
			int height = counter.Label.getBounds().height;
			assertEquals(10, x);
			assertEquals(10, y);
			assertEquals(60, width);
			assertEquals(15, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_enabled_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object label = counter.Label;
			assertNotNull(label);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_enabled_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Label_enabled = counter.Label.getEnabled();
			assertNotNull(Label_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_enabled_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Label_enabled = counter.Label.getEnabled();
			assertNotEquals(false, Label_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_enabled_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Label_enabled = counter.Label.getEnabled();
			assertEquals(true, Label_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_text_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object label = counter.Label;
			assertNotNull(label);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_text_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Label_text = counter.Label.getText();
			assertNotNull(Label_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_text_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Label_text = counter.Label.getText();
			assertNotEquals("", Label_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_text_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Label_text = counter.Label.getText();
			assertEquals("counter", Label_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_textdirection_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object label = counter.Button;
			assertNotNull(label);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_textdirection_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_textdirection = counter.Label.getTextDirection();
			assertNotNull(Label_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_textdirection_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getTextDirection();
			assertNotEquals(10663296, Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_textdirection_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getTextDirection();
			assertNotEquals(67108864, Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_textdirection_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getTextDirection();
			assertNotEquals(0, Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_textdirection_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Label_alignment = counter.Label.getTextDirection();
			assertEquals(33554432, Label_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_touchenabled_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object label = counter.Label;
			assertNotNull(label);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_touchenabled_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Label_touchenabled = counter.Label.getTouchEnabled();
			assertNotNull(Label_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_touchenabled_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Label_touchenabled = counter.Label.getTouchEnabled();
			assertEquals(false, Label_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label_touchenabled_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Label_touchenabled = counter.Label.getTouchEnabled();
			assertNotEquals(true, Label_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object shell = counter.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Rectangle bounds = counter.Shell.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Shell.getBounds().x;
			int y = counter.Shell.getBounds().y;
			int width = counter.Shell.getBounds().width;
			int height = counter.Shell.getBounds().height;
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Shell.getBounds().x;
			int y = counter.Shell.getBounds().y;
			int width = counter.Shell.getBounds().width;
			int height = counter.Shell.getBounds().height;
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Shell.getBounds().x;
			int y = counter.Shell.getBounds().y;
			int width = counter.Shell.getBounds().width;
			int height = counter.Shell.getBounds().height;
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Shell.getBounds().x;
			int y = counter.Shell.getBounds().y;
			int width = counter.Shell.getBounds().width;
			int height = counter.Shell.getBounds().height;
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Shell.getBounds().x;
			int y = counter.Shell.getBounds().y;
			int width = counter.Shell.getBounds().width;
			int height = counter.Shell.getBounds().height;
			assertEquals(0, x);
			assertEquals(0, y);
			assertEquals(250, width);
			assertEquals(100, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object shell = counter.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_enabled = counter.Shell.getEnabled();
			assertNotNull(Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_enabled = counter.Shell.getEnabled();
			assertEquals(true, Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_enabled = counter.Shell.getEnabled();
			assertNotEquals(false, Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object shell = counter.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = counter.Shell.getLayout();
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = counter.Shell.getLayout();
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = counter.Shell.getLayout();
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = counter.Shell.getLayout();
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = counter.Shell.getLayout();
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = counter.Shell.getLayout();
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = counter.Shell.getLayout();
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = counter.Shell.getLayout();
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object shell = counter.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Point shell_minimumsize = counter.Shell.getMinimumSize();
			assertNotNull(shell_minimumsize);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int shell_minimumsize_x = counter.Shell.getMinimumSize().x;
			int shell_minimumsize_y = counter.Shell.getMinimumSize().y;
			assertNotEquals(0, shell_minimumsize_x);
			assertNotEquals(0, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int shell_minimumsize_x = counter.Shell.getMinimumSize().x;
			int shell_minimumsize_y = counter.Shell.getMinimumSize().y;
			assertEquals(136, shell_minimumsize_x);
			assertEquals(39, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int shell_minimumsize_x = counter.Shell.getMinimumSize().x;
			int shell_minimumsize_y = counter.Shell.getMinimumSize().y;
			assertEquals(136, shell_minimumsize_x);
			assertNotEquals(0, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object shell = counter.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_modified = counter.Shell.getModified();
			assertNotNull(Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_modified = counter.Shell.getModified();
			assertNotEquals(true, Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_modified = counter.Shell.getModified();
			assertEquals(false, Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object shell = counter.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_text = counter.Shell.getText();
			assertNotNull(Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_text = counter.Shell.getText();
			assertNotEquals("", Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Shell_text = counter.Shell.getText();
			assertEquals("Application", Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object shell = counter.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Shell_textdirection = counter.Shell.getTextDirection();
			assertNotNull(Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Shell_textdirection = counter.Shell.getTextDirection();
			assertNotEquals(10663296, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Shell_textdirection = counter.Shell.getTextDirection();
			assertNotEquals(67108864, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Shell_textdirection = counter.Shell.getTextDirection();
			assertNotEquals(0, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Shell_textdirection = counter.Shell.getTextDirection();
			assertEquals(33554432, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object shell = counter.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_touchenabled = counter.Shell.getTouchEnabled();
			assertNotNull(Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_touchenabled = counter.Shell.getTouchEnabled();
			assertNotEquals(false, Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Shell_touchenabled = counter.Shell.getTouchEnabled();
			assertEquals(true, Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object text = counter.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Rectangle bounds = counter.Text.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Text.getBounds().x;
			int y = counter.Text.getBounds().y;
			int width = counter.Text.getBounds().width;
			int height = counter.Text.getBounds().height;
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Text.getBounds().x;
			int y = counter.Text.getBounds().y;
			int width = counter.Text.getBounds().width;
			int height = counter.Text.getBounds().height;
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
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Text.getBounds().x;
			int y = counter.Text.getBounds().y;
			int width = counter.Text.getBounds().width;
			int height = counter.Text.getBounds().height;
			assertEquals(70, x);
			assertEquals(10, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Text.getBounds().x;
			int y = counter.Text.getBounds().y;
			int width = counter.Text.getBounds().width;
			int height = counter.Text.getBounds().height;
			assertEquals(70, x);
			assertEquals(10, y);
			assertEquals(70, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_bounds_test_7() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int x = counter.Text.getBounds().x;
			int y = counter.Text.getBounds().y;
			int width = counter.Text.getBounds().width;
			int height = counter.Text.getBounds().height;
			assertEquals(70, x);
			assertEquals(10, y);
			assertEquals(70, width);
			assertEquals(20, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_editable_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object text = counter.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_editable_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_editable = counter.Text.getEditable();
			assertNotNull(Text_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_editable_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_editable = counter.Text.getEditable();
			assertEquals(true, Text_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_editable_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_editable = counter.Text.getEditable();
			assertNotEquals(false, Text_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_enabled_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object text = counter.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_enabled_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_enabled = counter.Text.getEnabled();
			assertNotNull(Text_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_enabled_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_enabled = counter.Text.getEnabled();
			assertNotEquals(false, Text_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_enabled_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_enabled = counter.Text.getEnabled();
			assertEquals(true, Text_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_text_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object text = counter.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_text_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Text_text = counter.Text.getText();
			assertNotNull(Text_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_text_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Text_text = counter.Text.getText();
			assertNotEquals("''", Text_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_text_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			String Text_text = counter.Text.getText();
			assertNotEquals("", Text_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object text = counter.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Text_textdirection = counter.Text.getTextDirection();
			assertNotNull(Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Text_textdirection = counter.Text.getTextDirection();
			assertNotEquals(10663296, Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Text_textdirection = counter.Text.getTextDirection();
			assertNotEquals(67108864, Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_5() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Text_textdirection = counter.Text.getTextDirection();
			assertNotEquals(0, Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_textdirection_test_6() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			int Text_textdirection = counter.Text.getTextDirection();
			assertEquals(33554432, Text_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_touchenabled_test_1() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			Object text = counter.Text;
			assertNotNull(text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_touchenabled_test_2() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_touchenabled = counter.Text.getTouchEnabled();
			assertNotNull(Text_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_touchenabled_test_3() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_touchenabled = counter.Text.getTouchEnabled();
			assertEquals(true, Text_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text_touchenabled_test_4() {
		CounterSWT counter = new CounterSWT();
		try {
			counter.createContents();
			boolean Text_touchenabled = counter.Text.getTouchEnabled();
			assertNotEquals(false, Text_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
