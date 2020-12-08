package views.account;

import controllers.account.AccountRegistrationController;
import enums.AccountTypeEnum;
import gateways.DataManager;
import presenters.account.RegistrationPresenter;

public class AccountCreationView extends RegistrationView{


    public AccountCreationView(AccountRegistrationController controller, RegistrationPresenter presenter) {
        super(controller, presenter);
    }

    public void runView() {
        presenter.startPrompt();
        AccountTypeEnum enumCommand = getType();

        accountInfoView(enumCommand);

        controller.saveData();
    }
}
