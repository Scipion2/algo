package utils;

import triangulation.Point;

import java.util.List;

public class QuickSort
{

    public static int positionX(List<Point> toSort, int pivot, int begin, int last)
    {

        int small=begin-1,ppg=begin;

        for(int i=begin;i<last;++i)
        {

            if(toSort.get(i).getX()<pivot)
            {

                swapList(toSort,i,small+1);
                swapList(toSort,i,ppg);
                small++;
                ppg++;

            }else if(toSort.get(i).getX()==pivot)
            {

                swapList(toSort,i,ppg);
                ppg++;

            }

        }

        return (small+1);
    }

    public static int positionY(List<Point> toSort, int pivot, int begin, int last)
    {

        int small=begin-1,ppg=begin;

        for(int i=begin;i<last;++i)
        {

            if(toSort.get(i).getY()>pivot)
            {

                swapList(toSort,i,small+1);
                swapList(toSort,i,ppg);
                small++;
                ppg++;

            }else if(toSort.get(i).getY()==pivot)
            {

                swapList(toSort,i,ppg);
                ppg++;

            }

        }

        return (small+1);
    }


    public static void quickSortX (List<Point> toSort,int begin, int last){

        if(begin<last)
        {

            int pos=positionX(toSort,toSort.get(begin).getX(),begin,last);
            quickSortX(toSort,begin,pos);
            quickSortX(toSort,pos+1,last);


        }
    }


    public static void quickSortY (List<Point> toSort,int begin, int last){

        if(begin<last)
        {

            int pos=positionY(toSort,toSort.get(begin).getY(),begin,last);
            quickSortY(toSort,begin,pos);
            quickSortY(toSort,pos+1,last);


        }
    }

    public static void quickSort (List<Point> toSort,int begin, int last){

        quickSortX(toSort, begin, last); //quicksort sur les X

        int indexTemp=1;
        int index = 1;
        boolean tosort = false;

        while(index < toSort.size()){

            indexTemp = index;
            while(toSort.get(index).getX() == toSort.get(index-1).getX() && index < toSort.size()-1){

                tosort = true;
                index++;

            }

            begin = indexTemp-1;
            last = index;


            if(tosort) {
                quickSortY(toSort, begin, last); //quicksort sur les Y parmis un groupe de X identiques
                tosort = false;
            }
            else{
                index++;
            }

        }


    }



    public static void swapList(List<Point> src,int index1,int index2)
    {

        Point temp=src.get(index1);
        src.set(index1,src.get(index2));
        src.set(index2,temp);

    }

}
