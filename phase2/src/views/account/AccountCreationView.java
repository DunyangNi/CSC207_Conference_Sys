package views.account;
import controllers.account.RegistrationController;
import enums.AccountTypeEnum;
import enums.ViewEnum;
import presenters.account.RegistrationPresenter;

/**
 * View responsible for Account creation functionality (The one that organizers have access to after logging in)
 */
public class AccountCreationView extends RegistrationView{


    public AccountCreationView(RegistrationController controller, RegistrationPresenter presenter) {
        super(controller, presenter);
    }

    /**
     * Run the view
     */
    public ViewEnum runView() {
        presenter.registrationHeader();
        presenter.registrationMenu();
        AccountTypeEnum accountTypeEnum = getAccountTypeEnum();

        getAccountInfo(accountTypeEnum);

        controller.saveData();

        return ViewEnum.VOID;
    }
}
