package se.chalmers.TDA367.group13.model.entities.boss;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.model.entities.AbstractMoveableEntityState;
import se.chalmers.TDA367.group13.model.entities.player.Player;
import se.chalmers.TDA367.group13.model.entities.projectile.BossProjectile;
import se.chalmers.TDA367.group13.model.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public class Boss_1 extends AbstractBoss {
	private XMLPackedSheet bossSheet;
	private Image laserBegin, laserBeam;
	private Image[] mouthOpen, mouthClose;
	private Animation openMouth, closeMouth;
	private int yOffset = 80;
	private AbstractBossState up, down, shooting;
	public static float bossScale = 5, projectileScale = 1.25f;
	
	public Boss_1(float x, float y) throws SlickException {
		super(x, y, new Image("/res/Sprites/Bosses/1/boss_1_head.png"));
		bossSheet = new XMLPackedSheet("/res/Sprites/Bosses/1/boss_1_sheet.png","/res/Sprites/Bosses/1/boss_1_sheet.xml");
		direction = Direction.LEFT;
		initAnimations();
		up = new BossMoving(closeMouth);
		up.setVelocity(new Vector2f(0,-0.2f));
		down = new BossMoving(closeMouth);
		down.setVelocity(new Vector2f(0,0.2f));
		shooting = new BossShooting(openMouth);
		state = shooting;
		healthBar = new BossHealthBar("KIM TRON IL");
	}
	
	@Override
	public void render(Graphics g){
		super.render(g);
		g.drawAnimation(state.getAnimation(Direction.LEFT), getX(), getY());
		if(state == getShootingState()){
			g.drawImage(laserBegin,x-laserBegin.getWidth(), y+yOffset);
		}
	}
	


	public void initAnimations() throws SlickException{
		int animationSpeed = 20;
		image = bossSheet.getSprite("still.png");

		mouthOpen = new Image[]{
				bossSheet.getSprite("open1.png"),
				bossSheet.getSprite("open2.png"),
				bossSheet.getSprite("open3.png"),
				bossSheet.getSprite("open4.png"),
				bossSheet.getSprite("open5.png")
		};

		mouthClose = new Image[]{
				bossSheet.getSprite("open5.png"),
				bossSheet.getSprite("open4.png"),
				bossSheet.getSprite("open3.png"),
				bossSheet.getSprite("open2.png"),
				bossSheet.getSprite("open1.png")
		};

		laserBegin = new Image("/res/Sprites/Bosses/1/laser_begin.png");
		laserBeam = new Image("/res/Sprites/Bosses/1/laser_beam.png");

		resize(bossScale);

		openMouth = new Animation(mouthOpen, animationSpeed);
		closeMouth = new Animation(mouthClose, animationSpeed);
		openMouth.setLooping(false);
		closeMouth.setLooping(false);

	}




	public void resize(float scale){
		super.resize(scale);
		resizeBeam(projectileScale);
		resizeImages(mouthClose, scale);
		resizeImages(mouthOpen, scale);
	}

	public void resizeBeam(float scale){
		laserBegin.setFilter(Image.FILTER_NEAREST);
		laserBegin = laserBegin.getScaledCopy(scale);
		laserBeam.setFilter(Image.FILTER_NEAREST);
		laserBeam = laserBeam.getScaledCopy(scale);
	}

	public Projectile fireLaser(){
		return new BossProjectile(x-laserBeam.getWidth()-laserBegin.getWidth(), y+yOffset, (float)Math.PI, direction);
	}
	
	public void update(Player p){
		if(((AbstractBossState)state).isNextState()){
			if(state == shooting){
				setState(getMovingState(p));
			} else {
				setState(shooting);
			}
		}
	}
	
	public void setState(AbstractMoveableEntityState state){
		this.state = state;
		((AbstractBossState)state).setStateStartedMillis();
		state.getAnimation(direction).restart();
	}
	public AbstractBossState getUpState(){
		return (AbstractBossState) up;
	}
	
	public AbstractBossState getDownState(){
		return (AbstractBossState) down;
	}
	
	
	public AbstractBossState getShootingState(){
		return (AbstractBossState) shooting;
	}
	
	public AbstractBossState getState(){
		return (AbstractBossState) state;
	}

	public AbstractBossState getMovingState(Player p) {	
		if(p.getCenterY() < this.getCenterY()){
			return up;
		} else {
			return down;
		}
		
	}

	@Override
	public void move(int delta) {
		moveY(delta);
		
	}
	


}
