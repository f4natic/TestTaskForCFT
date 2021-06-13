import org.assertj.core.api.Assertions;
import org.assertj.swing.core.GenericTypeMatcher;
import org.assertj.swing.core.MouseButton;
import org.assertj.swing.core.Robot;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.junit.*;
import ru.ftc.test.candidate.View;

import javax.swing.*;

public class ApplicationButtonsTest {
    private View frame;
    private FrameFixture window;
    private static Robot robot;
    private JButtonFixture click;

    @BeforeClass
    public static void setUpOnce(){
        FailOnThreadViolationRepaintManager.install();
//        robot =
    }

    @Before
    public void setUp() {
        FailOnThreadViolationRepaintManager.install();
        View frame = GuiActionRunner.execute(() -> new View());
        window = new FrameFixture(frame);
        window.show();
    }

    @After
    public void destruct() {
        window.cleanUp();
    }

    @Test
    public void testButtonsNamesAndValues() throws Exception {
        for(int i = 0; i < 10; i++) {
            mouseLeftClick(String.valueOf(i));
        }
        String line = window.textBox().text();
        Assert.assertEquals("Button check","0123456789", line);
    }

    @Test
    public void testDivisionOperation() throws Exception {
        mouseLeftClick("2");
        mouseLeftClick("/");
        mouseLeftClick("1");
        mouseLeftClick("=");
        String line = window.textBox().text();
        Assert.assertEquals("Division","2", line);
    }

    @Test
    public void testDivisionByZero() throws Exception{
        mouseLeftClick("1");
        mouseLeftClick("/");
        mouseLeftClick("0");
        mouseLeftClick("=");
        String line = window.textBox().text();
        Assert.assertEquals("Division by zero","NaN", line);
    }

//    @Test
//    public void testDoubleClickOnEqualsSign() {
//        try{
//            mouseLeftClick("=");
//            mouseLeftClick("=");
//        }catch (Exception e) {
//            Assertions.assertThatExceptionOfType(Exception.class);
//        }
//    }

    @Test
    public void testDoubleClickOnOperationButton() throws Exception{
        mouseLeftClick("1");
        mouseLeftClick("/");
        mouseLeftClick("+");
        mouseLeftClick("2");
        mouseLeftClick("=");
        String line = window.textBox().text();
        Assert.assertEquals("Result","3", line);
    }

    public void mouseLeftClick(String num) throws Exception{
        window.button(new GenericTypeMatcher<JButton>(JButton.class) {
            @Override
            protected boolean isMatching(JButton jButton) {
                if(jButton.getText().equals(num)) return true;
                return false;
            }
        }).click(MouseButton.LEFT_BUTTON);
    }
}