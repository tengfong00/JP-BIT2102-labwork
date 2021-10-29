import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

class Person {
    private int dose;
    private String ic;
    private String date;
    private String vBrand;
    private boolean gender;

    Person () {
        ic = "0";
        dose = 0;
        date = "0";
        vBrand = "0";
        gender = false;
    }

    public int getDose() {
        return dose;
    }

    public String getIc() {
        return ic;
    }

    public String getDate() {
        return date;
    }

    public String getvBrand() {
        return vBrand;
    }

    public boolean getGender() {
        return gender;
    }
    
    public int setDose(int newDose) {
        dose = newDose;
        return 0;
    }

    public String setIc(String newIc) {
        ic = newIc;
        return "0";
    }

    public String setDate(String newDate) {
        date = newDate;
        return "0";
    }

    public String setvBrand(String newvBrand) {
        vBrand = newvBrand;
        return "0";
    }

    public boolean setGender(boolean newGender) {
        gender = newGender;
        return false;
    }

   
    public void listUser()throws Exception{

        try {
                File f=new File("users.txt");
                BufferedReader fread = new BufferedReader(new FileReader(f));
                String s;
                while((s = fread.readLine()) != null) {
                String[] st = s.split(",");
                String Ic = st[0];
                String Gender = st[1];
                String Dose = st[2];
                String dDate = st[3];
                String svBrand = st[4];
                if(Gender.equals("false")){
                    Gender = "Male";
                }else if(Gender.equals("true")){
                    Gender = "Female";
                }else{
                    Gender = "Unknown";
                }
                System.out.println(Ic + "\t" + Gender + "\t\t" + Dose + "\t\t" +dDate + "\t\t" + svBrand);
                }
                fread.close();
                }catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              } 
            }  

public void completedose2(String newDate) throws Exception{
                Scanner input=new Scanner(System.in);
          
                File f=new File("users.txt");
                BufferedReader fread = new BufferedReader(new FileReader(f));
                SimpleDateFormat obj = new SimpleDateFormat("dd/MM/yyyy");
                String s;
                Date cDate = obj.parse(newDate);
                while((s = fread.readLine()) != null) {
                String[] st = s.split(",");
                String Ic = st[0];
                String Gender = st[1];
                String Dose = st[2];
                String dDate = st[3];
                String svBrand = st[4];
                if (Dose.equals("2")) {
                        Date date2 = obj.parse(dDate);
                        long t_differ = cDate.getTime() - date2.getTime();
                        long d_differ = (t_differ/ (1000 * 60 * 60 * 24)) % 365;
        
                        if (d_differ >= 14) {
                            System.out.println(Ic + "\t" + Dose + "\t\t" +dDate + "\t\t" + svBrand);
                        }
                    }
                }
                fread.close();
            }
            

}

class Medical extends Person {
    protected String username = "medical";
    protected String password = "medicalPass";

    private int tempDose, temp;
    private String tempIc, tempDate;
    private boolean  tempGender;
    Scanner scan = new Scanner(System.in);

    public void createFile() {
        try {
            FileWriter inFile = new FileWriter("users.txt", true);
            inFile.write(getIc() + "," + getGender() + "," + 
                            getDose() + "," + getDate() + "," +
                            getvBrand() + "\n");
            inFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void Insert() throws Exception{
        System.out.println("New User Details");
        System.out.println("Please insert following data");
        System.out.print("IC: ");
        tempIc = scan.next();
        int i = 0;
        String s;
        FileReader inFile = new FileReader("users.txt");
        BufferedReader fr = new BufferedReader(inFile);
        while ((s = fr.readLine()) != null) {
            String[] st = s.split(",");
            String ic = st[0];

            if (tempIc.equals(ic)) {
                i++;
                System.out.println("This IC is already registered");  
            }
        }
        if (i == 0) {
        System.out.print("Gender (Male:1/Female:2): ");
        temp = scan.nextInt();

        if (temp == 1) {
            tempGender = false;
        } else if (temp == 2) {
            tempGender = true;
        } else {
            System.out.println("Invalid");
        }

        System.out.print("Date (dd/mm/yyyy): ");
        tempDate = scan.next();

        System.out.print("Dose (0/1/2): ");
        tempDose = scan.nextInt();

        setIc(tempIc);
        setGender(tempGender);
        setDose(tempDose);
        setDate(tempDate);
        setvBrand("0");
        createFile();
    }
    }

    public void updateVaccine(String cDate) throws FileNotFoundException, IOException, ParseException{
        int select;
        int i = 0;
        int tempDose = 0;
        String tempic, s, vaccineBrand;
        String newDate=cDate;
        FileReader inFile = new FileReader("users.txt");
        BufferedReader fr = new BufferedReader(inFile);
        
        System.out.println("Update user dose details");
        System.out.print("Please insert IC: ");
        tempic = scan.next();

        while ((s = fr.readLine()) != null) {
            String[] st = s.split(",");
            String ic = st[0];
            int dose = Integer.parseInt(st[2]);

            if (tempic.equals(ic)) {
                i++;
                tempDose = dose;
            }
        }

        if (i == 0) {
            System.out.println("Invalid IC");
            System.exit(0);
        }

        inFile.close();

        if (tempDose == 0) {
            System.out.print("Had this user have already taken 1st dose? (Yes:1/No:2)");
            select = scan.nextInt();
            if (select == 1) {
                System.out.print("Please enter your vaccine brand: ");
                vaccineBrand = scan.next();

                FileReader brandFile = new FileReader("users.txt");
                FileWriter brandTempFile = new FileWriter("temp.txt");

                BufferedReader brandfr = new BufferedReader(brandFile);
                String line;
                i = 0;

                while ((line = brandfr.readLine()) != null) {
                    String[] st = line.split(",");
                    String updateIc = st[0];
                    String updateGender = st[1];
                    String updateDose = st[2];
                    String updateDate = st[3];
                    String updatevBrand = st[4];

                    if (updateIc.equals(tempic)) {
                        updatevBrand = vaccineBrand;
                        updateDose = "1";
                        updateDate = newDate;
                    }

                    brandTempFile.write(updateIc + "," + updateGender + "," + 
                                            updateDose + "," + updateDate + "," +
                                            updatevBrand + "\n");
                    }

                    brandFile.close();
                    brandTempFile.close();

                    File oldFile = new File("users.txt");
                    oldFile.delete();

                    File newFile = new File("temp.txt");
                    boolean flag = newFile.renameTo(oldFile);

                    if (flag == true) {
                        System.out.println("Updated");
                    } else{
                        System.out.println("failed");
                    }

            } else if (select == 2) {
                System.out.println("System Abort");
            } else {
                System.out.println("System Abort");
            }
        } else if (tempDose == 1) {
            FileReader doseFile = new FileReader("users.txt");
            FileWriter tempDoseFile = new FileWriter("temp.txt");

            BufferedReader dosefr = new BufferedReader(doseFile);
            String line;
            i = 0;

            while ((line = dosefr.readLine()) != null) {
                String[] st = line.split(",");
                String updateIc =st[0];
                String updateGender = st[1];
                String updateDose = st[2];
                String updateDate = st[3];
                String updatevBrand = st[4];

                if (updateIc.equals(tempic)) {
                    updateDose = "2";
                    updateDate = newDate;
                }

                tempDoseFile.write(updateIc + "," + updateGender + "," + 
                                            updateDose + "," + updateDate + "," +
                                            updatevBrand + "\n");
            }
            doseFile.close();
            tempDoseFile.close();

            File oldFile = new File("users.txt");
            oldFile.delete();

            File newFile = new File("temp.txt");
            boolean flag = newFile.renameTo(oldFile);

            if (flag == true) {
                System.out.println("Updated");
            } else{
                System.out.println("failed");
            }

        } else if (tempDose == 2) {
            System.out.println("This user already placed 2nd dose!");
        } else {
            System.out.println("System Abort");
        }
    }

    public void DisplayReady(String newDate) throws FileNotFoundException, IOException, ParseException{
        SimpleDateFormat obj = new SimpleDateFormat("dd/MM/yyyy");
        String s;
        Date currentDate = obj.parse(newDate);

        FileReader inFile = new FileReader("users.txt");
        BufferedReader fr = new BufferedReader(inFile);

        while((s = fr.readLine()) != null) {
            String[] st = s.split(",");
            String Ic = st[0];
            String Dose = st[2];
            String doseDate = st[3];

            if (Dose.equals("1")) {
                Date date2 = obj.parse(doseDate);
                long time_differ = currentDate.getTime() - date2.getTime();
                long day_differ = (time_differ/ (1000 * 60 * 60 * 24)) % 365;
    
                if (day_differ >= 14) {
                    System.out.println(Ic + "\t" + doseDate);
                }
            }
        }
        fr.close();
    }
    public void listUser()throws Exception{
        try {
                File f=new File("users.txt");
                BufferedReader fread = new BufferedReader(new FileReader(f));
                String s;
                while((s = fread.readLine()) != null) {
                String[] st = s.split(",");
                String Ic = st[0];
                String Gender = st[1];
                String Dose = st[2];
                String dDate = st[3];
                String svBrand = st[4];
                if(Gender.equals("false")){
                    Gender = "Male";
                }else if(Gender.equals("true")){
                    Gender = "Female";
                }else{
                    Gender = "Unknown";
                }
                System.out.println(Ic + "\t" + Gender + "\t\t" + Dose + "\t\t" +dDate + "\t\t" + svBrand);
                }
                fread.close();
                }catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              } 
            }
}

class User extends Person {
    public void search(String uic) throws Exception{
        
        File file=new File("users.txt");
        BufferedReader fr = new BufferedReader(new FileReader(file));
        String s;
        
        int i = 0;
        while((s = fr.readLine()) != null){
            
        String[] st = s.split(",");
        String sic = st[0];
        String sGender = st[1];
        String sDose= st[2];
        String sDate = st[3];
        String svBrand = st[4];

        if(sic.equals(uic)){
            i++;
            if(sGender.equals("false")){
                sGender = "Male";
            }else if(sGender.equals("true")){
                sGender = "Female";
            }else{
                sGender = "Unknown";
            }
        System.out.println("User IC = " + sic );
        System.out.println("Gender = "+ sGender);
        System.out.println("User Dose = " + sDose);
        System.out.println("Date = "+ sDate);
        System.out.println("Vaccine Brand = " + svBrand);
        
        }
        
    }
    if(i==0){
        System.out.println("This IC has not been registered");
    }
}
}

public class labWork {
    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void MenuTittle() {
        System.out.println("=======================================================");
        System.out.println("\tCovid-19 Vaccination Management System");
        System.out.println("=======================================================");
    }
    public static void main(String[] args) throws Exception{
        int select;
        String userID, pass, date,uic;
        Medical medical = new Medical();
        User user = new User();
        Person person = new Person();
        char choice;
        Scanner scan = new Scanner(System.in);
        // MENU
        do{
        clearScreen();
        MenuTittle();
        System.out.println("1. Medical");
        System.out.println("2. User");
        System.out.println("3. Person");
        System.out.print("Please enter your choice: ");
        select = scan.nextInt();

        switch (select) {
            case 1:
                clearScreen();
                MenuTittle();
                System.out.println("Medical Login");
                System.out.print("UserID: ");
                userID = scan.next();

                System.out.print("Password: ");
                pass = scan.next();

                if (!userID.equals(medical.username) || !pass.equals(medical.password)) {
                    System.out.println("Invalid");
                    break;
                }

                clearScreen();
                MenuTittle();
                select = 0;
                System.out.println("1. Insert new user details");
                System.out.println("2. Update user dose");
                System.out.println("3. Display user vaccine ready");
                System.out.println("4. Show all user details");
                System.out.print("Please enter your choice: ");
                select = scan.nextInt();

                if (select == 1) {
                    clearScreen();
                    MenuTittle();
                    medical.Insert();
                } else if (select == 2) {
                    clearScreen();
                    MenuTittle();
                    Date librarydate = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    date = formatter.format(librarydate);
                    medical.updateVaccine(date);
                } else if (select == 3) {
                    Date librarydate = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    date = formatter.format(librarydate);

                    clearScreen();
                    MenuTittle();
                    System.out.println("Userlist for eligble for 2nd dose vaccination\n");
                    System.out.println("IC\t\tDoseDate");
                    medical.DisplayReady(date);
                } else if (select==4){
                    clearScreen();
                    MenuTittle();
                    System.out.println("Show all user details\n");
                    System.out.println("IC\t\tGender\t\tDose\t\tGet Dose in\t\tVaccineType\n");
                    medical.listUser();
                }else {
                    System.out.println("Error input.");
                    break;
                }

                break;

            case 2: 
            clearScreen();
            MenuTittle();
            System.out.print("Enter IC to find details: ");
            uic = scan.next();
            user.search(uic);
            break;
            case 3:
                clearScreen();
                MenuTittle();
                select = 0;
                System.out.println("1. Show all user details");
                System.out.println("2. Who are completed dose 2 after 14 days");
                System.out.print("Please enter your choice: ");
                select = scan.nextInt();

                if (select == 1) {
                    clearScreen();
                    MenuTittle();
                    System.out.println("Show all user details\n");
                    System.out.println("IC\t\tGender\t\tDose\t\tGet Dose in\t\tVaccineType\n");
                    person.listUser();
                } else if (select == 2) {
                    Date dr = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    date = formatter.format(dr);
                    
                    clearScreen();
                    MenuTittle();
                    System.out.println("Userlist for completed 2nd dose vaccination after 14 days\n");
                    System.out.println("IC\t\tDose\t\tGet Dose2 in\t\tVaccineType\n");
                    person.completedose2(date);
                } else {
                    System.out.println("Error input.");
                    break;
                }
                break;
                default:
                System.out.println("Error input.");
                break;
        }
            System.out.println("Do You want to continue (Y/N) : ");
            choice = scan.next().charAt(0);
            }
            while ((choice == 'y') || (choice == 'Y'));
        scan.close();
    }
}