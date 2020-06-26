import com.sun.jdi.StringReference;

public class Texas {

    //是否是同花
    public static boolean Issamecolor(String str)
    {
        String[] strsplit = str.split(",");
        if (strsplit[0].charAt(1) == strsplit[1].charAt(1) && strsplit[0].charAt(1) == strsplit[2].charAt(1) &&
                strsplit[0].charAt(1) == strsplit[3].charAt(1) && strsplit[0].charAt(1) == strsplit[4].charAt(1) )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    //是否是顺子
    public static boolean Isfrequent(String str)
    {
        String[] strsplit = str.split(",");
        int[] temp = new int[5];

        for(int i = 0; i < 4; i++) {
            temp[i] = strsplit[i].charAt(0);
        }
        for(int i = 0; i < temp.length-1; i++) {
            for(int j = 0; j < temp.length-1-i; j++) {
                if (temp[j+1] < temp[j]) {
                    int temp1 = temp[j];
                    temp[j] = temp[j+1];
                    temp[j+1] = temp1;
                }
            }
        }
        for(int i = 1; i < 5; i++) {
            if (temp[i] - temp[i - 1] != 1) {
                return false;
            }
        }

        return true;
    }
    //是否是三条或四条
    public static boolean Issame(String str,int flag)
    {
        String[] strsplit = str.split(",");

        for(int i = 0; i < 5; i++) {
            int count = 0;
            int temp = getWeight(strsplit[i].charAt(0));
            for (int j = 0; j < 5; j++) {
                if (temp == getWeight(strsplit[j].charAt(0))) {
                    count++;
                    if (count == flag)
                        return true;
                }
            }
        }
        return false;
    }
    //是否是葫芦
    public static boolean Iscalabash(String str)
    {

        return Issame(str,3)&&Isonepair(str) == true ? true:false;
    }
    //是否是两对
    public static boolean Istwopair(String str)
    {
        String[] strsplit = str.split(",");
        int flag = 0;
        for(int i = 1; i < 5; i++) {
            if(strsplit[0].charAt(0) == strsplit[i].charAt(0)) {
                flag++;
            }
        }

        if(flag == 3)
            return true;
        return false;
    }
    //是否是对子
    public static boolean Isonepair(String str)
    {
        String[] strsplit = str.split(",");
        int[] temp = new int[5];
        int count =0;
        for(int i = 0; i < 4; i++) {
            temp[i] = getWeight(strsplit[i].charAt(0));
        }
        for(int i = 0; i < 5; i++) {
            for(int j = 0; j < 5; j++) {
                if (temp[i] == temp[j]&&i!=j) {
                    count++;
                }
            }
        }
        if (count == 2) {
            return true;
        }
        return false;
    }
    //获得权值
    public static int getWeight(char ch)
    {
        int weight;
        switch (ch){
            case 'J':   weight = 11;break;
            case 'Q':   weight = 12;break;
            case 'K':   weight = 13;break;
            case 'A':   weight = 14;break;
            default:    weight = ch - '0';break;
        }
        return  weight;
    }
    //获得对应牌型中的权值
    public static int getthebiggestoneoftype(String str, int flag)
    {
        int[] weight = new int[5];
        String[] strsplit = str.split(",");
        for(int i = 0;i < 5;i++) {
            weight[i] = getWeight(strsplit[i].charAt(0));
        }

        for(int i = 0; i < weight.length-1; i++) {
            for(int j = 0; j < weight.length-1-i; j++) {
                if (weight[j+1] < weight[j]) {
                    int temp1 = weight[j];
                    weight[j] = weight[j+1];
                    weight[j+1] = temp1;
                }
            }
        }
        switch (flag){
            //散排
            case 0: return weight[4];
            //四条
            case 1: return weight[2];
            //一对牌型
            case 2:
                for(int i = 0;i < 5;i++) {
                    for (int j = 0; j < 5; j++) {
                        if(i != j && weight[i] == weight[j])
                        {
                            return weight[i];
                        }
                    }
                }

            //三条
            case 3: return weight[2];
            //两对牌型
            //大对子
            case 4: return weight[3];
            //小对子
            case 5: return weight[1];
            //葫芦
            case 6: return weight[1] == weight[2] ? weight[2]:weight[3];
            //顺子
            case 7: return weight[4];
        }
        return 0;
    }

    //获得对应牌中除牌型外的的最大的点数(顺子，四条，三条，对子，两对，单牌）
    public static int gethighestoneoutoftype(String str, int flag) {
        int[] weight = new int[5];
        String[] strsplit = str.split(",");
        for(int i = 0;i < 5;i++) {
            weight[i] = getWeight(strsplit[i].charAt(0));
        }

        for(int i = 0; i < weight.length-1; i++) {
            for(int j = 0; j < weight.length-1-i; j++) {
                if (weight[j+1] < weight[j]) {
                    int temp1 = weight[j];
                    weight[j] = weight[j+1];
                    weight[j+1] = temp1;
                }
            }
        }
        switch (flag){
            //散排
            case 0: return weight[4];
            //四条
            case 1: return weight[0]!=weight[1] ? weight[0]:weight[4];
            //一对牌型
            case 2:
            {
                for(int i = 0;i < 5;i++) {
                    for (int j = 0; j < 5; j++) {
                        if(i != j && weight[i] == weight[j])
                        {
                            return weight[i-1];
                        }
                    }
                }
            }
            //三条
            case 3: return weight[3]!=weight[4] ? weight[4]:weight[1];
            //两对牌型
            case 4:
            {
                for(int i = 0; i < 4;i++)
                {
                    int sys = 0;
                    for(int j = 0; j < 4;j++)
                    {
                        if(weight[i]==weight[j]&&i!=j)
                        {
                           sys = 1;
                           break;
                        }
                    }
                    if (sys == 0)
                    {
                        return weight[i];
                    }
                }
            }
            //葫芦
            case 6: return weight[1]==weight[2] ? weight[3]:weight[2];
            //散排第2~5大值
            case 7: return weight[3];
            case 8: return weight[2];
            case 9: return weight[1];
            case 10: return weight[0];
            //对子
            case 11: return weight[3]==weight[4] ? weight[2] : weight[4];
            case 12:
            {
                for(int i = 0; i < 4;i++)
                {
                    if(weight[i]!=getthebiggestoneoftype(str,2) && weight[i]!=gethighestoneoutoftype(str,11) && weight[i]!=gethighestoneoutoftype(str,13))
                        return weight[i];
                }
            }
            case 13: return weight[0]==weight[1] ? weight[2] : weight[0];


        }
        return 0;
    }
    public static int getvalue(String str)
    {
        int weight;
        if((Issamecolor(str) && Isfrequent(str))==true)
            weight = 9;
        else if(Issame(str,4) == true)
            weight = 8;
        else if(Iscalabash(str) == true)
            weight = 7;
        else if(Issamecolor(str) == true)
            weight = 6;
        else if(Isfrequent(str)==true)
            weight = 5;
        else if(Issame(str,3)==true)
            weight = 4;
        else if(Istwopair(str)==true)
            weight = 3;
        else if(Isonepair(str)==true)
            weight = 2;
        else
            weight = 1;
        return weight;
    }
    public static int check(String str1,String str2)
    {
        String[] str1split = str1.split(",");
        String[] str2split = str2.split(",");
        for (String string1 : str1split)
        {
            for (String string2 : str2split)
            {
                if(string1.compareTo(string2) == 0)
                {
                    return -1;
                }
            }
        }
        return 1;
    }
    public static int texasCompare(String str1, String str2 ) {

        String[] str1split = str1.split(",");
        String[] str2split = str2.split(",");
        if(check(str1,str2) == -1)
            return -1;
        int  weight1 = getvalue(str1);
        int  weight2 = getvalue(str2);
        if(weight1 > weight2)
            return 1;
        else if(weight1 < weight2)
            return 2;
        else
        {
            switch (weight1)
            {
                case 9:
                    if(getthebiggestoneoftype(str1,7) > getthebiggestoneoftype(str2,7))
                    {
                        return 1;
                    }
                    else if(getthebiggestoneoftype(str1,7) < getthebiggestoneoftype(str2,7))
                    {
                        return 2;
                    }
                    else
                    {
                        return 0;
                    }
                case 8: return -1;
                case 7:
                    if(getthebiggestoneoftype(str1,6) > getthebiggestoneoftype(str2,6))
                    {
                        return 1;
                    }
                    else if(getthebiggestoneoftype(str1,6) < getthebiggestoneoftype(str2,6))
                    {
                        return 2;
                    }
                    else
                    {
                        return -1;
                    }
                case 6:
                    if(getthebiggestoneoftype(str1,0) > getthebiggestoneoftype(str2,0))
                    {
                        return 1;
                    }
                    else if(getthebiggestoneoftype(str1,0) < getthebiggestoneoftype(str2,0))
                    {
                        return 2;
                    }
                    else
                    {
                        return -1;
                    }
                case 5:
                    if(getthebiggestoneoftype(str1,7) > getthebiggestoneoftype(str2,7))
                    {
                        return 1;
                    }
                    else if(getthebiggestoneoftype(str1,7) < getthebiggestoneoftype(str2,7))
                    {
                        return 2;
                    }
                    else
                    {
                        return -1;
                    }
                case 4:
                    if(getthebiggestoneoftype(str1,3) > getthebiggestoneoftype(str2,3))
                    {
                        return 1;
                    }
                    else if(getthebiggestoneoftype(str1,3) < getthebiggestoneoftype(str2,3))
                    {
                        return 2;
                    }
                    else
                    {
                        return -1;
                    }
                case 3:
                    if(getthebiggestoneoftype(str1,4) > getthebiggestoneoftype(str2,4))
                    {
                        return 1;
                    }
                    else if(getthebiggestoneoftype(str1,4) < getthebiggestoneoftype(str2,4))
                    {
                        return 2;
                    }
                    else
                    {
                        if(getthebiggestoneoftype(str1,5) > getthebiggestoneoftype(str2,5))
                        {
                            return 1;
                        }
                        else if(getthebiggestoneoftype(str1,5) < getthebiggestoneoftype(str2,5))
                        {
                            return 2;
                        }
                        else
                        {
                            if(gethighestoneoutoftype(str1,4) > getthebiggestoneoftype(str2,4))
                            {
                                return 1;
                            }
                            else if(getthebiggestoneoftype(str1,4) < getthebiggestoneoftype(str2,4))
                            {
                                return 2;
                            }
                            else
                            {
                                return 0;
                            }
                        }
                    }
                case 2:
                {
                    if(getthebiggestoneoftype(str1,2) > getthebiggestoneoftype(str2,2))
                    {
                        return 1;
                    }
                    else if(getthebiggestoneoftype(str1,2) < getthebiggestoneoftype(str2,2))
                    {
                        return 2;
                    }
                    else
                    {
                        if(gethighestoneoutoftype(str1,11) > getthebiggestoneoftype(str2,11))
                        {
                            return 1;
                        }
                        else if(getthebiggestoneoftype(str1,11) < getthebiggestoneoftype(str2,11))
                        {
                            return 2;
                        }
                        else
                        {
                            if(gethighestoneoutoftype(str1,12) > getthebiggestoneoftype(str2,12))
                            {
                                return 1;
                            }
                            else if(getthebiggestoneoftype(str1,12) < getthebiggestoneoftype(str2,12))
                            {
                                return 2;
                            }
                            else
                            {
                                if(gethighestoneoutoftype(str1,13) > getthebiggestoneoftype(str2,13))
                                {
                                    return 1;
                                }
                                else if(getthebiggestoneoftype(str1,13) < getthebiggestoneoftype(str2,13))
                                {
                                    return 2;
                                }
                                else
                                {
                                    return 0;
                                }
                            }
                        }
                    }
                }
                case 1:
                {
                    if(getthebiggestoneoftype(str1,0) > getthebiggestoneoftype(str2,0))
                    {
                        return 1;
                    }
                    else if(getthebiggestoneoftype(str1,0) < getthebiggestoneoftype(str2,0))
                    {
                        return 2;
                    }
                    else
                    {
                        if(gethighestoneoutoftype(str1,7) > getthebiggestoneoftype(str2,7))
                        {
                            return 1;
                        }
                        else if(getthebiggestoneoftype(str1,7) < getthebiggestoneoftype(str2,7))
                        {
                            return 2;
                        }
                        else
                        {
                            if(gethighestoneoutoftype(str1,8) > gethighestoneoutoftype(str2,8))
                            {
                                return 1;
                            }
                            else if(gethighestoneoutoftype(str1,8) < gethighestoneoutoftype(str2,8))
                            {
                                return 2;
                            }
                            else
                            {
                                if(gethighestoneoutoftype(str1,9) > gethighestoneoutoftype(str2,9))
                                {
                                    return 1;
                                }
                                else if(gethighestoneoutoftype(str1,8) < gethighestoneoutoftype(str2,8))
                                {
                                    return 2;
                                }
                                else
                                {
                                    if(gethighestoneoutoftype(str1,9) > gethighestoneoutoftype(str2,9))
                                    {
                                        return 1;
                                    }
                                    else if(gethighestoneoutoftype(str1,8) < gethighestoneoutoftype(str2,8))
                                    {
                                        return 2;
                                    }
                                    else
                                    {
                                        return 0;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    return -1;
    }
}