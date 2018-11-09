public class Moves {
/*-------------------------------------MOVE ALL UPP--------------------------------------------------------------*/

    public static void Upp() {
        Game2048.moves++;
        for (int j = 0; j < Game2048.COLS; j++) {
            stackUpp(j);
            mergeUpp(j);
            stackUpp(j);
        }
        if (Game2048.isFull()) return;

        Game2048.genNewTile();

    }


    private static void mergeUpp(int col) {
        for (int i = 0; i <Game2048.LINES-1 ; i++) {
            if(Game2048.board[i][col]==Game2048.board[i+1][col]&&Game2048.board[i][col]!=0&&Game2048.board[i][col]!=Game2048.MAX_VALUE){
                Panel.hideTile(i+1,col);
                Panel.showTile(i,col,Game2048.board[i][col]*2);
                Game2048.points+=Game2048.board[i][col];
                Panel.updateScore(Game2048.points);
            }
        }

    }

    private static void stackUpp(int cols) {
        for (int i = 1; i <Game2048.LINES ; i++) {
            if((!Game2048.isFree(i,cols))){
                int aux=i;
                while (Game2048.board[aux-1][cols]==0 && aux>0){ //enquanto a peça em cima for 0
                    Game2048.move(aux,cols,-1,0); //mover a peça p/cima atá sair while
                    --aux;//peça cima
                    if(aux==0)break;
                }
            }
        }
    }
    /*-------------------------------------MOVE ALL DOWN--------------------------------------------------------------*/

    public static void Down() {

        Game2048.moves++;
        for (int j = 0; j < Game2048.COLS; j++) {
            stackDown(j);
            mergeDown(j);
            stackDown(j);
        }
        if (Game2048.isFull()) return;

        Game2048.genNewTile();
    }



    private static void mergeDown(int col) {
        for (int i = 0; i <Game2048.LINES-1 ; i++) {
            if(Game2048.board[i][col]==Game2048.board[i+1][col]&&Game2048.board[i][col]!=0&&Game2048.board[i][col]!=Game2048.MAX_VALUE){
                Panel.hideTile(i,col);
                Panel.showTile(i+1,col,Game2048.board[i+1][col]*2);
                Game2048.points+=Game2048.board[i+1][col];
                Panel.updateScore(Game2048.points);
            }
        }
    }


    private static void stackDown(int col) {
        for (int i = 0; i <Game2048.LINES ; i++) {
            if((!Game2048.isFree(i,col))){
                int aux=i;
                while (aux!=3 && Game2048.board[aux+1][col]==0){
                    Game2048.move(aux,col,+1,0);
                    ++aux;
                }
            }
        }
    }

    /*-------------------------------------MOVE ALL LEFT--------------------------------------------------------------*/
    public static void Left() {

        Game2048.moves++;
        for (int i = 0; i < Game2048.LINES; i++) {
            stackLeft(i);
            mergeLeft(i);
            stackLeft(i);
        }
        if (Game2048.isFull()) return;

        Game2048.genNewTile();
    }


    private static void mergeLeft(int line) {
        for (int j = 0; j < Game2048.COLS - 1; j++) {
            if (Game2048.board[line][j] == Game2048.board[line][j + 1] && Game2048.board[line][j] != 0&&Game2048.board[line][j]!=Game2048.MAX_VALUE) {
                Panel.hideTile(line, j + 1);
                Panel.showTile(line, j, Game2048.board[line][j] * 2);
                Game2048.points += Game2048.board[line][j];
                Panel.updateScore(Game2048.points);
            }
        }
    }

    private static void stackLeft(int line) {
        for (int j = 1; j < Game2048.COLS; j++) {
            if ((!Game2048.isFree(line, j))) {
                int aux = j;
                while (Game2048.board[line][aux - 1] == 0 && aux > 0) {
                    Game2048.move(line, aux, 0, -1);
                    --aux;
                    if (aux == 0) break;
                }
            }
        }
    }
    /*-------------------------------------MOVE ALL RIGHT--------------------------------------------------------------*/
    public static void Right() {

        Game2048.moves++;
        for (int i = 0; i < Game2048.LINES; i++) {
            stackRight(i);
            mergeRight(i);
            stackRight(i);
        }
        if (Game2048.isFull()) return;

        Game2048.genNewTile();
    }

    private static void mergeRight(int line) {
        for (int j = 0; j < Game2048.COLS - 1; j++) {
            if (Game2048.board[line][j] == Game2048.board[line][j + 1] && Game2048.board[line][j] != 0&&Game2048.board[line][j]!=Game2048.MAX_VALUE) {
                Panel.hideTile(line, j);
                Panel.showTile(line, j + 1, Game2048.board[line][j + 1] * 2);
                Game2048.points += Game2048.board[line][j + 1];
                Panel.updateScore(Game2048.points);
            }
        }
    }

    private static void stackRight(int line) {
        for (int j = 0; j < Game2048.COLS; j++) {
            if ((!Game2048.isFree(line, j))) {
                int aux = j;
                while (aux != 3 && Game2048.board[line][aux + 1] == 0) {
                    Game2048.move(line, aux, 0, +1);
                    ++aux;
                }
            }
        }
    }

}
