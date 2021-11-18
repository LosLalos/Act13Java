import java.io.*;
import java.util.HashMap;

public class AddressBook {

    // This hashmap temporarily stores the list of contacts.
    private HashMap<String, String> contactList;
    // Checks if the user wants to close and save the list of contacts.
    private boolean exit;
    private String databasePath = "C:\\Users\\USUARIO\\IdeaProjects\\Act13Java\\src\\database.txt";

    /**
     * Creates a new address book object.
     */
    public AddressBook() {
        // Initializes the hash map.
        contactList = new HashMap<>();
        // Initializes the exit value.
        exit = false;
        boolean empty = false;
    }

    /**
     * Loads the current list of contacts.
     * @throws IOException If I/O exception happens during the I/O transaction.
     */
    public void load() throws IOException {

        // Checks if there is no more data in database.txt.
        boolean empty = false;
        // Values of the read contact.
        String phoneNumber, name;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(databasePath));

        // While there is data on the database.txt file to be read.
        do {
            phoneNumber = bufferedReader.readLine();
            name = bufferedReader.readLine();

            if (name == null && phoneNumber == null) {
                // There is no more contacts to be inserted.
                empty = true;
            }
            else {
                // Inserts contact into the hash map list.
                create(phoneNumber, name);
            }

        }
        while (!empty);

    }

    /**
     * Saves the current list of contacts.
     * @throws IOException If I/O exception happens during the I/O transaction.
     */
    public void save() throws IOException {

        // Creates new save file.
        File file = new File(databasePath);
        // Deletes the old save file.
        file.delete();
        //Creates a new one with the current values.
        file.createNewFile();

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(databasePath));

        // Gets the keys of the contact list.
        String[] contacts = contactList.keySet().toArray(new String[0]);

        // The current contact list gets written into the save file.
        for (String contact : contacts) {
            // Phone number gets written.
            bufferedWriter.write(contact + "\n");
            // Name gets written.
            bufferedWriter.write(contactList.get(contact) + "\n");
        }

        // File gets closed.
        bufferedWriter.close();

    }

    /**
     * Prints the list of contacts into the console.
     */
    public void list() {

        // List is empty.
        if(contactList.isEmpty()) {
            System.out.println("\n[*] The list is empty.");
        }
        // List isn't empty.
        else {
            String[] contacts = contactList.keySet().toArray(new String[0]);
            System.out.println("\n[Contact List]");
            for (String contact : contacts) {
                System.out.println("    Phone Number: " + contact + " Contact Name: " + contactList.get(contact));
            }
        }

    }

    /**
     * Creates a contact and adds it to the contact list.
     * @param phoneNumber The phone number of the contact to create.
     * @param name The name of the contact to create.
     */
    public void create(String phoneNumber, String name) {
        contactList.put(phoneNumber, name);
    }

    /**
     * Deletes a contact from the contact list.
     * @param phoneNumber The phone number of the contact to delete.
     * @param name The name of the contact to delete.
     */
    public void delete(String phoneNumber, String name) {
        contactList.remove(phoneNumber, name);
    }

    /**
     * Sets the exit value of the list.
     * @param exit The new boolean value of exit.
     */
    public void setExit(boolean exit) {
        this.exit = exit;
    }

    /**
     * Gets the exit value of the list.
     * @return The boolean value of exit.
     */
    public boolean getExit() {
        return exit;
    }

    /**
     * Checks if the phone number exists in the list of contacts.
     * @param phoneNumber Phone number of the contact.
     * @return True if phone number exists within the hash map, and false if it doesn't.
     */
    public boolean exists(String phoneNumber){
        return contactList.containsKey(phoneNumber);
    }

}
