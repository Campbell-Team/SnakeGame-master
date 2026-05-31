package com.moon.snake;

import com.moon.obfuscate.annotation.Native;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class SnakeGame extends JFrame {

    private static final String GAME_TITLE = "Moon Snake - Obfuscation Test";
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 600;
    private static final int CELL_SIZE = 20;
    private static final int GRID_WIDTH = BOARD_WIDTH / CELL_SIZE;
    private static final int GRID_HEIGHT = BOARD_HEIGHT / CELL_SIZE;
    private static final int INITIAL_SPEED = 150;
    private static final float SPEED_INCREMENT = 0.95f;
    private static final int INITIAL_SNAKE_LENGTH = 3;
    private static final int FOOD_SCORE = 10;
    private static final int BONUS_SCORE = 50;
    private static final double BONUS_CHANCE = 0.15;
    private static final String COLOR_BG = "#1a1a2e";
    private static final String COLOR_SNAKE = "#00ff88";
    private static final String COLOR_FOOD = "#ff4444";
    private static final String COLOR_BONUS = "#ffdd00";
    private static final String COLOR_GRID = "#16213e";
    private static final String COLOR_TEXT = "#e94560";
    private static final String GAME_OVER_MSG = "Game Over!";
    private static final String RESTART_MSG = "Press SPACE or R to restart";
    private static final String SCORE_PREFIX = "Score: ";
    private static final String HIGH_SCORE_PREFIX = "Best: ";
    private static final String PAUSE_MSG = "PAUSED - Press P to resume";

    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private int score = 0;
    private int highScore = 0;
    private boolean gameRunning = false;
    private boolean gamePaused = false;

    public SnakeGame() {
        initUI();
    }

    private void initUI() {
        setTitle(GAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.decode(COLOR_BG));

        gamePanel = new GamePanel();
        scorePanel = new ScorePanel();

        mainPanel.add(scorePanel, BorderLayout.NORTH);
        mainPanel.add(gamePanel, BorderLayout.CENTER);

        add(mainPanel);
        pack();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e);
            }
        });

        setFocusable(true);
        startGame();
    }

    private void handleKeyPress(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            gamePanel.setDirection(Direction.UP);
        } else if (key == KeyEvent.VK_DOWN) {
            gamePanel.setDirection(Direction.DOWN);
        } else if (key == KeyEvent.VK_LEFT) {
            gamePanel.setDirection(Direction.LEFT);
        } else if (key == KeyEvent.VK_RIGHT) {
            gamePanel.setDirection(Direction.RIGHT);
        } else if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_R) {
            if (!gameRunning) {
                startGame();
            }
        } else if (key == KeyEvent.VK_P) {
            gamePaused = !gamePaused;
            gamePanel.repaint();
        }
    }

    private void startGame() {
        score = 0;
        gameRunning = true;
        gamePaused = false;
        scorePanel.updateScore(score, highScore);
        onSystem();
        gamePanel.resetGame();
    }

    private void gameOver() {
        gameRunning = false;
        if (score > highScore) {
            highScore = score;
        }
        scorePanel.updateScore(score, highScore);
        gamePanel.repaint();
    }

    public void addScore(int points) {
        score += points;
        scorePanel.updateScore(score, highScore);
    }

    public boolean isGameRunning() {
        return gameRunning;
    }

    public boolean isGamePaused() {
        return gamePaused;
    }

    @Native(library = "snakenative")
    public void onSystem(){
        System.out.println("Hello World");
        System.out.println("Hello World1");
        System.out.println("Hello World2");
        System.out.println("Hello World3");
        System.out.println("Hello World4");
    }

    // ========== Native-annotated methods for obfuscation testing ==========

    /**
     * Calculate a secure hash for score validation
     * This method is marked for native obfuscation
     */
    @Native(library = "snakenative")
    private int calculateScoreHash(int score, int seed) {
        int hash = seed;
        hash = hash * 31 + score;
        hash = hash * 17 + (score >> 8);
        hash = hash * 13 + (score << 3);
        hash = hash ^ (hash >>> 16);
        hash = hash * 0x85ebca6b;
        hash = hash ^ (hash >>> 13);
        hash = hash * 0xc2b2ae35;
        hash = hash ^ (hash >>> 16);
        return hash;
    }

    /**
     * Validate game state integrity
     * This method is marked for native obfuscation
     */
    @Native(library = "snakenative")
    private boolean validateGameState(int score, int highScore, long timestamp) {
        int checksum = score * 7 + highScore * 13;
        checksum += (int)(timestamp % 1000000);
        checksum = checksum ^ 0x5A827999;
        checksum = (checksum << 3) | (checksum >>> 29);
        return checksum != 0;
    }

    /**
     * Encrypt sensitive game data
     * This method is marked for native obfuscation
     */
    @Native(library = "snakenative")
    private byte[] encryptGameData(int[] data, int key) {
        byte[] result = new byte[data.length * 4];
        for (int i = 0; i < data.length; i++) {
            int encrypted = data[i] ^ key;
            encrypted = encrypted * 0x9e3779b9;
            encrypted = encrypted ^ (encrypted >>> 16);
            result[i * 4] = (byte)(encrypted >>> 24);
            result[i * 4 + 1] = (byte)(encrypted >>> 16);
            result[i * 4 + 2] = (byte)(encrypted >>> 8);
            result[i * 4 + 3] = (byte)(encrypted);
        }
        return result;
    }

    /**
     * Compute collision detection hash
     * This method is marked for native obfuscation
     */
    @Native(library = "snakenative")
    private int computeCollisionHash(int x, int y, int direction) {
        int hash = x * 73856093;
        hash ^= y * 19349663;
        hash ^= direction * 83492791;
        hash = hash ^ (hash >>> 16);
        hash = hash * 0x119de1f3;
        hash = hash ^ (hash >>> 13);
        hash = hash * 0x27d4eb2f;
        return hash;
    }

    /**
     * Generate secure random seed
     * This method is marked for native obfuscation
     */
    @Native(library = "snakenative")
    private long generateSecureSeed(long input) {
        long seed = input;
        seed = seed * 6364136223846793005L + 1442695040888963407L;
        seed = seed ^ (seed >>> 21);
        seed = seed * 0xda942042e4dd58b5L;
        seed = seed ^ (seed >>> 35);
        seed = seed * 0xd88320f39b7e5f5bL;
        seed = seed ^ (seed >>> 4);
        return seed;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SnakeGame game = new SnakeGame();
            game.setVisible(true);
        });
    }

    // Inner classes for better obfuscation testing

    enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    class GamePanel extends JPanel {
        private final ArrayList<Point> snake = new ArrayList<>();
        private Point food;
        private Point bonusFood;
        private boolean hasBonus = false;
        private Direction direction = Direction.RIGHT;
        private Direction nextDirection = Direction.RIGHT;
        private Timer timer;
        private int speed = INITIAL_SPEED;
        private final Random random = new Random();

        public GamePanel() {
            setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
            setBackground(Color.decode(COLOR_BG));
            resetGame();
        }

        public void resetGame() {
            snake.clear();
            int startX = GRID_WIDTH / 2;
            int startY = GRID_HEIGHT / 2;
            for (int i = 0; i < INITIAL_SNAKE_LENGTH; i++) {
                snake.add(new Point(startX - i, startY));
            }
            direction = Direction.RIGHT;
            nextDirection = Direction.RIGHT;
            speed = INITIAL_SPEED;
            hasBonus = false;
            bonusFood = null;
            spawnFood();

            if (timer != null) {
                timer.stop();
            }
            timer = new Timer(speed, e -> update());
            timer.start();
        }

        private void spawnFood() {
            int x, y;
            do {
                x = random.nextInt(GRID_WIDTH);
                y = random.nextInt(GRID_HEIGHT);
            } while (isSnakeAt(x, y));
            food = new Point(x, y);

            if (random.nextDouble() < BONUS_CHANCE) {
                int bx, by;
                do {
                    bx = random.nextInt(GRID_WIDTH);
                    by = random.nextInt(GRID_HEIGHT);
                } while (isSnakeAt(bx, by) || (bx == x && by == y));
                bonusFood = new Point(bx, by);
                hasBonus = true;
            } else {
                hasBonus = false;
            }
        }

        private boolean isSnakeAt(int x, int y) {
            for (Point p : snake) {
                if (p.x == x && p.y == y) return true;
            }
            return false;
        }

        public void setDirection(Direction dir) {
            if (dir == Direction.UP && direction != Direction.DOWN) nextDirection = dir;
            else if (dir == Direction.DOWN && direction != Direction.UP) nextDirection = dir;
            else if (dir == Direction.LEFT && direction != Direction.RIGHT) nextDirection = dir;
            else if (dir == Direction.RIGHT && direction != Direction.LEFT) nextDirection = dir;
        }

        private void update() {
            if (!isGameRunning() || isGamePaused()) {
                repaint();
                return;
            }

            direction = nextDirection;
            Point head = snake.get(0);
            int newX = head.x;
            int newY = head.y;

            switch (direction) {
                case UP: newY--; break;
                case DOWN: newY++; break;
                case LEFT: newX--; break;
                case RIGHT: newX++; break;
            }

            // Wall collision
            if (newX < 0 || newX >= GRID_WIDTH || newY < 0 || newY >= GRID_HEIGHT) {
                gameOver();
                return;
            }

            // Self collision
            if (isSnakeAt(newX, newY)) {
                gameOver();
                return;
            }

            snake.add(0, new Point(newX, newY));

            // Check food
            boolean ate = false;
            if (newX == food.x && newY == food.y) {
                addScore(FOOD_SCORE);
                ate = true;
                spawnFood();
                speed = (int) (speed * SPEED_INCREMENT);
                timer.setDelay(speed);
            } else if (hasBonus && bonusFood != null && newX == bonusFood.x && newY == bonusFood.y) {
                addScore(BONUS_SCORE);
                ate = true;
                hasBonus = false;
                bonusFood = null;
            }

            if (!ate) {
                snake.remove(snake.size() - 1);
            }

            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw grid
            g2d.setColor(Color.decode(COLOR_GRID));
            for (int x = 0; x <= GRID_WIDTH; x++) {
                g2d.drawLine(x * CELL_SIZE, 0, x * CELL_SIZE, BOARD_HEIGHT);
            }
            for (int y = 0; y <= GRID_HEIGHT; y++) {
                g2d.drawLine(0, y * CELL_SIZE, BOARD_WIDTH, y * CELL_SIZE);
            }

            // Draw snake
            for (int i = 0; i < snake.size(); i++) {
                Point p = snake.get(i);
                if (i == 0) {
                    g2d.setColor(Color.decode(COLOR_SNAKE));
                } else {
                    int alpha = 255 - (i * 5);
                    if (alpha < 100) alpha = 100;
                    g2d.setColor(new Color(0, 255, 136, alpha));
                }
                g2d.fillRoundRect(p.x * CELL_SIZE + 1, p.y * CELL_SIZE + 1,
                        CELL_SIZE - 2, CELL_SIZE - 2, 4, 4);
            }

            // Draw food
            if (food != null) {
                g2d.setColor(Color.decode(COLOR_FOOD));
                g2d.fillOval(food.x * CELL_SIZE + 2, food.y * CELL_SIZE + 2,
                        CELL_SIZE - 4, CELL_SIZE - 4);
            }

            // Draw bonus food
            if (hasBonus && bonusFood != null) {
                g2d.setColor(Color.decode(COLOR_BONUS));
                int cx = bonusFood.x * CELL_SIZE + CELL_SIZE / 2;
                int cy = bonusFood.y * CELL_SIZE + CELL_SIZE / 2;
                int r = CELL_SIZE / 2 - 2;
                int[] xPoints = {cx, cx - r, cx + r};
                int[] yPoints = {cy - r, cy + r, cy + r};
                g2d.fillPolygon(xPoints, yPoints, 3);
            }

            // Draw game over overlay
            if (!isGameRunning()) {
                g2d.setColor(new Color(0, 0, 0, 150));
                g2d.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

                g2d.setColor(Color.decode(COLOR_TEXT));
                g2d.setFont(new Font("Arial", Font.BOLD, 48));
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(GAME_OVER_MSG);
                g2d.drawString(GAME_OVER_MSG, (BOARD_WIDTH - textWidth) / 2, BOARD_HEIGHT / 2 - 20);

                g2d.setFont(new Font("Arial", Font.PLAIN, 18));
                fm = g2d.getFontMetrics();
                textWidth = fm.stringWidth(RESTART_MSG);
                g2d.drawString(RESTART_MSG, (BOARD_WIDTH - textWidth) / 2, BOARD_HEIGHT / 2 + 30);
            }

            // Draw pause overlay
            if (isGamePaused()) {
                g2d.setColor(new Color(0, 0, 0, 120));
                g2d.fillRect(0, 0, BOARD_WIDTH, BOARD_HEIGHT);

                g2d.setColor(Color.decode(COLOR_BONUS));
                g2d.setFont(new Font("Arial", Font.BOLD, 36));
                FontMetrics fm = g2d.getFontMetrics();
                int textWidth = fm.stringWidth(PAUSE_MSG);
                g2d.drawString(PAUSE_MSG, (BOARD_WIDTH - textWidth) / 2, BOARD_HEIGHT / 2);
            }
        }
    }

    class ScorePanel extends JPanel {
        private JLabel scoreLabel;
        private JLabel highScoreLabel;

        public ScorePanel() {
            setPreferredSize(new Dimension(BOARD_WIDTH, 40));
            setBackground(Color.decode(COLOR_BG));
            setLayout(new FlowLayout(FlowLayout.CENTER, 50, 8));

            scoreLabel = new JLabel(SCORE_PREFIX + "0");
            scoreLabel.setFont(new Font("Arial", Font.BOLD, 20));
            scoreLabel.setForeground(Color.decode(COLOR_SNAKE));

            highScoreLabel = new JLabel(HIGH_SCORE_PREFIX + "0");
            highScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
            highScoreLabel.setForeground(Color.decode(COLOR_BONUS));

            add(scoreLabel);
            add(highScoreLabel);
        }

        public void updateScore(int currentScore, int bestScore) {
            scoreLabel.setText(SCORE_PREFIX + currentScore);
            highScoreLabel.setText(HIGH_SCORE_PREFIX + bestScore);
        }
    }
}
