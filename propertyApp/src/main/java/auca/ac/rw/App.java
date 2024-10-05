package auca.ac.rw;

import auca.ac.rw.DAO.PropertyDao;
import auca.ac.rw.model.Property;

import java.util.List;

import java.util.Scanner;



public class App
{
    private static PropertyDao propertyDAO = new PropertyDao();

    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Create Property");
            System.out.println("2. List All Properties");
            System.out.println("3. Find Property by ID");
            System.out.println("4. Delete Property");
            System.out.println("5. Update Property");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Property ID: ");
                    String propertyID = scanner.nextLine();
                    System.out.print("Enter Owner ID: ");
                    String ownerID = scanner.nextLine();
                    System.out.print("Enter Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter Size: ");
                    double size = scanner.nextDouble();
                    System.out.print("Enter Valuation: ");
                    double valuation = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter Valuation Report: ");
                    String valuationReport = scanner.nextLine();
                    Property property = new Property(propertyID, ownerID, address, size, valuation, valuationReport);
                    propertyDAO.saveProperty(property);
                    System.out.println("Property created successfully!");
                    break;

                case 2:
                    List<Property> properties = propertyDAO.getAllProperties();
                    displayProperties(properties);
                    break;

                case 3:
                    System.out.print("Enter Property ID to find: ");
                    String findID = scanner.nextLine();
                    Property foundProperty = propertyDAO.getProperty(findID);
                    if (foundProperty != null) {
                        displayProperty(foundProperty);
                    } else {
                        System.out.println("Property not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter Property ID to delete: ");
                    String deleteID = scanner.nextLine();
                    propertyDAO.deleteProperty(deleteID);
                    System.out.println("Property deleted successfully!");
                    break;

                case 5:
                    System.out.print("Enter Property ID to update: ");
                    String updateID = scanner.nextLine();


                    Property existingProperty = propertyDAO.getProperty(updateID);
                    if (existingProperty != null) {
                        // Display current details
                        System.out.println("Current Property Details:");
                        System.out.println("Owner ID: " + existingProperty.getOwnerID());
                        System.out.println("Address: " + existingProperty.getAddress());
                        System.out.println("Size: " + existingProperty.getSize());
                        System.out.println("Valuation: " + existingProperty.getValuation());

                        // Prompt for new values
                        System.out.print("Enter new Owner ID (or press enter to keep current): ");
                        String newOwnerID = scanner.nextLine();
                        if (!newOwnerID.isEmpty()) {
                            existingProperty.setOwnerID(newOwnerID);
                        }

                        System.out.print("Enter new Address (or press enter to keep current): ");
                        String newAddress = scanner.nextLine();
                        if (!newAddress.isEmpty()) {
                            existingProperty.setAddress(newAddress);
                        }

                        System.out.print("Enter new Size (or press enter to keep current): ");
                        String newSize = scanner.nextLine();
                        if (!newSize.isEmpty()) {
                            existingProperty.setSize(Double.parseDouble(newSize));
                        }

                        System.out.print("Enter new Valuation (or press enter to keep current): ");
                        String newValuation = scanner.nextLine();
                        if (!newValuation.isEmpty()) {
                            existingProperty.setValuation(Double.parseDouble(newValuation));
                        }

                        // Call the update method
                        propertyDAO.updateProperty(existingProperty);
                        System.out.println("Property updated successfully!");
                    } else {
                        System.out.println("Property not found!");
                    }
                    break;


                case 6:
                    HibernateUtil.shutdown();
                    System.exit(0);
            }


        }
    }

    public static void displayProperties(List<Property> properties) {
        if (properties.isEmpty()) {
            System.out.println("No properties found.");
        } else {
            String format = "| %-10s | %-10s | %-20s | %-10s | %-12s | %-30s |%n";
            System.out.println("-----------------------------------------------------------------------------------------------");
            System.out.printf(format, "PropertyID", "OwnerID", "Address", "Size", "Valuation", "Valuation Report");
            System.out.println("-----------------------------------------------------------------------------------------------");

            for (Property property : properties) {
                System.out.printf(format,
                        property.getPropertyID(),
                        property.getOwnerID(),
                        property.getAddress(),
                        property.getSize(),
                        property.getValuation(),
                        property.getValuationReport());
            }

            System.out.println("-----------------------------------------------------------------------------------------------");
        }
}

    public static void displayProperty(Property property) {
        System.out.printf("%-15s %-15s %-30s %-10s %-10s%n", "Property ID", "Owner ID", "Address", "Size", "Valuation");
        System.out.println("---------------------------------------------------------------------------");
        System.out.printf("%-15s %-15s %-30s %-10s %-10s%n",
                property.getPropertyID(),
                property.getOwnerID(),
                property.getAddress(),
                property.getSize(),
                property.getValuation());
        System.out.println("---------------------------------------------------------------------------");
    }

}
