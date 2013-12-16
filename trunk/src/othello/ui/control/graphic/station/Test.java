package othello.ui.control.graphic.station;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author Hien Hoang
 */

class TestRoomStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RoomStation levelStation = new RoomStation();
        testFrame.add(levelStation);
        testFrame.setVisible(true);
    }
}

class TestLoginStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LoginStation levelStation = new LoginStation();
        testFrame.add(levelStation);
        testFrame.setVisible(true);
    }
}

class TestConfigStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ConfigStation levelStation = new ConfigStation();
        testFrame.add(levelStation);
        testFrame.setVisible(true);
    }
}

class TestLevelStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        LevelStation levelStation = new LevelStation();
        testFrame.add(levelStation);
        testFrame.setVisible(true);
    }
}
class TestPlayFirstStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayFirstStation levelStation = new PlayFirstStation();
        testFrame.add(levelStation);
        testFrame.setVisible(true);
    }
}
public class Test {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        testFrame.setVisible(true);
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OButton button = new OButton();
        button.setText("OFFLINE");
        testFrame.add(button);
        
    }
}

class TestMainStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainStation mainStation = new MainStation();
        testFrame.add(mainStation);
        testFrame.setVisible(true);
    }
}
class TestPlayWithStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
//        testFrame.setUndecorated(true);
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PlayWithStation playWithStation = new PlayWithStation();
        testFrame.add(playWithStation);
        testFrame.setVisible(true); 
//        Shape shape = new RoundRectangle2D.Double(0, 0, 300, 500, 25, 25);
//        AWTUtilities.setWindowShape(testFrame, shape);
        
        
    }
}
