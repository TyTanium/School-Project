package Space.entity;

public class Physics {

	public static boolean collision(EntityA enta, EntityB entb) {
		if (enta.getBounds().intersects(entb.getBounds())) {
			return true;
		} else return false;
	}

	public static boolean collision(EntityB entb, EntityA enta) {
		if (entb.getBounds().intersects(enta.getBounds())) {
			return true;
		} else return false;
	}
}
