import javax.swing.*;
import java.awt.*;
import java.util.Deque;
import java.util.Random;

public class Renderer extends JPanel {
    private final int width;
    private final int height;
    private final Rectangle bird;
    private final Deque<Rectangle> obstacles;

    public Renderer(int width, int height, Rectangle bird, Deque<Rectangle> obstacles) {
        this.width = width;
        this.height = height;
        this.bird = bird;
        this.obstacles = obstacles;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintGround(g);
        paintBird(g);
        paintObstacles(g);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        if (GameOver.getStop() && GameOver.getStart()) {
            g.drawString("Game Over", width / 2 - 10, height / 2 - 10);
        }
    }


    private void paintBackground(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(0, 0, width, height);
    }

    private void paintGround(Graphics g) {
        g.setColor(Color.orange);
        g.fillRect(0, height - 100, width, 100);

        g.setColor(Color.green);
        g.fillRect(0, height - 120, width, 20);
    }

    private void paintBird(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(bird.x, bird.y, bird.width, bird.height);
    }

    private void paintObstacles(Graphics g) {
        int height = 60 + new Random().nextInt(300);
        if (obstacles.isEmpty()) {
            obstacles.add(new Rectangle(9 * width / 10, height + 60, width / 15, 420 - height));
            obstacles.add(new Rectangle(9 * width / 10, 0, width / 15, height - 60));
        } else {
            Rectangle obstacle = obstacles.getLast();
            obstacles.add(new Rectangle(getSpace() + obstacle.x, height + 60, obstacle.width, 420 - height));
            obstacles.add(new Rectangle(getSpace() + obstacle.x, 0, obstacle.width, height - 60));
        }
        for (Rectangle obstacle : obstacles) {
            fillRectangles(obstacle, g, Color.GREEN.darker().darker());
        }
    }

    private void fillRectangles(Rectangle obstacle, Graphics g, Color color) {
        g.setColor(color);
        g.fillRect(obstacle.x, obstacle.y, obstacle.width, obstacle.height);
    }

    private int getSpace() {
        int space;
        if (obstacles.size() < 50) {
            space = 600;
        } else if (obstacles.size() < 100) {
            space = 500;
        } else if (obstacles.size() < 150) {
            space = 400;
        } else if (obstacles.size() < 200) {
            space = 300;
        } else if (obstacles.size() < 250) {
            space = 200;
        } else if (obstacles.size() < 300) {
            space = 100;
        } else {
            space = 85;
        }
        return space;
    }


}
