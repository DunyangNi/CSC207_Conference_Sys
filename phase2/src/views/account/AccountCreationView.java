package views.account;

import enums.AccountTypeEnum;
import gateways.DataManager;

public class AccountCreationView extends RegistrationView{

    public AccountCreationView(DataManager dm) {
        super(dm);
    }

    public void runView() {
        presenter.startPrompt();
        AccountTypeEnum enumCommand = getType();

        accountInfoView(enumCommand);

        controller.saveData();
    }
}
