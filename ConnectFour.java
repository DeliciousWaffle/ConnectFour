/***********************************
* To Do: 
* Add javadocs, 
* Make diagonal checkers less messy
* Make everything else less messy
***********************************/

public class ConnectFour
{
    public static void main(String[] args)
    {
        StdDraw.setCanvasSize(1000, 800);
        
        char[][] board = new char[6][7];
        boolean gameOver = false;
        //player1 = 'X' (RED); player2 = 'O' (GREEN)
        char players = 'X';                       
        boolean valid = true;
        
        board = initializeGameBoard(board);

        while(! gameOver)
        {
            valid = validateInput(board, valid);
            board = selectColumn(board, players);
            
            gameOver = checkHorizontal(board, players);
            gameOver = checkVertical(board, players, gameOver);
            gameOver = checkDiagonalDown(board, players, gameOver);
            gameOver = checkDiagonalUp(board, players, gameOver);
            gameOver = checkTie(board, gameOver);
            
            players = swapPlayers(players);
        } 
    }
    
    /** 
        Generates a blank game board with no tokens.
        @param a represents the table which has 6 rows and 7 columns.
        @returns the initialized game board
    */
    public static char[][] initializeGameBoard(char[][] a)
    {
        StdDraw.setPenColor(StdDraw.BLACK);
        
        StdDraw.filledSquare(.5, .5, .38);         // the game board
        StdDraw.filledSquare(.16, .08, .04);       // left leg        
        StdDraw.filledSquare(.84, .08, .04);       // right leg
        
        //starting values from top left space
        double x = .1;
        double y = .8;
        
        StdDraw.setPenColor(StdDraw.WHITE);
        
        //drawing the empty spaces
        for(int i=0; i<a.length; i++)
        {
            for(int j=0; j<a[i].length; j++)
            {
                a[i][j] = '.';  //. represents a blank space
                
                StdDraw.filledCircle(x + .1, y, .042);
                
                x += .1;      // increment gap between blank spaces
            }
            
            x -= .7;          // reset x-val to maintain alignment
            y -= .12;         // next row down
        }
        
        return a;
    }
    
    /**
        Makes sure that the area the user selected is a valid one. Prevents the tokens on
        the board from going out of bounds.
        @param a is the game board
        @param valid true means valid, false not valid
        @returns if the input is valid
    */    
    public static boolean validateInput(char a[][], boolean valid)
    {
        //CREDIT: Jason Covert on lab 4ish?
        //allows the user to click anywhere on the window to drop a token
        while(!StdDraw.isMousePressed());
        while(StdDraw.isMousePressed());
        
        //if top of 1st column is occupied and the player chooses the 1st column
        //this prevents array from going out of bounds
        if(a[0][0] != '.' && StdDraw.mouseX() < .25)
        {
            //recursive call back to this method; keeps doing this until player selects a valid column
            return validateInput(a, false);
        }
        //2nd column occupied; player selects 2nd column
        else if(a[0][1] != '.' && (StdDraw.mouseX() > .25 && StdDraw.mouseX() < 0.35))
        {
            return validateInput(a, false);
        }
        //3rd column occupied; player selects 3rd column
        else if(a[0][2] != '.' && (StdDraw.mouseX() > .35 && StdDraw.mouseX() < 0.45))
        {
            return validateInput(a, false);
        }
        //4th column occupied; player selects 4th column
        else if(a[0][3] != '.' && (StdDraw.mouseX() > .45 && StdDraw.mouseX() < 0.55))
        {
            return validateInput(a, false);
        }
        //5th column occupied; player selects 4th column
        else if(a[0][4] != '.' && (StdDraw.mouseX() > .55 && StdDraw.mouseX() < 0.65))
        {
            return validateInput(a, false);
        }
        //6th column occupied; player selects 4th column
        else if(a[0][5] != '.' && (StdDraw.mouseX() > .65 && StdDraw.mouseX() < 0.75))
        {
            return validateInput(a, false);
        }
        //7th column occupied; player selects 4th column
        else if(a[0][6] != '.' && StdDraw.mouseX() > .75)
        {
            return validateInput(a, false);
        }
        
        //when the player selects a correct column, escape this method
        return true;
    }
    
    /** 
        Current player chooses what column they want to drop their token into.
        @param a is the game board
        @param players represents the current player's turn
        @return the updated table with new token
    */    
    public static char[][] selectColumn(char[][] a, char players)
    {
        //checking to see who's turn it is; set token color to the player's color
        if(players == 'X')
        {
            StdDraw.setPenColor(StdDraw.RED);
        }
        else
        {
            StdDraw.setPenColor(StdDraw.GREEN);
        }
        
        //bottom of the columns, and will eventually be used to stack the tokens
        int col1 = 5;
        int col2 = 5;
        int col3 = 5;
        int col4 = 5;
        int col5 = 5;
        int col6 = 5;
        int col7 = 5;
        
        //starting location of the bottom row
        double y = .2;
        
        //player selected the 1st column
        if(StdDraw.mouseX() < .25)
        {
            //while an occupied space; allows tokens to stack
            while(a[col1][0] != '.')
            {
                col1--;
                y += .12;
            }
                    
            //setting the location selected to the player's token
            a[col1][0] = players;
            StdDraw.filledCircle(.2, y, .042); 
        }
        //2nd column
        else if(StdDraw.mouseX() > .25 && StdDraw.mouseX() < 0.35)
        {
            while(a[col2][1] != '.')
            {
                col2--;
                y += .12;
            }
                    
            a[col2][1] = players;
            StdDraw.filledCircle(.3, y, .042); 
        } 
        //3rd column      
        else if(StdDraw.mouseX() > .35 && StdDraw.mouseX() < 0.45)
        {
            while(a[col3][2] != '.')
            {
                col3--;
                y += .12;
            }
                    
            a[col3][2] = players;
            StdDraw.filledCircle(.4, y, .042); 
        } 
        //4th column        
        else if(StdDraw.mouseX() > .45 && StdDraw.mouseX() < 0.55)
        {
            while(a[col4][3] != '.')
            {
                col4--;
                y += .12;
            }
                    
            a[col4][3] = players;
            StdDraw.filledCircle(.5, y, .042); 
        }  
        //5th column       
        else if(StdDraw.mouseX() > .55 && StdDraw.mouseX() < 0.65)
        {
            while(a[col5][4] != '.')
            {
                col5--;
                y += .12;
            }
                    
            a[col5][4] = players;
            StdDraw.filledCircle(.6, y, .042); 
        } 
        //6th column        
        else if(StdDraw.mouseX() > .65 && StdDraw.mouseX() < 0.75)
        {
            while(a[col6][5] != '.')
            {
                col6--;
                y += .12;
            }
                    
            a[col6][5] = players;
            StdDraw.filledCircle(.7, y, .042); 
        }  
        //7th column      
        else if(StdDraw.mouseX() > .75)
        {
            while(a[col7][6] != '.')
            {
                col7--;
                y += .12;
            }
                    
            a[col7][6] = players;
            StdDraw.filledCircle(.8, y, .042); 
        } 
        
        //updating the board       
        return a;
    }
    
    /**
        Checks to see if the current player won horizontally.
        @param a the game board
        @players the current player
        @return whether or not the game is over (somebody won)
    */    
    
    public static boolean checkHorizontal(char[][] a, char players)
    {
        //how many tokens the current player has in a row
        int streak = 0;
        
        //loop through our rows and columns and check for streaks
        for(int i=0; i<a.length; i++)
        {
            for(int j=0; j<a[i].length; j++)
            {
                //a player has a streak going
                if(a[i][j] == players)
                {
                    //System.out.println("Horizontal streak at a[" + i + "][" + j + "]");
                    streak++;
                }
                //reset as soon as the streak ends
                else
                {
                    streak = 0;
                }
                
                //player got a streak of 4, they won
                if(streak >= 4)
                {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(.5, .94, "Player " + players + " Won Horizontally!");
                    return true;
                }
            }
            
            //this stupid thing needs to be reset because the player could have a
            //streak going on a previous row, but it gets carried over to the next row
            streak = 0;
        }
        
        //System.out.println();
        
        //current player didn't win
        return false;
    }
    
    /**
        Checks to see if the current player won vertically.
        @param a the game board
        @players the current player
        @return whether or not the game is over (somebody won)
    */
    public static boolean checkVertical(char[][] a, char players, boolean gameOver)
    {
        //if the player already won horizontally, there's no need to go through all of this
        if(gameOver == true)
        {
            return true;
        }
        
        int streak = 0;
        int column = 0;
        
        //since we want to check vertically, we have to make this more confusing than it needs to be
        while(column <= 6)
        {
            //our "rows"
            for(int i=0; i<=5; i++)
            {
                //checking for a vertical streak
                if(a[i][column] == players)
                {
                    //System.out.println("Vertical streak at a[" + i + "][" + column + "]");
                    streak++;
                }
                else
                {
                    streak = 0;
                }
                
                if(streak >= 4)
                {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(.5, .94, "Player " + players + " Won Vertically!");
                    return true;
                }
            }
            
            streak = 0;
            column++;
        }
        
        //System.out.println();
        
        return false;
    }        
            
    public static boolean checkDiagonalDown(char[][] a, char players, boolean gameOver)
    {   
        if(gameOver == true)
        {
            return true;
        }
        
        int streak = 0;
        
        //will be used to make sure that array doesn't go out of bounds (REFER TO NOTES)
        int outOfBoundsRow = 5;
        int outOfBoundsCol = 6;
        
        //method of solving the diagonal problem; these get added to the current index number
        //and will later be modified to check for other diagonals
        int adjustRow = 2;
        int adjustCol = 0;
        
        int diagonal = 0;
        
        //total number of possible diagonals; checking using only adjustRow first (makes things slightly less complicated)
        //checking #1, #2, #3
        while(diagonal < 3)
        {
            for(int i=0; i<a.length; i++)
            {
                //first checks to make sure the array doesn't go out of bounds 
                //checks current diagonal to see if the player has a streak going
                if((i + adjustRow <= outOfBoundsRow && i + adjustCol <= outOfBoundsCol) && (a[i + adjustRow][i + adjustCol] == players))
                {
                    streak++;
                    //System.out.println("Diagonally Down streak at a[" + (i + adjustRow) + "][" + (i + adjustCol) + "]");

                }
                else
                {
                    streak = 0;
                }
            
                if(streak >= 4)
                {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(.5, .94, "Player " + players + " Won Diagonally Down!");
                    return true;
                }
            
            }
                        
            streak = 0;
            adjustRow--;
            diagonal++;
        }
        
        adjustRow = 0;
        adjustCol = 3;
        diagonal = 0;
        
        //repeating a bunch of code
        //checking #4, #5, #6 using adjustCol
        while(diagonal < 3)
        {
            for(int i=0; i<a.length; i++)
            {
                if((i + adjustRow <= outOfBoundsRow && i + adjustCol <= outOfBoundsCol) && (a[i + adjustRow][i + adjustCol] == players))
                {
                    streak++;
                    //System.out.println("Diagonally Down streak at a[" + (i + adjustRow) + "][" + (i + adjustCol) + "]");
                }
                else
                {
                    streak = 0;
                }
            
                if(streak >= 4)
                {
                    System.out.println("Player " + players + " Won diagonally");
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(.5, .94, "Player " + players + " Won Diagonally Down!");
                    return true;
                }
            }
            
            //System.out.println();
            
            streak = 0;
            adjustCol--;
            diagonal++;
        }
        
        //System.out.println();
        
        return false;
    }  
    
    public static boolean checkDiagonalUp(char[][] a, char players, boolean gameOver)
    {
        if(gameOver == true)
        {
            return true;
        }
        
        int streak = 0;
        
        int outOfBoundsRow = 0;
        int outOfBoundsCol = 6;
        
        int adjustRow = 3;
        int adjustCol = 0;
        
        int diagonal = 0;
        
        //total number of possible diagonals; checking using only adjustRow first
        //checking #1, #2, #3
        while(diagonal < 3)
        {
            for(int i=0; i<a.length; i++)
            {
                if((adjustRow - i >= 0) && (a[adjustRow - i][i] == players))
                {
                    streak++;
                    //System.out.println("Diagonally Up streak at a[" + (adjustRow - i) + "][" + i + "]");
                }
                else
                {
                    streak = 0;
                }
            
                if(streak >= 4)
                {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(.5, .94, "Player " + players + " Won Diagonally Up!");
                    return true;
                }
            }
            
            //System.out.println();
            
            streak = 0;
            adjustRow++;
            diagonal++;
        }
        
        adjustRow = 5;
        adjustCol = 1;
        diagonal = 0;
        
        //#4, #5, #6
        while(diagonal < 3)
        {
            for(int i=0; i<a.length; i++)
            {
                if((adjustRow >= 0 && adjustCol + i <= outOfBoundsCol) && (a[adjustRow][adjustCol + i] == players))
                {
                    streak++;
                    //System.out.println("Diagonal Up streak at a[" + (adjustRow) + "][" + (adjustCol + i) + "]");
                }
                else
                {
                    streak = 0;
                }
            
                if(streak >= 4)
                {
                    StdDraw.setPenColor(StdDraw.BLACK);
                    StdDraw.text(.5, .94, "Player " + players + " Won Diagonally Up!");
                    return true;
                }
                
                adjustRow--;
            }
                        
            streak = 0;
            adjustRow = 5;
            adjustCol++;
            diagonal++;
        }
        
        //System.out.println();
        
        return false;
    }
    
    public static boolean checkTie(char[][] a, boolean gameOver)
    {
        //somebody won already, don't need to botheer executing this code
        if(gameOver == true)
        {
            return true;
        }
        
        int count = 0;
        
        for(int i=0; i<a.length; i++)
        {
            for(int j=0; j<a[i].length; j++)
            {
                //space is occupied with a token, then count up
                if(a[i][j] != '.')
                {
                    count++;
                }
            }
        }
        
        //all spaces are occupied with tokens; 7 rows * 6 columns = 42 total spaces
        if(count == 42)
        {
            //System.out.println("It's a tie!");
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(.5, .94, "Looks like it's a tie!");
            return true;
        }
        
        return false;
    }
    
    /**
        Swaps the player's turn.
        @param players represents the current player
        @returns the next player's turn
    */    
    public static char swapPlayers(char players)
    {
        if(players == 'X')
        {
            return 'O';
        }
        
        return 'X';
    }
}