package gateway;

import java.io.*;

public class AccountDataManager {
    private final String accountPath;

    public AccountDataManager(String accountPath) {
        this.accountPath = accountPath;
    }

    private void saveSerializable(String filePath, Serializable ser) throws IOException {
        OutputStream file = new FileOutputStream(filePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);
        output.writeObject(ser);
        output.close();
        buffer.close();
        file.close();
    }

    public void saveManager(String managerName, String filePath, Serializable manager) {
        try {
            saveSerializable(filePath, manager);
            System.out.println("Saved " + managerName);
        } catch (IOException e) {
            System.out.printf("Failed to save the %s.%n", managerName);
        }
    }

    public void saveAllManagers(AccountManager accountManager) {
        saveManager("AccountManager", accountPath, accountManager);
    }

    private Object readObject(String path) throws IOException, ClassNotFoundException {
        InputStream file = new FileInputStream(path);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);
        Object ob = input.readObject();
        input.close();
        buffer.close();
        file.close();
        return ob;
    }

    public AccountManager readAccountManager() {
        try{
            return (AccountManager) readObject(accountPath);
        } catch (IOException e) {
            System.out.println("Could not read AccountManager, creating a new AccountManager.");
            return new AccountManager();
        } catch (ClassNotFoundException e) {
            System.out.println("AccountManager not found, creating a new AccountManager.");
            return new AccountManager();
        }
    }
}
