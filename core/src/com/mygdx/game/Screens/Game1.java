package com.mygdx.game.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Helpers.Djostyk;
import com.mygdx.game.Helpers.Puli;
import com.mygdx.game.MyGame;


public class Game1 implements Screen {
    float x,y,z;
    MyGame m;
    Djostyk dj;
    BitmapFont bf;
public Game1(MyGame my){
        m=my;
        dj=new Djostyk(m.cicle,m.styc,new Vector2(50,50),new Vector2(50,50),300);

   bf= new BitmapFont();
   bf.setColor(Color.BLUE);

    }


    @Override
    public void show() {
Gdx.input.setInputProcessor(new InputProcessor(){
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        screenY=Gdx.graphics.getHeight()-screenY;
        multituch(screenX,screenY,true,pointer);



        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        screenY=Gdx.graphics.getHeight()-screenY;
        multituch(screenX,screenY,false,pointer);

        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        screenY=Gdx.graphics.getHeight()-screenY;
        multituch(screenX,screenY,true,pointer);




        return false;
    }
///////////////////////////////////////////////////////////////////////////////
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(int amount) { return false; }
    @Override public boolean keyDown(int keycode) { return false; }
    @Override public boolean keyUp(int keycode) { return false; }
    @Override public boolean keyTyped(char character) { return false; }
});}

    @Override
    public void render(float delta) {
        if (m.loading && m.assets.update()) m.doneLoading();
        else {
            try {
                updeitSpr();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

try {


    m.modelBatch.begin(m.player.cam);

    ////////////////////////////////
//        for (final GameObject instance : m.instances) {
//                m.modelBatch.render(instance, m.environment);
//
//        }
    ////////////////////////////////

        m.modelBatch.render(m.player.pl, m.environment);
    for (Puli p:m.player.pulList){
        //p.updeit();
        m.modelBatch.render(p.instance,m.environment);
    }


    // m.modelBatch.render(m.instances, m.environment);
    if (m.space != null)
        m.modelBatch.render(m.space);
    m.modelBatch.end();

    m.batch.begin();
    dj.draw(m.batch);
    bf.draw(m.batch, " " +" " + m.player.anglP.y, 100, 100);
    m.batch.end();
} catch (Exception e) {
    e.printStackTrace();
}

        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void multituch(float x,float y,boolean isDownToch, int pointer){
    for(int i=0; i<5; i++){
        dj.updeit(x, y, isDownToch, pointer,m.player);
    }
    }
public void updeitSpr(){

//   m.instances.get(0).transform.setToRotation(0,1,0,-90-45*dj.direction.x);
//   m.instances.get(0).transform.rotate(0,0,1,-45*dj.direction.y);

//    m.instances.get(0).transform.setTranslation(x-=(dj.direction.x),(y-=dj.direction.y),z+=1-Math.sqrt(dj.direction.x*dj.direction.x+dj.direction.y*dj.direction.y));
//    m.cam.position.set(x+0+dj.direction.x*15, y+20-dj.direction.y*15, z-15f);
//    m.cam.lookAt(x+0-dj.direction.x*15,y+0+dj.direction.y*15,z+20f);
//  //  m.cam.rotateAround(new Vector3(x,y,-15f),new Vector3(0,1,0),dj.direction.x);//Это решение проблемы!!!
//    m.cam.rotate(-dj.direction.x,0,0,1);
//    m.cam.rotate(-dj.direction.y,1,0,0);
//    m.cam.update();


   m.player.updeit(dj.direction.x,dj.direction.y,Gdx.graphics.getDeltaTime());
    for (Puli p:m.player.pulList){
        p.updeit();
    }

    // m.player.updeit(0,1,Gdx.graphics.getDeltaTime());
    x+=0.00001;
}

}
