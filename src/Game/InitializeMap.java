package Game;
/*
 * �˷������ڳ�ʼ����ͼ
 */
public class InitializeMap {

	public static boolean isFirstInitialize=true;
	public static int stepNum=0;
	public static int stepLimit=0;
	public static int TimeLimit=0;
	public static int TimeNum=0;
	public static int ScoreNum=0;
	
	public static void Initialize(String str){
		InitializePictureNum();
		if(isFirstInitialize=true){
			stepNum=0;
			stepLimit=30;
			ScoreNum=0;
			InitializeMap.isFirstInitialize=false;
		}
		boolean isXLine=isXLine();
		boolean isYLine=isYLine();
		boolean CannotCancel=!CanCancel();
		
		while(isXLine||isYLine||CannotCancel){
			InitializePictureNum();
			
			isXLine=isXLine();
			isYLine=isYLine();
			CannotCancel=(!CanCancel());
		}
			InitializePicture(str);
	}
	private static void InitializePictureNum(){
		/*
		 * ��ʼ����ͼ��ͼƬ��Ӧ������
		 */
		for(int y=0;y<AllSize.YBlockNum;y++)
			for(int x=0;x<AllSize.XBlockNum;x++){
				int pictureNum=(int)(Math.random()*6)+1;
				Block.Buttons[y][x].PictureNum=pictureNum;
			}
	}
	private static void InitializePicture(String str){
		/*
		 * ���ݳ�ʼ�������֣��Ե�ͼ�а�ť����Ӧ��ͼƬ
		 */
		for(int y=0;y<AllSize.YBlockNum;y++)
			for(int x=0;x<AllSize.XBlockNum;x++){
				int pictureNum=Block.Buttons[y][x].PictureNum;
				/*
				 * ��ť�м����ӦͼƬ
				 */
				new Pictures(str);
				switch(pictureNum){
				case 1:
					Block.Buttons[y][x].setIcon(Pictures.icon1);
					break;
				case 2:
					Block.Buttons[y][x].setIcon(Pictures.icon2);
					break;
				case 3:
					Block.Buttons[y][x].setIcon(Pictures.icon3);
					break;
				case 4:
					Block.Buttons[y][x].setIcon(Pictures.icon4);
					break;
				case 5:
					Block.Buttons[y][x].setIcon(Pictures.icon5);
					break;
				case 6:
					Block.Buttons[y][x].setIcon(Pictures.icon6);
					break;
				}
			}
	}
	private static boolean isXLine(){
		/*
		 * �ж��Ƿ������в��������������������ķ��顣��û�У��򷵻�false��
		 */
		boolean isXLine=false;
		int Ycount=0;
		int Xcount=0;
		/*
		 * isXLineΪ��һ����ͬ�����������Ϊ(x,y),(x,y+1),(x,y+2)����������ɫ��ͬ����Ϊtrue��
		 */
		for(Xcount=0;Xcount<AllSize.XBlockNum;Xcount++){
			for(Ycount=0;Ycount<AllSize.YBlockNum-2;Ycount++){
				int y=Ycount;
				int yPlus1=Ycount+1;
				int yPlus2=Ycount+2;
				boolean FirstEqual=(Block.Buttons[y][Xcount].PictureNum==Block.Buttons[yPlus1][Xcount].PictureNum);
				boolean SecondEqual=(Block.Buttons[yPlus1][Xcount].PictureNum==Block.Buttons[yPlus2][Xcount].PictureNum);
				if(FirstEqual&&SecondEqual){
					isXLine=true;
				}
			}
		}
		return isXLine;
	}
	private static boolean isYLine(){
		/*
		 * �ж��Ƿ�����в����������Ŀ��������ķ��顣��û�У��򷵻�false��
		 */
		boolean isYLine=false;
		int Ycount=0;
		int Xcount=0;
		/*
		 * isYLineΪ�ڶ�����ͬ�����������Ϊ(x,y),(x+1,y),(x+2,y)����������ɫ��ͬ����Ϊtrue��
		 */
		for(Ycount=0;Ycount<AllSize.YBlockNum;Ycount++){
			for(Xcount=0;Xcount<AllSize.XBlockNum-2;Xcount++){
				int x=Xcount;
				int xPlus1=Xcount+1;
				int xPlus2=Xcount+2;
				boolean FirstEqual=(Block.Buttons[Ycount][x].PictureNum==Block.Buttons[Ycount][xPlus1].PictureNum);
				boolean SecondEqual=(Block.Buttons[Ycount][xPlus1].PictureNum==Block.Buttons[Ycount][xPlus2].PictureNum);
				if(FirstEqual&&SecondEqual){
					isYLine=true;
				}
			}
		}
		return isYLine;
	}
	private static boolean CanCancel(){
		/*
		 * �ж��Ƿ����������ͼ�����������򷵻�false
		 * 
		 * ȡ��һ����(x,y)
		 * �жϿ������Էֳ����������
		 * ���1��(x,y+1)��ɫ��ͬ��(x-1,y-1)(x+1,y-1)(x-1,y+2)(x+1,y+2)����һ����ͬ�����������������
		 * ���2��(x+1,y)��ͬ��(x-1,y-1)(x-1,y+1)(x+2,y-1)(x+2,y+1)��һ����ͬ
		 * ���3��(x-1,y-1)��(x+1,y-1)
		 * ���4��(x+1,y-1)(x+1,y+1)
		 * ���5��(x+1,y+1)(x-1,y+1)
		 * ���6��(x-1,y+1)(x-1.y-1)
		 */
		boolean CanCancel=false;
		int XYPictureNum=0;
		boolean Condition1=false;
		boolean Condition2=false;
		boolean Condition3=false;
		boolean Condition4=false;
		boolean Condition5=false;
		boolean Condition6=false;
		
		
		for(int y=0;y<AllSize.YBlockNum;y++)
			for(int x=0;x<AllSize.XBlockNum;x++){
				XYPictureNum=Block.Buttons[y][x].PictureNum;
			
				/*
				 * Condition1
				 * ���1��(x,y+1)��ɫ��ͬ��(x-1,y-1)(x+1,y-1)(x-1,y+2)(x+1,y+2)����һ����ͬ�����������������
				 */
				{
					if(y!=AllSize.YBlockNum-1){
						int XYPlus1PictureNum=Block.Buttons[y+1][x].PictureNum;
						if(XYPlus1PictureNum==XYPictureNum){
							if(x==0&&y==0){
								//���϶���
								int XPlus1YPlus2PictureNum=Block.Buttons[y+2][x+1].PictureNum;						    	
								if(XPlus1YPlus2PictureNum==XYPictureNum)
									Condition1=true;
							}
							else if(x==AllSize.XBlockNum-1&&y==0){
								//���϶���
								int XSub1YPlus2PictureNum=Block.Buttons[y+2][x-1].PictureNum;
								if(XSub1YPlus2PictureNum==XYPictureNum)
									Condition1=true;
							}
							else if(y==0&&x!=AllSize.XBlockNum-1&&x!=0){
								//�ϱ߳�����
								int XPlus1YPlus2PictureNum=Block.Buttons[y+2][x+1].PictureNum;	
								int XSub1YPlus2PictureNum=Block.Buttons[y+2][x-1].PictureNum;
								if(XPlus1YPlus2PictureNum==XYPictureNum||XSub1YPlus2PictureNum==XYPictureNum)
									Condition1=true;
							}
							else if(x==0&&y!=0&&y!=AllSize.YBlockNum-2){
								//��߳�����
								int XPlus1YSub1PictureNum=Block.Buttons[y-1][x+1].PictureNum;
								int XPlus1YPlus2PictureNum=Block.Buttons[y+2][x+1].PictureNum;
								if(XPlus1YSub1PictureNum==XYPictureNum||XPlus1YPlus2PictureNum==XYPictureNum)
									Condition1=true;
							}
							else if(x==AllSize.XBlockNum-1&&y!=0&&y!=AllSize.YBlockNum-2){
								//�ұ߳�����
								int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
								int XSub1YPlus2PictureNum=Block.Buttons[y+2][x-1].PictureNum;
								if(XSub1YSub1PictureNum==XYPictureNum||XSub1YPlus2PictureNum==XYPictureNum)
									Condition1=true;
							}
							else if(y==AllSize.YBlockNum-2&&x==0){
								//���±߽�
								int XPlus1YSub1PictureNum=Block.Buttons[y-1][x+1].PictureNum;
								if(XPlus1YSub1PictureNum==XYPictureNum)
									Condition1=true;
							}
							else if(y==AllSize.YBlockNum-2&&x==AllSize.XBlockNum-1){
								//���±߽�
								int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
								if(XSub1YSub1PictureNum==XYPictureNum)
									Condition1=true;
							}
							else if(y==AllSize.YBlockNum-2&&x!=AllSize.XBlockNum-1&&x!=0){
								//�±߽������
								int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
								int XPlus1YSub1PictureNum=Block.Buttons[y-1][x+1].PictureNum;
								if(XSub1YSub1PictureNum==XYPictureNum||XPlus1YSub1PictureNum==XYPictureNum){
									Condition1=true;
								}
							}
							else{
								//���Ǳ߽��һ�����
								int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
								int XPlus1YSub1PictureNum=Block.Buttons[y-1][x+1].PictureNum;
								int XSub1YPlus2PictureNum=Block.Buttons[y+2][x-1].PictureNum;
								int XPlus1YPlus2PictureNum=Block.Buttons[y+2][x+1].PictureNum;
								if(XSub1YSub1PictureNum==XYPictureNum||XPlus1YSub1PictureNum==XYPictureNum||XSub1YPlus2PictureNum==XYPictureNum||XPlus1YPlus2PictureNum==XYPictureNum)
									Condition1=true;
							}
						}	
					}	
				}
					
				 /*
				  * Condition2
				  * ���2��(x+1,y)��ͬ��(x-1,y-1)(x-1,y+1)(x+2,y-1)(x+2,y+1)��һ����ͬ
				  */
				 {
					  if(x!=AllSize.XBlockNum-1){
						  int XPlus1YPictureNum=Block.Buttons[y][x+1].PictureNum;
						  if(XPlus1YPictureNum==XYPictureNum){
							 if(x==0&&y==0){
								//���϶���
								int XPlus2YPlus1PictureNum=Block.Buttons[y+1][x+2].PictureNum;
								if(XYPictureNum==XPlus2YPlus1PictureNum)
									Condition2=true;
							}
							 else if(x==0&&y==AllSize.YBlockNum-1){
								//���¶���
								int XPlus2YSub1PictureNum=Block.Buttons[y-1][x+2].PictureNum;
								if(XYPictureNum==XPlus2YSub1PictureNum)
									Condition2=true;
							}
							else if(x==0&&y!=0&&y!=AllSize.YBlockNum-1){
								//��߳�����
								int XPlus2YPlus1PictureNum=Block.Buttons[y+1][x+2].PictureNum;
								int XPlus2YSub1PictureNum=Block.Buttons[y-1][x+2].PictureNum;
								if(XPlus2YPlus1PictureNum==XYPictureNum||XPlus2YSub1PictureNum==XYPictureNum)
									Condition2=true;
							}
							else if(x!=0&&y==0&&x!=AllSize.XBlockNum-2){
								//�ϱ߳�����
								int XPlus2YPlus1PictureNum=Block.Buttons[y+1][x+2].PictureNum;
								int XSub1YPlus1PictureNum=Block.Buttons[y+1][x-1].PictureNum;
								if(XPlus2YPlus1PictureNum==XYPictureNum||XSub1YPlus1PictureNum==XYPictureNum)
									Condition2=true;
							}
							else if(x!=0&&y==AllSize.YBlockNum-1&&x!=AllSize.XBlockNum-2){
								//�±߳�����
								int XPlus2YSub1PictureNum=Block.Buttons[y-1][x+2].PictureNum;
								int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
								if(XPlus2YSub1PictureNum==XYPictureNum||XSub1YSub1PictureNum==XYPictureNum)
									Condition2=true;
							}
							else if(x==AllSize.XBlockNum-2&&y==0){
								//���϶���
								int XSub1YPlus1PictureNum=Block.Buttons[y+1][x-1].PictureNum;
								if(XSub1YPlus1PictureNum==XYPictureNum)
									Condition2=true;
							}
							else if(x==AllSize.XBlockNum-2&&y==AllSize.YBlockNum-1){
								//���¶���
								int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
								if(XSub1YSub1PictureNum==XYPictureNum)
									Condition2=true;
							}
							else if(x==AllSize.XBlockNum-2&&y!=0&&y!=AllSize.YBlockNum-1){
								//�ұ߳�����
								int XSub1YPlus1PictureNum=Block.Buttons[y+1][x-1].PictureNum;
								int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
								if(XSub1YPlus1PictureNum==XYPictureNum||XSub1YSub1PictureNum==XYPictureNum)
									Condition2=true;
							}
							else{
								//һ�����
								int XSub1YPlus1PictureNum=Block.Buttons[y+1][x-1].PictureNum;
								int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
								int XPlus2YPlus1PictureNum=Block.Buttons[y+1][x+2].PictureNum;
								int XPlus2YSub1PictureNum=Block.Buttons[y-1][x+2].PictureNum;
								if(XSub1YPlus1PictureNum==XYPictureNum||XSub1YSub1PictureNum==XYPictureNum||XPlus2YPlus1PictureNum==XYPictureNum||XPlus2YSub1PictureNum==XYPictureNum)
									Condition2=true;
							}
						}
					  }
				 }
				   
				 /*
				  * Condition3
				  * ���3��(x-1,y-1)��(x+1,y-1)
		          */
				 {
				     if(x!=0&&x!=AllSize.XBlockNum-1&&y!=0){
				    	 int XSub1YSub1PictureNum=Block.Buttons[y-1][x-1].PictureNum;
				         int XPlus1YSub1PictureNum=Block.Buttons[y-1][x+1].PictureNum;
				         if(XSub1YSub1PictureNum==XYPictureNum&&XPlus1YSub1PictureNum==XYPictureNum)
				        	 Condition3=true;
				     }
				 }
				    
			     /*
				  * Condition4
				  * ���4��(x+1,y-1)(x+1,y+1)
				  */
				 {
					 if(x!=AllSize.XBlockNum-1&&y!=0&&y!=AllSize.YBlockNum-1){
						 int XPlus1YSub1PictureNum=Block.Buttons[y-1][x+1].PictureNum;
						 int XPlus1YPlus1PictureNum=Block.Buttons[y+1][x+1].PictureNum;
						 if(XPlus1YSub1PictureNum==XYPictureNum&&XPlus1YPlus1PictureNum==XYPictureNum)
							 Condition4=true;
					 }
				 }
				     
				 /*
				  * Condition5
				  * ���5��(x+1,y+1)(x-1,y+1)
				  */
				 {
					 if(x!=AllSize.XBlockNum-1&&x!=0&&y!=AllSize.YBlockNum-1){
						 int XPlus1YPlus1PictureNum=Block.Buttons[x+1][y+1].PictureNum;
						 int XSub1YPlus1PictureNum=Block.Buttons[x-1][y+1].PictureNum;
						 if(XPlus1YPlus1PictureNum==XYPictureNum&&XSub1YPlus1PictureNum==XYPictureNum)
							 Condition5=true;
					 }
				 }
				      
				 /*
				  * Condition6
				  * ���6��(x-1,y+1)(x-1.y-1)
				  */
				 {
					 if(x!=0&&y!=0&&y!=AllSize.YBlockNum-1){
						 int XSub1YPlus1PictureNum=Block.Buttons[x-1][y+1].PictureNum;
						 int XSub1YSub1PictureNum=Block.Buttons[x-1][y-1].PictureNum;
						 if(XSub1YPlus1PictureNum==XYPictureNum&&XSub1YSub1PictureNum==XYPictureNum)
							 Condition6=true;
					 }  
				 }
			}
		CanCancel=Condition1||Condition2||Condition3||Condition4||Condition5||Condition6;
		return CanCancel;
	}
}
