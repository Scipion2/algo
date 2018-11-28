package utils;

import triangulation.Point;

import java.util.List;

public class QuickSort
{



    public static int position(List<Point> toSort, int pivot, int begin, int last)
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


    public static void quickSort (List<Point> toSort,int begin, int last)
    {
        if(begin<last)
        {

            int pos=position(toSort,toSort.get(begin).getX(),begin,last);
            quickSort(toSort,begin,pos);
            quickSort(toSort,pos+1,last);


        }
    }

    public static void swapList(List<Point> src,int index1,int index2)
    {

        Point temp=src.get(index1);
        src.set(index1,src.get(index2));
        src.set(index2,temp);

    }

}
