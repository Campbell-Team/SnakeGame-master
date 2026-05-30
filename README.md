# SnakeGame

> 经典贪吃蛇游戏 - 用于测试 MoonObfuscate 混淆器

[![GPL v3.0 License](https://img.shields.io/badge/License-GPL%20v3.0-blue.svg)](LICENSE)
[![Java Version](https://img.shields.io/badge/Java-17+-green.svg)](https://www.java.com/)

## 📋 项目简介

SnakeGame 是一个使用 Java Swing 开发的经典贪吃蛇游戏。本项目主要用于演示和测试 MoonObfuscate 混淆器的混淆效果，展示混淆前后程序的功能一致性。

### 游戏特性

- 🎮 经典贪吃蛇玩法
- 🎨 现代化图形界面
- 🏆 实时分数显示
- ⚡ 流畅的游戏体验
- 🎯 多种难度级别
- 🎵 音效支持（可选）

## 👥 作者与团队

**作者**: SandRone

**团队**: Campbell Team

## 📖 使用方法

### 快速开始

1. **下载游戏 JAR 文件**
   ```bash
   # 从 target 目录获取构建好的 JAR
   ```

2. **运行游戏**
   ```bash
   java -jar snake-game-1.0.0.jar
   ```
   或直接双击 JAR 文件运行

### 游戏控制

| 按键 | 功能 |
|------|------|
| ↑ / W | 向上移动 |
| ↓ / S | 向下移动 |
| ← / A | 向左移动 |
| → / D | 向右移动 |
| 空格键 | 暂停/继续 |
| ESC | 退出游戏 |
| R | 重新开始 |

### 游戏规则

1. 控制蛇移动，吃掉红色食物
2. 每吃一个食物，蛇身增长一节，得分增加
3. 撞墙或撞到自己身体则游戏结束
4. 游戏速度会逐渐加快

### 测试混淆效果

本项目包含多种混淆测试版本：

| 文件名 | 描述 |
|--------|------|
| `snake-game-1.0.0-obfuscated.jar` | 完全混淆版本 |
| `test-StringEncryption.jar` | 字符串加密测试 |
| `test-Renamer.jar` | 重命名测试 |
| `test-ControlFlow.jar` | 控制流混淆测试 |
| `test-GarbageCode.jar` | 垃圾代码测试 |
| `test-AntiDebug.jar` | 反调试测试 |
| `test-combo.jar` | 组合混淆测试 |

## 🔨 构建方法

### 环境要求

- JDK 17 或更高版本
- Maven 3.6+

### 构建步骤

1. **进入项目目录**
   ```bash
   cd E:\SnakeGame
   ```

2. **使用 Maven 构建**
   ```bash
   mvn clean package
   ```

3. **运行游戏**
   ```bash
   java -jar target/snake-game-1.0.0.jar
   ```

### 开发构建

```bash
# 清理并编译
mvn clean compile

# 运行测试
mvn test

# 打包 JAR（包含依赖）
mvn clean package shade:shade

# 直接运行
mvn exec:java -Dexec.mainClass="com.moon.snake.SnakeGame"
```

### Maven 配置

项目使用标准 Maven 配置：
- Java 17 编译目标
- 自动包含主类清单
- 支持 Maven Shade 插件打包

## 💻 代码演示

### 核心游戏逻辑

**SnakeGame.java 主类结构:**

```java
package com.moon.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SnakeGame extends JFrame {
    
    // 游戏常量
    private static final int TILE_SIZE = 25;
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int DELAY = 100; // 毫秒
    
    // 游戏状态
    private enum GameState { PLAYING, PAUSED, GAME_OVER }
    private GameState currentState = GameState.PLAYING;
    
    // 蛇的方向
    private enum Direction { UP, DOWN, LEFT, RIGHT }
    private Direction currentDirection = Direction.RIGHT;
    
    // 游戏组件
    private GamePanel gamePanel;
    private ScorePanel scorePanel;
    private Timer gameTimer;
    
    public SnakeGame() {
        setTitle("贪吃蛇游戏 - SnakeGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // 初始化游戏面板
        gamePanel = new GamePanel();
        scorePanel = new ScorePanel();
        
        // 布局
        setLayout(new BorderLayout());
        add(scorePanel, BorderLayout.NORTH);
        add(gamePanel, BorderLayout.CENTER);
        
        // 键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode());
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        
        // 启动游戏循环
        startGame();
    }
    
    private void startGame() {
        // 初始化蛇和食物
        // 启动定时器
        gameTimer = new Timer(DELAY, e -> gameLoop());
        gameTimer.start();
    }
    
    private void gameLoop() {
        if (currentState != GameState.PLAYING) return;
        
        // 移动蛇
        moveSnake();
        
        // 检查碰撞
        checkCollision();
        
        // 检查食物
        checkFood();
        
        // 重绘
        gamePanel.repaint();
    }
    
    private void moveSnake() {
        // 根据当前方向移动蛇头
        switch (currentDirection) {
            case UP:    /* 向上移动 */ break;
            case DOWN:  /* 向下移动 */ break;
            case LEFT:  /* 向左移动 */ break;
            case RIGHT: /* 向右移动 */ break;
        }
    }
    
    private void handleKeyPress(int keyCode) {
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (currentDirection != Direction.DOWN)
                    currentDirection = Direction.UP;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (currentDirection != Direction.UP)
                    currentDirection = Direction.DOWN;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                if (currentDirection != Direction.RIGHT)
                    currentDirection = Direction.LEFT;
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                if (currentDirection != Direction.LEFT)
                    currentDirection = Direction.RIGHT;
                break;
            case KeyEvent.VK_SPACE:
                togglePause();
                break;
            case KeyEvent.VK_R:
                restartGame();
                break;
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
        }
    }
    
    private void togglePause() {
        if (currentState == GameState.PLAYING) {
            currentState = GameState.PAUSED;
            gameTimer.stop();
        } else if (currentState == GameState.PAUSED) {
            currentState = GameState.PLAYING;
            gameTimer.start();
        }
    }
    
    private void restartGame() {
        currentState = GameState.PLAYING;
        currentDirection = Direction.RIGHT;
        // 重置游戏状态...
        gameTimer.start();
    }
    
    private void checkCollision() {
        // 检查撞墙和撞自己
    }
    
    private void checkFood() {
        // 检查是否吃到食物
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SnakeGame());
    }
}
```

### 游戏面板绘制

```java
class GamePanel extends JPanel {
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        
        // 启用抗锯齿
        g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING, 
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        
        // 绘制背景
        g2d.setColor(new Color(40, 40, 40));
        g2d.fillRect(0, 0, getWidth(), getHeight());
        
        // 绘制网格（可选）
        drawGrid(g2d);
        
        // 绘制蛇
        drawSnake(g2d);
        
        // 绘制食物
        drawFood(g2d);
        
        // 绘制游戏状态
        if (gameOver) {
            drawGameOver(g2d);
        } else if (paused) {
            drawPaused(g2d);
        }
    }
    
    private void drawSnake(Graphics2D g2d) {
        // 绘制蛇身
        for (Point segment : snakeBody) {
            g2d.setColor(new Color(0, 200, 0));
            g2d.fillRoundRect(
                segment.x * TILE_SIZE, 
                segment.y * TILE_SIZE,
                TILE_SIZE - 2, 
                TILE_SIZE - 2,
                8, 8
            );
        }
        
        // 蛇头用不同颜色
        Point head = snakeBody.get(0);
        g2d.setColor(new Color(0, 255, 0));
        g2d.fillRoundRect(
            head.x * TILE_SIZE, 
            head.y * TILE_SIZE,
            TILE_SIZE - 2, 
            TILE_SIZE - 2,
            8, 8
        );
    }
    
    private void drawFood(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.fillOval(
            food.x * TILE_SIZE + 2,
            food.y * TILE_SIZE + 2,
            TILE_SIZE - 4,
            TILE_SIZE - 4
        );
    }
}
```

### 分数面板

```java
class ScorePanel extends JPanel {
    private JLabel scoreLabel;
    private int score = 0;
    private int highScore = 0;
    
    public ScorePanel() {
        setBackground(new Color(30, 30, 30));
        setPreferredSize(new Dimension(WIDTH, 40));
        
        scoreLabel = new JLabel("分数: 0 | 最高分: 0");
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
        add(scoreLabel);
    }
    
    public void updateScore(int newScore) {
        this.score = newScore;
        if (newScore > highScore) {
            highScore = newScore;
        }
        scoreLabel.setText(String.format("分数: %d | 最高分: %d", score, highScore));
    }
}
```

## ⚖️ GPL v3.0 开源协议

```
SnakeGame - 贪吃蛇游戏
Copyright (C) 2024 SandRone / Campbell Team

本程序是自由软件，您可以按照 GNU 通用公共许可证第三版
（GPL v3）的条款重新分发和/或修改它。

本程序希望能有所帮助，但不作任何保证；甚至不保证
其商品性或适用于特定用途。详情请参阅 GNU 通用公共许可证。

您应该已经收到了 GNU 通用公共许可证的副本；
如果没有，请参阅 <http://www.gnu.org/licenses/>。
```

完整的 GPL v3.0 协议文本请参阅 [LICENSE](LICENSE) 文件。

### 您的自由

根据 GPL v3.0 协议，您拥有以下自由：

1. **运行** - 出于任何目的自由运行本程序
2. **研究和修改** - 查看源代码，学习程序运作方式，并进行修改
3. **分发** - 自由分发本程序的副本
4. **分发修改版** - 分发您修改过的版本，造福整个社区

### 您必须遵守

- 分发时必须保留版权声明和本许可证
- 修改后的程序必须明确标注
- 分发的修改版本也必须使用 GPL v3.0 或更高版本开源

## 📜 Campbell Team 团队声明

```
================================================================================
                        CAMPBELL TEAM 官方声明
================================================================================

Campbell Team 是本项目的官方维护团队，负责项目的开发、维护和社区支持。

本团队声明：
1. 本项目由 SandRone 创建并维护，版权归作者所有
2. 本项目采用 GPL v3.0 开源协议开源
3. 欢迎社区贡献，但所有贡献内容将归入 Campbell Team 管理
4. 本团队保留对本项目进行任何修改的权利
5. 本团队不对使用本软件造成的任何直接或间接损失负责

联系方式：
- GitHub Issues: https://github.com/campbell-team/SnakeGame/issues
- 团队邮箱: campbell-team@example.com (示例地址)

================================================================================
                              SandRone 签名
================================================================================

作者：SandRone
团队：Campbell Team
邮箱： sandrone@example.com (示例地址)
网站： https://campbell-team.github.io

"游戏是编程的最佳练习场"

================================================================================
```

## 🎯 混淆测试说明

本项目设计用于测试 MoonObfuscate 混淆器的各种功能：

### 测试流程

1. **原始版本测试**
   ```bash
   java -jar target/snake-game-1.0.0.jar
   # 确认游戏正常运行
   ```

2. **混淆版本测试**
   ```bash
   java -jar target/snake-game-1.0.0-obfuscated.jar
   # 确认混淆后游戏功能一致
   ```

3. **反编译对比**
   - 使用 JD-GUI 或 FernFlower 反编译
   - 对比混淆前后代码差异
   - 验证混淆效果

### 验证清单

- [ ] 游戏能正常启动
- [ ] 键盘控制响应正常
- [ ] 蛇能正常移动和吃食物
- [ ] 碰撞检测正常工作
- [ ] 分数统计正确
- [ ] 暂停/继续功能正常
- [ ] 重新开始功能正常

## 🤝 贡献指南

欢迎提交 Issue 和 Pull Request！

1. Fork 本仓库
2. 创建您的特性分支 (`git checkout -b feature/AmazingFeature`)
3. 提交您的更改 (`git commit -m 'Add some AmazingFeature'`)
4. 推送到分支 (`git push origin feature/AmazingFeature`)
5. 创建 Pull Request

## 📄 许可证

本项目采用 [GPL v3.0](LICENSE) 开源许可证。

---

**© 2024 SandRone / Campbell Team. All Rights Reserved.**
