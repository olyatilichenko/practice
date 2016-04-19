
import java.io.*;
import java.util.Scanner;


public class Interface {
    public static final int MAX_STR = 140;

    public static void choice() throws FileNotFoundException {
        Chats chats = new Chats();
        chats.readFile("log.txt");
        FileOutputStream log = new FileOutputStream("logfile.log");
        PrintStream logger = new PrintStream(log);
        try {
            Scanner sc = new Scanner(System.in);


            System.out.println("1 - Search by author");
            System.out.println("2 - Search by keyword");
            System.out.println("3 - Regular expression search");
            System.out.println("4 - Search by period of time");
            System.out.println("5 - Delete message");
            System.out.println("6 - Add message");


            System.out.println("What is your choice?");
            int a = sc.nextInt();

            switch (a) {
                case 1: {
                    try {
                        logger.println("\nA request to search by author.\r \n");
                        sc = new Scanner(System.in);
                        System.out.println("Enter author");
                        String str = sc.nextLine();
                        if (str.length() < MAX_STR) {
                            chats.searchAuthor(str);
                            chats.writeFile("1.json");
                        }
                    } catch (NotFindChatException | FileNotFoundException e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }

                }
                break;
                case 2: {
                    try {
                        logger.println("\nA request to search by keyword.\r \n");
                        System.out.println("Enter keyword");
                        sc = new Scanner(System.in);
                        String str = sc.nextLine();
                        if (str.length() < MAX_STR) {
                            chats.searchWord(str);
                            chats.writeFile("2.json");
                        }
                    } catch (NotFindChatException | FileNotFoundException e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }
                    break;
                }

                case 3: {
                    try {
                        logger.println("\nA request to search by regular expression. Files are created.\r \n");
                        sc = new Scanner(System.in);
                        System.out.println("Enter regular expression");
                        String str = sc.nextLine();
                        if (str.length() < MAX_STR) {
                            chats.findRegular(str);
                            chats.writeFile("3.json");
                        }
                    } catch (NotFindChatException | FileNotFoundException e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }
                    break;
                }
                case 4: {
                    sc = new Scanner(System.in);

                    try {
                        logger.println("\nA request to search by period of time.\r \n");
                        System.out.println("Enter period");

                        long count1 = sc.nextLong();
                        long count2 = sc.nextLong();
                        chats.findTimeMessage(count1, count2);
                        chats.writeFile("4.json");
                    } catch (NotFindChatException | FileNotFoundException e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }

                    break;

                }
                case 5: {
                    sc = new Scanner(System.in);
                    try {
                        logger.println("\nA request to deleting message.\r \n");
                        System.out.println("Enter id");


                        chats.deleteMessage(sc.nextLine());
                        chats.writeFile("5.json");
                    } catch (FileNotFoundException e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }
                    break;

                }
                case 6: {
                    sc = new Scanner(System.in);
                    try {
                        logger.println("\nA request to adding new message.\r \n");
                        System.out.println("Enter id , author , message , timestamp");
                        Message ch = new Message(sc.nextLine(), sc.nextLine(), sc.nextLine(), sc.nextLong());
                        chats.addMessage(ch);
                        chats.writeFile("6.json");
                    } catch (FileNotFoundException e) {
                        logger.println("Not Found");
                    } catch (IOException e) {
                        logger.println("Error in file");
                    }
                    break;

                }
                default:
                    logger.println("\nError.\r \n");
                    break;
            }
        } catch (Exception e) {
            logger.println("Error");
        }
    }
}

