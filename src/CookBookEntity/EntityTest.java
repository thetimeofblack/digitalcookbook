package CookBookEntity;
import DigitalCookbook.CookBookApp;
public class EntityTest {
	public void main(String[]  args) {
		Recipe recipe = CookBookApp.createGongBaoJiding();
		recipe.show();
	}
}
