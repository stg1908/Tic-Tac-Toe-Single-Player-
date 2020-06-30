import java.io.*;
import java.util.*;
class move
{
	int r;
	int c;
	move()
	{
		r=-1;
		c=-1;
	}
}
class tictac
{
	static void display(char mat[][])
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				System.out.print(mat[i][j]);
			}
			System.out.println();
		}
	}
	static int eval(char mat[][])
	{
		for(int i=0;i<3;i++)
		{
			if(mat[i][0]==mat[i][1]&&mat[i][1]==mat[i][2])
			{
				
				if(mat[i][0]=='X')
					return 10;
				if(mat[i][0]=='0')
					return -10;
			}
		}
		for(int i=0;i<3;i++)
		{
			if(mat[0][i]==mat[1][i]&&mat[1][i]==mat[2][i])
			{
				
				if(mat[0][i]=='X')
					return 10;
				if(mat[0][i]=='0')
					return -10;
			}
		}
		if(mat[0][0]==mat[1][1]&&mat[1][1]==mat[2][2])
		{
				
			if(mat[0][0]=='X')
				return 10;
			if(mat[0][0]=='0')
				return -10;
		}
		if(mat[0][2]==mat[1][1]&&mat[1][1]==mat[2][0])
		{
				
			if(mat[0][2]=='X')
				return 10;
			if(mat[0][2]=='0')
				return -10;
		}
		return 0;
	}
	static int minmax(char mat[][], int depth,boolean max )
	{
		int score = eval(mat);
		if(score==10)
			return score-depth;
		if(score==-10)
			return score+depth;
		if(!isanyleft(mat))
		{
			return 0;
		}
		if(max)
		{
			int best = -1000;
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(mat[i][j]=='-')
					{
						mat[i][j]='X';
						best = Math.max(best,minmax(mat,depth+1,!max));
						mat[i][j]='-';
					}
					
				}
			}
			return best;
		}
		else
		{
			int best = 1000;
			for(int i=0;i<3;i++)
			{
				for(int j=0;j<3;j++)
				{
					if(mat[i][j]=='-')
					{
						mat[i][j]='0';
						best = Math.min(best,minmax(mat,depth+1,!max));
						mat[i][j]='-';
					}
					
				}
			}
			return best;
		
		}
	}
	static move bestmove(char mat[][])
	{
		move m = new move();
		int eva = eval(mat);
		if(eva==10)
		{
			System.out.println("Player X wins");
			System.exit(0);
		}
		if(eva==-10)
		{
			System.out.println("Player 0 wins");
			System.exit(0);
		}
		if(!isanyleft(mat))
		{
			display(mat);
			System.out.println("Match is tie");
			System.exit(0);
		}
		int best = -1000;
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(mat[i][j]=='-')
				{
					mat[i][j]='X';
					int temp = minmax(mat,0,false);
					mat[i][j]='-';
					if(temp>best)
					{
						best=temp;
						m.r= i;
						m.c = j;
					}
				
				}  			
			}
		}
		return m;	
	}
	static boolean isanyleft(char mat[][])
	{
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(mat[i][j]=='-')
					return true;
			}
		}
		return false;
	}
	public static void main(String arg[]) throws Exception
	{
		char mat[][] = new char[3][3];
		for(int i=0;i<3;i++)
		{
			Arrays.fill(mat[i],'-');
		}
		display(mat);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true)
		{
			
			
			String name = br.readLine().trim();
			if(name.equals("end"))
				System.exit(0);
			
			String rc[] = name.split(" ");
			int r = Integer.parseInt(rc[0]);
			int c = Integer.parseInt(rc[1]);
			if(r<0||r>2||c<0||c>2)
			{
				System.out.println("Enter valid location");
				continue;
			}
			if(mat[r][c]=='-')
			{	
				mat[r][c]='0';
				//display(mat);
			}
			else
			{
				System.out.println("Enter valid location");
				continue;
			}
			move m = bestmove(mat);
			if(m.r==-1||m.c==-1)
			{
				display(mat);
				System.out.println("Match is tie");
				break;
			}
			mat[m.r][m.c]='X';
			System.out.println("Your turn");
			display(mat);
			if(eval(mat)==10)
			{
				System.out.println("Player X wins");
				//System.exit(0);
				break;
			}
			if(!isanyleft(mat))
			{
				display(mat);
				System.out.println("Match is tie");
				break;
			}
		}
		
	}
}
