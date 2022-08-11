import java.util.*;
import java.io.*;


class Person{


    private String name;
        private String emailID;
        private String password;
        private String country;
        private String city;
        public Person(String name,String emailID,String password,String country,String city){
            this.name=name;
            this.emailID=emailID;
            this.password=password;
            this.country=country;
            this.city=city;
        }
        public String getName() {
            return this.name;
        }
        public String getEmailID() {
            return this.emailID;
        }
        public String getCountry() {
            return this.country;
        }
        public String getCity() {
            return this.city;
        }
        class Service{
            protected String[] studentName;
            // protected ArrayList<String> upcomingEvents=new ArrayList<>();
            // protected String importantLinks;
            // protected String notices;
            BufferedReader brup, brimp,brnot;
            public void displayUpcomingEvents() throws IOException {
                brup=new BufferedReader(new FileReader("upcomingEvents.txt"));
            String s;
            while((s=brup.readLine())!=null) System.out.println(s+"\n");
        }
            public void displayImportantLinks() throws IOException{
                    brimp = new BufferedReader(new FileReader("importantLinks.txt"));
                String s1;
                while((s1=brimp.readLine())!=null) System.out.println(s1+"\n");
        }
            public void viewNotice() throws IOException {
                    brnot = new BufferedReader(new FileReader("Notices.txt"));
                String s1;
                while((s1=brnot.readLine())!=null) System.out.println(s1+"\n");
        }
            public void displayStudentsList() throws IOException {
                    BufferedReader brsl=new BufferedReader(new FileReader("studentDetails.txt"));
                    String s2;
                    String[] studentDetails2 = new String[7];
                    while((s2=brsl.readLine())!=null) {
                            studentDetails2 = s2.split(";");
                        System.out.println(studentDetails2[2]+" "+studentDetails2[5]);;
                }
                    brsl.close();
        }
    }
}
    class Student extends Person{
            private String studentID;
            private String courseEnrolled;
            private int marks;
            public Student(String emailID,String password,String name,String country,String city,String studentID,String courseEnrolled, String marks){
                super(name,emailID,password,country,city);
                this.studentID=studentID;
                this.courseEnrolled=courseEnrolled;
                this.marks=Integer.parseInt(marks);
        }
        public String getStudentID() {
                return this.studentID;
        }
        class StudentService extends Person.Service{
                public void displayCourseEnrolled() {
                    System.out.println(courseEnrolled);
            }
            public void displayMarks() {
                    System.out.println(marks);
            }
    }
}
    class Admin extends Person{
            private String facultyID;
            private String courseTeaching;
            public Admin(String emailID,String password,String name,String country,String city,String facultyID,String courseTeaching){
                super(name,emailID,password,country,city);
                this.facultyID=facultyID;
                this.courseTeaching=courseTeaching;
        }
        public String getFacultyID() {
                return this.facultyID;
        }
        class AdminService extends Person.Service{
                public void displayCourseTeaching() {
                    System.out.println(courseTeaching);
            }
            public void displayMarksList() throws IOException {
                         BufferedReader brml=new BufferedReader(new FileReader("studentDetails.txt"));
                    String s1;
                    String[] studentDetails = new String[7];
                    while((s1=brml.readLine())!=null) {
                            studentDetails = s1.split(";");
                        System.out.println(studentDetails[2]+" "+studentDetails[5]+" "+studentDetails[6]);;
                }
                    brml.close();
        }
            public void setUpcomingEvents(ArrayList<String> s) {
                    try {
                    FileWriter fwup=new FileWriter("upcomingEvents.txt",true);
                for(String up:s) fwup.write(up+"\n");
                fwup.close();
            }catch(Exception e) {
                        return;
                }
        }
            public void setImportantLinks(ArrayList<String> s) {
                    try {
                        FileWriter fwimp=new FileWriter("importantLinks.txt",true);
                    for(String imp:s) fwimp.write(imp+"\n");
                    fwimp.close();
                }catch(Exception e) {
                            return;
                    }
        }
            public void setNotice(ArrayList<String> s) {
                    try {
                        FileWriter fwnot=new FileWriter("notices.txt",true);
                    for(String not:s) fwnot.write(not+"\n");
                    fwnot.close();
                }catch(Exception e) {
                            return;
                    }
        }
    }
}
    class Test{
            Scanner sc=new Scanner(System.in);
            BufferedReader br=new BufferedReader(new FileReader("Questions.txt"));
        public Test(String[] studentDetails) throws NumberFormatException, IOException {
                String s1;
                int marks=Integer.parseInt(studentDetails[6]);
                String[] questions = new String[6];
                while((s1=br.readLine())!=null) {
                    questions = s1.split(";");
                System.out.println(questions[0]);
                System.out.println(questions[1]);
                System.out.println(questions[2]);
                System.out.println(questions[3]);
                System.out.println(questions[4]);
                System.out.println("\nPlease enter the correction option('a','b','c','d')");
                String option=sc.nextLine();
                if(answerChecking(questions[5],option)) marks+=1;
        }
            updateMarks(studentDetails,marks);
        }
        public boolean answerChecking(String answer,String option) {
                if(answer.equals(option)) return true;
                else return false;
        }
        public void updateMarks(String[] studentDetails,int marks) throws NumberFormatException, IOException {
                BufferedReader buf = null;
                try {
                    buf = new BufferedReader(new FileReader("studentDetails.txt"));
        } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            String s1;
            String newLine="";
            String oldContent="";
            String oldLine="";
            while((s1=buf.readLine())!=null) {
                    String[] details=s1.split(";");
                oldContent = oldContent + s1 + System.lineSeparator();
                if(details[0].equals(studentDetails[0])) {
                        int previousMarks=Integer.parseInt(details[6]);
                        previousMarks+=marks;
                        System.out.println("\n Thank you for taking the test. Total marks stored in this test:"+previousMarks);
                    oldLine=s1;
                    newLine=details[0]+";"+details[1]+";"+details[2]+";"+details[3]+";"+details[4]+";"+details[5]+";"+previousMarks;
            }
        }
            String newContent = oldContent.replaceAll(oldLine, newLine);
            FileWriter fw = null;
            try {
                     fw=new FileWriter("studentDetails.txt",false);
        } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
            fw.write(newContent);
            fw.close();
            buf.close();
    }
}
    public class CourseManagement {
            public static void studentFunction(String[] studentDetails) throws IOException{
                Student s = new Student(studentDetails[0], studentDetails[1], studentDetails[2], studentDetails[3], studentDetails[4], studentDetails[5], "CS F213", studentDetails[6]);
            Student.StudentService ss = s.new StudentService();
            Scanner sc=new Scanner(System.in);
            System.out.println("\nEnter 1 to see your Personal Details");
            System.out.println("Enter 2 to view Enrolled Courses");
            System.out.println("Enter 3 to view Marks");
            System.out.println("Enter 4 to take Active Tests");
            System.out.println("Enter 5 to view upcoming events");
            System.out.println("Enter 6 to view important links");
            System.out.println("Enter 7 to view notices");
            System.out.println("Enter 8 to view the student list");
            System.out.println("Enter 9 to log out");
            int a=sc.nextInt();
            while(a!=1 && a!=2 && a!=3 && a!=4 && a!=5 && a!=6 && a!=7 && a!=8 && a!=9){
                    System.out.println("Invalid input, Please select a valid option");
                a=sc.nextInt();
        }
            switch(a){
                    case 1:
                        System.out.println("\nName:"+s.getName()+"\nEmailId:"+s.getEmailID() +"\nStudentId:"+s.getStudentID()+"\nCity:"+s.getCity()+"\nCountry:"+s.getCountry());
                    studentFunction(studentDetails);
                    break;
                case 2:
                    ss.displayCourseEnrolled();
                    studentFunction(studentDetails);
                    break;
                case 3:
                    ss.displayMarks();
                    studentFunction(studentDetails);
                    break;
                case 4:
                    //display active tests
                    Test t=new Test(studentDetails);
                    BufferedReader br1=new BufferedReader(new FileReader("studentDetails.txt"));
                    String s1;
                    String[] details=new String[7];
                    while((s1=br1.readLine())!=null) {
                            details=s1.split(";");
                        if(studentDetails[0].equals(details[0])) studentDetails[6]=details[6];
                }
                    studentFunction(studentDetails);
                    break;
                case 5:
                    ss.displayUpcomingEvents();
                    studentFunction(studentDetails);
                    break;
                case 6:
                    ss.displayImportantLinks();
                    studentFunction(studentDetails);
                    break;
                case 7:
                    ss.viewNotice();
                    studentFunction(studentDetails);
                    break;
                case 8:
                    //view student list
                    ss.displayStudentsList();
                    studentFunction(studentDetails);
                    break;
                case 9:
                    System.out.println("\n\t\tThank you for using the portal");
                    System.exit(0);
                    break;
        }
    }
        public static void adminFunction() throws IOException{
                Admin ad = new Admin("amit.dua@gmail.com", "amit.dua","Amit Dua","India","Pilani","2012A7PS0001P", "CS F213");
            Admin.AdminService as = ad.new AdminService();
            Scanner sc=new Scanner(System.in);
            System.out.println("\nEnter 1 to see your Personal Details");
            System.out.println("Enter 2 to view Course Teaching");
            System.out.println("Enter 3 to view Student MarksList");
            System.out.println("Enter 4 to make Tests");
            System.out.println("Enter 5 to set upcoming events");
            System.out.println("Enter 6 to set important links");
            System.out.println("Enter 7 to give notices");
            System.out.println("Enter 8 to view the student list");
            System.out.println("Enter 9 to log out");
            int a=sc.nextInt();
            while(a!=1 && a!=2 && a!=3 && a!=4 && a!=5 && a!=6 && a!=7 && a!=8 && a!=9){
                    System.out.println("Invalid input, Please select a valid option");
                a=sc.nextInt();
        }
            switch(a){
                    case 1:
                        System.out.println("\nName:"+ad.getName()+"\nEmailId:"+ad.getEmailID() +"\nStudentId:"+ad.getFacultyID()+"\nCity:"+ad.getCity()+"\nCountry:"+ad.getCountry());
                    adminFunction();
                    break;
                case 2:
                    as.displayCourseTeaching();
                    adminFunction();
                    break;
                case 3:
                    //view marks list
                    as.displayMarksList();
                    
                    adminFunction();
                    break;
                case 4:
                    //make tests
                    System.out.println("A test has been created.");
                    adminFunction();
                    break;
                case 5:
                    //input upcoming Events
                    System.out.println("How many upcoming events, do you want to add");
                    int n=sc.nextInt();
                    ArrayList<String> upEv=new ArrayList<>();
                    System.out.println("Enter the events");
                    for(int i=0;i<=n;i++) {
                            upEv.add(sc.nextLine());
                    }
                    as.setUpcomingEvents(upEv);
                    adminFunction();
                    break;
                case 6:
                    //input important links
                    System.out.println("How many important links, do you want to add");
                    int n1=sc.nextInt();
                    ArrayList<String> imLi=new ArrayList<>();
                    System.out.println("Enter the links");
                    for(int i=0;i<=n1;i++) {
                            imLi.add(sc.nextLine());
                    }
                    as.setImportantLinks(imLi);
                    adminFunction();
                    break;
                case 7:
                    //input notices
                    System.out.println("How many notices, do you want to add");
                    int n2=sc.nextInt();
                    ArrayList<String> not=new ArrayList<>();
                    System.out.println("Enter the links");
                    for(int i=0;i<=n2;i++) {
                          not.add(sc.nextLine());
                    }
                    as.setNotice(not);
                    adminFunction();
                    break;
                case 8:
                    //view student list
                    as.displayStudentsList();
                    adminFunction();
                    break;
                case 9:
                    System.out.println("\n Thank you for using the portal");
                    System.exit(0);
                    break;
        }
    }
        
        public static void login() {
                Scanner sc=new Scanner(System.in);
                FileWriter fw1;
                BufferedReader bw1;
                try {
                    fw1 = new FileWriter("studentDetails.txt",true);
                bw1 = new BufferedReader(new FileReader("studentDetails.txt"));
            System.out.println("Press 1 for Student or 2 for Admin");
            int a=sc.nextInt();
            sc.nextLine();
            while(a!=1 && a!=2) {
                    System.out.println("\nInvalid input, kindly press again");
                a=sc.nextInt();
                sc.nextLine();
        }
            if(a==1) {
                    System.out.println("\nPress 1 to signIn or 2 to signUp");
                int b=sc.nextInt();
                sc.nextLine();
                while(b!=1 && b!=2) {
                        System.out.println("\nInvalid input, kindly press again");
                    b=sc.nextInt();
                    sc.nextLine();
            }
                if(b==1) {
                        System.out.println("\nEnter emailID");
                    String emailID=sc.next();
                    System.out.println("\nEnter password");
                    String password=sc.next();
                    String s1;
                    String[] studentDetails = new String[7];
                    // String s2 = null;
                    while((s1=bw1.readLine())!=null) {
                            studentDetails = s1.split(";");
                        if(studentDetails[0].equals(emailID) && studentDetails[1].equals(password)) break;
                }
                    if(s1==null) {
                            System.out.println("\nInvalid email address or password \n");
                        login();
                }else{
                            //call student wala function. 
                            studentFunction(studentDetails);
                    }
            }else {
                        System.out.println("Enter emailID");
                    String details= sc.nextLine();
                    System.out.println("Enter password");
                    details = details + ";" + sc.nextLine();
                    System.out.println("Enter Name");
                    details = details + ";" + sc.nextLine();
                    System.out.println("Enter Country");
                    details = details + ";" + sc.nextLine();
                    System.out.println("Enter City");
                    details = details + ";" + sc.nextLine();
                    System.out.println("Enter StudentId");
                    details = details + ";" + sc.nextLine()+";0";
                    //Code to input the emailID and password to a file
                    fw1.write(details+"\n");
                    fw1.close();
                    // fw2.close();
                    System.out.println("\n You can successfully signed Up, please sign In to use the portal. \n");
                    login();
            }
        }else {
                    adminFunction();
            }
        } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
            }
    }
        public static void main(String[] args) {
                // TODO Auto-generated method stub
                System.out.println("\t\t Welcome to the Course Management Portal\n");
            login();
        }
    }