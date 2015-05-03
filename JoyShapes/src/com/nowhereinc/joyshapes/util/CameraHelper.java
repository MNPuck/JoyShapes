/*******************************************************************************
 *
 *
 ******************************************************************************/


package com.nowhereinc.joyshapes.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.nowhereinc.joyshapes.game.objects.AbstractGameObject;

public class CameraHelper {

	private static final String TAG = CameraHelper.class.getName();

	private final float MAX_ZOOM_IN = 0.25f;
	private final float MAX_ZOOM_OUT = 10.0f;
	private final float FOLLOW_SPEED = 4.0f;

	private Vector2 position;
	private float zoom;
	private AbstractGameObject target;

	public CameraHelper () {
		position = new Vector2();
		zoom = 1.0f;
	}

	public void update (float deltaTime) {
		if (!hasTarget()) return;

		position.lerp(target.position, FOLLOW_SPEED * deltaTime);
		
		// Prevent camera from moving too far
		
		//position.x = Math.max(3f, position.x);
		//position.x = Math.max(-3f, position.x);
		//position.y = Math.max(3f, position.y);
		//position.y = Math.max(-3f, position.y);
		
		if (position.x > .33f)
			position.x = .33f;
		
		if (position.x < -.33f)
			position.x = -.33f;
		
		if (position.y > .5f)
			position.y = .5f;
		
		if (position.y < -.5f)
			position.y = -.5f;
		
	}

	public void setPosition (float x, float y) {
		this.position.set(x, y);
	}

	public Vector2 getPosition () {
		return position;
	}

	public void addZoom (float amount) {
		setZoom(zoom + amount);
	}

	public void setZoom (float zoom) {
		this.zoom = MathUtils.clamp(zoom, MAX_ZOOM_IN, MAX_ZOOM_OUT);
	}

	public float getZoom () {
		return zoom;
	}

	public void setTarget (AbstractGameObject target) {
		this.target = target;
	}

	public AbstractGameObject getTarget () {
		return target;
	}

	public boolean hasTarget () {
		return target != null;
	}

	public boolean hasTarget (AbstractGameObject target) {
		return hasTarget() && this.target.equals(target);
	}

	public void applyTo (OrthographicCamera camera) {
		camera.position.x = position.x;
		camera.position.y = position.y;
		camera.zoom = zoom;
		camera.update();
	}

}