package se.iths.labborationer.labb2MikaelEdwartz.UserInterfaces;

public abstract class Greetings {
    public static void costumerMenuGreeting() {
        System.out.println("1. Vill gå igenom alla kategorier en i taget?");
        System.out.println("2. Välj en specifik kategori");
        System.out.println("3. Printa ut alla produkter");
        System.out.println("4. print your register");
        System.out.println("5. Gå till kassan och betala");
    }
    public static void adminMenuGreeting() {
        System.out.println("-----------------------------------------");
        System.out.println("Hej och välkommen till Kortedala mataffär");
        System.out.println("1: Lägg till vara/skapa kategori");
        System.out.println("2: ta bort varor");
        System.out.println("3: Printa ut lagersaldo");
        System.out.println("4: Sök via kategori");
        System.out.println("5: Sök inom ett prisintervall");
        System.out.println("6: Gå tillbaka till menyn");
        System.out.println("7: Spara ändringar");
        System.out.println("e: Avsluta");
    }
    public static void firstMenuGreeting() {
        System.out.println("Välkommen! vänligen logga in genom att välja ett alternativ nedan.");
        System.out.println("Tryck 1 för att logga in som admin");
        System.out.println("Tryck 2 för att börja shoppa");
        System.out.println("Tryck e för att avsluta.");
    }
}
