package views.account;

import controllers.account.RegistrationController;
import enums.AccountTypeEnum;
import enums.ViewEnum;
import presenters.account.RegistrationPresenter;

public class AccountCreationView extends RegistrationView{


    public AccountCreationView(RegistrationController controller, RegistrationPresenter presenter) {
        super(controller, presenter);
    }

    public ViewEnum runView() {
        presenter.startPrompt();
        AccountTypeEnum accountTypeEnum = getAccountTypeEnum();

        getAccountInfo(accountTypeEnum);

        controller.saveData();

        return ViewEnum.VOID;
    }
}
