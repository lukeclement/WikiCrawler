import java.util.*;
import java.io.*;
import java.net.*;

public class Interpret{
  public static URL search(String term){
    try{
    return new URL("https://en.wikipedia.org/wiki/Test_(assessment)");
}catch(Exception e){return null;}
  }

  public static void main(String[] args){
    Scanner scan=new Scanner(System.in);
    System.out.println("What do you want written about?");
    System.out.print(">>");
    String sTerm=scan.nextLine();
    URL url;
    List<String> lines=new ArrayList<>();
    InputStream is=null;
    BufferedReader br;
    String line;
    try{
      url=Interpret.search(sTerm);
      is = url.openStream();  // throws an IOException
      br = new BufferedReader(new InputStreamReader(is));

      while ((line = br.readLine()) != null) {
        lines.add(line);
        //System.out.println(line);
      }
    }
    catch(Exception e){
      System.out.println("Error!");
    }
    finally {
        try {
            if (is != null) is.close();
        } catch (IOException ioe) {

        }
    }
    List<String> liners=new ArrayList<>();
    List<String> ref=new ArrayList<>();
    List<Integer> point=new ArrayList<>();
    String total="";
    for(int i=0;i<lines.size();i++){
      for(int j=0;j<lines.get(i).length()-9;j++){
        if(lines.get(i).substring(j,j+3).equals("<p>")){
          int end=j+3;
          while(!lines.get(i).substring(end,end+4).equals("</p>")){
            end++;
          }
          liners.add(lines.get(i).substring(j+3,end));
        }
      }
    }
    point.add(0);
    for(int i=0;i<liners.size();i++){
      for(int j=0;j<liners.get(i).length()-25;j++){
        if(liners.get(i).substring(j,j+15).equals("<a href=\"/wiki/")
        &&!liners.get(i).substring(j+15,j+20).equals("File:")
        &&!liners.get(i).substring(j+15,j+23).equals("Special:")
        &&!liners.get(i).substring(j+15,j+20).equals("Help:")
        &&!liners.get(i).substring(j+15,j+20).equals("Talk:")
        &&!liners.get(i).substring(j+15,j+20).equals("User:")
        &&!liners.get(i).substring(j+15,j+22).equals("Portal:")
        &&!liners.get(i).substring(j+15,j+24).equals("Category:")
        &&!liners.get(i).substring(j+15,j+25).equals("Wikipedia:")){
          int endr=j+15;
          while(!liners.get(i).substring(endr,endr+1).equals("\"")){
            endr++;
          }
          ref.add(lines.get(i).substring(j+15,endr));
          point.add(j);
          point.add(endr);
        }

      }
    }
    for(int i=0;i<liners.size();i++){
      total=total+liners.get(i).substring(point.get(i*2),point.get((i*2)+1))+ref.get(i);
    }

  }
}
