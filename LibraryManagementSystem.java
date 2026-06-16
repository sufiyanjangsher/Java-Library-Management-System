import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
class Book{
    int book_id;
    String book_name;
    String author_name;
    boolean is_issued;
    public void printdetails(){
        System.out.println("id: "+ book_id);
        System.out.println("name: "+ book_name);
        System.out.println("author: "+ author_name);
        System.out.println("is issued: "+ is_issued);
    }
}
class LibraryManagementSystem{
    public static void loaddetails(ArrayList<Book> l) throws Exception{
       try{ BufferedReader br=new BufferedReader(new FileReader("Book.txt"));
        String line;
        while((line=br.readLine())!=null){
            String data[]=line.split(",");
            Book b=new Book();
            b.book_id=Integer.parseInt(data[0]);
            b.book_name=data[1];
            b.author_name=data[2];
            b.is_issued=Boolean.parseBoolean(data[3]);
            l.add(b);
        }
        br.close();
    }catch(Exception e){
        System.out.println("no previous record found");
    }
    }
    public static void savedetails(ArrayList<Book> l) throws Exception{
        BufferedWriter bw=new BufferedWriter(new FileWriter("Book.txt"));
        for(int i=0;i<l.size();i++){
            Book b=l.get(i);
            bw.write( b.book_id + "," +
                      b.book_name + "," +
                      b.author_name + "," +
                     b.is_issued
                    );
            bw.newLine();
        }
        bw.close();
        System.out.println("data saved successfully");
    }
    public static void main(String arg[]) throws Exception{
        ArrayList<Book> l=new ArrayList<>();
        loaddetails(l);
        Scanner sc=new Scanner(System.in);
        int choice;
        do{
        System.out.println("enter 1 for adding books");
        System.out.println("enter 2 view all deatails");
        System.out.println("enter 3 for searching");
        System.out.println("enter 4 for issue");
        System.out.println("enter 5 to return");
        System.out.println("enter 6 for delete");
        // System.out.println("enetr 7 for save deatils");
        System.out.println("enter 0 to exit");
         choice=sc.nextInt();
        if(choice==1){
        System.out.println("how many books you want to add");
        int a=sc.nextInt();
        for(int i=0;i<a;i++){
        Book b=new Book();
        System.out.println("enter book id");
        b.book_id=sc.nextInt();
        sc.nextLine();
        System.out.println("enter book name");
        b.book_name=sc.nextLine();
        System.out.println("enter author name");
        b.author_name=sc.nextLine();
        b.is_issued=false;;
        l.add(b);
        
        }
    }
    else if(choice==2){
        if(l.isEmpty()){
            System.out.println("no record found");

        }
        else {
        for(int i=0;i<l.size();i++){
           Book b=l.get(i);
            b.printdetails();
        }
    }
    }
    else if(choice==3){
        System.out.println("enter book id to search");
        int id=sc.nextInt();
        boolean found = false;
        for(int i=0;i<l.size();i++){
            Book b=l.get(i);
            
            if(id==b.book_id){
                b.printdetails();
                found = true;
                break;
            }

        }
        if(!found){
            System.out.println("not found");
        }
    }
    else if(choice==4){
        System.out.println("enter book you want to issue");
        int id=sc.nextInt();
         boolean found=false;
        for(int i=0;i<l.size();i++){
            Book b=l.get(i);
           if(id==b.book_id){
            if(b.is_issued==false){
                System.out.println("available");
                System.out.println("to issue this book give ur roll num");
                int num=sc.nextInt();
                System.out.println("issued by: " + num);
                b.is_issued=true;
                found= true;
                break ;

            }
            else {
                System.out.println("not avaialable");
            }
        }
        }
    
        if(!found){
            System.out.println("not found");
        }
    }
         else  if(choice==5){
        System.out.println("enter book id which you want to return");
        int id=sc.nextInt();
         boolean found=false;
        for(int i=0;i<l.size();i++){
        Book b=l.get(i);
       
        if(id==b.book_id){
            if(b.is_issued==true){
                b.is_issued=false;
            System.out.println("returned");
            found=true;
            break ;
            }
            else {
                System.out.println("already returned");
            }
        }
        }
        if(!found){
            System.out.println("not found");
        }
          }
         else if(choice==6){
        System.out.println("enter book id for delete");
        int id=sc.nextInt();
        boolean found=false;
        for(int i=0;i<l.size();i++){
            Book b=l.get(i);
            if(id==b.book_id){
                l.remove(i);
                System.out.println("deleted");
                found=true;
                break;
            }
        }
        if(!found){
            System.out.println("not found");
        }
    }
    // else if(choice==7){
    //     savedetails(l);
    //}
    else {
        System.out.println("invalid choice");
    }
}while(choice!=0);
savedetails(l);
     System.out.println("exited the program");
        
    }
}