import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    // Main function.
    public static void main(String[] args) throws IOException {
        menuHandler();
    }

    /**
     * Checks if the user's input is not null and returns it.
     * @return The user's input.
     * @throws IOException If I/O exception happens during the I/O transaction.
     */
    private static String getUserInput() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // Stores user input.
        String userInput = null;
        // Checks for null pointer exceptions.
        boolean error;

        // Repeats if there is a null pointer exception.
        do {
            // User gets prompt.
            try {
                System.out.print(">>> ");
                userInput = bufferedReader.readLine();
                // User's input is null.
                if (userInput.isEmpty()) {
                    throw new NullPointerException();
                }
                error = false;
            }
            // User is asked to fill the field.
            catch (NullPointerException e) {
                System.out.println("\n[!] You forgot to fill the field. Please try again.");
                error = true;
            }
        }
        while (error);

        // User input is returned.
        return userInput;
    }

    /**
     * Prompts the user to insert the contact information that will be used.
     * @return User's contact information input.
     * @throws IOException If I/O exception happens during the I/O transaction.
     */
    private static String[] getContactInformation() throws IOException {
        // Stores the contact information
        String[] contactInformation = new String[2];

        // Ask for phone number.
        System.out.println("\n[?] Please insert the contact's phone number.");
        contactInformation[0] = getUserInput();
        // Ask for name.
        System.out.println("\n[?] Please insert the contact's name.");
        contactInformation[1] = getUserInput();

        // Contact information input.
        return contactInformation;
    }

    /**
     * Prompts the user to insert the menu option that will be used.
     * @return User's chosen menu option.
     * @throws IOException If I/O exception happens during the I/O transaction.
     */
    private static String getMenuOption() throws IOException {
        // Stores chosen menu option.
        String menuOption;

        // User is prompted.
        System.out.println("\n[?] Please insert the menu option you want to use.");
        menuOption = getUserInput();

        // User input is returned.
        return menuOption;
    }

    /**
     * Checks if the user's chosen menu option can be used to modify the used address book.
     * @param addressBook The address book that is being used.
     * @param menuOption User's chosen menu option.
     * @throws IOException If I/O exception happens during the I/O transaction.
     */
    private static void menuOptionHandler(AddressBook addressBook, String menuOption) throws IOException {

        // Stores the user's contact information input.
        String[] contactInformation;

        switch (menuOption) {
            // Prints the contact list.
            case "a" -> addressBook.list();
            // Adds a contact to the list.
            case "b" -> {
                // Gets contact information.
                contactInformation = getContactInformation();
                // Creates and adds new contact.
                addressBook.create(contactInformation[0], contactInformation[1]);
                // Notification message.
                System.out.println("\n[*] The contact was successfully added to the list.");
            }
            // Deletes a contact from the list.
            case "c" -> {
                // Gets contact information.
                contactInformation = getContactInformation();

                // Contact exists.
                if (addressBook.exists(contactInformation[0])) {
                    // Deletes the contact.
                    addressBook.delete(contactInformation[0], contactInformation[1]);
                    // Notification message.
                    System.out.println("\n[*] The contact was successfully deleted to the list.");
                }
                // Contact doesn't exist.
                else {
                    // Notification message.
                    System.out.println("\n[!] The contact you entered doesn't exist.");
                }
            }
            // Ends the program.
            case "d" -> {
                addressBook.setExit(true);
                System.out.println("\n[*] The contact list was successfully saved.");
                System.out.println("\n[*] Program successfully ended.");
            }
            // Chosen menu option is invalid.
            default -> System.out.println("\n[!] Invalid menu option. Please try again.");
        }
    }

    /**
     * Takes care of the UI aspects of the application.
     *      - Prints the menu.
     *      - Loads the contact list.
     *      - Saves the contact list.
     *      - Checks if the user wants to exit the app.
     * @throws IOException If I/O exception happens during the I/O transaction.
     */
    private static void menuHandler() throws IOException {
        // User's chosen menu option.
        String menuOption;
        // Address book gets initialized.
        AddressBook addressBook = new AddressBook();

        // Address book get loaded.
        addressBook.load();
        // Menu gets printed into the console.
        System.out.println("\n[*] Option menu:\n    [a] View contact list\n    [b] Create a new contact\n    [c] Delete a contact\n    [d] Exit program");

        do {
            // Gets user's chosen menu option.
            menuOption = getMenuOption();
            // Checks want it can do with the chosen menu option.
            menuOptionHandler(addressBook, menuOption);
        }
        while (!addressBook.getExit());

        // Address book gets saved.
        addressBook.save();

    }

}
