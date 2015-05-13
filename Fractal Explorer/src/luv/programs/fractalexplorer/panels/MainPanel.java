package luv.programs.fractalexplorer.panels;

import luv.values.colorizers.Colorizer;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.event.MouseInputListener;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import luv.graphics.video.avi.AVIOutputStream;
import luv.opencl.OpenCL;
import luv.programs.fractalexplorer.ConfigManager;
import luv.programs.fractalexplorer.Registry;
import luv.values.Configurable;
import luv.values.animators.Animator;
import luv.values.animators.SinglePropertyAnimator;
import luv.values.generators.ValueGenerator;
import luv.values.generators.fractals.flame.Flame;
import luv.values.mappers.ValueBag;

public class MainPanel extends RecursivelyRepaintingPanel implements KeyListener,
        MouseInputListener, MouseWheelListener, ComponentListener {

    private static final long serialVersionUID = 1L;

    final int FRAMES = 30;
    final Timer timer = new Timer();
    final int period = 200; // milliseconds
    boolean animationActive = false;

    private final List<Animator> animators;

    private final List<Colorizer> colorizers;
    private final List<ValueGenerator> fractals;
    private ValueBag selectedFractals;

    private final Frame window;
    private final ToolBox toolBox;
    private final ValueTree valueTree;
    private final ImagePanel imagePanel;
    private final JScrollPane scrollPane;

    AVIOutputStream videoStream = null;

    OpenCL openCL;
    Registry registry;    

    public MainPanel(Frame window, int threadCount, ConfigManager configManager) {
        this.window = window;

        try {
            openCL = new OpenCL();
        } catch (Exception ex) {
            openCL = null;
        }
        registry = new Registry();

        colorizers = Arrays.asList(registry.getColorizers(openCL));
        Colorizer selectedColorizer = configManager.getSelectedColorizer() < colorizers.size() ? colorizers.get(configManager.getSelectedColorizer()) : colorizers.get(0);

        fractals = Arrays.asList(registry.getGenerators(openCL));
        selectedFractals = new ValueBag(configManager.getSelectedFractal() < fractals.size() ? fractals.get(configManager.getSelectedFractal()) : fractals.get(0));

        animators = new ArrayList<>();
        
        setLayout(new BorderLayout());

        imagePanel = new ImagePanel(this, configManager.getFractalX(), configManager.getFractalY(), configManager.getFractalZ());
        this.add(imagePanel, BorderLayout.CENTER);

        toolBox = new ToolBox(this);
        this.add(toolBox, BorderLayout.NORTH);

        valueTree = new ValueTree(this, selectedColorizer, selectedFractals);
        scrollPane = new JScrollPane(valueTree);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.WEST);

        timerGo();
    }

    public void updateAnimation() {
        if (!animationActive) {
            timerGo();
            return;
        }

        boolean progress = false;

        for (Animator animator : animators) {
            if (animator.animate()) {
                progress = true;
            }
        }

        if (progress) {
            forceRecursiveRepaint();
        }

        timerGo();
    }

    private void timerGo() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                updateAnimation();
            }
        }, period);
    }

    @Override
    public void forceRecursiveRepaint() {
        imagePanel.clearImage();

        toolBox.forceRecursiveRepaint();
        valueTree.forceRecursiveRepaint();

        super.forceRecursiveRepaint();

    }

    public int getColorizerId() {
        return colorizers.indexOf(valueTree.getColorizer());
    }

    public int getFractalId() {
        return fractals.indexOf(selectedFractals.get().get(0));
    }

    public double getFractalX() {
        return imagePanel.getFractalX();
    }

    public double getFractalY() {
        return imagePanel.getFractalY();
    }

    public double getFractalZ() {
        return imagePanel.getFractalZ();
    }

    @Override
    public void componentResized(ComponentEvent resizeEvent) {
        forceRecursiveRepaint();
    }

    @Override
    public void componentHidden(ComponentEvent arg0) {
    }

    @Override
    public void componentMoved(ComponentEvent arg0) {
    }

    @Override
    public void componentShown(ComponentEvent arg0) {
    }

    public void saveImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.addChoosableFileFilter(new FileFilter() {

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().toLowerCase().endsWith(".png");
            }

            @Override
            public String getDescription() {
                return "PNG Image (*.png)";
            }
        });

        int returnVal = fileChooser.showSaveDialog(window);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                ImageIO.write(imagePanel.getImage(), "png", file);
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setColorizer(Colorizer selectedItem) {
        valueTree.setColorizer(selectedItem);
    }

    public void setFractal(ValueGenerator selectedItem) {
        if (selectedItem == null) {
            return;
        }
        this.setFractal(new ValueBag(selectedItem));
    }

    public void setFractal(ValueBag selectedItem) {
        if (selectedItem == null) {
            return;
        }
        selectedFractals = selectedItem;
        valueTree.setRoot(selectedFractals);
    }

    boolean animationActive() {
        return animationActive;
    }

    void toogleAnimation() {
        animationActive = !animationActive;
    }

    void startRecording() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("AVI Video (*.avi)", ".avi"));

        int returnVal = fileChooser.showSaveDialog(window);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                if (!file.getName().endsWith(".avi")) {
                    file = new File(file.getAbsoluteFile() + ".avi");
                }
                videoStream = new AVIOutputStream(file, AVIOutputStream.VideoFormat.PNG, 24);
                videoStream.setVideoCompressionQuality(1.0f);

                videoStream.setTimeScale(1);
                videoStream.setFrameRate(FRAMES);
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    boolean recordingActive() {
        return videoStream != null;
    }

    void toogleRecording() {
        if (videoStream == null) {
            startRecording();
        } else {
            final AVIOutputStream stream = videoStream;
            videoStream = null;

            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    try {
                        stream.close();
                    } catch (IOException ex) {
                        Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }, 2000);
        }
    }

    public void toogleAnimator(Configurable configurable, String propertyName) {
        SinglePropertyAnimator animator = new SinglePropertyAnimator(configurable, propertyName);
        if (animators.contains(animator)) {
            animators.remove(animator);
        } else {
            animators.add(animator);
        }
    }

    public List<Configurable> getValueMappers() {
        return Arrays.asList((Configurable[]) registry.getMappers(openCL));
    }

    public List<Configurable> getValueGenerators() {
        return Arrays.asList((Configurable[]) registry.getGenerators(openCL));
    }

    public List<Configurable> getColorizers() {
        return Arrays.asList((Configurable[]) registry.getColorizers(openCL));
    }

    public List<ValueBag> getExamples() {
        return Arrays.asList(registry.getExamples(openCL));
    }

    public Colorizer getSelectedColorizer() {
        return valueTree.getColorizer();
    }

    public ValueBag getSelectedFractals() {
        return selectedFractals;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        imagePanel.mouseClicked(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        imagePanel.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        imagePanel.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        imagePanel.mouseEntered(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        imagePanel.mouseExited(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        imagePanel.mouseDragged(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        imagePanel.mouseMoved(e);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        imagePanel.mouseWheelMoved(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        imagePanel.keyTyped(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        imagePanel.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        final int keyCode = e.getKeyCode();

        switch (keyCode) {
            case KeyEvent.VK_T:
                toolBox.setVisible(!toolBox.isVisible());
                scrollPane.setVisible(!scrollPane.isVisible());
                forceRecursiveRepaint();
                break;
        }

        imagePanel.keyReleased(e);
    }

    public void resetPosition() {
        imagePanel.resetPosition();
    }

    public void loadDefaultExamples() {
        this.setFractal((ValueBag) new ValueBagSelector(getExamples()).getResult());
    }

    void updateVideo(BufferedImage image) {
        if (videoStream != null) {
            try {
                videoStream.writeFrame(image);
            } catch (IOException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
	public void saveExampleToFile() {
		final JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            final String fileName = fc.getSelectedFile().getAbsolutePath();
            Registry.saveExample(this.getSelectedFractals(), fileName);
        }
	}

	public void loadExampleFromFile() {
		final JFileChooser fc = new JFileChooser();
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            final String fileName = fc.getSelectedFile().getAbsolutePath();
            final ValueBag fractal = Registry.loadExample(fileName);
            //fractal.setOpenCL(openCL); Nullpointerexcpetion
            this.setFractal(fractal);
        }
	}
}
