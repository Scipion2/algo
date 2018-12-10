package GUI;

public class ZDialogInfo {

    private String NumberPoints, min_x, max_x, min_y, max_y;


    public ZDialogInfo(){}

    public ZDialogInfo(String NumberPoints, String min_x, String max_x, String min_y, String max_y){

        this.NumberPoints = NumberPoints;

        this.min_x = min_x;

        this.max_x = max_x;

        this.min_y = min_y;

        this.max_y = max_y;

    }


    public String toString(){

        String str;

        if(this.NumberPoints != null && this.min_x != null && this.max_y != null && this.max_x != null && this.min_y != null){

            str = "Parametres de la generation\n";

            str += "Nombre de point : " + this.NumberPoints + "\n";

            str += "X min : " + this.min_x + "\n";

            str += "X max : " + this.max_x + "\n";

            str += "Y min : " + this.min_y + "\n";

            str += "Y max : " + this.max_y + "\n";

        }

        else{

            str = "Aucune information !";

        }

        return str;

    }


    public String[] get()
    {

        String[] result=new String[5];

        result[0]=NumberPoints;
        result[1]=min_x;
        result[2]=max_x;
        result[3]=min_y;
        result[4]=max_y;

        return result;

    }

}