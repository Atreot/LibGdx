package com.mygdx.game.Helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;
import java.util.Timer;


public class Player {
    public GameObject pl;  //3d
    public PerspectiveCamera cam;
    public Vector3 posP, camPos, anglP;//позиция
public Vector2 spid;
public ArrayList<Puli>pulList;
Timer timer;

    public Player(GameObject gob) {
        pulList=new ArrayList<Puli>();
        pl = gob;
        posP = new Vector3(0, 0, 0);//позиция игрока
        camPos = new Vector3(0f, 0f, 15f);//позиция камеры
        anglP = new Vector3(0, 0, 0);//поворот игрока оотносительно начального положения
        spid=new Vector2(0,0);

        pl.transform.setToRotation(1, 0, 0, 90);

        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(camPos);
        cam.lookAt(posP);
        //cam.rotate(90,1,0,0);
        cam.near = 0.5f;
        cam.far = 300f;
        cam.update();

    }

    public void updeit(float x, float y, float deltaT) {
if (y!=0 && x!=0) {

    if (y >= 0) {
        if(Math.abs((Math.acos(x))-anglP.x)<Math.PI) {
            anglP.add((-anglP.x + ((float) Math.acos(x))) * 4 * deltaT, 0, 0);
            anglP.y=(-anglP.x + ((float) Math.acos(x))) * 4 * deltaT;
           // cam.rotateAround(posP,new Vector3(0,0,1),((-anglP.x + ((float) Math.acos(x))) * 4 * deltaT)*180/((float)Math.PI));
        }
        else {
            anglP.add((((float)Math.acos(x))+(float)Math.PI * 2-anglP.x)*4*deltaT,0,0);
            anglP.y=(((float)Math.acos(x))+(float)Math.PI * 2-anglP.x)*4*deltaT;
            //anglP.add((anglP.x - ((float) Math.acos(x))) * deltaT, 0, 0);
          //  //cam.rotateAround(posP,new Vector3(0,0,1),((anglP.x - ((float) Math.acos(x))) * deltaT)*180/((float)Math.PI));
        }
    }
    else {
        if(Math.abs(((Math.PI * 2 - Math.acos(x)))-anglP.x)<Math.PI) {
            anglP.add((-anglP.x + ((float) (Math.PI * 2 - Math.acos(x)))) * 4 * deltaT, 0, 0);
            anglP.y=(-anglP.x + ((float) (Math.PI * 2 - Math.acos(x)))) * 4 * deltaT;
            //cam.rotateAround(posP,new Vector3(0,0,1),((-anglP.x + ((float) (Math.PI * 2 - Math.acos(x)))) * 4 * deltaT)*180/((float)Math.PI));
        }
        else {
           // if(((Math.PI * 2 - Math.acos(x)))>anglP.x){
                anglP.add(-(((float)Math.acos(x))+anglP.x)*4*deltaT,0,0);
            anglP.y=-(((float)Math.acos(x))+anglP.x)*4*deltaT;
           // anglP.add((anglP.x - ((float) (Math.PI * 2 - Math.acos(x)))) * deltaT, 0, 0);
          //  //cam.rotateAround(posP,new Vector3(0,0,1),((anglP.x - ((float) (Math.PI * 2 - Math.acos(x)))) * 4 * deltaT)*180/((float)Math.PI));
        }

}
}
if(anglP.x<0)
    anglP.add(((float)Math.PI*2),0,0);
else
        if(anglP.x>((float) 2*Math.PI))
            anglP.add(-((float)Math.PI*2),0,0);



        calculPos(x* deltaT, y* deltaT, deltaT);

    }

    public void calculPos(float x, float y, float deltaT) {
        spid.add(((float)Math.cos(anglP.x))*deltaT,((float)Math.sin(anglP.x))*deltaT);
        if(spid.x>1)
            spid.x=1;
        if(spid.x<-1)
            spid.x=-1;
        if(spid.y>1)
            spid.y=1;
        if(spid.y<-1)
            spid.y=-1;


        posP.add(spid.x*deltaT*10, spid.y*deltaT*10, 0);
        cam.position.add(spid.x*deltaT*10, spid.y*deltaT*10, 0);

       pl.transform.setToRotationRad(0,0,1,anglP.x);
        pl.transform.rotate(1,0,0,90);
        pl.transform.rotate(1,0,0,-anglP.y*100);
//       cam.rotate((lastAngl.x-anglP.x)*180/3.14f,0,1,0);
//        cam.rotate(-(lastAngl.y-a nglP.y)*180/3.14f,1,0,0);


        pl.transform.setTranslation(posP);
//        float y1=posP.y+(camPos.y-posP.y)*((float)Math.cos(anglP.x*deltaT))-(camPos.y-posP.y)*((float)Math.sin(anglP.x*deltaT));
//        float x1=posP.x+(camPos.z-posP.z)*((float)Math.sin(anglP.x*deltaT))+(camPos.x-posP.x)*((float)Math.cos(anglP.x*deltaT));
//        camPos.set(x1,y1,camPos.z);
    //    cam.position.set(camPos);


        cam.lookAt(posP);

        cam.update();

    }

    public  void shut(){

            pulList.add(new Puli(posP.x,posP.y,(float)Math.cos(anglP.x),((float)Math.sin(anglP.x))));

            Gdx.app.log("qwer","qwer ");

    }
}