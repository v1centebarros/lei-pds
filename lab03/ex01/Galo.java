package lab03.ex01;

import java.util.Arrays;

public class Galo implements JGaloInterface{

    private final char[][] tabuleiro;
    private  int nJogadas = 0;
    private char atualPlayer;

    public Galo (){
        tabuleiro = new char[3][3];
        startTabuleiro();
        atualPlayer = 'X';
    }

    public Galo (char aP) {
        tabuleiro = new char[3][3];
        startTabuleiro();
        if (aP == 'O' || aP == 'X') {
            atualPlayer = aP;
        } else {
            atualPlayer = 'X';
        }
    }

    private void startTabuleiro() {
        for (char[] linha : tabuleiro)
            Arrays.fill(linha,' ');
    }
    @Override
    public char getActualPlayer() {
        return atualPlayer;
    }

    private void turnPlayer() {
        atualPlayer =  atualPlayer == 'O' ? 'X': 'O';
    }

    @Override
    public boolean setJogada(int lin, int col) {
        if (tabuleiro[lin-1][col-1] == ' ') {
            tabuleiro[lin-1][col-1] = getActualPlayer();
            turnPlayer();
            nJogadas++;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFinished() {
       return nJogadas == 9 || hasWinner();
    }

    private boolean hasWinner() {
        for (int i = 0; i < 3; i++) {
            if ((tabuleiro[i][0] != ' ' && tabuleiro[i][0] == tabuleiro[i][1] && tabuleiro[i][0] == tabuleiro[i][2]) ||
                (tabuleiro[0][i] != ' ' && tabuleiro[0][i] == tabuleiro[1][i] && tabuleiro[0][i] == tabuleiro[2][i]))
                return true;
        }

        return (tabuleiro[0][0] != ' ' && tabuleiro[0][0] == tabuleiro[1][1] && tabuleiro[0][0] == tabuleiro[2][2]) ||
                (tabuleiro[0][2] != ' ' && tabuleiro[0][2] == tabuleiro[1][1] && tabuleiro[0][2] == tabuleiro[2][0]);
    }
    @Override
    public char checkResult() {
        if (hasWinner()){
            turnPlayer();
            return getActualPlayer();
        } else {
            return ' ';
        }
    }
}