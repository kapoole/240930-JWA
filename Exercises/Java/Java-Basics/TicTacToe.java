import java.util.Scanner;

public class TicTacToe {
    TicTacToeBoard board;
    private int currentPlayer;

    public TicTacToe(){
        this.board = new TicTacToeBoard();
        this.currentPlayer = 1;
    }

    public TicTacToeBoard getBoard(){
        return this.board;
    }

    private char getCurrentPlayerCharacter(){
        if (this.currentPlayer == 1){
            return 'X';
        } else {
            return 'O';
        }
    }

    private void swapCurrentPlayer(){
        if (this.currentPlayer == 1){
            this.currentPlayer = 2;
        } else {
            this.currentPlayer = 1;
        }
    }

    public void play(){
        System.out.println(" _______ _____ _____   _______       _____   _______ ____  ______");
        System.out.println("|__   __|_   _/ ____| |__   __|/\\   / ____| |__   __/ __ \\|  ____|");
        System.out.println("   | |    | || |   ______| |  /  \\ | |   ______| | | |  | | |__  ");
        System.out.println("   | |    | || |  |______| | / /\\ \\| |  |______| | | |  | |  __| ");
        System.out.println("   | |   _| || |____     | |/ ____ \\ |____     | | | |__| | |____");
        System.out.println("   |_|  |______\\_____|   |_/_/    \\_\\_____|    |_|  \\____/|______|");
        System.out.println("by Kenan Poole");
        System.out.println();
        System.out.println("X gets to go first.");

        try (Scanner scanner = new Scanner(System.in)) {
            do {
                this.getBoard().printBoard();

                // use input to determine location to fill in
                System.out.println("Place an '" + this.getCurrentPlayerCharacter() + "' on the TicTacToe Board using 0 indexing");
                System.out.print("Which row? ");
                String userRow = scanner.nextLine();
                System.out.print("Which column? ");
                String userCol = scanner.nextLine();

                // check that the row and column specified is a valid spot to place a new character
                boolean isValidInput = this.getBoard().isValidInput(Integer.parseInt(userRow), Integer.parseInt(userCol));
                if (!isValidInput){
                    System.out.println("Unavailable square on TicTacToe Board");
                    continue;
                }else {
                    this.getBoard().setCharAt(Integer.parseInt(userRow), Integer.parseInt(userCol), this.getCurrentPlayerCharacter());
                }

                // since a valid character was input, alternate to the other player
                this.swapCurrentPlayer();

                // only continue while the board is Unfinished
            } while(!this.getBoard().isBoardComplete());

            this.getBoard().printBoard();

            String winner = this.getBoard().getResolution();
            if (winner.equals("Tie")){
                System.out.println("SCRATCH! The game ends in a Tie...");
            } else {
                System.out.println(winner + " is the WINNER!");
            }
        }
    }
}