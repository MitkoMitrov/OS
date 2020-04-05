import jdk.nashorn.internal.objects.Global;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

interface GlobalConstants
{
    String desktop = "..\\..\\";
}


public class demo implements GlobalConstants {
    public static void main(String[] args) throws IOException{
        writeAndRead();
    }

    // 1. Get list of all file/directory names
    static void ListAllFiles()
    {
        //
       File file = new File(".");
       String[] fileList = file.list();
       for(String name : fileList)
       {
           System.out.println(name);
       }
    }

    // 2. Get specific files by extensions from a specified folder
    static void getSpecificFiles()
    {
        File file = new File("..\\..\\");
        String[] list = file.list(new FilenameFilter()
        {
            @Override
            public boolean accept(File dir, String name)
            {
                if(name.toLowerCase().endsWith("txt") || name.toLowerCase().endsWith("docx")) // txt ili word
                {
                    return true;
                }
                else if(name.contains("Cs")) // ili vo imeto go sodrzi stringot "Cs"
                {
                    return true;
                }
                else{
                    return false;
                }
            }
        });
        for(String x : list)
        {
            System.out.println(x);
        }
    }

   // 3.Check if a file or directory specified by pathname exists or not
   static void checkIfFileExist()
   {
       File file = new File("..\\..\\codeblocks");
       if(file.exists()) // ako postoi true ako ne false ( i folderi i fajlovi)
       {
           System.out.println("true");
       }
       else
       {
           System.out.println("false");
       }
   }

   // 4.Check if a file or directory has read and write permission
    static void hasReadandWritePermission(){
        File my_file_dir = new File(desktop + " stopanska.txt");
        if(my_file_dir.canWrite())
        {
            System.out.println(my_file_dir.getAbsolutePath() + " can write ");
        }
        else
        {
            System.out.println(my_file_dir.getAbsolutePath() + " cannot write ");
        }
        if(my_file_dir.canRead())
        {
            System.out.println(my_file_dir.getAbsolutePath() + " can read\n");
        }
        else
        {
            System.out.println(my_file_dir.getAbsolutePath() + " cannot read");
        }

    }

    // 5.Check if given pathname is a directory or a file
    static void DirectoryOrFile(){
        File my_file_dir = new File(desktop);
        File[] list = my_file_dir.listFiles();
        for(File x: list) //foreach za site fajlovi od listata
        {
            if(x.isDirectory())
            {
                System.out.println(x.getAbsolutePath() + " is a directory");
            }
            else
            {
                System.out.println(x.getAbsolutePath() + " is not a directory");
            }
            if(x.isFile()) {
                System.out.println(x.getAbsolutePath() + " is a file");
            }
            else{
                System.out.println(x.getAbsolutePath() + " is not a file");
            }
        }
    }

    // 6.Get last modified time of a file
    static void getLastModified(){
        File file = new File(desktop);
        Date date = new Date(file.lastModified()); //koga posleden pat e izmenet fajlot
        System.out.println("\nThe file was last modified on: " +date);
    }

    // 7.Read input from java console
    static void ReadInput() throws IOException{
        BufferedReader R = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Input your name: ");
        String name = R.readLine();
        System.out.println("Your name is: " + name);

    }

    // 8.Get file size in bytes, kb, mb
    static void getFileSize(){
        File file = new File(desktop);
        File[] list = file.listFiles();
        /* metodi */


        for(File x : list)
        {
            System.out.println(x.getName() + " : " + filesize_in_megaBytes(x));
            System.out.println(x.getName() + " : " + filesize_in_kiloBytes(x));
            System.out.println(x.getName() + " : " + filesize_in_Bytes(x));
        }
    }
    private static String filesize_in_megaBytes(File file) {

        DecimalFormat dFormat = new DecimalFormat("0.00");

        return dFormat.format((double)file.length()/(1024*1024))+" Mb";
    }
    private static String filesize_in_kiloBytes(File file) {
        DecimalFormat dFormat = new DecimalFormat("0.00");

        return dFormat.format((double) file.length()/1024)+"  Kb";
    }
    private static String filesize_in_Bytes(File file) {
        return file.length()+" bytes";
    }

    // 9. Read contents from a file into byte array
    static void readContents() {
    /* --> PRV NACIN <--
        String file_name = desktop + "test.txt";

        InputStream fins = null;
        try{
            fins = new FileInputStream(file_name);
            byte file_content[] = new byte[2 + 1024];
            int read_count = 0;
            while((read_count = fins.read(file_content)) > 0)
            {
                System.out.println(new String(file_content, 0 , read_count -1 ));
            }
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(fins != null) fins.close();
            }catch(Exception ex){}
        }
     */
    /* --> VTOR NACIN <-- */
    File file = new File(desktop + "test.txt");
    FileInputStream fis = null;
    try {
         fis = new FileInputStream(file);
        System.out.println("(char)fis.read(): " + (char)fis.read()); // go prikazuva prviot karakter vo fajlot
         System.out.println("fis.read(): " + fis.read()); // go sodrzi vkupniot broj na bajti vo fajlot

         byte c;
         while((c = (byte)fis.read()) != -1)
         {
             System.out.print((char)c);
         }
    }catch(FileNotFoundException e){
        e.printStackTrace();
    }catch(IOException e){
        e.printStackTrace();
    }finally{
        try {
            if (fis != null) {
                fis.close();
            }
        }catch(Exception ex){}
    }


    }

    // 10.Read a file content line by line
    //Citanje linija po linija od txt file i stavanje vo String lista
    static void readLineByLine(){
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        List<String> list = new ArrayList<String>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(desktop + "test.txt"));
            while (strLine != null)
            {
                strLine = br.readLine();
                sb.append(strLine);
                sb.append(System.lineSeparator());
                list.add(strLine);
            }
            System.out.println(Arrays.toString(list.toArray()));
            br.close();
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
        }
    }

    // 11. Read a plain text file
    static void readPlain(){
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(desktop + "test.txt"));
            strLine = br.readLine();
            System.out.println("prvo: " + strLine); // go sodrzi priot red od text fajlot - se sto e do znakot za nov red
            while(strLine != null){
                sb.append(strLine);
                sb.append(System.lineSeparator());
                strLine = br.readLine();
                if(strLine == null) break; // bidejki e cela linija moze da stigne do null i samiot null da go isprinta
                else
                System.out.println(strLine);
            }
            br.close();
        } catch(FileNotFoundException e) {
            System.err.println("File not found");
        } catch(IOException e) {
            System.err.println("Unable to read the file");
        }
    }

    // 12. Read a file line by line and store it into a variable
    static void readAndStoreIntoVariable(){
        StringBuilder sb =  new StringBuilder();
        String strLine = "";
        String str_data = "";
        try{
            BufferedReader br = new BufferedReader(new FileReader(desktop + "test.txt"));
            while(strLine != null){
                if(strLine == null){
                    break;
                }
                str_data += strLine; // ke stavi se vo edna linija
                strLine = br.readLine(); //vcituva nov red, za narednata iteracija na while-ot
            }
            System.out.println(str_data);
            br.close();
        }catch(FileNotFoundException e){
            System.err.println("File not found");
        }catch(IOException e){
            System.err.println("Can't reach the file");
        }
    }

    // 13. Write and read a plain text file
    static void writeAndRead(){
        StringBuilder sb = new StringBuilder();
        String strLine = "";
        try
        {
            String filename= desktop + "test.txt";
            FileWriter fw = new FileWriter(filename,false);
            //appends the string to the file
            fw.write("Python Exercises\n");
            fw.close();
            BufferedReader br = new BufferedReader(new FileReader(desktop + "test.txt"));
            //read the file content
            while (strLine != null)
            {
                sb.append(strLine);
                sb.append(System.lineSeparator());
                strLine = br.readLine();
                System.out.println(strLine);
            }
            br.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
    }

}
