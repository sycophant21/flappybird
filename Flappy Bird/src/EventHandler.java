import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Deque;

public class EventHandler implements ActionListener, MouseListener, KeyListener {
    private final Renderer renderer;
    private final Deque<Rectangle> obstacles;
    private final Rectangle bird;
    private Rectangle tempBird;

    public EventHandler(Renderer renderer, Deque<Rectangle> obstacles, Rectangle bird) {
        this.renderer = renderer;
        this.obstacles = obstacles;
        this.bird = bird;
        setTempBird();
        new Timer(20, this).start();
    }

    private void setTempBird() {
        tempBird = new Rectangle(bird.x, bird.y, bird.width, bird.height);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!GameOver.getStop()) {
            GameOver.setStart(true);
            for (Rectangle obstacle : obstacles) {
                obstacle.x -= 5;
                if (obstacle.intersects(bird)) {
                    intersection(obstacle, bird);
                    GameOver.setStop(true);
                    break;
                }
                if (obstacle.x + obstacle.width < 0) {
                    obstacles.remove(obstacle);
                }
            }
        }
        if (!GameOver.getStop()) {
            if (bird.y < 460) {
                bird.y += 5;
            } else {
                GameOver.setStop(true);
            }
        }
        renderer.repaint();
    }


    private void intersection(Rectangle obstacle, Rectangle bird) {
        if (getXConditions(obstacle, bird)) {
            if (obstacle.y > 0) {
                if (bird.y + bird.height <= obstacle.y) {
                    bird.y = obstacle.y - bird.height;
                }
            } else {
                if (bird.y >= obstacle.height) {
                    bird.y = obstacle.height;
                }
            }
        }

    }

    private boolean getXConditions(Rectangle obstacle, Rectangle bird) {
        if (bird.x >= obstacle.x && bird.x <= obstacle.x + obstacle.width) {
            return true;
        } else return bird.x + bird.width >= obstacle.x && bird.x + bird.width <= obstacle.x + obstacle.width;

    }



    @Override
    public void mouseClicked(MouseEvent e) {
        clicked();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void keyTyped(KeyEvent e) {
        clicked();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressed();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void clicked() {
        if (!GameOver.getStop()) {
            if (bird.y >= 125 && bird.y < 450) {
                bird.y -= 125;
            } else {
                bird.y = 0;
                GameOver.setStop(true);
            }
        }
    }

    private void pressed() {
        if (!GameOver.getStart()) {
            if (GameOver.getStop()) {
                GameOver.setStop(false);
            }
        } else {
            if (GameOver.getStop()) {
                obstacles.clear();
                bird.y = tempBird.y;
                bird.x = tempBird.x;
                GameOver.setStop(false);
            }
        }
    }
}
