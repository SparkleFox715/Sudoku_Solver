import java.io.*;
import java.util.*;
public class Solver{
    public static void main(String[] args){
        board Sodoku = new board();
        Scanner in = new Scanner(System.in);
        for(int x = 0;x<9;x++){
            for(int i = 0;i<9;i++){
                int num = in.nextInt();
                Sodoku.addNumber(num, i, x);
            }
        }
        Sodoku.calculateVoidSpaces();
        Sodoku.PrintBoard();

    }
}
class board{
    private int filledSpaces = 0;
    public square[][]board;
    public board(){
        board = new square[9][9];
    }
    public int getFilledSpaces(){
        return filledSpaces;
    }
    public void addNumber(int number, int x, int y){
        board[y][x] = new square(number);
    }
    public square[][] getBoard(){
        return board;   
    }
    public void calculateVoidSpaces(){
        int voidSpaces = 0;
        for(int i =0;i<9;i++){
            for(int j =0;j<9;j++){
                if(board[i][j].getValue()==-1){
                    voidSpaces++;
                }   
            }
        }
        filledSpaces = 81-voidSpaces;
    }
    public void PrintBoard(){
        for(int i = 0;i<9;i++){
            for(int j = 0;j<9;j++){
                if(board[i][j].getValue()!=-1)
                System.out.print(board[i][j].getValue()+" ");
                else
                System.out.print("  ");
            }
            System.out.println();
        }
    }
    public void Solve(){

    }
    public void checkRow(int row){
        ArrayList<Integer>temp  = new ArrayList();
        int index = -1;
        for(int x= 1;x<10;x++){
            temp.add(x);
        }
        for(int i = 0;i<9;i++){
            if(board[row][i].getValue()!=-1){
                temp.remove(board[row][i].getValue());
            }else{
                index = i;
            }
        }
        if(temp.size()==1){
           board[row][index].changeNumber(temp.get(0)); 
        }
    }
    public void checkColoumn(int col){
        ArrayList<Integer>temp  = new ArrayList();
        int index = -1;
        for(int x= 1;x<10;x++){
            temp.add(x);
        }
        for(int i = 0;i<9;i++){
            if(board[i][col].getValue()!=-1){
                temp.remove(board[i][col].getValue());
            }else{
                index = i;
            }
        }
        if(temp.size()==1){
           board[index][col].changeNumber(temp.get(0)); 
        }
    }
    public void CheckBox(int row, int col){
        ArrayList<Integer> temp = new ArrayList();
        int indx = -1;
        int indy=-1;
        for(int x = 1;x<10;x++){
            temp.add(x);
        }
        for(int i = 3*col;i<3+(3*col);i++){
            for(int j = 3*row;j<3+(3*row);j++){
                if(board[i][j].getValue()!=-1){
                    temp.remove(board[i][j].getValue());
                }else{
                    indx = i;
                    indy = j;
                }
            }
        }
        if(temp.size()==1){
            board[indx][indy].changeNumber(temp.get(0));
        }
    }
}
class square{
    private int number;
    private ArrayList<Integer> suggestions;
    public square(int number){
        this.number = number;
        suggestions = new ArrayList();
    }
    public ArrayList<Integer> getSuggestions(){
        return suggestions;
    }
    public int getValue(){
        return number;
    }
    public void changeNumber(int number){
        this.number = number;
        suggestions = null;
    }
    public void addSuggestion(int number){
        suggestions.add(number);
    }
    public void removeSuggestions(int number){
        suggestions.remove(number);
    }
}