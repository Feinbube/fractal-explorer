package luv.programs.fractalexplorer.panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;
import luv.values.generators.ValueGenerator;
import luv.values.generators.fractals.flame.*;

public class ImagePanel extends JPanel implements MouseInputListener, MouseWheelListener, KeyListener {

    MainPanel mainPanel;

    private boolean zooming = false;
    private int mouseStartX;
    private int mouseStartY;
    private int mouseCurrentX;
    private int mouseCurrentY;

    final double XY_FACTOR = 0.1;
    final double Z_FACTOR = 0.8;

    private double x;
    private double y;
    private double z;

    private BufferedImage image;

    Flame flame = new FlameSet();
    
    ImagePanel(MainPanel mainPanel, double fractalX, double fractalY, double fractalZ) {
        this.mainPanel = mainPanel;
        this.x = fractalX;
        this.y = fractalY;
        this.z = fractalZ;
    }
    
    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

        if (image == null) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            image = mainPanel.getSelectedColorizer().getImage(
                    x - this.getSize().width / 2 * z,
                    y - this.getSize().height / 2 * z,
                    x + this.getSize().width / 2 * z,
                    y + this.getSize().height / 2 * z,
                    this.getSize().width, this.getSize().height, mainPanel.getSelectedFractals().get().toArray(new ValueGenerator[]{}));
            
            // THIS ACTIVATES FRACTAL FLAMES MODE
            /* image = flame.getImage(this.getSize().width, this.getSize().height); */
            setCursor(Cursor.getDefaultCursor());
        }
        g2.drawImage(image, 0, 0, null);
        mainPanel.updateVideo(this.image);

        if (zooming) {
            g2.setColor(Color.WHITE);
            g2.drawRect((int) Math.min(mouseStartX, mouseCurrentX), (int) Math.min(mouseStartY, mouseCurrentY),
                    (int) Math.abs(mouseStartX - mouseCurrentX), (int) Math.abs(mouseStartY - mouseCurrentY));
            g2.setColor(Color.BLACK);
            g2.drawRect((int) Math.min(mouseStartX, mouseCurrentX) + 1, (int) Math.min(mouseStartY, mouseCurrentY) + 1,
                    (int) Math.abs(mouseStartX - mouseCurrentX) - 2, (int) Math.abs(mouseStartY - mouseCurrentY) - 2);
        }
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
        zooming = false;

        if (Math.abs(mouseStartX - mouseCurrentX) + Math.abs(mouseStartY - mouseCurrentY) < 10) {
            return;
        }

        x += (mouseStartX + mouseCurrentX - this.getSize().width) / 2 * z;
        y += (mouseStartY + mouseCurrentY - this.getSize().height) / 2 * z;

        double rx = Math.abs(mouseStartX - mouseCurrentX) / (double) this.getSize().width;
        double ry = Math.abs(mouseStartY - mouseCurrentY) / (double) this.getSize().height;

        z *= Math.max(rx, ry);

        mainPanel.forceRecursiveRepaint();
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (zooming == false && mouseEvent.getButton() == MouseEvent.BUTTON1) {
            zooming = true;
            mouseStartX = mouseEvent.getLocationOnScreen().x - this.getLocationOnScreen().x;
            mouseStartY = mouseEvent.getLocationOnScreen().y - this.getLocationOnScreen().y;
            mouseCurrentX = mouseStartX;
            mouseCurrentY = mouseStartY;
        }

        mouseMoved(mouseEvent);
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        mouseCurrentX = mouseEvent.getLocationOnScreen().x - this.getLocationOnScreen().x;
        mouseCurrentY = mouseEvent.getLocationOnScreen().y - this.getLocationOnScreen().y;
        if (zooming) {
            this.update(getGraphics());
        }
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        mouseMoved(mouseEvent);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mouseWheelEvent) {

        double oldZ = z;

        if (mouseWheelEvent.getWheelRotation() < 0) {
            z *= Z_FACTOR;
        } else {
            z /= Z_FACTOR;
        }

        x = x + (this.getSize().width / 2.0 - mouseCurrentX) * (z - oldZ);
        y = y + (this.getSize().height / 2.0 - mouseCurrentY) * (z - oldZ);

        mainPanel.forceRecursiveRepaint();
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    public double getFractalX() {
        return x;
    }

    public double getFractalY() {
        return y;
    }

    public double getFractalZ() {
        return z;
    }

    BufferedImage getImage() {
        return image;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        final int keyCode = keyEvent.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_RIGHT:
                x += XY_FACTOR * this.getSize().width * z;
                mainPanel.forceRecursiveRepaint();
                break;
            case KeyEvent.VK_LEFT:
                x -= XY_FACTOR * this.getSize().width * z;
                mainPanel.forceRecursiveRepaint();
                break;
            case KeyEvent.VK_UP:
                y -= XY_FACTOR * this.getSize().height * z;
                mainPanel.forceRecursiveRepaint();
                break;
            case KeyEvent.VK_DOWN:
                y += XY_FACTOR * this.getSize().height * z;
                mainPanel.forceRecursiveRepaint();
                break;
            case KeyEvent.VK_SPACE:
                resetPosition();
                flame.randomFlame();
                break;
        }

        if (keyEvent.getModifiersEx() == KeyEvent.CTRL_DOWN_MASK) {
            if (keyCode == KeyEvent.VK_PLUS) {
                z *= Z_FACTOR;
                mainPanel.forceRecursiveRepaint();
            } else if (keyCode == KeyEvent.VK_MINUS) {
                z /= Z_FACTOR;
                mainPanel.forceRecursiveRepaint();
            }
        }
        assert (z > 0);
    }

    public void resetPosition() {
        x = 0;
        y = 0;
        z = 0.01;

        mainPanel.forceRecursiveRepaint();
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    void clearImage() {
        this.image = null;
    }
}
