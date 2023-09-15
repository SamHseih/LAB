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

public class GradeTest {
	private Boolean listenerCalled;
	private String teststr; 
	public String bs_event_handler = "widgetselected";
	
	@Test
	public void Button_alignment_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object button = Grade.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_alignment = Grade.Button.getAlignment();
			assertNotNull(Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_alignment = Grade.Button.getAlignment();
			assertNotEquals(131072, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_alignment = Grade.Button.getAlignment();
			assertNotEquals(16384, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_alignment = Grade.Button.getAlignment();
			assertEquals(16777216, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_alignment_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_alignment = Grade.Button.getAlignment();
			assertNotEquals(0, Button_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object button = Grade.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Rectangle bounds = Grade.Button.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Button.getBounds().x;
			int y = Grade.Button.getBounds().y;
			int width = Grade.Button.getBounds().width;
			int height = Grade.Button.getBounds().height;
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Button.getBounds().x;
			int y = Grade.Button.getBounds().y;
			int width = Grade.Button.getBounds().width;
			int height = Grade.Button.getBounds().height;
			assertEquals(120, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Button.getBounds().x;
			int y = Grade.Button.getBounds().y;
			int width = Grade.Button.getBounds().width;
			int height = Grade.Button.getBounds().height;
			assertEquals(120, x);
			assertEquals(80, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Button.getBounds().x;
			int y = Grade.Button.getBounds().y;
			int width = Grade.Button.getBounds().width;
			int height = Grade.Button.getBounds().height;
			assertEquals(120, x);
			assertEquals(80, y);
			assertEquals(75, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_bounds_test_7() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Button.getBounds().x;
			int y = Grade.Button.getBounds().y;
			int width = Grade.Button.getBounds().width;
			int height = Grade.Button.getBounds().height;
			assertEquals(120, x);
			assertEquals(80, y);
			assertEquals(75, width);
			assertEquals(25, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object button = Grade.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_enabled = Grade.Button.getEnabled();
			assertNotNull(Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_enabled = Grade.Button.getEnabled();
			assertNotEquals(false, Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_enabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_enabled = Grade.Button.getEnabled();
			assertEquals(true, Button_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object button = Grade.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_grayed = Grade.Button.getGrayed();
			assertNotNull(Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_grayed = Grade.Button.getGrayed();
			assertNotEquals(true, Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_grayed_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_grayed = Grade.Button.getGrayed();
			assertEquals(false, Button_grayed);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object button = Grade.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_selection = Grade.Button.getSelection();
			assertNotNull(Button_selection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_selection = Grade.Button.getSelection();
			assertEquals(false, Button_selection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			assertNotNull(listener);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			Grade.Button.addSelectionListener(listener);
			Grade.Button.notifyListeners(SWT.Selection, new Event());
			assertNotEquals("", bs_event_handler);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_selectionlistener_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			SelectionListener listener;		
			listener = SelectionListener.widgetSelectedAdapter(e->listenerCalled=true);
			Grade.Button.addSelectionListener(listener);
			Grade.Button.notifyListeners(SWT.Selection, new Event());
			assertEquals("widgetselected", bs_event_handler);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object button = Grade.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Button_text = Grade.Button.getText();
			assertNotNull(Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Button_text = Grade.Button.getText();
			assertEquals("evaluate", Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_text_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Button_text = Grade.Button.getText();
			assertNotEquals("", Button_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object button = Grade.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_textdirection = Grade.Button.getTextDirection();
			assertNotNull(Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_textdirection = Grade.Button.getTextDirection();
			assertNotEquals(10663296, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_textdirection = Grade.Button.getTextDirection();
			assertNotEquals(67108864, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_textdirection = Grade.Button.getTextDirection();
			assertEquals(33554432, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_textdirection_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Button_textdirection = Grade.Button.getTextDirection();
			assertNotEquals(0, Button_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object button = Grade.Button;
			assertNotNull(button);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_touchenabled = Grade.Button.getTouchEnabled();
			assertNotNull(Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_touchenabled = Grade.Button.getTouchEnabled();
			assertNotEquals(false, Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Button_touchenabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Button_touchenabled = Grade.Button.getTouchEnabled();
			assertEquals(true, Button_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_alignment_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label1 = Grade.Label1;
			assertNotNull(Label1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_alignment_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getAlignment();
			assertNotNull(Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_alignment_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getAlignment();
			assertNotEquals(16777216, Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_alignment_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getAlignment();
			assertNotEquals(131072, Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_alignment_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getAlignment();
			assertNotEquals(0, Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_alignment_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getAlignment();
			assertEquals(16384, Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_bounds_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label1 = Grade.Label1;
			assertNotNull(Label1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_bounds_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Rectangle bounds = Grade.Label1.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_bounds_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label1.getBounds().x;
			int y = Grade.Label1.getBounds().y;
			int width = Grade.Label1.getBounds().width;
			int height = Grade.Label1.getBounds().height;
			assertNotEquals(0, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_bounds_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label1.getBounds().x;
			int y = Grade.Label1.getBounds().y;
			int width = Grade.Label1.getBounds().width;
			int height = Grade.Label1.getBounds().height;
			assertEquals(20, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_bounds_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label1.getBounds().x;
			int y = Grade.Label1.getBounds().y;
			int width = Grade.Label1.getBounds().width;
			int height = Grade.Label1.getBounds().height;
			assertEquals(20, x);
			assertEquals(40, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_bounds_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label1.getBounds().x;
			int y = Grade.Label1.getBounds().y;
			int width = Grade.Label1.getBounds().width;
			int height = Grade.Label1.getBounds().height;
			assertEquals(20, x);
			assertEquals(40, y);
			assertEquals(60, width);
			assertEquals(15, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_bounds_test_7() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label1.getBounds().x;
			int y = Grade.Label1.getBounds().y;
			int width = Grade.Label1.getBounds().width;
			int height = Grade.Label1.getBounds().height;
			assertEquals(20, x);
			assertEquals(40, y);
			assertEquals(60, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_enabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label1 = Grade.Label1;
			assertNotNull(Label1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_enabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label1_enabled = Grade.Label1.getEnabled();
			assertNotNull(Label1_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_enabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label1_enabled = Grade.Label1.getEnabled();
			assertNotEquals(false, Label1_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_enabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label1_enabled = Grade.Label1.getEnabled();
			assertEquals(true, Label1_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_text_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label1 = Grade.Label1;
			assertNotNull(Label1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_text_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Label1_text = Grade.Label1.getText();
			assertNotNull(Label1_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_text_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Label1_text = Grade.Label1.getText();
			assertNotEquals("", Label1_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_text_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Label1_text = Grade.Label1.getText();
			assertEquals("score", Label1_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_textdirection_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label1 = Grade.Button;
			assertNotNull(Label1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_textdirection_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_textdirection = Grade.Label1.getTextDirection();
			assertNotNull(Label1_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_textdirection_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getTextDirection();
			assertNotEquals(10663296, Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_textdirection_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getTextDirection();
			assertNotEquals(67108864, Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_textdirection_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getTextDirection();
			assertNotEquals(0, Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_textdirection_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label1_alignment = Grade.Label1.getTextDirection();
			assertEquals(33554432, Label1_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_touchenabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label1 = Grade.Label1;
			assertNotNull(Label1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_touchenabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label1_touchenabled = Grade.Label1.getTouchEnabled();
			assertNotNull(Label1_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_touchenabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label1_touchenabled = Grade.Label1.getTouchEnabled();
			assertNotEquals(false, Label1_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label1_touchenabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label1_touchenabled = Grade.Label1.getTouchEnabled();
			assertEquals(true, Label1_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_alignment_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label2 = Grade.Label2;
			assertNotNull(Label2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_alignment_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getAlignment();
			assertNotNull(Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_alignment_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getAlignment();
			assertNotEquals(16777216, Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_alignment_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getAlignment();
			assertNotEquals(131072, Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_alignment_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getAlignment();
			assertNotEquals(0, Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_alignment_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getAlignment();
			assertEquals(16384, Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_bounds_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label2 = Grade.Label2;
			assertNotNull(Label2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_bounds_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Rectangle bounds = Grade.Label2.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_bounds_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label2.getBounds().x;
			int y = Grade.Label2.getBounds().y;
			int width = Grade.Label2.getBounds().width;
			int height = Grade.Label2.getBounds().height;
			assertNotEquals(0, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_bounds_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label2.getBounds().x;
			int y = Grade.Label2.getBounds().y;
			int width = Grade.Label2.getBounds().width;
			int height = Grade.Label2.getBounds().height;
			assertEquals(20, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_bounds_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label2.getBounds().x;
			int y = Grade.Label2.getBounds().y;
			int width = Grade.Label2.getBounds().width;
			int height = Grade.Label2.getBounds().height;
			assertEquals(20, x);
			assertEquals(120, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_bounds_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label2.getBounds().x;
			int y = Grade.Label2.getBounds().y;
			int width = Grade.Label2.getBounds().width;
			int height = Grade.Label2.getBounds().height;
			assertEquals(20, x);
			assertEquals(120, y);
			assertEquals(60, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_bounds_test_7() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Label2.getBounds().x;
			int y = Grade.Label2.getBounds().y;
			int width = Grade.Label2.getBounds().width;
			int height = Grade.Label2.getBounds().height;
			assertEquals(20, x);
			assertEquals(120, y);
			assertEquals(60, width);
			assertEquals(15, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_enabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label2 = Grade.Label2;
			assertNotNull(Label2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_enabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label2_enabled = Grade.Label2.getEnabled();
			assertNotNull(Label2_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_enabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label2_enabled = Grade.Label2.getEnabled();
			assertNotEquals(false, Label2_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_enabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label2_enabled = Grade.Label2.getEnabled();
			assertEquals(true, Label2_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_text_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label2 = Grade.Label2;
			assertNotNull(Label2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_text_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Label2_text = Grade.Label2.getText();
			assertNotNull(Label2_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_text_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Label2_text = Grade.Label2.getText();
			assertNotEquals("", Label2_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_text_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Label2_text = Grade.Label2.getText();
			assertEquals("grade", Label2_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_textdirection_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label2 = Grade.Button;
			assertNotNull(Label2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_textdirection_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_textdirection = Grade.Label2.getTextDirection();
			assertNotNull(Label2_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_textdirection_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getTextDirection();
			assertNotEquals(10663296, Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_textdirection_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getTextDirection();
			assertNotEquals(67108864, Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_textdirection_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getTextDirection();
			assertEquals(33554432, Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_textdirection_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Label2_alignment = Grade.Label2.getTextDirection();
			assertNotEquals(0, Label2_alignment);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_touchenabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Label2 = Grade.Label2;
			assertNotNull(Label2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_touchenabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label2_touchenabled = Grade.Label2.getTouchEnabled();
			assertNotNull(Label2_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_touchenabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label2_touchenabled = Grade.Label2.getTouchEnabled();
			assertEquals(true, Label2_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Label2_touchenabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Label2_touchenabled = Grade.Label2.getTouchEnabled();
			assertNotEquals(false, Label2_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	
	@Test
	public void Shell_bounds_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object shell = Grade.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Rectangle bounds = Grade.Shell.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_bounds_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Shell.getBounds().x;
			int y = Grade.Shell.getBounds().y;
			int width = Grade.Shell.getBounds().width;
			int height = Grade.Shell.getBounds().height;
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Shell.getBounds().x;
			int y = Grade.Shell.getBounds().y;
			int width = Grade.Shell.getBounds().width;
			int height = Grade.Shell.getBounds().height;
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Shell.getBounds().x;
			int y = Grade.Shell.getBounds().y;
			int width = Grade.Shell.getBounds().width;
			int height = Grade.Shell.getBounds().height;
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Shell.getBounds().x;
			int y = Grade.Shell.getBounds().y;
			int width = Grade.Shell.getBounds().width;
			int height = Grade.Shell.getBounds().height;
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Shell.getBounds().x;
			int y = Grade.Shell.getBounds().y;
			int width = Grade.Shell.getBounds().width;
			int height = Grade.Shell.getBounds().height;
			assertEquals(0, x);
			assertEquals(0, y);
			assertEquals(250, width);
			assertEquals(250, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object shell = Grade.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_enabled = Grade.Shell.getEnabled();
			assertNotNull(Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_enabled = Grade.Shell.getEnabled();
			assertEquals(true, Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_enabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_enabled = Grade.Shell.getEnabled();
			assertNotEquals(false, Shell_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object shell = Grade.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_layout_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Grade.Shell.getLayout();
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Grade.Shell.getLayout();
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Grade.Shell.getLayout();
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Grade.Shell.getLayout();
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Grade.Shell.getLayout();
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Grade.Shell.getLayout();
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Grade.Shell.getLayout();
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_layoutinfo = "";
			Layout Shell_layout = Grade.Shell.getLayout();
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
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object shell = Grade.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Point shell_minimumsize = Grade.Shell.getMinimumSize();
			assertNotNull(shell_minimumsize);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int shell_minimumsize_x = Grade.Shell.getMinimumSize().x;
			int shell_minimumsize_y = Grade.Shell.getMinimumSize().y;
			assertNotEquals(0, shell_minimumsize_x);
			assertNotEquals(0, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int shell_minimumsize_x = Grade.Shell.getMinimumSize().x;
			int shell_minimumsize_y = Grade.Shell.getMinimumSize().y;
			assertEquals(136, shell_minimumsize_x);
			assertEquals(39, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_minimumsize_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int shell_minimumsize_x = Grade.Shell.getMinimumSize().x;
			int shell_minimumsize_y = Grade.Shell.getMinimumSize().y;
			assertEquals(136, shell_minimumsize_x);
			assertNotEquals(0, shell_minimumsize_y);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object shell = Grade.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_modified = Grade.Shell.getModified();
			assertNotNull(Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_modified = Grade.Shell.getModified();
			assertEquals(false, Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_modified_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_modified = Grade.Shell.getModified();
			assertNotEquals(true, Shell_modified);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object shell = Grade.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_text = Grade.Shell.getText();
			assertNotNull(Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_text = Grade.Shell.getText();
			assertNotEquals("", Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_text_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Shell_text = Grade.Shell.getText();
			assertEquals("Grade", Shell_text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object shell = Grade.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Shell_textdirection = Grade.Shell.getTextDirection();
			assertNotNull(Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Shell_textdirection = Grade.Shell.getTextDirection();
			assertNotEquals(10663296, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Shell_textdirection = Grade.Shell.getTextDirection();
			assertNotEquals(67108864, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Shell_textdirection = Grade.Shell.getTextDirection();
			assertNotEquals(0, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_textdirection_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Shell_textdirection = Grade.Shell.getTextDirection();
			assertEquals(33554432, Shell_textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object shell = Grade.Shell;
			assertNotNull(shell);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_touchenabled = Grade.Shell.getTouchEnabled();
			assertNotNull(Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_touchenabled = Grade.Shell.getTouchEnabled();
			assertNotEquals(false, Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Shell_touchenabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Shell_touchenabled = Grade.Shell.getTouchEnabled();
			assertEquals(true, Shell_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_bounds_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text1 = Grade.Text1;
			assertNotNull(Text1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_bounds_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Rectangle bounds = Grade.Text1.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_bounds_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text1.getBounds().x;
			int y = Grade.Text1.getBounds().y;
			int width = Grade.Text1.getBounds().width;
			int height = Grade.Text1.getBounds().height;
			assertNotEquals(0, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_bounds_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text1.getBounds().x;
			int y = Grade.Text1.getBounds().y;
			int width = Grade.Text1.getBounds().width;
			int height = Grade.Text1.getBounds().height;
			assertEquals(120, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_bounds_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text1.getBounds().x;
			int y = Grade.Text1.getBounds().y;
			int width = Grade.Text1.getBounds().width;
			int height = Grade.Text1.getBounds().height;
			assertEquals(120, x);
			assertEquals(40, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_bounds_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text1.getBounds().x;
			int y = Grade.Text1.getBounds().y;
			int width = Grade.Text1.getBounds().width;
			int height = Grade.Text1.getBounds().height;
			assertEquals(120, x);
			assertEquals(40, y);
			assertEquals(70, width);
			assertEquals(20, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_bounds_test_7() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text1.getBounds().x;
			int y = Grade.Text1.getBounds().y;
			int width = Grade.Text1.getBounds().width;
			int height = Grade.Text1.getBounds().height;
			assertEquals(120, x);
			assertEquals(40, y);
			assertEquals(70, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_editable_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text1 = Grade.Text1;
			assertNotNull(Text1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_editable_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_editable = Grade.Text1.getEditable();
			assertNotNull(Text1_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_editable_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_editable = Grade.Text1.getEditable();
			assertEquals(true, Text1_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_editable_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_editable = Grade.Text1.getEditable();
			assertNotEquals(false, Text1_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_enabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text1 = Grade.Text1;
			assertNotNull(Text1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_enabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_enabled = Grade.Text1.getEnabled();
			assertNotNull(Text1_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_enabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_enabled = Grade.Text1.getEnabled();
			assertNotEquals(false, Text1_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_enabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_enabled = Grade.Text1.getEnabled();
			assertEquals(true, Text1_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text1 = Grade.Text1;
			assertNotNull(Text1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Text1_Text = Grade.Text1.getText();
			assertNotNull(Text1_Text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Text1_Text = Grade.Text1.getText();
			assertNotEquals("''", Text1_Text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Text1_Text = Grade.Text1.getText();
			assertEquals("", Text1_Text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1direction_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text1 = Grade.Text1;
			assertNotNull(Text1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1direction_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text1_Textdirection = Grade.Text1.getTextDirection();
			assertNotNull(Text1_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1direction_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text1_Textdirection = Grade.Text1.getTextDirection();
			assertNotEquals(10663296, Text1_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1direction_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text1_Textdirection = Grade.Text1.getTextDirection();
			assertNotEquals(67108864, Text1_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1direction_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text1_Textdirection = Grade.Text1.getTextDirection();
			assertEquals(33554432, Text1_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_Text1direction_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text1_Textdirection = Grade.Text1.getTextDirection();
			assertNotEquals(0, Text1_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_touchenabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text1 = Grade.Text1;
			assertNotNull(Text1);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_touchenabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_touchenabled = Grade.Text1.getTouchEnabled();
			assertNotNull(Text1_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_touchenabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_touchenabled = Grade.Text1.getTouchEnabled();
			assertNotEquals(false, Text1_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text1_touchenabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text1_touchenabled = Grade.Text1.getTouchEnabled();
			assertEquals(true, Text1_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_bounds_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text2 = Grade.Text2;
			assertNotNull(Text2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_bounds_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Rectangle bounds = Grade.Text2.getBounds();
			assertNotNull(bounds);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_bounds_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text2.getBounds().x;
			int y = Grade.Text2.getBounds().y;
			int width = Grade.Text2.getBounds().width;
			int height = Grade.Text2.getBounds().height;
			assertNotEquals(0, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_bounds_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text2.getBounds().x;
			int y = Grade.Text2.getBounds().y;
			int width = Grade.Text2.getBounds().width;
			int height = Grade.Text2.getBounds().height;
			assertEquals(120, x);
			assertNotEquals(0, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_bounds_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text2.getBounds().x;
			int y = Grade.Text2.getBounds().y;
			int width = Grade.Text2.getBounds().width;
			int height = Grade.Text2.getBounds().height;
			assertEquals(120, x);
			assertEquals(120, y);
			assertNotEquals(0, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_bounds_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text2.getBounds().x;
			int y = Grade.Text2.getBounds().y;
			int width = Grade.Text2.getBounds().width;
			int height = Grade.Text2.getBounds().height;
			assertEquals(120, x);
			assertEquals(120, y);
			assertEquals(70, width);
			assertNotEquals(0, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_bounds_test_7() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int x = Grade.Text2.getBounds().x;
			int y = Grade.Text2.getBounds().y;
			int width = Grade.Text2.getBounds().width;
			int height = Grade.Text2.getBounds().height;
			assertEquals(120, x);
			assertEquals(120, y);
			assertEquals(70, width);
			assertEquals(20, height);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_editable_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text2 = Grade.Text2;
			assertNotNull(Text2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_editable_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_editable = Grade.Text2.getEditable();
			assertNotNull(Text2_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_editable_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_editable = Grade.Text2.getEditable();
			assertNotEquals(false, Text2_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_editable_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_editable = Grade.Text2.getEditable();
			assertEquals(true, Text2_editable);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_enabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text2 = Grade.Text2;
			assertNotNull(Text2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_enabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_enabled = Grade.Text2.getEnabled();
			assertNotNull(Text2_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_enabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_enabled = Grade.Text2.getEnabled();
			assertNotEquals(false, Text2_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_enabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_enabled = Grade.Text2.getEnabled();
			assertEquals(true, Text2_enabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text2 = Grade.Text2;
			assertNotNull(Text2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Text2_Text = Grade.Text2.getText();
			assertNotNull(Text2_Text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Text2_Text = Grade.Text2.getText();
			assertEquals("", Text2_Text);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			String Text2_Text = Grade.Text2.getText();
			assertNotEquals("''", Text2_Text);	
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2direction_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text2 = Grade.Text2;
			assertNotNull(Text2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2direction_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text2_Textdirection = Grade.Text2.getTextDirection();
			assertNotNull(Text2_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2direction_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text2_Textdirection = Grade.Text2.getTextDirection();
			assertNotEquals(10663296, Text2_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2direction_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text2_Textdirection = Grade.Text2.getTextDirection();
			assertNotEquals(67108864, Text2_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2direction_test_5() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text2_Textdirection = Grade.Text2.getTextDirection();
			assertEquals(33554432, Text2_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_Text2direction_test_6() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			int Text2_Textdirection = Grade.Text2.getTextDirection();
			assertNotEquals(0, Text2_Textdirection);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_touchenabled_test_1() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			Object Text2 = Grade.Text2;
			assertNotNull(Text2);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_touchenabled_test_2() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_touchenabled = Grade.Text2.getTouchEnabled();
			assertNotNull(Text2_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_touchenabled_test_3() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_touchenabled = Grade.Text2.getTouchEnabled();
			assertEquals(true, Text2_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void Text2_touchenabled_test_4() {
		GradeSWT Grade = new GradeSWT();
		try {
			Grade.createContents();
			boolean Text2_touchenabled = Grade.Text2.getTouchEnabled();
			assertNotEquals(false, Text2_touchenabled);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
}
