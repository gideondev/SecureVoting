package Client;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import libs.Vote;

public class BulletinBoard {

    public static ArrayList<Vote> storage = new ArrayList<Vote>();

    public BulletinBoard() throws IOException {

    }

    public static boolean addToBulletinBoard(Vote vote)
    {
        return storage.add(vote);
    }

    public static void displayBulletinBoard(ArrayList<Vote> votes)
    {
        for (int i=0;i<votes.size();i++)
        {
            System.out.println(votes.get(i).getVote());
        }
    }

    public static void storeVotes() throws IOException {
        FileOutputStream fos = new FileOutputStream("votes.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(storage);
    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        System.out.println("Welcome to Bulletin Board");
        System.out.println("-------------------------");

        //Print the menu here
        System.out.println("Please select what you want to do : ");
        System.out.println("1. Display Board");
        System.out.println("2. Verify Vote");

        Scanner sc = new Scanner(System.in);

        int choice = sc.nextInt();
        switch(choice)
        {
            case 1:
            {
                // read object from file
                FileInputStream fis = new FileInputStream("votes.ser");
                ObjectInputStream ois = new ObjectInputStream(fis);
                ArrayList<Vote> votes = (ArrayList<Vote>) ois.readObject();
                displayBulletinBoard(votes);
                ois.close();
                break;
            }
            case 2:
            {
                break;
            }
            default:
            {
                System.out.println("Please select a proper option.");
            }
        }
    }
}