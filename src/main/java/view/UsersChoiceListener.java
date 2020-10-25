package view;

/**
 * Interface that needs to be implemented by every class that
 * would serve as a controller between the {@link View} and the {@link model.ListOfTasks}
 *
 * @author lukaszczajkowski
 */
public interface UsersChoiceListener {
	void choiceMade(UsersChoiceEvent event);
}
