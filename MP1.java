import java.io.File;
import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class MP1 {
    Random generator;
    String userName;
    String inputFileName;
    String delimiters = " \t,;.?!-:@[](){}_*/";
    String[] stopWordsArray = {"i", "me", "my", "myself", "we", "our", "ours", "ourselves", "you", "your", "yours",
            "yourself", "yourselves", "he", "him", "his", "himself", "she", "her", "hers", "herself", "it", "its",
            "itself", "they", "them", "their", "theirs", "themselves", "what", "which", "who", "whom", "this", "that",
            "these", "those", "am", "is", "are", "was", "were", "be", "been", "being", "have", "has", "had", "having",
            "do", "does", "did", "doing", "a", "an", "the", "and", "but", "if", "or", "because", "as", "until", "while",
            "of", "at", "by", "for", "with", "about", "against", "between", "into", "through", "during", "before",
            "after", "above", "below", "to", "from", "up", "down", "in", "out", "on", "off", "over", "under", "again",
            "further", "then", "once", "here", "there", "when", "where", "why", "how", "all", "any", "both", "each",
            "few", "more", "most", "other", "some", "such", "no", "nor", "not", "only", "own", "same", "so", "than",
            "too", "very", "s", "t", "can", "will", "just", "don", "should", "now"};

    void initialRandomGenerator(String seed) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA");
        messageDigest.update(seed.toLowerCase().trim().getBytes());
        byte[] seedMD5 = messageDigest.digest();

        long longSeed = 0;
        for (int i = 0; i < seedMD5.length; i++) {
            longSeed += ((long) seedMD5[i] & 0xffL) << (8 * i);
        }

        this.generator = new Random(longSeed);
    }

    Integer[] getIndexes() throws NoSuchAlgorithmException {
        Integer n = 10000;
        Integer number_of_lines = 50000;
        Integer[] ret = new Integer[n];
        this.initialRandomGenerator(this.userName);
        for (int i = 0; i < n; i++) {
            ret[i] = generator.nextInt(number_of_lines);
        }
        return ret;
    }

    public MP1(String userName, String inputFileName) {
        this.userName = userName;
        this.inputFileName = inputFileName;
    }

    public String[] process() throws Exception {
        String[] ret = new String[20];
      
        
        String s[][]=new String[30][200];
        String st ="";
        File filepath=new File(inputFileName);
        FileInputStream fin=new FileInputStream(filepath);
        //FileInputStream fin1=new FileInputStream(filepath);
        int i,j=0,k=0,count=1;
        
        String st1="",st2="";
        List l=new ArrayList();
        while((i=fin.read())!=-1)
        {
                
                st=st+(char)i;
                char c =(char)i;
                if(c=='\n')
                {
                    StringTokenizer str=new StringTokenizer(st,delimiters);
                    while(str.hasMoreTokens())
                    {
                      StringBuilder sb=new StringBuilder(str.nextToken().toLowerCase().trim());
                 //if(Character.isWhitespace(sb.charAt(0)))
                  //System.out.print("hello");
                     String stt=sb.toString();
                    l.add(stt);
                    count++;
                    }
                    
                    st="";
                }
        }   
        
        HashSet m=new HashSet();
        Collections.sort(l);
         System.out.println(l);
         j=1;
         for(int f=0;f<count-1;f++)
         {
             String st5=l.get(f).toString();
               for(int ft=0;ft<count-1;ft++)
               {
           String st6=l.get(ft).toString();
             if(st5.equalsIgnoreCase(st6))
             {
                 ++k;
               }
           
               }  
               
             //System.out.println(l.get(f).toString()+k);
               m.add((st5+k));
                 k=0;
                 
                // j++;
                 
                      }
             //System.out.println(t.toString()+count);ject
         
    //Collection stmap = m.values()
         
    Iterator itr =m.iterator();
    while(itr.hasNext())
      System.out.println(itr.next());
            System.out.println("finished");
        
          
        //TODO

        return ret;
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1){
            System.out.println("MP1 <User ID>");
        }
        else {
            String userName = args[0];
            String inputFileName = "./input.txt";
            MP1 mp = new MP1(userName, inputFileName);
            String[] topItems = mp.process();
            for (String item: topItems){
                System.out.println(item);
            }
        }
    }
}
