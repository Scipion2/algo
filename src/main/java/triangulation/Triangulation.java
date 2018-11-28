package triangulation;

public class Triangulation {

    GeneratePoints pointsMatrix;

    public Triangulation(GeneratePoints generatePoints){
        this.pointsMatrix = generatePoints;
    }


    public static void main(String[] args){
        Triangulation triangulation = new Triangulation(new GeneratePoints(100,0, 500, 0, 500));
    }

}


/*

public class Triangulation
{
    
    List <Object> cloud;
    
    public Triangulation(List <Object> src)
    {
        
        this.cloud=src;
        
    }
    
}


*/