package Game;
import javax.swing.*;
import java.awt.*;
public class Button extends JButton implements Comparable<Button>{

	/**
	 * �������а�ť��ͼƬ��λ�á���ɫ�ȡ�
	 */
	private static final long serialVersionUID = -1920604175552962458L;
	public int setY=0;
	public int setX=0;
	public int PictureNum=0;
	Button(){
		addActionListener(new ButtonActionListener());
		setPreferredSize(new Dimension(AllSize.BlockWidth,AllSize.BlockHeight));
		setBackground(Color.DARK_GRAY);
		setBorderPainted(false);
	}
	@Override
	public int compareTo(Button that) {
		// TODO �Զ����ɵķ������
		if(this.setX>that.setX){
			return 1;
		}else if(this.setX<that.setX){
			return -1;
		}else if(this.setY>that.setY){
			return 1;
		}else if(this.setY<that.setY){
			return -1;
		}
		return 0;
	}
}
