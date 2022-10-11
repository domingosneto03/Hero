import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    boolean GameRunning = true;
    private Screen screen;
    private Arena arena;


    public Game() {
        try {
            arena = new Arena(40,20);
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();

    }

    private void processKey(KeyStroke key) {
        arena.processKey(key);
    }

    public void run() throws IOException {
        while(GameRunning) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            KeyType keyType = key.getKeyType();
            if (keyType == KeyType.EOF) {
                GameRunning = false;
            } else if (keyType == KeyType.Character) {
                if (key.getCharacter() == 'q') {
                    screen.close();
                    GameRunning = false;
                }
            }
            arena.moveMonsters();
            if(arena.verifyMonsterCollisions()){
                screen.close();
                break;
            }
        }

    }

}
