public class TicTacToeBoard {
    private String[][] board;
    private final int ROWS;
    private final int COLS;
    private String resolution;

    public TicTacToeBoard(){
        this.ROWS = 3;
        this.COLS = 3;
        this.board = new String[this.ROWS][this.COLS];
        this.resolution = "Unfinished";
    }

    public void printBoard(){
        System.out.println();
        for(int r = 0; r < this.ROWS; r++){
            for(int c = 0; c < this.COLS; c++){
                System.out.print(" " + this.getCharAt(r,c) + " ");
                if (c < this.COLS-1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (r < this.ROWS-1) {
                System.out.println("-----------");
            }

        }
        System.out.println();
    }

    private String getCharAt(int row, int col){
        String current = this.board[row][col];
        current = (current != null) ? current : " ";
        switch (current){
            case "x":
            case "X":
                return "X";
            case "o":
            case "O":
                return "O";
            default:
                return " ";

        }
    }

    public void setCharAt(int row, int col, char input){
        if (!isValidInput(row,col)){
            return;
        }

        this.board[row][col] = Character.toString(input);

        this.updateBoardResolution();
    }

    public boolean isValidInput(int row, int col){
        if (row >= this.ROWS || col >= this.COLS){
            return false;
        }

        String current = this.getCharAt(row,col);

        return current.equals(" ");
    }

    public String getResolution(){
        return this.resolution;
    }

    private void setResolution(String newResolution){
        this.resolution = newResolution;
    }

    private void updateBoardResolution(){
        StringBuilder fullboard = new StringBuilder();
        String potentialSolution;

        for(int r = 0; r < this.ROWS; r++){
            for(int c = 0; c < this.COLS; c++){
                fullboard.append(this.getCharAt(r, c));
            }


            // row solution
            potentialSolution = this.getCharAt(r, 0) + this.getCharAt(r, 1) + this.getCharAt(r, 2);
            if (potentialSolution.equals("XXX")){
                this.setResolution("X");
                return;
            }
            if (potentialSolution.equals("OOO")){
                this.setResolution("O");
                return;
            }

            // col solution
            potentialSolution = this.getCharAt(0, r) + this.getCharAt(1, r) + this.getCharAt(2, r);
            if (potentialSolution.equals("XXX")){
                this.setResolution("X");
                return;
            }
            if (potentialSolution.equals("OOO")){
                this.setResolution("O");
                return;
            }
        }

        // right diagonal solution
        potentialSolution = this.getCharAt(0,0) + this.getCharAt(1,1) + this.getCharAt(2,2);
        if (potentialSolution.equals("XXX")){
            this.setResolution("X");
            return;
        }
        if (potentialSolution.equals("OOO")){
            this.setResolution("O");
            return;
        }

        // left diagonal solution
        potentialSolution = this.getCharAt(2,0) + this.getCharAt(1,1) + this.getCharAt(0,2);
        if (potentialSolution.equals("XXX")){
            this.setResolution("X");
            return;
        }
        if (potentialSolution.equals("OOO")){
            this.setResolution("O");
            return;
        }

        // tie board game
        if (!fullboard.toString().contains(" ")){
            this.setResolution("Tie");
        }
    }


    public boolean isBoardComplete(){
        return !this.getResolution().equals("Unfinished");
    }
}
