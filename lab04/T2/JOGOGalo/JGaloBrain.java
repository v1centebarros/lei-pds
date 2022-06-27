package lab03.JOGOGalo;

public class JGaloBrain implements JGaloInterface{
    char ActualPlayer;
    boolean Finished;
    char Winner;
    char[][] Game = new char[3][3];


    public JGaloBrain() {
        this.ActualPlayer = 'X';
        this.Finished = false;
        this.Winner = 'E';
    }
    public JGaloBrain(char player) {
        this.ActualPlayer = player;
        this.Finished = false;        
        this.Winner = 'E';
    }

    @Override
    public char getActualPlayer() {
        return this.ActualPlayer;   
    }

    @Override
    public boolean setJogada(int lin, int col) {
        if (!this.checkIfSquareIsFree(lin, col) || lin > 3 || col > 3) return false;

        Game[lin-1][col-1] =  this.ActualPlayer;

        if (this.ActualPlayer == 'X'){this.ActualPlayer = 'O';}else{this.ActualPlayer = 'X';}

        return true;
    }


    @Override
    public boolean isFinished() {
        if(this.SeeIfWon() || this.SeeIfFull()){
            this.Finished = true;
            return this.Finished;
        }
            return this.Finished;
    }

    @Override
    public char checkResult() {
        return this.Winner;
    }

    public boolean checkIfSquareIsFree(int lin, int col){
        if(this.Game[lin-1][col-1] == 0) return true;
        return false;
    }
    public boolean SeeIfWon(){
        for (int line = 0; line < 3; line++){
            if(this.Game[line][0] != 0 && this.Game[line][1] != 0 && this.Game[line][2] != 0){
                if(this.Game[line][0] == this.Game[line][1] && this.Game[line][0] == this.Game[line][2]){
                    this.Winner = Game[line][0];
                    return true;
                }
            }   
        }
        for (int col = 0; col < 3; col++){
            if(this.Game[0][col] != 0 && this.Game[1][col] != 0 && this.Game[2][col] != 0){
                if(this.Game[0][col] == this.Game[1][col] && this.Game[0][col] == this.Game[2][col]){
                    this.Winner = Game[0][col];
                    return true;
                }
            }
        }
        if(this.Game[0][0] != 0 && this.Game[1][1] != 0 && this.Game[2][2] != 0){
            if((this.Game[0][0] == this.Game[1][1] && this.Game[0][0] == this.Game[2][2])){
                this.Winner = Game[1][1];
                return true;
            }
        }
        if(this.Game[0][2] != 0 && this.Game[1][1] != 0 && this.Game[2][0] != 0){
            if(this.Game[0][2] == this.Game[1][1] && this.Game[0][2] == this.Game[2][0]){
                this.Winner = Game[1][1];
                return true;
            }
        }

        return false;
    }
    public boolean SeeIfFull(){
        for(int line = 0; line < 3; line++){
            for (int col = 0; col < 3; col++){
                if (this.Game[line][col] == 0) return false;
            }
        }
        return true;
    }
}