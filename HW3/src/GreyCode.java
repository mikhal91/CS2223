import java.util.LinkedList;

public class GreyCode {

    public static void main(String[] args) {
        GreyCode og=new GreyCode();
        og.clownshow(5);
    }



    public void clownshow(int n){
        GreyCode oog = new GreyCode();
        LinkedList<String> tolook=oog.BRGC(n);
        System.out.println(tolook.size());
        System.out.println(tolook.get(0));
        String movement=tolook.get(0);
        System.out.println(tolook.get(0).length());
        LinkedList<String> L=new LinkedList<>();
        int d=0;
        for (int i = 1; i <=movement.length(); i++) {
            if (i%n==0){
                L.add(movement.substring(d,i));
                d=i;
            }
        }
        boolean mon1 =false;
        boolean mon2 =false;
        boolean mon3 =false;
        boolean mon4 =false;
        boolean mon5 =false;
        String toadd="";
        int index =0;
        for (String s:L) {
            if (s.equals("00000")){
                toadd+="Spotlight";
            }
            if (s.charAt(n-1)=='1'&&!mon1){
                toadd+="Axel Pedals ";
                mon1=true;
            }
            if (s.charAt(n-1)=='0'&&mon1){
                toadd+="Axel leaves ";
                mon1=false;
            }
            if (s.charAt(n-2)=='1'&&!mon2){
                toadd+="Boxo joins ";
                mon2=true;
            }
            if (s.charAt(n-2)=='0'&&mon2){
                toadd+="Boxo leaves ";
                mon2=false;
            }
            if (s.charAt(n-3)=='1'&&!mon3){
                toadd+="Alex joins ";
                mon3=true;
            }
            if (s.charAt(n-3)=='0'&&mon3){
                toadd+="Alex leaves ";
                mon3=false;
            }
            if (s.charAt(n-4)=='1'&&!mon4){
                toadd+="Crunchy joins ";
                mon4=true;
            }
            if (s.charAt(n-4)=='0'&&mon4){
                toadd+="Crunchy leaves ";
                mon4=false;
            }
            if (s.charAt(0)=='1'&&!mon5){
                toadd+="Enzo joins ";
                mon5=true;
            }
            if (s.charAt(0)=='0'&&mon5){
                toadd+="Enzo leaves ";
                mon5=false;
            }
            if (s.equals("10000")){
                toadd+="and Enzo crashes";
            }
            System.out.println("At index "+index+" Graycode is "+s+" and "+toadd);
            toadd="";
            index++;
        }
    }
    public LinkedList<String> BRGC(int n){
        if (n==1){
            LinkedList<String> L= new LinkedList<>();
            L.add("01");
            return L;
        }
        LinkedList<String> L1= BRGC(n-1);
        //System.out.println("we are working with "+L1.get(0));
        LinkedList<String> L2= new LinkedList<>();
        String revresd="";
        String toreverse=L1.get(0);
        for (int i = 0; i <toreverse.length(); i++) {
            revresd=toreverse.charAt(i)+revresd;
        }
        //System.out.println("reversed it is, "+revresd);
        int d=0;
        L1.clear();
        for (int i = 1; i<=toreverse.length(); i++) {
            if (i%(n-1)==0){
                //System.out.println(i+"%("+n+"-"+1+") is 0, so we're adding 0"+toreverse.substring(d,i)+" to L1");
                L1.add("0"+toreverse.substring(d,i));
                d=i;
            }
        }
        d=0;
        for (int i = 1; i<=L1.size(); i++) {
            //System.out.println("We are adding to L2, "+"1"+(L1.get(L1.size()-i).substring(1)));
            L2.add( "1"+(L1.get(L1.size()-i).substring(1)));
        }
        LinkedList<String> L= new LinkedList<>();
        String Megal1="";
        for (int i = 0; i <L1.size(); i++) {
            Megal1+=L1.get(i);
        }
        String Megal2="";
        for (int i = 0; i <L2.size(); i++) {
            Megal2+=L2.get(i);
        }
        //System.out.println(Megal1+" this is L1 total");
        //System.out.println(Megal2+" this is L2 total");
        L.add(Megal1);
        L.add(Megal2);
        LinkedList<String> L3= new LinkedList<>();
        L3.add(L.get(0));
        L3.set(0,L.get(0)+L.get(1));
        return L3;

    }
}