package se.chalmers.TDA367.group13.model.entities.enemies.boss2;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.XMLPackedSheet;
import org.newdawn.slick.geom.Vector2f;

import se.chalmers.TDA367.group13.model.entities.AbstractMoveableEntityState;
import se.chalmers.TDA367.group13.model.entities.enemies.boss.AbstractBoss;
import se.chalmers.TDA367.group13.model.entities.enemies.boss.AbstractBossState;
import se.chalmers.TDA367.group13.model.entities.enemies.boss.BossHealthBar;
import se.chalmers.TDA367.group13.model.entities.enemies.boss.BossMoving;
import se.chalmers.TDA367.group13.model.entities.enemies.boss.BossShooting;
import se.chalmers.TDA367.group13.model.entities.player.Player;
import se.chalmers.TDA367.group13.model.entities.projectile.BossProjectile;
import se.chalmers.TDA367.group13.model.entities.projectile.Projectile;
import se.chalmers.TDA367.group13.util.Direction;

public class Boss_2 extends AbstractBoss {
	private XMLPackedSheet bossSheet;
	private Image laserBegin, laserBeam;
	private Image[] mouthOpen, mouthClose;
	private Animation openMouth, closeMouth;
	private int xOffset = 100;
	private AbstractBossState left, right, shooting;
	public static float bossScale = 5, projectileScale = 1.25f;
	
	public Boss_2(float x, float y) throws SlickException {
		super(x, y, new Image("/res/Sprites/Bosses/2/boss_2_Sheet.png"));
		bossSheet = new XMLPackedSheet("/res/Sprites/Bosses/2/boss_2_Sheet.png","/res/Sprites/Bosses/2/boss_2_Sheet.xml");
		direction = Direction.LEFT;
		initAnimations();
		left = new BossMoving(closeMouth);
		left.setVelocity(new Vector2f(-0.2f,0));
		right = new BossMoving(closeMouth);
		right.setVelocity(new Vector2f(0.2f,0));
		shooting = new BossShooting(openMouth);
		state = shooting;
		healthBar = new BossHealthBar("KIM TRON UN");
	}
	
	@Override
	public void render(Graphics g){
		super.render(g);
		g.drawAnimation(state.getAnimation(Direction.LEFT), getX(), getY());
		if(state == getShootingState()){
			g.drawImage(laserBegin,x+xOffset, y+getHeight()+12);
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

		laserBegin = new Image("/res/Sprites/Bosses/2/laser_begin.png");
		laserBeam = new Image("/res/Sprites/Bosses/2/laser_beam.png");

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
		return new BossProjectile(x + xOffset + 12, y + getHeight() + laserBegin.getHeight(), (float)(Math.PI*1.5), direction);
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
	public AbstractBossState getLeftState(){
		return (AbstractBossState) left;
	}
	
	public AbstractBossState getRightState(){
		return (AbstractBossState) right;
	}
	
	
	public AbstractBossState getShootingState(){
		return (AbstractBossState) shooting;
	}
	
	public AbstractBossState getState(){
		return (AbstractBossState) state;
	}

	public AbstractBossState getMovingState(Player p) {	
		if(p.getCenterX() < this.getCenterX()){
			return left;
		} else {
			return right;
		}
		
	}

	@Override
	public void move(int delta) {
		moveX(delta);
		
	}

}
