import isel.leic.pg.Console;

import java.awt.event.KeyEvent;

public class Game2048 {
    static final int LINES = 4, COLS = 4;
    static final int MIN_VALUE = 2, MAX_VALUE = 2048;


    private static TopScores top = new TopScores();

    public static int points = 0;


    public static int moves = 0;

    private static boolean exit = false;
    private static int key;

    public static int[][] board = new int[LINES][COLS];

    //private static int l = LINES / 2, c = COLS / 2;


    private static int l = (int) (Math.random() * (LINES)), c = (int) (Math.random() * (COLS));

    private static int value = 16;

    public static void main(String[] args) {
        Panel.open();
        startGameEngine();
        Panel.close();
        top.saveToFile();
    }

    public static void startGameEngine() {
        init();
        for (; ; ) {
            key = Console.waitKeyPressed(0);
            if (!processKey(key)) break;
            while (Console.isKeyPressed(key)) ;
        }
    }

    private static void init() {
        Panel.showMessage("Use cursor keys to play");
        int aux = genNewValue();
        Panel.showTile(l, c, aux);
        board[l][c] = aux;
        genNewTile();//GENERATE NEW TILE != FROM ABOVE

        Panel.updateMoves(moves);
        Panel.updateScore(0);
        for (int i = 0; i < top.MAX_SCORES; i++) {
            Score s = top.getRow(i);
            if (s == null && i == 0) {
                Panel.updateBestRow(1, "----------", 0);
                break;
            } else {
                if (s == null) break;
                Panel.updateBestRow(i, s.getName(), s.getPoints());
            }

        }
    }

    private static int genNewValue() {
        if (Math.random() <= 0.1) return 4;
        return 2;
    }

    public static void genNewTile() {
        int l1 = (int) (Math.random() * (LINES));
        int c1 = (int) (Math.random() * (COLS));

        while (!isFree(l1, c1)) {
            l1 = (int) (Math.random() * (LINES));
            c1 = (int) (Math.random() * (COLS));
        }
        int var = genNewValue();
        Panel.showTile(l1, c1, var);
        board[l1][c1] = var;

    }

    private static boolean processKey(int key) {
        switch (key) {
            case KeyEvent.VK_UP:
                Moves.Upp();
                break;

            case KeyEvent.VK_DOWN:
                Moves.Down();
                break;

            case KeyEvent.VK_LEFT:
                Moves.Left();
                break;
            case KeyEvent.VK_RIGHT:
                Moves.Right();
                break;

            case KeyEvent.VK_R:
                restart();
                break;
            case KeyEvent.VK_ESCAPE:
            case 'Q':
                quitGame();
                break;

        }
        return !exit;
    }


    private static void restart() {
        int resp = Panel.questionChar("New Game (Y/N)");
        if (resp == 'Y') {
            for (int i = 0; i < LINES; i++) {
                for (int j = 0; j < COLS; j++) {
                    Panel.hideTile(i, j);


                }
            }
            moves = 0;
            points = 0;
        } else return;
        startGameEngine();
    }


    public static boolean isFull() {
        for (int i = 0; i < LINES; i++) {
            for (int j = 0; j < COLS; j++) {
                if (isFree(i, j)) return false;
            }
        }
        return true;
    }


    private static void quitGame() {
        int resp = Panel.questionChar("Exit game (Y/N)");
        if (resp == 'Y') {
            exit = true;
            if (points == 0) return;
            if (top.canAdd(points)) {
                String name = Panel.questionString("Your Name", 10);
                top.addRow(name, points);
            }
        }
        key = 0;
    }

    public static void move(int l, int c, int dLin, int dCol) {
        value = board[l][c];
        int lin = l + dLin;
        if (lin < 0 || lin >= LINES) lin = l;
        int col = c + dCol;
        if (col < 0 || col >= COLS) col = c;
        if (lin != l || col != c) {
            Panel.hideTile(l, c);
            Panel.showTile(l = lin, c = col, value);
            Panel.updateMoves(moves);
        }
    }

    public static boolean isFree(int l, int c) {
        if (board[l][c] != 0) return false;

        return true;
    }

}