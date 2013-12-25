package othello.ui.control.graphic.station;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import othello.common.Piece;
import othello.models.Board;
import othello.models.Location;
import othello.models.Player;
import othello.ui.control.graphic.station.setting.GraphicStation;
import othello.ui.control.graphic.station.setting.SettingStation;
import othello.ui.control.graphic.station.setting.SoundStation;

/**
 *
 * @author Hien Hoang
 */
class TestOnOffButton {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        OnOffButton ofb = new OnOffButton();
        testFrame.add(ofb);
        testFrame.setVisible(true);
    }
}

class TestSoundStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SoundStation soundStation = new SoundStation();
        testFrame.add(soundStation);
        testFrame.setVisible(true);
    }
}

class TestSettingStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SettingStation settingStation = new SettingStation();
        testFrame.add(settingStation);
        testFrame.setVisible(true);
    }
}

class TestGraphicStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicStation graphicStation = new GraphicStation();
        testFrame.add(graphicStation);
        testFrame.setVisible(true);
    }
}

class TestTableStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        List<Board> boards = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Board board = new Board("board " + i);
            Player p1 = new Player();
            p1.setUsername("user" + i);
            p1.setType(1);
            p1.setScore(i+100);
            Player p2 = new Player();
            p2.setUsername("opp" + i);
            p2.setType(2);
            p2.setScore(i + 200);
            board.setPlayer(Piece.BLACK, p1);
            board.setPlayer(Piece.WHITE, p2);
            boards.add(board);
        }
        TableStation levelStation = new TableStation();
        levelStation.loadBoards(boards);
        testFrame.add(levelStation);
        testFrame.setVisible(true);
    }
}

class TestRoomStation {
    public static void main(String[] args) {
        JFrame testFrame = new JFrame();
        testFrame.setSize(300, 500);
        testFrame.setLayout(new BorderLayout());
        
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        List<Location> rooms = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Location room = new Location();
            room.id = "" + i;
            room.name = "Room " + i;
            room.numUsers = i;
            rooms.add(room);
        }
        
        RoomStation levelStation = new RoomStation();
        levelStation.loadRoomsList(rooms);
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
