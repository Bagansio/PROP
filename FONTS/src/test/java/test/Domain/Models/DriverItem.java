package test.Domain.Models;

import com.recommender.recommenderapp.Domain.Models.Item;
import com.recommender.recommenderapp.Domain.Utils.ItemTypes;
import stubs.StubsItem;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;


/**
 * @author  Artur Farriols
 */
public class DriverItem {

    public static final String ansiGreen = "\u001B[32m";
    public static final String ansiRed = "\u001B[31m";
    public static final String ansiNormal = "\u001B[0m";

    static boolean first = true;

    public static void main(String[] args) {
        Scanner reader =  new Scanner(System.in);
        int usage;
        Usage();
        while ((usage = reader.nextInt()) != 20) {
            switch(usage){
                case 1:
                    executeAddIntAttribute();
                    break;

                case 2:
                    executeAddDoubleAttribute();
                    break;

                case 3:
                    executeAddSettAttribute();
                    break;

                case 4:
                    executeAddStringAttribute();
                    break;

                case 5:
                    executeGetStringAttributes();
                    break;

                case 6:
                    executeSetStringAttributes();
                    break;

                case 7:
                    executeGetIntAttributes();
                    break;

                case 8:
                    executeSetIntAttributes();
                    break;

                case 9:
                    executeGetDoubleAttributes();
                    break;

                case 10:
                    executeSetDoubleAttributes();
                    break;

                case 11:
                    executeGetSetAttributes();
                    break;

                case 12:
                    executeSetSetAttributes();
                    break;

                case 13:
                    executeGetId();
                    break;

                case 14:
                    executeSetId();
                    break;

                case 15:
                    executeGetTitle();
                    break;

                case 16:
                    executeSetTitle();
                    break;

                case 17:
                    executeGetType();
                    break;

                case 18:
                    executeSetType();
                    break;
                case 19:
                    executeConstructor();
                    break;
                default:
                    System.out.println(ansiRed + "Invalid value" + ansiNormal);
                    System.out.println();
                    Usage();
                    break;
            }
        }
    }

    public static void Usage() {
        if (first) {
            System.out.println();
            first = false;
        }
        System.out.println("You are executing Item's class driver. Choose one of the following options in order to" +
                " test this class");
        System.out.println("\t1. addIntAttribute: adds two integers and checks if the insertion has been done properly");
        System.out.println(" \t2. addDoubleAttribute: adds two doubles and checks if the insertion has been done properly");
        System.out.println(" \t3. addSetAttribute: adds two sets of strings and checks if the insertion has been done properly");
        System.out.println(" \t4. addStringAttribute: adds two strings and checks if the insertion has been done properly");
        System.out.println(" \t5. getStringAttributes: creates an item and checks if the function returns the proper value");
        System.out.println(" \t6. setStringAttributes: adds a set of Strings and checks if the insertion has been done properly");
        System.out.println(" \t7. getIntAttributes: creates an item and checks if the function returns the proper value");
        System.out.println(" \t8. setIntAttributes: adds a set of integers and checks if the insertion has been done properly");
        System.out.println(" \t9. getDoubleAttributes: creates an item and checks if the function returns the proper value");
        System.out.println(" \t10. setDoubleAttributes: adds a set of doubles and checks if the insertion has been done properly");
        System.out.println(" \t11. getSetAttributes: creates an item and checks if the function returns the proper value");
        System.out.println(" \t12. setSetAttributes: adds a group of set attribute and checks if the insertion has been done properly");
        System.out.println(" \t13. getId: creates an item and checks if the function returns the proper value");
        System.out.println(" \t14. setId: adds an id and checks if the insertion has been done properly");
        System.out.println(" \t15. getTitle: creates an item and checks if the function returns the proper value");
        System.out.println(" \t16. setTitle: adds a title and checks if the insertion has been done properly");
        System.out.println(" \t17. getType: creates an item and checks if the function returns the proper value");
        System.out.println(" \t18. setType: adds a type and checks if the insertion has been done properly");
        System.out.println(" \t19. Constructor Operation: creates an Item and checks if its values have been added properly");
        System.out.println(ansiRed + "\t20. Exit" + ansiNormal);
    }

    private static void executeAddIntAttribute() {
        System.out.println("We will now  create an Item and add two integer values from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Integer> m = stubsItem.getStubIntAttributes();

        Item i = new Item();
        Set<String> keys = m.keySet();

        System.out.println("Expected map keys values after the execution:" + keys);
        System.out.println("Expected map integer values after the execution:" + m.entrySet());


        for (String s: m.keySet()) {
            System.out.println("Inserting value " + s);
            i.addIntAttribute(s, m.get(s));
        }

        Map<String, Integer> m2 = i.getIntAttributes();
        System.out.println("Map key values after the execution" + m2.keySet());
        System.out.println("Map integer values after the execution" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeAddDoubleAttribute() {
        System.out.println("We will now  create an Item and add two double values from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Double> m = stubsItem.getStubDoubleAttributes();

        Item i = new Item();
        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map double values after the execution:" + m.entrySet());


        for (String s: m.keySet()) {
            System.out.println("Inserting value " + s);
            i.addDoubleAttribute(s, m.get(s));
        }

        Map<String, Double> m2 = i.getDoubleAttributes();
        System.out.println("Map key values after the execution" + m2.keySet());
        System.out.println("Map double values after the execution" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeAddSettAttribute() {
        System.out.println("We will now  create an Item and add two sets from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Set<String>> m = stubsItem.getStubSetAttributes();

        Item i = new Item();
        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map set values after the execution:" + m.entrySet());


        for (String s: m.keySet()) {
            System.out.println("Inserting value " + s);
            i.addSetAttribute(s, m.get(s));
        }

        Map<String, Set<String>> m2 = i.getSetAttributes();
        System.out.println("Map key values after the execution" + m2.keySet());
        System.out.println("Map set values after the execution" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeAddStringAttribute() {
        System.out.println("We will now  create an Item and add two String values from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, String> m = stubsItem.getStubStringAttributes();

        Item i = new Item();
        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map string values after the execution:" + m.entrySet());


        for (String s: m.keySet()) {
            System.out.println("Inserting value " + s);
            i.addStringAttribute(s, m.get(s));
        }

        Map<String, String> m2 = i.getStringAttributes();
        System.out.println("Map key values after the execution" + m2.keySet());
        System.out.println("Map string values after the execution" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeGetStringAttributes() {
        System.out.println("We will now  create an Item and add two String values from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, String> m = stubsItem.getStubStringAttributes();


        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map string values after the execution:" + m.entrySet());


        System.out.println("Creating Item");
        Item i = new Item();
        i.setStringAttributes(m);

        Map<String, String> m2 = i.getStringAttributes();
        System.out.println("Map key values obtained using the tested function" + m2.keySet());
        System.out.println("Map string values obtained using the tested function" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeSetStringAttributes() {
        System.out.println("We will now  create an Item and add two String values from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, String> m = stubsItem.getStubStringAttributes();

        Item i = new Item();
        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map string values after the execution:" + m.entrySet());


            System.out.println("Inserting values");
            i.setStringAttributes(m);

        Map<String, String> m2 = i.getStringAttributes();
        System.out.println("Map key values after the execution" + m2.keySet());
        System.out.println("Map string values after the execution" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeGetIntAttributes() {
        System.out.println("We will now  create an Item and add an integer attribute from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Integer> m = stubsItem.getStubIntAttributes();

        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map integer values after the execution:" + m.entrySet());


        System.out.println("Creating Item");
        Item i = new Item();
        i.setIntAttributes(m);

        Map<String, Integer> m2 = i.getIntAttributes();
        System.out.println("Map key values obtained using the tested function" + m2.keySet());
        System.out.println("Map integer values obtained using the tested function" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeSetIntAttributes() {
        System.out.println("We will now  create an Item and add an integer attribute from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Integer> m = stubsItem.getStubIntAttributes();

        Item i = new Item();
        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map integer values after the execution:" + m.entrySet());


            System.out.println("Inserting values");
            i.setIntAttributes(m);

        Map<String, Integer> m2 = i.getIntAttributes();
        System.out.println("Map key values after the execution" + m2.keySet());
        System.out.println("Map integer values after the execution" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeGetDoubleAttributes() {
        System.out.println("We will now  create an Item and add two double values from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Double> m = stubsItem.getStubDoubleAttributes();

        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map double values after the execution:" + m.entrySet());


        System.out.println("Creating Item");
        Item i = new Item();
        i.setDoubleAttributes(m);

        Map<String, Double> m2 = i.getDoubleAttributes();
        System.out.println("Map key values obtained using the tested function" + m2.keySet());
        System.out.println("Map double values obtained using the tested function" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeSetDoubleAttributes() {
        System.out.println("We will now  create an Item and add two double values from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Double> m = stubsItem.getStubDoubleAttributes();

        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map double values after the execution:" + m.entrySet());


            System.out.println("Creating Item");
            Item i = new Item();
            i.setDoubleAttributes(m);

        Map<String, Double> m2 = i.getDoubleAttributes();
        System.out.println("Map key values after the execution" + m2.keySet());
        System.out.println("Map double values after the execution" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeGetSetAttributes() {
        System.out.println("We will now  create an Item and add two sets from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Set<String>> m = stubsItem.getStubSetAttributes();

        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map set values after the execution:" + m.entrySet());

        System.out.println("Creating Item");
        Item i = new Item();
        i.setSetAttributes(m);

        Map<String, Set<String>> m2 = i.getSetAttributes();
        System.out.println("Map key values obtained using the tested function" + m2.keySet());
        System.out.println("Map set values obtained using the tested function" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeSetSetAttributes() {
        System.out.println("We will now  create an Item and add two sets from the stub");
        StubsItem stubsItem = new StubsItem();
        Map<String, Set<String>> m = stubsItem.getStubSetAttributes();

        Item i = new Item();
        Set<String> keys = m.keySet();

        System.out.println("Expected map key values after the execution:" + keys);
        System.out.println("Expected map set values after the execution:" + m.entrySet());

            System.out.println("Inserting values");
            i.setSetAttributes(m);

        Map<String, Set<String>> m2 = i.getSetAttributes();
        System.out.println("Map key values after the execution" + m2.keySet());
        System.out.println("Map set values after the execution" + m2.entrySet());

        if (m2.equals(m)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeGetId() {
        System.out.println("We will now  create an Item and add an Id from the stub");
        StubsItem stubsItem = new StubsItem();
        String id = stubsItem.getStubItemId();
        System.out.println("Expected Item id value: " + id);

        System.out.println("Creating Item");
        Item i = new Item();
        i.setId(id);

        String aux = i.getId();
        System.out.println("id value obtained using the tested function: " + aux);
        if (id.equals(aux)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);

    }

    private static void executeSetId() {
        System.out.println("We will now  create an Item and add an Id from the stub");
        StubsItem stubsItem = new StubsItem();
        String id = stubsItem.getStubItemId();

        Item i = new Item();

        System.out.println("Expected id value after the execution: " + id);

        System.out.println("Inserting value");
        i.setId(id);

        System.out.println("id value after the execution: " + i.getId());

        if (i.getId().equals(id)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeGetTitle() {
        System.out.println("We will now  create an Item and add a title from the stub");
        StubsItem stubsItem = new StubsItem();
        String title = stubsItem.getStubTitle();
        System.out.println("Expected Item title value: " + title);

        System.out.println("Creating Item");
        Item i = new Item();
        i.setTitle(title);

        String aux = i.getTitle();
        System.out.println("Title value obtained using the tested function: " + aux);
        if (title.equals(aux)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeSetTitle() {
        System.out.println("We will now  create an Item and add a title from the stub");
        StubsItem stubsItem = new StubsItem();
        String title = stubsItem.getStubTitle();

        Item i = new Item();

        System.out.println("Expected title value after the execution: " + title);

        System.out.println("Inserting value");
        i.setTitle(title);

        System.out.println("Title value after the execution: " + i.getTitle());

        if (i.getTitle().equals(title)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeGetType() {
        System.out.println("We will now  create an Item and add a type from the stub");
        StubsItem stubsItem = new StubsItem();
        ItemTypes type = stubsItem.getStubItemTypes();
        System.out.println("Expected Item type value: " + type);

        System.out.println("Creating Item");
        Item i = new Item();
        i.setType(type);

        ItemTypes aux = i.getType();
        System.out.println("Type value obtained using the tested function: " + aux);
        if (type.equals(aux)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeSetType() {
        System.out.println("We will now  create an Item and add a title from the stub");
        StubsItem stubsItem = new StubsItem();
        ItemTypes type = stubsItem.getStubItemTypes();

        Item i = new Item();

        System.out.println("Expected type value after the execution: " + type);

        System.out.println("Inserting value");
        i.setType(type);

        System.out.println("Type value obtained using the tested function: " + i.getType());

        if (i.getType().equals(type)) System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

    private static void executeConstructor() {
        System.out.println("We will now  create an Item with an id, title and type from the stub");
        StubsItem stubsItem = new StubsItem();
        String id = stubsItem.getStubItemId();
        String title = stubsItem.getStubTitle();
        ItemTypes type = stubsItem.getStubItemTypes();

        System.out.println("Expected id value after the execution: " + id);
        System.out.println("Expected title value after the execution: " + title);
        System.out.println("Expected type value after the execution: " + type);

        System.out.println("Creating item");
        Item i= new Item(id, title, type);

        System.out.println("Id value obtained after creating the item: " + i.getId());
        System.out.println("Title value obtained after creating the item: " + i.getTitle());
        System.out.println("Type value obtained after creating the item: " + i.getType());

        if (i.getTitle().equals(title) && i.getId().equals(id) && i.getType().equals(type)) {
            System.out.println(ansiGreen + "Function worked properly" + ansiNormal);
        }
        else System.out.println(ansiRed + "Function did not work properly" + ansiNormal);
    }

}

