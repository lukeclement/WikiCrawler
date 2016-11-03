import java.util.*;
import java.net.*;
import java.io.*;
class main{
    public static void main(String[] args){
        List<String> links = new ArrayList<>();
        links.add("Main_Page");
        for(int po=0;po<links.size();po++){
              URL url;
              InputStream is=null;
              BufferedReader br;
              String line;
              List<String> lines=new ArrayList<>();
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
              System.out.println(lines.size());
          for(int i=0;i<lines.size();i++){
            for(int j=0;j<lines.get(i).length()-25;j++){
              if(lines.get(i).substring(j,j+15).equals("<a href=\"/wiki/")
              &&!lines.get(i).substring(j+15,j+20).equals("File:")
              &&!lines.get(i).substring(j+15,j+23).equals("Special:")
              &&!lines.get(i).substring(j+15,j+20).equals("Help:")
              &&!lines.get(i).substring(j+15,j+20).equals("Talk:")
              &&!lines.get(i).substring(j+15,j+22).equals("Portal:")
              &&!lines.get(i).substring(j+15,j+24).equals("Category:")
              &&!lines.get(i).substring(j+15,j+25).equals("Wikipedia:")){
                int end=j+15;
                while(!lines.get(i).substring(end,end+1).equals("\"")){
                  end++;
                }
                if(!links.contains(lines.get(i).substring(j+15,end))){
                  links.add(lines.get(i).substring(j+15,end));
                  System.out.println("Link to "+lines.get(i).substring(j+15,end));
                }
              }
            }
          }
        }
    }
}
