/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payments;

import accounts.ConnectionAccount;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import rates.Tarrif;

/**
 *
 * @author Venkat Sai Jarugula
 */
public class TelecommDriver {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<MobileService> connections = new ArrayList<>();
        Scanner sc = new Scanner(new File("usersCallLog.txt"));
        MobileService account = null;
        String num = sc.next();
        while (sc.hasNext()) {
            String customername = num + " " + sc.next();
            sc.nextLine();
            String connectiontype = sc.nextLine();
            String phoneno = sc.nextLine();

            if (connectiontype.equalsIgnoreCase("Postpaid")) {
                String joiningdate = sc.nextLine();
                double dataAvailable = sc.nextDouble();
                ConnectionAccount con = new ConnectionAccount(customername,
                        phoneno, connectiontype, joiningdate);
                account = new PostpaidService(con, dataAvailable);
            } else {
                double balance = sc.nextDouble();
                double data = sc.nextDouble();
                ConnectionAccount con = new ConnectionAccount(customername,
                        phoneno, connectiontype);
                account = new PrepaidService(con, balance, data);
            }

            num = sc.next();

            do {
                String number = num;
                String startdate1 = sc.next();
                String startdate2 = sc.next();
                String starttime = startdate1 + " " + startdate2;
                String enddate1 = sc.next();
                String enddate2 = sc.next();
                String endtime = enddate1 + " " + enddate2;
                String con = sc.next();
                Tarrif calltype;

                if (con.trim().equalsIgnoreCase("L")) {
                    calltype = Tarrif.LOCAL;
                } else if (con.trim().equalsIgnoreCase("R")) {
                    calltype = Tarrif.ROAMING;
                } else {
                    calltype = Tarrif.INTERNATIONAL;
                }

                Call call = new Call(number, starttime, endtime, calltype);
                if (connectiontype.equalsIgnoreCase("Postpaid")) {
                    boolean A = account.makeCall(call);
                } else {
                    boolean B = account.makeCall(call);
                }

                if (sc.hasNext()) {
                    sc.nextLine();
                    num = sc.next();
                } else {
                    break;
                }

            } while (num.contains("+"));
            connections.add(account);
        }
        System.out.println("************************Postpaid customers "
                + "invoice***************************");
        for (MobileService S : connections) {
            if (S.getAccount().getConnectionType().equalsIgnoreCase("postpaid")) {
                System.out.println(S.toString());
            }
        }
        System.out.println("*************************Prepaid customers "
                + "invoice***************************");
        for (MobileService S : connections) {
            if (S.getAccount().getConnectionType().equalsIgnoreCase("Prepaid")) {
                System.out.println(S.toString());
            }

        }
        System.out.println("**********************Invoking useData() of "
                + "customer*************************");
        for (MobileService S : connections) {
            switch (S.getAccount().getCustomerName()) {
                case "Aegon Targaryen":
                    S.useData(156774.40);
                    System.out.println(S.toString());
                    break;

                case "Tyrion Lannister":
                    S.useData(13516.80);
                    System.out.println(S.toString());
                    break;

                case "Eddard Stark":
                    S.useData(104427.52);
                    System.out.println(S.toString());
                    break;
            }
        }
        System.out.println("*************************Best postpaid "
                + "customer******************************");
        System.out.println("----------------------------------------------"
                + "-------------------------------");

        PostpaidService post = (PostpaidService) (connections.get(0));
        double maxPost = post.finalBillAfterDiscount();
        String cust = post.getAccount().getCustomerName();
        for (MobileService S : connections) {
            if (S.getAccount().getConnectionType().equalsIgnoreCase("Postpaid")) {
                post = (PostpaidService) (S);
                if (maxPost < post.finalBillAfterDiscount()) {
                    maxPost = post.finalBillAfterDiscount();
                    cust = post.getAccount().getCustomerName();
                }

            }
        }
        System.out.println("Best Customer Details:\n	Customer Name:	"
                + "" + cust + "\n	Bill amount	:"
                + "$" + String.format("%.2f", Math.round(maxPost * 100) / 100.0));

    }

}
