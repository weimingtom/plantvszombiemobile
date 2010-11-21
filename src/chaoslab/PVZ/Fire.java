package chaoslab.PVZ;

/*
 * this is one application of the particle systems
 */
import java.lang.Math;
import android.graphics.*;

public class Fire {
	//some constant to be used
	protected final int INNER = 0;
	protected final int MID = 1;
	protected final int OUTER = 2;
	//particle to simulate fire
	protected Parti_obj[][] parti_obj = null;
	protected int parti_num = 0;
	//the fire size
	protected double width = 0;
	protected double height = 0;
	//position of the fire
	protected Position pos;
	
	
	public Fire(double w, //the width of the fire
			double h,     //the height of the fire
			Position p,
			int style  //TODO:the style of the fire
			){
		width = w;
		height = h;
		pos = p;
		parti_num = 25;
		parti_obj = new Parti_obj[3][parti_num];
		for(int i=0;i<3;++i){
			for(int j=0;j<parti_num;++j){
				parti_obj[i][j] = new Parti_obj();
			}
		}
		init();
	}
	/*
	 * initialize the fire particles
	 */
	public void init(){
		//initialize the inner particles
		for(int i=0;i<parti_num;++i){
			parti_obj[INNER][i].x = width/3 + (float)(width/3*Math.random());
			parti_obj[INNER][i].y = Math.random()*height/2*Math.sin(((parti_obj[INNER][i].x-width/3)*Math.PI*3/width));
			parti_obj[INNER][i].color = 0xffff9d00;
			parti_obj[INNER][i].life = (int)Math.random()*100;
			parti_obj[INNER][i].speedX = 5+Math.random()*5;
			parti_obj[INNER][i].speedY = 5+Math.random()*5;
			parti_obj[INNER][i].accX = 2+Math.random();
			parti_obj[INNER][i].accY = 2+Math.random();
		}
		//initialize the middle particles
		for(int i=0;i<parti_num;++i){
			parti_obj[MID][i].x = (float)(width*Math.random());
			if(parti_obj[MID][i].x >= width*2/3 || parti_obj[MID][i].x <= width/3){
				parti_obj[MID][i].y = Math.random()*height*3/4*Math.sin((parti_obj[MID][i].x*Math.PI/width));
			}else{
				parti_obj[MID][i].y = height/2*Math.sin(((parti_obj[INNER][i].x-width/3)/Math.PI*3/width)) +
								 Math.random()*(height*3/4*Math.sin((parti_obj[MID][i].x*Math.PI/width))-
										 height/2*Math.sin(((parti_obj[INNER][i].x-width/3)/Math.PI*3/width)));
			}
			parti_obj[MID][i].color = 0xffffa734;
			parti_obj[MID][i].life = (int)Math.random()*100;
			parti_obj[MID][i].speedX = 5+Math.random()*5;
			parti_obj[MID][i].speedY = 5+Math.random()*5;
			parti_obj[MID][i].accX = 2+Math.random();
			parti_obj[MID][i].accY = 2+Math.random();
		}
		//initialize the outer particles
		for(int i=0;i<parti_num;++i){
			parti_obj[OUTER][i].x = (float)(width*Math.random());
			parti_obj[OUTER][i].y = height*3/4*Math.sin((parti_obj[MID][i].x*Math.PI/width)) +
									Math.random()*(height/4*Math.sin((parti_obj[MID][i].x*Math.PI/width)));
			parti_obj[OUTER][i].color = 0xfff55700;
			parti_obj[OUTER][i].life = (int)Math.random()*100;
			parti_obj[OUTER][i].speedX = Math.random()*5;
			parti_obj[OUTER][i].speedY = Math.random()*5;
			parti_obj[OUTER][i].accX = Math.random();
			parti_obj[OUTER][i].accY = Math.random();
		}
	}
	public void update(){
		for(int j=0;j<parti_num;++j){
			Parti_obj obj = parti_obj[INNER][j];
			obj.x += obj.speedX;
			obj.y += obj.speedY;
			obj.speedX += obj.accX;
			obj.speedY += obj.accY;
			if(obj.x < width/3 ||
					obj.x > width*2/3||
					obj.y - height/4> height/2*Math.sin(((obj.x-width/3)/Math.PI*3/width))||
					obj.y < 0){
				reset(obj,INNER);
			}
		}
		for(int j=0;j<parti_num;++j){
			Parti_obj obj = parti_obj[MID][j];
			obj.x += obj.speedX;
			obj.y += obj.speedY;
			obj.speedX += obj.accX;
			obj.speedY += obj.accY;
			if(obj.x >= width*2/3 || obj.x <= width/3){
				if(obj.x < 0 ||
						obj.x > width||
						obj.y - height/4> height*3/4*Math.sin((obj.x*Math.PI/width))||
						obj.y < 0){
					reset(obj,MID);
				}
			}else{
				if(obj.y - height/4> height*3/4*Math.sin((obj.x*Math.PI/width))||
						obj.y + height/4< height/2*Math.sin(((obj.x-width/3)/Math.PI*3/width))||
						obj.y < 0){
					reset(obj,MID);
				}
			}
		}
		for(int j=0;j<parti_num;++j){
			Parti_obj obj = parti_obj[OUTER][j];
			obj.x += obj.speedX;
			obj.y += obj.speedY;
			obj.speedX += obj.accX;
			obj.speedY += obj.accY;
			if(obj.x < 0 ||
					obj.x > width||
					obj.y - height/4> height*Math.sin((obj.x*Math.PI/width))||
					obj.y + height/4< height*3/4*Math.sin((obj.x*Math.PI/width))||
					obj.y < 0){
				reset(obj,OUTER);
			}
		}
	}
	public void reset(Parti_obj obj,int type){
		//initialize the inner particles
		if(type == INNER){
			obj.x = width/3 + (float)(width/3*Math.random());
			obj.y = Math.random()*height/2*Math.sin(((obj.x-width/3)*Math.PI*3/width));
		}
		//initialize the middle particles
		else if(type == MID){
			obj.x = (float)(width*Math.random());
			if(obj.x >= width*2/3 || obj.x <= width/3){
				obj.y = Math.random()*height*3/4*Math.sin((obj.x*Math.PI/width));
			}else{
				obj.y = height/2*Math.sin(((obj.x-width/3)/Math.PI*3/width)) +
								 Math.random()*(height*3/4*Math.sin((obj.x*Math.PI/width))-
										 height/2*Math.sin(((obj.x-width/3)/Math.PI*3/width)));
			}

		}
		//initialize the outer particles
		else if(type == OUTER){
			obj.x = (float)(width*Math.random());
			obj.y = height*3/4*Math.sin((obj.x*Math.PI/width)) +
									Math.random()*(height/4*Math.sin((obj.x*Math.PI/width)));

		}
		obj.life = (int)Math.random()*100;
		if(obj.x%2==0){
			obj.speedX = 5*Math.random();
			obj.speedY = 5*Math.random();
		}else{
			obj.speedX = -5*Math.random();
			obj.speedY = -5*Math.random();
		}
		if(obj.y%2 == 0){
			obj.accX = Math.random();
			obj.accY = Math.random();
		}else{
			obj.accX = -Math.random();
			obj.accY = -Math.random();
		}
	}
	public void doDraw(Canvas canvas, float scaleX, float scaleY, Paint paint){
		Paint p = new Paint();
		for(int i=2;i>=0;--i){
			p.setColor(parti_obj[i][0].color);
			for(int j=parti_num-1;j>=0;--j){
				canvas.drawCircle((float)(pos.x + 20 + parti_obj[i][j].x)*scaleX, 
						(float)(pos.y - parti_obj[i][j].y)*scaleY, 
						 5f,p);
			}
		}
	}
}

