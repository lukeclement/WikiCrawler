import java.util.*;
import java.net.*;
import java.io.*;
public class main{
    public static void main(String[] args){
        List<String> links = new ArrayList<>();
        links.add("Main_Page");
        int show=1;
        int record=1;
        String fileName="links.txt";
        String fileNameTwo="number.txt";
        String fileNameThree="index.txt";
        String lining=null;
        int current=0;
        float percentTwo=0;
        try{
          FileReader fileReader = new FileReader(fileName);
          BufferedReader br=new BufferedReader(fileReader);
          while((lining=br.readLine())!=null){
            links.add(lining);
          }
          br.close();
        }catch(Exception ex){}
        try{
          FileReader fileReaderr = new FileReader(fileNameTwo);
          BufferedReader brr=new BufferedReader(fileReaderr);
          while((lining=brr.readLine())!=null){
            current=Integer.parseInt(lining);
          }
          brr.close();
        }catch(Exception ex){}

        //Node origin=new Node("Main_Page");
        List<Integer> topLinks=new ArrayList<>();
        System.out.println("Found "+links.size()+" starting at "+current);
        for(int po=current;po<links.size();po++){
              URL url;
              InputStream is=null;
              BufferedReader br;
              String line;
              List<String> lines=new ArrayList<>();
              int linksadded=0;
              try{
                FileWriter writer = new FileWriter(fileNameTwo, false);
                PrintWriter print_liner = new PrintWriter(writer);
                print_liner.printf( "%s" + "%n" , po);
                print_liner.close();
              }catch(Exception ex){}
              try {
                  url = new URL("https://en.wikipedia.org/wiki/"+links.get(po));
                  is = url.openStream();  // throws an IOException
                  br = new BufferedReader(new InputStreamReader(is));

                  while ((line = br.readLine()) != null) {
                      lines.add(line);
                      //System.out.println(line);
                  }
              } catch (MalformedURLException mue) {
                  mue.printStackTrace();
              } catch (IOException ioe) {
                  ioe.printStackTrace();
              } finally {
                  try {
                      if (is != null) is.close();
                  } catch (IOException ioe) {

                  }
              }
              //System.out.println(lines.size());
          for(int i=0;i<lines.size();i++){
            for(int j=0;j<lines.get(i).length()-25;j++){
              if(lines.get(i).substring(j,j+15).equals("<a href=\"/wiki/")
              &&!lines.get(i).substring(j+15,j+20).equals("File:")
              &&!lines.get(i).substring(j+15,j+23).equals("Special:")
              &&!lines.get(i).substring(j+15,j+20).equals("Help:")
              &&!lines.get(i).substring(j+15,j+20).equals("Talk:")
              &&!lines.get(i).substring(j+15,j+20).equals("User:")
              &&!lines.get(i).substring(j+15,j+22).equals("Portal:")
              &&!lines.get(i).substring(j+15,j+24).equals("Category:")
              &&!lines.get(i).substring(j+15,j+25).equals("Wikipedia:")){
                int end=j+15;
                while(!lines.get(i).substring(end,end+1).equals("\"")){
                  end++;
                }
                if(!links.contains(lines.get(i).substring(j+15,end))){
                  //if(show==record){
                    //record=show*2;
                    //System.out.println("Found "+show+" entries, currently searching link number "+po+" ("+links.get(po)+")");
                  //}
                  show++;
                  links.add(lines.get(i).substring(j+15,end));
                  try{
                    FileWriter write = new FileWriter(fileName, true);
                    PrintWriter print_line = new PrintWriter(write);
                    print_line.printf( "%s" + "%n" , lines.get(i).substring(j+15,end));
                    print_line.close();
                    FileWriter writerr = new FileWriter(fileNameThree, true);
                    PrintWriter print_linerr = new PrintWriter(writerr);
                    print_linerr.printf( "%s" + "%n" , lines.get(i).substring(j+15,end)+" from "+links.get(po));
                    print_linerr.close();
                  }catch(Exception ex){}
                  linksadded++;
                  //origin.addEdge(new Node(links.get(1)));
                  //System.out.println("Link to "+lines.get(i).substring(j+15,end));
                }
              }
            }
          }
          /*topLinks.add(linksadded);
          boolean test=true;
          for(int i=1;i<topLinks.size();i++){
            if(topLinks.get(i-1)>linksadded){
              test=false;
            }
          }
          if(test){
            System.out.println(links.get(po)+" has "+linksadded+" links (searched "+((float)po*100/(float)links.size())+"%)");
          }*/
          float percentOne=(float)po*100/(float)links.size();
          System.out.println(percentOne+"% Searched "+po+" links (just finished on "+links.get(po)+", added "+linksadded+" links) out of a total of "+links.size()+" links");
        }
    }
}
