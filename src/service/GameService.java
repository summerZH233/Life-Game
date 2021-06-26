package service;
import data.CellArray;
import java.util.Random;

import javax.swing.JFrame;

import data.CellState;
public class GameService {
//	private static int rows;
//	private static int cols;
	
	public static int[] temp= {-1,0,1};
	/*初始化随机*/
	public static CellArray initMap(int row,int col ) {
		CellArray cells=new CellArray(row,col);
		for(int  i=0;i<row;i++) {
			for(int j=0;j<col;j++) {
				Random r=new Random();
				int a=r.nextInt(4);
				if(a==1) {
					cells.setCell(i,j,CellState.LIVE.getValue());
				}
				else {
					cells.setCell(i,j,CellState.DEAD.getValue());
					
				}
			}
		}
		return cells;
	}
	
	public static CellArray generate(CellArray cells) {
		CellArray nextCells=new CellArray(cells.getRow(),cells.getCol());
	  for(int i=0;i<nextCells.getRow();i++) 
		  for(int j=0;j<nextCells.getCol();j++) {
			  
			  int count=countNumber(cells,i,j);
			 if(count==3) {
				 nextCells.setCell(i, j, CellState.LIVE.getValue());
			 }//如果一个死细胞周围有3个活细胞，它在回合结束时变为活细胞。
			 else if(count==2&&cells.getCell(i, j)==CellState.LIVE.getValue()) {
				 nextCells.setCell(i, j, CellState.LIVE.getValue());
			 }//如果一个活细胞周围有2或3个活细胞，它在回合结束时保持原样。
			 else {
				 nextCells.setCell(i, j, CellState.DEAD.getValue());
			 }//其他情况出现时，活细胞在回合结束后死亡。
		  }
	  
		
		return  nextCells;
		
	}
	
	
	public static int countNumber(CellArray cells,int x,int y) {
		//计算细胞周围的活细胞数量
		int count = 0;
		 for (int i = 0; i < 3; ++i) {
	            for (int j = 0; j < 3; ++j) {
	            	//在3*3的矩阵中计算活细胞的数量并保存在count
	                if (CellState.LIVE.getValue() == cells.getCell(x + temp[i], y + temp[j])) {
	                    ++count;
	                }
	            }
	        }	
		 if (CellState.LIVE.getValue() == cells.getCell(x, y)) {
	            --count;
	        }
		
        return count;//int型最后得到活细胞的数量
	}	
	
}
