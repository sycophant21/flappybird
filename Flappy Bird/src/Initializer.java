import javax.swing.*;
import java.awt.*;
import java.util.Deque;

public class Initializer {
    private final int width;
    private final int height;
    private Renderer renderer;

    public Initializer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public JFrame initializer(JFrame jFrame, Rectangle bird, Deque<Rectangle> obstacles) {
        renderer = new Renderer(width, height, bird, obstacles);
        jFrame.add(renderer);
        jFrame.setTitle("Flappy Bird");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(width, height);
        jFrame.setResizable(false);
        jFrame.setVisible(true);
        return jFrame;
    }

    public Rectangle getBird() {
        return new Rectangle(((width / 2) - 10) / 3, (height / 2) - 10, 20, 20);
    }

    public Renderer getRenderer() {
        return renderer;
    }
}
