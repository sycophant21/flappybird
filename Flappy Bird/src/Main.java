import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.concurrent.LinkedBlockingDeque;

public class Main {
    private static JFrame jFrame = new JFrame();
    public static void main(String[] args) {
        Initializer initializer = new Initializer(600,600);
        Rectangle bird = initializer.getBird();
        Deque<Rectangle> obstacles = new LinkedBlockingDeque<>();
        jFrame = initializer.initializer(jFrame, bird, obstacles);
        EventHandler eventHandler = new EventHandler(initializer.getRenderer(), obstacles, bird);
        jFrame.addMouseListener(eventHandler);
        jFrame.addKeyListener(eventHandler);

    }
}