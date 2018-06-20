package CookBookEntity;
import DigitalCookbook.CookBookApp;
public class EntityTest {
	public static void main(String[]  args) {
		Recipe recipe = CookBookApp.createGongBaoJiding();
		System.out.println("heo");
		recipe.show();
	}
}
