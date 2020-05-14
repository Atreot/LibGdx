package com.mygdx.game.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import java.util.Vector;


public class Djostyk {
    Texture cicle,styc;
   public Vector2 posC,posS,direction,touch;
    float R;
    int pointer=-1,pointShut=-1;
    boolean isShut;
   public Djostyk(Texture cicle,Texture styc,Vector2 posC,Vector2 posS,float s){
       this.cicle =cicle;
       this.styc=styc;
       this.posC=posC;
       this.posS=posS;
       R=s/2;
       direction=new Vector2();
       touch=new Vector2();
    }
    public void updeit(float x,float y,boolean isDownToch, int pointer,Player player){
        Vector2 pos= new Vector2(x,y);
        if(isContain(pos)&&isDownToch&&this.pointer==-1)this.pointer=pointer;
        if(this.pointer==pointer&&isDownToch) atControl(x-R,y-R);
        if (!isDownToch&&pointer==this.pointer){
            this.pointer=-1;
            posS.set(posC);
            direction.set(0,0);
        }

        if(isContainShut(pos)&&isDownToch&&this.pointShut==-1)this.pointShut=pointer;
        if(this.pointShut==pointer&&isDownToch) player.shut();
        if (!isDownToch&&pointer==this.pointShut){
            this.pointShut=-1;
        }
    }
    public void draw(SpriteBatch batch){
       batch.draw(cicle,posC.x,posC.y,R*2,R*2);
       batch.draw(styc,posS.x,posS.y,R*2,R*2);
       batch.draw(styc, Gdx.graphics.getWidth()-350,50,R*2,R*2);
    }
    public void  atControl(float x,float y){
       posS.set(x,y);
       float dx=posC.x-posS.x;
       float dy=posC.y-posS.y;
       float dist=(float)Math.sqrt(dx*dx+dy*dy);
       direction.set(-(dx/dist),-(dy/dist));
    }

public boolean isContain(Vector2 pos){
    float dx=posC.x+R-pos.x;
    float dy=posC.y+R-pos.y;

    return dx*dx+dy*dy<=R*R;
}
boolean  isContainShut(Vector2 pos){
    float dx=Gdx.graphics.getWidth()-350+R-pos.x;
    float dy=50+R-pos.y;
    //Gdx.app.log("qwer","qwer "+((dx*dx+dy*dy)<=R*R));
    return dx*dx+dy*dy<=R*R;
}

}
