package com.mygdx.game.Helpers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Puli {
    int id;
    float TimeLife,time;
    boolean Visibl;
    Vector2 pos,speed;
    Model model;
   public ModelInstance instance;
    Puli(float x,float y,float sX,float sY){
        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createSphere(1f, 1f, 1f, 7,7,
                new Material(ColorAttribute.createDiffuse(Color.GREEN)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);

        instance = new ModelInstance(model);
        instance.transform.setToRotation(Vector3.Z, 120);
        id=0;
        pos= new Vector2(x,y);
        speed=new Vector2(sX,sY);
    }
   public void updeit(){
pos.add(speed);
instance.transform.setTranslation(pos.x,pos.y,0);
   }
   public  void draw(){

   }
}
